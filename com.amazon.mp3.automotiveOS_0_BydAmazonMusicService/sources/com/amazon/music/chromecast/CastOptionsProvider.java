package com.amazon.music.chromecast;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.OptionsProvider;
import com.google.android.gms.cast.framework.SessionProvider;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class CastOptionsProvider implements OptionsProvider {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) CastOptionsProvider.class);
    public static final String RECEIVER_APPLICATION_ID = "6119F6EA";
    private static final String RECEIVER_APP_ID_OVERRIDE_KEY = "chromecast_receiver_app_id_override";

    @Override // com.google.android.gms.cast.framework.OptionsProvider
    public List<SessionProvider> getAdditionalSessionProviders(Context context) {
        return null;
    }

    @Override // com.google.android.gms.cast.framework.OptionsProvider
    public CastOptions getCastOptions(Context context) {
        return new CastOptions.Builder().setReceiverApplicationId(getReceiverApplicationId(context)).setCastMediaOptions(new CastMediaOptions.Builder().setNotificationOptions(null).build()).build();
    }

    private String getReceiverApplicationId(Context context) {
        try {
            if (2 == (context.getApplicationInfo().flags & 2)) {
                String string = getPrefs(context).getString(RECEIVER_APP_ID_OVERRIDE_KEY, null);
                if (string == null) {
                    return RECEIVER_APPLICATION_ID;
                }
                String strTrim = string.trim();
                if (TextUtils.isEmpty(strTrim)) {
                    return RECEIVER_APPLICATION_ID;
                }
                LOG.debug("Chromecast receiver app ID developer override: " + strTrim);
                return strTrim;
            }
            LOG.debug("Chromecast receiver app ID developer override not available");
            return RECEIVER_APPLICATION_ID;
        } catch (RuntimeException e) {
            LOG.error("Encountered error while checking for a Chromecast receiver app ID developer override: ", (Throwable) e);
            return RECEIVER_APPLICATION_ID;
        }
    }

    private static SharedPreferences getPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }
}
