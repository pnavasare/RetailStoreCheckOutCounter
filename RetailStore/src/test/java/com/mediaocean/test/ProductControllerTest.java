package com.mediaocean.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediaocean.Model.Product;
import com.mediaocean.Service.ProductService;
import com.mediaocean.controller.ProductController;
import com.mediaocean.util.ProductCategory;

import SpringWebAppInitializer.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={SpringWebAppInitializer.class})
public class ProductControllerTest {

		public ProductControllerTest() {
			super();
			// TODO Auto-generated constructor stub
		}
		   @Mock
		   private ProductService prodService ;
  
	    @InjectMocks
	    private ProductController prodController ;

	    private MockMvc mockMvc;
	    
	    @Before
	    public void init() throws Exception{
	    	MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(prodController).build();
	    }

	   @Test
	    public void getProductbyIdTest1() throws Exception{
		   Product product = new Product();
			product.setId(1L);
			product.setName("proname");
			product.setPrice(21540.00);
			product.setBarCodeId("1");
			product.setProductCategory(ProductCategory.CatA);
			Mockito.when(prodService.getProduct(Mockito.anyString())).thenReturn(product);
			mockMvc.perform(MockMvcRequestBuilders.get("/product/1")).andExpect(MockMvcResultMatchers.status().isNotFound());

	    }
	   
	   @Test
		public void addPoductTest() throws Exception {
		   Product product = new Product();
				product.setId(1L);
				product.setName("proname");
				product.setPrice(21540.00);
				product.setBarCodeId("1");
				product.setProductCategory(ProductCategory.CatA);
			   ObjectMapper mapper = new ObjectMapper();
			   String jsonString = mapper.writeValueAsString(product);
			   Mockito.when(prodService.getProduct(Mockito.anyString())).thenReturn(product);
			   Mockito.when(prodService.addProduct(Mockito.any(Product.class))).thenReturn(product);
			   MvcResult result = mockMvc
					.perform(MockMvcRequestBuilders.post("/products").content(jsonString)
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
			   Assert.assertEquals(201, result.getResponse().getStatus());
		}

	   @Test
	    public void getProductbyIdTest2() throws Exception{
		   Product product = new Product();
			product.setId(1L);
			product.setName("proname");
			product.setPrice(21540.00);
			product.setBarCodeId("1");
			product.setProductCategory(ProductCategory.CatA);
			Mockito.when(prodService.getProduct(Mockito.anyString())).thenReturn(product);
			mockMvc.perform(MockMvcRequestBuilders.get("/products/1")).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.barCodeId").value("1"));

	    }
	   
	   @Test
		public void updateProductTest() throws Exception {
		    Product product = new Product();
			product.setId(1L);
			product.setName("proname");
			product.setPrice(21000.00);
			product.setBarCodeId("1");
			product.setProductCategory(ProductCategory.CatA);
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(product);
			Mockito.when(prodService.getProduct(Mockito.anyString())).thenReturn(product);
			Mockito.when(prodService.updateProduct(Mockito.any(Product.class),Mockito.anyString())).thenReturn(product);
			mockMvc.perform(
					MockMvcRequestBuilders.put("/products/1").content(jsonString).contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().is(200));
		}
	   
	   @Test
		public void deleteProductTest() throws Exception {
		    Product product = new Product();
			product.setId(1L);
			product.setName("proname");
			product.setPrice(21000.00);
			product.setBarCodeId("1");
			product.setProductCategory(ProductCategory.CatA);
			Mockito.when(prodService.getProduct(Mockito.anyString())).thenReturn(product);
			Mockito.when(prodService.deleteProduct(Mockito.anyString())).thenReturn(product);
			mockMvc.perform(MockMvcRequestBuilders.delete("/products/1")).andExpect(MockMvcResultMatchers.status().is(200));
		}



}
