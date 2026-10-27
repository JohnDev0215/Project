package b.a.a.i;

/* JADX INFO: compiled from: MethodCollector.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f212a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f213b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f216e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final StringBuilder f215d = new StringBuilder();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f214c = 0;

    public g(int i, int i2) {
        this.f213b = i;
        this.f212a = i2;
        this.f216e = i2 == 0;
    }

    public String a() {
        return this.f215d.length() != 0 ? this.f215d.substring(1) : "";
    }

    public void b(String str, int i) {
        int i2 = this.f213b;
        if (i < i2 || i >= i2 + this.f212a) {
            return;
        }
        if (!str.equals("arg" + this.f214c)) {
            this.f216e = true;
        }
        this.f215d.append(',');
        this.f215d.append(str);
        this.f214c++;
    }
}
