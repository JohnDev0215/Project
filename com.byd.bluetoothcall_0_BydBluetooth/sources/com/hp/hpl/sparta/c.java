package com.hp.hpl.sparta;

import com.hp.hpl.sparta.Sparta;
import com.hp.hpl.sparta.xpath.XPathException;
import com.hp.hpl.sparta.xpath.c0;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class c extends f {
    private static final Integer g = new Integer(1);
    static final Enumeration h = new e();
    private d i;
    private String j;
    private Sparta.a k;
    private Vector l;
    private final Hashtable m;

    public interface a {
        void a(c cVar);
    }

    public c() {
        this.i = null;
        this.k = Sparta.b();
        this.l = new Vector();
        this.m = null;
        this.j = "MEMORY";
    }

    c(String str) {
        this.i = null;
        this.k = Sparta.b();
        this.l = new Vector();
        this.m = null;
        this.j = str;
    }

    @Override // com.hp.hpl.sparta.f
    protected int a() {
        return this.i.hashCode();
    }

    @Override // com.hp.hpl.sparta.f
    public Object clone() {
        c cVar = new c(this.j);
        cVar.i = (d) this.i.clone();
        return cVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            return this.i.equals(((c) obj).i);
        }
        return false;
    }

    @Override // com.hp.hpl.sparta.f
    void h() {
        Enumeration enumerationElements = this.l.elements();
        while (enumerationElements.hasMoreElements()) {
            ((a) enumerationElements.nextElement()).a(this);
        }
    }

    @Override // com.hp.hpl.sparta.f
    public void l(Writer writer) throws IOException {
        this.i.l(writer);
    }

    @Override // com.hp.hpl.sparta.f
    public void n(Writer writer) throws IOException {
        writer.write("<?xml version=\"1.0\" ?>\n");
        this.i.n(writer);
    }

    public d o() {
        return this.i;
    }

    void p(c0 c0Var) throws XPathException {
    }

    public void q(d dVar) {
        this.i = dVar;
        dVar.j(this);
        h();
    }

    public void r(String str) {
        this.j = str;
        h();
    }

    r s(c0 c0Var, boolean z) throws XPathException {
        if (c0Var.e() == z) {
            return new r(this, c0Var);
        }
        String str = z ? "evaluates to element not string" : "evaluates to string not element";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\"");
        stringBuffer.append(c0Var);
        stringBuffer.append("\" evaluates to ");
        stringBuffer.append(str);
        throw new XPathException(c0Var, stringBuffer.toString());
    }

    public d t(String str) throws ParseException {
        try {
            if (str.charAt(0) != '/') {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("/");
                stringBuffer.append(str);
                str = stringBuffer.toString();
            }
            c0 c0VarB = c0.b(str);
            p(c0VarB);
            return s(c0VarB, false).u();
        } catch (XPathException e2) {
            throw new ParseException("XPath problem", e2);
        }
    }

    @Override // com.hp.hpl.sparta.f
    public String toString() {
        return this.j;
    }
}
