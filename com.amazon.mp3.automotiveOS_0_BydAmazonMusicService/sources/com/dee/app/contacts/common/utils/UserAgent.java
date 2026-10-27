package com.dee.app.contacts.common.utils;

import android.os.Build;
import com.dee.app.contacts.common.exceptions.ClientNotSupportedException;

/* JADX INFO: loaded from: classes5.dex */
public final class UserAgent {
    private String appName;

    public UserAgent(String str) {
        this.appName = str;
    }

    public String get() throws ClientNotSupportedException {
        return getUserAgentHeader() + "1.0-" + formatUserAgentEntry("PLATFORM", "Android") + formatUserAgentEntry("MANUFACTURER", Build.MANUFACTURER) + formatUserAgentEntry("RELEASE", Build.VERSION.RELEASE) + formatUserAgentEntry("BRAND", Build.BRAND) + formatUserAgentEntry("SDK", String.valueOf(Build.VERSION.SDK_INT)) + formatUserAgentEntry("MODEL", Build.MODEL);
    }

    private String formatUserAgentEntry(String str, String str2) {
        return "[" + str + "=" + str2 + "]";
    }

    private String getUserAgentHeader() throws ClientNotSupportedException {
        int i = AnonymousClass1.$SwitchMap$com$dee$app$contacts$common$utils$Client[Client.get(this.appName).ordinal()];
        if (i == 1) {
            return "Amazon Photos/";
        }
        if (i == 2) {
            return "Amazon Music/";
        }
        if (i == 3) {
            return "PitanguiBridge/";
        }
        throw new ClientNotSupportedException(this.appName);
    }

    /* JADX INFO: renamed from: com.dee.app.contacts.common.utils.UserAgent$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$dee$app$contacts$common$utils$Client;

        static {
            int[] iArr = new int[Client.values().length];
            $SwitchMap$com$dee$app$contacts$common$utils$Client = iArr;
            try {
                iArr[Client.PhotosApp.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$dee$app$contacts$common$utils$Client[Client.MusicApp.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$dee$app$contacts$common$utils$Client[Client.AlexaApp.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
