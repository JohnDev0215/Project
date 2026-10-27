package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.media.AudioManagerAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class UG extends BroadcastReceiver {
    public final /* synthetic */ AudioManagerAndroid a;

    public UG(AudioManagerAndroid audioManagerAndroid) {
        this.a = audioManagerAndroid;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
        if (intExtra == 0) {
            synchronized (this.a.j) {
                this.a.k[3] = false;
            }
        } else if (intExtra != 1) {
            if (intExtra != 2) {
                if (intExtra != 3) {
                    AudioManagerAndroid.g("Invalid state");
                }
            } else {
                synchronized (this.a.j) {
                    this.a.k[3] = true;
                }
            }
        }
    }
}
