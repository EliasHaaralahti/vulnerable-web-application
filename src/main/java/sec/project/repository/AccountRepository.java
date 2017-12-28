package sec.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByName(String name);
}
