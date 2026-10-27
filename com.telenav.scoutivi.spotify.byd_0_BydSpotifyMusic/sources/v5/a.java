package v5;

import h5.d0;

/* JADX INFO: compiled from: Progressions.kt */
/* JADX INFO: loaded from: classes.dex */
public class a implements Iterable<Integer>, s5.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final C0166a f9683i = new C0166a(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f9684e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f9685g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f9686h;

    /* JADX INFO: renamed from: v5.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Progressions.kt */
    public static final class C0166a {
        private C0166a() {
        }

        public /* synthetic */ C0166a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final a a(int i7, int i8, int i9) {
            return new a(i7, i8, i9);
        }
    }

    public a(int i7, int i8, int i9) {
        if (i9 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i9 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f9684e = i7;
        this.f9685g = m5.c.b(i7, i8, i9);
        this.f9686h = i9;
    }

    public final int c() {
        return this.f9684e;
    }

    public final int d() {
        return this.f9685g;
    }

    public final int e() {
        return this.f9686h;
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            if (!isEmpty() || !((a) obj).isEmpty()) {
                a aVar = (a) obj;
                if (this.f9684e != aVar.f9684e || this.f9685g != aVar.f9685g || this.f9686h != aVar.f9686h) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public d0 iterator() {
        return new b(this.f9684e, this.f9685g, this.f9686h);
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.f9684e * 31) + this.f9685g) * 31) + this.f9686h;
    }

    public boolean isEmpty() {
        if (this.f9686h > 0) {
            if (this.f9684e > this.f9685g) {
                return true;
            }
        } else if (this.f9684e < this.f9685g) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb;
        int i7;
        if (this.f9686h > 0) {
            sb = new StringBuilder();
            sb.append(this.f9684e);
            sb.append("..");
            sb.append(this.f9685g);
            sb.append(" step ");
            i7 = this.f9686h;
        } else {
            sb = new StringBuilder();
            sb.append(this.f9684e);
            sb.append(" downTo ");
            sb.append(this.f9685g);
            sb.append(" step ");
            i7 = -this.f9686h;
        }
        sb.append(i7);
        return sb.toString();
    }
}
