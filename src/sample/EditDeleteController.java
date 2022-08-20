package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EditDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button editButton;

    @FXML
    private TableColumn<Table, String> Name;

    @FXML
    private TextField NewUserNick;

    @FXML
    private TableColumn<Table, String> Nick;

    @FXML
    private TextField OldUserNick;

    @FXML
    private Button removeButton;

    @FXML
    private TableView<Table> TableNameNick;

    @FXML
    void initialize() {
        singUpNTables();
        editButton.setOnAction(actionEvent -> {
            singUpNewUser();
            singUpNTables();
        });

        removeButton.setOnAction(actionEvent -> {
            deletUser();
            singUpNTables();
        });

        backButton.setOnAction(actionEvent -> {
            try {
                // open form
                Parent rootMainMenuForm = FXMLLoader.load(getClass().getResource
                        ("/sample/fxml/MincraftTimeLimitMainField.fxml"));
                Scene scene = new Scene(rootMainMenuForm);
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void singUpNTables() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ObservableList<Table> table = FXCollections.observableArrayList();
        table = dbHandler.showUsersTables();

        Name.setCellValueFactory(new PropertyValueFactory<Table, String>("UserName"));
        Nick.setCellValueFactory(new PropertyValueFactory<Table, String>("UserNick"));
        TableNameNick.setItems(table);
    }

    private void singUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String OldNick = OldUserNick.getText();
        String Newick = NewUserNick.getText();

        dbHandler.EditUser(OldNick, Newick);
    }

    private void deletUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String OldNick = OldUserNick.getText();
        dbHandler.DeleteUser(OldNick);
    }

}
