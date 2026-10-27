package com.hp.hpl.sparta.xpath;

/* JADX INFO: loaded from: classes.dex */
public abstract class u extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f4214a;

    u(String str) {
        this.f4214a = str;
    }

    public String b() {
        return this.f4214a;
    }

    protected String c(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[text()");
        stringBuffer.append(str);
        stringBuffer.append("'");
        stringBuffer.append(this.f4214a);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }
}
