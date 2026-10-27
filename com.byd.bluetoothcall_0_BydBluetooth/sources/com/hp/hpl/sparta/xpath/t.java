package com.hp.hpl.sparta.xpath;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: a */
    public static t f4210a = new t(z.f4217a, a0.f4193a);

    /* JADX INFO: renamed from: b */
    private final o f4211b;

    /* JADX INFO: renamed from: c */
    private final k f4212c;

    /* JADX INFO: renamed from: d */
    private final boolean f4213d;

    t(c0 c0Var, boolean z, s sVar) throws XPathException, IOException {
        o mVar;
        this.f4213d = z;
        int i = sVar.f4205a;
        if (i != -3) {
            if (i == 42) {
                mVar = a.f4192a;
            } else if (i != 46) {
                if (i != 64) {
                    throw new XPathException(c0Var, "at begininning of step", sVar, "'.' or '*' or name");
                }
                if (sVar.a() != -3) {
                    throw new XPathException(c0Var, "after @ in node test", sVar, "name");
                }
                mVar = new j(sVar.f4207c);
            } else if (sVar.a() == 46) {
                mVar = q.f4203a;
            } else {
                sVar.c();
                mVar = z.f4217a;
            }
        } else if (!sVar.f4207c.equals("text")) {
            mVar = new m(sVar.f4207c);
        } else {
            if (sVar.a() != 40 || sVar.a() != 41) {
                throw new XPathException(c0Var, "after text", sVar, "()");
            }
            mVar = y.f4216a;
        }
        this.f4211b = mVar;
        if (sVar.a() != 91) {
            this.f4212c = a0.f4193a;
            return;
        }
        sVar.a();
        this.f4212c = n.a(c0Var, sVar);
        if (sVar.f4205a != 93) {
            throw new XPathException(c0Var, "after predicate expression", sVar, "]");
        }
        sVar.a();
    }

    t(o oVar, k kVar) {
        this.f4211b = oVar;
        this.f4212c = kVar;
        this.f4213d = false;
    }

    public o a() {
        return this.f4211b;
    }

    public k b() {
        return this.f4212c;
    }

    public boolean c() {
        return this.f4213d;
    }

    public boolean d() {
        return this.f4211b.b();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.f4211b.toString());
        stringBuffer.append(this.f4212c.toString());
        return stringBuffer.toString();
    }
}
