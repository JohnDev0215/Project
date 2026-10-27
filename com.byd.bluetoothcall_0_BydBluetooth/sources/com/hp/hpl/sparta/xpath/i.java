package com.hp.hpl.sparta.xpath;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4200b;

    i(String str, int i) {
        super(str);
        this.f4200b = i;
    }

    public double c() {
        return this.f4200b;
    }

    protected String d(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(super.toString());
        stringBuffer.append(str);
        stringBuffer.append("'");
        stringBuffer.append(this.f4200b);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }
}
