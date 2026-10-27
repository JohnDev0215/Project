package defpackage;

import java.util.Objects;

/* JADX INFO: renamed from: pJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1322pJ implements InterfaceC1101lJ {
    public final /* synthetic */ C1431rJ s;

    public C1322pJ(C1431rJ c1431rJ, RunnableC1267oJ runnableC1267oJ) {
        this.s = c1431rJ;
    }

    @Override // defpackage.InterfaceC1101lJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        InterfaceC1157mJ interfaceC1157mJ = this.s.t;
        if (interfaceC1157mJ != null) {
            interfaceC1157mJ.close();
        }
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        C1431rJ c1431rJ = this.s;
        Objects.requireNonNull(c1431rJ);
        C1045kJ c1045kJ = c0989jJ.a().d;
        if (c1045kJ.a(1)) {
            InterfaceC1157mJ interfaceC1157mJ = c1431rJ.t;
            if (interfaceC1157mJ != null) {
                return interfaceC1157mJ.p(c0989jJ, new C1377qJ(c1431rJ));
            }
            c1431rJ.s.close();
        } else if (c1045kJ.a(2)) {
            long j = c1045kJ.d;
            InterfaceC1101lJ interfaceC1101lJ = (InterfaceC1101lJ) c1431rJ.v.get(Long.valueOf(j));
            if (interfaceC1101lJ != null) {
                c1431rJ.v.remove(Long.valueOf(j));
                return interfaceC1101lJ.q(c0989jJ);
            }
        } else {
            InterfaceC1157mJ interfaceC1157mJ2 = c1431rJ.t;
            if (interfaceC1157mJ2 != null) {
                return interfaceC1157mJ2.q(c0989jJ);
            }
        }
        return false;
    }
}
