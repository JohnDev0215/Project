package g.a.a;

import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class v0 extends q implements w {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f4080c;

    public v0(String str) {
        this(str, false);
    }

    public v0(String str, boolean z) {
        Objects.requireNonNull(str, "string cannot be null");
        if (z && !p(str)) {
            throw new IllegalArgumentException("string contains illegal characters");
        }
        this.f4080c = g.a.f.i.e(str);
    }

    public v0(byte[] bArr) {
        this.f4080c = bArr;
    }

    public static v0 n(Object obj) {
        if (obj == null || (obj instanceof v0)) {
            return (v0) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (v0) q.j((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e2.toString());
        }
    }

    public static v0 o(x xVar, boolean z) {
        q qVarP = xVar.p();
        return (z || (qVarP instanceof v0)) ? n(qVarP) : new v0(((n) qVarP).p());
    }

    public static boolean p(String str) {
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) > 127) {
                return false;
            }
        }
        return true;
    }

    @Override // g.a.a.w
    public String d() {
        return g.a.f.i.b(this.f4080c);
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof v0) {
            return g.a.f.a.a(this.f4080c, ((v0) qVar).f4080c);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(22, this.f4080c);
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.z(this.f4080c);
    }

    @Override // g.a.a.q
    public int i() {
        return y1.a(this.f4080c.length) + 1 + this.f4080c.length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    public String toString() {
        return d();
    }
}
