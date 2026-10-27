package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.media.g;

/* JADX INFO: compiled from: AudioDeviceSelectorPreS.java */
/* JADX INFO: loaded from: classes3.dex */
public final class l9 extends BroadcastReceiver {
    public final /* synthetic */ g a;

    public l9(g gVar) {
        this.a = gVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", 0);
        g gVar = this.a;
        if (intExtra != 0) {
            if (intExtra != 1) {
                return;
            }
            gVar.d = 1;
        } else {
            if (gVar.d != 3) {
                gVar.e();
            }
            gVar.d = 0;
        }
    }
}
