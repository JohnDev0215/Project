package G7;

import n7.z;

/* JADX INFO: loaded from: classes.dex */
public class b implements Iterable, B7.a {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f2712m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f2713n;

    public b(int i7, int i10, int i11) {
        if (i11 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i11 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.l = i7;
        this.f2712m = z.n(i7, i10, i11);
        this.f2713n = i11;
    }

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final c iterator() {
        return new c(this.l, this.f2712m, this.f2713n);
    }

    public boolean equals(Object obj) {
        if (obj instanceof b) {
            if (!isEmpty() || !((b) obj).isEmpty()) {
                b bVar = (b) obj;
                if (this.l != bVar.l || this.f2712m != bVar.f2712m || this.f2713n != bVar.f2713n) {
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
        return this.f2713n + (((this.l * 31) + this.f2712m) * 31);
    }

    public boolean isEmpty() {
        int i7 = this.f2713n;
        int i10 = this.f2712m;
        int i11 = this.l;
        if (i7 > 0) {
            if (i11 <= i10) {
                return false;
            }
        } else if (i11 >= i10) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb;
        int i7 = this.f2712m;
        int i10 = this.l;
        int i11 = this.f2713n;
        if (i11 > 0) {
            sb = new StringBuilder();
            sb.append(i10);
            sb.append("..");
            sb.append(i7);
            sb.append(" step ");
        } else {
            sb = new StringBuilder();
            sb.append(i10);
            sb.append(" downTo ");
            sb.append(i7);
            sb.append(" step ");
            i11 = -i11;
        }
        sb.append(i11);
        return sb.toString();
    }
}
