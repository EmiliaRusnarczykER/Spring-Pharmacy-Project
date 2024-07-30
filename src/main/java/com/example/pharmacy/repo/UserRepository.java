package com.example.pharmacy.repo;
import com.example.pharmacy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.management.relation.Role;
import java.util.List;
public interface UserRepository extends JpaRepository<User, Long>  {
    User findByUsername(String username);
}
