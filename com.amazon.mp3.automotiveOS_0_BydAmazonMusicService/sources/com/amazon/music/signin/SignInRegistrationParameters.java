package com.amazon.music.signin;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class SignInRegistrationParameters {
    private static final Locale SPAIN = new Locale("es", "ES");
    private static final Locale UNITED_KINGDOM_GB = new Locale("gb", "GB");
    private static final Locale GERMANY_AT = new Locale("at", "AT");
    private static final Locale GERMANY_CH = new Locale("ch", "CH");
    private static final Locale MEXICO = new Locale("es", "MX");
    private static final Locale INDIA = new Locale("en", "IN");
    private static final Locale AUSTRALIA = new Locale("en", "AU");
    protected static final RegistrationParameters NA_REGISTRATION_PARAMETERS = new RegistrationParameters("com", "amzn_tiburon_na");
    private static final RegistrationParameters UK_REGISTRATION_PARAMETERS = new RegistrationParameters("co.uk", "amzn_tiburon_uk");
    private static final RegistrationParameters FR_REGISTRATION_PARAMETERS = new RegistrationParameters("fr", "amzn_tiburon_fr");
    private static final RegistrationParameters DE_REGISTRATION_PARAMETERS = new RegistrationParameters("de", "amzn_tiburon_de");
    private static final RegistrationParameters IT_REGISTRATION_PARAMETERS = new RegistrationParameters("it", "amzn_tiburon_it");
    protected static final RegistrationParameters ES_REGISTRATION_PARAMETERS = new RegistrationParameters("es", "amzn_tiburon_es");
    private static final RegistrationParameters JP_REGISTRATION_PARAMETERS = new RegistrationParameters("co.jp", "amzn_tiburon_jp");
    private static final RegistrationParameters CN_REGISTRATION_PARAMETERS = new RegistrationParameters("cn", "amzn_tiburon_cn");
    private static final RegistrationParameters CA_REGISTRATION_PARAMETERS = new RegistrationParameters("com", "amzn_tiburon_na");
    private static final RegistrationParameters IN_REGISTRATION_PARAMETERS = new RegistrationParameters("com", "amzn_tiburon_na");
    private static final RegistrationParameters MX_REGISTRATION_PARAMETERS = new RegistrationParameters("com", "amzn_tiburon_na");
    private static final RegistrationParameters AU_REGISTRATION_PARAMETERS = new RegistrationParameters("com", "amzn_tiburon_na");
    private static final Map<String, RegistrationParameters> COUNTRY_TO_REGISTRATION_PARAMETERS = new HashMap<String, RegistrationParameters>() { // from class: com.amazon.music.signin.SignInRegistrationParameters.1
        {
            Locale locale = Locale.US;
            put(locale.getCountry().toLowerCase(locale), SignInRegistrationParameters.NA_REGISTRATION_PARAMETERS);
            put(Locale.UK.getCountry().toLowerCase(locale), SignInRegistrationParameters.UK_REGISTRATION_PARAMETERS);
            put(SignInRegistrationParameters.UNITED_KINGDOM_GB.getCountry().toLowerCase(locale), SignInRegistrationParameters.UK_REGISTRATION_PARAMETERS);
            put(Locale.FRANCE.getCountry().toLowerCase(locale), SignInRegistrationParameters.FR_REGISTRATION_PARAMETERS);
            put(Locale.GERMANY.getCountry().toLowerCase(locale), SignInRegistrationParameters.DE_REGISTRATION_PARAMETERS);
            put(SignInRegistrationParameters.GERMANY_AT.getCountry().toLowerCase(locale), SignInRegistrationParameters.DE_REGISTRATION_PARAMETERS);
            put(SignInRegistrationParameters.GERMANY_CH.getCountry().toLowerCase(locale), SignInRegistrationParameters.DE_REGISTRATION_PARAMETERS);
            put(Locale.ITALY.getCountry().toLowerCase(locale), SignInRegistrationParameters.IT_REGISTRATION_PARAMETERS);
            put(SignInRegistrationParameters.SPAIN.getCountry().toLowerCase(locale), SignInRegistrationParameters.ES_REGISTRATION_PARAMETERS);
            put(Locale.JAPAN.getCountry().toLowerCase(locale), SignInRegistrationParameters.JP_REGISTRATION_PARAMETERS);
            put(Locale.CHINA.getCountry().toLowerCase(locale), SignInRegistrationParameters.CN_REGISTRATION_PARAMETERS);
            put(Locale.CANADA.getCountry().toLowerCase(locale), SignInRegistrationParameters.CA_REGISTRATION_PARAMETERS);
            put(SignInRegistrationParameters.INDIA.getCountry().toLowerCase(locale), SignInRegistrationParameters.IN_REGISTRATION_PARAMETERS);
            put(SignInRegistrationParameters.MEXICO.getCountry().toLowerCase(locale), SignInRegistrationParameters.MX_REGISTRATION_PARAMETERS);
            put(SignInRegistrationParameters.AUSTRALIA.getCountry().toLowerCase(locale), SignInRegistrationParameters.AU_REGISTRATION_PARAMETERS);
        }
    };
    private static final Map<String, RegistrationParameters> LANGUAGE_TO_REGISTRATION_PARAMETERS = new HashMap<String, RegistrationParameters>() { // from class: com.amazon.music.signin.SignInRegistrationParameters.2
        {
            put(Locale.ENGLISH.getLanguage(), SignInRegistrationParameters.NA_REGISTRATION_PARAMETERS);
            put(Locale.FRENCH.getLanguage(), SignInRegistrationParameters.FR_REGISTRATION_PARAMETERS);
            put(Locale.GERMAN.getLanguage(), SignInRegistrationParameters.DE_REGISTRATION_PARAMETERS);
            put(Locale.ITALIAN.getLanguage(), SignInRegistrationParameters.IT_REGISTRATION_PARAMETERS);
            put(SignInRegistrationParameters.SPAIN.getLanguage(), SignInRegistrationParameters.ES_REGISTRATION_PARAMETERS);
            put(Locale.JAPANESE.getLanguage(), SignInRegistrationParameters.JP_REGISTRATION_PARAMETERS);
            put(Locale.CHINESE.getLanguage(), SignInRegistrationParameters.CN_REGISTRATION_PARAMETERS);
        }
    };

    SignInRegistrationParameters() {
    }

    protected void setUpRegistrationParameters(@NonNull Bundle bundle, @Nullable Locale locale) {
        Validate.notNull(bundle, "bundle cannot be null", new Object[0]);
        RegistrationParameters registrationParametersForLocale = getRegistrationParametersForLocale(locale);
        bundle.putString("com.amazon.identity.ap.domain", registrationParametersForLocale.getEndpoint());
        bundle.putString("com.amazon.identity.ap.assoc_handle", registrationParametersForLocale.getAssociationHandle());
        bundle.putInt(MAPAccountManager.AuthPortalActivityUIOptions.KEY_SPLASH_SCREEN_RESOURCE, R$drawable.logo_sign_in);
        bundle.putString(MAPAccountManager.AuthPortalActivityUIOptions.KEY_SPLASH_SCREEN_SCALE_TYPE, ImageView.ScaleType.CENTER.toString());
        bundle.putBoolean(MAPAccountManager.AuthPortalActivityUIOptions.KEY_IS_FULLSCREEN, true);
        bundle.putBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary", true);
    }

    protected static void setUpRegistrationParametersForNativeSignIn(@NonNull Bundle bundle, @Nullable Locale locale) {
        Validate.notNull(bundle, "bundle cannot be null", new Object[0]);
        bundle.putString("com.amazon.identity.ap.domain", getRegistrationParametersForLocale(locale).getEndpoint());
    }

    protected static void setUpRegistrationParametersForLinkCodeRequest(@NonNull Bundle bundle, @Nullable Locale locale) {
        Validate.notNull(bundle, "bundle cannot be null", new Object[0]);
        bundle.putString(MAPAccountManager.KEY_LINK_CODE_DOMAIN, getRegistrationParametersForLocale(locale).getEndpoint());
    }

    protected static String getRegistrationEndpoint(@Nullable Locale locale) {
        return getRegistrationParametersForLocale(locale).getFullUrl();
    }

    protected static RegistrationParameters getRegistrationParametersForLocale(@Nullable Locale locale) {
        if (locale == null) {
            return LANGUAGE_TO_REGISTRATION_PARAMETERS.get("en");
        }
        String country = locale.getCountry();
        Locale locale2 = Locale.US;
        RegistrationParameters registrationParameters = COUNTRY_TO_REGISTRATION_PARAMETERS.get(country.toLowerCase(locale2));
        if (registrationParameters != null) {
            return registrationParameters;
        }
        String lowerCase = locale.getLanguage().toLowerCase(locale2);
        Map<String, RegistrationParameters> map = LANGUAGE_TO_REGISTRATION_PARAMETERS;
        RegistrationParameters registrationParameters2 = map.get(lowerCase);
        return registrationParameters2 != null ? registrationParameters2 : map.get("en");
    }

    protected static class RegistrationParameters {
        private String associationHandle;
        private String endpoint;
        private String fullUrl;

        RegistrationParameters(String str, String str2) {
            this.endpoint = ".amazon." + str;
            this.associationHandle = str2;
            this.fullUrl = "www" + this.endpoint;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getEndpoint() {
            return this.endpoint;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getFullUrl() {
            return this.fullUrl;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getAssociationHandle() {
            return this.associationHandle;
        }
    }
}
