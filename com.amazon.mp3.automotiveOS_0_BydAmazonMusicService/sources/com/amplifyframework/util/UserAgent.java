package com.amplifyframework.util;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.logging.Logger;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class UserAgent {
    private static final int SIZE_LIMIT = 254;
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:core");
    private static String instance = null;

    private UserAgent() {
    }

    public static synchronized void configure(@NonNull Map<Platform, String> map) throws AmplifyException {
        if (instance != null) {
            throw new AmplifyException("User-Agent was already configured successfully.", "User-Agent is configured internally during Amplify configuration. This method should not be called externally.");
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Platform, String> entry : map.entrySet()) {
            sb.append(String.format("%s/%s ", entry.getKey().getLibraryName(), entry.getValue()));
        }
        sb.append(forAndroid());
        if (sb.length() > SIZE_LIMIT) {
            throw new AmplifyException("User-Agent exceeds the size limit of VARCHAR(254).", AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
        }
        instance = sb.toString();
    }

    @VisibleForTesting
    public static synchronized void reset() {
        instance = null;
    }

    @NonNull
    @SuppressLint({"SyntheticAccessor"})
    public static String string() {
        String str = instance;
        if (str != null) {
            return str;
        }
        LOG.debug("User-Agent is not yet configured. Returning default Android user-agent.");
        return forAndroid();
    }

    private static String forAndroid() {
        return new Builder().libraryName(Platform.ANDROID.getLibraryName()).libraryVersion("1.37.11").systemName("Android").systemVersion(Build.VERSION.RELEASE).deviceManufacturer(Build.MANUFACTURER).deviceName(Build.MODEL).userLanguage(System.getProperty("user.language")).userRegion(System.getProperty("user.region")).toString();
    }

    public static boolean isFlutter() {
        return string().contains(Platform.FLUTTER.libraryName);
    }

    public enum Platform {
        ANDROID("amplify-android"),
        FLUTTER("amplify-flutter");

        private final String libraryName;

        Platform(String str) {
            this.libraryName = str;
        }

        public String getLibraryName() {
            return this.libraryName;
        }
    }

    private static final class Builder {
        private String deviceManufacturer;
        private String deviceName;
        private String libraryName;
        private String libraryVersion;
        private String systemName;
        private String systemVersion;
        private String userLanguage;
        private String userRegion;

        @NonNull
        private static String sanitize(@Nullable String str) {
            return str != null ? str : "UNKNOWN";
        }

        private Builder() {
        }

        Builder libraryName(String str) {
            this.libraryName = sanitize(str);
            return this;
        }

        Builder libraryVersion(String str) {
            this.libraryVersion = sanitize(str);
            return this;
        }

        Builder systemName(String str) {
            this.systemName = sanitize(str);
            return this;
        }

        Builder systemVersion(String str) {
            this.systemVersion = sanitize(str);
            return this;
        }

        Builder deviceManufacturer(String str) {
            this.deviceManufacturer = sanitize(str);
            return this;
        }

        Builder deviceName(String str) {
            this.deviceName = sanitize(str);
            return this;
        }

        Builder userLanguage(String str) {
            this.userLanguage = sanitize(str);
            return this;
        }

        Builder userRegion(String str) {
            this.userRegion = sanitize(str);
            return this;
        }

        @NonNull
        public String toString() {
            return String.format("%s/%s (%s %s; %s %s; %s_%s)", this.libraryName, this.libraryVersion, this.systemName, this.systemVersion, this.deviceManufacturer, this.deviceName, this.userLanguage, this.userRegion);
        }
    }
}
