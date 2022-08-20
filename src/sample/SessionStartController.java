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

public class SessionStartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TextField userNickTextField;

    @FXML
    private TextField userTimeTextField;

    @FXML
    private Button calculationButton;

    @FXML
    private TableView<Table> tableUsers;

    @FXML
    private TableColumn<Table, String> TavleColumnName;

    @FXML
    private TableColumn<Table, String> TavleColumnNick;

    @FXML
    private TableColumn<Table, Integer> TavleColumnTime;

    @FXML
    void initialize() {
        viewTable();
        calculationButton.setOnAction(actionEvent -> {
            setAttributesUser();
            viewTable();
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
    private void viewTable() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ObservableList<Table> table = FXCollections.observableArrayList();
        table = dbHandler.showUsersTables();

        TavleColumnName.setCellValueFactory(new PropertyValueFactory<Table, String>("UserName"));
        TavleColumnNick.setCellValueFactory(new PropertyValueFactory<Table, String>("UserNick"));
        TavleColumnTime.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Time"));
        tableUsers.setItems(table);
    }

    private void setAttributesUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String userName = userNickTextField.getText();
        if(!userTimeTextField.getText().matches("\\d*")){
            userTimeTextField.setText(userTimeTextField.getText().replaceAll("[^\\d]", ""));
        }
        else {
            int time = Integer.parseInt(userTimeTextField.getText());
            dbHandler.UpdateSessionTime(userName, time);
        }
    }


}
