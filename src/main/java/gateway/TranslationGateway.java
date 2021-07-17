package gateway;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import pojo.PayLoad;
import pojo.ToTranslateData;
import pojo.TokenResponse;
import pojo.TranslationResponse;

import java.io.FileReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class TranslationGateway {
    public TranslationResponse Translate(String text, String sourceLanguage, String targetLanguage) throws Exception {
        String token = getIamToken();
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setAccept(ContentType.JSON)
                .build();

        ToTranslateData toTranslate = new ToTranslateData();
        toTranslate.sourceLanguageCode = sourceLanguage;
        toTranslate.targetLanguageCode = targetLanguage;
        toTranslate.texts.add(text);
        toTranslate.format = "PLAIN_TEXT";
        TranslationResponse translateResponse = given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(toTranslate)
                .post(Endpoints.TRANSLATE)
                .then()
                .statusCode(200)
                .extract()
                .as(TranslationResponse.class);

        return translateResponse;
    }

    //код для получения JWT и обмен на IAM токен взят из официальной документации Яндекса
    private String getIamToken() throws Exception {
        PemObject privateKeyPem;
        try (PemReader reader = new PemReader(new FileReader("src/main/resources/private.pem"))) {
            privateKeyPem = reader.readPemObject();
        }

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyPem.getContent()));

        String serviceAccountId = "aje9nuvqhgkjkrdkfr2m";
        String keyId = "ajeikrjt9bn7r9icrote";

        Instant now = Instant.now();

        String encodedToken = Jwts.builder()
                .setHeaderParam("kid", keyId)
                .setIssuer(serviceAccountId)
                .setAudience("https://iam.api.cloud.yandex.net/iam/v1/tokens")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(360)))
                .signWith(SignatureAlgorithm.PS256, privateKey)
                .compact();

        PayLoad payload = new PayLoad();
        payload.jwt = encodedToken;

        TokenResponse tokenResponse = (TokenResponse) given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post(Endpoints.IM_TOKEN_CREATE)
                .then()
                .statusCode(200)
                .extract()
                .as(TokenResponse.class);

        return tokenResponse.iamToken;
    }
}