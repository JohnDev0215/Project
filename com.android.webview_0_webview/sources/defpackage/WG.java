package defpackage;

import J.N;
import android.database.ContentObserver;
import android.os.Handler;
import org.chromium.media.AudioManagerAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WG extends ContentObserver {
    public final /* synthetic */ AudioManagerAndroid a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WG(AudioManagerAndroid audioManagerAndroid, Handler handler) {
        super(handler);
        this.a = audioManagerAndroid;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        super.onChange(z);
        int streamVolume = this.a.a.getStreamVolume(0);
        AudioManagerAndroid audioManagerAndroid = this.a;
        N.MCgftn_d(audioManagerAndroid.b, audioManagerAndroid, streamVolume == 0);
    }
}
