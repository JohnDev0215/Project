package com.hp.hpl.sparta.xpath;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class XPathException extends Exception {
    private Throwable cause_;

    XPathException(c0 c0Var, Exception exc) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(c0Var);
        stringBuffer.append(" ");
        stringBuffer.append(exc);
        super(stringBuffer.toString());
        this.cause_ = null;
        this.cause_ = exc;
    }

    public XPathException(c0 c0Var, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(c0Var);
        stringBuffer.append(" ");
        stringBuffer.append(str);
        super(stringBuffer.toString());
        this.cause_ = null;
    }

    XPathException(c0 c0Var, String str, s sVar, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(" got \"");
        stringBuffer.append(a(sVar));
        stringBuffer.append("\" instead of expected ");
        stringBuffer.append(str2);
        this(c0Var, stringBuffer.toString());
    }

    private static String a(s sVar) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(b(sVar));
            if (sVar.f4205a != -1) {
                sVar.a();
                stringBuffer.append(b(sVar));
                sVar.c();
            }
            return stringBuffer.toString();
        } catch (IOException e2) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("(cannot get  info: ");
            stringBuffer2.append(e2);
            stringBuffer2.append(")");
            return stringBuffer2.toString();
        }
    }

    private static String b(s sVar) {
        StringBuffer stringBuffer;
        int i = sVar.f4205a;
        if (i == -3) {
            return sVar.f4207c;
        }
        if (i == -2) {
            stringBuffer = new StringBuffer();
            stringBuffer.append(sVar.f4206b);
        } else {
            if (i == -1) {
                return "<end of expression>";
            }
            stringBuffer = new StringBuffer();
            stringBuffer.append((char) sVar.f4205a);
        }
        stringBuffer.append("");
        return stringBuffer.toString();
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause_;
    }
}
