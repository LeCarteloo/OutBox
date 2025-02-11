package main.java.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import main.java.App;
import main.java.SceneManager;
import main.java.features.Alerts;
import main.java.features.PdfGenerator;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

public class AdminRaport {


    @FXML
    private Button raportButton;

    @FXML
    private TextField fileName;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private AnchorPane RightPaneAnchorPane;

    /**
     * Method that generate report to PDF file from date to date choose from DataPicker
     *
     * @param actionEvent action event
     */

    public void generateRaport(ActionEvent actionEvent) {

        if (fileName.getText().isEmpty()) {
            Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("nameFile"));
        } else {
            if (dateFrom.getValue() == null || dateTo.getValue() == null) {
                Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("pickDate"));
            } else {

                String pathFile;
                File selectedDirectory = filePathSelection();

                if (selectedDirectory == null) {
                    Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("fileSaveLocationNotSelected"));
                } else {
                    if (validateFileName()) {
                        File f = new File(selectedDirectory + fileName.getText() + ".pdf");

                        if (f.exists() && f.isFile()) {
                            Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("fileExists"));
                        } else {

                            if (selectedDirectory.toString().endsWith("\\")) {
                                pathFile = selectedDirectory + fileName.getText() + ".pdf";
                            } else {
                                pathFile = selectedDirectory + "\\" + fileName.getText() + ".pdf";
                            }
                            try {
                                if (dateFrom.getValue() == null || dateTo.getValue() == null ||
                                        dateFrom.getValue().isAfter(dateTo.getValue()) == true ||
                                        dateFrom.getValue().isAfter(LocalDate.now()) ||
                                        dateTo.getValue().isAfter(LocalDate.now())) {
                                    Alerts.createCustomAlert(RightPaneAnchorPane, raportButton, "WARNING",
                                            App.getLanguageProperties("adminGeneratePDFFailure"), 370, 86, "alertFailure");
                                } else {
                                    LocalDate startDataValue = dateFrom.getValue();
                                    LocalDate endDataValue = dateTo.getValue().plusDays(1);

                                    Date startValue = java.sql.Date.valueOf(startDataValue);
                                    Date endValue = java.sql.Date.valueOf(endDataValue);

                                    if (startDataValue != null && endDataValue != null) {

                                        PdfGenerator.createPdf(startValue, endValue, pathFile);
                                        Alerts.createCustomAlert(RightPaneAnchorPane, raportButton, "CHECK",
                                                App.getLanguageProperties("adminGeneratePDF"), 350, 86, "alertSuccess");
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Błąd przy tworzeniu raportu PDF");
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("nameFile"));
                    }
                }
            }
        }
    }

    /**
     * Method that generate report to PDF file from last day
     *
     * @param actionEvent action event
     */
    public void raportLastDay(ActionEvent actionEvent) {

        LocalDate startDataValue = LocalDate.now().plusDays(1);
        LocalDate endDataValue = startDataValue.minusDays(1);

        Date startValue = java.sql.Date.valueOf(startDataValue);
        Date endValue = java.sql.Date.valueOf(endDataValue);


        if (fileName.getText().isEmpty()) {
            Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("nameFile"));
        } else {
            String pathFile;
            //if no path is selected for saving the report
            File selectedDirectory = filePathSelection();

            if (selectedDirectory == null) {
                Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("fileSaveLocationNotSelected"));
            } else {
                if (validateFileName()) {
                    File f = new File(selectedDirectory + fileName.getText() + ".pdf");

                    if (f.exists() && f.isFile()) {
                        Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("fileExists"));
                    } else {

                        if (selectedDirectory.toString().endsWith("\\")) {
                            pathFile = selectedDirectory + fileName.getText() + ".pdf";
                        } else {
                            pathFile = selectedDirectory + "\\" + fileName.getText() + ".pdf";
                        }

                        try {
                            PdfGenerator.createPdf(endValue, startValue, pathFile);
                            Alerts.createCustomAlert(RightPaneAnchorPane, raportButton, "CHECK",
                                    App.getLanguageProperties("adminGeneratePDF"), 350, 86, "alertSuccess");
                        } catch (Exception e) {
                            System.out.println("Błąd przy tworzeniu raportu PDF");
                            e.printStackTrace();
                        }

                    }
                } else {
                    Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("nameFile"));
                }
            }
        }
    }

    /**
     * Method that generate report to PDF file from last month
     *
     * @param actionEvent action event
     */
    public void raportLastMonth(ActionEvent actionEvent) {

        LocalDate startDataValue = LocalDate.now().plusDays(1);
        LocalDate endDataValue = startDataValue.minusMonths(1).minusDays(1);

        Date startValue = java.sql.Date.valueOf(startDataValue);
        Date endValue = java.sql.Date.valueOf(endDataValue);

        if (fileName.getText().isEmpty()) {
            Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("nameFile"));
        } else {
            String pathFile;
            //if no path is selected for saving the report
            File selectedDirectory = filePathSelection();

            if (selectedDirectory == null) {
                Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("fileSaveLocationNotSelected"));
            } else {
                if (validateFileName()) {
                    File f = new File(selectedDirectory + fileName.getText() + ".pdf");

                    if (f.exists() && f.isFile()) {
                        Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("fileExists"));
                    } else {

                        if (selectedDirectory.toString().endsWith("\\")) {
                            pathFile = selectedDirectory + fileName.getText() + ".pdf";
                        } else {
                            pathFile = selectedDirectory + "\\" + fileName.getText() + ".pdf";
                        }

                        try {
                            PdfGenerator.createPdf(endValue, startValue, pathFile);
                            Alerts.createCustomAlert(RightPaneAnchorPane, raportButton, "CHECK",
                                    App.getLanguageProperties("adminGeneratePDF"), 350, 86, "alertSuccess");
                        } catch (Exception e) {
                            System.out.println("Błąd przy tworzeniu raportu PDF");
                            e.printStackTrace();
                        }

                    }
                } else {
                    Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("nameFile"));
                }
            }
        }

    }


    public void raportLastWeek(ActionEvent actionEvent) {


        LocalDate startDataValue = LocalDate.now().plusDays(1);
        LocalDate endDataValue = startDataValue.minusDays(7);

        Date startValue = java.sql.Date.valueOf(startDataValue);
        Date endValue = java.sql.Date.valueOf(endDataValue);


        if (fileName.getText().isEmpty()) {
            Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("nameFile"));
        } else {
            String pathFile;
            //if no path is selected for saving the report
            File selectedDirectory = filePathSelection();

            if (selectedDirectory == null) {
                Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("fileSaveLocationNotSelected"));
            } else {
                if (validateFileName()) {
                    File f = new File(selectedDirectory + fileName.getText() + ".pdf");

                    if (f.exists() && f.isFile()) {
                        Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("fileExists"));
                    } else {

                        if (selectedDirectory.toString().endsWith("\\")) {
                            pathFile = selectedDirectory + fileName.getText() + ".pdf";
                        } else {
                            pathFile = selectedDirectory + "\\" + fileName.getText() + ".pdf";
                        }

                        try {
                            PdfGenerator.createPdf(endValue, startValue, pathFile);
                            Alerts.createCustomAlert(RightPaneAnchorPane, raportButton, "CHECK",
                                    App.getLanguageProperties("adminGeneratePDF"), 350, 86, "alertSuccess");
                        } catch (Exception e) {
                            System.out.println("Błąd przy tworzeniu raportu PDF");
                            e.printStackTrace();
                        }

                    }
                } else {
                    Alerts.createAlert(RightPaneAnchorPane, raportButton, "WARNING", App.getLanguageProperties("nameFile"));
                }
            }
        }


    }

    boolean validateFileName() {
        return !fileName.getText().isEmpty();
    }

    public File filePathSelection() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle(App.getLanguageProperties("titleSaveRaport"));
        File defaultDirectory = new File("c:/");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(SceneManager.getStage());

        return selectedDirectory;
    }
}
