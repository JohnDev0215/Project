package defpackage;

import J.N;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;

/* JADX INFO: compiled from: ChromeMessageAutodismissDurationProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class iq implements jg1 {
    public final long a;
    public final long b;

    public iq() {
        sk skVar = bq.a;
        this.a = N.M37SqSAy("MessagesForAndroidInfrastructure", "autodismiss_duration_ms", TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE);
        this.b = N.M37SqSAy("MessagesForAndroidInfrastructure", "autodismiss_duration_with_a11y_ms", 30000);
    }
}
