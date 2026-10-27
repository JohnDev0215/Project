package OooO00o.OooO00o.OooO0Oo.OooO0OO.d;

import com.android.volley.BuildConfig;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static String a(String str, String str2) {
        byte[] bArrB = b(str);
        if (bArrB != null) {
            try {
                return new String(bArrB, str2);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return BuildConfig.FLAVOR;
    }

    public static byte[] b(String str) {
        FileInputStream fileInputStream;
        byte[] bArr = null;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            fileInputStream = null;
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bArr;
    }

    public static String c(String str) {
        return a(str, "UTF-8");
    }
}
