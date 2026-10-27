package spi;

/* JADX INFO: loaded from: classes.dex */
public final class a5 extends pk {
    public final g5 a;
    public final a5 b;
    public ll<r> c;

    public static final class a<T> implements ll<T> {
        public final int a;

        public a(g5 g5Var, a5 a5Var, int i) {
            this.a = i;
        }

        @Override // spi.ll
        public final T get() {
            if (this.a == 0) {
                return (T) new on();
            }
            throw new AssertionError(this.a);
        }
    }

    private a5(g5 g5Var) {
        this.b = this;
        this.a = g5Var;
        this.c = u6.a(new a(g5Var, this, 0));
    }

    public /* synthetic */ a5(g5 g5Var, d dVar) {
        this(g5Var);
    }

    @Override // spi.q.c
    public final r a() {
        return this.c.get();
    }

    @Override // spi.j.a
    public final i b() {
        return new x4(this.a, this.b, null);
    }
}
