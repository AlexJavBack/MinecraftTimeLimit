package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addUserFormButton;

    @FXML
    private Button startSessionsButton;

    @FXML
    private Button EditUserFormButton;


    @FXML
    void initialize() {
        addUserFormButton.setOnAction(actionEvent -> {
            try {
                // open form
                Parent rootAddUserMenu = FXMLLoader.load(getClass().getResource
                        ("/sample/fxml/MincraftTimeLimitAddUsers.fxml"));
                openForm(rootAddUserMenu);
            } catch (IOException e) {
                e.printStackTrace();
            }
             });

        EditUserFormButton.setOnAction(actionEvent -> {
            try {
                // open form
                Parent rootEditUserMenu = FXMLLoader.load(getClass().getResource
                        ("/sample/fxml/MincraftTimeLimitEditUsers.fxml"));
                openForm(rootEditUserMenu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        startSessionsButton.setOnAction(actionEvent -> {
            try {
                // open form
                Parent rootStartSessionMenu = FXMLLoader.load(getClass().getResource
                        ("/sample/fxml/MincraftTimeLimitSession.fxml"));
                openForm(rootStartSessionMenu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void openForm (Parent parent){
        Scene scene = new Scene(parent);
        Stage stage = (Stage) startSessionsButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
