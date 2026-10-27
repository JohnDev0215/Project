package g.a.a;

import g.a.f.a;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public abstract class t extends q implements Iterable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Vector f4052c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4053d;

    public t() {
        this.f4052c = new Vector();
        this.f4053d = false;
    }

    public t(e eVar) {
        Vector vector = new Vector();
        this.f4052c = vector;
        this.f4053d = false;
        vector.addElement(eVar);
    }

    public t(f fVar, boolean z) {
        this.f4052c = new Vector();
        this.f4053d = false;
        for (int i2 = 0; i2 != fVar.c(); i2++) {
            this.f4052c.addElement(fVar.b(i2));
        }
        if (z) {
            u();
        }
    }

    public t(e[] eVarArr, boolean z) {
        this.f4052c = new Vector();
        this.f4053d = false;
        for (int i2 = 0; i2 != eVarArr.length; i2++) {
            this.f4052c.addElement(eVarArr[i2]);
        }
        if (z) {
            u();
        }
    }

    public static t o(Object obj) {
        if (obj == null || (obj instanceof t)) {
            return (t) obj;
        }
        if (obj instanceof u) {
            return o(((u) obj).c());
        }
        if (obj instanceof byte[]) {
            try {
                return o(q.j((byte[]) obj));
            } catch (IOException e2) {
                throw new IllegalArgumentException("failed to construct set from byte[]: " + e2.getMessage());
            }
        }
        if (obj instanceof e) {
            q qVarC = ((e) obj).c();
            if (qVarC instanceof t) {
                return (t) qVarC;
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    public static t p(x xVar, boolean z) {
        if (z) {
            if (xVar.r()) {
                return (t) xVar.p();
            }
            throw new IllegalArgumentException("object implicit - explicit expected.");
        }
        q qVarP = xVar.p();
        if (xVar.r()) {
            return xVar instanceof j0 ? new h0(qVarP) : new p1(qVarP);
        }
        if (qVarP instanceof t) {
            return (t) qVarP;
        }
        if (qVarP instanceof r) {
            r rVar = (r) qVarP;
            return xVar instanceof j0 ? new h0(rVar.s()) : new p1(rVar.s());
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + xVar.getClass().getName());
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (!(qVar instanceof t)) {
            return false;
        }
        t tVar = (t) qVar;
        if (size() != tVar.size()) {
            return false;
        }
        Enumeration enumerationS = s();
        Enumeration enumerationS2 = tVar.s();
        while (enumerationS.hasMoreElements()) {
            e eVarQ = q(enumerationS);
            e eVarQ2 = q(enumerationS2);
            q qVarC = eVarQ.c();
            q qVarC2 = eVarQ2.c();
            if (qVarC != qVarC2 && !qVarC.equals(qVarC2)) {
                return false;
            }
        }
        return true;
    }

    @Override // g.a.a.l
    public int hashCode() {
        Enumeration enumerationS = s();
        int size = size();
        while (enumerationS.hasMoreElements()) {
            size = (size * 17) ^ q(enumerationS).hashCode();
        }
        return size;
    }

    @Override // java.lang.Iterable
    public Iterator<e> iterator() {
        return new a.C0097a(v());
    }

    @Override // g.a.a.q
    public boolean k() {
        return true;
    }

    @Override // g.a.a.q
    public q l() {
        if (this.f4053d) {
            e1 e1Var = new e1();
            e1Var.f4052c = this.f4052c;
            return e1Var;
        }
        Vector vector = new Vector();
        for (int i2 = 0; i2 != this.f4052c.size(); i2++) {
            vector.addElement(this.f4052c.elementAt(i2));
        }
        e1 e1Var2 = new e1();
        e1Var2.f4052c = vector;
        e1Var2.u();
        return e1Var2;
    }

    @Override // g.a.a.q
    public q m() {
        p1 p1Var = new p1();
        p1Var.f4052c = this.f4052c;
        return p1Var;
    }

    public final byte[] n(e eVar) {
        try {
            return eVar.c().f("DER");
        } catch (IOException unused) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    public final e q(Enumeration enumeration) {
        e eVar = (e) enumeration.nextElement();
        return eVar == null ? w0.f4105c : eVar;
    }

    public e r(int i2) {
        return (e) this.f4052c.elementAt(i2);
    }

    public Enumeration s() {
        return this.f4052c.elements();
    }

    public int size() {
        return this.f4052c.size();
    }

    public final boolean t(byte[] bArr, byte[] bArr2) {
        int iMin = Math.min(bArr.length, bArr2.length);
        for (int i2 = 0; i2 != iMin; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return (bArr[i2] & 255) < (bArr2[i2] & 255);
            }
        }
        return iMin == bArr.length;
    }

    public String toString() {
        return this.f4052c.toString();
    }

    public void u() {
        if (this.f4053d) {
            return;
        }
        this.f4053d = true;
        if (this.f4052c.size() > 1) {
            int size = this.f4052c.size() - 1;
            boolean z = true;
            while (z) {
                int i2 = 0;
                byte[] bArrN = n((e) this.f4052c.elementAt(0));
                z = false;
                int i3 = 0;
                while (i3 != size) {
                    int i4 = i3 + 1;
                    byte[] bArrN2 = n((e) this.f4052c.elementAt(i4));
                    if (t(bArrN, bArrN2)) {
                        bArrN = bArrN2;
                    } else {
                        Object objElementAt = this.f4052c.elementAt(i3);
                        Vector vector = this.f4052c;
                        vector.setElementAt(vector.elementAt(i4), i3);
                        this.f4052c.setElementAt(objElementAt, i4);
                        z = true;
                        i2 = i3;
                    }
                    i3 = i4;
                }
                size = i2;
            }
        }
    }

    public e[] v() {
        e[] eVarArr = new e[size()];
        for (int i2 = 0; i2 != size(); i2++) {
            eVarArr[i2] = r(i2);
        }
        return eVarArr;
    }
}
