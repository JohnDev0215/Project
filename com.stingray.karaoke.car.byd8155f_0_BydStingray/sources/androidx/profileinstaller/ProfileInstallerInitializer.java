package androidx.profileinstaller;

import android.content.Context;
import android.hardware.bydauto.TsNormalNum;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import l9.n;
import u3.InterfaceC2524b;

/* JADX INFO: loaded from: classes.dex */
public class ProfileInstallerInitializer implements InterfaceC2524b {
    @Override // u3.InterfaceC2524b
    public final List a() {
        return Collections.emptyList();
    }

    @Override // u3.InterfaceC2524b
    public final Object b(Context context) {
        final Context applicationContext = context.getApplicationContext();
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() { // from class: q3.f
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j3) {
                this.l.getClass();
                Handler.createAsync(Looper.getMainLooper()).postDelayed(new RunnableC2180g(applicationContext, 0), new Random().nextInt(Math.max(1000, 1)) + TsNormalNum.THOUSAND_FIVE);
            }
        });
        return new n(6);
    }
}
