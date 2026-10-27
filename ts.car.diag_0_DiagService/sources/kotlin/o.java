package kotlin;

import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0087@\u0018\u0000 \u00152\u00020\u0001B\u0011\b\u0012\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0012¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0006\u001a\u00020\u00028\u0000@\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0012ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lkotlin/o;", "Lkotlin/Comparable;", "Lkotlin/Short;", "other", "p1", "Lkotlin/Int;", "c", "(SS)I", "p0", "Lkotlin/String;", "g", "(S)Ljava/lang/String;", "hashCode", "()I", "Lkotlin/Any;", "Lkotlin/Boolean;", "equals", "(Ljava/lang/Object;)Z", "S", "<init>", "(S)V", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
@ExperimentalUnsignedTypes
public final class o implements Comparable<o> {
    private final short c;

    @PublishedApi
    private /* synthetic */ o(short s) {
        this.c = s;
    }

    @NotNull
    public static final /* synthetic */ o a(short s) {
        return new o(s);
    }

    @InlineOnly
    private int b(short s) {
        return c(this.c, s);
    }

    @InlineOnly
    private static int c(short other, short p1) {
        return kotlin.jvm.internal.q.d(other & 65535, p1 & 65535);
    }

    @PublishedApi
    public static short d(short s) {
        return s;
    }

    public static boolean e(short s, @Nullable Object obj) {
        if (obj instanceof o) {
            if (s == ((o) obj).getC()) {
                return true;
            }
        }
        return false;
    }

    public static int f(short s) {
        return s;
    }

    @NotNull
    public static String g(short p0) {
        return String.valueOf(p0 & 65535);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(o oVar) {
        return b(oVar.getC());
    }

    public boolean equals(Object other) {
        return e(this.c, other);
    }

    /* JADX INFO: renamed from: h, reason: from getter */
    public final /* synthetic */ short getC() {
        return this.c;
    }

    public int hashCode() {
        short s = this.c;
        f(s);
        return s;
    }

    @NotNull
    public String toString() {
        return g(this.c);
    }
}
