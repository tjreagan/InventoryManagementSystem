package main.view_controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.model.Inventory;
import main.model.Part;
import main.model.Product;

import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;

import static main.model.Inventory.*;

public class AddProductController implements Initializable {

    @FXML
    private TableView<Part> tblAllParts;
    @FXML
    private TableColumn<Part, Integer> tblAllPartsIdColumn;
    @FXML
    private TableColumn<Part, String> tblAllPartsNameColumn;
    @FXML
    private TableColumn<Part, Integer> tblAllPartsInvLvlColumn;
    @FXML
    private TableColumn<Part, Double> tblAllPartsPPUColumn;
    @FXML
    private TableView<Part> tblAsParts;
    @FXML
    private TableColumn<Part, Integer> tblAsPartsIdColumn;
    @FXML
    private TableColumn<Part, String> tblAsPartsNameColumn;
    @FXML
    private TableColumn<Part, Integer> tblAsPartsInvLvlColumn;
    @FXML
    private TableColumn<Part, Double> tblAsPartsPPUColumn;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtInvLvl;
    @FXML
    private TextField txtPPU;
    @FXML
    private TextField txtMax;
    @FXML
    private TextField txtMin;
    @FXML
    private TextField txtSearchParts;

    Product newProduct;
    ObservableList<Part> asParts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        int productId = Inventory.getAllProducts().size();
        txtId.setText(Integer.toString(productId));

        tblAllPartsIdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Part, Integer> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getID());
            }
        });
        tblAllPartsNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Part, String> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getName());
            }
        });
        tblAllPartsInvLvlColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Part, Integer> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getStock());
            }
        });
        tblAllPartsPPUColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Part, Double> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getPrice());
            }
        });
        tblAsPartsIdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Part, Integer> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getID());
            }
        });
        tblAsPartsNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Part, String> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getName());
            }
        });
        tblAsPartsInvLvlColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Part, Integer> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getStock());
            }
        });
        tblAsPartsPPUColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Part, Double> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getPrice());
            }
        });
        updateAllPartsTable();
    }

    @FXML
    private void searchParts(ActionEvent actionEvent)
    {
        ObservableList<Part> searchTable = lookupPart(txtSearchParts.getText());
        tblAllParts.setItems(searchTable);
    }

    private void updateAllPartsTable() {
        tblAllParts.setItems(getAllParts());
    }

    @FXML
    public void addAsPart(ActionEvent actionEvent)
    {
        asParts.add(tblAllParts.getSelectionModel().getSelectedItem());
        updateAsPartsTable();
    }

    @FXML
    public void delAsPart(ActionEvent actionEvent)
    {
        asParts.remove(tblAsParts.getSelectionModel().getSelectedItem());
        updateAsPartsTable();
    }

    private void updateAsPartsTable()
    {
        tblAsParts.setItems(asParts);
    }

    @FXML
    public void addProductCancel(ActionEvent actionEvent) throws Exception
    {
        Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene mainScene = new Scene(parent);
        Stage mainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    @FXML
    public void addProductSave(ActionEvent actionEvent) throws Exception
    {

        int id, stock, min, max;
        String name;
        double price;

        id = Integer.parseInt(txtId.getText());
        name = txtProductName.getText();
        price = Double.parseDouble(txtPPU.getText());
        stock = Integer.parseInt(txtInvLvl.getText());
        min = Integer.parseInt(txtMin.getText());
        max = Integer.parseInt(txtMax.getText());

        double totalCostOfParts = 0.0;

        newProduct = new Product(id, name, price, stock, min, max);

        ListIterator<Part> partIterator;

        if(asParts.size() != 0)
        {
            partIterator = asParts.listIterator();
            while(partIterator.hasNext())
            {
                newProduct.addAssociatedPart(partIterator.next());
                partIterator.previous();
                totalCostOfParts += partIterator.next().getPrice();
            }
            if(newProduct.getPrice() > totalCostOfParts)
            {
                addProduct(newProduct);

                Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));
                Scene mainScene = new Scene(parent);
                Stage mainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                mainStage.setScene(mainScene);
                mainStage.show();
            }
            else
            {
                Alert notEnoughParts = new Alert(Alert.AlertType.ERROR );
                notEnoughParts.setTitle("Information");
                notEnoughParts.setHeaderText("Price Error.");
                notEnoughParts.setContentText("Cost of product must be greater than the total cost of the parts.");
                notEnoughParts.showAndWait();
            }

        }
        else
        {
            Alert notEnoughParts = new Alert(Alert.AlertType.ERROR );
            notEnoughParts.setTitle("Information");
            notEnoughParts.setHeaderText("Not enough parts.");
            notEnoughParts.setContentText("Must have at least 1 associated part.");
            notEnoughParts.showAndWait();
        }
    }
}
