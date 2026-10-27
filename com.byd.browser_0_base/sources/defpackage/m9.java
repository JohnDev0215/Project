package defpackage;

import J.N;
import android.database.ContentObserver;
import android.os.Handler;
import org.chromium.media.AudioManagerAndroid;

/* JADX INFO: compiled from: AudioManagerAndroid.java */
/* JADX INFO: loaded from: classes3.dex */
public final class m9 extends ContentObserver {
    public final /* synthetic */ AudioManagerAndroid a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m9(AudioManagerAndroid audioManagerAndroid, Handler handler) {
        super(handler);
        this.a = audioManagerAndroid;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        AudioManagerAndroid audioManagerAndroid = this.a;
        N.MCgftn_d(audioManagerAndroid.b, audioManagerAndroid, audioManagerAndroid.a.getStreamVolume(0) == 0);
    }
}
