package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.*;
import repository.BiletaRepository;
import repository.PagesaRepository;
import repository.PasagjeriRepository;
import repository.RezervimiRepository;
import service.PasagjeriService;

import java.sql.Date;
import java.sql.SQLException;

public class PagesaController extends HomeController{

    @FXML
    private TextField cardNameField;

    @FXML
    private TextField cardNumberField;

    @FXML
    private RadioButton creditCardRadio;

    @FXML
    private TextField cvvField;

    @FXML
    private RadioButton debitCardRadio;

    @FXML
    private DatePicker expirationDate;

    @FXML
    private ToggleGroup pagesa;

    @FXML
    private BorderPane root;
    Alert alert = new Alert(Alert.AlertType.ERROR,"");
    private static Pasagjeri pasagjeri;
    private static Rezervimi rezervimi;
    private static Bileta bileta;
    private static Bagazhet bagazhi;


    @FXML
    void rezervo(ActionEvent event) throws SQLException {
        if (pagesa.getSelectedToggle() != null && expirationDate.getValue() != null && !cvvField.getText().equals("")
        && !cardNameField.getText().equals("") && !cardNumberField.getText().equals("")){
            String mp = menyraPageses();
            pasagjeri = PasagjeriService.regjistroPasagjerin(pasagjeri);
            int biletaId = BiletaRepository.insert(bileta);
            rezervimi.setBileta_id(biletaId);
            rezervimi.setPasagjeri_id(pasagjeri.getId());
            rezervimi.setPasagjeri_id(pasagjeri.getId());
            RezervimiRepository.insert(rezervimi);
            Pagesa pagesaObj = new Pagesa(0,mp, cardNameField.getText(), cardNumberField.getText(),
                    Date.valueOf(expirationDate.getValue()), cvvField.getText(),biletaId);
            PagesaRepository.insert(pagesaObj);
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Reservation was made successfully!");
            alert.show();
            close();
        }else{
            alert.setContentText("These fields should be filled!");
            alert.show();
        }
    }


    private String menyraPageses(){
        if (pagesa.getSelectedToggle().equals("MasterCard")){
            return "MasterCard";
        }
        return  "Visa";
    }


    public static void setData(Object object){
        if (object instanceof  Pasagjeri){
            pasagjeri = (Pasagjeri) object;
        }else if(object instanceof  Rezervimi){
            rezervimi = (Rezervimi) object;
        }else if(object instanceof Bileta){
            bileta = (Bileta) object;
        }else if(object instanceof Bagazhet){
            bagazhi = (Bagazhet) object;
        }
    }

    @FXML
    public void anuloRezervimin(ActionEvent actionEvent) {
        pasagjeri = null;
        bileta = null;
        rezervimi = null;
        bagazhi = null;
        close();
    }

    void close(){
        Stage stage = (Stage) cardNumberField.getScene().getWindow();
        stage.close();
    }

    @Override
    void translateEnglish(){

    };

    @Override
    void translateAlbanian(){

    };
}
