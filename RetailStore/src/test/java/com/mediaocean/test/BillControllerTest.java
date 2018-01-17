package com.mediaocean.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediaocean.Model.Bill;
import com.mediaocean.Service.BillService;
import com.mediaocean.controller.BillController;

public class BillControllerTest {

	public BillControllerTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Mock
	private BillService billService;

	@InjectMocks
	private BillController billController;

	private MockMvc mockMvc;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(billController).build();
	}

	@Test
	public void getBillbyIdTest1() throws Exception {
		Bill bill = new Bill(1L);
		Mockito.when(billService.getBillbyId(Mockito.anyLong())).thenReturn(bill);
		mockMvc.perform(MockMvcRequestBuilders.get("/bill/1")).andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	@Test
	public void createBillTest() throws Exception {
		Bill bill = new Bill(1L);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(bill);
		Mockito.when(billService.getBillbyId(Mockito.anyLong())).thenReturn(bill);
		Mockito.when(billService.createBill()).thenReturn(bill);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/bills").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
		Assert.assertEquals(201, result.getResponse().getStatus());
	}
	
	 
	@Test
	public void addProductToBillTest() throws Exception {
		Bill bill = new Bill(1L);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(bill);
		Mockito.when(billService.getBillbyId(Mockito.anyLong())).thenReturn(bill);
		Mockito.when(billService.addProduct(Mockito.anyLong(), Mockito.anyString(), Mockito.anyInt())).thenReturn(bill);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/bill/1/1?quan=2").content(jsonString)
						.contentType(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
		Assert.assertEquals(201, result.getResponse().getStatus());
	}
	
	@Test
	public void updateProductToBillTest() throws Exception {
		Bill bill = new Bill(1L);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(bill);
		Mockito.when(billService.getBillbyId(Mockito.anyLong())).thenReturn(bill);
		Mockito.when(billService.updateProduct(Mockito.anyLong(), Mockito.anyString(), Mockito.anyInt())).thenReturn(bill);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/bill/1/1?quan=3").content(jsonString)
						.contentType(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void deleteProductToBillTest() throws Exception {
		Bill bill = new Bill(1L);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(bill);
		Mockito.when(billService.getBillbyId(Mockito.anyLong())).thenReturn(bill);
		Mockito.when(billService.deleteProduct(Mockito.anyLong(), Mockito.anyString(), Mockito.anyInt())).thenReturn(bill);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete("/bill/1/1?quan=2").content(jsonString)
						.contentType(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());
	}

}
