package infrastructure.database.repository;


import infrastructure.database.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Optional<User> findBy(String email) {
        return find("email = ?1", email).firstResultOptional();
    }
}
