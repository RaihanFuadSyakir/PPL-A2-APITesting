package ppl.a2.apitesting.testcase.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.Response;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import ppl.a2.apitesting.models.User;
import ppl.a2.apitesting.util.ApiClient;
import ppl.a2.apitesting.util.CustomAssertionError;

public class TC0 {
    private final Map<String, String> headers = new HashMap<>();
    private ApiClient client;
    private CustomAssertionError errorList;

    @BeforeEach
    void setUp() {
        headers.put("app-id", "662e62e6bb70a7bb4025960f");
        client = new ApiClient("https://dummyapi.io/data/v1", headers);
        errorList = new CustomAssertionError();
    }

    @Test
    void testGetSuccess() {
        User expected = new User("662e83b01846fba28fd56f7f", "Budi", "Yanto", "budiyanto@gmail.com",
                "2024-04-28T17:13:20.540Z", "2024-04-28T17:13:20.540Z");
        int expectedStatus = 200;
        String url = "/user/662e83b01846fba28fd56f7f";
        Response actualRes = client.get(url);
        // Perform assertions
        try {
            assertEquals(expectedStatus, actualRes.statusCode());
        } catch (AssertionError e) {
            // Append error message to customAssertionError
            errorList.appendMessage("Expected status: " + expectedStatus + ", but was: " + actualRes.statusCode());
        }
        User actual = actualRes.as(User.class);
        List<String> differences = expected.compare(actual);
        errorList.appendMessages(differences);

        // If any assertions failed, throw CustomAssertionError
        if (errorList.getMessage().length() > 0) {
            throw errorList;
        }
    }

    @Test
    void testGetFailed() {
        User expected = new User("662e83b01846fba28fd56f7f", "Budis", "Yantos", "budiyantos@gmail.com",
                "2024-04-28T17:13:20.540Z", "2024-04-28T17:13:20.540Z");
        int expectedStatus = 201;
        String url = "/user/662e83b01846fba28fd56f7f";
        Response actualRes = client.get(url);
        // Perform assertions
        try {
            assertEquals(expectedStatus, actualRes.statusCode());
        } catch (AssertionError e) {
            // Append error message to customAssertionError
            errorList.appendMessage("Expected status: " + expectedStatus + ", but was: " + actualRes.statusCode());
        }
        User actual = actualRes.as(User.class);
        List<String> differences = expected.compare(actual);
        errorList.appendMessages(differences);

        // If any assertions failed, throw CustomAssertionError
        if (errorList.getMessage().length() > 0) {
            throw errorList;
        }
    }
}
