package com.amazon.alexa.voice.superbowl.plugins;

import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public interface SystemRepository {

    public interface OnChangedListener {
        void onEndpointChanged(SystemRepository systemRepository);

        void onLocaleChanged(SystemRepository systemRepository);
    }

    void addOnChangedListener(OnChangedListener onChangedListener);

    String getEndpoint();

    Locale getLocale();

    void setEndpoint(String str);

    void setLocale(Locale locale);
}
