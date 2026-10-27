package com.google.android.gms.cast;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class CastMediaControlIntent {

    @NonNull
    public static final String ACTION_SYNC_STATUS = "com.google.android.gms.cast.ACTION_SYNC_STATUS";

    @NonNull
    public static final String DEFAULT_MEDIA_RECEIVER_APPLICATION_ID = "CC1AD845";
    public static final int ERROR_CODE_REQUEST_FAILED = 1;
    public static final int ERROR_CODE_SESSION_START_FAILED = 2;
    public static final int ERROR_CODE_TEMPORARILY_DISCONNECTED = 3;

    @NonNull
    public static final String EXTRA_CAST_APPLICATION_ID = "com.google.android.gms.cast.EXTRA_CAST_APPLICATION_ID";

    @NonNull
    public static final String EXTRA_CAST_LANGUAGE_CODE = "com.google.android.gms.cast.EXTRA_CAST_LANGUAGE_CODE";

    @NonNull
    public static final String EXTRA_CAST_RELAUNCH_APPLICATION = "com.google.android.gms.cast.EXTRA_CAST_RELAUNCH_APPLICATION";

    @NonNull
    public static final String EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS = "com.google.android.gms.cast.EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS";

    @NonNull
    public static final String EXTRA_CUSTOM_DATA = "com.google.android.gms.cast.EXTRA_CUSTOM_DATA";

    @NonNull
    public static final String EXTRA_DEBUG_LOGGING_ENABLED = "com.google.android.gms.cast.EXTRA_DEBUG_LOGGING_ENABLED";

    @NonNull
    public static final String EXTRA_ERROR_CODE = "com.google.android.gms.cast.EXTRA_ERROR_CODE";

    private CastMediaControlIntent() {
    }

    @NonNull
    public static String categoryForRemotePlayback() {
        return zza("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK", null, null, false, true);
    }

    @NonNull
    public static String languageTagForLocale(@NonNull Locale locale) {
        return CastUtils.zzd(locale);
    }

    private static String zza(String str, @Nullable String str2, @Nullable Collection collection, boolean z, boolean z2) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder(str);
        if (str2 != null) {
            String upperCase = str2.toUpperCase();
            if (!upperCase.matches("[A-F0-9]+")) {
                throw new IllegalArgumentException(str2.length() != 0 ? "Invalid application ID: ".concat(str2) : new String("Invalid application ID: "));
            }
            sb.append("/");
            sb.append(upperCase);
        }
        if (collection != null) {
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("Must specify at least one namespace");
            }
            if (str2 == null) {
                sb.append("/");
            }
            sb.append("/");
            Iterator it = collection.iterator();
            boolean z3 = true;
            while (it.hasNext()) {
                String str3 = (String) it.next();
                CastUtils.throwIfInvalidNamespace(str3);
                if (!z3) {
                    sb.append(",");
                }
                sb.append(CastUtils.zze(str3));
                z3 = false;
            }
        }
        if (str2 == null && collection == null) {
            sb.append("/");
        }
        if (collection == null) {
            sb.append("/");
        }
        sb.append("//ALLOW_IPV6");
        return sb.toString();
    }

    @NonNull
    public static String categoryForCast(@NonNull String str) throws IllegalArgumentException {
        if (str != null) {
            return zza("com.google.android.gms.cast.CATEGORY_CAST", str, null, false, true);
        }
        throw new IllegalArgumentException("applicationId cannot be null");
    }

    @NonNull
    public static String categoryForRemotePlayback(@NonNull String str) throws IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("applicationId cannot be null or empty");
        }
        return zza("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK", str, null, false, true);
    }

    @NonNull
    public static String categoryForCast(@NonNull String str, @NonNull Collection<String> collection) {
        if (str == null) {
            throw new IllegalArgumentException("applicationId cannot be null");
        }
        if (collection == null) {
            throw new IllegalArgumentException("namespaces cannot be null");
        }
        return zza("com.google.android.gms.cast.CATEGORY_CAST", str, collection, false, true);
    }

    @NonNull
    public static String categoryForCast(@NonNull Collection<String> collection) throws IllegalArgumentException {
        if (collection == null) {
            throw new IllegalArgumentException("namespaces cannot be null");
        }
        return zza("com.google.android.gms.cast.CATEGORY_CAST", null, collection, false, true);
    }
}
