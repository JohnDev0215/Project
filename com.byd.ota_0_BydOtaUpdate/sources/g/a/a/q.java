package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class q extends l {
    public static q j(byte[] bArr) throws IOException {
        i iVar = new i(bArr);
        try {
            q qVarM = iVar.M();
            if (iVar.available() == 0) {
                return qVarM;
            }
            throw new IOException("Extra data detected in stream");
        } catch (ClassCastException unused) {
            throw new IOException("cannot recognise object in stream");
        }
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        return this;
    }

    @Override // g.a.a.l
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof e) && g(((e) obj).c());
    }

    public abstract boolean g(q qVar);

    public abstract void h(p pVar) throws IOException;

    public abstract int i() throws IOException;

    public abstract boolean k();

    public q l() {
        return this;
    }

    public q m() {
        return this;
    }
}
