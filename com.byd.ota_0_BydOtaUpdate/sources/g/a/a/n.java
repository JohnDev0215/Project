package g.a.a;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class n extends q implements o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f3929c;

    public n(byte[] bArr) {
        Objects.requireNonNull(bArr, "string cannot be null");
        this.f3929c = bArr;
    }

    public static n n(Object obj) {
        if (obj == null || (obj instanceof n)) {
            return (n) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return n(q.j((byte[]) obj));
            } catch (IOException e2) {
                throw new IllegalArgumentException("failed to construct OCTET STRING from byte[]: " + e2.getMessage());
            }
        }
        if (obj instanceof e) {
            q qVarC = ((e) obj).c();
            if (qVarC instanceof n) {
                return (n) qVarC;
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static n o(x xVar, boolean z) {
        q qVarP = xVar.p();
        return (z || (qVarP instanceof n)) ? n(qVarP) : c0.r(r.n(qVarP));
    }

    @Override // g.a.a.s1
    public q a() {
        return c();
    }

    @Override // g.a.a.o
    public InputStream b() {
        return new ByteArrayInputStream(this.f3929c);
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof n) {
            return g.a.f.a.a(this.f3929c, ((n) qVar).f3929c);
        }
        return false;
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.z(p());
    }

    @Override // g.a.a.q
    public q l() {
        return new y0(this.f3929c);
    }

    @Override // g.a.a.q
    public q m() {
        return new y0(this.f3929c);
    }

    public byte[] p() {
        return this.f3929c;
    }

    public String toString() {
        return "#" + g.a.f.i.b(g.a.f.j.d.b(this.f3929c));
    }
}
