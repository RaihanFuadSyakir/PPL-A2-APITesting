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

public class TC2 {
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
    void TestAppIdInvalid() {
        String url = "/user/60d0fe4f5311236168a109fd";
        headers.put("app-id", "6633dd597309359fe9ceb8aa");
        client = new ApiClient("https://dummyapi.io/data/v1", headers);
        Response actualRes = client.get(url);

        try {
            assertEquals(403, actualRes.statusCode());
        } catch (AssertionError e) {
            errorList.appendMessage("Expected status: 403 (Unauthorized), but was: " + actualRes.statusCode());
        }

        if (errorList.getMessage().length() > 0) {
            throw errorList;
        }
    }
}