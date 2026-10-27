package kotlin;

import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0087@\u0018\u0000 \u00152\u00020\u0001B\u0011\b\u0012\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0012¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0006\u001a\u00020\u00028\u0000@\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0012ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lkotlin/l;", "Lkotlin/Comparable;", "Lkotlin/Long;", "other", "p1", "Lkotlin/Int;", "c", "(JJ)I", "p0", "Lkotlin/String;", "g", "(J)Ljava/lang/String;", "hashCode", "()I", "Lkotlin/Any;", "Lkotlin/Boolean;", "equals", "(Ljava/lang/Object;)Z", "J", "<init>", "(J)V", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
@ExperimentalUnsignedTypes
public final class l implements Comparable<l> {
    private final long c;

    @PublishedApi
    private /* synthetic */ l(long j) {
        this.c = j;
    }

    @NotNull
    public static final /* synthetic */ l a(long j) {
        return new l(j);
    }

    @InlineOnly
    private int b(long j) {
        return c(this.c, j);
    }

    @InlineOnly
    private static int c(long other, long p1) {
        return Int.b(other, p1);
    }

    @PublishedApi
    public static long d(long j) {
        return j;
    }

    public static boolean e(long j, @Nullable Object obj) {
        if (obj instanceof l) {
            if (j == ((l) obj).getC()) {
                return true;
            }
        }
        return false;
    }

    public static int f(long j) {
        return (int) (j ^ (j >>> 32));
    }

    @NotNull
    public static String g(long p0) {
        return Int.c(p0);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(l lVar) {
        return b(lVar.getC());
    }

    public boolean equals(Object other) {
        return e(this.c, other);
    }

    /* JADX INFO: renamed from: h, reason: from getter */
    public final /* synthetic */ long getC() {
        return this.c;
    }

    public int hashCode() {
        return f(this.c);
    }

    @NotNull
    public String toString() {
        return g(this.c);
    }
}
