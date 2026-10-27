package defpackage;

/* JADX INFO: compiled from: CachedFieldTrialParameter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class rk {
    public final String a;
    public final String b;

    public rk(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public abstract void a();

    public final String b() {
        return ar.d.b(this.a + ":" + this.b);
    }
}
