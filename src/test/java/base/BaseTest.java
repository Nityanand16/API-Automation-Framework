package base;

import config.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = ConfigReader.get("base.url");

        log.info(" API Test Execution Started");
        log.info("Base URI: " + RestAssured.baseURI);
    }

    @AfterClass
    public void tearDown() {
        log.info(" API Test Execution Completed");
    }
}