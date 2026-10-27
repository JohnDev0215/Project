package com.hp.hpl.sparta.xpath;

import com.hp.hpl.sparta.Sparta;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f4194b;

    b(String str, String str2) {
        super(str);
        this.f4194b = Sparta.a(str2);
    }

    public String c() {
        return this.f4194b;
    }

    protected String d(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(super.toString());
        stringBuffer.append(str);
        stringBuffer.append("'");
        stringBuffer.append(this.f4194b);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }
}
