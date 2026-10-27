package defpackage;

import J.N;
import android.graphics.Canvas;

/* JADX INFO: renamed from: Or, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0268Or implements InterfaceC0286Pr {
    public long a;
    public final InterfaceC0250Nr b;
    public final int c;

    public C0268Or(InterfaceC0250Nr interfaceC0250Nr) {
        this.b = interfaceC0250Nr;
        long jM_e6WGre = N.M_e6WGre();
        this.a = jM_e6WGre;
        this.c = N.MVv$dooB(jM_e6WGre, this);
    }

    @Override // defpackage.InterfaceC0286Pr
    public void a() {
    }

    @Override // defpackage.InterfaceC0286Pr
    public long b() {
        return N.MyqeD0HT(this.a, this);
    }

    @Override // defpackage.InterfaceC0286Pr
    public boolean c(Canvas canvas) {
        this.b.d(canvas, this.c);
        return true;
    }

    @Override // defpackage.InterfaceC0286Pr
    public void destroy() {
        N.MTNG5d$d(this.a, this);
        this.a = 0L;
    }
}
