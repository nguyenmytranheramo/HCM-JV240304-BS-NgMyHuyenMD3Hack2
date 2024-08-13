

package ra.model;

import java.util.Scanner;

public class Catalog {
    private int catalogId;
    public static int autoId=0;
    private String catalogName;
    private String descriptions;

    // Constructors
    public Catalog() {
    }

    public Catalog( String catalogName, String descriptions) {
        this.catalogId = ++autoId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
    }

    // Getters and Setters
    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        if (catalogName != null && !catalogName.isEmpty()) {
            this.catalogName = catalogName;
        } else {
            throw new IllegalArgumentException("Catalog name cannot be empty.");
        }
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        if (descriptions != null && !descriptions.isEmpty()) {
            this.descriptions = descriptions;
        } else {
            throw new IllegalArgumentException("Descriptions cannot be empty.");
        }
    }

    private static String validateCatalogName(Scanner scanner) {
        String name;
        do {
            System.out.print("Nhập tên danh mục (6-30 ký tự): ");
            name = scanner.nextLine();
            if (name.length() < 6 || name.length() > 30) {
                System.out.println("Tên danh mục không hợp lệ! Vui lòng nhập từ 6 đến 30 ký tự.");
            }
        } while (name.length() < 6 || name.length() > 30);
        return name;
    }

    private static String validateProductName(Scanner scanner) {
        String name;
        do {
            System.out.print("Nhập tên sản phẩm (không được để trống): ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Tên sản phẩm không được để trống. Vui lòng nhập lại.");
            }
        } while (name.trim().isEmpty());
        return name;
    }


    @Override
    public String toString() {
        return "Catalog ID: " + catalogId + ", Catalog Name: " + catalogName + ", Description: " + descriptions;
    }
}