package com.etnetera.hr;

import static org.hamcrest.Matchers.eventFrom;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.etnetera.hr.data.FrameworkRequestDTO;
import com.etnetera.hr.rest.HypeLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Class used for Spring Boot/MVC based tests.
 * 
 * @author Etnetera
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JavaScriptFrameworkTests {

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private JavaScriptFrameworkRepository repository;

	private LocalDate date = LocalDate.now();

	private void prepareData() throws Exception {
		JavaScriptFramework react = new JavaScriptFramework("ReactJS");
		JavaScriptFramework vue = new JavaScriptFramework("Vue.js");
		
		repository.save(react);
		repository.save(vue);
	}

	private void prepareMoreComplexData() throws Exception {
	    JavaScriptFramework framework1 = new JavaScriptFramework("framework1", "10.0", HypeLevel.BORN_TO_LOSE, date.plusYears(1));
	    JavaScriptFramework framework2 = new JavaScriptFramework("framework1", "10.1", HypeLevel.PEAK_OF_EXPECTATION, date.plusYears(2));
	    JavaScriptFramework framework3 = new JavaScriptFramework("framework2", "1.2", HypeLevel.CLIBING_THE_SLOPE, date);

	    repository.save(framework1);
	    repository.save(framework2);
	    repository.save(framework3);
    }


	@Test
	public void frameworksTest() throws Exception {
		prepareData();

		mockMvc.perform(get("/frameworks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("ReactJS")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("Vue.js")));
	}

	@Test
    public void addNewFramework() throws JsonProcessingException, Exception {
	    JavaScriptFramework framework = new JavaScriptFramework("Framework1");

	    mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(framework)))
                .andExpect(status().isOk());

	    mockMvc.perform(get("/frameworks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Framework1")));
    }
	
	@Test
	public void addFrameworkInvalid() throws JsonProcessingException, Exception {
		JavaScriptFramework framework = new JavaScriptFramework();
		mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(framework)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors[0].field", is("name")))
				.andExpect(jsonPath("$.errors[0].message", is("must not be null")));

		framework.setName("verylongnameofthejavascriptframeworkjavaisthebest");
		mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(framework)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", hasSize(1)))
			.andExpect(jsonPath("$.errors[0].field", is("name")))
			.andExpect(jsonPath("$.errors[0].message", is("length must be between 0 and 30")));
	}

	@Test
    public void deleteFrameworkByName() throws Exception{
	    prepareMoreComplexData();
	    FrameworkRequestDTO request = new FrameworkRequestDTO(null, "framework1", null, null );
	    mockMvc.perform(post("/remove").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(request)))
                .andExpect(status().isOk());


        mockMvc.perform(get("/frameworks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("framework2")));
    }

    @Test
    public void deleteByIdAndName() throws Exception{
	    prepareMoreComplexData();

	    FrameworkRequestDTO request = new FrameworkRequestDTO(1L, "framework1", null, null);

	    mockMvc.perform(post("/remove").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(request)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/frameworks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("framework1")));
    }

    @Test
    public void updateFrameworks() throws Exception{
	    prepareMoreComplexData();

	    FrameworkRequestDTO request = new FrameworkRequestDTO(null, "framework1", null, null, null, null, HypeLevel.DEPRICATED
                ,null);

        mockMvc.perform(post("/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("framework1")))
                .andExpect(jsonPath("$[0].name", is("framework1")))
                .andExpect(jsonPath("$[0].hypeLevel", is("DEPRICATED")));

	}

	@Test
    public void searchFrameworks() throws Exception{
	    prepareMoreComplexData();

	    FrameworkRequestDTO request1 = new FrameworkRequestDTO(2L, null, null, null);
	    FrameworkRequestDTO request2 = new FrameworkRequestDTO(null, "framework1", null, null);
	    FrameworkRequestDTO request3 = new FrameworkRequestDTO(1L, "framework2", null, null);
	    FrameworkRequestDTO request4 = new FrameworkRequestDTO(null, null, null, HypeLevel.CLIBING_THE_SLOPE);

        mockMvc.perform(post("/search").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(request1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("framework1")))
                .andExpect(jsonPath("$[0].version", is("10.1")));

        mockMvc.perform(post("/search").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(request2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("framework1")));

        mockMvc.perform(post("/search").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(request3)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        mockMvc.perform(post("/search").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(request4)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("framework2")));
        }
	
}
