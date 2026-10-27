package defpackage;

import android.widget.PopupWindow;

/* JADX INFO: renamed from: kB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1037kB implements PopupWindow.OnDismissListener {
    public final /* synthetic */ C1149mB s;

    public C1037kB(C1149mB c1149mB) {
        this.s = c1149mB;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C1149mB c1149mB = this.s;
        if (c1149mB.c) {
            return;
        }
        c1149mB.a.a(null);
        c1149mB.c = true;
    }
}
