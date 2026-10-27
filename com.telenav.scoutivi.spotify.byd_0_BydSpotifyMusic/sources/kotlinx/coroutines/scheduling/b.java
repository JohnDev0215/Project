package kotlinx.coroutines.scheduling;

import kotlinx.coroutines.scheduling.a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f7761a;

    static {
        int[] iArr = new int[a.c.values().length];
        f7761a = iArr;
        iArr[a.c.PARKING.ordinal()] = 1;
        iArr[a.c.BLOCKING.ordinal()] = 2;
        iArr[a.c.CPU_ACQUIRED.ordinal()] = 3;
        iArr[a.c.DORMANT.ordinal()] = 4;
        iArr[a.c.TERMINATED.ordinal()] = 5;
    }
}
