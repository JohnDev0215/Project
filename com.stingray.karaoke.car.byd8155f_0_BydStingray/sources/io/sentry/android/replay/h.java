package io.sentry.android.replay;

import C0.U;
import C0.V;
import H7.H;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.H1;
import java.io.File;
import java.io.Serializable;
import w.C2579j;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class h extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f16401m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f16402n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f16403p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(long j3, Object obj, Serializable serializable, int i7) {
        super(1);
        this.f16401m = i7;
        this.f16402n = j3;
        this.o = obj;
        this.f16403p = serializable;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f16401m) {
            case 0:
                j jVar = (j) obj;
                A7.m.f(jVar, "it");
                if (jVar.f16413b < this.f16402n) {
                    ((i) this.o).b(jVar.f16412a);
                    return Boolean.TRUE;
                }
                A7.z zVar = (A7.z) this.f16403p;
                if (zVar.l == null) {
                    zVar.l = jVar.c;
                }
                return Boolean.FALSE;
            case 1:
                io.sentry.android.replay.capture.i iVar = (io.sentry.android.replay.capture.i) obj;
                A7.m.f(iVar, "it");
                H1 h12 = iVar.f16376a;
                if (h12.f15874F.getTime() >= this.f16402n) {
                    return Boolean.FALSE;
                }
                io.sentry.android.replay.capture.f fVar = (io.sentry.android.replay.capture.f) this.o;
                fVar.n(fVar.j() - 1);
                File file = h12.f15870A;
                F1 f12 = fVar.f16370s;
                if (file != null) {
                    try {
                        if (!file.delete()) {
                            f12.getLogger().k(EnumC1495p1.ERROR, "Failed to delete replay segment: %s", file.getAbsolutePath());
                        }
                    } catch (Throwable th) {
                        f12.getLogger().p(EnumC1495p1.ERROR, th, "Failed to delete replay segment: %s", file.getAbsolutePath());
                    }
                    break;
                }
                ((A7.v) this.f16403p).l = true;
                return Boolean.TRUE;
            default:
                g0.c cVar = ((C2579j) this.o).f23106b;
                V v10 = (V) this.f16403p;
                U.e((U) obj, v10, cVar.a(H.c(v10.l, v10.f871m), this.f16402n, a1.k.l));
                return m7.q.f19267a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(C2579j c2579j, V v10, long j3) {
        super(1);
        this.f16401m = 2;
        this.o = c2579j;
        this.f16403p = v10;
        this.f16402n = j3;
    }
}
