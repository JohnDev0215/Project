package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: SystemAccountManagerDelegate.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ay2 extends BroadcastReceiver {
    public final /* synthetic */ by2 a;

    public ay2(by2 by2Var) {
        this.a = by2Var;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.a.b.F();
    }
}
