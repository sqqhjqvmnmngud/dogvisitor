package pl.com.rest.database;

/**
 * Created by wewe on 28.05.16.
 */
public class DatabaseConstant {

    // public static final String HOST = "172.17.0.1";
    // public static final int PORT = 27017;
    // public static final String DATABASE = "dogvisitor";
    // public static final String USER_NAME = "dog";
    // public static final String PASSWORD = "cat";

    public static final String HOST = System.getenv("DB_HOST");
    public static final int PORT = Integer.parseInt(System.getenv("DB_PORT"));
    public static final String DATABASE = System.getenv("DB_NAME");
    public static final String USER_NAME = System.getenv("DB_USER");
    public static final String PASSWORD = System.getenv("DB_PASSWORD");
}
