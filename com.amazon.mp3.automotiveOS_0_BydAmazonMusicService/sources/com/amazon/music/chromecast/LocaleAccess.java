package com.amazon.music.chromecast;

import android.content.Context;
import androidx.annotation.Nullable;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class LocaleAccess {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) LocaleAccess.class);
    private final Context applicationContext;

    public LocaleAccess(Context context) {
        this.applicationContext = context.getApplicationContext();
    }

    @Nullable
    public String currentLocaleString() {
        try {
            Locale locale = this.applicationContext.getResources().getConfiguration().getLocales().get(0);
            String language = locale.getLanguage();
            String country = locale.getCountry();
            if ("".equals(country)) {
                return language;
            }
            return language + "-" + country;
        } catch (Exception e) {
            LOG.error("Unable to determine current locale: " + e.getLocalizedMessage());
            return null;
        }
    }
}
