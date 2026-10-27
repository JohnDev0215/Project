package com.amazon.music.config;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public interface Attributes {
    JSONObject getDefaultConfigJSON();

    String getLocaleCountryCode();

    String getLocaleLanguage();

    String getPlatformName();

    String getVersion();

    int getVersionCode();

    String getVersionShort();
}
