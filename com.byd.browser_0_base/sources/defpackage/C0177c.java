package defpackage;

import android.util.Base64;
import android.util.Log;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes3.dex
 */
/* JADX INFO: renamed from: c, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AESUtils.java */
/* JADX INFO: loaded from: classes3.dex */
public final class C0177c {
    public static final Charset a = StandardCharsets.UTF_8;

    public static String a(String str) {
        try {
            Charset charset = a;
            SecretKeySpec secretKeySpec = new SecretKeySpec("com.byd.browser.".getBytes(charset), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, secretKeySpec);
            return Base64.encodeToString(cipher.doFinal(str.getBytes(charset)), 2);
        } catch (Exception e) {
            Log.e("c", "encrypt---->" + e);
            return null;
        }
    }
}
