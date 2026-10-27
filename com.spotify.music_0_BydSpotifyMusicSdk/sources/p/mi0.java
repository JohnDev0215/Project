package p;

/* JADX INFO: loaded from: classes.dex */
public final class mi0 extends ni0 {
    public final Runnable k;

    public mi0(ul2 ul2Var, long j) {
        super(j);
        this.k = ul2Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.k.run();
    }

    @Override // p.ni0
    public final String toString() {
        return super.toString() + this.k;
    }
}
