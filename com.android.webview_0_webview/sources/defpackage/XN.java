package defpackage;

import android.view.View;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class XN {
    public static boolean a(View view) {
        if (view.isInTouchMode() ? view.isFocusableInTouchMode() : view.isFocusable()) {
            return view.hasFocus();
        }
        return true;
    }
}
