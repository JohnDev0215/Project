package z5;

import java.text.DecimalFormat;

/* JADX INFO: compiled from: DurationJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f10054a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final ThreadLocal<DecimalFormat>[] f10055b;

    static {
        ThreadLocal<DecimalFormat>[] threadLocalArr = new ThreadLocal[4];
        for (int i7 = 0; i7 < 4; i7++) {
            threadLocalArr[i7] = new ThreadLocal<>();
        }
        f10055b = threadLocalArr;
    }

    public static final boolean a() {
        return f10054a;
    }
}
