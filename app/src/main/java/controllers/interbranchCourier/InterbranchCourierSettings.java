package main.java.controllers.interbranchCourier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import main.java.App;
import main.java.SceneManager;
import main.java.features.Preference;

import java.net.URL;
import java.util.ResourceBundle;

public class InterbranchCourierSettings implements Initializable {

    private final ObservableList<String> languages = FXCollections.observableArrayList("Polski", "English");
    private final ObservableList<String> colors = FXCollections.observableArrayList(
            App.getLanguageProperties("colorOrange"),
            App.getLanguageProperties("colorRed"),
            App.getLanguageProperties("colorWhite"));
    @FXML
    private ComboBox<String> pickLanguage;
    @FXML
    private ComboBox<String> pickColor;
    @FXML
    private AnchorPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pickLanguage.setItems(languages);
        if (Preference.readPreference("language").equals("english")) {
            pickLanguage.setValue(languages.get(1));
        } else {
            pickLanguage.setValue(languages.get(0));
        }

        pickColor.setItems(colors);
        if (Preference.readPreference("color").equals("orange")) {
            pickColor.setValue(colors.get(0));
        } else if (Preference.readPreference("color").equals("red")) {
            pickColor.setValue(colors.get(1));
        } else {
            pickColor.setValue(colors.get(2));
        }

    }

    /**
     * method that changes system preference of language to polish or english
     * and changes displaying language in application
     *
     * @param event
     */
    @FXML
    void changeLanguage(ActionEvent event) {
        Preference pref = new Preference();
        if (pickLanguage.getValue().equals("English"))
            pref.addPreference("language", "english");
        else {
            pref.addPreference("language", "polski");
        }
        SceneManager.renderScene("interbranchCourier");
    }

    /**
     * method that adds system preference of color, which changes theme of application
     *
     * @param event
     */
    @FXML
    void changeTheme(ActionEvent event) {
        Preference pref = new Preference();
        if (pickColor.getValue().equals(App.getLanguageProperties("colorOrange"))) {
            pref.addPreference("color", "orange");
            SceneManager.getStage().getScene().getRoot().setStyle("-fx-main-color: #ffa500;" +
                    "-fx-second-color: #000000;");
        } else if (pickColor.getValue().equals(App.getLanguageProperties("colorRed"))) {
            pref.addPreference("color", "red");
            SceneManager.getStage().getScene().getRoot().setStyle("-fx-main-color: #d82020;" +
                    "-fx-second-color: #ffffff;");
        } else if (pickColor.getValue().equals(App.getLanguageProperties("colorWhite"))) {
            pref.addPreference("color", "white");
            SceneManager.getStage().getScene().getRoot().setStyle("-fx-main-color: #FFFFFF;" +
                    "-fx-second-color: #000000;");
        }
    }
}
