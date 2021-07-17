package service;

import gateway.TranslationGateway;
import pojo.TranslationResponse;

public class TranslationService {

    public String Translate(String Text, String SourceLanguage, String TargetLanguage) throws Exception {
        TranslationGateway gateway = new TranslationGateway();
        TranslationResponse response = gateway.Translate(Text, SourceLanguage, TargetLanguage);

        return response.translations.get(0).text;
    }
}
