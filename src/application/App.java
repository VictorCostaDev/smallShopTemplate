package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

public class App {
    public static void main(String[] args) throws Exception {
        
        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Product> products = new ArrayList<>();

        System.out.print("Enter the number of products: ");
        int numberOfProducts = scan.nextInt();

        for(int index = 1; index <= numberOfProducts; index++) {
            System.out.printf("Product #%d data\n", index);
            System.out.print("Common, used or imported (c/u/i): ");
            char statusOfProduct = scan.next().charAt(0);

            System.out.print("Name: ");
            scan.nextLine();
            String productName = scan.nextLine();
            System.out.print("Price: ");
            double productPrice = scan.nextDouble();
            if(statusOfProduct == 'u') {
                System.out.print("Manufacture date (DD/MM/YYYY): ");
                Date productManufactureDate = sdf.parse(scan.next());

                products.add(new UsedProduct(productName, productPrice, productManufactureDate));
            } else if( statusOfProduct == 'i') {
                System.out.print("Customs fee: ");
                double productCustomsFee = scan.nextDouble();

                products.add(new ImportedProduct(productName, productPrice, productCustomsFee));
            } else {
                products.add(new Product(productName, productPrice));
            }
        }

        scan.close();

        System.out.println();
        System.out.println("Price Tags: ");
        for (Product product : products) {
            System.out.println(product.priceTag());
        }

    }
}
