package kotlin;

import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0087@\u0018\u0000 \u00142\u00020\u0001B\u0011\b\u0012\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0012¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0005\u001a\u00020\u00028\u0000@\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0011ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lkotlin/j;", "Lkotlin/Comparable;", "Lkotlin/Int;", "other", "p1", "c", "(II)I", "p0", "Lkotlin/String;", "g", "(I)Ljava/lang/String;", "hashCode", "()I", "Lkotlin/Any;", "Lkotlin/Boolean;", "equals", "(Ljava/lang/Object;)Z", "I", "<init>", "(I)V", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
@ExperimentalUnsignedTypes
public final class j implements Comparable<j> {
    private final int c;

    @PublishedApi
    private /* synthetic */ j(int i) {
        this.c = i;
    }

    @NotNull
    public static final /* synthetic */ j a(int i) {
        return new j(i);
    }

    @InlineOnly
    private int b(int i) {
        return c(this.c, i);
    }

    @InlineOnly
    private static int c(int other, int p1) {
        return Int.a(other, p1);
    }

    @PublishedApi
    public static int d(int i) {
        return i;
    }

    public static boolean e(int i, @Nullable Object obj) {
        if (obj instanceof j) {
            if (i == ((j) obj).getC()) {
                return true;
            }
        }
        return false;
    }

    public static int f(int i) {
        return i;
    }

    @NotNull
    public static String g(int p0) {
        return String.valueOf(((long) p0) & 4294967295L);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(j jVar) {
        return b(jVar.getC());
    }

    public boolean equals(Object other) {
        return e(this.c, other);
    }

    /* JADX INFO: renamed from: h, reason: from getter */
    public final /* synthetic */ int getC() {
        return this.c;
    }

    public int hashCode() {
        int i = this.c;
        f(i);
        return i;
    }

    @NotNull
    public String toString() {
        return g(this.c);
    }
}
