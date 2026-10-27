package g.a.a;

import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Vector f3826a = new Vector();

    public void a(e eVar) {
        this.f3826a.addElement(eVar);
    }

    public e b(int i2) {
        return (e) this.f3826a.elementAt(i2);
    }

    public int c() {
        return this.f3826a.size();
    }
}
