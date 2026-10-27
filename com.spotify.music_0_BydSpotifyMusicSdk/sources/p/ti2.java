package p;

/* JADX INFO: loaded from: classes.dex */
public final class ti2 implements Comparable {
    public final int c;
    public final int e;
    public final String k;
    public final String l;

    public ti2(int i, String str, int i2, String str2) {
        this.c = i;
        this.e = i2;
        this.k = str;
        this.l = str2;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        ti2 ti2Var = (ti2) obj;
        v03.j(ti2Var, "other");
        int i = this.c - ti2Var.c;
        return i == 0 ? this.e - ti2Var.e : i;
    }
}
