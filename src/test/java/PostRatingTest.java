import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostRatingTest extends BaseTest {
    private String guestSessionId;

    @BeforeClass
    public void createGuestSession() {
        guestSessionId =
                given()
                        .queryParam("api_key", API_KEY)
                        .when()
                        .post("/authentication/guest_session/new")
                        .then()
                        .log().all() // Tambahkan logging
                        .statusCode(200) // Verifikasi status 200 OK
                        .extract()
                        .path("guest_session_id");

        System.out.println("Guest Session ID: " + guestSessionId);
    }

    @Test
    public void testPostRatingSuccess() {
        int movieId = 550; // Contoh ID film
        double rating = 8.5;

        given()
                .queryParam("api_key", API_KEY)
                .queryParam("guest_session_id", guestSessionId)
                .contentType("application/json")
                .body("{\"value\": " + rating + "}")
                .when()
                .post("/movie/" + movieId + "/rating")
                .then()
                .log().all() // Tambahkan logging
                .statusCode(201) // Harus mendapatkan 201 Created
                .body("status_message", equalTo("Success."));
    }
}
