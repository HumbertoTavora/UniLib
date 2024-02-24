package com.example.userService.model.subsistemagoogle;

import com.example.userService.model.entity.Usuario;
import com.example.userService.model.repository.UsuarioRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class GoogleSignInController {

    private static final String CLIENT_ID = "291288604194-g7fphlakarfaj9t0qjroli77kb2uvr06.apps.googleusercontent.com";
    private final UsuarioRepository usuarioRepository;

    public GoogleSignInController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/googleSignIn")
    public Map<String, Boolean> doGoogleSignIn(@RequestBody Map<String, String> payload) {
        List<Usuario> users = usuarioRepository.listarUsuarios();
        String idTokenString = payload.get("idToken");
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                Payload googleUser = idToken.getPayload();

                String email = googleUser.getEmail();
                boolean isUserRegistered = users.stream().anyMatch(user -> user.getEmail().equals(email));

                return Collections.singletonMap("isUserRegistered", isUserRegistered);
            } else {
                System.out.println("Invalid ID token.");
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        return Collections.singletonMap("isUserRegistered", false);
    }
}
