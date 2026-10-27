package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.collections.AbstractC0119j;
import kotlin.collections.AbstractC0120k;
import kotlin.collections.AbstractC0121l;
import kotlin.collections.B;
import kotlin.collections.C;
import kotlin.collections.J;
import kotlin.collections.x;
import kotlin.collections.z;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\b\u001a\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0001\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\f\u001a\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0001\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0015\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0001\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0015\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0001\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0015\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0001\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0015\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0001\u001a\u00020\u001d¢\u0006\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lkotlin/ByteArray;", "array", "Lkotlin/collections/k;", "b", "([B)Lkotlin/collections/k;", "Lkotlin/CharArray;", "Lkotlin/collections/l;", "c", "([C)Lkotlin/collections/l;", "Lkotlin/ShortArray;", "Lkotlin/collections/J;", "h", "([S)Lkotlin/collections/J;", "Lkotlin/IntArray;", "Lkotlin/collections/B;", "f", "([I)Lkotlin/collections/B;", "Lkotlin/LongArray;", "Lkotlin/collections/C;", "g", "([J)Lkotlin/collections/C;", "Lkotlin/FloatArray;", "Lkotlin/collections/z;", "e", "([F)Lkotlin/collections/z;", "Lkotlin/DoubleArray;", "Lkotlin/collections/x;", "d", "([D)Lkotlin/collections/x;", "Lkotlin/BooleanArray;", "Lkotlin/collections/j;", "a", "([Z)Lkotlin/collections/j;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final class i {
    @NotNull
    public static final AbstractC0119j a(@NotNull boolean[] zArr) {
        q.c(zArr, "array");
        return new a(zArr);
    }

    @NotNull
    public static final AbstractC0120k b(@NotNull byte[] bArr) {
        q.c(bArr, "array");
        return new b(bArr);
    }

    @NotNull
    public static final AbstractC0121l c(@NotNull char[] cArr) {
        q.c(cArr, "array");
        return new c(cArr);
    }

    @NotNull
    public static final x d(@NotNull double[] dArr) {
        q.c(dArr, "array");
        return new d(dArr);
    }

    @NotNull
    public static final z e(@NotNull float[] fArr) {
        q.c(fArr, "array");
        return new e(fArr);
    }

    @NotNull
    public static final B f(@NotNull int[] iArr) {
        q.c(iArr, "array");
        return new f(iArr);
    }

    @NotNull
    public static final C g(@NotNull long[] jArr) {
        q.c(jArr, "array");
        return new j(jArr);
    }

    @NotNull
    public static final J h(@NotNull short[] sArr) {
        q.c(sArr, "array");
        return new k(sArr);
    }
}
