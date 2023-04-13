package pl.bsiwallet.bsitest.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.bsiwallet.bsitest.entities.User;
import pl.bsiwallet.bsitest.interfaces.UserRepository;
import pl.bsiwallet.bsitest.utils.RandomStringGenerator;
import pl.bsiwallet.bsitest.utils.SecurityUtils;
import pl.bsiwallet.bsitest.utils.UserSession;
import pl.bsiwallet.bsitest.wrappers.PasswordEncryptionUpdateWrapper;
import pl.bsiwallet.bsitest.wrappers.UserRequestWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private FunctionRunService functionRunService;
    @Autowired
    private DataChangeService dataChangeService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void shouldCreateUser() {
        UserRequestWrapper wrapper = new UserRequestWrapper();
        wrapper.setLogin("login");
        wrapper.setPassword("password");
        wrapper.setIsHmac(true);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(new User());
        ResponseEntity<?> response = userService.createUser(wrapper);
        verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));

        User responseUser = (User)response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wrapper.getLogin(), responseUser.getLogin());
        assertEquals(wrapper.getIsHmac(), responseUser.getKeptAsHash());
        assertNotNull(responseUser);
    }

    @Test
    void shouldThrowErrorInsteadOfCreatingUser() {
        User user = new User("login","passwordHash", "salt", true);
        UserRequestWrapper wrapper = new UserRequestWrapper();
        wrapper.setLogin("login");
        wrapper.setPassword("password");
        wrapper.setIsHmac(true);
        String errorMessage = "Error occurred";

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(new RuntimeException(errorMessage));
        ResponseEntity<?> response = userService.createUser(wrapper);
        verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    public void shouldReturnAllUserList() {
        List<User> userList = new ArrayList<>();
        User user1 = new User("john", true);
        User user2 = new User("jane", true);
        userList.add(user1);
        userList.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(userList);
        ResponseEntity<?> response = userService.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    public void shouldThrowErrorInsteadReturningAllUsers() {
        Mockito.when(userRepository.findAll()).thenThrow(new RuntimeException("Error occurred"));
        ResponseEntity<?> response = userService.getAllUsers();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error occurred", response.getBody());
    }
    @Test
    void shouldReturnUserWhenFindingByLogin() {
        String login = "login";
        User user = new User(login, "password", "salt", false);

        Mockito.when(userRepository.findByLogin(Mockito.any(String.class))).thenReturn(Optional.of(user));
        Optional<User> response = userService.getUserByLogin(login);

        assertNotNull(response);
        assertEquals(user, response.get());
    }

    @Test
    void shouldSuccessfullyLogin() {
        String login = "testuser";
        String password = "password123";
        User user = new User(login, false);
        user.setSalt(RandomStringGenerator.generateRandomString());
        user.setPasswordHash(userService.calculatePasswordHash(password, user.getSalt(), user.getKeptAsHash()));

        Mockito.when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));
        boolean result = userService.login(login, password);

        assertTrue(result);
        assertTrue(UserSession.getSession().getIsValid());
        assertEquals(user, UserSession.getSession().getUser());
    }

    @Test
    void shouldNotLogin() {
        String login = "testuser";
        String password = "password123";

        boolean result = userService.login(login, password);

        assertFalse(result);
        assertFalse(UserSession.getSession().getIsValid());
        assertNull(UserSession.getSession().getUser());
    }

    @Test
    void shouldChangePassword() {
        User user = new User("testuser", false);
        user.setSalt("salt");
        user.setPasswordHash(SecurityUtils.calculateSha512("password" + user.getSalt()));
        String oldPassword = user.getPasswordHash();
        UserSession.getSession().setUser(user);

        PasswordEncryptionUpdateWrapper wrapper = new PasswordEncryptionUpdateWrapper();
        wrapper.setOldPassword(user.getPasswordHash());
        wrapper.setNewPassword(SecurityUtils.calculateSha512("newpassword" + user.getSalt()));

        // Create a mock RestTemplate instance
        RestTemplate restTemplateMock = mock(RestTemplate.class);
        ResponseEntity<Void> responseEntityMock = new ResponseEntity<>(HttpStatus.OK);

        Mockito.when(restTemplateMock.exchange(
                Mockito.eq("http://localhost:8080/password/updateEncoding"),
                Mockito.eq(HttpMethod.PUT),
                Mockito.any(HttpEntity.class),
                Mockito.eq(Void.class)
        )).thenReturn(responseEntityMock);

        UserService userService = new UserService(userRepository, functionRunService, dataChangeService);
        userService.setRestTemplate(restTemplateMock);

        ResponseEntity<?> response = userService.changePassword("newpassword");
        System.out.println(response);
        User responseUser = (User) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotEquals(oldPassword, responseUser.getPasswordHash());
    }

    @Test
    void shouldReturnErrorInsteadOfChangingPassword() {
        String newPassword = "newpassword123";
        User user = new User("testuser", false);
        user.setSalt(RandomStringGenerator.generateRandomString());
        user.setPasswordHash(userService.calculatePasswordHash("oldpassword123", user.getSalt(), user.getKeptAsHash()));
        UserSession.getSession().setUser(user);
        Mockito.doThrow(new RuntimeException("Error")).when(userRepository).save(user);

        ResponseEntity<?> response = userService.changePassword(newPassword);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(user.getPasswordHash(), UserSession.getSession().getUser().getPasswordHash());
    }
}