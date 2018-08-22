package todolist;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by hello on 21/08/2018.
 */
public class EncoderTest {

    @Test
    public void test() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);

        System.out.println("encoded passwords...");
        System.out.println(encoder.encode("jo"));
        System.out.println(encoder.encode("ma"));
        System.out.println(encoder.encode("letmein"));
    }
}
