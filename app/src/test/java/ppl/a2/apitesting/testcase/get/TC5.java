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

public class TC5 {
    private final Map<String, String> headers = new HashMap<>();
    private ApiClient client;
    private CustomAssertionError errorList;

    @BeforeEach
    void setUp() {
        headers.put("app-id", "6633dd597309359fe9ceb8ad");
        client = new ApiClient("https://dummyapi.io/data/v1", headers);
        errorList = new CustomAssertionError();
    }
    
    @Test
    void TestAppIdNotRegistered() {
        String url = "/user/60d0fe4f5311236168a109fa5";
        headers.put("app-id", "6633dd597309359fe9ceb8ad");
        client = new ApiClient("https://dummyapi.io/data/v1", headers);
        Response actualRes = client.get(url);

        try {
            assertEquals(400, actualRes.statusCode());
        } catch (AssertionError e) {
            errorList.appendMessage("Expected status: 400 (Unauthorized), but was: " + actualRes.statusCode());
        }

        if (errorList.getMessage().length() > 0) {
            throw errorList;
        }
    }
}