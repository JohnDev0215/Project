package com.hp.hpl.sparta;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f4167a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private d f4168b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private f f4169c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private f f4170d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Object f4171e = null;
    private int f = 0;

    protected static void f(Writer writer, String str) throws IOException {
        String string;
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt >= 128) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("&#");
                stringBuffer.append((int) cCharAt);
                stringBuffer.append(";");
                string = stringBuffer.toString();
            } else {
                string = cCharAt != '\"' ? cCharAt != '<' ? cCharAt != '>' ? cCharAt != '&' ? cCharAt != '\'' ? null : "&#39;" : "&amp;" : "&gt;" : "&lt;" : "&quot;";
            }
            if (string != null) {
                writer.write(str, i, i2 - i);
                writer.write(string);
                i = i2 + 1;
            }
        }
        if (i < length) {
            writer.write(str, i, length - i);
        }
    }

    protected abstract int a();

    public f b() {
        return this.f4170d;
    }

    public c c() {
        return this.f4167a;
    }

    public abstract Object clone();

    public d d() {
        return this.f4168b;
    }

    public f e() {
        return this.f4169c;
    }

    void g(f fVar) {
        this.f4169c = fVar;
        if (fVar != null) {
            fVar.f4170d = this;
        }
    }

    void h() {
        this.f = 0;
        c cVar = this.f4167a;
        if (cVar != null) {
            cVar.h();
        }
    }

    public int hashCode() {
        if (this.f == 0) {
            this.f = a();
        }
        return this.f;
    }

    void i() {
        f fVar = this.f4169c;
        if (fVar != null) {
            fVar.f4170d = this.f4170d;
        }
        f fVar2 = this.f4170d;
        if (fVar2 != null) {
            fVar2.f4169c = fVar;
        }
        this.f4170d = null;
        this.f4169c = null;
    }

    void j(c cVar) {
        this.f4167a = cVar;
    }

    void k(d dVar) {
        this.f4168b = dVar;
    }

    abstract void l(Writer writer) throws IOException;

    public String m() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
        n(outputStreamWriter);
        outputStreamWriter.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    abstract void n(Writer writer) throws IOException;

    public String toString() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
            l(outputStreamWriter);
            outputStreamWriter.flush();
            return new String(byteArrayOutputStream.toByteArray());
        } catch (IOException unused) {
            return super.toString();
        }
    }
}
