package c.a.d.s;

import android.text.TextUtils;
import java.security.MessageDigest;
import java.util.Map;
import java.util.SortedMap;
import java.util.UUID;

/* JADX INFO: compiled from: SignUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class l {
    public static String a(SortedMap<String, String> sortedMap) {
        return f(g(sortedMap));
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (char c2 : str.toCharArray()) {
            Integer numValueOf = Integer.valueOf(c2);
            if (numValueOf.intValue() >= 48 && numValueOf.intValue() <= 57) {
                stringBuffer.append(c2);
            }
            if (numValueOf.intValue() > 96 && numValueOf.intValue() < 103) {
                stringBuffer.append(c2);
            }
        }
        return stringBuffer.toString();
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (char c2 : str.toCharArray()) {
            Integer numValueOf = Integer.valueOf(c2);
            if ((numValueOf.intValue() >= 48 && numValueOf.intValue() <= 57) || (numValueOf.intValue() >= 100 && numValueOf.intValue() <= 102)) {
                stringBuffer.append(c2);
            }
        }
        return stringBuffer.toString();
    }

    public static String d() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static String e() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String f(String str) {
        if (str != null && str.length() != 0) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                messageDigest.update(str.getBytes("UTF-8"));
                byte[] bArrDigest = messageDigest.digest();
                char[] cArr2 = new char[bArrDigest.length * 2];
                int i2 = 0;
                for (byte b2 : bArrDigest) {
                    int i3 = i2 + 1;
                    cArr2[i2] = cArr[(b2 >>> 4) & 15];
                    i2 = i3 + 1;
                    cArr2[i3] = cArr[b2 & 15];
                }
                return new String(cArr2);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static String g(SortedMap<String, String> sortedMap) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return stringBuffer.substring(0, stringBuffer.lastIndexOf("&"));
    }
}
