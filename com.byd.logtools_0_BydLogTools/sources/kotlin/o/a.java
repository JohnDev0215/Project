package kotlin.o;

import kotlin.collections.u;

/* JADX INFO: compiled from: Progressions.kt */
/* JADX INFO: loaded from: classes.dex */
public class a implements Iterable<Integer>, kotlin.jvm.internal.n.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0095a f5131b = new C0095a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f5132c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f5133d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f5134e;

    /* JADX INFO: renamed from: kotlin.o.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Progressions.kt */
    public static final class C0095a {
        private C0095a() {
        }

        public final a a(int i, int i2, int i3) {
            return new a(i, i2, i3);
        }

        public /* synthetic */ C0095a(kotlin.jvm.internal.f fVar) {
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
        this.f5132c = i;
        this.f5133d = kotlin.m.c.b(i, i2, i3);
        this.f5134e = i3;
    }

    public final int a() {
        return this.f5132c;
    }

    public final int b() {
        return this.f5133d;
    }

    public final int c() {
        return this.f5134e;
    }

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public u iterator() {
        return new b(this.f5132c, this.f5133d, this.f5134e);
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            if (!isEmpty() || !((a) obj).isEmpty()) {
                a aVar = (a) obj;
                if (this.f5132c != aVar.f5132c || this.f5133d != aVar.f5133d || this.f5134e != aVar.f5134e) {
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
        return (((this.f5132c * 31) + this.f5133d) * 31) + this.f5134e;
    }

    public boolean isEmpty() {
        if (this.f5134e > 0) {
            if (this.f5132c > this.f5133d) {
                return true;
            }
        } else if (this.f5132c < this.f5133d) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb;
        int i;
        if (this.f5134e > 0) {
            sb = new StringBuilder();
            sb.append(this.f5132c);
            sb.append("..");
            sb.append(this.f5133d);
            sb.append(" step ");
            i = this.f5134e;
        } else {
            sb = new StringBuilder();
            sb.append(this.f5132c);
            sb.append(" downTo ");
            sb.append(this.f5133d);
            sb.append(" step ");
            i = -this.f5134e;
        }
        sb.append(i);
        return sb.toString();
    }
}
