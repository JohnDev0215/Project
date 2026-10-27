package org.chromium.device.vibration;

import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import defpackage.C1648vG;
import defpackage.C1868zG;
import defpackage.InterfaceC1154mG;
import defpackage.InterfaceC1209nG;
import defpackage.InterfaceC1264oG;
import defpackage.Is;
import defpackage.Ts;
import defpackage.WJ;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VibrationManagerImpl implements InterfaceC1264oG {
    public static long v = -1;
    public static boolean w;
    public final AudioManager s;
    public final Vibrator t;
    public final boolean u;

    public VibrationManagerImpl() {
        Context context = Is.a;
        this.s = (AudioManager) context.getSystemService("audio");
        this.t = (Vibrator) context.getSystemService("vibrator");
        boolean z = context.checkCallingOrSelfPermission("android.permission.VIBRATE") == 0;
        this.u = z;
        if (z) {
            return;
        }
        Ts.f("VibrationManagerImpl", "Failed to use vibrate API, requires VIBRATE permission.", new Object[0]);
    }

    public static boolean getVibrateCancelledForTesting() {
        return w;
    }

    public static long getVibrateMilliSecondsForTesting() {
        return v;
    }

    @Override // defpackage.InterfaceC1264oG
    public void B(InterfaceC1154mG interfaceC1154mG) {
        if (this.u) {
            this.t.cancel();
        }
        w = true;
        ((C1648vG) interfaceC1154mG).a();
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
    }

    @Override // defpackage.InterfaceC1264oG
    public void K(long j, InterfaceC1209nG interfaceC1209nG) {
        long jMax = Math.max(1L, Math.min(j, 10000L));
        if (this.s.getRingerMode() != 0 && this.u) {
            this.t.vibrate(jMax);
        }
        v = jMax;
        ((C1868zG) interfaceC1209nG).a();
    }

    @Override // defpackage.InterfaceC0822gJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }
}
