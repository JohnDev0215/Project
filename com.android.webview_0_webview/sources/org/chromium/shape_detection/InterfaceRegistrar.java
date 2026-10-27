package org.chromium.shape_detection;

import defpackage.AM;
import defpackage.AbstractC0599cJ;
import defpackage.AbstractC0823gK;
import defpackage.AbstractC0935iK;
import defpackage.C0937iM;
import defpackage.C0990jK;
import defpackage.C1102lK;
import defpackage.C1431rJ;
import defpackage.C1763xL;
import defpackage.CL;
import defpackage.DL;
import defpackage.HL;
import defpackage.InterfaceC0713eM;
import defpackage.InterfaceC1709wM;
import defpackage.LJ;
import defpackage.PL;
import defpackage.VJ;
import java.util.Objects;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class InterfaceRegistrar {
    public static VJ a(int i) {
        CoreImpl coreImpl = (CoreImpl) AbstractC0823gK.a;
        Objects.requireNonNull(coreImpl);
        return new C0990jK(new C1102lK(coreImpl, i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void bindBarcodeDetectionProvider(int i) {
        VJ vjA = a(i);
        HL hlX = C1763xL.x();
        if (hlX == null) {
            ((AbstractC0935iK) vjA).close();
            return;
        }
        AbstractC0599cJ abstractC0599cJ = HL.o;
        C1431rJ c1431rJ = new C1431rJ(vjA);
        LJ ljZ = vjA.z();
        c1431rJ.x(hlX);
        c1431rJ.A(new PL(ljZ, hlX));
        c1431rJ.G();
    }

    public static void bindFaceDetectionProvider(int i) {
        AbstractC0599cJ abstractC0599cJ = InterfaceC0713eM.q;
        CL cl = new CL();
        VJ vjA = a(i);
        C1431rJ c1431rJ = new C1431rJ(vjA);
        LJ ljZ = vjA.z();
        c1431rJ.x(cl);
        c1431rJ.A(new C0937iM(ljZ, cl));
        c1431rJ.G();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void bindTextDetection(int i) {
        VJ vjA = a(i);
        InterfaceC1709wM interfaceC1709wMX = DL.x();
        if (interfaceC1709wMX == null) {
            ((AbstractC0935iK) vjA).close();
            return;
        }
        AbstractC0599cJ abstractC0599cJ = InterfaceC1709wM.r;
        C1431rJ c1431rJ = new C1431rJ(vjA);
        LJ ljZ = vjA.z();
        c1431rJ.x(interfaceC1709wMX);
        c1431rJ.A(new AM(ljZ, interfaceC1709wMX));
        c1431rJ.G();
    }
}
