package pl.bsiwallet.bsitest.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.bsiwallet.bsitest.interfaces.UserRepository;
import pl.bsiwallet.bsitest.entities.User;
import pl.bsiwallet.bsitest.utils.Access;
import pl.bsiwallet.bsitest.utils.RandomStringGenerator;
import pl.bsiwallet.bsitest.utils.SecurityUtils;
import pl.bsiwallet.bsitest.utils.UserSession;
import pl.bsiwallet.bsitest.wrappers.PasswordEncryptionUpdateWrapper;
import pl.bsiwallet.bsitest.wrappers.UserRequestWrapper;

import java.util.Optional;

@Service
public class UserService {
    private RestTemplate restTemplate = new RestTemplate();

    private final UserRepository userRepository;
    private UserSession userSession = UserSession.getSession();

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public ResponseEntity<?> createUser(UserRequestWrapper userWrapper) {
        try {
            User user = new User(userWrapper.getLogin(), userWrapper.getIsHmac());
            user.setSalt(RandomStringGenerator.generateRandomString());
            user.setPasswordHash(calculatePasswordHash(
                    userWrapper.getPassword(), user.getSalt(), user.getKeptAsHash())
            );
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllUsers() {
        try {
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public Boolean login(String login, String password) {
        userSession.refreshSession();
        Optional<User> optUser = userRepository.findByLogin(login);
        if(optUser.isPresent()) {
            Boolean isCorrect = isPasswordCorrect(optUser.get(), password);
            userSession.setIsValid(isCorrect);
            if (isCorrect) {
                userSession.setUser(optUser.get());
            }
            return isCorrect;
        } else {
            userSession.setIsValid(false);
            return false;
        }
    }

    public ResponseEntity<?> changePassword(String password) {
        try {
            User user = userSession.getUser();
            PasswordEncryptionUpdateWrapper wrapper = new PasswordEncryptionUpdateWrapper();

            user.setSalt(RandomStringGenerator.generateRandomString());
            wrapper.setOldPassword(user.getPasswordHash());

            user.setPasswordHash(calculatePasswordHash(password, user.getSalt(), user.getKeptAsHash()));
            wrapper.setNewPassword(user.getPasswordHash());

            String url = "http://localhost:8080/password/updateEncoding";
            restTemplate.put(url, wrapper);
            userRepository.save(user);
            userSession.setUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String changeAccessLevel() {
        if (userSession.getAccess() == Access.MODIFY) {
            userSession.setAccess(Access.READONLY);
        } else if (userSession.getAccess() == Access.READONLY) {
            userSession.setAccess(Access.MODIFY);
        }

        return "Access level changed to " + userSession.getAccess();
    }

    public String calculatePasswordHash(String password, String salt, Boolean isHmac) {
        if(!isHmac) {
            return SecurityUtils.calculateSha512(password + salt);
        } else {
            return SecurityUtils.calculateHmac(password, SecurityUtils.HMAC_KEY);
        }
    }

    private Boolean isPasswordCorrect(User user, String password) {
        return user.getPasswordHash().equals(calculatePasswordHash(password, user.getSalt(), user.getKeptAsHash()));
    }

    public void setRestTemplate(RestTemplate restTemplateMock) {
        restTemplate = restTemplateMock;
    }
}
