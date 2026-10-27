package P;

import E0.AbstractC0156f;
import E0.H;
import K7.K;
import android.view.View;
import androidx.compose.material.ripple.RippleNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import java.util.LinkedHashMap;
import n0.AbstractC1874c;
import n0.InterfaceC1889r;

/* JADX INFO: renamed from: P.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0490b extends RippleNode implements s {

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public r f5219H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public t f5220I;

    @Override // g0.n
    public final void A0() {
        r rVar = this.f5219H;
        if (rVar != null) {
            h0();
            M.t tVar = rVar.o;
            t tVar2 = (t) ((LinkedHashMap) tVar.l).get(this);
            if (tVar2 != null) {
                tVar2.c();
                LinkedHashMap linkedHashMap = (LinkedHashMap) tVar.l;
                t tVar3 = (t) linkedHashMap.get(this);
                if (tVar3 != null) {
                }
                linkedHashMap.remove(this);
                rVar.f5250n.add(tVar2);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r15v1, types: [A7.o, z7.a] */
    @Override // androidx.compose.material.ripple.RippleNode
    public final void H0(C.m mVar, long j3, float f10) {
        r rVarA = this.f5219H;
        if (rVarA == null) {
            rVarA = z.a(z.b((View) AbstractC0156f.i(this, AndroidCompositionLocals_androidKt.f11690f)));
            this.f5219H = rVarA;
            A7.m.c(rVarA);
        }
        t tVarA = rVarA.a(this);
        tVarA.b(mVar, this.f11530z, j3, C7.a.W(f10), J0(), ((h) this.B.a()).d, new K(this, 10));
        this.f5220I = tVarA;
        AbstractC0156f.m(this);
    }

    /* JADX WARN: Type inference failed for: r7v1, types: [A7.o, z7.a] */
    @Override // androidx.compose.material.ripple.RippleNode
    public final void I0(H h10) {
        InterfaceC1889r interfaceC1889rM = h10.l.f20638m.m();
        t tVar = this.f5220I;
        if (tVar != null) {
            tVar.e(this.f11526E, J0(), ((h) this.B.a()).d);
            tVar.draw(AbstractC1874c.a(interfaceC1889rM));
        }
    }

    @Override // androidx.compose.material.ripple.RippleNode
    public final void L0(C.m mVar) {
        t tVar = this.f5220I;
        if (tVar != null) {
            tVar.d();
        }
    }

    @Override // P.s
    public final void h0() {
        this.f5220I = null;
        AbstractC0156f.m(this);
    }
}
