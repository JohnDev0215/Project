package org.chromium.shape_detection;

import defpackage.dh1;
import defpackage.fj3;
import defpackage.pj0;
import defpackage.qj0;
import defpackage.tc;
import defpackage.uc;
import defpackage.x73;
import defpackage.xc2;
import defpackage.z73;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: loaded from: classes3.dex */
public class InterfaceRegistrar {
    public static dh1 a(long j) {
        CoreImpl coreImpl = CoreImpl.a.a;
        coreImpl.getClass();
        return new dh1(new fj3(coreImpl, j));
    }

    @CalledByNative
    public static void bindBarcodeDetectionProvider(long j) {
        dh1 dh1VarA = a(j);
        tc tcVarT = tc.t();
        if (tcVarT == null) {
            dh1VarA.close();
            return;
        }
        xc2 xc2Var = new xc2(dh1VarA);
        xc2Var.e.i = tcVarT;
        xc2Var.f = new uc.h(dh1VarA.f, tcVarT);
        xc2Var.q();
    }

    @CalledByNative
    public static void bindFaceDetectionProvider(long j) {
        pj0 pj0Var = new pj0();
        dh1 dh1VarA = a(j);
        xc2 xc2Var = new xc2(dh1VarA);
        xc2Var.e.i = pj0Var;
        xc2Var.f = new qj0.d(dh1VarA.f, pj0Var);
        xc2Var.q();
    }

    @CalledByNative
    public static void bindTextDetection(long j) {
        dh1 dh1VarA = a(j);
        x73 x73VarT = x73.t();
        if (x73VarT == null) {
            dh1VarA.close();
            return;
        }
        xc2 xc2Var = new xc2(dh1VarA);
        xc2Var.e.i = x73VarT;
        xc2Var.f = new z73.c(dh1VarA.f, x73VarT);
        xc2Var.q();
    }
}
