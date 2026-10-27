package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Objects;
import org.chromium.media.AudioManagerAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TG extends BroadcastReceiver {
    public final /* synthetic */ AudioManagerAndroid a;

    public TG(AudioManagerAndroid audioManagerAndroid) {
        this.a = audioManagerAndroid;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("state", 0);
        if (intExtra == 0) {
            synchronized (this.a.j) {
                this.a.k[1] = false;
                if (this.a.e()) {
                    this.a.k[4] = true;
                    this.a.k[2] = false;
                } else {
                    Objects.requireNonNull(this.a);
                    if (Is.a.getPackageManager().hasSystemFeature("android.hardware.telephony")) {
                        this.a.k[2] = true;
                        this.a.k[4] = false;
                    }
                }
            }
        } else if (intExtra != 1) {
            AudioManagerAndroid.g("Invalid state");
        } else {
            synchronized (this.a.j) {
                this.a.k[1] = true;
                this.a.k[2] = false;
                this.a.k[4] = false;
            }
        }
        if (AudioManagerAndroid.b(this.a)) {
            AudioManagerAndroid.c(this.a);
        }
    }
}
