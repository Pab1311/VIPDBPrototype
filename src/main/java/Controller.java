import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Controller {

  @FXML
  private Label lblOutput;

  @FXML
  private TextField userField;

  @FXML
  private TextField passField;

  @FXML
  private Label lblOutput1;

  @FXML
  private Label loginError;

  @FXML
  private TextArea interactions;

  @FXML
  private TextField searchField;

  @FXML
  private Label searchError;

  @FXML
  private TextField updateField;

  @FXML
  private Label updateError;

  @FXML
  private Button deleteButton;

  @FXML
  private TextField deleteUIN;

  private ObservableList<String> info = FXCollections.observableArrayList();

  @FXML
  private ListView <String> memberInfo;

  private final String username = "jrose";
  private String password = "test123";
  public Member testOne = new Member("123456789", "Paul Basso", 5);
  public Member testTwo = new Member("987654321", "Abraham Lincoln", 10);
  public Member currentMember;

  @FXML
  void login(ActionEvent event) throws Exception {
    if (userField.getText().isBlank() || passField.getText().isBlank()){
      loginError.setText("Please enter all fields before attempting to login.");
    } else{
      String name = userField.getText();
      String pass = passField.getText();
      if (name.equals(username) && pass.equals(password)){
        Parent home_page = FXMLLoader.load(getClass().getResource("database.fxml"));
        Stage homeStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene home = new Scene(home_page, 730, 554);
        homeStage.setTitle("Database System");
        homeStage.setScene(home);
        homeStage.show();
      } else{
        loginError.setText("Username or Password is incorrect. Please try again.");
      }
    }
  }

  @FXML
  void searchMember(ActionEvent event) {
    info.clear();
    memberInfo.getItems().clear();
    deleteButton.setDisable(true);
    if (searchField.getText().isBlank()){
      searchError.setText("Please enter a UIN before\nattempting to search.");
    } else{
      // in final release, we must check and see if string contains only numbers
      if (searchField.getText().length() != 9){
        searchError.setText("UIN must be 9 numbers long.\nPlease try again.");
      }
      if (!(searchField.getText().equals(testOne.getUIN())) && !(searchField.getText().equals(testTwo.getUIN()))){
        searchError.setText("No member with that UIN exists.");
      }
      else{
        if (searchField.getText().equals(testOne.getUIN())) {
          interactions.appendText(username + " searched for " + testOne.getName() + "\n");
          info.add("Name: " + testOne.getName());
          info.add("Volunteer Hours: " + testOne.getVolunteerHours());
          memberInfo.setItems(info);
          currentMember = new Member(searchField.getText());
          searchError.setText("");
          searchField.clear();
          deleteButton.setDisable(false);
        } else if (searchField.getText().equals(testTwo.getUIN())){
          interactions.appendText(username + " searched for " + testTwo.getName() + "\n");
          info.add("Name: " + testTwo.getName());
          info.add("Volunteer Hours: " + testTwo.getVolunteerHours());
          memberInfo.setItems(info);
          currentMember = new Member(searchField.getText());
          searchError.setText("");
          searchField.clear();
          deleteButton.setDisable(false);
        }
        System.out.println(currentMember.getUIN());
      }
    }
  }

  @FXML
  void updateField(ActionEvent event) {
    if (memberInfo.getSelectionModel().getSelectedItem() == null) {
      updateError.setText("You must select the field that will be updated!");
    } else {
      if (memberInfo.getSelectionModel().getSelectedItem().contains("Name")) {
        if (info.get(0).contains(testOne.getName())) {
          testOne.setName(updateField.getText());
          updateField.clear();
          info.set(0, "Name: " + testOne.getName());
        } else if (info.get(0).contains(testTwo.getName())) {
          testTwo.setName(updateField.getText());
          updateField.clear();
          info.set(0, "Name: " + testTwo.getName());
        }
      }
      if (memberInfo.getSelectionModel().getSelectedItem().contains("Volunteer")) {
        if (info.get(1).contains(String.valueOf(testOne.getVolunteerHours()))) {
          try {
            testOne.setVolunteerHours(Integer.parseInt(updateField.getText()));
            updateField.clear();
            info.set(1, "Volunteer Hours: " + testOne.getVolunteerHours());
          } catch (NumberFormatException e) {
            updateError.setText("Please enter an integer value.");
          }
        } else if (info.get(1).contains(String.valueOf(testTwo.getVolunteerHours()))) {
          try {
            testTwo.setVolunteerHours(Integer.parseInt(updateField.getText()));
            updateField.clear();
            info.set(1, "Volunteer Hours: " + testTwo.getVolunteerHours());
          } catch (NumberFormatException e) {
            updateError.setText("Please enter an integer value.");
          }
        }
      }
    }
  }

  @FXML
  void deleteMember(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteMember.fxml"));
    Parent delete_page = loader.load();

    ControllerTwo controllerTwo = loader.getController();
    controllerTwo.getMemberInfos(testOne, testTwo, currentMember);

    Stage homeStage = (Stage)((Node) event.getSource()).getScene().getWindow();
    Stage deleteStage = new Stage();
    deleteStage.initModality(Modality.WINDOW_MODAL);
    deleteStage.initOwner(homeStage);
    Scene delete = new Scene(delete_page, 730, 303);
    deleteStage.setTitle("Delete Member");
    deleteStage.setScene(delete);
    deleteStage.showAndWait();

    String UIN = controllerTwo.test;
    if (UIN.equals(testOne.getUIN())){
      testOne.setUIN(null);
      memberInfo.getItems().clear();
    } else if (UIN.equals(testTwo.getUIN())){
      testTwo.setUIN(null);
      memberInfo.getItems().clear();
    }
  }


}

