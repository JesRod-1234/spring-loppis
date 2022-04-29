package se.iths.springloppis.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.springloppis.entity.UserEntity;
import se.iths.springloppis.repository.UserRepository;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

//    @Autowired
//    BeanScopesDemo beanScopesDemo;

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UserEntity createUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Vi skickar in en användare och vi ska skicka med ett lösenord.
        // Och innan de sparas in i databasen, kommer de lösenordet att åka igenom den BCryptPasswordEncoded. Och sedan genom mekanismen hashing sedan sparas i databasen.
        return userRepository.save(user);
    }
    public Optional<UserEntity> findUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
    public void deleteUser(Long id) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.deleteById(foundUser.getId());
    }
    public Iterable<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

}