package kotlinx.coroutines;

/* JADX INFO: compiled from: EventLoop.common.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class y0 extends e0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f7852g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f7853h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private kotlinx.coroutines.internal.a<s0<?>> f7854i;

    private final long e0(boolean z6) {
        return z6 ? 4294967296L : 1L;
    }

    public static /* synthetic */ void i0(y0 y0Var, boolean z6, int i7, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incrementUseCount");
        }
        if ((i7 & 1) != 0) {
            z6 = false;
        }
        y0Var.h0(z6);
    }

    public final void d0(boolean z6) {
        long jE0 = this.f7852g - e0(z6);
        this.f7852g = jE0;
        if (jE0 <= 0 && this.f7853h) {
            shutdown();
        }
    }

    public final void f0(s0<?> s0Var) {
        kotlinx.coroutines.internal.a<s0<?>> aVar = this.f7854i;
        if (aVar == null) {
            aVar = new kotlinx.coroutines.internal.a<>();
            this.f7854i = aVar;
        }
        aVar.a(s0Var);
    }

    protected long g0() {
        kotlinx.coroutines.internal.a<s0<?>> aVar = this.f7854i;
        return (aVar == null || aVar.c()) ? Long.MAX_VALUE : 0L;
    }

    public final void h0(boolean z6) {
        this.f7852g += e0(z6);
        if (z6) {
            return;
        }
        this.f7853h = true;
    }

    public final boolean j0() {
        return this.f7852g >= e0(true);
    }

    public final boolean k0() {
        kotlinx.coroutines.internal.a<s0<?>> aVar = this.f7854i;
        if (aVar != null) {
            return aVar.c();
        }
        return true;
    }

    public final boolean l0() {
        s0<?> s0VarD;
        kotlinx.coroutines.internal.a<s0<?>> aVar = this.f7854i;
        if (aVar == null || (s0VarD = aVar.d()) == null) {
            return false;
        }
        s0VarD.run();
        return true;
    }

    protected void shutdown() {
    }
}
