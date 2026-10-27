package com.hp.hpl.sparta;

/* JADX INFO: loaded from: classes.dex */
public class ParseException extends Exception {
    private Throwable cause_;
    private int lineNumber_;

    public ParseException(i iVar, char c2, char c3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("got '");
        stringBuffer.append(c2);
        stringBuffer.append("' instead of expected '");
        stringBuffer.append(c3);
        stringBuffer.append("'");
        this(iVar, stringBuffer.toString());
    }

    public ParseException(i iVar, char c2, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("got '");
        stringBuffer.append(c2);
        stringBuffer.append("' instead of ");
        stringBuffer.append(str);
        stringBuffer.append(" as expected");
        this(iVar, stringBuffer.toString());
    }

    public ParseException(i iVar, char c2, char[] cArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("got '");
        stringBuffer.append(c2);
        stringBuffer.append("' instead of ");
        stringBuffer.append(b(cArr));
        this(iVar, stringBuffer.toString());
    }

    public ParseException(i iVar, String str) {
        this(iVar.f(), iVar.g(), iVar.e(), iVar.d(), iVar.c(), str);
    }

    public ParseException(i iVar, String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("got \"");
        stringBuffer.append(str);
        stringBuffer.append("\" instead of \"");
        stringBuffer.append(str2);
        stringBuffer.append("\" as expected");
        this(iVar, stringBuffer.toString());
    }

    public ParseException(i iVar, String str, char[] cArr) {
        this(iVar, str, new String(cArr));
    }

    public ParseException(k kVar, String str, int i, int i2, String str2, String str3) {
        this(str, i, i2, str2, str3);
        kVar.b(str3, str, i);
    }

    public ParseException(String str) {
        super(str);
        this.lineNumber_ = -1;
        this.cause_ = null;
    }

    public ParseException(String str, int i, int i2, String str2, String str3) {
        super(a(str, i, i2, str2, str3));
        this.lineNumber_ = -1;
        this.cause_ = null;
        this.lineNumber_ = i;
    }

    public ParseException(String str, Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(" ");
        stringBuffer.append(th);
        super(stringBuffer.toString());
        this.lineNumber_ = -1;
        this.cause_ = null;
        this.cause_ = th;
    }

    private static String a(String str, int i, int i2, String str2, String str3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("(");
        stringBuffer.append(i);
        stringBuffer.append("): \n");
        stringBuffer.append(str2);
        stringBuffer.append("\nLast character read was '");
        stringBuffer.append(charRepr(i2));
        stringBuffer.append("'\n");
        stringBuffer.append(str3);
        return stringBuffer.toString();
    }

    private static String b(char[] cArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cArr[0]);
        for (int i = 1; i < cArr.length; i++) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("or ");
            stringBuffer2.append(cArr[i]);
            stringBuffer.append(stringBuffer2.toString());
        }
        return stringBuffer.toString();
    }

    static String charRepr(int i) {
        if (i == -1) {
            return "EOF";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("");
        stringBuffer.append((char) i);
        return stringBuffer.toString();
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause_;
    }

    public int getLineNumber() {
        return this.lineNumber_;
    }
}
