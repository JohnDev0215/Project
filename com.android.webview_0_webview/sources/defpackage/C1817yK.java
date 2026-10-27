package defpackage;

import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import java.util.Objects;

/* JADX INFO: renamed from: yK, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1817yK extends PhoneStateListener {
    public ServiceState a;
    public final /* synthetic */ C1872zK b;

    public C1817yK(C1872zK c1872zK, AbstractC1762xK abstractC1762xK) {
        this.b = c1872zK;
    }

    @Override // android.telephony.PhoneStateListener
    public void onServiceStateChanged(ServiceState serviceState) {
        ServiceState serviceState2 = this.a;
        if (serviceState2 == null || !serviceState2.equals(serviceState)) {
            this.a = serviceState;
            C1872zK c1872zK = this.b;
            TelephonyManager telephonyManagerB = C1872zK.b();
            Objects.requireNonNull(c1872zK);
            if (telephonyManagerB == null) {
                return;
            }
            telephonyManagerB.getNetworkCountryIso();
            c1872zK.a = telephonyManagerB.getNetworkOperator();
            c1872zK.b = telephonyManagerB.getSimOperator();
        }
    }
}
