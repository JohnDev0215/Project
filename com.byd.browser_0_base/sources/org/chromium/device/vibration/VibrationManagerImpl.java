package org.chromium.device.vibration;

import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import android.util.Log;
import defpackage.a21;
import defpackage.ki1;
import defpackage.ol3;
import defpackage.pl3;
import defpackage.xz;
import defpackage.y11;
import defpackage.yg1;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class VibrationManagerImpl implements ol3 {
    public static long h = -1;
    public static boolean i;
    public final AudioManager e;
    public final Vibrator f;
    public final boolean g;

    public static class a implements a21<ol3> {
        @Override // defpackage.a21
        public final y11 d() {
            return new VibrationManagerImpl();
        }
    }

    public VibrationManagerImpl() {
        Context context = xz.a;
        this.e = (AudioManager) context.getSystemService("audio");
        this.f = (Vibrator) context.getSystemService("vibrator");
        boolean z = context.checkCallingOrSelfPermission("android.permission.VIBRATE") == 0;
        this.g = z;
        if (z) {
            return;
        }
        Log.w("cr_VibrationManagerImpl", "Failed to use vibrate API, requires VIBRATE permission.");
    }

    @CalledByNative
    public static boolean getVibrateCancelledForTesting() {
        return i;
    }

    @CalledByNative
    public static long getVibrateMilliSecondsForTesting() {
        return h;
    }

    @Override // defpackage.ol3
    public final void O0(long j, pl3.k kVar) {
        long jMax = Math.max(1L, Math.min(j, 10000L));
        if (this.e.getRingerMode() != 0 && this.g) {
            this.f.vibrate(jMax);
        }
        h = jMax;
        kVar.b.a(new pl3.i().c(kVar.a, new yg1(0, 2, kVar.c)));
    }

    @Override // defpackage.ol3
    public final void x0(pl3.g gVar) {
        if (this.g) {
            this.f.cancel();
        }
        i = true;
        gVar.b.a(new pl3.e().c(gVar.a, new yg1(1, 2, gVar.c)));
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
    }
}
