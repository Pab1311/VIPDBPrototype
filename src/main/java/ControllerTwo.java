import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerTwo {

  @FXML
  public TextField deleteUIN;

  public Member testOne;
  public Member testTwo;
  public Member currentMember;
  public String test;

  public void getMemberInfos(Member one, Member two, Member current) {
    testOne = one;
    testTwo = two;
    currentMember = current;
  }

  @FXML
  void confirmDeletion(ActionEvent event){
    Stage homeStage = (Stage)((Node) event.getSource()).getScene().getWindow();
    test = deleteUIN.getText();
    if (test.equals(currentMember.getUIN())){
      homeStage.close();
    }
  }

}
