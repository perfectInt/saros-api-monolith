package ru.saros.sarosapimonolith.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.saros.sarosapimonolith.api.init.UserInit;
import ru.saros.sarosapimonolith.api.services.AuthService;
import ru.saros.sarosapimonolith.models.dtos.LoginDto;
import ru.saros.sarosapimonolith.models.dtos.RegisterDto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import({AuthService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerIT {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    RegisterDto registerDto;
    LoginDto loginDto;

    @MockBean
    private UserInit conf;

    @BeforeAll
    public void init() {
        registerDto = new RegisterDto(
                "la@mail.ru",
                "12345678",
                "12345678",
                "Jotaro",
                "Kujo"
        );
        loginDto = new LoginDto(
                "la@mail.ru",
                "12345678"
        );
    }

    @Test
    @Order(1)
    @DisplayName("Registration test")
    public void registrationTest() throws Exception {

        RequestBuilder requestBuilderPost = MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDto));

        MvcResult mvcResult = mockMvc.perform(requestBuilderPost)
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertNotNull(response);
        assertTrue(response.length() > 20);
    }

    @Test
    @Order(2)
    @DisplayName("Registration with the same data")
    public void secondRegistrationTest() throws Exception {
        RequestBuilder requestBuilderPost = MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDto));

        mockMvc.perform(requestBuilderPost).andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    @DisplayName("Registration if password and passwordConfirmation are not equal")
    public void passwordRegistrationTest() throws Exception {
        registerDto.setPasswordConfirmation("123456789");
        RequestBuilder requestBuilderPost = MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDto));

        mockMvc.perform(requestBuilderPost).andExpect(status().isBadRequest());
    }

    @Test
    @Order(4)
    @Disabled
    @DisplayName("Registration with incorrect email format")
    public void incorrectEmailFormatRegistrationTest() throws Exception {
        registerDto.setPasswordConfirmation("12345678");
        registerDto.setEmail("notemail");

        RequestBuilder requestBuilderPost = MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDto));

        mockMvc.perform(requestBuilderPost).andExpect(status().is4xxClientError());
    }

    @Test
    @Order(5)
    @DisplayName("Log in test")
    public void logInTest() throws Exception {
        RequestBuilder requestBuilderPost = MockMvcRequestBuilders.post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(loginDto));

        MvcResult mvcResult = mockMvc.perform(requestBuilderPost)
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertNotNull(response);
        assertTrue(response.length() > 20);
    }

    @Test
    @Order(6)
    @DisplayName("Attempt to log in throw not existing user")
    public void incorrectEmailLogInTest() throws Exception {
        loginDto.setEmail("l@mail.ru");

        RequestBuilder requestBuilderPost = MockMvcRequestBuilders.post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(loginDto));

        mockMvc.perform(requestBuilderPost).andExpect(status().isNotFound());
    }

    @Test
    @Order(7)
    @DisplayName("Attempt to log in with incorrect password")
    public void incorrectPasswordLogInTest() throws Exception {
        loginDto.setEmail("la@mail.ru");
        loginDto.setPassword("123455678");

        RequestBuilder requestBuilderPost = MockMvcRequestBuilders.post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(loginDto));

        mockMvc.perform(requestBuilderPost).andExpect(status().isForbidden());
    }
}
