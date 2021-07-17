package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "text",
        "detectedLanguageCode"
})
public class Translation {

    @JsonProperty("text")
    public String text;
    @JsonProperty("detectedLanguageCode")
    public String detectedLanguageCode;

}