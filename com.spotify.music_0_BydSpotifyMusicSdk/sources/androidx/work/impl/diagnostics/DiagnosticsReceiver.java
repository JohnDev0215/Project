package androidx.work.impl.diagnostics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.impl.workers.DiagnosticsWorker;
import p.lq1;
import p.vc1;
import p.wx2;

/* JADX INFO: loaded from: classes.dex */
public class DiagnosticsReceiver extends BroadcastReceiver {
    public static final String a = vc1.d("DiagnosticsRcvr");

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        vc1.c().getClass();
        try {
            wx2.E(context).i(new lq1(DiagnosticsWorker.class).a());
        } catch (IllegalStateException e) {
            vc1.c().b(a, "WorkManager is not initialized", e);
        }
    }
}
