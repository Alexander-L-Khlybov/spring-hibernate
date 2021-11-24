package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        for (int i = 0; i < 5; i++) {
            User user = new User("first_" + i, "last_" + i, "em" + i + "@j.m");
            user.setCar(new Car("audi", i));
            userService.add(user);
        }

        for (User user : userService.listUsers()) {
            System.out.println(user);
        }

        System.out.println(userService.getUserByCar("audi", 2));

        context.close();
    }
}
