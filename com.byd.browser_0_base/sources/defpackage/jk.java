package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;

/* JADX INFO: compiled from: BydPCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class jk extends BroadcastReceiver {
    public final /* synthetic */ ik a;

    public jk(ik ikVar) {
        this.a = ikVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        boolean zEquals = "android.net.wifi.STATE_CHANGE".equals(intent.getAction());
        ik ikVar = this.a;
        if (zEquals) {
            ikVar.a((NetworkInfo) intent.getParcelableExtra("networkInfo"), true);
        } else {
            ikVar.a(null, false);
        }
    }
}
