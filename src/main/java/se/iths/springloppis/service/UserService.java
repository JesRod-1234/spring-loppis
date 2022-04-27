package se.iths.springloppis.service;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserEntity createUser(UserEntity user) {
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