package h.a.a.a.a;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX INFO: compiled from: StringUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static String a(byte[] bArr, Charset charset) {
        if (bArr == null) {
            return null;
        }
        return new String(bArr, charset);
    }

    public static String b(byte[] bArr) {
        return a(bArr, StandardCharsets.US_ASCII);
    }

    public static String c(byte[] bArr) {
        return a(bArr, StandardCharsets.UTF_8);
    }
}
