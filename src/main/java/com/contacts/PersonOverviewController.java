package com.contacts;

import com.contacts.model.Person;
import com.contacts.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    private App mainApp; // Reference to the main application.

    /**
     * Default constructor, called before the initialize() method.
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

        showPersonDetails(null); // To clear any possibly remaining data in the UI

        // Listen for selection changes in the UI & display a persons data as they are selected
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Shows a person's details by filling in the text fields.
     * If the provided Person object is null, the text fields will be set to empty Strings.
     *
     * @param person a Person object representing the person whose details you want to display
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    /**
     * Handle for when the user clicks on the 'delete' button in the UI.
     * Gets the currently selected person's index from the ObservableList and removes them. If no person
     * is selected then a warning is shown to the user.
     */
    @FXML
    private void handleDeletePerson() {
        final int selectedIndex = personTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Warning");
            alert.setHeaderText("No Entry Selected");
            alert.setContentText("Please select a person in the table before attempting to delete an entry.");

            alert.showAndWait();
        }
    }

    /**
     * Provides the main application with a reference to itself.
     *
     * @param mainApp a reference to the main application
     */
    void setMainApp(App mainApp) {
        this.mainApp = mainApp;

        personTable.setItems(mainApp.getPersonData()); // Add observable list data to the table
    }
}
