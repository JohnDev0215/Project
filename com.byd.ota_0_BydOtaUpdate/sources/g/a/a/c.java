package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class c extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final byte[] f3781c = {-1};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final byte[] f3782d = {0};
    public static final c s = new c(false);
    public static final c x = new c(true);
    public final byte[] y;

    public c(boolean z) {
        this.y = z ? f3781c : f3782d;
    }

    public c(byte[] bArr) {
        if (bArr.length != 1) {
            throw new IllegalArgumentException("byte value should have 1 byte in it");
        }
        if (bArr[0] == 0) {
            this.y = f3782d;
        } else if ((bArr[0] & 255) == 255) {
            this.y = f3781c;
        } else {
            this.y = g.a.f.a.f(bArr);
        }
    }

    public static c n(byte[] bArr) {
        if (bArr.length == 1) {
            return bArr[0] == 0 ? s : (bArr[0] & 255) == 255 ? x : new c(bArr);
        }
        throw new IllegalArgumentException("BOOLEAN value should have 1 byte in it");
    }

    public static c o(Object obj) {
        if (obj == null || (obj instanceof c)) {
            return (c) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (c) q.j((byte[]) obj);
        } catch (IOException e2) {
            throw new IllegalArgumentException("failed to construct boolean from byte[]: " + e2.getMessage());
        }
    }

    public static c p(x xVar, boolean z) {
        q qVarP = xVar.p();
        return (z || (qVarP instanceof c)) ? o(qVarP) : n(((n) qVarP).p());
    }

    public static c q(boolean z) {
        return z ? x : s;
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        return (qVar instanceof c) && this.y[0] == ((c) qVar).y[0];
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(1, this.y);
    }

    @Override // g.a.a.l
    public int hashCode() {
        return this.y[0];
    }

    @Override // g.a.a.q
    public int i() {
        return 3;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    public boolean r() {
        return this.y[0] != 0;
    }

    public String toString() {
        return this.y[0] != 0 ? "TRUE" : "FALSE";
    }
}
