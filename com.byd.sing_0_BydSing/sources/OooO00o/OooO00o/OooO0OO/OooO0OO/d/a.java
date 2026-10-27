package OooO00o.OooO00o.OooO0Oo.OooO0OO.d;

import android.annotation.SuppressLint;
import com.android.volley.BuildConfig;
import com.loostone.libserver.version1.entity.PMConfig;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(String str) {
        return PMConfig.getIv() + ((str == null || str.length() <= 10) ? ".r1a5s" : str.substring(str.length() - 6));
    }

    public static String b(String str, String str2) {
        String strA = a(str2);
        String strD = d(str2);
        if (str == null || str.length() == 0) {
            return BuildConfig.FLAVOR;
        }
        byte[] bArrDoFinal = null;
        IvParameterSpec ivParameterSpec = new IvParameterSpec(strA.getBytes());
        SecretKeySpec secretKeySpec = new SecretKeySpec(strD.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(2, secretKeySpec, ivParameterSpec);
            bArrDoFinal = cipher.doFinal(f(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bArrDoFinal == null ? BuildConfig.FLAVOR : new String(bArrDoFinal);
    }

    public static String c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        String str = BuildConfig.FLAVOR;
        for (int i = 0; i < length; i++) {
            str = (bArr[i] & 255) < 16 ? str + "0" + Integer.toHexString(bArr[i] & 255) : str + Integer.toHexString(bArr[i] & 255);
        }
        return str;
    }

    public static String d(String str) {
        String strSubstring;
        String strSubstring2;
        if (str == null || str.length() <= 10) {
            strSubstring = "s.1=";
            strSubstring2 = "4z;l";
        } else {
            strSubstring = str.substring(0, 4);
            strSubstring2 = str.substring(str.length() - 4);
        }
        return strSubstring + PMConfig.getKey() + strSubstring2;
    }

    @SuppressLint({"TrulyRandom"})
    public static String e(String str, String str2) {
        String strA = a(str2);
        String strD = d(str2);
        byte[] bArrDoFinal = null;
        if (str == null || str.length() == 0) {
            return null;
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(strA.getBytes());
        SecretKeySpec secretKeySpec = new SecretKeySpec(strD.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            bArrDoFinal = cipher.doFinal(g(str).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c(bArrDoFinal);
    }

    public static byte[] f(String str) {
        if (str == null || str.length() < 2) {
            return null;
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    public static String g(String str) {
        int length = 16 - (str.length() % 16);
        for (int i = 0; i < length; i++) {
            str = str + ' ';
        }
        return str;
    }
}
