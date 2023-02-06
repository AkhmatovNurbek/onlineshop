package uz.soft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import uz.soft.entity.AuthUser;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByPassport(String passport);
    boolean existsByPassport(String pass);

}
