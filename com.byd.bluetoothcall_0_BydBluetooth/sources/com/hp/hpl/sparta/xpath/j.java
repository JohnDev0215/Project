package com.hp.hpl.sparta.xpath;

/* JADX INFO: loaded from: classes.dex */
public class j extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f4201a;

    j(String str) {
        this.f4201a = str;
    }

    @Override // com.hp.hpl.sparta.xpath.o
    public void a(b0 b0Var) {
        b0Var.l(this);
    }

    @Override // com.hp.hpl.sparta.xpath.o
    public boolean b() {
        return true;
    }

    public String c() {
        return this.f4201a;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("@");
        stringBuffer.append(this.f4201a);
        return stringBuffer.toString();
    }
}
