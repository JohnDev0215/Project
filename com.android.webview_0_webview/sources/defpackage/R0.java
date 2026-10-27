package defpackage;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class R0 {
    public static void a(View view, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
            return;
        }
        U0 u0 = U0.B;
        if (u0 != null && u0.s == view) {
            U0.c(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new U0(view, charSequence);
            return;
        }
        U0 u02 = U0.C;
        if (u02 != null && u02.s == view) {
            u02.b();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }
}
