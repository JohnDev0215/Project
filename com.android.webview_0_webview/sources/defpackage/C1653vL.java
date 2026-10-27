package defpackage;

import java.util.HashMap;
import java.util.Map;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: renamed from: vL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1653vL implements InterfaceC1159mL {
    public final Map s = new HashMap();

    /* JADX WARN: Multi-variable type inference failed */
    public static C1653vL x(VJ vj) {
        C1653vL c1653vL = new C1653vL();
        C1431rJ c1431rJ = new C1431rJ(vj);
        CoreImpl coreImpl = ((AbstractC0935iK) vj).t;
        c1431rJ.s.w = c1653vL;
        c1431rJ.t = new C1379qL(coreImpl, c1653vL);
        c1431rJ.G();
        return c1653vL;
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
        this.s.clear();
    }

    @Override // defpackage.InterfaceC0822gJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.s.clear();
    }

    @Override // defpackage.InterfaceC1159mL
    public void l(String str, VJ vj) {
        C1598uL c1598uL = (C1598uL) this.s.get(str);
        if (c1598uL == null) {
            return;
        }
        InterfaceC0822gJ interfaceC0822gJA = c1598uL.b.a();
        if (interfaceC0822gJA == null) {
            vj.close();
        } else {
            c1598uL.a.c(interfaceC0822gJA, vj);
        }
    }
}
