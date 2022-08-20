package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBackButton;

    @FXML
    private Button AddButton;

    @FXML
    private TextField AddUserNameF;

    @FXML
    private TextField AddUserNickF;

    @FXML
    void initialize() {
        // add new User Button
        AddButton.setOnAction(actionEvent -> {
            addNewUser();
        });
        //Button Back in main menu
        addBackButton.setOnAction(actionEvent -> {
            try {
                // open form
                Parent rootMainMenuForm = FXMLLoader.load(getClass().getResource
                        ("/sample/fxml/MincraftTimeLimitMainField.fxml"));
                Scene scene = new Scene(rootMainMenuForm);
                Stage stage = (Stage) addBackButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void addNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        int time = dbHandler.SelectTimeNewUser();
        String UserName = AddUserNameF.getText();
        String UserNick = AddUserNickF.getText();
        User user = new User(UserName, UserNick, time);
        dbHandler.addNewUser(user);
    }

}
