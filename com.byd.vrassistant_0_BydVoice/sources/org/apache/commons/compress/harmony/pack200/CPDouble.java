package org.apache.commons.compress.harmony.pack200;

/* JADX INFO: loaded from: classes4.dex */
public class CPDouble extends CPConstant {
    private final double theDouble;

    public CPDouble(double d) {
        this.theDouble = d;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return Double.compare(this.theDouble, ((CPDouble) obj).theDouble);
    }

    public double getDouble() {
        return this.theDouble;
    }
}
