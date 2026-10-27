package p;

/* JADX INFO: loaded from: classes.dex */
public final class gv implements c00 {
    public static final gv c = new gv();

    @Override // p.c00
    public final y10 getContext() {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    @Override // p.c00
    public final void k(Object obj) {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    public final String toString() {
        return "This continuation is already complete";
    }
}
