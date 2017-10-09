package by.mchs.reposotory;

import by.mchs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.firstName = ?1")
    List<User> getUserByFirstName(String first);

    @Query("SELECT u FROM User u WHERE u.lastName = ?1")
    List<User> getUserByLastName(String last);

    @Query("SELECT u FROM User u WHERE u.number = ?1")
    List<User> getUserByNumber(String number);

    @Query("SELECT u FROM User u WHERE u.coordinateX < ?2 and u.coordinateX > ?1 and u.coordinateY > ?3 and u.coordinateY < ?4")
    List<User> getUserByRangeCoordinate(double x1, double x2, double y1, double y2);
}
