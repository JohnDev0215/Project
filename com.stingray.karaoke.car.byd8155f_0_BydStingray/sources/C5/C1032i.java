package c5;

import java.io.Serializable;

/* JADX INFO: renamed from: c5.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1032i implements InterfaceC1031h, Serializable {
    public final transient Object l = new Object();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC1031h f12404m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile transient boolean f12405n;
    public transient Object o;

    public C1032i(InterfaceC1031h interfaceC1031h) {
        this.f12404m = interfaceC1031h;
    }

    @Override // c5.InterfaceC1031h
    public final Object get() {
        if (!this.f12405n) {
            synchronized (this.l) {
                try {
                    if (!this.f12405n) {
                        Object obj = this.f12404m.get();
                        this.o = obj;
                        this.f12405n = true;
                        return obj;
                    }
                } finally {
                }
            }
        }
        return this.o;
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("Suppliers.memoize(");
        if (this.f12405n) {
            obj = "<supplier that returned " + this.o + ">";
        } else {
            obj = this.f12404m;
        }
        sb.append(obj);
        sb.append(")");
        return sb.toString();
    }
}
