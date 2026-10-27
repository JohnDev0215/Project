package com.amazon.mp3.net.stratus.preferences;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.net.stratus.StratusRequest;
import com.amazon.mp3.platform.providers.AppConfigProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: PrivacyCompliancePreferencesAPIProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0016\u0010\n\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u000bJ\b\u0010\f\u001a\u00020\u0004H\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\"\u0010\u0012\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/net/stratus/preferences/PrivacyCompliancePreferencesAPIProvider;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "execute", "Lorg/json/JSONObject;", "retrievalKeys", "Lorg/json/JSONArray;", "getCookieConsentStatusAndLegaleseUrl", "Lkotlin/Pair;", "getLocale", "logException", "", "message", "e", "Ljava/lang/Exception;", "parseCookiePreferencesResponse", "response", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PrivacyCompliancePreferencesAPIProvider {
    private final String TAG = PrivacyCompliancePreferencesAPIProvider.class.getSimpleName();

    public final Pair<String, String> getCookieConsentStatusAndLegaleseUrl() throws Exception {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("cookieConsent");
        jSONArray.put("cookieConsentLegaleseUrl");
        return parseCookiePreferencesResponse(execute(jSONArray));
    }

    private final Pair<String, String> parseCookiePreferencesResponse(JSONObject response) {
        Pair<String, String> pair;
        if (response == null) {
            pair = null;
        } else {
            try {
                JSONObject jSONObject = response.getJSONObject("preferencesByNamespace").getJSONObject("cookiePreferences");
                pair = new Pair<>(new JSONObject(jSONObject.getString("cookieConsent")).getString("interpretedStringState"), jSONObject.getString("cookieConsentLegaleseUrl"));
            } catch (JSONException e) {
                logException("Error parsing cookie consent response", e);
                return new Pair<>(null, null);
            }
        }
        return pair == null ? new Pair<>(null, null) : pair;
    }

    private final JSONObject execute(JSONArray retrievalKeys) throws Exception {
        StratusRequest stratusRequest = StratusRequest.RetrievePrivacyCompliancePreferences;
        JSONObject jSONObject = new JSONObject();
        Context context = AmazonApplication.getContext();
        try {
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
            jSONObject.put(Splash.PARAMS_DEVICE_TYPE, accountCredentialStorage.getDeviceType());
            jSONObject.put(Splash.PARAMS_DEVICE_ID, accountCredentialStorage.getDeviceId());
            jSONObject.put("marketplaceId", AccountDetailUtil.get(context).getHomeMarketPlaceId());
            String customerId = AccountManagerSingleton.get().getUser().getCustomerId();
            Intrinsics.checkNotNullExpressionValue(customerId, "get().user.customerId");
            jSONObject.put("customerId", customerId);
            String locale = getLocale();
            AppConfigProvider.Companion companion = AppConfigProvider.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(context, "context");
            jSONObject.put("tld", companion.getInstance(context).getConfiguration().string("cookie_consent_top_level_domains"));
            jSONObject.put(Splash.PARAMS_LOCALE, locale);
            jSONObject.put("namespace", "cookiePreferences");
            jSONObject.put("keys", retrievalKeys);
            try {
                return stratusRequest.execute(jSONObject);
            } catch (Exception e) {
                logException("Error obtaining StratusRequest response", e);
                throw e;
            }
        } catch (DataNotReadyException e2) {
            logException("Error reading customer", e2);
            return null;
        } catch (MusicAccountNotCreatedException e3) {
            logException("Error finding music account", e3);
            return null;
        } catch (JSONException e4) {
            logException("Error creating request parameters", e4);
            return null;
        }
    }

    private final void logException(String message, Exception e) {
        Log.error(this.TAG, message, e);
    }

    private final String getLocale() {
        Locale locale = AmazonApplication.getLocale();
        StringBuilder sb = new StringBuilder();
        sb.append((Object) locale.getLanguage());
        sb.append('_');
        sb.append((Object) locale.getCountry());
        return sb.toString();
    }
}
