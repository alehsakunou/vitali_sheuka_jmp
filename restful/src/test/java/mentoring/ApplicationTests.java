package mentoring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.json.JacksonMappingAwareSortTranslator;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

//	@Autowired
//	private EmployeeRepository employeeRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
//		employeeRepository.deleteAll();
	}

    @Test
    public void shouldWork() throws Exception {
//
//        User employee = new User();
//        employee.setFirstName("Vitali1");
//        employee.setLastName("Surname1");
//        employee.setStatus(User.EmployeeStatus.MENTORING);
//
//        User.Address address = new User.Address();
//        address.setStreet("Street1");
//        address.setCountry("BY");
//        address.setCity("Gomel");
//        address.setZipCode("1111");
//        employee.setAddress(address);
//
//
//
//        Project project = new Project();
//        project.setName("Project1");
//
//
//        List<Project> projects = new ArrayList<>();
//        projects.add(project);
//
//        employee.setProject(projects);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(employee);
//
//        System.out.println(json);
//        mockMvc.perform(post("/employee").content(json)
//        ).andExpect(
//                status().isCreated()).andExpect(
//                header().string("Location", containsString("employee/")));
    }
    

//	@Test
//	public void shouldReturnRepositoryIndex() throws Exception {
//
//		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
//				jsonPath("$._links.employee").exists());
//	}
//
//	@Test
//	public void shouldCreateEntity() throws Exception {
//
//		mockMvc.perform(post("/employee").content(
//				"{\"firstName\": \"Vitali\", \"lastName\":\"Surname\"}")).andExpect(
//						status().isCreated()).andExpect(
//								header().string("Location", containsString("employee/")));
//	}
//
//	@Test
//	public void shouldRetrieveEntity() throws Exception {
//
//		MvcResult mvcResult = mockMvc.perform(post("/employee").content(
//				"{\"firstName\": \"Vitali\", \"lastName\":\"Surname\"}")).andExpect(
//						status().isCreated()).andReturn();
//
//		String location = mvcResult.getResponse().getHeader("Location");
//		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
//				jsonPath("$.firstName").value("Vitali")).andExpect(
//						jsonPath("$.lastName").value("Surname"));
//	}
//
//	@Test
//	public void shouldQueryEntity() throws Exception {
//
//		mockMvc.perform(post("/employee").content(
//				"{ \"firstName\": \"Vitali\", \"lastName\":\"Surname\"}")).andExpect(
//						status().isCreated());
//
//		mockMvc.perform(
//				get("/employee/search/findByLastName?name={name}", "Surname")).andExpect(
//						status().isOk()).andExpect(
//								jsonPath("$._embedded.employee[0].firstName").value(
//										"Vitali"));
//	}
//
//	@Test
//	public void shouldUpdateEntity() throws Exception {
//
//		MvcResult mvcResult = mockMvc.perform(post("/employee").content(
//				"{\"firstName\": \"Vitali\", \"lastName\":\"Surname\"}")).andExpect(
//						status().isCreated()).andReturn();
//
//		String location = mvcResult.getResponse().getHeader("Location");
//
//		mockMvc.perform(put(location).content(
//				"{\"firstName\": \"Bilbo\", \"lastName\":\"Surname\"}")).andExpect(
//						status().isNoContent());
//
//		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
//				jsonPath("$.firstName").value("Bilbo")).andExpect(
//						jsonPath("$.lastName").value("Surname"));
//	}
//
//	@Test
//	public void shouldPartiallyUpdateEntity() throws Exception {
//
//		MvcResult mvcResult = mockMvc.perform(post("/employee").content(
//				"{\"firstName\": \"Vitali\", \"lastName\":\"Surname\"}")).andExpect(
//						status().isCreated()).andReturn();
//
//		String location = mvcResult.getResponse().getHeader("Location");
//
//		mockMvc.perform(
//				patch(location).content("{\"firstName\": \"Bilbo Jr.\"}")).andExpect(
//						status().isNoContent());
//
//		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
//				jsonPath("$.firstName").value("Bilbo Jr.")).andExpect(
//						jsonPath("$.lastName").value("Surname"));
//	}
//
//	@Test
//	public void shouldDeleteEntity() throws Exception {
//
//		MvcResult mvcResult = mockMvc.perform(post("/employee").content(
//				"{ \"firstName\": \"Bilbo\", \"lastName\":\"Surname\"}")).andExpect(
//						status().isCreated()).andReturn();
//
//		String location = mvcResult.getResponse().getHeader("Location");
//		mockMvc.perform(delete(location)).andExpect(status().isNoContent());
//
//		mockMvc.perform(get(location)).andExpect(status().isNotFound());
//	}
}