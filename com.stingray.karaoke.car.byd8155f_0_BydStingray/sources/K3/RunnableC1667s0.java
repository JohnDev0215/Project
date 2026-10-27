package k3;

import android.util.SparseBooleanArray;
import java.util.List;

/* JADX INFO: renamed from: k3.s0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC1667s0 implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f18109m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f18110n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f18111p;

    public /* synthetic */ RunnableC1667s0(int i7, int i10, Object obj, Object obj2, Object obj3) {
        this.l = i10;
        this.f18110n = obj;
        this.f18109m = i7;
        this.o = obj2;
        this.f18111p = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                C1671u0 c1671u0 = (C1671u0) this.f18110n;
                if (this.f18109m == c1671u0.f18129h) {
                    A0 a02 = (A0) this.o;
                    c1671u0.d(a02, (e7.f) this.f18111p, c1671u0.c(a02, false));
                }
                break;
            default:
                Q0 q02 = (Q0) ((B5.u) this.f18110n).o;
                int i7 = this.f18109m;
                List list = (List) this.o;
                if (i7 == -1) {
                    q02.f17843f.f17811t.r0(list);
                } else {
                    q02.f17843f.f17811t.z(i7, list);
                }
                I0 i02 = q02.f17843f;
                new SparseBooleanArray().append(20, true);
                i02.o((C1679y0) this.f18111p);
                break;
        }
    }
}
