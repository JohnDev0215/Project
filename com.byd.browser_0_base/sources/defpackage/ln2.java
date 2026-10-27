package defpackage;

/* JADX INFO: compiled from: SingleThreadBarrierClosure.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ln2 implements Runnable {
    public int e = 2;
    public final Runnable f;

    public ln2(qm0 qm0Var) {
        this.f = qm0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        if (i == 0) {
            return;
        }
        int i2 = i - 1;
        this.e = i2;
        if (i2 == 0) {
            this.f.run();
        }
    }
}
