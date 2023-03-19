package googleService;

import entities.concretes.User;

public class GoogleAuthentication {

    public boolean authenticateWithGoogle(User user) {
        System.out.println(user.getEmailAddress() + " User authorized by google");
        return true;
    }

}
