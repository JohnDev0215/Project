package g.a.a;

import g.a.f.a;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public abstract class r extends q implements Iterable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Vector f4011c;

    public r() {
        this.f4011c = new Vector();
    }

    public r(e eVar) {
        Vector vector = new Vector();
        this.f4011c = vector;
        vector.addElement(eVar);
    }

    public r(f fVar) {
        this.f4011c = new Vector();
        for (int i2 = 0; i2 != fVar.c(); i2++) {
            this.f4011c.addElement(fVar.b(i2));
        }
    }

    public r(e[] eVarArr) {
        this.f4011c = new Vector();
        for (int i2 = 0; i2 != eVarArr.length; i2++) {
            this.f4011c.addElement(eVarArr[i2]);
        }
    }

    public static r n(Object obj) {
        if (obj == null || (obj instanceof r)) {
            return (r) obj;
        }
        if (obj instanceof s) {
            return n(((s) obj).c());
        }
        if (obj instanceof byte[]) {
            try {
                return n(q.j((byte[]) obj));
            } catch (IOException e2) {
                throw new IllegalArgumentException("failed to construct sequence from byte[]: " + e2.getMessage());
            }
        }
        if (obj instanceof e) {
            q qVarC = ((e) obj).c();
            if (qVarC instanceof r) {
                return (r) qVarC;
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    public static r o(x xVar, boolean z) {
        if (z) {
            if (xVar.r()) {
                return n(xVar.p().c());
            }
            throw new IllegalArgumentException("object implicit - explicit expected.");
        }
        q qVarP = xVar.p();
        if (xVar.r()) {
            return xVar instanceof j0 ? new f0(qVarP) : new o1(qVarP);
        }
        if (qVarP instanceof r) {
            return (r) qVarP;
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + xVar.getClass().getName());
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (!(qVar instanceof r)) {
            return false;
        }
        r rVar = (r) qVar;
        if (size() != rVar.size()) {
            return false;
        }
        Enumeration enumerationR = r();
        Enumeration enumerationR2 = rVar.r();
        while (enumerationR.hasMoreElements()) {
            e eVarP = p(enumerationR);
            e eVarP2 = p(enumerationR2);
            q qVarC = eVarP.c();
            q qVarC2 = eVarP2.c();
            if (qVarC != qVarC2 && !qVarC.equals(qVarC2)) {
                return false;
            }
        }
        return true;
    }

    @Override // g.a.a.l
    public int hashCode() {
        Enumeration enumerationR = r();
        int size = size();
        while (enumerationR.hasMoreElements()) {
            size = (size * 17) ^ p(enumerationR).hashCode();
        }
        return size;
    }

    @Override // java.lang.Iterable
    public Iterator<e> iterator() {
        return new a.C0097a(s());
    }

    @Override // g.a.a.q
    public boolean k() {
        return true;
    }

    @Override // g.a.a.q
    public q l() {
        c1 c1Var = new c1();
        c1Var.f4011c = this.f4011c;
        return c1Var;
    }

    @Override // g.a.a.q
    public q m() {
        o1 o1Var = new o1();
        o1Var.f4011c = this.f4011c;
        return o1Var;
    }

    public final e p(Enumeration enumeration) {
        return (e) enumeration.nextElement();
    }

    public e q(int i2) {
        return (e) this.f4011c.elementAt(i2);
    }

    public Enumeration r() {
        return this.f4011c.elements();
    }

    public e[] s() {
        e[] eVarArr = new e[size()];
        for (int i2 = 0; i2 != size(); i2++) {
            eVarArr[i2] = q(i2);
        }
        return eVarArr;
    }

    public int size() {
        return this.f4011c.size();
    }

    public String toString() {
        return this.f4011c.toString();
    }
}
