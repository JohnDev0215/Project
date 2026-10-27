package kotlin;

import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: renamed from: kotlin.r, reason: from Kotlin metadata */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001d\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001d\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\u000b\u001a\u001d\u0010\r\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0000¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lkotlin/Int;", "v1", "v2", "a", "(II)I", "Lkotlin/Long;", "b", "(JJ)I", "v", "Lkotlin/String;", "c", "(J)Ljava/lang/String;", "base", "d", "(JI)Ljava/lang/String;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
@JvmName(name = "UnsignedKt")
public final class Int {
    @PublishedApi
    public static final int a(int i, int i2) {
        return kotlin.jvm.internal.q.d(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
    }

    @PublishedApi
    public static final int b(long j, long j2) {
        return ((j ^ Long.MIN_VALUE) > (j2 ^ Long.MIN_VALUE) ? 1 : ((j ^ Long.MIN_VALUE) == (j2 ^ Long.MIN_VALUE) ? 0 : -1));
    }

    @NotNull
    public static final String c(long j) {
        return d(j, 10);
    }

    @NotNull
    public static final String d(long j, int i) {
        if (j >= 0) {
            kotlin.text.a.a(i);
            String string = Long.toString(j, i);
            kotlin.jvm.internal.q.b(string, "java.lang.Long.toString(this, checkRadix(radix))");
            return string;
        }
        long j2 = i;
        long j3 = ((j >>> 1) / j2) << 1;
        long j4 = j - (j3 * j2);
        if (j4 >= j2) {
            j4 -= j2;
            j3++;
        }
        StringBuilder sb = new StringBuilder();
        kotlin.text.a.a(i);
        String string2 = Long.toString(j3, i);
        kotlin.jvm.internal.q.b(string2, "java.lang.Long.toString(this, checkRadix(radix))");
        sb.append(string2);
        kotlin.text.a.a(i);
        String string3 = Long.toString(j4, i);
        kotlin.jvm.internal.q.b(string3, "java.lang.Long.toString(this, checkRadix(radix))");
        sb.append(string3);
        return sb.toString();
    }
}
