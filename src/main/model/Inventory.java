package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ListIterator;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart)
    {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct)
    {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId)
    {
        ListIterator<Part> partIterator = allParts.listIterator();
        Part result;
        while(partIterator.hasNext())
        {
            result = partIterator.next();
            if(result.getID() == partId)
            {
                return result;
            }
        }
        return null;
    }

    public static Product lookupProduct(int productId)
    {
        ListIterator<Product> productIterator = allProducts.listIterator();
        Product result;
        while(productIterator.hasNext())
        {
            result = productIterator.next();
            if(result.getID() == productId)
            {
                return result;
            }

        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName)
    {
        ListIterator<Part> partIterator = allParts.listIterator();
        ObservableList<Part> result = FXCollections.observableArrayList();
        while(partIterator.hasNext())
        {
            Part currentPart = partIterator.next();
            if(currentPart.getName().contains(partName))
            {
                result.add(currentPart);
            }
        }
        return result;
    }

    public static ObservableList<Product> lookupProduct(String productName)
    {
        ListIterator<Product> productIterator = allProducts.listIterator();
        ObservableList<Product> result = FXCollections.observableArrayList();
        while(productIterator.hasNext())
        {
            Product currentProduct = productIterator.next();
            if(currentProduct.getName().equals(productName))
            {
                result.add(currentProduct);
            }
        }
        return result;
    }

    public static void updatePart(int index, Part selectedPart)
    {
        allParts.set(index, selectedPart);
    }
    public static void updateProduct(int index, Product newProduct)
    {
        allProducts.set(index, newProduct);
    }
    public static boolean deletePart(Part selectedPart)
    {
        allParts.remove(selectedPart);
        return true;
    }
    public static boolean deleteProduct(Product selectedProduct)
    {
        allProducts.remove(selectedProduct);
        return true;
    }
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }

}
