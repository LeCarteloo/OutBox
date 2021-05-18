package main.java.controllers.manager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.java.App;
import main.java.controllers.auth.Login;
import main.java.dao.PackagesDAO;
import main.java.dao.UserInfosDAO;
import main.java.dao.UsersDAO;
import main.java.entity.Packages;
import main.java.entity.UserInfos;
import main.java.entity.Users;
import main.java.features.Alerts;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManagerNullCourier implements Initializable {

    @FXML
    private AnchorPane appWindow;

    @FXML
    private Label noDataText;

    @FXML
    private Pane panePackages;

    @FXML
    private Label packageNumber;

    @FXML
    private Label cityAndStreet;

    @FXML
    private Label city;

    @FXML
    private Label voivo;

    @FXML
    private Label howManyPackages;

    @FXML
    private Pane alertPane;

    @FXML
    private Label labelAlertPane;

    @FXML
    private Button buttonBackPackage;

    @FXML
    private Button buttonNextPackage;

    @FXML
    private Label noDataCourierText;

    @FXML
    private Pane paneCourier;

    @FXML
    private Label courierNameSurname;

    @FXML
    private Label courierPhone;

    @FXML
    private Label courierEmail;

    @FXML
    private Label howManyCouriers;

    @FXML
    private Button buttonBackCourier;

    @FXML
    private Button buttonNextCourier;

    @FXML
    private Label packageLabel;
    @FXML
    private Label courierLabel;



    UserInfos ui = UserInfosDAO.getUserInfoByID(Login.getUserInfoID()).get(0);
    List<Packages> getPackages;
    List<UserInfos> dataUserInfos;
    int dataIndex = 0;

    List<UserInfos> dataUserInfosCourier;
    List<Users> dataUserCourier;
    int dataIndexCourier = 0;

    @FXML
    void assignPackage(MouseEvent event) {

        PackagesDAO.updatePackage(getPackages.get(dataIndex).getId(),getPackages.get(dataIndex).getTypeId(),
                getPackages.get(dataIndex).getUserId(),dataUserCourier.get(dataIndexCourier).getId(),
                getPackages.get(dataIndex).getUserInfoId(), getPackages.get(dataIndex).getEmail(),
        getPackages.get(dataIndex).getPackageNumber(),getPackages.get(dataIndex).getTimeOfPlannedDelivery(),
                getPackages.get(dataIndex).getAdditionalComment());


        alertPane.setVisible(true);
    }


    public void confirmButton(MouseEvent mouseEvent) {
        dataIndex = 0;
        getPackages = PackagesDAO.getPackagesByNullCourier();
        clearDataPackages();
        clearDataCouriers();
        alertPane.setVisible(false);
    }

    public void setDataLabel(){

        dataUserInfos = UserInfosDAO.getUserInfoByID(getPackages.get(dataIndex).getUserInfoId());
        packageNumber.setText(getPackages.get(dataIndex).getPackageNumber());
        cityAndStreet.setText(dataUserInfos.get(0).getStreetAndNumber());
        voivo.setText(dataUserInfos.get(0).getVoivodeship());
        city.setText(dataUserInfos.get(0).getCity());


        if(getPackages.size() > 1)
        {
            System.out.println("PONAD 1");
            packageLabel.setVisible(true);
            panePackages.setVisible(true);
            buttonBackPackage.setVisible(true);
            buttonNextPackage.setVisible(true);
            noDataText.setVisible(false);
        }
        else if(getPackages.size() == 1)
        {
            System.out.println("JEDEN");
            packageLabel.setVisible(true);
            buttonBackPackage.setVisible(false);
            buttonNextPackage.setVisible(false);
            panePackages.setVisible(true);
            noDataText.setVisible(false);
        }
        else{
            System.out.println("BRAK");
            packageLabel.setVisible(false);
            panePackages.setVisible(false);
            noDataText.setVisible(true);
            buttonBackPackage.setVisible(false);
            buttonNextPackage.setVisible(false);
        }
        howManyPackages.setText((dataIndex+1)+"/"+String.valueOf(getPackages.size()));
    }

    public void setDataLabelCourier(){
        dataUserInfosCourier = UserInfosDAO.getUserInfoByID(dataUserCourier.get(dataIndexCourier).getId());

        courierNameSurname.setText(dataUserInfosCourier.get(0).getName()+" "+dataUserInfosCourier.get(0).getSurname());
        courierPhone.setText(dataUserInfosCourier.get(0).getPhoneNumber());
        courierEmail.setText(dataUserCourier.get(dataIndexCourier).getEmail());

        howManyCouriers.setText((dataIndexCourier+1)+"/"+String.valueOf(dataUserCourier.size()));

        if(dataUserCourier.size() > 1)
        {
            System.out.println("PONAD 1 kurier");
            courierLabel.setVisible(true);
            paneCourier.setVisible(true);
            buttonBackCourier.setVisible(true);
            buttonNextCourier.setVisible(true);
            noDataCourierText.setVisible(false);
        }
        else if(dataUserCourier.size() == 1)
        {
            courierLabel.setVisible(true);
            buttonBackCourier.setVisible(true);
            buttonNextCourier.setVisible(true);
            paneCourier.setVisible(true);
            noDataCourierText.setVisible(false);
        }
        else{
            courierLabel.setVisible(false);
            paneCourier.setVisible(false);
            noDataCourierText.setVisible(true);
            buttonBackCourier.setVisible(false);
            buttonNextCourier.setVisible(false);
        }
    }

    @FXML
    void buttonBack(MouseEvent event) {
        dataIndex--;

        if(dataIndex<0){
            dataIndex=0;
        }
        setDataLabel();
    }

    @FXML
    void buttonNext(MouseEvent event) {
        dataIndex++;

        if(dataIndex>getPackages.size()-1){
            dataIndex=getPackages.size()-1;
        }
        setDataLabel();
    }

    @FXML
    void buttonBackCourier(MouseEvent event) {
        dataIndexCourier--;

        if(dataIndexCourier<0){
            dataIndexCourier=0;
        }
        setDataLabelCourier();
    }

    @FXML
    void buttonNextCourier(MouseEvent event) {
        dataIndexCourier++;

        if(dataIndexCourier>dataUserCourier.size()-1){
            dataIndexCourier=dataUserCourier.size()-1;
        }
        setDataLabelCourier();
    }

    void clearDataPackages(){
        for(int i=0; i<getPackages.size(); i++) {
            dataUserInfos = UserInfosDAO.getUserInfoByID(getPackages.get(i).getUserInfoId());
            //System.out.println("["+i+"]  [user]" + dataUserInfos.get(0).getVoivodeship() +"/[UI]"+ui.getVoivodeship());
            if (!dataUserInfos.get(0).getVoivodeship().equals(ui.getVoivodeship())) {
                getPackages.remove(i);
                i--;
            }
        }

        for(int i=0; i<getPackages.size(); i++) {
            dataUserInfos = UserInfosDAO.getUserInfoByID(getPackages.get(i).getUserInfoId());
            //System.out.println("["+i+"]  [user]" + dataUserInfos.get(0).getVoivodeship() +"/[UI]"+ui.getVoivodeship());
            if (!dataUserInfos.get(0).getVoivodeship().equals(ui.getVoivodeship())) {
                getPackages.remove(i);
                i--;
            }
        }
        if(getPackages.size()==0){
            packageLabel.setVisible(false);
            panePackages.setVisible(false);
            noDataText.setVisible(true);
            buttonBackPackage.setVisible(false);
            buttonNextPackage.setVisible(false);
        }
        else{
            packageLabel.setVisible(true);
            noDataText.setVisible(false);
            setDataLabel();
            howManyPackages.setText((dataIndex+1)+"/"+String.valueOf(getPackages.size()));
        }
    }

    void clearDataCouriers(){
        for(int i=0; i<dataUserCourier.size(); i++) {

            if (!dataUserCourier.get(i).getRole().equals("Kurier")) {
                dataUserCourier.remove(i);
                i--;
            }
        }
        for(int i=0; i<dataUserCourier.size(); i++) {
            dataUserInfosCourier = UserInfosDAO.getUserInfoByID(dataUserCourier.get(i).getId());
            if (!dataUserInfosCourier.get(0).getVoivodeship().equals(ui.getVoivodeship())) {
                dataUserCourier.remove(i);
                i--;
            }
        }
        if(dataUserCourier.size()==0){
            courierLabel.setVisible(false);
            paneCourier.setVisible(false);
            noDataCourierText.setVisible(true);
            buttonBackCourier.setVisible(false);
            buttonNextCourier.setVisible(false);
        }
        else{
            courierLabel.setVisible(true);
            noDataCourierText.setVisible(false);
            System.out.println("ILE KURIERIÓW: " + dataUserCourier.size());

            howManyCouriers.setText((dataIndexCourier+1)+"/"+String.valueOf(dataUserCourier.size()));
            setDataLabelCourier();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getPackages = PackagesDAO.getPackagesByNullCourier();
        dataUserCourier = UsersDAO.getUsers();
        clearDataPackages();
        clearDataCouriers();

    }

}
