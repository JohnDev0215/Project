package defpackage;

import org.chromium.android_webview.PopupTouchHandleDrawable;

/* JADX INFO: renamed from: mq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1182mq extends ID implements JD {
    public final /* synthetic */ PopupTouchHandleDrawable a;

    public C1182mq(PopupTouchHandleDrawable popupTouchHandleDrawable) {
        this.a = popupTouchHandleDrawable;
    }

    @Override // defpackage.JD
    public void b(int i, int i2) {
        PopupTouchHandleDrawable.a(this.a, true);
    }

    @Override // defpackage.ID, defpackage.JD
    public void c(int i, int i2) {
        PopupTouchHandleDrawable.a(this.a, false);
    }

    @Override // defpackage.ID, defpackage.JD
    public void e(int i, int i2) {
        PopupTouchHandleDrawable.a(this.a, false);
    }

    @Override // defpackage.ID, defpackage.JD
    public void g(int i, int i2) {
        this.a.m();
    }

    @Override // defpackage.ID, defpackage.JD
    public void i() {
        this.a.destroy();
    }

    @Override // defpackage.ID, defpackage.JD
    public void onWindowFocusChanged(boolean z) {
        PopupTouchHandleDrawable popupTouchHandleDrawable = this.a;
        if (popupTouchHandleDrawable.P == z) {
            return;
        }
        popupTouchHandleDrawable.P = z;
        popupTouchHandleDrawable.i();
    }
}
