package kotlinx.coroutines;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
public class o1 extends s1 implements w {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f7713e;

    public o1(l1 l1Var) {
        super(true);
        initParentJobInternal$kotlinx_coroutines_core(l1Var);
        this.f7713e = f();
    }

    private final boolean f() {
        s1 s1Var;
        p parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        if (!(parentHandle$kotlinx_coroutines_core instanceof q)) {
            parentHandle$kotlinx_coroutines_core = null;
        }
        q qVar = (q) parentHandle$kotlinx_coroutines_core;
        if (qVar != null && (s1Var = (s1) qVar.f7715i) != null) {
            while (!s1Var.getHandlesException$kotlinx_coroutines_core()) {
                p parentHandle$kotlinx_coroutines_core2 = s1Var.getParentHandle$kotlinx_coroutines_core();
                if (!(parentHandle$kotlinx_coroutines_core2 instanceof q)) {
                    parentHandle$kotlinx_coroutines_core2 = null;
                }
                q qVar2 = (q) parentHandle$kotlinx_coroutines_core2;
                if (qVar2 == null || (s1Var = (s1) qVar2.f7715i) == null) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlinx.coroutines.s1
    public boolean getHandlesException$kotlinx_coroutines_core() {
        return this.f7713e;
    }

    @Override // kotlinx.coroutines.s1
    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return true;
    }
}
