package com.hp.hpl.sparta;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public class p extends f {
    private StringBuffer g;

    public p(String str) {
        this.g = new StringBuffer(str);
    }

    @Override // com.hp.hpl.sparta.f
    protected int a() {
        return this.g.toString().hashCode();
    }

    @Override // com.hp.hpl.sparta.f
    public Object clone() {
        return new p(this.g.toString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof p) {
            return this.g.toString().equals(((p) obj).g.toString());
        }
        return false;
    }

    @Override // com.hp.hpl.sparta.f
    void l(Writer writer) throws IOException {
        writer.write(this.g.toString());
    }

    @Override // com.hp.hpl.sparta.f
    void n(Writer writer) throws IOException {
        String string = this.g.toString();
        if (string.length() < 50) {
            f.f(writer, string);
            return;
        }
        writer.write("<![CDATA[");
        writer.write(string);
        writer.write("]]>");
    }

    public void o(char[] cArr, int i, int i2) {
        this.g.append(cArr, i, i2);
        h();
    }

    public String p() {
        return this.g.toString();
    }
}
