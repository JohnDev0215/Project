package kotlin.t;

import kotlin.Metadata;
import kotlin.collections.B;
import kotlin.jvm.internal.o;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class a implements Iterable<Integer> {
    public static final C0048a f = new C0048a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f1533c;
    private final int d;
    private final int e;

    /* JADX INFO: renamed from: kotlin.t.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u0000:\u0001\u0006B\t\b\u0012¢\u0006\u0004\b\b\u0010\tB\u0011\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\b\u0010\fJ%\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lkotlin/t/a$a;", "Lkotlin/Int;", "rangeStart", "rangeEnd", "step", "Lkotlin/t/a;", "a", "(III)Lkotlin/t/a;", "<init>", "()V", "Lkotlin/jvm/internal/o;", "p0", "(Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public static final class C0048a {
        private C0048a() {
        }

        @NotNull
        public final a a(int rangeStart, int rangeEnd, int step) {
            return new a(rangeStart, rangeEnd, step);
        }

        public /* synthetic */ C0048a(o oVar) {
            this();
        }
    }

    public a(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i3 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f1533c = i;
        this.d = kotlin.internal.a.b(i, i2, i3);
        this.e = i3;
    }

    public final int a() {
        return this.f1533c;
    }

    public final int b() {
        return this.d;
    }

    public final int c() {
        return this.e;
    }

    @Override // java.lang.Iterable
    @NotNull
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public B iterator() {
        return new b(this.f1533c, this.d, this.e);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof a) {
            if (!isEmpty() || !((a) obj).isEmpty()) {
                a aVar = (a) obj;
                if (this.f1533c != aVar.f1533c || this.d != aVar.d || this.e != aVar.e) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return this.e + (((this.f1533c * 31) + this.d) * 31);
    }

    public boolean isEmpty() {
        if (this.e > 0) {
            if (this.f1533c > this.d) {
                return true;
            }
        } else if (this.f1533c < this.d) {
            return true;
        }
        return false;
    }

    @NotNull
    public String toString() {
        StringBuilder sb;
        int i;
        if (this.e > 0) {
            sb = new StringBuilder();
            sb.append(this.f1533c);
            sb.append("..");
            sb.append(this.d);
            sb.append(" step ");
            i = this.e;
        } else {
            sb = new StringBuilder();
            sb.append(this.f1533c);
            sb.append(" downTo ");
            sb.append(this.d);
            sb.append(" step ");
            i = -this.e;
        }
        sb.append(i);
        return sb.toString();
    }
}
