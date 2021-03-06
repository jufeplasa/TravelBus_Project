package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Ciudad;
import model.TravelBus;

public class TravelBusGUI {
	private Stage mainStage;
	private TravelBus travel;

	@FXML
	private AnchorPane pane;

	@FXML
	Circle origin;

	@FXML
	Circle destiny;

	@FXML
	private Label labelOrigin;

	@FXML
	private Label labelDestination;

	@FXML
	private ComboBox<String> cmbxChooseOriginDepartment;

	@FXML
	private ComboBox<String> cmbxChooseOrigin;

	@FXML
	private ComboBox<String> cmbxChooseDestinationDepartment;

	@FXML
	private ComboBox<String> cmbxChooseDestination;

	@FXML
	private TextField txtUsername;

	@FXML
	private DatePicker dpTravelDate;

	@FXML
	private Label ticketOrigin;

	@FXML
	private Label tichetDestiny;


	List<String> citiesOfAntioquia;
	List<String> citiesOfCauca;
	List<String> citiesOfHuila;
	List<String> citiesOfNarinio;
	List<String> citiesOfPutumayo;
	List<String> citiesOfQuindio;
	List<String> citiesOfRisaralda;
	List<String> citiesOfValle;
	List<String> totalCities;


	public TravelBusGUI() {
		travel=new TravelBus();
		citiesOfAntioquia = travel.getCityByDep("Antioquia");
		citiesOfCauca =travel.getCityByDep("Cauca");
		citiesOfHuila = travel.getCityByDep("Huila");
		citiesOfNarinio =travel.getCityByDep("Nari?o");
		citiesOfPutumayo = travel.getCityByDep("Putumayo");
		citiesOfQuindio = travel.getCityByDep("Quindio");
		citiesOfRisaralda =travel.getCityByDep("Risaralda");
		citiesOfValle =travel.getCityByDep("Valle del Cauca");
		totalCities = new ArrayList<String>();


		totalCities.addAll(citiesOfAntioquia);
		totalCities.addAll(citiesOfCauca);
		totalCities.addAll(citiesOfHuila);
		totalCities.addAll(citiesOfNarinio);
		totalCities.addAll(citiesOfPutumayo);
		totalCities.addAll(citiesOfQuindio);
		totalCities.addAll(citiesOfRisaralda);
		totalCities.addAll(citiesOfValle);

		origin = new Circle();
		destiny = new Circle();
		pane = new AnchorPane();
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
		String origen=cmbxChooseOrigin.getSelectionModel().getSelectedItem();
		String destino=cmbxChooseDestination.getSelectionModel().getSelectedItem();
		int indexOrigen=travel.getIndexCity(origen);
		int indexDestino=travel.getIndexCity(destino);
		Stack<Ciudad> rutaTemp=travel.rutaDijkstraCosto(indexOrigen,indexDestino);
		for(int i=0;i<rutaTemp.size();i++) {
			Circle c = (Circle) pane.lookup("#" + rutaTemp.get(i).getCiudad());
			origin = c;
			origin.setFill(Color.BLUEVIOLET);
			labelOrigin.setText(c.getId());
		}
	}



	@FXML
	public void calculateFastestRoute(ActionEvent event) {
		String origen=cmbxChooseOrigin.getSelectionModel().getSelectedItem();
		String destino=cmbxChooseDestination.getSelectionModel().getSelectedItem();
		int indexOrigen=travel.getIndexCity(origen);
		int indexDestino=travel.getIndexCity(destino);
		Stack<Ciudad> rutaTemp=travel.rutaDijkstra(indexOrigen,indexDestino);
		for(int i=0;i<rutaTemp.size();i++) {
			Circle ci = (Circle) pane.lookup("#" + rutaTemp.get(i).getCiudad());
			origin = ci;
			origin.setFill(Color.BLUEVIOLET);
			labelOrigin.setText(ci.getId());
		}
	}


	public void initializeComboBoxOfDepartments() {
		List<String> departments = new ArrayList<String>();

		departments=travel.departamentos();

		ObservableList<String> observableList = FXCollections.observableArrayList(departments);
		cmbxChooseOriginDepartment.setItems(observableList);
		cmbxChooseDestinationDepartment.setItems(observableList);
		eventOrigin();
		eventDestination();
	}



	public void eventOrigin() {
		cmbxChooseOriginDepartment.setOnAction(e -> cmbxChooseOrigin.setItems(observableList(cmbxChooseOriginDepartment)) );

		cmbxChooseOriginDepartment.valueProperty().addListener((ov, p1, p2) -> {
			//System.out.println("Nueva Selecci?n: " + p2);
			// System.out.println("Vieja Selecci?n: " + p1);
		});
	}

	public void eventDestination() {
		cmbxChooseDestinationDepartment.setOnAction(e -> cmbxChooseDestination.setItems(observableList(cmbxChooseDestinationDepartment)) );

		cmbxChooseDestinationDepartment.valueProperty().addListener((ov, p1, p2) -> {
			//System.out.println("Nueva Selecci?n: " + p2);
			// System.out.println("Vieja Selecci?n: " + p1);
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
			else if(comboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Nari?o")) {
				observableList = FXCollections.observableArrayList(citiesOfNarinio);
				return observableList;
			}
			else if(comboBox.getSelectionModel().getSelectedItem().equals("Putumayo")) {
				observableList = FXCollections.observableArrayList(citiesOfPutumayo);
				return observableList;
			}
			else if(comboBox.getSelectionModel().getSelectedItem().equals("Quindio")) {
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
	@FXML
	public void selectOrigin() {
		AudioClip sound = new AudioClip("file:resources/sounds/click.mp3");
		sound.play();

		if (cmbxChooseOrigin.getSelectionModel().getSelectedItem() != null) {
			Circle c = (Circle) pane.lookup("#" + cmbxChooseOrigin.getSelectionModel().getSelectedItem());
			origin.setFill(Color.RED);
			origin = c;
			origin.setFill(Color.BLUE);
			labelOrigin.setText(c.getId());
		} else {
			String message = " Please select your origin node.\nYou have to select it from the menu";
			errorAlert(message);
		}

	}

	@FXML
	public void selectDestiny() {
		AudioClip sound = new AudioClip("file:resources/sounds/click.mp3");
		sound.play();

		if (cmbxChooseDestination.getSelectionModel().getSelectedItem() != null) {
			Circle c = (Circle) pane.lookup("#"+cmbxChooseDestination.getSelectionModel().getSelectedItem());
			destiny.setFill(Color.RED);
			destiny = c;
			destiny.setFill(Color.BLUE);
			labelDestination.setText(c.getId());
		} else {
			String message = " Please select your destination node.\nYou have to select it from the menu";
			errorAlert(message);
		}

	}

	@FXML
	public void deleteRoute(ActionEvent event) {
		cmbxChooseOriginDepartment.getSelectionModel().clearSelection();
		cmbxChooseOrigin.getSelectionModel().clearSelection();
		cmbxChooseDestinationDepartment.getSelectionModel().clearSelection();
		cmbxChooseDestination.getSelectionModel().clearSelection();

		labelOrigin.setText("");
		labelDestination.setText("");

		origin.setFill(Color.RED);
		destiny.setFill(Color.RED);
	}



	@FXML
	public void toBuyTicketWindow(ActionEvent event) throws IOException {
		AudioClip sound = new AudioClip("file:resources/sounds/click.mp3");
		sound.play();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sales.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
	}

	public Circle circleId(String id) {
		boolean stop = false;
		Circle circle = null;
		for (int i = 0; i < pane.getChildren().size() && !stop; i++) {
			if (pane.getChildren().get(i) instanceof Circle) {
				System.out.println(id + " = " + pane.getChildren().get(i).getId());
				if (pane.getChildren().get(i).getId().equals(id)) {
					circle = (Circle) pane.getChildren().get(i);
					circle.setFill(Color.YELLOW);
					stop = true;
				}
			}
		}
		return circle;
	}

	@FXML
	public void buyTickey(ActionEvent event) {

	}


	@FXML
	public void cancelPurchase(ActionEvent event) throws IOException {
		AudioClip sound = new AudioClip("file:resources/sounds/click.mp3");
		sound.play();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MapRoutes.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		initializeComboBoxOfDepartments();
		mainStage.show();
	}

	public void errorAlert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("TRANSPORTADORA 1A");
		alert.setHeaderText("Error");
		alert.setContentText(message);
		alert.show();
	}

}
