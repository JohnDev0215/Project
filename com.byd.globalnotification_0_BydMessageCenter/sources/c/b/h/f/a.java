package c.b.h.f;

/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2318a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f2319b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f2320c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2321d;

    public a(int i) {
        this.f2318a = i;
        Object[] objArr = new Object[i + 1];
        this.f2319b = objArr;
        this.f2320c = objArr;
    }

    public void a(T t) {
        int i = this.f2318a;
        int i2 = this.f2321d;
        if (i2 == i) {
            Object[] objArr = new Object[i + 1];
            this.f2320c[i] = objArr;
            this.f2320c = objArr;
            i2 = 0;
        }
        this.f2320c[i2] = t;
        this.f2321d = i2 + 1;
    }
}
