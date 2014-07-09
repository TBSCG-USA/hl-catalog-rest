package hello;

import java.util.List;

public class Product {

    private long sku;
    private String productype;
    private String quantity;
    private String overview;
    private String description;
    private String pdfLink;
    private List<String> benefits;
    private String productname;
    private String message;
    private List<String> linkedSkus;
    private String disclaimer;
    private String icon;
    private String category;
    private String subcategory;
    
    public Product(long sku, String productype, String quantity, String productname, 
    		String overview, String description, String pdfLink, List<String> benefits, List<String> linkedSkus, 
    		String disclaimer, String icon, String category, String subcategory,
    		String message) {
        this.sku = sku;
        this.productype = productype;
        this.quantity = quantity;
        this.productname = productname;
        this.overview = overview;
        this.description = description;
        this.pdfLink = pdfLink;
        this.benefits = benefits;
        this.linkedSkus = linkedSkus;
        this.disclaimer = disclaimer;
        this.icon = icon;
        this.category = category;
        this.subcategory = subcategory;
        this.message = this.toString();
    }
    
    @Override
    public String toString() {
        return String.format(
                "Product[sku=%d, productname='%s', productype='%s', quantity='%s', linkedSkus='%s', disclaimer='%s', category='%s', subcategory='%s' ]",
                sku, productname, productype, quantity, linkedSkus, disclaimer, category, subcategory );
    }
    
    public String getMessage() {
		return message;
	}

	public long getSku() {
        return sku;
    }

    public String getProductype() {
        return productype;
    }

	public String getQuantity() {
		return quantity;
	}

	public String getOverview() {
		return overview;
	}

	public String getDescription() {
		return description;
	}

	public String getPdfLink() {
		return pdfLink;
	}

	public List<String> getBenefits() {
		return benefits;
	}

	public String getProductname() {
		return productname;
	}

	public List<String> getLinkedSkus() {
		return linkedSkus;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public String getIcon() {
		return icon;
	}

	public String getCategory() {
		return category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSku(long sku) {
		this.sku = sku;
	}

	public void setProductype(String productype) {
		this.productype = productype;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPdfLink(String pdfLink) {
		this.pdfLink = pdfLink;
	}

	public void setBenefits(List<String> benefits) {
		this.benefits = benefits;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setLinkedSkus(List<String> linkedSkus) {
		this.linkedSkus = linkedSkus;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	
}
