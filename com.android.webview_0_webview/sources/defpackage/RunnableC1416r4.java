package defpackage;

import android.graphics.Rect;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: renamed from: r4, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1416r4 implements Runnable {
    public final /* synthetic */ boolean A;
    public final /* synthetic */ ArrayList B;
    public final /* synthetic */ Object C;
    public final /* synthetic */ Rect D;
    public final /* synthetic */ C4 s;
    public final /* synthetic */ C0971j1 t;
    public final /* synthetic */ Object u;
    public final /* synthetic */ C1471s4 v;
    public final /* synthetic */ ArrayList w;
    public final /* synthetic */ View x;
    public final /* synthetic */ K3 y;
    public final /* synthetic */ K3 z;

    public RunnableC1416r4(C4 c4, C0971j1 c0971j1, Object obj, C1471s4 c1471s4, ArrayList arrayList, View view, K3 k3, K3 k32, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
        this.s = c4;
        this.t = c0971j1;
        this.u = obj;
        this.v = c1471s4;
        this.w = arrayList;
        this.x = view;
        this.y = k3;
        this.z = k32;
        this.A = z;
        this.B = arrayList2;
        this.C = obj2;
        this.D = rect;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0971j1 c0971j1E = AbstractC1526t4.e(this.s, this.t, this.u, this.v);
        if (c0971j1E != null) {
            this.w.addAll(c0971j1E.values());
            this.w.add(this.x);
        }
        AbstractC1526t4.c(this.y, this.z, this.A, c0971j1E, false);
        Object obj = this.u;
        if (obj != null) {
            this.s.t(obj, this.B, this.w);
            View viewK = AbstractC1526t4.k(c0971j1E, this.v, this.C, this.A);
            if (viewK != null) {
                this.s.j(viewK, this.D);
            }
        }
    }
}
