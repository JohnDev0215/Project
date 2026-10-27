package defpackage;

import android.graphics.Rect;
import android.view.View;

/* JADX INFO: renamed from: q4, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1362q4 implements Runnable {
    public final /* synthetic */ K3 s;
    public final /* synthetic */ K3 t;
    public final /* synthetic */ boolean u;
    public final /* synthetic */ C0971j1 v;
    public final /* synthetic */ View w;
    public final /* synthetic */ C4 x;
    public final /* synthetic */ Rect y;

    public RunnableC1362q4(K3 k3, K3 k32, boolean z, C0971j1 c0971j1, View view, C4 c4, Rect rect) {
        this.s = k3;
        this.t = k32;
        this.u = z;
        this.v = c0971j1;
        this.w = view;
        this.x = c4;
        this.y = rect;
    }

    @Override // java.lang.Runnable
    public void run() {
        AbstractC1526t4.c(this.s, this.t, this.u, this.v, false);
        View view = this.w;
        if (view != null) {
            this.x.j(view, this.y);
        }
    }
}
