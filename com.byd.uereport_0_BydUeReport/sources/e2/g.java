package e2;

/* JADX INFO: compiled from: MethodCollector.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f4558a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4559b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4562e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final StringBuilder f4561d = new StringBuilder();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4560c = 0;

    public g(int i8, int i9) {
        this.f4559b = i8;
        this.f4558a = i9;
        this.f4562e = i9 == 0;
    }

    public String a() {
        return this.f4561d.length() != 0 ? this.f4561d.substring(1) : "";
    }

    public void b(String str, int i8) {
        int i9 = this.f4559b;
        if (i8 < i9 || i8 >= i9 + this.f4558a) {
            return;
        }
        if (!str.equals("arg" + this.f4560c)) {
            this.f4562e = true;
        }
        this.f4561d.append(',');
        this.f4561d.append(str);
        this.f4560c++;
    }
}
