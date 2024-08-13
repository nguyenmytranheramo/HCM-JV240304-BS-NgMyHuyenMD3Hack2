package ra.model;

public class Product {
    private String productId;
    private String productName;
    private double productPrice;
    private String description;
    private int stock;
    private Catalog catalog;
    private boolean status = true;

    // Constructors
    public Product() {}

    public Product(String productId, String productName, double productPrice, String description, int stock, Catalog catalog, boolean status) {
        this.setProductId(productId);
        this.setProductName(productName);
        this.setProductPrice(productPrice);
        this.setDescription(description);
        this.setStock(stock);
        this.setCatalog(catalog);
        this.status = status;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        if (productId != null && productId.matches("P\\d{4}")) {
            this.productId = productId;
        } else {
            throw new IllegalArgumentException("Product ID must start with 'P' and be followed by 4 digits.");
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName != null && !productName.isEmpty()) {
            this.productName = productName;
        } else {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        if (productPrice > 0) {
            this.productPrice = productPrice;
        } else {
            throw new IllegalArgumentException("Product price must be greater than 0.");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock >= 10) {
            this.stock = stock;
        } else {
            throw new IllegalArgumentException("Stock must be at least 10.");
        }
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        if (catalog != null) {
            this.catalog = catalog;
        } else {
            throw new IllegalArgumentException("Catalog cannot be null.");
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String statusStr = status ? "Available" : "Not Available";
        return "Product ID: " + productId + ", Product Name: " + productName + ", Price: " + productPrice + ", Description: " + description +", Stock: " + stock + ", Catalog: [" + catalog.toString() + "], Status: " + statusStr;
    }
}