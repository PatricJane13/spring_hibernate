package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User user1 = new User("Magomedov", "Magomed", "maga@mail.ru");
      User user2 = new User("User6", "Lastname6", "user6@mail.ru");

      Car car = new Car("BMW M5 F90", 13);
      Car car2 = new Car("AUDI R8", 12);

      user1.setCar(car);
      user2.setCar(car2);

      userService.add(user1);
      userService.add(user2);

      List<User> users = userService.listUsers();
      for (User user : users) {
         user.setCar(car);
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar().getName());
         System.out.println();
      }

      User magomedBmw = userService.getUserByNameAndSeries("BMW M5 F90", 13);
      User audi = userService.getUserByNameAndSeries("AUDI R8", 12);
      System.out.println("Магомед ----  " + magomedBmw.getFirstName());
      System.out.println("Кто то  ------  " + audi.getFirstName());

      context.close();
   }
}
