package d.c.a.b.b;

import android.text.TextUtils;
import android.util.Log;
import java.text.SimpleDateFormat;

/* JADX INFO: compiled from: Logging.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f4059a = 3072;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected static boolean f4060b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static SimpleDateFormat f4061c = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.SSS");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static d.c.a.b.a.a f4062d;

    public static int a(String str, String str2) {
        if (!f4060b) {
            return 0;
        }
        b(3, str, str2);
        return 0;
    }

    public static int b(int i, String str, String str2) {
        d.c.a.b.a.a aVar = f4062d;
        if (aVar != null) {
            return aVar.a(i, str, str2);
        }
        int iD = 0;
        if (TextUtils.isEmpty(str2)) {
            return 0;
        }
        if (i != 3) {
            return i != 4 ? i != 5 ? i != 6 ? Log.v(str, str2) : Log.e(str, str2) : Log.w(str, str2) : Log.i(str, str2);
        }
        int length = str2.length();
        int i2 = f4059a;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= 100) {
                break;
            }
            if (length > i2) {
                Log.d(str + i3, str2.substring(i4, i2));
                i3++;
                i4 = i2;
                i2 = f4059a + i2;
            } else if (i3 == 0) {
                iD = Log.d(str, str2.substring(i4, length));
            } else {
                iD = Log.d(str + i3, str2.substring(i4, length));
            }
        }
        return iD;
    }
}
