package defpackage;

import android.os.Bundle;
import android.support.customtabs.trusted.ITrustedWebActivityCallback;

/* JADX INFO: compiled from: TrustedWebActivityServiceConnection.java */
/* JADX INFO: loaded from: classes.dex */
public final class cg3 extends ITrustedWebActivityCallback.Stub {
    public final /* synthetic */ vd1 e;

    public cg3(vd1 vd1Var) {
        this.e = vd1Var;
    }

    @Override // android.support.customtabs.trusted.ITrustedWebActivityCallback
    public final void onExtraCallback(String str, Bundle bundle) {
        this.e.q(str, bundle);
    }
}
