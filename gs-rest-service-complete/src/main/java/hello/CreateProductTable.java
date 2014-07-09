package hello;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class CreateProductTable {
	
	public static SimpleDriverDataSource getDatasource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUsername("sa");
        dataSource.setUrl("jdbc:h2:mem");
        dataSource.setPassword("");
        return dataSource;
	}
	
	public static void main(String[] args) {
        // simple DS for test (not for production!)
        SimpleDriverDataSource dataSource = getDatasource();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        System.out.println("Creating product table");
        jdbcTemplate.execute("drop table product if exists");
        
        //should use category id and subcategory id from category table
        jdbcTemplate.execute("create table product(" +
                "sku serial, productype varchar(255), quantity varchar(255), productname varchar(255), "
                + "overview varchar(255), description TEXT, pdfLink varchar(255), benefits Text,"
                + "linkedSkus varchar(255), disclaimer varchar(255), icon varchar(255), "         
                + "category varchar(255),"
                + "subcategory varchar(255) )");        
      
        
        List<Product> products = new ArrayList<Product>();
        List<String> benefits = new ArrayList<String>();
		benefits.add("Re-energize your life");
		benefits.add("Lose weight");
		benefits.add("Tastes great");
		benefits.add("High protein, low calorie.");
		
		List<String> benefits2 = new ArrayList<String>();
		benefits2.add("Re-balance your life");
		benefits2.add("Control your health");
		benefits2.add("Provides recommended DRA of vitamins and minerals");
		List<String> linkedSkus = new ArrayList<String>();
		linkedSkus.add("5");
		linkedSkus.add("15");
		
		String disclaimer = "May cause sneezing in young children."; 
		
		String icon = "/yadda/yadda/eyeball-32.gif";
		String category = "Drinks";
		String subcategory = "Shakes";
		
        Product prod1 = new Product(1,"shake","55","Cleanse me now drink","Great taste and cleans your body.", 
        		"Have the shake as a meal 2 times a day for good health.", "/yaddayadda/herbalife-pdf-056.pdf", benefits, 
        		linkedSkus, disclaimer, icon, category, subcategory,
        		"");
        List<String> linkedSkus2 = new ArrayList<String>();
        linkedSkus2.add("6");
		linkedSkus2.add("26");
		disclaimer = "May make one hyper-active or nauseous.";
        icon = "/yadda/yadda/whirlygig-5.gif";
		category = "Vitamins &amp; Supplements";
		subcategory = "Tablets";
		
        Product prod2 = new Product(3,"vitamin tablet pack","125","Vita-Good Tablets","Provides essential nutrients and vitamins for daily health.", 
        		"Take by mouth once a day for good health.", "/vitamins/herbalpack-06.pdf", benefits2, 
        		linkedSkus2, disclaimer, icon, category, subcategory,
        		"");
        /*Product prod3 = new Product();
        Product prod4 = new Product();*/
        products.add(prod1);
        products.add(prod2);
        /*products.add(prod3);
        products.add(prod4);*/
        for (Product prod : products) {
            System.out.printf("Inserting product record for %s\n", prod.getProductname());
            String benefitString = StringUtils.join(prod.getBenefits(),"|");
            String skuString = StringUtils.join(prod.getLinkedSkus(),"|");
            /*
             * linkedSkus varchar(255), disclaimer varchar(255), icon varchar(255), "         
                + "category varchar(255),"
                + "subcategory
             */
            jdbcTemplate.update(
                    "INSERT INTO product(sku, productype, quantity, productname, "
                + "overview, description, pdfLink, benefits, linkedSkus, disclaimer, icon, category, subcategory) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    prod.getSku(), prod.getProductype(), prod.getQuantity(), prod.getProductname(),
                    prod.getOverview(), prod.getDescription(), prod.getPdfLink(), benefitString,
                    skuString, prod.getDisclaimer(), prod.getIcon(), prod.getCategory(), prod.getSubcategory());
        }
        
        //now retieve records
        System.out.println("nearing the end!");
        getRecords(dataSource,jdbcTemplate);
	}
	
	public static void getRecords(SimpleDriverDataSource dataSource, JdbcTemplate jdbcTemplate) {
		List<Product> results = null;
		try {
			results = jdbcTemplate.query(
					"select * from product where sku > ?", new Object[] { 0 },
					new RowMapper<Product>() {
						@Override
						public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
							String benefitStr = rs.getString("benefits");
							List<String> benefitList = new ArrayList<String>();
							if (benefitStr.length()>0) {
								String[] benes = benefitStr.split("\\|");
								for (String b : benes) {
									benefitList.add(b);
								}
							}
							String skuListStr = rs.getString("linkedSkus");
							List<String> skuList = new ArrayList<String>();
							if (skuListStr.length()>0) {
								String[] skus = skuListStr.split("\\|");
								for (String sk : skus) {
									skuList.add(sk);
								}
							}
							return new Product(rs.getLong("sku"), rs.getString("productype"),
									rs.getString("quantity"), rs.getString("productname"), rs.getString("overview"),
									rs.getString("description"), rs.getString("pdfLink"), benefitList, skuList, 
									rs.getString("disclaimer"), rs.getString("icon"), rs.getString("category"), rs.getString("subcategory"),"");
						}
					});

			for (Product product : results) {
				System.out.println(product.toString());
			}

		} catch(Exception er) {
			System.out.println("uh-oh!");
			er.printStackTrace();
		}
		System.out.println("AT the end!");
		
	}
}