package kotlin;

import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0087@\u0018\u0000 \u00152\u00020\u0001B\u0011\b\u0012\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0012¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0006\u001a\u00020\u00028\u0000@\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0012ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lkotlin/h;", "Lkotlin/Comparable;", "Lkotlin/Byte;", "other", "p1", "Lkotlin/Int;", "c", "(BB)I", "p0", "Lkotlin/String;", "g", "(B)Ljava/lang/String;", "hashCode", "()I", "Lkotlin/Any;", "Lkotlin/Boolean;", "equals", "(Ljava/lang/Object;)Z", "B", "<init>", "(B)V", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
@ExperimentalUnsignedTypes
public final class h implements Comparable<h> {
    private final byte c;

    @PublishedApi
    private /* synthetic */ h(byte b2) {
        this.c = b2;
    }

    @NotNull
    public static final /* synthetic */ h a(byte b2) {
        return new h(b2);
    }

    @InlineOnly
    private int b(byte b2) {
        return c(this.c, b2);
    }

    @InlineOnly
    private static int c(byte other, byte p1) {
        return kotlin.jvm.internal.q.d(other & 255, p1 & 255);
    }

    @PublishedApi
    public static byte d(byte b2) {
        return b2;
    }

    public static boolean e(byte b2, @Nullable Object obj) {
        if (obj instanceof h) {
            if (b2 == ((h) obj).getC()) {
                return true;
            }
        }
        return false;
    }

    public static int f(byte b2) {
        return b2;
    }

    @NotNull
    public static String g(byte p0) {
        return String.valueOf(p0 & 255);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(h hVar) {
        return b(hVar.getC());
    }

    public boolean equals(Object other) {
        return e(this.c, other);
    }

    /* JADX INFO: renamed from: h, reason: from getter */
    public final /* synthetic */ byte getC() {
        return this.c;
    }

    public int hashCode() {
        byte b2 = this.c;
        f(b2);
        return b2;
    }

    @NotNull
    public String toString() {
        return g(this.c);
    }
}
