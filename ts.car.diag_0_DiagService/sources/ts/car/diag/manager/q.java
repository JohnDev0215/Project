package ts.car.diag.manager;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f1623a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f1624b;

    public static void a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" ");
        c(str2);
        sb.append(str2);
        Log.d("Diag-Log", sb.toString());
    }

    public static void b(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" ");
        c(str2);
        sb.append(str2);
        Log.e("Diag-Log", sb.toString());
    }

    private static String c(String str) {
        return str;
    }

    public static void d(String str) {
        c(str);
        Log.i("Diag-Log", str);
    }

    public static void e(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" ");
        c(str2);
        sb.append(str2);
        Log.i("Diag-Log", sb.toString());
    }

    public static void f(Context context) {
        int i = Settings.System.getInt(context.getContentResolver(), "key_for_test_open_function", 0);
        f1624b = i;
        if (i != 0) {
            f1623a = true;
            Log.w("Diag-Log", "startOutputSensitiveInformation: true");
            return;
        }
        Log.d("Diag-Log", "init mForTestValue is " + f1624b + " execute return");
    }

    public static void g(String str) {
        c(str);
        Log.w("Diag-Log", str);
    }

    public static void h(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" ");
        c(str2);
        sb.append(str2);
        Log.w("Diag-Log", sb.toString());
    }

    public static void i(String str, String str2) {
        if (f1623a) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" ");
            c(str2);
            sb.append(str2);
            Log.w("Diag-Log", sb.toString());
        }
    }
}
