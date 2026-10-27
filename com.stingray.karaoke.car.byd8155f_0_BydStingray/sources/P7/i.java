package P7;

import A7.A;
import A7.B;
import A7.s;
import H7.w;
import T7.z;
import n7.AbstractC1998m;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class i extends N7.i {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ w[] f5436h;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public N7.l f5437f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final E8.i f5438g;

    static {
        B b10 = A.f443a;
        f5436h = new w[]{b10.h(new s(b10.b(i.class), "customizer", "getCustomizer()Lorg/jetbrains/kotlin/builtins/jvm/JvmBuiltInsCustomizer;"))};
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(E8.l lVar) {
        super(lVar);
        A7.k.p(1, "kind");
        this.f5438g = new E8.i(lVar, new B8.A(this, 20, lVar));
        int iC = AbstractC2641j.c(1);
        if (iC == 1) {
            c(false);
        } else {
            if (iC != 2) {
                return;
            }
            c(true);
        }
    }

    public final n H() {
        return (n) C7.a.N(this.f5438g, f5436h[0]);
    }

    @Override // N7.i
    public final S7.b d() {
        return H();
    }

    @Override // N7.i
    public final Iterable l() {
        Iterable iterableL = super.l();
        E8.l lVar = this.d;
        z zVarK = k();
        A7.m.e(zVarK, "builtInsModule");
        return AbstractC1998m.m0(iterableL, new g(lVar, zVarK));
    }

    @Override // N7.i
    public final S7.d o() {
        return H();
    }
}
