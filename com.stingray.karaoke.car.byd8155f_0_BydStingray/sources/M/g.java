package M;

import e2.C1195c;
import h2.AbstractC1347d;
import java.util.function.IntConsumer;
import k3.C1628b0;
import n2.C1903c;
import n2.C1904d;
import n2.C1923x;
import n2.SurfaceHolderCallbackC1920u;
import o2.C2025b;
import x1.AbstractC2663b;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4139m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4140n;

    public /* synthetic */ g(int i7, int i10, Object obj) {
        this.l = i10;
        this.f4140n = obj;
        this.f4139m = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                ((IntConsumer) this.f4140n).accept(this.f4139m);
                break;
            case 1:
                ((C1628b0) this.f4140n).f17912k.remove(Integer.valueOf(this.f4139m));
                break;
            case 2:
                C1904d c1904d = ((C1903c) this.f4140n).f19628b;
                c1904d.getClass();
                int i7 = this.f4139m;
                if (i7 == -3 || i7 == -2) {
                    if (i7 != -2) {
                        C1195c c1195c = c1904d.d;
                        if (!(c1195c != null && c1195c.f14051a == 1)) {
                            c1904d.c(4);
                        }
                    }
                    SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u = c1904d.c;
                    if (surfaceHolderCallbackC1920u != null) {
                        C1923x c1923x = surfaceHolderCallbackC1920u.l;
                        c1923x.x1(0, 1, c1923x.C());
                    }
                    c1904d.c(3);
                } else if (i7 == -1) {
                    SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u2 = c1904d.c;
                    if (surfaceHolderCallbackC1920u2 != null) {
                        C1923x c1923x2 = surfaceHolderCallbackC1920u2.l;
                        c1923x2.x1(-1, 2, c1923x2.C());
                    }
                    c1904d.a();
                    c1904d.c(1);
                } else if (i7 == 1) {
                    c1904d.c(2);
                    SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u3 = c1904d.c;
                    if (surfaceHolderCallbackC1920u3 != null) {
                        C1923x c1923x3 = surfaceHolderCallbackC1920u3.l;
                        c1923x3.x1(1, 1, c1923x3.C());
                    }
                } else {
                    AbstractC1347d.p("Unknown focus change type: ", i7, "AudioFocusManager");
                }
                break;
            case 3:
                n2.D d = (n2.D) this.f4140n;
                int i10 = d.l[this.f4139m].f19636m;
                o2.e eVar = d.f19494I;
                eVar.Z(eVar.Y(), 1033, new C2025b(12));
                break;
            default:
                ((AbstractC2663b) this.f4140n).g(this.f4139m);
                break;
        }
    }

    public /* synthetic */ g(n2.D d, int i7, boolean z6) {
        this.l = 3;
        this.f4140n = d;
        this.f4139m = i7;
    }
}
