package com.amazon.music.languagepreference;

import com.amazon.musicensembleservice.GetAvailableContentLanguagesCall;
import com.amazon.musicensembleservice.GetAvailableContentLanguagesRequest;
import com.amazon.musicensembleservice.GetAvailableContentLanguagesResponse;
import com.amazon.stratus.RetrievePreferencesRequest;
import com.amazon.stratus.RetrievePreferencesResponse;
import com.amazon.stratus.UpdatePreferencesRequest;
import com.amazon.stratus.UpdatePreferencesResponse;
import com.amazon.stratus.external.RetrievePreferencesCall;
import com.amazon.stratus.external.UpdatePreferencesCall;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
class DefaultLanguagePreferenceService {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private Configuration configuration;

    public DefaultLanguagePreferenceService(Configuration configuration) {
        this.configuration = configuration;
    }

    RetrievePreferencesResponse getPreference(RetrievePreferencesRequest retrievePreferencesRequest) throws VolleyError {
        return new RetrievePreferencesCall(this.configuration.getStratusUrl(), retrievePreferencesRequest, this.configuration.getRequestInterceptor()).execute(MAPPER);
    }

    UpdatePreferencesResponse updatePreference(UpdatePreferencesRequest updatePreferencesRequest) throws VolleyError {
        return new UpdatePreferencesCall(this.configuration.getStratusUrl(), updatePreferencesRequest, this.configuration.getRequestInterceptor()).execute(MAPPER);
    }

    GetAvailableContentLanguagesResponse getAvailableContentLanguage(GetAvailableContentLanguagesRequest getAvailableContentLanguagesRequest) throws VolleyError {
        return new GetAvailableContentLanguagesCall(this.configuration.getMuseUrl(), getAvailableContentLanguagesRequest, this.configuration.getRequestInterceptor()).execute(MAPPER);
    }

    RetrievePreferencesRequest buildGetPreferenceRequest() {
        RetrievePreferencesRequest retrievePreferencesRequest = new RetrievePreferencesRequest();
        retrievePreferencesRequest.setDeviceType(this.configuration.getDeviceType());
        retrievePreferencesRequest.setDeviceId(this.configuration.getDeviceId());
        retrievePreferencesRequest.setNamespace("global");
        ArrayList arrayList = new ArrayList();
        arrayList.add("contentLanguagePreferences");
        retrievePreferencesRequest.setKeys(arrayList);
        return retrievePreferencesRequest;
    }

    UpdatePreferencesRequest buildUpdatePreferencesRequest(List<Language> list) {
        UpdatePreferencesRequest updatePreferencesRequest = new UpdatePreferencesRequest();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        StringBuilder sb = new StringBuilder();
        for (Language language : list) {
            if (language.getUserPreferred()) {
                if (sb.length() == 0) {
                    sb.append(language.getLanguageCode());
                } else {
                    sb.append("," + language.getLanguageCode());
                }
            }
        }
        linkedHashMap.put("contentLanguagePreferences", sb.toString());
        updatePreferencesRequest.setNamespace("global");
        updatePreferencesRequest.setDeviceId(this.configuration.getDeviceId());
        updatePreferencesRequest.setDeviceType(this.configuration.getDeviceType());
        updatePreferencesRequest.setPreferences(linkedHashMap);
        return updatePreferencesRequest;
    }

    GetAvailableContentLanguagesRequest buildGetAvailableContentLanguagesRequest() {
        GetAvailableContentLanguagesRequest getAvailableContentLanguagesRequest = new GetAvailableContentLanguagesRequest();
        getAvailableContentLanguagesRequest.setMusicTerritory(this.configuration.getMusicTerritory());
        getAvailableContentLanguagesRequest.setDisplayLanguage(this.configuration.getDisplayLanguage());
        getAvailableContentLanguagesRequest.setRequestedContent(this.configuration.getRequestContent());
        getAvailableContentLanguagesRequest.setDeviceId(this.configuration.getDeviceId());
        getAvailableContentLanguagesRequest.setDeviceType(this.configuration.getDeviceType());
        return getAvailableContentLanguagesRequest;
    }
}
