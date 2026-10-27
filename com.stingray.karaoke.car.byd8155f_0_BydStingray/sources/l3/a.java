package L3;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.bumptech.glide.d {
    public final int d;

    public a(int i7) {
        this.d = i7;
        if (i7 <= 0) {
            throw new IllegalArgumentException("px must be > 0.");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof a) {
            if (this.d == ((a) obj).d) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.d;
    }

    public final String toString() {
        return String.valueOf(this.d);
    }
}
