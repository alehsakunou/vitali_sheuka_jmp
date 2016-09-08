package cinema;

/**
 * Написать консольное или веб  приложение, которое будет бронировать билет на кинофильм.
 * Created by Vitali_Sheuka on 9/7/2016.
 */
import com.google.common.io.Files;
import org.apache.commons.compress.utils.Charsets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class Application {

    private static List<String> films;

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = Application.class.getClassLoader();
        File file =new File(classLoader.getResource("films.dat").getFile());
        films = Files.readLines(file, Charsets.UTF_8);
        SpringApplication.run(Application.class, args);
    }

    public static String getFilm(int i) {
        return films.get(i);
    }
}
