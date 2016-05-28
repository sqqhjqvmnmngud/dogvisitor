//package pl.com.rest.repository;
//
//import org.springframework.stereotype.Component;
//import pl.com.rest.model.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class UserRepository {
//
//    private final List<User> users = new ArrayList<>();
//
//    public UserRepository() {
////        save("mieszko", new User("Mieszko Pierwszy"));
////        save("bolek", new User("Bolesław Chrobry" ));
////        save("janek", new User("Jancio Wodnik" ));
//    }
//
//    public List<User> getUsers(){
//        return users;
//    }
//
//    public User save(String login, User user){
//        User dbUser = findByLogin(login);
//
//        if(dbUser == null) {
//            dbUser = user;
//
//            users.add(user);
//        }
//
//
//        dbUser.setName(user.getName());
//
//
//        return dbUser;
//    }
//
//    public User findByLogin(String login) {
//        for(User user : users){
//            if (login.equalsIgnoreCase(user.getName())){
//                return user;
//            }
//        }
//        return null;
//    }
//
//    public User remove(String login){
//        User user = findByLogin(login);
//        if (user != null){
//            users.remove(user);
//        }
//
//        return user;
//    }
//
//}
