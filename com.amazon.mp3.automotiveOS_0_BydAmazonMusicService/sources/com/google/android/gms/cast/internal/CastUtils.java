package com.google.android.gms.cast.internal;

import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@KeepForSdk
public final class CastUtils {
    private static final Pattern zza = Pattern.compile("urn:x-cast:[-A-Za-z0-9_]+(\\.[-A-Za-z0-9_]+)*");
    private static final Random zzb = new Random(SystemClock.elapsedRealtime());

    private CastUtils() {
    }

    @Nullable
    @KeepForSdk
    public static JSONObject jsonStringToJsonObject(@Nullable String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    @KeepForSdk
    public static double millisecToSec(long j) {
        return j / 1000.0d;
    }

    @Nullable
    @KeepForSdk
    public static String optStringOrNull(@NonNull JSONObject jSONObject, @NonNull String str) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return null;
        }
        return jSONObject.optString(str);
    }

    @KeepForSdk
    public static long secToMillisec(double d) {
        return (long) (d * 1000.0d);
    }

    @KeepForSdk
    public static long secToMillisec(long j) {
        return j * 1000;
    }

    @KeepForSdk
    public static void throwIfInvalidNamespace(@NonNull String str) throws IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Namespace cannot be null or empty");
        }
        if (str.length() > 128) {
            throw new IllegalArgumentException("Invalid namespace length");
        }
        if (!str.startsWith("urn:x-cast:")) {
            throw new IllegalArgumentException("Namespace must begin with the prefix \"urn:x-cast:\"");
        }
        if (str.length() == 11) {
            throw new IllegalArgumentException("Namespace must begin with the prefix \"urn:x-cast:\" and have non-empty suffix");
        }
    }

    @NonNull
    @KeepForSdk
    public static long[] toLongArray(@NonNull Collection<Long> collection) {
        long[] jArr = new long[collection.size()];
        Iterator<Long> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            jArr[i] = it.next().longValue();
            i++;
        }
        return jArr;
    }

    @Nullable
    @KeepForSdk
    public static long[] toLongArraySafely(@Nullable JSONArray jSONArray) {
        try {
            return zzj(jSONArray);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static int zza(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    public static long zzb() {
        return zzb.nextLong();
    }

    @NonNull
    public static String zzc(@NonNull String str) {
        return str.length() != 0 ? "urn:x-cast:".concat(str) : new String("urn:x-cast:");
    }

    @NonNull
    public static String zzd(@NonNull Locale locale) {
        StringBuilder sb = new StringBuilder(20);
        sb.append(locale.getLanguage());
        String country = locale.getCountry();
        if (!TextUtils.isEmpty(country)) {
            sb.append('-');
            sb.append(country);
        }
        String variant = locale.getVariant();
        if (!TextUtils.isEmpty(variant)) {
            sb.append('-');
            sb.append(variant);
        }
        return sb.toString();
    }

    @NonNull
    public static String zze(@NonNull String str) {
        if (zza.matcher(str).matches()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt < 'A' || cCharAt > 'Z') && ((cCharAt < 'a' || cCharAt > 'z') && !((cCharAt >= '0' && cCharAt <= '9') || cCharAt == '_' || cCharAt == '-' || cCharAt == '.' || cCharAt == ':'))) {
                sb.append(String.format("%%%04x", Integer.valueOf(cCharAt)));
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    @NonNull
    public static List zzf(@NonNull int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList;
    }

    @Nullable
    public static JSONObject zzg(@Nullable JSONObject jSONObject, @NonNull String str) {
        if (jSONObject.has(str)) {
            return jSONObject.optJSONObject(str);
        }
        return null;
    }

    public static boolean zzh(@NonNull Object obj, @NonNull Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    @NonNull
    public static int[] zzi(@NonNull Collection collection) {
        int[] iArr = new int[collection.size()];
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = ((Integer) it.next()).intValue();
            i++;
        }
        return iArr;
    }

    @Nullable
    public static long[] zzj(@Nullable JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        long[] jArr = new long[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            jArr[i] = jSONArray.getLong(i);
        }
        return jArr;
    }
}
