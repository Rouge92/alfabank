package pojo;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sourceLanguageCode",
        "targetLanguageCode",
        "format",
        "texts"
})
public class ToTranslateData {

    @JsonProperty("sourceLanguageCode")
    public String sourceLanguageCode;
    @JsonProperty("targetLanguageCode")
    public String targetLanguageCode;
    @JsonProperty("format")
    public String format;
    @JsonProperty("texts")
    public List<String> texts = new ArrayList<String>();

}