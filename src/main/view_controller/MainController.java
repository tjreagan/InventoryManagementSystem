package main.view_controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.model.Inventory;
import main.model.Part;
import main.model.Product;

import java.net.URL;
import java.util.ResourceBundle;

import static main.model.Inventory.*;

public class MainController implements Initializable {

    @FXML
    private TableView<Part> tblParts;
    @FXML
    private TableColumn<Part, Integer> tblPartsIDColumn;
    @FXML
    private TableColumn<Part, String> tblPartsNameColumn;
    @FXML
    private TableColumn<Part, Integer> tblPartsInvLvlColumn;
    @FXML
    private TableColumn<Part, Double> tblPartsPPUColumn;
    @FXML
    private TableView<Product> tblProducts;
    @FXML
    private TableColumn<Product, Integer> tblProductsIDColumn;
    @FXML
    private TableColumn<Product, String> tblProductsNameColumn;
    @FXML
    private TableColumn<Product, Integer> tblProductsInvLvlColumn;
    @FXML
    private TableColumn<Product, Double> tblProductsPPUColumn;
    @FXML
    private TextField txtSearchParts;
    @FXML
    private TextField txtSearchProducts;

    private static Part partToModify;
    private static int indexOfPartToModify;
    private static Product productToModify;
    private static int indexOfProductToModify;

    public static int getIndexOfPartToModify()
    {
        return indexOfPartToModify;
    }
    public static int getIndexOfProductToModify()
    {
        return indexOfProductToModify;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        tblPartsIDColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Part, Integer> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getID());
            }
        });
        tblPartsNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Part, String> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getName());
            }
        });
        tblPartsInvLvlColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Part, Integer> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getStock());
            }
        });
        tblPartsPPUColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Part, Double> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getPrice());
            }
        });
        tblProductsIDColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Product, Integer> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getID());
            }
        });
        tblProductsNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getName());
            }
        });
        tblProductsInvLvlColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Product, Integer> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getStock());
            }
        });
        tblProductsPPUColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Product, Double> partIntegerCellDataFeatures) {
                return new ReadOnlyObjectWrapper(partIntegerCellDataFeatures.getValue().getPrice());
            }
        });
        updatePartsTable();
        updateProductsTable();
    }

    @FXML
    private void exitProgram(ActionEvent actionEvent)
    {
        System.exit(0);
    }

    @FXML
    private void deleteProduct(ActionEvent actionEvent)
    {
        Product productToDelete = tblProducts.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(productToDelete);
        updatePartsTable();
    }

    @FXML
    private void modifyProduct(ActionEvent actionEvent) throws Exception
    {
        Parent parent = FXMLLoader.load(getClass().getResource("modifyProduct.fxml"));
        Scene addPartScene = new Scene(parent);
        Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    @FXML
    private void addProduct (ActionEvent actionEvent) throws Exception
    {
        Parent parent = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        Scene addPartScene = new Scene(parent);
        Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    @FXML
    private void deletePart(ActionEvent actionEvent)
    {
        Inventory.deletePart(tblParts.getSelectionModel().getSelectedItem());
        updatePartsTable();
    }

    @FXML
    private void modifyPart(ActionEvent actionEvent) throws Exception
    {
        partToModify = tblParts.getSelectionModel().getSelectedItem();
        indexOfPartToModify = getAllParts().indexOf(partToModify);
        Parent parent = FXMLLoader.load(getClass().getResource("modifyPart.fxml"));
        Scene addPartScene = new Scene(parent);
        Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    @FXML
    private void addPart(ActionEvent actionEvent) throws Exception
    {
        Parent parent = FXMLLoader.load(getClass().getResource("addPart.fxml"));
        Scene addPartScene = new Scene(parent);
        Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }
    @FXML
    private void searchParts(ActionEvent actionEvent)
    {
        ObservableList<Part> searchTable = lookupPart(txtSearchParts.getText());
        tblParts.setItems(searchTable);
    }

    @FXML
    private void searchProducts(ActionEvent actionEvent)
    {
        lookupProduct(txtSearchProducts.getText());
    }

    public void updatePartsTable()
    {
        tblParts.setItems(getAllParts());
    }

    public void updateProductsTable()
    {
        tblProducts.setItems(getAllProducts());
    }


}
