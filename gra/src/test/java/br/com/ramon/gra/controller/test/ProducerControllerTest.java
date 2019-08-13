package br.com.ramon.gra.controller.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ProducerControllerTest {

	@Autowired
	private MockMvc mock;

	@Test
	public void testProducersResult() throws Exception {
		MvcResult result = this.mock.perform(get("/producers-result")).andExpect(status().isOk()).andDo(print())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();

		JSONObject content = new JSONObject(result.getResponse().getContentAsString());

		JSONAssert.assertEquals(
				"{\"min\":[{\"followingWin\":1991,\"producer\":\"Joel Silver\",\"interval\":1,\"previousWin\":1990}],\"max\":[{\"followingWin\":2015,\"producer\":\"Matthew Vaughn\",\"interval\":13,\"previousWin\":2002}]}",
				content, false);
	}

}
