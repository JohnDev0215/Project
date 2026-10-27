package defpackage;

import android.view.View;

/* JADX INFO: renamed from: sN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewOnLayoutChangeListenerC1490sN implements View.OnLayoutChangeListener {
    public final /* synthetic */ C1600uN a;

    public ViewOnLayoutChangeListenerC1490sN(C1600uN c1600uN) {
        this.a = c1600uN;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        C1600uN c1600uN = this.a;
        if (view == c1600uN.b) {
            c1600uN.a();
        }
    }
}
