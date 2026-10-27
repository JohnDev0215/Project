package defpackage;

/* JADX INFO: compiled from: Schlick.java */
/* JADX INFO: loaded from: classes.dex */
public final class me2 extends hf0 {
    public final double d;
    public final double e;

    public me2(String str) {
        this.a = str;
        int iIndexOf = str.indexOf(40);
        int iIndexOf2 = str.indexOf(44, iIndexOf);
        this.d = Double.parseDouble(str.substring(iIndexOf + 1, iIndexOf2).trim());
        int i = iIndexOf2 + 1;
        this.e = Double.parseDouble(str.substring(i, str.indexOf(44, i)).trim());
    }

    @Override // defpackage.hf0
    public final double a(double d) {
        double d2 = this.e;
        double d3 = this.d;
        if (d < d2) {
            return (d2 * d) / (((d2 - d) * d3) + d);
        }
        return ((d - 1.0d) * (1.0d - d2)) / ((1.0d - d) - ((d2 - d) * d3));
    }

    @Override // defpackage.hf0
    public final double b(double d) {
        double d2 = this.e;
        double d3 = this.d;
        if (d < d2) {
            double d4 = d3 * d2 * d2;
            double d5 = ((d2 - d) * d3) + d;
            return d4 / (d5 * d5);
        }
        double d6 = d2 - 1.0d;
        double d7 = (((d2 - d) * (-d3)) - d) + 1.0d;
        return ((d6 * d3) * d6) / (d7 * d7);
    }
}
