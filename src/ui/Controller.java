package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import model.BasketballGame;

public class Controller {

	private BasketballGame bg;
	@FXML
	private ChoiceBox<String> cbDays;

	@FXML
	private GridPane matrix;

	@FXML
	private Button btnGenerate;

	@FXML
	public void initialize() {
		cbDays.getItems().add("2");
		cbDays.getItems().add("3");

	}

	private int getSelectedValue() {
		return cbDays.getSelectionModel().getSelectedItem() == null ? 0
				: Integer.parseInt(cbDays.getSelectionModel().getSelectedItem());
	}

	@FXML
	void generateMatrix(ActionEvent event) {

		matrix.getChildren().clear();
		if (cbDays.getSelectionModel().getSelectedItem() == null) {

			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Cantidad de días");
			alert.setContentText("Se debe escoger la cantidad de días antes de generar la matriz.");
			alert.showAndWait();
		} else {

			bg = new BasketballGame(getSelectedValue());
			String[][] modelMatrix = bg.generateMatrix();

			Label actual;
			for (int i = 0; i < modelMatrix.length; i++) {
				for (int j = 0; j < modelMatrix[1].length; j++) {

					actual = new Label(modelMatrix[i][j]);
					matrix.add(actual, j, i);
				}
			}
		}
	}

}
