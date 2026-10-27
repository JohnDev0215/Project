package c5;

/* JADX INFO: loaded from: classes.dex */
public final class k implements InterfaceC1031h {
    public static final C1033j o = new C1033j(0);
    public final Object l = new Object();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile InterfaceC1031h f12406m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f12407n;

    public k(InterfaceC1031h interfaceC1031h) {
        this.f12406m = interfaceC1031h;
    }

    @Override // c5.InterfaceC1031h
    public final Object get() {
        InterfaceC1031h interfaceC1031h = this.f12406m;
        C1033j c1033j = o;
        if (interfaceC1031h != c1033j) {
            synchronized (this.l) {
                try {
                    if (this.f12406m != c1033j) {
                        Object obj = this.f12406m.get();
                        this.f12407n = obj;
                        this.f12406m = c1033j;
                        return obj;
                    }
                } finally {
                }
            }
        }
        return this.f12407n;
    }

    public final String toString() {
        Object obj = this.f12406m;
        StringBuilder sb = new StringBuilder("Suppliers.memoize(");
        if (obj == o) {
            obj = "<supplier that returned " + this.f12407n + ">";
        }
        sb.append(obj);
        sb.append(")");
        return sb.toString();
    }
}
