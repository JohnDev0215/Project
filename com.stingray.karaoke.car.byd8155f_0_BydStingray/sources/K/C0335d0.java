package K;

import A.C0004b;
import Q.C0507g0;
import Q.C0523o0;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: K.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0335d0 implements C0.K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3245a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f3246b;

    public /* synthetic */ C0335d0(Object obj, int i7) {
        this.f3245a = i7;
        this.f3246b = obj;
    }

    @Override // C0.K
    public final C0.L h(C0.M m10, List list, long j3) {
        switch (this.f3245a) {
            case 0:
                return m10.m0(a1.a.h(j3), a1.a.g(j3), n7.v.l, new C0004b(list, 17, this));
            default:
                int size = list.size();
                for (int i7 = 0; i7 < size; i7++) {
                    C0.J j10 = (C0.J) list.get(i7);
                    if (androidx.compose.ui.layout.a.a(j10) == Q.V.l) {
                        C0.V vB = j10.b(j3);
                        int size2 = list.size();
                        for (int i10 = 0; i10 < size2; i10++) {
                            C0.J j11 = (C0.J) list.get(i10);
                            if (androidx.compose.ui.layout.a.a(j11) == Q.V.f5709m) {
                                C0.V vB2 = j11.b(a1.a.a(io.sentry.config.a.h0(-vB.l, 0, 2, j3), 0, 0, 0, 0, 11));
                                int i11 = vB.l + vB2.l;
                                int iMax = Math.max(vB2.f871m, vB.f871m);
                                float f10 = vB2.f871m;
                                C0523o0 c0523o0 = (C0523o0) this.f3246b;
                                c0523o0.f6009t.j(f10);
                                c0523o0.f6007r.j(i11);
                                return m10.m0(i11, iMax, n7.v.l, new C0507g0(vB2, vB.l / 2, (iMax - vB2.f871m) / 2, vB, C7.a.W(c0523o0.c() * vB2.l), (iMax - vB.f871m) / 2));
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }
    }
}
