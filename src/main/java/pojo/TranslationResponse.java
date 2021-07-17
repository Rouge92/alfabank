package pojo;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "translations"
})
public class TranslationResponse {
    @JsonProperty("translations")
    public List<Translation> translations = new ArrayList<Translation>();

    public List<Translation> getTranslations() {
        return translations;
    }
}