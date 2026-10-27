package Z8;

import S8.AbstractC0727y;

/* JADX INFO: loaded from: classes.dex */
public final class j extends i {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Runnable f11014n;

    public j(Runnable runnable, long j3, boolean z6) {
        super(j3, z6);
        this.f11014n = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f11014n.run();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Task[");
        Runnable runnable = this.f11014n;
        sb.append(runnable.getClass().getSimpleName());
        sb.append('@');
        sb.append(AbstractC0727y.n(runnable));
        sb.append(", ");
        sb.append(this.l);
        sb.append(", ");
        return android.hardware.bydauto.ac.tsimpl.a.k(sb, this.f11013m ? "Blocking" : "Non-blocking", ']');
    }
}
