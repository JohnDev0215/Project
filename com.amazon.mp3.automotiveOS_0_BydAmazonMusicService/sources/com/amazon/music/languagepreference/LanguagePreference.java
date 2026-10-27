package com.amazon.music.languagepreference;

import android.content.Context;
import com.amazon.music.featureflag.FeatureFlagProvider;
import com.amazon.music.featureflag.FeatureFlagUnavailableException;
import com.amazon.stratus.UpdatePreferencesResponse;
import com.android.volley.VolleyError;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class LanguagePreference {
    private static final Logger LOG = LoggerFactory.getLogger(LanguagePreference.class.getSimpleName());
    private Configuration configuration;
    private DefaultLanguagePreferenceService service;

    public LanguagePreference(Configuration configuration) {
        this.configuration = configuration;
        this.service = new DefaultLanguagePreferenceService(configuration);
    }

    private List<String> getPreferredLanguages() throws LanguagePreferenceException {
        try {
            Map<String, String> preferences = this.service.getPreference(this.service.buildGetPreferenceRequest()).getPreferences();
            if (preferences.containsKey("contentLanguagePreferences")) {
                return Arrays.asList(preferences.get("contentLanguagePreferences").split(","));
            }
            return null;
        } catch (VolleyError e) {
            LOG.error("Error getting preferred languages", (Throwable) e);
            throw new LanguagePreferenceException(e);
        }
    }

    public List<Language> getAvailableContentLanguage() throws LanguagePreferenceException {
        try {
            List<Language> listConvert = AvailableContentLanguageToLanguageConverter.convert(this.service.getAvailableContentLanguage(this.service.buildGetAvailableContentLanguagesRequest()).getContentLanguageMap());
            List<String> preferredLanguages = getPreferredLanguages();
            if (preferredLanguages != null && !preferredLanguages.isEmpty()) {
                for (Language language : listConvert) {
                    if (preferredLanguages.contains(language.getLanguageCode())) {
                        language.setUserPreferred(true);
                    }
                }
            }
            return listConvert;
        } catch (VolleyError e) {
            LOG.error("Error getting available languages", (Throwable) e);
            throw new LanguagePreferenceException(e);
        }
    }

    public boolean updatePreferePreference(List<Language> list) throws LanguagePreferenceException {
        try {
            UpdatePreferencesResponse updatePreferencesResponseUpdatePreference = this.service.updatePreference(this.service.buildUpdatePreferencesRequest(list));
            if (updatePreferencesResponseUpdatePreference == null || !updatePreferencesResponseUpdatePreference.getPreferences().containsKey("contentLanguagePreferences")) {
                return false;
            }
            LanguagePreferenceCache.write(this.configuration.getContext());
            return true;
        } catch (VolleyError e) {
            LOG.error("Error updating preferences", (Throwable) e);
            throw new LanguagePreferenceException(e);
        }
    }

    public static void onUserSignOut(Context context) {
        LanguagePreferenceCache.clear(context);
    }

    public boolean isAvailableInSettings(FeatureFlagProvider featureFlagProvider) {
        return isFeatureSupported(featureFlagProvider);
    }

    public boolean isAvailableOnAppOne(FeatureFlagProvider featureFlagProvider) throws LanguagePreferenceException {
        return isFeatureSupported(featureFlagProvider) && !LanguagePreferenceCache.sharedPreferenceShow(this.configuration.getContext()) && getPreferredLanguages() == null;
    }

    private boolean isFeatureSupported(FeatureFlagProvider featureFlagProvider) {
        try {
            return featureFlagProvider.supportsLanguagePreferences();
        } catch (FeatureFlagUnavailableException e) {
            LOG.error("Error getting value from feature flag, defaulting to false", (Throwable) e);
            return false;
        }
    }
}
