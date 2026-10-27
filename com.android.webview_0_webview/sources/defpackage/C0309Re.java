package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: renamed from: Re, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0309Re extends AbstractC0219Me {
    public final IBinder g;
    public final /* synthetic */ BaseGmsClient h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0309Re(BaseGmsClient baseGmsClient, int i, IBinder iBinder, Bundle bundle) {
        super(baseGmsClient, i, bundle);
        this.h = baseGmsClient;
        this.g = iBinder;
    }

    @Override // defpackage.AbstractC0219Me
    public final boolean c() {
        try {
            String interfaceDescriptor = this.g.getInterfaceDescriptor();
            if (!this.h.j().equals(interfaceDescriptor)) {
                String strJ = this.h.j();
                StringBuilder sb = new StringBuilder(String.valueOf(interfaceDescriptor).length() + String.valueOf(strJ).length() + 34);
                sb.append("service descriptor mismatch: ");
                sb.append(strJ);
                sb.append(" vs. ");
                sb.append(interfaceDescriptor);
                Log.e("GmsClient", sb.toString());
                return false;
            }
            IInterface iInterfaceD = this.h.d(this.g);
            if (iInterfaceD == null || !(BaseGmsClient.r(this.h, 2, 4, iInterfaceD) || BaseGmsClient.r(this.h, 3, 4, iInterfaceD))) {
                return false;
            }
            BaseGmsClient baseGmsClient = this.h;
            baseGmsClient.v = null;
            InterfaceC0148Ie interfaceC0148Ie = baseGmsClient.r;
            if (interfaceC0148Ie == null) {
                return true;
            }
            ((C1775xf) interfaceC0148Ie).a.f(null);
            return true;
        } catch (RemoteException unused) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }

    @Override // defpackage.AbstractC0219Me
    public final void d(ConnectionResult connectionResult) {
        InterfaceC0166Je interfaceC0166Je = this.h.s;
        if (interfaceC0166Je != null) {
            ((C1720wf) interfaceC0166Je).a.i(connectionResult);
        }
        this.h.n(connectionResult);
    }
}
