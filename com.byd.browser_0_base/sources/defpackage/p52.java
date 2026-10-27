package defpackage;

/* JADX INFO: compiled from: ProcessInitException.java */
/* JADX INFO: loaded from: classes.dex */
public final class p52 extends RuntimeException {
    public p52(int i) {
        super(gq2.e("errorCode=", i));
    }

    public p52(int i, Throwable th) {
        super(gq2.e("errorCode=", i), th);
    }
}
