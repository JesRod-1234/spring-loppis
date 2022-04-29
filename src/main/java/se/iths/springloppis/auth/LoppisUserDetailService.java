package se.iths.springloppis.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.iths.springloppis.entity.UserEntity;
import se.iths.springloppis.repository.UserRepository;

// Vi behöver länka våran egen UserEntity till någonting som Spring Security förstår. Och det kallas för UserDetails
@Service // Det är är service för Principal objektet
public class LoppisUserDetailService implements UserDetailsService {

    private final UserRepository userRepository; // Nedan kommer att hämta ut användaren som försöker att identifiera sig.

    public LoppisUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null){
            throw new UsernameNotFoundException("Can't find user with username:" + username);
        }
        return new LoppisPrincipal(userEntity);
    }

    // Det som servicen gör: Är att den har en metod som försöker hämta en användare baserat på
    // användarnamnet som vi skickar in. Och hittar vi då en användare vid de nament, får vi ut
    // en LoppisPrincipal där vi skickar in username som ett argument.
}
