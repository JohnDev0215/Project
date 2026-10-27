package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.reflect.KVariance;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final /* synthetic */ class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f1519a;

    static {
        int[] iArr = new int[KVariance.values().length];
        f1519a = iArr;
        iArr[KVariance.INVARIANT.ordinal()] = 1;
        f1519a[KVariance.IN.ordinal()] = 2;
        f1519a[KVariance.OUT.ordinal()] = 3;
    }
}
