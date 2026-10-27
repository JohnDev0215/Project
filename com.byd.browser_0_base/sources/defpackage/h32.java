package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: PowerSavingModeMonitor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h32 extends BroadcastReceiver {
    public final /* synthetic */ i32 a;

    public h32(i32 i32Var) {
        this.a = i32Var;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.a.b();
    }
}
