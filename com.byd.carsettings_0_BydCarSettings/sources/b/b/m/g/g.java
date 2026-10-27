package b.b.m.g;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.util.List;

/* JADX INFO: compiled from: SystemTool.java */
/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f2180a = false;

    public static void a(EditText editText) {
        if (editText == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getApplicationContext().getSystemService("input_method");
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
        }
    }

    public static void b(EditText editText, int i) {
        if (editText == null) {
            return;
        }
        Context applicationContext = editText.getContext().getApplicationContext();
        if (i == 6) {
            InputMethodManager inputMethodManager = (InputMethodManager) applicationContext.getSystemService("input_method");
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
                editText.clearFocus();
            }
        }
    }

    public static String c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean d(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                if (context.getPackageManager().getApplicationInfo(str, 8192) != null) {
                    return true;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                b.b.m.c.a.c("SystemTool", "---isAppInstalled---e = " + e2.toString());
            }
        }
        return false;
    }

    public static boolean e() {
        return f2180a;
    }

    public static void f(EditText editText) {
        if (editText == null) {
            return;
        }
        ((InputMethodManager) editText.getContext().getApplicationContext().getSystemService("input_method")).showSoftInput(editText, 1);
    }

    public static void g(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        b.b.m.c.a.a("SystemTool", "----startByScheme----resolveInfos: " + listQueryIntentActivities);
        if (listQueryIntentActivities.isEmpty()) {
            return;
        }
        context.startActivity(intent);
    }
}
