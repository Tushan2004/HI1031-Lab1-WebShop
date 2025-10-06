package bo;

/**
 * Represents a user of the webshop.
 * Stores the username and password.
 */
public class User {

    /** The username of the user. */
    private String username;

    /** The password of the user. */
    private String password;

    /**
     * Constructs a new User with the specified username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets a new password for the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets a new username for the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
