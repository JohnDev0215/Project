package com.hp.hpl.sparta;

import com.hp.hpl.sparta.xpath.XPathException;
import com.hp.hpl.sparta.xpath.a0;
import com.hp.hpl.sparta.xpath.b0;
import com.hp.hpl.sparta.xpath.c0;
import com.hp.hpl.sparta.xpath.t;
import com.hp.hpl.sparta.xpath.v;
import com.hp.hpl.sparta.xpath.w;
import com.hp.hpl.sparta.xpath.x;
import com.hp.hpl.sparta.xpath.y;
import com.hp.hpl.sparta.xpath.z;
import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
class r implements b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Boolean f4184a = new Boolean(true);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Boolean f4185b = new Boolean(false);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g f4186c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Vector f4187d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Enumeration f4188e;
    private Object f;
    private final a g;
    private f h;
    private boolean i;
    private c0 j;

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private C0105a f4189a;

        /* JADX INFO: renamed from: com.hp.hpl.sparta.r$a$a, reason: collision with other inner class name */
        private static class C0105a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final Boolean f4190a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final C0105a f4191b;

            C0105a(Boolean bool, C0105a c0105a) {
                this.f4190a = bool;
                this.f4191b = c0105a;
            }
        }

        private a() {
            this.f4189a = null;
        }

        /* synthetic */ a(q qVar) {
            this();
        }

        Boolean a() {
            C0105a c0105a = this.f4189a;
            Boolean bool = c0105a.f4190a;
            this.f4189a = c0105a.f4191b;
            return bool;
        }

        void b(Boolean bool) {
            this.f4189a = new C0105a(bool, this.f4189a);
        }
    }

    public r(c cVar, c0 c0Var) throws XPathException {
        this(c0Var, cVar);
    }

    public r(d dVar, c0 c0Var) throws XPathException {
        this(c0Var, dVar);
        if (c0Var.d()) {
            throw new XPathException(c0Var, "Cannot use element as context node for absolute xpath");
        }
    }

    private r(c0 c0Var, f fVar) throws XPathException {
        this.f4186c = new g();
        this.f4187d = new Vector();
        this.f4188e = null;
        this.f = null;
        this.g = new a(null);
        this.j = c0Var;
        this.h = fVar;
        Vector vector = new Vector(1);
        this.f4187d = vector;
        vector.addElement(this.h);
        Enumeration enumerationC = c0Var.c();
        while (enumerationC.hasMoreElements()) {
            t tVar = (t) enumerationC.nextElement();
            this.i = tVar.c();
            this.f4188e = null;
            tVar.a().a(this);
            this.f4188e = this.f4186c.d();
            this.f4187d.removeAllElements();
            com.hp.hpl.sparta.xpath.k kVarB = tVar.b();
            while (this.f4188e.hasMoreElements()) {
                this.f = this.f4188e.nextElement();
                kVarB.a(this);
                if (this.g.a().booleanValue()) {
                    this.f4187d.addElement(this.f);
                }
            }
        }
    }

    private void q(c cVar) {
        d dVarO = cVar.o();
        this.f4186c.a(dVarO, 1);
        if (this.i) {
            r(dVarO);
        }
    }

    private void r(d dVar) {
        int i = 0;
        for (f fVarT = dVar.t(); fVarT != null; fVarT = fVarT.b()) {
            if (fVarT instanceof d) {
                i++;
                this.f4186c.a(fVarT, i);
                if (this.i) {
                    r((d) fVarT);
                }
            }
        }
    }

    private void s(c cVar, String str) {
        d dVarO = cVar.o();
        if (dVarO == null) {
            return;
        }
        if (dVarO.v() == str) {
            this.f4186c.a(dVarO, 1);
        }
        if (this.i) {
            t(dVarO, str);
        }
    }

    private void t(d dVar, String str) {
        int i = 0;
        for (f fVarT = dVar.t(); fVarT != null; fVarT = fVarT.b()) {
            if (fVarT instanceof d) {
                d dVar2 = (d) fVarT;
                if (dVar2.v() == str) {
                    i++;
                    this.f4186c.a(dVar2, i);
                }
                if (this.i) {
                    t(dVar2, str);
                }
            }
        }
    }

    @Override // com.hp.hpl.sparta.xpath.l
    public void a(com.hp.hpl.sparta.xpath.d dVar) throws XPathException {
        Object obj = this.f;
        if (!(obj instanceof d)) {
            throw new XPathException(this.j, "Cannot test attribute of document");
        }
        String strS = ((d) obj).s(dVar.b());
        this.g.b(strS != null && strS.length() > 0 ? f4184a : f4185b);
    }

    @Override // com.hp.hpl.sparta.xpath.l
    public void b(com.hp.hpl.sparta.xpath.c cVar) throws XPathException {
        Object obj = this.f;
        if (!(obj instanceof d)) {
            throw new XPathException(this.j, "Cannot test attribute of document");
        }
        this.g.b(cVar.c().equals(((d) obj).s(cVar.b())) ? f4184a : f4185b);
    }

    @Override // com.hp.hpl.sparta.xpath.l
    public void c(a0 a0Var) {
        this.g.b(f4184a);
    }

    @Override // com.hp.hpl.sparta.xpath.p
    public void d(z zVar) {
        this.f4186c.f();
        this.f4186c.a(this.h, 1);
    }

    @Override // com.hp.hpl.sparta.xpath.l
    public void e(com.hp.hpl.sparta.xpath.f fVar) throws XPathException {
        Object obj = this.f;
        if (!(obj instanceof d)) {
            throw new XPathException(this.j, "Cannot test attribute of document");
        }
        this.g.b((((double) Long.parseLong(((d) obj).s(fVar.b()))) > fVar.c() ? 1 : (((double) Long.parseLong(((d) obj).s(fVar.b()))) == fVar.c() ? 0 : -1)) > 0 ? f4184a : f4185b);
    }

    @Override // com.hp.hpl.sparta.xpath.l
    public void f(x xVar) throws XPathException {
        a aVar;
        Boolean bool;
        Object obj = this.f;
        if (!(obj instanceof d)) {
            throw new XPathException(this.j, "Cannot test attribute of document");
        }
        f fVarT = ((d) obj).t();
        while (true) {
            if (fVarT != null) {
                if ((fVarT instanceof p) && !((p) fVarT).p().equals(xVar.b())) {
                    aVar = this.g;
                    bool = f4184a;
                    break;
                }
                fVarT = fVarT.b();
            } else {
                aVar = this.g;
                bool = f4185b;
                break;
            }
        }
        aVar.b(bool);
    }

    @Override // com.hp.hpl.sparta.xpath.l
    public void g(com.hp.hpl.sparta.xpath.g gVar) throws XPathException {
        Object obj = this.f;
        if (!(obj instanceof d)) {
            throw new XPathException(this.j, "Cannot test attribute of document");
        }
        this.g.b((((double) Long.parseLong(((d) obj).s(gVar.b()))) > gVar.c() ? 1 : (((double) Long.parseLong(((d) obj).s(gVar.b()))) == gVar.c() ? 0 : -1)) < 0 ? f4184a : f4185b);
    }

    @Override // com.hp.hpl.sparta.xpath.p
    public void h(com.hp.hpl.sparta.xpath.q qVar) throws XPathException {
        this.f4186c.f();
        d dVarD = this.h.d();
        if (dVarD == null) {
            throw new XPathException(this.j, "Illegal attempt to apply \"..\" to node with no parent.");
        }
        this.f4186c.a(dVarD, 1);
    }

    @Override // com.hp.hpl.sparta.xpath.l
    public void i(com.hp.hpl.sparta.xpath.r rVar) throws XPathException {
        Object obj = this.f;
        if (!(obj instanceof d)) {
            throw new XPathException(this.j, "Cannot test position of document");
        }
        this.g.b(this.f4186c.e((d) obj) == rVar.b() ? f4184a : f4185b);
    }

    @Override // com.hp.hpl.sparta.xpath.l
    public void j(com.hp.hpl.sparta.xpath.h hVar) throws XPathException {
        Object obj = this.f;
        if (!(obj instanceof d)) {
            throw new XPathException(this.j, "Cannot test attribute of document");
        }
        this.g.b(hVar.c().equals(((d) obj).s(hVar.b())) ^ true ? f4184a : f4185b);
    }

    @Override // com.hp.hpl.sparta.xpath.l
    public void k(v vVar) throws XPathException {
        a aVar;
        Boolean bool;
        Object obj = this.f;
        if (!(obj instanceof d)) {
            throw new XPathException(this.j, "Cannot test attribute of document");
        }
        f fVarT = ((d) obj).t();
        while (true) {
            if (fVarT != null) {
                if ((fVarT instanceof p) && ((p) fVarT).p().equals(vVar.b())) {
                    aVar = this.g;
                    bool = f4184a;
                    break;
                }
                fVarT = fVarT.b();
            } else {
                aVar = this.g;
                bool = f4185b;
                break;
            }
        }
        aVar.b(bool);
    }

    @Override // com.hp.hpl.sparta.xpath.p
    public void l(com.hp.hpl.sparta.xpath.j jVar) {
        String strS;
        Vector vector = this.f4187d;
        this.f4186c.f();
        Enumeration enumerationElements = vector.elements();
        while (enumerationElements.hasMoreElements()) {
            f fVar = (f) enumerationElements.nextElement();
            if ((fVar instanceof d) && (strS = ((d) fVar).s(jVar.c())) != null) {
                this.f4186c.b(strS);
            }
        }
    }

    @Override // com.hp.hpl.sparta.xpath.p
    public void m(com.hp.hpl.sparta.xpath.a aVar) {
        Vector vector = this.f4187d;
        this.f4186c.f();
        Enumeration enumerationElements = vector.elements();
        while (enumerationElements.hasMoreElements()) {
            Object objNextElement = enumerationElements.nextElement();
            if (objNextElement instanceof d) {
                r((d) objNextElement);
            } else if (objNextElement instanceof c) {
                q((c) objNextElement);
            }
        }
    }

    @Override // com.hp.hpl.sparta.xpath.p
    public void n(y yVar) {
        Vector vector = this.f4187d;
        this.f4186c.f();
        Enumeration enumerationElements = vector.elements();
        while (enumerationElements.hasMoreElements()) {
            Object objNextElement = enumerationElements.nextElement();
            if (objNextElement instanceof d) {
                for (f fVarT = ((d) objNextElement).t(); fVarT != null; fVarT = fVarT.b()) {
                    if (fVarT instanceof p) {
                        this.f4186c.b(((p) fVarT).p());
                    }
                }
            }
        }
    }

    @Override // com.hp.hpl.sparta.xpath.l
    public void o(w wVar) throws XPathException {
        a aVar;
        Boolean bool;
        Object obj = this.f;
        if (!(obj instanceof d)) {
            throw new XPathException(this.j, "Cannot test attribute of document");
        }
        f fVarT = ((d) obj).t();
        while (true) {
            if (fVarT == null) {
                aVar = this.g;
                bool = f4185b;
                break;
            } else {
                if (fVarT instanceof p) {
                    aVar = this.g;
                    bool = f4184a;
                    break;
                }
                fVarT = fVarT.b();
            }
        }
        aVar.b(bool);
    }

    @Override // com.hp.hpl.sparta.xpath.p
    public void p(com.hp.hpl.sparta.xpath.m mVar) {
        String strC = mVar.c();
        Vector vector = this.f4187d;
        int size = vector.size();
        this.f4186c.f();
        for (int i = 0; i < size; i++) {
            Object objElementAt = vector.elementAt(i);
            if (objElementAt instanceof d) {
                t((d) objElementAt, strC);
            } else if (objElementAt instanceof c) {
                s((c) objElementAt, strC);
            }
        }
    }

    public d u() {
        if (this.f4187d.size() == 0) {
            return null;
        }
        return (d) this.f4187d.elementAt(0);
    }

    public String v() {
        if (this.f4187d.size() == 0) {
            return null;
        }
        return this.f4187d.elementAt(0).toString();
    }
}
