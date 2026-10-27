package g.a.a;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class a1 extends p {
    public a1(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // g.a.a.p
    public p a() {
        return this;
    }

    @Override // g.a.a.p
    public p b() {
        return this;
    }

    @Override // g.a.a.p
    public void j(e eVar) throws IOException {
        if (eVar == null) {
            throw new IOException("null object detected");
        }
        eVar.c().l().h(this);
    }
}
