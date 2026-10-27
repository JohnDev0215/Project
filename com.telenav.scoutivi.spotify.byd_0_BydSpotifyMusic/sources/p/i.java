package p;

/* JADX INFO: compiled from: Schlick.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    double f8762d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    double f8763e;

    i(String str) {
        this.f8707a = str;
        int iIndexOf = str.indexOf(40);
        int iIndexOf2 = str.indexOf(44, iIndexOf);
        this.f8762d = Double.parseDouble(str.substring(iIndexOf + 1, iIndexOf2).trim());
        int i7 = iIndexOf2 + 1;
        this.f8763e = Double.parseDouble(str.substring(i7, str.indexOf(44, i7)).trim());
    }

    private double d(double d7) {
        double d8 = this.f8763e;
        if (d7 < d8) {
            double d9 = this.f8762d;
            return ((d9 * d8) * d8) / ((((d8 - d7) * d9) + d7) * ((d9 * (d8 - d7)) + d7));
        }
        double d10 = this.f8762d;
        return (((d8 - 1.0d) * d10) * (d8 - 1.0d)) / (((((-d10) * (d8 - d7)) - d7) + 1.0d) * ((((-d10) * (d8 - d7)) - d7) + 1.0d));
    }

    private double e(double d7) {
        double d8 = this.f8763e;
        return d7 < d8 ? (d8 * d7) / (d7 + (this.f8762d * (d8 - d7))) : ((1.0d - d8) * (d7 - 1.0d)) / ((1.0d - d7) - (this.f8762d * (d8 - d7)));
    }

    @Override // p.c
    public double a(double d7) {
        return e(d7);
    }

    @Override // p.c
    public double b(double d7) {
        return d(d7);
    }
}
