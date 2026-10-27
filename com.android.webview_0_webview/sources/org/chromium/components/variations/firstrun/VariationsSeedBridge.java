package org.chromium.components.variations.firstrun;

import android.util.Base64;
import defpackage.Hs;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VariationsSeedBridge {
    public static String a(String str) {
        return Hs.a.getString(str, "");
    }

    public static void clearFirstRunPrefs() {
        Hs.a.edit().remove("variations_seed_base64").remove("variations_seed_signature").remove("variations_seed_country").remove("variations_seed_date_ms").remove("variations_seed_date").remove("variations_seed_is_gzip_compressed").apply();
    }

    public static String getVariationsFirstRunSeedCountry() {
        return a("variations_seed_country");
    }

    public static byte[] getVariationsFirstRunSeedData() {
        return Base64.decode(a("variations_seed_base64"), 2);
    }

    public static long getVariationsFirstRunSeedDate() {
        long j = Hs.a.getLong("variations_seed_date_ms", 0L);
        if (j > 0) {
            return j;
        }
        String strA = a("variations_seed_date");
        if (strA.isEmpty()) {
            return 0L;
        }
        try {
            return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US).parse(strA).getTime();
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date in first run seed pref", e);
        }
    }

    public static boolean getVariationsFirstRunSeedIsGzipCompressed() {
        return Hs.a.getBoolean("variations_seed_is_gzip_compressed", false);
    }

    public static String getVariationsFirstRunSeedSignature() {
        return a("variations_seed_signature");
    }

    public static void markVariationsSeedAsStored() {
        Hs.a.edit().putBoolean("variations_seed_native_stored", true).apply();
    }

    public static void setVariationsFirstRunSeed(byte[] bArr, String str, String str2, long j, boolean z) {
        Hs.a.edit().putString("variations_seed_base64", Base64.encodeToString(bArr, 2)).putString("variations_seed_signature", str).putString("variations_seed_country", str2).putLong("variations_seed_date_ms", j).putBoolean("variations_seed_is_gzip_compressed", z).apply();
    }
}
