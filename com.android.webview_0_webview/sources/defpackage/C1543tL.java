package defpackage;

import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: renamed from: tL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1543tL implements LI {
    public InterfaceC1103lL s;

    /* JADX WARN: Multi-variable type inference failed */
    public C1543tL(VJ vj) {
        AbstractC0599cJ abstractC0599cJ = InterfaceC1159mL.m;
        C1431rJ c1431rJ = new C1431rJ(vj);
        CoreImpl coreImpl = ((AbstractC0935iK) vj).t;
        C1324pL c1324pL = new C1324pL(coreImpl, new II(coreImpl, c1431rJ));
        SI si = new SI();
        si.s.add(c1324pL);
        c1431rJ.s.w = si;
        c1431rJ.G();
        C0487aJ c0487aJ = c1324pL.s;
        c0487aJ.v = 0;
        this.s = c1324pL;
        c0487aJ.u = this;
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
        ((AbstractC0543bJ) this.s).close();
    }
}
