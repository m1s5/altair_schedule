package io.kissmara.altair_schedule.model.user;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);
}
