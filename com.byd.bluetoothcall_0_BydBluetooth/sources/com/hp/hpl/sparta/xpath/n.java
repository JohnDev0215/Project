package com.hp.hpl.sparta.xpath;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class n {
    static k a(c0 c0Var, s sVar) throws XPathException, IOException {
        int i;
        int i2;
        int i3 = sVar.f4205a;
        if (i3 == -3) {
            if (!sVar.f4207c.equals("text")) {
                throw new XPathException(c0Var, "at beginning of expression", sVar, "text()");
            }
            if (sVar.a() != 40) {
                throw new XPathException(c0Var, "after text", sVar, "(");
            }
            if (sVar.a() != 41) {
                throw new XPathException(c0Var, "after text(", sVar, ")");
            }
            int iA = sVar.a();
            if (iA != 33) {
                if (iA != 61) {
                    return w.f4215a;
                }
                sVar.a();
                int i4 = sVar.f4205a;
                if (i4 != 34 && i4 != 39) {
                    throw new XPathException(c0Var, "right hand side of equals", sVar, "quoted string");
                }
                String str = sVar.f4207c;
                sVar.a();
                return new v(str);
            }
            sVar.a();
            if (sVar.f4205a != 61) {
                throw new XPathException(c0Var, "after !", sVar, "=");
            }
            sVar.a();
            int i5 = sVar.f4205a;
            if (i5 != 34 && i5 != 39) {
                throw new XPathException(c0Var, "right hand side of !=", sVar, "quoted string");
            }
            String str2 = sVar.f4207c;
            sVar.a();
            return new x(str2);
        }
        if (i3 == -2) {
            int i6 = sVar.f4206b;
            sVar.a();
            return new r(i6);
        }
        if (i3 != 64) {
            throw new XPathException(c0Var, "at beginning of expression", sVar, "@, number, or text()");
        }
        if (sVar.a() != -3) {
            throw new XPathException(c0Var, "after @", sVar, "name");
        }
        String str3 = sVar.f4207c;
        int iA2 = sVar.a();
        if (iA2 == 33) {
            sVar.a();
            if (sVar.f4205a != 61) {
                throw new XPathException(c0Var, "after !", sVar, "=");
            }
            sVar.a();
            int i7 = sVar.f4205a;
            if (i7 != 34 && i7 != 39) {
                throw new XPathException(c0Var, "right hand side of !=", sVar, "quoted string");
            }
            String str4 = sVar.f4207c;
            sVar.a();
            return new h(str3, str4);
        }
        switch (iA2) {
            case 60:
                sVar.a();
                int i8 = sVar.f4205a;
                if (i8 == 34 || i8 == 39) {
                    i = Integer.parseInt(sVar.f4207c);
                } else {
                    if (i8 != -2) {
                        throw new XPathException(c0Var, "right hand side of less-than", sVar, "quoted string or number");
                    }
                    i = sVar.f4206b;
                }
                sVar.a();
                return new g(str3, i);
            case 61:
                sVar.a();
                int i9 = sVar.f4205a;
                if (i9 != 34 && i9 != 39) {
                    throw new XPathException(c0Var, "right hand side of equals", sVar, "quoted string");
                }
                String str5 = sVar.f4207c;
                sVar.a();
                return new c(str3, str5);
            case 62:
                sVar.a();
                int i10 = sVar.f4205a;
                if (i10 == 34 || i10 == 39) {
                    i2 = Integer.parseInt(sVar.f4207c);
                } else {
                    if (i10 != -2) {
                        throw new XPathException(c0Var, "right hand side of greater-than", sVar, "quoted string or number");
                    }
                    i2 = sVar.f4206b;
                }
                sVar.a();
                return new f(str3, i2);
            default:
                return new d(str3);
        }
    }
}
