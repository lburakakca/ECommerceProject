import business.abstracts.UserService;
import business.concretes.UserCheckManager;
import business.concretes.UserManager;
import core.concretes.GoogleAuthenticationServiceAdapter;
import dataAccess.concretes.InMemoryUserRepository;
import entities.concretes.User;

public class Main {

    public static void main(String[] args) {

        UserService userManager = new UserManager(
                new InMemoryUserRepository(),
                new UserCheckManager(new InMemoryUserRepository()),
                new GoogleAuthenticationServiceAdapter()
        );

        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
        User user1 = inMemoryUserRepository.getByEmailAddress("burak@gmail.com");

        userManager.login("burak@gmail.com", "1234567890");
        userManager.register( "Ali ", "Mehmet", "anonim@gmail.com", "1234567890");
        userManager.loginWithGoogleService(user1);

    }

}
