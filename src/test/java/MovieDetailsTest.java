import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MovieDetailsTest extends BaseTest {

    @Test
    public void testMovieDetailsStatus200() {
        int movieId = 550; // Contoh ID film

        given()
                .queryParam("api_key", API_KEY)
                .when()
                .get("/movie/" + movieId)
                .then()
                .statusCode(200);
    }

    @Test
    public void testMovieDetailsContainsTitle() {
        int movieId = 550;

        given()
                .queryParam("api_key", API_KEY)
                .when()
                .get("/movie/" + movieId)
                .then()
                .body("title", notNullValue());
    }

    @Test
    public void testMovieDetailsInvalidId() {
        int invalidMovieId = 9999999; // ID tidak valid

        given()
                .queryParam("api_key", API_KEY)
                .when()
                .get("/movie/" + invalidMovieId)
                .then()
                .statusCode(404)
                .body("status_message", containsString("The resource you requested could not be found."));
    }
}
