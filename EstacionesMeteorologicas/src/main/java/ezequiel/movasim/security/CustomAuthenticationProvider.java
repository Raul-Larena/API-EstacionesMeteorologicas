package ezequiel.movasim.security;

import ezequiel.movasim.model.Rol;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import ezequiel.movasim.repository.UserRepository;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(userRepository.findByUsername(username).get().getRol().toString()));

        return userRepository.findByUsername(username)
                .filter(user -> BCrypt.checkpw(password, user.getPassword()))
                .map(user -> new UsernamePasswordAuthenticationToken(username, password, roles))
                .orElse(null);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
