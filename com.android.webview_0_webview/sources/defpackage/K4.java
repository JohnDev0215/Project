package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class K4 {
    public static final Object h = new Object();
    public final Object a;
    public C0860h1 b;
    public volatile Object c;
    public volatile Object d;
    public int e;
    public boolean f;
    public boolean g;

    public K4() {
        Object obj = h;
        this.a = new Object();
        this.b = new C0860h1();
        this.c = obj;
        this.d = obj;
        this.e = -1;
    }

    public void a(Object obj) {
        if (!Y0.b().a.a()) {
            throw new IllegalStateException("Cannot invoke setValue on a background thread");
        }
        this.e++;
        this.c = obj;
        if (this.f) {
            this.g = true;
            return;
        }
        this.f = true;
        do {
            this.g = false;
            C0860h1 c0860h1 = this.b;
            C0748f1 c0748f1 = new C0748f1(c0860h1);
            c0860h1.s.put(c0748f1, Boolean.FALSE);
            c0748f1.hasNext();
        } while (this.g);
        this.f = false;
    }
}
