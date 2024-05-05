package ppl.a2.apitesting.testcase.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import ppl.a2.apitesting.util.ApiClient;
import ppl.a2.apitesting.util.CustomAssertionError;

public class CreateUserAPITest {
    private final Map<String, String> headers = new HashMap<>();
    private ApiClient client;
    private CustomAssertionError errorList;

    @BeforeEach
    void setUp() {
        headers.put("app-id", "662e62e6bb70a7bb4025960f"); // Update with your valid app-id
        client = new ApiClient("https://dummyapi.io/data/v1", headers);
        errorList = new CustomAssertionError();
    }

    @Test
    void testCreateUserWithValidField() {
        String url = "/user/create";
        String requestBody = "{\"firstName\": \"abc\", \"lastName\": \"naf\", \"email\": \"abcnaf@gmail.com\"}";
        int expectedStatus = 201;

        Response actualRes = client.post(url, requestBody);

        try {
            assertEquals(expectedStatus, actualRes.statusCode());
        } catch (AssertionError e) {
            errorList.appendMessage("Expected status: " + expectedStatus + ", but was: " + actualRes.statusCode());
        }

        if (errorList.getMessage().length() > 0) {
            throw errorList;
        }
    }

    @Test
    void testCreateUserWithValidLongNameAndEmail() {
        String url = "/user/create";
        String requestBody = "{\"firstName\": \"azyxemiypezvgkfiqexmrabkryvcjfhyefzvtkumnriatzbmt\", \"lastName\": \"gjrauzkdawvtunncyacdzzemjzevabpezxuvuuyrdfqpgvtjg\", \"email\": \"azyxemigjrauz@gmail.com\"}";
        int expectedStatus = 201;

        Response actualRes = client.post(url, requestBody);

        try {
            assertEquals(expectedStatus, actualRes.statusCode());
        } catch (AssertionError e) {
            errorList.appendMessage("Expected status: " + expectedStatus + ", but was: " + actualRes.statusCode());
        }

        if (errorList.getMessage().length() > 0) {
            throw errorList;
        }
    }

    @Test
    void testCreateUserWithExistingEmail() {
        String url = "/user/create";
        String requestBody = "{\"firstName\": \"budi\", \"lastName\": \"yanto\", \"email\": \"budiyanto@gmail.com\"}";
        int expectedStatus = 400;

        Response actualRes = client.post(url, requestBody);

        try {
            assertEquals(expectedStatus, actualRes.statusCode());
        } catch (AssertionError e) {
            errorList.appendMessage("Expected status: " + expectedStatus + ", but was: " + actualRes.statusCode());
        }

        if (errorList.getMessage().length() > 0) {
            throw errorList;
        }
    }

    @Test
    void testCreateUserWithShortNameAndExistingEmail() {
        String url = "/user/create";
        String requestBody = "{\"firstName\": \"ab\", \"lastName\": \"ya\", \"email\": \"abya@gmail.com\"}";
        int expectedStatus = 400;

        Response actualRes = client.post(url, requestBody);

        try {
            assertEquals(expectedStatus, actualRes.statusCode());
        } catch (AssertionError e) {
            errorList.appendMessage("Expected status: " + expectedStatus + ", but was: " + actualRes.statusCode());
        }

        if (errorList.getMessage().length() > 0) {
            throw errorList;
        }
    }

    @Test
    void testCreateUserWithLongNameAndExistingEmail() {
        String url = "/user/create";
        String requestBody = "{\"firstName\": \"hridbpeauqxzvcwzcqitmchjpfyiwbjmhjamitbniqxvzttcvy\", \"lastName\": \"hnzagnhfdpkdxgmmggjbnmpdcciucmahnatgzfpihhqufatczz\", \"email\": \"hridbpeahnzagnhf@gmail.com\"}";
        int expectedStatus = 400;

        Response actualRes = client.post(url, requestBody);

        try {
            assertEquals(expectedStatus, actualRes.statusCode());
        } catch (AssertionError e) {
            errorList.appendMessage("Expected status: " + expectedStatus + ", but was: " + actualRes.statusCode());
        }

        if (errorList.getMessage().length() > 0) {
            throw errorList;
        }
    }
}

