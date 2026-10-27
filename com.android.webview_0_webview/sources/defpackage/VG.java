package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.media.AudioManagerAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VG extends BroadcastReceiver {
    public final /* synthetic */ AudioManagerAndroid a;

    public VG(AudioManagerAndroid audioManagerAndroid) {
        this.a = audioManagerAndroid;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", 0);
        if (intExtra == 0) {
            AudioManagerAndroid audioManagerAndroid = this.a;
            if (audioManagerAndroid.e != 3 && AudioManagerAndroid.b(audioManagerAndroid)) {
                AudioManagerAndroid.c(this.a);
            }
            this.a.e = 0;
            return;
        }
        if (intExtra == 1) {
            this.a.e = 1;
        } else if (intExtra != 2) {
            AudioManagerAndroid.g("Invalid state");
        }
    }
}
