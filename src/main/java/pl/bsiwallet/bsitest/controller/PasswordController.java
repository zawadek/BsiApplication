package pl.bsiwallet.bsitest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bsiwallet.bsitest.service.PasswordService;
import pl.bsiwallet.bsitest.wrappers.PasswordEncryptionUpdateWrapper;
import pl.bsiwallet.bsitest.wrappers.PasswordRequestWrapper;

@RestController
@RequestMapping(path = "password")
@AllArgsConstructor
public class PasswordController {
    private final PasswordService passwordService;

    @PostMapping("/create")
    public ResponseEntity<?> createPassword(@RequestBody PasswordRequestWrapper passwordWrapper) {
        return passwordService.createPassword(passwordWrapper);
    }

    @GetMapping("/sessionUserPasswords")
    public ResponseEntity<?> getSessionUserPasswords() {
        return passwordService.getSessionUserPasswords();
    }

    @GetMapping("/decrypt")
    public ResponseEntity<?> getDecryptedPassword(@RequestParam("id") Integer id) {
        return passwordService.getDecryptedPassword(id);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editPassword(@RequestBody PasswordRequestWrapper passwordWrapper) {
        return passwordService.editPassword(passwordWrapper);
    }

    @PutMapping("/updateEncoding")
    public void updatePasswordEncoding(@RequestBody PasswordEncryptionUpdateWrapper wrapper) throws Exception {
        passwordService.updatePasswordEncoding(wrapper.getOldPassword(), wrapper.getNewPassword());
    }
}
