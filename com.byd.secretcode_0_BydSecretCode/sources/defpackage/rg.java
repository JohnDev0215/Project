package defpackage;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/* JADX INFO: compiled from: StringEncryptor.java */
/* JADX INFO: loaded from: classes.dex */
public class rg {
    public static String a(String str) {
        try {
            return Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
