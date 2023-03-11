package pl.bsiwallet.bsitest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bsiwallet.bsitest.entities.User;
import pl.bsiwallet.bsitest.service.UserService;
import pl.bsiwallet.bsitest.wrappers.UserRequestWrapper;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam("login") String login) {
        Optional<User> optUser = userService.getUserByLogin(login);
        if (optUser.isPresent()) {
            return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("We couldn't find user with login: " + login, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/login")
    public Boolean login(@RequestParam("login") String login,
                             @RequestParam("password") String password){
        return userService.login(login, password);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequestWrapper userWrapper) {
        return userService.createUser(userWrapper);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody UserRequestWrapper userWrapper) {
        return userService.changePassword(userWrapper.getPassword());
    }
}
