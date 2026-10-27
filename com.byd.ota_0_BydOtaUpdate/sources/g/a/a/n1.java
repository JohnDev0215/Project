package g.a.a;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class n1 extends p {
    public n1(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // g.a.a.p
    public void j(e eVar) throws IOException {
        if (eVar == null) {
            throw new IOException("null object detected");
        }
        eVar.c().m().h(this);
    }
}
