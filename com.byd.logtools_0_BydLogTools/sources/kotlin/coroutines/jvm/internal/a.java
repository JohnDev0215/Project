package kotlin.coroutines.jvm.internal;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a implements kotlin.l.a<Object> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f5101b = new a();

    private a() {
    }

    @Override // kotlin.l.a
    public kotlin.l.c getContext() {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    @Override // kotlin.l.a
    public void resumeWith(Object obj) {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    public String toString() {
        return "This continuation is already complete";
    }
}
