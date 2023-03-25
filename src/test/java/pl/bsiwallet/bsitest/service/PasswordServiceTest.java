package pl.bsiwallet.bsitest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.bsiwallet.bsitest.entities.Password;
import pl.bsiwallet.bsitest.entities.User;
import pl.bsiwallet.bsitest.interfaces.PasswordRepository;
import pl.bsiwallet.bsitest.utils.Access;
import pl.bsiwallet.bsitest.utils.SecurityUtils;
import pl.bsiwallet.bsitest.utils.UserSession;
import pl.bsiwallet.bsitest.wrappers.PasswordRequestWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class PasswordServiceTest {

    @Autowired
    private PasswordService passwordService;

    @MockBean
    private PasswordRepository passwordRepository;

    private User user;
    private PasswordRequestWrapper passwordWrapper;
    private Password password;

    @BeforeEach
    public void setUp() throws Exception {
        user = new User();
        user.setId(1);
        user.setPasswordHash("passwordhash");
        user.setLogin("testuser");

        UserSession userSession = UserSession.getSession();
        userSession.setUser(user);
        userSession.setIsValid(true);
        userSession.setAccess(Access.MODIFY);

        passwordWrapper = new PasswordRequestWrapper();
        passwordWrapper.setDescription("testdescription");
        passwordWrapper.setLogin("testlogin");
        passwordWrapper.setWebAddress("testwebaddress");
        passwordWrapper.setPassword("testpassword");

        password = new Password();
        password.setId(1);
        password.setDescription("testdescription");
        password.setLogin("testlogin");
        password.setWebAddress("testwebaddress");
        password.setPassword(SecurityUtils.encrypt(
                "testpassword",
                SecurityUtils.generateKey(SecurityUtils.calculateMD5(user.getPasswordHash()))
        ));
        password.setUser(user);
    }

    @Test
    void shouldCreateValidPassword() {
        Mockito.when(passwordRepository.save(Mockito.any(Password.class))).thenReturn(password);
        ResponseEntity<?> response = passwordService.createPassword(passwordWrapper);

        Password responsePassword = (Password)response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(responsePassword);
        assertEquals(passwordWrapper.getLogin(), responsePassword.getLogin());
        assertNotNull(passwordWrapper.getDescription(), responsePassword.getDescription());
        assertNotNull(passwordWrapper.getWebAddress(), responsePassword.getWebAddress());
    }

    @Test
    void shouldThrowErrorInsteadOfCreatingPassword() {
        String errorMessage = "errorMessage";

        Mockito.when(passwordRepository.save(Mockito.any(Password.class))).thenThrow(new IllegalArgumentException(errorMessage));
        ResponseEntity<?> response = passwordService.createPassword(passwordWrapper);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void shouldReturnSessionUserPasswordList() {
        List<Password> passwordList = new ArrayList<>();
        passwordList.add(password);

        Mockito.when(passwordRepository.findByUserId(Mockito.any(Integer.class))).thenReturn(Optional.of(passwordList));
        ResponseEntity<?> response = passwordService.getSessionUserPasswords();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(passwordList, response.getBody());
    }

    @Test
    void shouldReturnEmptyPasswordList() {
        Mockito.when(passwordRepository.findByUserId(Mockito.any(Integer.class))).thenReturn(Optional.empty());
        ResponseEntity<?> response = passwordService.getSessionUserPasswords();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(String.class, response.getBody().getClass());
    }

    @Test
    void shouldReturnDecryptedPassword() {
        Mockito.when(passwordRepository.findById(UserSession.getSession().getUser().getId())).
                thenReturn(Optional.of(password));
        ResponseEntity<?> response = passwordService.getDecryptedPassword(UserSession.getSession().getUser().getId());
        PasswordRequestWrapper responseWrapper = (PasswordRequestWrapper)response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(passwordWrapper.getPassword(), responseWrapper.getPassword());
        assertEquals(passwordWrapper.getLogin(), responseWrapper.getLogin());
        assertEquals(passwordWrapper.getDescription(), responseWrapper.getDescription());
        assertEquals(passwordWrapper.getWebAddress(), responseWrapper.getWebAddress());
    }

    @Test
    void shouldNotFindPasswordForDecryption() {
        Mockito.when(passwordRepository.findById(UserSession.getSession().getUser().getId())).
                thenReturn(Optional.empty());
        ResponseEntity<?> response = passwordService.getDecryptedPassword(UserSession.getSession().getUser().getId());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(String.class, response.getBody().getClass());
    }

    @Test
    void shouldReturnErrorInsteadOfDecryptedPassword() {
        String errorMessage = "message";
        Mockito.when(passwordRepository.findById(UserSession.getSession().getUser().getId())).
                thenThrow(new IllegalArgumentException(errorMessage));
        ResponseEntity<?> response = passwordService.getDecryptedPassword(UserSession.getSession().getUser().getId());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void shouldReturnEditedPassword() {
        passwordWrapper.setLogin("logink2");
        Mockito.when(passwordRepository.findById(passwordWrapper.getId())).thenReturn(Optional.of(password));
        Mockito.when(passwordRepository.save(Mockito.any(Password.class))).thenReturn(password);

        ResponseEntity<?> response = passwordService.editPassword(passwordWrapper);
        Password responsePassword = (Password)response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(passwordWrapper.getLogin(), responsePassword.getLogin());
    }

    @Test
    void shouldReturnErrorInsteadOfEditedPassword() {
        String errorMessage = "message";
        Mockito.when(passwordRepository.findById(passwordWrapper.getId()))
                .thenThrow(new IllegalArgumentException(errorMessage));

        ResponseEntity<?> response = passwordService.editPassword(passwordWrapper);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }
}