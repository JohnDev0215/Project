package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Objects;
import p.vc1;
import p.wx2;

/* JADX INFO: loaded from: classes.dex */
public class RescheduleReceiver extends BroadcastReceiver {
    public static final String a = vc1.d("RescheduleReceiver");

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        vc1 vc1VarC = vc1.c();
        Objects.toString(intent);
        vc1VarC.getClass();
        try {
            wx2 wx2VarE = wx2.E(context);
            BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
            synchronized (wx2.s) {
                try {
                    BroadcastReceiver.PendingResult pendingResult = wx2VarE.o;
                    if (pendingResult != null) {
                        pendingResult.finish();
                    }
                    wx2VarE.o = pendingResultGoAsync;
                    if (wx2VarE.n) {
                        pendingResultGoAsync.finish();
                        wx2VarE.o = null;
                    }
                } finally {
                }
            }
        } catch (IllegalStateException e) {
            vc1.c().b(a, "Cannot reschedule jobs. WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", e);
        }
    }
}
