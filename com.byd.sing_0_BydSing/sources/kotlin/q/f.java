package kotlin.q;

import kotlin.collections.u;

/* JADX INFO: compiled from: Progressions.kt */
/* JADX INFO: loaded from: classes.dex */
public class f implements Iterable<Integer> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f4097b = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4098c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f4099d;
    private final int e;

    /* JADX INFO: compiled from: Progressions.kt */
    public static final class a {
        private a() {
        }

        public final f a(int i, int i2, int i3) {
            return new f(i, i2, i3);
        }

        public /* synthetic */ a(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    public f(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i3 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f4098c = i;
        this.f4099d = kotlin.o.a.c(i, i2, i3);
        this.e = i3;
    }

    public final int a() {
        return this.f4098c;
    }

    public final int b() {
        return this.f4099d;
    }

    public final int c() {
        return this.e;
    }

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public u iterator() {
        return new g(this.f4098c, this.f4099d, this.e);
    }

    public boolean equals(Object obj) {
        if (obj instanceof f) {
            if (!isEmpty() || !((f) obj).isEmpty()) {
                f fVar = (f) obj;
                if (this.f4098c != fVar.f4098c || this.f4099d != fVar.f4099d || this.e != fVar.e) {
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
        return (((this.f4098c * 31) + this.f4099d) * 31) + this.e;
    }

    public boolean isEmpty() {
        if (this.e > 0) {
            if (this.f4098c > this.f4099d) {
                return true;
            }
        } else if (this.f4098c < this.f4099d) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb;
        int i;
        if (this.e > 0) {
            sb = new StringBuilder();
            sb.append(this.f4098c);
            sb.append("..");
            sb.append(this.f4099d);
            sb.append(" step ");
            i = this.e;
        } else {
            sb = new StringBuilder();
            sb.append(this.f4098c);
            sb.append(" downTo ");
            sb.append(this.f4099d);
            sb.append(" step ");
            i = -this.e;
        }
        sb.append(i);
        return sb.toString();
    }
}
