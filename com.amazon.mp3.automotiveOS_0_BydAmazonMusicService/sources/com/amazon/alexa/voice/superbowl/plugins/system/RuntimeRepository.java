package com.amazon.alexa.voice.superbowl.plugins.system;

import android.text.TextUtils;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class RuntimeRepository extends BaseSystemRepository {
    private String endpoint;
    private Locale locale;

    public RuntimeRepository() {
        this("https://avs-alexa-na.amazon.com", Locale.getDefault());
    }

    public RuntimeRepository(String str, Locale locale) {
        Preconditions.notNull(str, "endpoint == null");
        Preconditions.notNull(locale, "locale == null");
        this.endpoint = str;
        this.locale = locale;
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SystemRepository
    public String getEndpoint() {
        return this.endpoint;
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SystemRepository
    public void setEndpoint(String str) {
        Preconditions.notNull(str, "endpoint == null");
        if (TextUtils.equals(this.endpoint, str)) {
            return;
        }
        this.endpoint = str;
        notifyEndpointChanged();
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SystemRepository
    public Locale getLocale() {
        return this.locale;
    }

    @Override // com.amazon.alexa.voice.superbowl.plugins.SystemRepository
    public void setLocale(Locale locale) {
        Preconditions.notNull(locale, "locale == null");
        Locale localeFrom = SuperbowlLocale.from(locale);
        if (getLocale().equals(localeFrom)) {
            return;
        }
        this.locale = localeFrom;
        notifyLocaleChanged();
    }
}
