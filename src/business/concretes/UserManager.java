package business.concretes;

import business.abstracts.UserCheckService;
import business.abstracts.UserService;
import core.abstracts.ExternalAuthenticationService;
import dataAccess.abstracts.UserDao;
import entities.concretes.User;

public class UserManager implements UserService {

    private final ExternalAuthenticationService googleService;
    private UserDao userDao;
    private UserCheckService userCheckService;

    public UserManager(UserDao userDao,
                       UserCheckService userCheckService,
                       ExternalAuthenticationService googleService) {
        this.userDao = userDao;
        this.googleService = googleService;
        this.userCheckService = userCheckService;
    }


    @Override
    public void login(String emailAddress, String password) {
        User user = userDao.getByEmailAddress(emailAddress);
        if (user.getEmailAddress().equals(emailAddress)) {
            if (user.getPassword().equals(password)) {
                System.out.println("Login Successfuly");
            } else {
                System.out.println("Password or username is incorrect");
            }
        } else {
            System.out.println("Password or username is incorrect");
        }
    }

    @Override
    public void register(String firstName, String lastName, String emailAddress, String password) {

        if (userCheckService.checkFirstName(firstName) &&
                userCheckService.checkLastName(lastName) &&
                userCheckService.isValidMail(emailAddress) &&
                userCheckService.isUniqueMail(emailAddress) &&
                userCheckService.isValidPassword(password)
        ) {
            System.out.println(firstName + lastName + " You have registered to the system with your user name... A verification link has been sent to your e-mail address.");
        } else {
            System.out.println(" Login failed e-mail or Password is incorrect, check your e-mail and password and try again.");
        }


    }

    @Override
    public boolean loginWithGoogleService(User user) {
        return googleService.authenticate(user);
    }
}
