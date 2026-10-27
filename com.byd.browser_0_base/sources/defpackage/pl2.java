package defpackage;

import android.content.SharedPreferences;
import android.util.Log;
import defpackage.aj;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/* JADX INFO: compiled from: SharedPreferencesBrandingTimeStorage.java */
/* JADX INFO: loaded from: classes.dex */
public final class pl2 implements aj.a {
    public static pl2 b;
    public SharedPreferences a;

    public pl2() {
        if (bj.n.c()) {
            a().edit().clear().apply();
        }
    }

    public static String b(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(c6.b(str + ""));
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            Formatter formatter = new Formatter(sb);
            for (byte b2 : bArrDigest) {
                formatter.format("%02x", Byte.valueOf(b2));
            }
            String string = sb.toString();
            formatter.close();
            return string;
        } catch (NoSuchAlgorithmException unused) {
            Log.e("HashUtil", "Unable to find digest algorithm MD5");
            return null;
        }
    }

    public final SharedPreferences a() {
        if (this.a == null) {
            this.a = xz.a.getSharedPreferences("pref_cct_brand_show_time", 0);
        }
        return this.a;
    }
}
