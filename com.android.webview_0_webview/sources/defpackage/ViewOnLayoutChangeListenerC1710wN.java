package defpackage;

import android.view.View;

/* JADX INFO: renamed from: wN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewOnLayoutChangeListenerC1710wN implements View.OnLayoutChangeListener {
    public final /* synthetic */ C1820yN a;

    public ViewOnLayoutChangeListenerC1710wN(C1820yN c1820yN) {
        this.a = c1820yN;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        C1820yN c1820yN = this.a;
        if (view == c1820yN.a) {
            c1820yN.a();
        }
    }
}
