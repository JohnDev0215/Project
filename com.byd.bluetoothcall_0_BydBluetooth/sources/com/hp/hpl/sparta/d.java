package com.hp.hpl.sparta;

import com.hp.hpl.sparta.xpath.XPathException;
import com.hp.hpl.sparta.xpath.c0;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class d extends f {
    private f g;
    private f h;
    private Hashtable i;
    private Vector j;
    private String k;

    d() {
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
    }

    public d(String str) {
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.k = Sparta.a(str);
    }

    private boolean w(f fVar) {
        for (f fVarB = this.g; fVarB != null; fVarB = fVarB.b()) {
            if (fVarB.equals(fVar)) {
                if (this.g == fVarB) {
                    this.g = fVarB.b();
                }
                if (this.h == fVarB) {
                    this.h = fVarB.e();
                }
                fVarB.i();
                fVarB.k(null);
                fVarB.j(null);
                return true;
            }
        }
        return false;
    }

    private r z(String str, boolean z) throws XPathException {
        c0 c0VarB = c0.b(str);
        if (c0VarB.e() == z) {
            return new r(this, c0VarB);
        }
        String str2 = z ? "evaluates to element not string" : "evaluates to string not element";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\"");
        stringBuffer.append(c0VarB);
        stringBuffer.append("\" evaluates to ");
        stringBuffer.append(str2);
        throw new XPathException(c0VarB, stringBuffer.toString());
    }

    public String A(String str) throws ParseException {
        try {
            return z(str, true).v();
        } catch (XPathException e2) {
            throw new ParseException("XPath problem", e2);
        }
    }

    @Override // com.hp.hpl.sparta.f
    protected int a() {
        int iHashCode = this.k.hashCode();
        Hashtable hashtable = this.i;
        if (hashtable != null) {
            Enumeration enumerationKeys = hashtable.keys();
            while (enumerationKeys.hasMoreElements()) {
                String str = (String) enumerationKeys.nextElement();
                iHashCode = (((iHashCode * 31) + str.hashCode()) * 31) + ((String) this.i.get(str)).hashCode();
            }
        }
        for (f fVarB = this.g; fVarB != null; fVarB = fVarB.b()) {
            iHashCode = (iHashCode * 31) + fVarB.hashCode();
        }
        return iHashCode;
    }

    @Override // com.hp.hpl.sparta.f
    public Object clone() {
        return r(true);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (!this.k.equals(dVar.k)) {
            return false;
        }
        Hashtable hashtable = this.i;
        int size = hashtable == null ? 0 : hashtable.size();
        Hashtable hashtable2 = dVar.i;
        if (size != (hashtable2 == null ? 0 : hashtable2.size())) {
            return false;
        }
        Hashtable hashtable3 = this.i;
        if (hashtable3 != null) {
            Enumeration enumerationKeys = hashtable3.keys();
            while (enumerationKeys.hasMoreElements()) {
                String str = (String) enumerationKeys.nextElement();
                if (!((String) this.i.get(str)).equals((String) dVar.i.get(str))) {
                    return false;
                }
            }
        }
        f fVarB = this.g;
        f fVarB2 = dVar.g;
        while (fVarB != null) {
            if (!fVarB.equals(fVarB2)) {
                return false;
            }
            fVarB = fVarB.b();
            fVarB2 = fVarB2.b();
        }
        return true;
    }

    @Override // com.hp.hpl.sparta.f
    void l(Writer writer) throws IOException {
        for (f fVarB = this.g; fVarB != null; fVarB = fVarB.b()) {
            fVarB.l(writer);
        }
    }

    @Override // com.hp.hpl.sparta.f
    public void n(Writer writer) throws IOException {
        String string;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<");
        stringBuffer.append(this.k);
        writer.write(stringBuffer.toString());
        Vector vector = this.j;
        if (vector != null) {
            Enumeration enumerationElements = vector.elements();
            while (enumerationElements.hasMoreElements()) {
                String str = (String) enumerationElements.nextElement();
                String str2 = (String) this.i.get(str);
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(" ");
                stringBuffer2.append(str);
                stringBuffer2.append("=\"");
                writer.write(stringBuffer2.toString());
                f.f(writer, str2);
                writer.write("\"");
            }
        }
        if (this.g == null) {
            string = "/>";
        } else {
            writer.write(">");
            for (f fVarB = this.g; fVarB != null; fVarB = fVarB.b()) {
                fVarB.n(writer);
            }
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("</");
            stringBuffer3.append(this.k);
            stringBuffer3.append(">");
            string = stringBuffer3.toString();
        }
        writer.write(string);
    }

    public void o(f fVar) {
        if (!q(fVar)) {
            fVar = (d) fVar.clone();
        }
        p(fVar);
        h();
    }

    void p(f fVar) {
        d dVarD = fVar.d();
        if (dVarD != null) {
            dVarD.w(fVar);
        }
        fVar.g(this.h);
        if (this.g == null) {
            this.g = fVar;
        }
        fVar.k(this);
        this.h = fVar;
        fVar.j(c());
    }

    boolean q(f fVar) {
        if (fVar == this) {
            return false;
        }
        d dVarD = d();
        if (dVarD == null) {
            return true;
        }
        return dVarD.q(fVar);
    }

    public d r(boolean z) {
        d dVar = new d(this.k);
        Vector vector = this.j;
        if (vector != null) {
            Enumeration enumerationElements = vector.elements();
            while (enumerationElements.hasMoreElements()) {
                String str = (String) enumerationElements.nextElement();
                dVar.x(str, (String) this.i.get(str));
            }
        }
        if (z) {
            for (f fVarB = this.g; fVarB != null; fVarB = fVarB.b()) {
                dVar.o((f) fVarB.clone());
            }
        }
        return dVar;
    }

    public String s(String str) {
        Hashtable hashtable = this.i;
        if (hashtable == null) {
            return null;
        }
        return (String) hashtable.get(str);
    }

    public f t() {
        return this.g;
    }

    public f u() {
        return this.h;
    }

    public String v() {
        return this.k;
    }

    public void x(String str, String str2) {
        if (this.i == null) {
            this.i = new Hashtable();
            this.j = new Vector();
        }
        if (this.i.get(str) == null) {
            this.j.addElement(str);
        }
        this.i.put(str, str2);
        h();
    }

    public void y(String str) {
        this.k = Sparta.a(str);
        h();
    }
}
