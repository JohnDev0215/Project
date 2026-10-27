package org.chromium.components.variations.firstrun;

import android.util.Base64;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class VariationsSeedBridge {
    @CalledByNative
    public static void clearFirstRunPrefs() {
        xz.a.a.edit().remove("variations_seed_base64").remove("variations_seed_signature").remove("variations_seed_country").remove("variations_seed_date_ms").remove("variations_seed_is_gzip_compressed").apply();
    }

    @CalledByNative
    public static String getVariationsFirstRunSeedCountry() {
        return xz.a.a.getString("variations_seed_country", "");
    }

    @CalledByNative
    public static byte[] getVariationsFirstRunSeedData() {
        return Base64.decode(xz.a.a.getString("variations_seed_base64", ""), 2);
    }

    @CalledByNative
    public static long getVariationsFirstRunSeedDate() {
        return xz.a.a.getLong("variations_seed_date_ms", 0L);
    }

    @CalledByNative
    public static boolean getVariationsFirstRunSeedIsGzipCompressed() {
        return xz.a.a.getBoolean("variations_seed_is_gzip_compressed", false);
    }

    @CalledByNative
    public static String getVariationsFirstRunSeedSignature() {
        return xz.a.a.getString("variations_seed_signature", "");
    }

    @CalledByNative
    public static boolean hasNativePref() {
        return xz.a.a.getBoolean("variations_seed_native_stored", false);
    }

    @CalledByNative
    public static void markVariationsSeedAsStored() {
        xz.a.a.edit().putBoolean("variations_seed_native_stored", true).apply();
    }

    @CalledByNative
    public static void setVariationsFirstRunSeed(byte[] bArr, String str, String str2, long j, boolean z) {
        xz.a.a.edit().putString("variations_seed_base64", Base64.encodeToString(bArr, 2)).putString("variations_seed_signature", str).putString("variations_seed_country", str2).putLong("variations_seed_date_ms", j).putBoolean("variations_seed_is_gzip_compressed", z).apply();
    }
}
