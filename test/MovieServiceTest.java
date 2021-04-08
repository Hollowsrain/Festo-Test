import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;


public class MovieServiceTest {

    private static RestTemplate restTemplate = null;

    @BeforeTestClass
    public static void initialize(){
        restTemplate = new RestTemplateBuilder().build();
    }

    @Test
    public void isMoviesSorted() {

    }
}
