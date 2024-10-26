import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NowPlayingTest extends BaseTest {

    @Test
    public void testNowPlayingStatus200() {
        given()
                .queryParam("api_key", API_KEY)
                .when()
                .get("/movie/now_playing")
                .then()
                .statusCode(200);
    }

    @Test
    public void testNowPlayingContainsMovies() {
        given()
                .queryParam("api_key", API_KEY)
                .when()
                .get("/movie/now_playing")
                .then()
                .body("results", not(empty()));
    }

    @Test
    public void testNowPlayingMoviesHaveTitle() {
        given()
                .queryParam("api_key", API_KEY)
                .when()
                .get("/movie/now_playing")
                .then()
                .body("results.title", everyItem(notNullValue()));
    }
}
