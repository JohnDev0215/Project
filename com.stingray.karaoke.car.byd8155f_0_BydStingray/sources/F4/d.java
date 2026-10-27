package F4;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        Float fValueOf = Float.valueOf(0.0f);
        ((d) obj).getClass();
        return fValueOf.equals(Float.valueOf(0.0f)) && Float.valueOf(0.0f).equals(Float.valueOf(0.0f));
    }

    public final int hashCode() {
        return Float.hashCode(0.0f) + (Float.hashCode(0.0f) * 31);
    }

    public final String toString() {
        return "QrOffset(x=0.0, y=0.0)";
    }
}
