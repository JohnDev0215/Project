package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.ConnectionInfo;

/* JADX INFO: renamed from: cf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0614cf extends AbstractBinderC0401Wg implements InterfaceC0670df {
    public AbstractBinderC0614cf() {
        super("com.google.android.gms.common.internal.IGmsCallbacks");
    }

    @Override // defpackage.AbstractBinderC0401Wg
    public boolean u(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            ((BinderC0291Qe) this).v(parcel.readInt(), parcel.readStrongBinder(), (Bundle) AbstractC0383Vg.b(parcel, Bundle.CREATOR));
        } else if (i == 2) {
            parcel.readInt();
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        } else {
            if (i != 3) {
                return false;
            }
            int i3 = parcel.readInt();
            IBinder strongBinder = parcel.readStrongBinder();
            ConnectionInfo connectionInfo = (ConnectionInfo) AbstractC0383Vg.b(parcel, ConnectionInfo.CREATOR);
            BinderC0291Qe binderC0291Qe = (BinderC0291Qe) this;
            Cif.g(binderC0291Qe.a, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Cif.f(connectionInfo);
            binderC0291Qe.a.x = connectionInfo;
            binderC0291Qe.v(i3, strongBinder, connectionInfo.s);
        }
        parcel2.writeNoException();
        return true;
    }
}
