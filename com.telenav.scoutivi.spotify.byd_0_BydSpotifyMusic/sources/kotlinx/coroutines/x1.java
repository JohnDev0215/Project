package kotlinx.coroutines;

/* JADX INFO: compiled from: Job.kt */
/* JADX INFO: loaded from: classes.dex */
public final class x1 implements v0, p {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final x1 f7849e = new x1();

    private x1() {
    }

    @Override // kotlinx.coroutines.v0
    public void dispose() {
    }

    @Override // kotlinx.coroutines.p
    public boolean k(Throwable th) {
        return false;
    }

    public String toString() {
        return "NonDisposableHandle";
    }
}
