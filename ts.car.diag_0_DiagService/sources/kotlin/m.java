package kotlin;

import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.M;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@ExperimentalUnsignedTypes
public final class m implements Collection<l> {

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0011\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\b¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0000@\u0000X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lkotlin/m$a;", "Lkotlin/collections/M;", "Lkotlin/Boolean;", "hasNext", "()Z", "Lkotlin/Long;", "b", "()J", "Lkotlin/LongArray;", "d", "[J", "Lkotlin/Int;", "c", "I", "p0", "<init>", "([J)V", "a", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    private static final class a extends M {
        private int c;
        private final long[] d;

        public a(@NotNull long[] jArr) {
            kotlin.jvm.internal.q.c(jArr, "array");
            this.d = jArr;
        }

        @Override // kotlin.collections.M
        public long b() {
            int i = this.c;
            long[] jArr = this.d;
            if (i >= jArr.length) {
                throw new NoSuchElementException(String.valueOf(this.c));
            }
            this.c = i + 1;
            long j = jArr[i];
            l.d(j);
            return j;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.c < this.d.length;
        }
    }

    @NotNull
    public static M a(long[] jArr) {
        return new a(jArr);
    }
}
