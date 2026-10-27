package defpackage;

import android.app.Activity;
import android.os.UserManager;
import android.text.Html;
import android.text.Spanned;
import android.view.inputmethod.InputMethodSubtype;

/* JADX INFO: renamed from: qs, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1404qs {
    public static String a(InputMethodSubtype inputMethodSubtype) {
        return inputMethodSubtype.getLanguageTag();
    }

    public static boolean b() {
        return ((UserManager) Is.a.getSystemService("user")).isDemoUser();
    }

    public static boolean c(Activity activity) {
        return activity.isInMultiWindowMode();
    }

    public static String d(Spanned spanned, int i) {
        return Html.toHtml(spanned, i);
    }
}
