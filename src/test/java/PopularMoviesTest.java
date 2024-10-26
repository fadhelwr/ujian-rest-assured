import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PopularMoviesTest extends BaseTest {

    @Test
    public void testPopularMoviesStatus200() {
        given()
                .queryParam("api_key", API_KEY)
                .when()
                .get("/movie/popular")
                .then()
                .statusCode(200);
    }

    @Test
    public void testPopularMoviesContainsMovies() {
        given()
                .queryParam("api_key", API_KEY)
                .when()
                .get("/movie/popular")
                .then()
                .body("results", not(empty()));
    }

    @Test
    public void testPopularMoviesHaveTitle() {
        given()
                .queryParam("api_key", API_KEY)
                .when()
                .get("/movie/popular")
                .then()
                .body("results.title", everyItem(notNullValue()));
    }
}
