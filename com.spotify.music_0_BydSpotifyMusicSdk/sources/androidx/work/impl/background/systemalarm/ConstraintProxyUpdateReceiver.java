package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import p.jq;
import p.vc1;
import p.wx2;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintProxyUpdateReceiver extends BroadcastReceiver {
    public static final /* synthetic */ int a = 0;

    static {
        vc1.d("ConstrntProxyUpdtRecvr");
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (!"androidx.work.impl.background.systemalarm.UpdateProxies".equals(intent != null ? intent.getAction() : null)) {
            vc1.c().getClass();
            return;
        }
        BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
        wx2.E(context).j.a(new jq(this, intent, context, pendingResultGoAsync, 1));
    }
}
