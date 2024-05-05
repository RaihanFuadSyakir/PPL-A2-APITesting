# Build With
+ JDK 17
+ Gradle 8.0.2

# Prerequisites
+ Installed JDK 17

# Overview Test Cases
| Nomor API | Nama HTTP Method | Total Test Case | Total Test Failure |
| --------- | ---------------- | --------------- | ------------------ |
| F01       | POST             | 43              | 18                 |
| F02       | GET              | 5               | 0                  |
| F03       | PUT              | 24              | 9                  |
| F04       | DELETE           | 6               | 0                  |
# Directory Tree
```md
Proeject Root
│   .gitattributes
│   .gitignore
│   gradlew
│   gradlew.bat
│   README.md
│   settings.gradle.kts
├───.gradle
├───app
│   │   build.gradle.kts
│   │
│   ├───build
│   │   ├───libs
│   │   │       app.jar
│   │   │
│   │   ├───reports
│   │   │   └───tests
│   │   │       └───test
│   │   │           │   index.html
│   │   │           │
│   │   │           ├───classes
│   │   │           │       ppl.a2.apitesting.testcase.update.TC23.html
│   │   │           │       ppl.a2.apitesting.testcase.update.TC5.html
│   │   │           │       ppl.a2.apitesting.testcase.update.TC6.html
│   │   │           │       ppl.a2.apitesting.testcase.update.TC7.html
│   │   │           │       ppl.a2.apitesting.testcase.update.TC8.html
│   │   │           │
│   │   │           ├───css
│   │   │           │       base-style.css
│   │   │           │       style.css
│   │   │           │
│   │   │           ├───js
│   │   │           │       report.js
│   │   │           │
│   │   │           └───packages
│   │   │                   ppl.a2.apitesting.testcase.update.html
│   │   │
│   └───src
│       ├───main
│       │   ├───java
│       │   │   └───ppl
│       │   │       └───a2
│       │   │           └───apitesting
│       │   │               │   App.java
│       │   │               │
│       │   │               └───models
│       │   │                       Location.java
│       │   │                       User.java
│       │   │
│       │   └───resources
│       └───test
│           ├───java
│           │   └───ppl
│           │       └───a2
│           │           └───apitesting
│           │               │   AppTest.java
│           │               │
│           │               ├───testcase
│           │               │   ├───create
│           │               │   ├───delete
│           │               │   ├───get
│           │               │   │       TC0.java
│           │               │   │
│           │               │   └───update
│           │               │           TC23.java
│           │               │           TC5.java
│           │               │           TC6.java
│           │               │           TC7.java
│           │               │           TC8.java
│           │               │
│           │               └───util
│           │                       ApiClient.java
│           │                       CustomAssertionError.java
│           │                       JsonPathHandler.java
│           │
│           └───resources
└───gradle
```

# How to create Test Case
+ Pastikan data yang diuji sudah dapat diparse oleh JsonPathHandler pada ```app\src\test\java\ppl\a2\apitesting\util\JsonPathHandler.java```
+ Khususnya pengujian API pada ```https://dummyapi.io``` User Data tidak perlu membuat Struktur data lagi.
+ Untuk memulai membuat class Test dengan setup evirontmentnya
```java
private final Map<String, String> headers = new HashMap<>();
    private ApiClient client;
    private CustomAssertionError errorList;

    @BeforeEach
    void setUp() {
        headers.put("app-id", "662e62e6bb70a7bb4025960f");
        client = new ApiClient("https://dummyapi.io/data/v1", headers);
        errorList = new CustomAssertionError();
    }
```
lalu membuat method yang berisi test itu sendiri
```java
@Test
    void testGetSuccess() {
        //melakkan setup data awal untuk response
        User expected = new User("662e83b01846fba28fd56f7f", "Budi", "Yanto", "budiyanto@gmail.com",
                "2024-04-28T17:13:20.540Z", "2024-04-28T17:13:20.540Z");
        int expectedStatus = 200;
        String url = "/user/662e83b01846fba28fd56f7f";
        //lakukan request melalui util Api Client
        Response actualRes = client.get(url);
        // Perform assertions
        try {
            // membandingkan status code, jika tidak sesuai tambahkan pada list error
            assertEquals(expectedStatus, actualRes.statusCode());
        } catch (AssertionError e) {
            // Append error message to customAssertionError
            errorList.appendMessage("Expected status: " + expectedStatus + ", but was: " + actualRes.statusCode());
        }
        //parse dari json ke objek User
        User actual = actualRes.as(User.class);
        List<String> differences = expected.compare(actual);
        errorList.appendMessages(differences);

        // If any assertions failed, throw CustomAssertionError
        if (errorList.getMessage().length() > 0) {
            throw errorList;
        }
    }
```
# How to generate test report
+ gunakan ```./gradlew test``` untuk mengeksekusi semua pengujian
+ gunakan ```./gradlew test --tests 'package.subpackage.*.*'``` untuk mengeksekusi pada satu package tertentu.
+ misal ```./gradlew test --tests 'ppl.a2.apitesting.testcase.update.*.*'```
+ gunakan `./gradlew test --tests NamaClass.NamaMethod` untuk mengeksekusi spesifik method
+ misal `"./gradlew test --tests TC5.testUpdateFirstName"`

## Hasil report

laporan pengujian yang digenerate oleh gradle ada pada `app/build/reports/index.html`
