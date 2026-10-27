package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class pi0 extends kotlinx.coroutines.b {
    public static final /* synthetic */ int n = 0;
    public long k;
    public boolean l;
    public gb m;

    public final void K0(boolean z) {
        long j = this.k - (z ? 4294967296L : 1L);
        this.k = j;
        if (j <= 0 && this.l) {
            shutdown();
        }
    }

    public final void L0(ga0 ga0Var) {
        gb gbVar = this.m;
        if (gbVar == null) {
            gbVar = new gb();
            this.m = gbVar;
        }
        gbVar.addLast(ga0Var);
    }

    public abstract Thread M0();

    public final void N0(boolean z) {
        this.k = (z ? 4294967296L : 1L) + this.k;
        if (z) {
            return;
        }
        this.l = true;
    }

    public final boolean O0() {
        return this.k >= 4294967296L;
    }

    public abstract long P0();

    public final boolean Q0() {
        gb gbVar = this.m;
        if (gbVar == null) {
            return false;
        }
        ga0 ga0Var = (ga0) (gbVar.isEmpty() ? null : gbVar.removeFirst());
        if (ga0Var == null) {
            return false;
        }
        ga0Var.run();
        return true;
    }

    public void R0(long j, ni0 ni0Var) {
        kotlinx.coroutines.c.r.V0(j, ni0Var);
    }

    public abstract void shutdown();
}
