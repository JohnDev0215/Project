package m4;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/* JADX INFO: compiled from: SystemTool.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f6377a = false;

    public static void a(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        boolean zD = d(viewGroup, null);
        x3.a.a("SystemTool", "---clearEditTextFocus---hasFocus: " + zD);
        if (zD) {
            return;
        }
        View rootView = viewGroup.getRootView();
        x3.a.a("SystemTool", "---clearEditTextFocus---root: " + rootView);
        if (rootView instanceof ViewGroup) {
            d((ViewGroup) rootView, viewGroup);
        }
    }

    public static void b(EditText editText) {
        if (editText == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getApplicationContext().getSystemService("input_method");
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
        }
    }

    public static void c(EditText editText, int i8) {
        if (editText == null) {
            return;
        }
        Context applicationContext = editText.getContext().getApplicationContext();
        if (i8 == 6) {
            InputMethodManager inputMethodManager = (InputMethodManager) applicationContext.getSystemService("input_method");
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
                editText.clearFocus();
            }
        }
    }

    public static boolean d(ViewGroup viewGroup, ViewGroup viewGroup2) {
        if (viewGroup == null) {
            return false;
        }
        int childCount = viewGroup.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = viewGroup.getChildAt(i8);
            if (viewGroup2 == null || !childAt.equals(viewGroup2)) {
                if (childAt instanceof ViewGroup) {
                    if (d((ViewGroup) childAt, viewGroup2)) {
                        return true;
                    }
                } else if (childAt instanceof EditText) {
                    EditText editText = (EditText) childAt;
                    if (editText.hasFocus()) {
                        x3.a.a("SystemTool", "---findAndClearEditTextFocus---et: " + editText);
                        editText.clearFocus();
                        b(editText);
                        return true;
                    }
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    public static EditText e(ViewGroup viewGroup, ViewGroup viewGroup2) {
        EditText editTextE;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = viewGroup.getChildAt(i8);
            if (childAt instanceof ViewGroup) {
                if ((viewGroup2 == null || !childAt.equals(viewGroup2)) && (editTextE = e((ViewGroup) childAt, viewGroup2)) != null) {
                    return editTextE;
                }
            } else if (childAt instanceof EditText) {
                EditText editText = (EditText) childAt;
                if (editText.hasFocus()) {
                    x3.a.a("SystemTool", "---findFocusingEditText---et: " + editText);
                    return editText;
                }
            } else {
                continue;
            }
        }
        return null;
    }

    public static EditText f(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return null;
        }
        EditText editTextE = e(viewGroup, null);
        x3.a.a("SystemTool", "---findFocusingEditTextOtherPage---et: " + editTextE);
        if (editTextE != null) {
            return editTextE;
        }
        View rootView = viewGroup.getRootView();
        x3.a.a("SystemTool", "---findFocusingEditTextOtherPage---root: " + rootView);
        if (rootView instanceof ViewGroup) {
            return e((ViewGroup) rootView, viewGroup);
        }
        return null;
    }

    public static Drawable g(Context context, String str) {
        PackageInfo packageInfo;
        x3.a.a("SystemTool", "---getAppIcon---pkgName = " + str);
        if (context != null && !TextUtils.isEmpty(str)) {
            PackageManager packageManager = context.getPackageManager();
            try {
                packageInfo = packageManager.getPackageInfo(str, 0);
            } catch (PackageManager.NameNotFoundException e8) {
                x3.a.b("SystemTool", "---getAppName---e = " + e8.toString());
                packageInfo = null;
            }
            x3.a.a("SystemTool", "---getAppIcon---pi = " + packageInfo);
            if (packageInfo != null) {
                return packageInfo.applicationInfo.loadIcon(packageManager);
            }
        }
        return null;
    }

    public static String h(Context context, String str) {
        PackageInfo packageInfo;
        if (context != null && !TextUtils.isEmpty(str)) {
            PackageManager packageManager = context.getPackageManager();
            try {
                packageInfo = packageManager.getPackageInfo(str, 0);
            } catch (PackageManager.NameNotFoundException e8) {
                x3.a.b("SystemTool", "---getAppName---e = " + e8.toString());
                packageInfo = null;
            }
            if (packageInfo != null) {
                return packageInfo.applicationInfo.loadLabel(packageManager).toString();
            }
        }
        return null;
    }

    public static boolean i() {
        return f6377a;
    }

    public static void j(EditText editText) {
        if (editText == null) {
            return;
        }
        ((InputMethodManager) editText.getContext().getApplicationContext().getSystemService("input_method")).showSoftInput(editText, 1);
    }

    public static void k(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        x3.a.b("SystemTool", "---startOtherApp---in: " + launchIntentForPackage);
        if (launchIntentForPackage != null) {
            context.startActivity(launchIntentForPackage);
        }
    }
}
