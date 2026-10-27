package p;

/* JADX INFO: loaded from: classes.dex */
public final class ej2 extends zi2 {
    public final Runnable k;

    public ej2(Runnable runnable, long j, w1 w1Var) {
        super(j, w1Var);
        this.k = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.k.run();
        } finally {
            this.e.getClass();
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Task[");
        Runnable runnable = this.k;
        sb.append(runnable.getClass().getSimpleName());
        sb.append('@');
        sb.append(f60.s(runnable));
        sb.append(", ");
        sb.append(this.c);
        sb.append(", ");
        sb.append(this.e);
        sb.append(']');
        return sb.toString();
    }
}
