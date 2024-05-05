package ppl.a2.apitesting.testcase.update;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import ppl.a2.apitesting.models.User;
import ppl.a2.apitesting.util.ApiClient;
import ppl.a2.apitesting.util.CustomAssertionError;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class TC7 {
    private final Map<String, String> headers = new HashMap<>();
    private ApiClient client;
    private CustomAssertionError errorList;
    private String userId;

    @BeforeEach
    void setUp() {
        headers.put("app-id", "6633dd597309359fe9ceb8ad");
        client = new ApiClient("https://dummyapi.io/data/v1", headers);
        errorList = new CustomAssertionError();
        userId = "60d0fe4f5311236168a109cc";
    }

    @Test
    void testUpdateTitleMr() {
        String url = "/user/" + userId;
        Response initialUser = client.get(url);
        User expected = initialUser.as(User.class);
        if (expected.getError() != null) {
            errorList.appendMessage(expected.getError());
        }
        expected.setTitle("mr");
        String reqBody = "{\"title\" : \"mr\"}";
        int expectedStatus = 200;
        Response actualRes = client.put(url, reqBody);
        // Perform assertions
        try {
            assertEquals(expectedStatus, actualRes.statusCode());
        } catch (AssertionError e) {
            // Append error message to customAssertionError
            errorList.appendMessage("Expected status: " + expectedStatus + ", but was: " + actualRes.statusCode());
        }

        User actual = actualRes.as(User.class);
        if (actual.getError() != null) {
            errorList.appendMessage(actual.getError());
        }
        List<String> differences = expected.compare(actual);
        errorList.appendMessages(differences);

        // If any assertions failed, throw CustomAssertionError
        if (errorList.getMessage().length() > 0) {
            throw errorList;
        }
    }
}
