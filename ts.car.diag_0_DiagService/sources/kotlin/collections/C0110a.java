package kotlin.collections;

import kotlin.Metadata;

/* JADX INFO: renamed from: kotlin.collections.a, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final /* synthetic */ class C0110a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f1484a;

    static {
        int[] iArr = new int[State.values().length];
        f1484a = iArr;
        iArr[State.Done.ordinal()] = 1;
        f1484a[State.Ready.ordinal()] = 2;
    }
}
