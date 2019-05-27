package com.contacts;

import com.contacts.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {

    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private App mainApp;

    /**
     * Default constructor called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class; called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Called by the main application to give a reference back to itself.
     *
     * @param mainApp a reference to the main application
     */
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;

        personTable.setItems(mainApp.getPersonData()); // Add observable list data to the table
    }
}
