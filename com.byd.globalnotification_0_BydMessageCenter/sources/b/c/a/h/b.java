package b.c.a.h;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Log.d("[BYDGlobalNotification]", str);
    }

    public static void b(String str) {
        String str2;
        String strSubstring;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.length() <= 4000) {
            Log.e("[BYDGlobalNotification]", str);
            return;
        }
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 4000;
            if (i2 < str.length()) {
                str2 = "[BYDGlobalNotification]--" + i;
                strSubstring = str.substring(i, i2);
            } else {
                str2 = "[BYDGlobalNotification]--" + i;
                strSubstring = str.substring(i, str.length());
            }
            Log.e(str2, strSubstring);
            i = i2;
        }
    }
}
