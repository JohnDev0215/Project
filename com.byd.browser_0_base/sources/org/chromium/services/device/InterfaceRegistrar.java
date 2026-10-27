package org.chromium.services.device;

import defpackage.dh1;
import defpackage.e21;
import defpackage.fj3;
import defpackage.ge;
import defpackage.hn1;
import defpackage.ie;
import defpackage.in1;
import defpackage.pl3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.device.nfc.NfcDelegate;
import org.chromium.device.vibration.VibrationManagerImpl;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: loaded from: classes3.dex */
public class InterfaceRegistrar {
    @CalledByNative
    public static void createInterfaceRegistryForContext(long j, NfcDelegate nfcDelegate) {
        CoreImpl coreImpl = CoreImpl.a.a;
        coreImpl.getClass();
        e21 e21VarC = e21.C(new dh1(new fj3(coreImpl, j)));
        e21VarC.t(ie.a, new ge());
        e21VarC.t(in1.a, new hn1.a(nfcDelegate));
        e21VarC.t(pl3.a, new VibrationManagerImpl.a());
    }
}
