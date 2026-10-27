package b5;

/* JADX INFO: compiled from: Pow2.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static int a(int i7) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i7 - 1));
    }
}
