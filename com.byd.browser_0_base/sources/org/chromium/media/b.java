package org.chromium.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: AudioDeviceListener.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b extends BroadcastReceiver {
    public final /* synthetic */ d a;

    public b(d dVar) {
        this.a = dVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
        d dVar = this.a;
        if (intExtra == 0) {
            dVar.f.a(3, false);
            e.this.e();
        } else {
            if (intExtra != 2) {
                return;
            }
            dVar.f.a(3, true);
            e.this.e();
        }
    }
}
