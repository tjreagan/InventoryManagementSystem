package main.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.InHouse;
import main.model.Inventory;
import main.model.Outsourced;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable
{
    @FXML
    private RadioButton rbtnInHouse;
    @FXML
    private RadioButton rbtnOutsourced;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtPtName;
    @FXML
    private TextField txtInv;
    @FXML
    private TextField txtPPU;
    @FXML
    private TextField txtMax;
    @FXML
    private TextField txtCompanyName;
    @FXML
    private TextField txtMin;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;

    private boolean isInhouse = true;

    @FXML
    public void addPartInHouse(ActionEvent actionEvent)
    {
        isInhouse = true;
        txtCompanyName.setPromptText("Machine ID");
        rbtnOutsourced.setSelected(false);
    }

    @FXML
    public void addPartOutsourced(ActionEvent actionEven)
    {
        isInhouse = false;
        txtCompanyName.setPromptText("Company Name");
        rbtnInHouse.setSelected(false);
    }

    @FXML
    public void addPartSave(ActionEvent actionEvent) throws Exception
    {
        int id, stock, min, max;
        String name;
        double price;
        
        id = Integer.parseInt(txtId.getText());
        name = txtPtName.getText();
        price = Double.parseDouble(txtPPU.getText());
        stock = Integer.parseInt(txtInv.getText());
        min = Integer.parseInt(txtMin.getText());
        max = Integer.parseInt(txtMax.getText());
        
        if(isInhouse)
        {
            int machineId = Integer.parseInt(txtCompanyName.getText());
            InHouse partToAdd = new InHouse(id, name, price, stock, min, max, machineId);
            Inventory.addPart(partToAdd);
        }
        else
        {
            String companyName = txtCompanyName.getText();
            Outsourced partToAdd = new Outsourced(id, name, price, stock, min, max, companyName);
            Inventory.addPart(partToAdd);
        }
        Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene mainScene = new Scene(parent);
        Stage mainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    @FXML
    public void addPartCancel(ActionEvent actionEvent) throws Exception
    {
        Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene mainScene = new Scene(parent);
        Stage mainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        int partId = Inventory.getAllParts().size();
        txtId.setText(Integer.toString(partId));
    }


}
