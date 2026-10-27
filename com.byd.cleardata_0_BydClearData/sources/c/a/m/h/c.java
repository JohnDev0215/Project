package c.a.m.h;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static int a(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }
}
