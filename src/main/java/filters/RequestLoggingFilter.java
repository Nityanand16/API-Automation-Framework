package filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RequestLoggingFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {
        System.out.println("=== REQUEST ===");
        System.out.println(requestSpec.getMethod() + " " + requestSpec.getURI());
        System.out.println("Headers: " + requestSpec.getHeaders());
        System.out.println("Body: " + requestSpec.getBody());
        return ctx.next(requestSpec, responseSpec);
    }
}