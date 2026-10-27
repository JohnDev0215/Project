package org.chromium.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: AudioDeviceListener.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a extends BroadcastReceiver {
    public final /* synthetic */ d a;

    public a(d dVar) {
        this.a = dVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("state", 0);
        d dVar = this.a;
        if (intExtra == 0) {
            dVar.f.a(1, false);
        } else if (intExtra == 1) {
            dVar.f.a(1, true);
        }
        e.this.e();
    }
}
