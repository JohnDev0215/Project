package e.a.n.h;

/* JADX INFO: compiled from: Pow2.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static int a(int i2) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i2 - 1));
    }
}
