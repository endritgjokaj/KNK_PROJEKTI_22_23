package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Perdoruesi;
import repository.UserRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PerdoruesitController extends HomeController implements Initializable {

    @FXML
    private TextField filterField;

    @FXML
    private Label fluturimett;

    @FXML
    private Button fshijperdoruesin;

    @FXML
    private Button goBack;

    @FXML
    private Button kerko;

    @FXML
    private TableColumn emri;

    @FXML
    private TableColumn mbiemri;

    @FXML
    private Pagination pagination;

    @FXML
    private TableView tabela;

    @FXML
    private Button teGjitha;

    @FXML
    private TableColumn username;

    @FXML
    private TableColumn gjinia;

    @FXML
    void fshijPerdoruesinEPerzgjedhur(ActionEvent event) throws Exception{
        Perdoruesi perdoruesi = (Perdoruesi) tabela.getSelectionModel().getSelectedItem();
        if (perdoruesi != null){
            int id = perdoruesi.getId();
            UserRepository.delete(id);
        }
    }


    @FXML
    void kerkoStudentin(ActionEvent event) throws Exception {
        if (!filterField.getText().equals("")){
            ObservableList<Perdoruesi> perdoruesit = UserRepository.getAll(filterField.getText());

            tabela.setItems(perdoruesit);
        }

    }

    @FXML
    void shfaqTeGjithePerdoruesit(ActionEvent event) throws SQLException {
        ObservableList<Perdoruesi> perdoruesit = UserRepository.getAll("");

        tabela.setItems(perdoruesit);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        mbiemri.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        gjinia.setCellValueFactory(new PropertyValueFactory<>("gjinia"));
    }

    @FXML
    public void goToUsers(ActionEvent actionEvent) throws IOException {
        goTo("Perdoruesit", "perdoruesit.fxml", actionEvent);

    }

    @FXML
    public void goToStats(ActionEvent actionEvent) throws IOException {
        goTo("Statistikat", "diagramet.fxml", actionEvent);

    }

    @FXML
    public void goToFluturimet(ActionEvent actionEvent) throws IOException {
        goTo("Fluturimet", "fluturimet.fxml", actionEvent);

    }

    @Override
    void translateEnglish(){

    }

    @Override
    void translateAlbanian(){

    }
}
