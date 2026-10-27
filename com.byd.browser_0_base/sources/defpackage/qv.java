package defpackage;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class qv implements b00<Object> {
    public static final qv e = new qv();

    @Override // defpackage.b00
    public final void c(Object obj) {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    @Override // defpackage.b00
    public final m00 getContext() {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    public final String toString() {
        return "This continuation is already complete";
    }
}
