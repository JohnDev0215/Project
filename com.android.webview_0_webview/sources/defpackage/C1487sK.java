package defpackage;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.net.AndroidCellularSignalStrength;

/* JADX INFO: renamed from: sK, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1487sK extends PhoneStateListener implements InterfaceC1843ys {
    public final TelephonyManager a;
    public final /* synthetic */ AndroidCellularSignalStrength b;

    public C1487sK(AndroidCellularSignalStrength androidCellularSignalStrength) {
        this.b = androidCellularSignalStrength;
        ThreadUtils.a();
        TelephonyManager telephonyManager = (TelephonyManager) Is.a.getSystemService("phone");
        this.a = telephonyManager;
        if (telephonyManager.getSimState() != 5) {
            return;
        }
        ApplicationStatus.e.f(this);
        int stateForApplication = ApplicationStatus.getStateForApplication();
        if (stateForApplication == 1) {
            this.a.listen(this, 256);
        } else if (stateForApplication == 2) {
            this.b.a = Integer.MIN_VALUE;
            this.a.listen(this, 0);
        }
    }

    @Override // android.telephony.PhoneStateListener
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        if (ApplicationStatus.getStateForApplication() != 1) {
            return;
        }
        try {
            this.b.a = signalStrength.getLevel();
        } catch (SecurityException unused) {
            this.b.a = Integer.MIN_VALUE;
        }
    }
}
