package defpackage;

/* JADX INFO: compiled from: DirectoryOption.java */
/* JADX INFO: loaded from: classes.dex */
public final class ma0 {
    public String a;
    public final String b;
    public final long c;
    public final long d;
    public final int e;

    public ma0(String str, String str2, long j, long j2, int i) {
        this(j, i, str2, j2);
        this.a = str;
    }

    public final Object clone() {
        return new ma0(this.a, this.b, this.c, this.d, this.e);
    }

    public ma0(long j, int i, String str, long j2) {
        this.b = str;
        this.c = j;
        this.d = j2;
        this.e = i;
    }
}
