import java.util.List;
import java.util.Scanner;

import ra.model.Catalog;
import ra.model.Product;
import ra.service.CatalogService;
import ra.service.ProductService;

public class BookManagement {
    private static final CatalogService catalogService = new CatalogService();
    private static final ProductService productService = new ProductService();
    public static byte getByte() {
        Scanner sc = new Scanner(System.in);
        byte result = 0;
        boolean validInput = false;
        do {
            System.out.print("Nhập lựa chọn (điền 1 số): ");
            String input = sc.nextLine();
            try {
                result = Byte.parseByte(input);
                validInput = true; // Input is valid
            } catch (NumberFormatException e) {
                System.err.println("Đầu vào không hợp lệ. Vui lòng nhập một số nguyên.");
            }
        } while (!validInput);

        return result;
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println("**************************BASIC-MENU**************************\n" +
                    "1. Quản lý danh mục\n" +
                    "2. Quản lý sản phẩm\n" +
                    "3. Thoát");
            byte choice = getByte();
            switch (choice) {
                case 1:
                    menuCatalog();
                    break;
                case 2:
                    menuProduct();
                    break;
                case 3:
                    System.out.println("Tạm biệt");
                    break;
                default:
                    System.err.println("Lựa chọn ko chính xác , vui lòng nhập lại");
            }
            if (choice == 3) {
                break;
            }
        }
    }


    private static void menuCatalog() {
        while (true) {
            System.out.println("********************CATALOG-MANAGEMENT********************\n" +
                    "1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục\n" +
                    "2. Hiển thị thông tin tất cả các danh mục\n" +
                    "3. Sửa tên danh mục theo mã danh mục\n" +
                    "4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm)\n" +
                    "5. Quay lại");
            Scanner scanner = new Scanner(System.in);
            byte choice = getByte();
            switch (choice) {
                case 1:
                    addCatalog(scanner);
                    break;
                case 2:
                    displayAllCatalogs();
                    break;
                case 3:
                    updateCatalog(scanner);
                    break;
                case 4:
                    deleteCatalog(scanner);
                    break;
                case 5:
                    return;
default:
        System.err.println("Lựa chọn ko chính xác , vui lòng nhập lại");
            }
                    }
                    }

private static void addCatalog(Scanner scanner) {
    System.out.print("Nhập số lượng danh mục cần thêm: ");
    int count = Integer.parseInt(scanner.nextLine());
    for (int i = 0; i < count; i++) {
        System.out.print("Nhập tên danh mục thứ " + (i + 1));
        String catalogName = scanner.nextLine();
        System.out.print("Nhập mô tả danh mục ");
        String descriptions = scanner.nextLine();

        Catalog catalog = new Catalog(catalogName, descriptions);
        catalogService.save(catalog);
    }
}

private static void displayAllCatalogs() {
    List<Catalog> catalogList = catalogService.getAll();
    for (Catalog catalog : catalogList) {
        System.out.println(catalog);
    }
}

private static void updateCatalog(Scanner scanner) {
    System.out.print("Nhập mã danh mục cần sửa: ");
    int catalogId = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    Catalog catalog = catalogService.findById(catalogId);
    if (catalog != null) {
        System.out.print("Nhập tên danh mục mới: ");
        String newName = scanner.nextLine();
        catalog.setCatalogName(newName);
        System.out.println("Cập nhật thành công.");
    } else {
        System.out.println("Không tìm thấy danh mục.");
    }
}

private static void deleteCatalog(Scanner scanner) {
    System.out.print("Nhập mã danh mục cần xóa: ");
    int catalogId = scanner.nextInt();
    Catalog catalog = catalogService.findById(catalogId);
    if (catalog != null) {
        if (productService.getAll().stream().noneMatch(p -> p.getCatalog().getCatalogId() == catalogId)) {
            catalogService.delete(catalogId);
            System.out.println("Xóa danh mục thành công.");
        } else {
            System.err.println("Không thể xóa danh mục đang có sản phẩm.");
        }
    } else {
        System.err.println("Không tìm thấy danh mục.");
    }
}

private static void menuProduct() {
    while (true) {
        System.out.println("********************PRODUCT-MANAGEMENT********************\n" +
                "1. Nhập số sản sản phẩm và nhập thông tin sản phẩm \n" +
                "2. Hiển thị thông tin các sản phẩm \n" +
                "3. Sắp xếp sản phẩm theo giá giảm dần \n" +
                "4. Xóa sản phẩm theo mã \n" +
                "5. Tìm kiếm sách theo tên sách\n" +"6. Thay đổi thông tin của sách theo mã sách \n" +
        "7. Quay lại");
Scanner scanner = new Scanner(System.in);
byte choice = getByte();
            switch (choice) {
        case 1:
addProduct(scanner);
                    break;
                            case 2:
displayAllProducts();
                    break;
                            case 3:
sortProductsByPrice();
                    break;
                            case 4:
deleteProduct(scanner);
                    break;
                            case 5:
searchProductByName(scanner);
                    break;
                            case 6:
updateProduct(scanner);
                case 7:
                        return;
default:
        System.err.println("Lựa chọn ko chính xác , vui lòng nhập lại");
            }
                    }
                    }

private static void addProduct(Scanner scanner) {
    System.out.print("Nhập số lượng sản phẩm cần thêm: ");
    int count = Integer.parseInt(scanner.nextLine());
    for (int i = 0; i < count; i++) {
        String productId = scanner.nextLine();

        System.out.print("Nhập tên sản phẩm thứ " + (i + 1) + ":");
        String productName = scanner.nextLine();

        System.out.print("Nhập giá sản phẩm: ");
        double productPrice = Double.parseDouble(scanner.nextLine());

        System.out.print("Nhập mô tả sản phẩm: ");
        String description = scanner.nextLine();

        System.out.print("Nhập số lượng sản phẩm ");
        int stock = Integer.parseInt(scanner.nextLine());

        System.out.println("Chọn danh mục cho sản phẩm:");
        displayAllCatalogs();
        int catalogId = Integer.parseInt(scanner.nextLine());
        Catalog catalog = catalogService.findById(catalogId);

        if (catalog != null) {
            Product product = new Product(productId, productName, productPrice, description, stock, catalog, true);
            productService.save(product);
        } else {
            System.out.println("Danh mục không tồn tại.");
        }
    }
}

private static void displayAllProducts() {
    List<Product> productList = productService.getAll();
    for (Product product : productList) {
        System.out.println(product);
    }
}

private static void sortProductsByPrice() {
    productService.sortByPriceDescending();
    displayAllProducts();
}

private static void deleteProduct(Scanner scanner) {
    System.out.print("Nhập mã sản phẩm cần xóa: ");
    String productId = scanner.next();
    Product product = productService.findById(productId);
    if (product != null) {
        productService.delete(productId);
            System.out.println("Xóa sản phẩm thành công.");
        } else {
                System.out.println("Không tìm thấy sản phẩm.");
        }
                }

private static void searchProductByName(Scanner scanner) {
    System.out.print("Nhập tên sản phẩm cần tìm: ");
    String productName = scanner.nextLine();
    List<Product> result = productService.searchByName(productName);
    if (result.isEmpty()) {
        System.err.println("Không tìm thấy sản phẩm nào.");
    } else {
        for (Product product : result) {
            System.out.println(product);
        }
    }
}

private static void updateProduct(Scanner scanner) {
    System.out.print("Nhập mã sản phẩm cần cập nhật (bắt đầu bằng 'P' và theo sau là 4 chữ số): ");
    String productId = scanner.nextLine();

    Product product = productService.findById(productId);
    if (product != null) {
        System.out.print("Nhập tên sản phẩm mới: ");
        String newName = scanner.nextLine();
        System.out.print("Nhập giá sản phẩm mới: ");
        double newPrice = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập mô tả sản phẩm mới: ");
        String newDescription = scanner.nextLine();
        System.out.print("Nhập số lượng tồn kho mới ");
        int newStock = Integer.parseInt(scanner.nextLine()); ;

        product.setProductName(newName);
        product.setProductPrice(newPrice);
        product.setDescription(newDescription);
        product.setStock(newStock);

        System.out.println("Cập nhật sản phẩm thành công.");
        menuProduct();
    } else {
        System.err.println("Không tìm thấy sản phẩm.");
    }
}

}