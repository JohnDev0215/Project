package G;

import E0.r0;
import E0.s0;
import android.os.Bundle;
import java.util.List;
import n7.AbstractC1999n;
import y0.C2733k;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class g0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2562m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A7.z f2563n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g0(A7.z zVar, int i7) {
        super(1);
        this.f2562m = i7;
        this.f2563n = zVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f2562m) {
            case 0:
                s0 s0Var = (s0) obj;
                A7.m.d(s0Var, "null cannot be cast to non-null type androidx.compose.foundation.lazy.layout.TraversablePrefetchStateNode");
                V v10 = ((l0) s0Var).f2600y;
                A7.z zVar = this.f2563n;
                List listF = (List) zVar.l;
                if (listF != null) {
                    listF.add(v10);
                } else {
                    listF = AbstractC1999n.F(v10);
                }
                zVar.l = listF;
                return r0.f1697m;
            case 1:
                String str = (String) obj;
                A7.m.f(str, "key");
                Object obj2 = this.f2563n.l;
                boolean z6 = true;
                if (obj2 != null && ((Bundle) obj2).containsKey(str)) {
                    z6 = false;
                }
                return Boolean.valueOf(z6);
            case 2:
                C2733k c2733k = (C2733k) obj;
                A7.z zVar2 = this.f2563n;
                Object obj3 = zVar2.l;
                if (obj3 == null && c2733k.f24069A) {
                    zVar2.l = c2733k;
                } else if (obj3 != null && c2733k.f24071z && c2733k.f24069A) {
                    zVar2.l = c2733k;
                }
                return Boolean.TRUE;
            case 3:
                C2733k c2733k2 = (C2733k) obj;
                r0 r0Var = r0.l;
                if (!c2733k2.f24069A) {
                    return r0Var;
                }
                this.f2563n.l = c2733k2;
                return c2733k2.f24071z ? r0.f1697m : r0Var;
            default:
                C2733k c2733k3 = (C2733k) obj;
                if (c2733k3.f24071z && c2733k3.f24069A) {
                    this.f2563n.l = c2733k3;
                }
                return Boolean.TRUE;
        }
    }
}
