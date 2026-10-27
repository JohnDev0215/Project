package kotlin.coroutines.jvm.internal;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c implements k5.d<Object> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f7484e = new c();

    private c() {
    }

    @Override // k5.d
    public k5.g getContext() {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    @Override // k5.d
    public void resumeWith(Object obj) {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    public String toString() {
        return "This continuation is already complete";
    }
}
