package defpackage;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public final class Z0 extends W0 {
    public final Runnable e;

    public Z0(Runnable runnable, long j, X0 x0) {
        super(j, x0);
        this.e = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.e.run();
        } finally {
            this.d.getClass();
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Task[");
        Runnable runnable = this.e;
        sb.append(runnable.getClass().getSimpleName());
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(runnable)));
        sb.append(", ");
        sb.append(this.c);
        sb.append(", ");
        sb.append(this.d);
        sb.append(']');
        return sb.toString();
    }
}
