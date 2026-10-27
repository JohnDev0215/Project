package com.amazon.music.voice;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.superbowl.plugins.system.BaseSystemRepository;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public final class MusicPersistentRepository extends BaseSystemRepository {
    private final SharedPreferences preferences;

    public MusicPersistentRepository(SharedPreferences sharedPreferences) {
        this.preferences = sharedPreferences;
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SystemRepository
    public String getEndpoint() {
        return this.preferences.getString("voice.system.endpoint", "https://avs-alexa-na.amazon.com");
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SystemRepository
    public void setEndpoint(String str) {
        Preconditions.notNull(str, "endpoint == null");
        if (TextUtils.equals(getEndpoint(), str)) {
            return;
        }
        this.preferences.edit().putString("voice.system.endpoint", str).apply();
        notifyEndpointChanged();
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SystemRepository
    public Locale getLocale() {
        Locale locale = Locale.US;
        return new Locale(this.preferences.getString("voice.system.language", locale.getLanguage()), this.preferences.getString("voice.system.country", locale.getCountry()));
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SystemRepository
    public void setLocale(Locale locale) {
        Preconditions.notNull(locale, "locale == null");
        if (getLocale().equals(locale)) {
            return;
        }
        this.preferences.edit().putString("voice.system.language", locale.getLanguage()).putString("voice.system.country", locale.getCountry()).apply();
        notifyLocaleChanged();
    }
}
