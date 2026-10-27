package kotlin;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f4065a;

    static {
        int[] iArr = new int[LazyThreadSafetyMode.values().length];
        f4065a = iArr;
        iArr[LazyThreadSafetyMode.SYNCHRONIZED.ordinal()] = 1;
        iArr[LazyThreadSafetyMode.PUBLICATION.ordinal()] = 2;
        iArr[LazyThreadSafetyMode.NONE.ordinal()] = 3;
    }
}
