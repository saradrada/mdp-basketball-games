package ui;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    private ChoiceBox<String> cbDays;

    @FXML
    private GridPane matrix;
    
    @FXML
    public void initialize() {
    	cbDays.getItems().add("1");
    	cbDays.getItems().add("2");
    }
}
