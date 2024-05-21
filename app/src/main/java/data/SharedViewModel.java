package data;
import androidx.lifecycle.ViewModel;


public class SharedViewModel extends ViewModel {
    private String userRol;
    private int userId;
    private int postJob;

    // Getters and setters
    public String getUserRol() {
        return userRol;
    }

    public void setUserRol(String userRol) {
        this.userRol = userRol;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getPostJob() {
        return postJob;
    }

    public void setPostJob(int userId) {
        this.userId = postJob;
    }
}
