package g.a.a.w2;

import g.a.a.c1;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class n extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Hashtable f4133c = new Hashtable();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Vector f4134d = new Vector();

    public n(g.a.a.r rVar) {
        Enumeration enumerationR = rVar.r();
        while (enumerationR.hasMoreElements()) {
            m mVarJ = m.j(enumerationR.nextElement());
            if (this.f4133c.containsKey(mVarJ.h())) {
                throw new IllegalArgumentException("repeated extension found: " + mVarJ.h());
            }
            this.f4133c.put(mVarJ.h(), mVarJ);
            this.f4134d.addElement(mVarJ.h());
        }
    }

    public static n h(Object obj) {
        if (obj instanceof n) {
            return (n) obj;
        }
        if (obj != null) {
            return new n(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        Enumeration enumerationElements = this.f4134d.elements();
        while (enumerationElements.hasMoreElements()) {
            fVar.a((m) this.f4133c.get((g.a.a.m) enumerationElements.nextElement()));
        }
        return new c1(fVar);
    }

    public m g(g.a.a.m mVar) {
        return (m) this.f4133c.get(mVar);
    }

    public Enumeration i() {
        return this.f4134d.elements();
    }
}
