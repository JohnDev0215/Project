package l5;

/* JADX INFO: loaded from: classes.dex */
public final class j extends Number {
    public final String l;

    public j(String str) {
        this.l = str;
    }

    @Override // java.lang.Number
    public final double doubleValue() {
        return Double.parseDouble(this.l);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            return this.l.equals(((j) obj).l);
        }
        return false;
    }

    @Override // java.lang.Number
    public final float floatValue() {
        return Float.parseFloat(this.l);
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    @Override // java.lang.Number
    public final int intValue() {
        String str = this.l;
        try {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                return (int) Long.parseLong(str);
            }
        } catch (NumberFormatException unused2) {
            return AbstractC1776d.i(str).intValue();
        }
    }

    @Override // java.lang.Number
    public final long longValue() {
        String str = this.l;
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return AbstractC1776d.i(str).longValue();
        }
    }

    public final String toString() {
        return this.l;
    }
}
