package p;

import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class lv {
    public String a = null;
    public final HashSet b;
    public final HashSet c;
    public int d;
    public final int e;
    public aw f;
    public final HashSet g;

    public lv(Class cls, Class[] clsArr) {
        HashSet hashSet = new HashSet();
        this.b = hashSet;
        this.c = new HashSet();
        this.d = 0;
        this.e = 0;
        this.g = new HashSet();
        hashSet.add(dz1.a(cls));
        for (Class cls2 : clsArr) {
            if (cls2 == null) {
                throw new NullPointerException("Null interface");
            }
            this.b.add(dz1.a(cls2));
        }
    }

    public final void a(d90 d90Var) {
        if (!(!this.b.contains(d90Var.a))) {
            throw new IllegalArgumentException("Components are not allowed to depend on interfaces they themselves provide.");
        }
        this.c.add(d90Var);
    }

    public final mv b() {
        if (this.f != null) {
            return new mv(this.a, new HashSet(this.b), new HashSet(this.c), this.d, this.e, this.f, this.g);
        }
        throw new IllegalStateException("Missing required property: factory.");
    }
}
