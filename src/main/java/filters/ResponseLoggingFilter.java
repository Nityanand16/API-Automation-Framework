package filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ResponseLoggingFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        System.out.println("=== RESPONSE ===");
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
        return response;
    }
}