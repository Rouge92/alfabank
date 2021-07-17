
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.TranslationService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslationTest {

    @Test()
    @DisplayName("Hello World translation test")
    void helloWorldTest() throws Exception {
        TranslationService translationService = new TranslationService();
        assertEquals("Всем привет!", translationService.Translate("Hello World!", "en", "ru"));
    }
}
