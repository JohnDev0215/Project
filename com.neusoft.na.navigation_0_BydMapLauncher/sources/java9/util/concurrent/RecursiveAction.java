package java9.util.concurrent;

/* JADX INFO: loaded from: classes3.dex */
public abstract class RecursiveAction extends ForkJoinTask<Void> {
    private static final long serialVersionUID = 5232453952276485070L;

    protected abstract void compute();

    @Override // java9.util.concurrent.ForkJoinTask
    public final Void getRawResult() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java9.util.concurrent.ForkJoinTask
    public final void setRawResult(Void r1) {
    }

    @Override // java9.util.concurrent.ForkJoinTask
    protected final boolean exec() {
        compute();
        return true;
    }
}
