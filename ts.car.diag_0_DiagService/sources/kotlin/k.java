package kotlin;

import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.L;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@ExperimentalUnsignedTypes
public final class k implements Collection<j> {

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0011\b\u0016\u0012\u0006\u0010\r\u001a\u00020\b¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0000@\u0000X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u00020\u00058\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u0011"}, d2 = {"Lkotlin/k$a;", "Lkotlin/collections/L;", "Lkotlin/Boolean;", "hasNext", "()Z", "Lkotlin/Int;", "b", "()I", "Lkotlin/IntArray;", "d", "[I", "c", "I", "p0", "<init>", "([I)V", "a", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    private static final class a extends L {
        private int c;
        private final int[] d;

        public a(@NotNull int[] iArr) {
            kotlin.jvm.internal.q.c(iArr, "array");
            this.d = iArr;
        }

        @Override // kotlin.collections.L
        public int b() {
            int i = this.c;
            int[] iArr = this.d;
            if (i >= iArr.length) {
                throw new NoSuchElementException(String.valueOf(this.c));
            }
            this.c = i + 1;
            int i2 = iArr[i];
            j.d(i2);
            return i2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.c < this.d.length;
        }
    }

    @NotNull
    public static L a(int[] iArr) {
        return new a(iArr);
    }
}
