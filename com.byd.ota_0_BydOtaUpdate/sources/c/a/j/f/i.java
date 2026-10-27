package c.a.j.f;

/* JADX INFO: compiled from: UpgradeConfigSignInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2867a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f2868b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f2869c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f2870d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2871e;

    public void a() {
        this.f2870d = "";
        this.f2867a = "";
        this.f2868b = "";
        this.f2871e = "";
        this.f2869c = "";
    }

    public String b() {
        return this.f2868b;
    }

    public String c() {
        return this.f2871e;
    }

    public void d(String str) {
        this.f2867a = str;
    }

    public void e(String str) {
        this.f2869c = str;
    }

    public void f(String str) {
        this.f2868b = str;
    }

    public void g(String str) {
        this.f2871e = str;
    }

    public String toString() {
        return "Sign{data='" + this.f2867a + "', signature='" + this.f2868b + "', issuer='" + this.f2869c + "', algorithm='" + this.f2870d + "', signer='" + this.f2871e + "'}";
    }
}
