package utils;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.ByteArrayInputStream;

public class AllureRestFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {

        // Log Request
        String requestLog = "METHOD: " + requestSpec.getMethod() + "\n" +
                "URL: " + requestSpec.getURI() + "\n" +
                "HEADERS: " + requestSpec.getHeaders() + "\n" +
                "BODY: " + requestSpec.getBody();

        Allure.addAttachment("API Request", new ByteArrayInputStream(requestLog.getBytes()));

        // Execute request
        Response response = ctx.next(requestSpec, responseSpec);

        // Log Response
        String responseLog = "STATUS: " + response.getStatusCode() + "\n" +
                "BODY: " + response.asPrettyString();

        Allure.addAttachment("API Response", new ByteArrayInputStream(responseLog.getBytes()));

        return response;
    }
}