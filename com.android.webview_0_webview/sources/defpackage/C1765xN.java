package defpackage;

import android.widget.PopupWindow;

/* JADX INFO: renamed from: xN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1765xN implements PopupWindow.OnDismissListener {
    public final /* synthetic */ C1820yN s;

    public C1765xN(C1820yN c1820yN) {
        this.s = c1820yN;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        PopupWindow.OnDismissListener onDismissListener = this.s.f;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
        C1820yN c1820yN = this.s;
        c1820yN.a.removeOnLayoutChangeListener(c1820yN.e);
        this.s.a.setTag(null);
    }
}
