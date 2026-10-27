package com.hp.hpl.sparta.xpath;

/* JADX INFO: loaded from: classes.dex */
public class d extends e {
    d(String str) {
        super(str);
    }

    @Override // com.hp.hpl.sparta.xpath.k
    public void a(l lVar) throws XPathException {
        lVar.a(this);
    }

    @Override // com.hp.hpl.sparta.xpath.e
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(super.toString());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
