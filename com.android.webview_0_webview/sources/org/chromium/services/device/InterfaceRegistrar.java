package org.chromium.services.device;

import defpackage.AG;
import defpackage.AbstractC0599cJ;
import defpackage.AbstractC0823gK;
import defpackage.AbstractC1097lF;
import defpackage.C0990jK;
import defpackage.C1096lE;
import defpackage.C1102lK;
import defpackage.C1598uL;
import defpackage.C1653vL;
import defpackage.FF;
import defpackage.IG;
import defpackage.InterfaceC0651dF;
import defpackage.InterfaceC1264oG;
import defpackage.MF;
import defpackage.QG;
import java.util.Objects;
import org.chromium.device.nfc.NfcDelegate;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class InterfaceRegistrar {
    public static void createInterfaceRegistryForContext(int i, NfcDelegate nfcDelegate) {
        CoreImpl coreImpl = (CoreImpl) AbstractC0823gK.a;
        Objects.requireNonNull(coreImpl);
        C1653vL c1653vLX = C1653vL.x(new C0990jK(new C1102lK(coreImpl, i)));
        AbstractC0599cJ abstractC0599cJ = InterfaceC0651dF.e;
        AbstractC0599cJ abstractC0599cJ2 = AbstractC1097lF.a;
        c1653vLX.s.put(abstractC0599cJ2.f(), new C1598uL(abstractC0599cJ2, new C1096lE()));
        AbstractC0599cJ abstractC0599cJ3 = FF.h;
        AbstractC0599cJ abstractC0599cJ4 = MF.a;
        c1653vLX.s.put(abstractC0599cJ4.f(), new C1598uL(abstractC0599cJ4, new IG(nfcDelegate)));
        AbstractC0599cJ abstractC0599cJ5 = InterfaceC1264oG.i;
        AbstractC0599cJ abstractC0599cJ6 = AG.a;
        c1653vLX.s.put(abstractC0599cJ6.f(), new C1598uL(abstractC0599cJ6, new QG()));
    }
}
