package com.hp.hpl.sparta.xpath;

/* JADX INFO: loaded from: classes.dex */
public class r extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4204a;

    public r(int i) {
        this.f4204a = i;
    }

    @Override // com.hp.hpl.sparta.xpath.k
    public void a(l lVar) throws XPathException {
        lVar.i(this);
    }

    public int b() {
        return this.f4204a;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(this.f4204a);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
