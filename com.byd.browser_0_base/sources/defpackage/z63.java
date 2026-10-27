package defpackage;

/* JADX INFO: compiled from: Tasks.kt */
/* JADX INFO: loaded from: classes.dex */
public final class z63 extends v63 {
    public final Runnable g;

    public z63(Runnable runnable, long j, w63 w63Var) {
        super(j, w63Var);
        this.g = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.g.run();
        } finally {
            this.f.a();
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Task[");
        Runnable runnable = this.g;
        sb.append(runnable.getClass().getSimpleName());
        sb.append('@');
        sb.append(d70.d(runnable));
        sb.append(", ");
        sb.append(this.e);
        sb.append(", ");
        sb.append(this.f);
        sb.append(']');
        return sb.toString();
    }
}
