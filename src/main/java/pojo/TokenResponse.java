package pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


    @JsonPropertyOrder({
            "iamToken",
            "expiresAt"
    })

    public class TokenResponse {
        @JsonProperty("iamToken")
        public String iamToken;
        @JsonProperty("expiresAt")
        public String expiresAt;

    }

