package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TravelBusGUI {
	private Stage mainStage;

	@FXML
	Pane pane;
	
	@FXML
	Circle origin;
	
	@FXML
	Circle destiny;
	
	@FXML
    private ComboBox<?> cmbxChooseOriginDepartment;

    @FXML
    private ComboBox<?> cmbxChooseOrigin;

    @FXML
    private ComboBox<?> cmbxChooseDestinationDepartment;

    @FXML
    private ComboBox<?> cmbxChooseDestination;
    
	
	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	@FXML
	public void toRoutesWindow(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Routes.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
	}
	

	@FXML
	public void calculateCheapestRoute(ActionEvent event) {
		
	}

	
	
	@FXML
	public void calculateFastestRoute(ActionEvent event) {
		
	}
	
}
