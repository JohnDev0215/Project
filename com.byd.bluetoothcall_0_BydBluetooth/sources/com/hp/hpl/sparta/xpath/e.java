package com.hp.hpl.sparta.xpath;

/* JADX INFO: loaded from: classes.dex */
public abstract class e extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f4199a;

    e(String str) {
        this.f4199a = str;
    }

    public String b() {
        return this.f4199a;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("@");
        stringBuffer.append(this.f4199a);
        return stringBuffer.toString();
    }
}
