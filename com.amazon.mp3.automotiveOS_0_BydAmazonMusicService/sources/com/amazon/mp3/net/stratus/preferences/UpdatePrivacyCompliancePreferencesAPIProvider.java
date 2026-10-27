package com.amazon.mp3.net.stratus.preferences;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.net.stratus.StratusRequest;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: UpdatePrivacyCompliancePreferencesAPIProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002J\b\u0010\t\u001a\u00020\u0004H\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\u0004R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/net/stratus/preferences/UpdatePrivacyCompliancePreferencesAPIProvider;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "execute", "Lorg/json/JSONObject;", "preferencesJSON", "getLocale", "logException", "", "message", "e", "Ljava/lang/Exception;", "updateCookieConsentPreferences", "consent", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UpdatePrivacyCompliancePreferencesAPIProvider {
    private final String TAG = PrivacyCompliancePreferencesAPIProvider.class.getSimpleName();

    public final JSONObject updateCookieConsentPreferences(String consent) throws Exception {
        Intrinsics.checkNotNullParameter(consent, "consent");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cookieConsent", consent);
        return execute(jSONObject);
    }

    private final JSONObject execute(JSONObject preferencesJSON) throws Exception {
        StratusRequest stratusRequest = StratusRequest.UpdatePrivacyCompliancePreferences;
        JSONObject jSONObject = new JSONObject();
        Context context = AmazonApplication.getContext();
        try {
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
            jSONObject.put(Splash.PARAMS_DEVICE_TYPE, accountCredentialStorage.getDeviceType());
            jSONObject.put(Splash.PARAMS_DEVICE_ID, accountCredentialStorage.getDeviceId());
            jSONObject.put("marketplaceId", AccountDetailUtil.get(context).getHomeMarketPlaceId());
            jSONObject.put(Splash.PARAMS_LOCALE, getLocale());
            String customerId = AccountManagerSingleton.get().getUser().getCustomerId();
            Intrinsics.checkNotNullExpressionValue(customerId, "get().user.customerId");
            jSONObject.put("customerId", customerId);
            jSONObject.put("preferences", preferencesJSON);
            jSONObject.put("namespace", "cookiePreferences");
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
