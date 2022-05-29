package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ComboBox<String> cmbxChooseOriginDepartment;

    @FXML
    private ComboBox<String> cmbxChooseOrigin;

    @FXML
    private ComboBox<String> cmbxChooseDestinationDepartment;

    @FXML
    private ComboBox<String> cmbxChooseDestination;
    
    List<String> citiesOfAntioquia;
    List<String> citiesOfCauca;
    List<String> citiesOfHuila;
    List<String> citiesOfNarinio;
    List<String> citiesOfPutumayo;
    List<String> citiesOfQuindio;
    List<String> citiesOfRisaralda;
    List<String> citiesOfValle;
 

    
    public TravelBusGUI() {
    	citiesOfAntioquia = new ArrayList<String>( Arrays.asList("Chigorodo","Ituango","Medellin","Toledo","Turbo","Zaragoza") );
    	citiesOfCauca = new ArrayList<String>( Arrays.asList("Cajibio","El Tambo","La Vega","Morales","Piamonte","Popayan","Timbiqui") );
    	citiesOfHuila = new ArrayList<String>( Arrays.asList("La Plata","Nataga","Neiva","Palermo","Pitalito","Tarqui","Teruel") );
    	citiesOfNarinio = new ArrayList<String>( Arrays.asList("Chachagui","Ipiales","Pasto","Ricaurte","Sandona","Tumaco") );
    	citiesOfPutumayo = new ArrayList<String>( Arrays.asList("La Hormiga","Mocoa","Puerto Asis","Puerto Guzman","Sibundoy","Villa Garzon") );
    	citiesOfQuindio = new ArrayList<String>( Arrays.asList("Armenia","Calarca","Circasia","Filandia","Salento") );
    	citiesOfRisaralda = new ArrayList<String>( Arrays.asList("Apia","Mistrato","Pereira","Pueblo Rico","Santa Rosa de Cabal") );
    	citiesOfValle = new ArrayList<String>( Arrays.asList("Cali","Cartago","Buga","Florida","Palmira","Trujillo","Tulua","Yumbo") );
    	
    }
    
	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	@FXML
	public void toRoutesWindow(ActionEvent event) throws IOException {
		AudioClip sound = new AudioClip("file:resources/sounds/click.mp3");
		sound.play();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MapRoutes.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
		initializeComboBoxOfDepartments();
	
	}
	

	@FXML
	public void calculateCheapestRoute(ActionEvent event) {
		
	}

	
	
	@FXML
	public void calculateFastestRoute(ActionEvent event) {
		
	}
	
	
	public void initializeComboBoxOfDepartments() {
		List<String> departments = new ArrayList<String>();

		String department1 = "Antioquia";
		String department2 = "Cauca";
		String department3 = "Huila";
		String department4 = "Nariño";
		String department5 = "Putumayo";
		String department6 = "Quindío";
		String department7 = "Risaralda";
		String department8 = "Valle del Cauca";

		departments.add(department1);
		departments.add(department2);
		departments.add(department3);
		departments.add(department4);
		departments.add(department5);
		departments.add(department6);
		departments.add(department7);
		departments.add(department8);

		ObservableList<String> observableList = FXCollections.observableArrayList(departments);
		cmbxChooseOriginDepartment.setItems(observableList);
		cmbxChooseDestinationDepartment.setItems(observableList);
		eventOrigin();
		eventDestination();
	}
	
	
	
	public void eventOrigin() {
		cmbxChooseOriginDepartment.setOnAction(e -> cmbxChooseOrigin.setItems(observableList(cmbxChooseOriginDepartment)) );

		cmbxChooseOriginDepartment.valueProperty().addListener((ov, p1, p2) -> {
		    //System.out.println("Nueva Selección: " + p2);
		   // System.out.println("Vieja Selección: " + p1);
		});
	}
	
	public void eventDestination() {
		cmbxChooseDestinationDepartment.setOnAction(e -> cmbxChooseDestination.setItems(observableList(cmbxChooseDestinationDepartment)) );

		cmbxChooseDestinationDepartment.valueProperty().addListener((ov, p1, p2) -> {
		    //System.out.println("Nueva Selección: " + p2);
		   // System.out.println("Vieja Selección: " + p1);
		});
	}
	
	
	public ObservableList<String> observableList(ComboBox<String> comboBox){
		ObservableList<String> observableList;
		if(comboBox.getValue() != null) {
			if( comboBox.getSelectionModel().getSelectedItem().equals("Antioquia") ) {
				observableList = FXCollections.observableArrayList(citiesOfAntioquia);
				return observableList;
			}
			else if(comboBox.getSelectionModel().getSelectedItem().equals("Cauca")) {
				observableList = FXCollections.observableArrayList(citiesOfCauca);
				return observableList;
			}else if(comboBox.getSelectionModel().getSelectedItem().equals("Huila")) {
				observableList = FXCollections.observableArrayList(citiesOfHuila);
				return observableList;
			}
			else if(comboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Nariño")) {
				observableList = FXCollections.observableArrayList(citiesOfNarinio);
				return observableList;
			}
			else if(comboBox.getSelectionModel().getSelectedItem().equals("Putumayo")) {
				observableList = FXCollections.observableArrayList(citiesOfPutumayo);
				return observableList;
			}
			else if(comboBox.getSelectionModel().getSelectedItem().equals("Quindío")) {
				observableList = FXCollections.observableArrayList(citiesOfQuindio);
				return observableList;
			}
			else if(comboBox.getSelectionModel().getSelectedItem().equals("Risaralda")) {
				observableList = FXCollections.observableArrayList(citiesOfRisaralda);
				return observableList;
			}
			else if(comboBox.getSelectionModel().getSelectedItem().equals("Valle del Cauca")) {
				observableList = FXCollections.observableArrayList(citiesOfValle);
				return observableList;
			}
			else {
				return null;
			}
		}else {
			return null;
		}
	}
	
}
