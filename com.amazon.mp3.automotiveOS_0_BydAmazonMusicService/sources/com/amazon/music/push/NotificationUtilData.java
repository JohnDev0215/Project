package com.amazon.music.push;

import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationUtilData {
    private final Map decodedDeeplinkMap;
    private final String deviceLocale;
    private final String jsonFileString;
    private final String notificationType;

    NotificationUtilData(Map<String, String> map, String str, String str2, String str3) {
        this.decodedDeeplinkMap = map;
        this.notificationType = str;
        this.jsonFileString = str2;
        this.deviceLocale = str3;
    }

    public Map<String, String> getDecodedDeeplinkMap() {
        return this.decodedDeeplinkMap;
    }

    public String getNotificationType() {
        return this.notificationType;
    }

    public String getJsonFileString() {
        return this.jsonFileString;
    }

    public String getDeviceLocale() {
        return this.deviceLocale;
    }
}
