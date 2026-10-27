package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class OD {
    public static OD b;
    public List a = new ArrayList();

    public final void a(C1653vL c1653vL, Object obj) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            Objects.requireNonNull((C1191mz) ((PD) it.next()));
            AbstractC0599cJ abstractC0599cJ = InterfaceC1266oI.l;
            c1653vL.s.put("media.mojom.AndroidOverlayProvider", new C1598uL(AbstractC1540tI.a, new C1697wA()));
        }
    }
}
