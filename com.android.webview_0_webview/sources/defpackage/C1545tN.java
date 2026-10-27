package defpackage;

import android.widget.PopupWindow;

/* JADX INFO: renamed from: tN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1545tN implements PopupWindow.OnDismissListener {
    public final /* synthetic */ C1600uN s;

    public C1545tN(C1600uN c1600uN) {
        this.s = c1600uN;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.s.g.f.dismiss();
        C1600uN c1600uN = this.s;
        c1600uN.b.removeOnLayoutChangeListener(c1600uN.e);
        this.s.b.setTag(null);
    }
}
