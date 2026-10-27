package p;

import android.media.AudioManager;
import com.spotify.audiomanager.audiomanagerproxy.AudioManagerProxyUser;
import com.spotify.messages.AudioManagerProxyEvent;

/* JADX INFO: loaded from: classes.dex */
public final class kd implements AudioManager.OnAudioFocusChangeListener {
    public final /* synthetic */ md a;
    public final /* synthetic */ AudioManagerProxyUser b;
    public final /* synthetic */ AudioManager.OnAudioFocusChangeListener c;

    public /* synthetic */ kd(md mdVar, AudioManagerProxyUser audioManagerProxyUser, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this.a = mdVar;
        this.b = audioManagerProxyUser;
        this.c = onAudioFocusChangeListener;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public final void onAudioFocusChange(int i) {
        String str;
        id idVar = this.a.b;
        idVar.getClass();
        AudioManagerProxyUser audioManagerProxyUser = this.b;
        v03.j(audioManagerProxyUser, "user");
        hd hdVarM = AudioManagerProxyEvent.m();
        hdVarM.f(audioManagerProxyUser.a());
        hdVarM.l("FOCUS_CHANGED");
        if (i == -3) {
            str = "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK";
        } else if (i == -2) {
            str = "AUDIOFOCUS_LOSS_TRANSIENT";
        } else if (i == -1) {
            str = "AUDIOFOCUS_LOSS";
        } else if (i != 1) {
            str = "UNKNOWN(" + i + ')';
        } else {
            str = "AUDIOFOCUS_GAIN";
        }
        hdVarM.h(str);
        idVar.a.a(hdVarM.build());
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = this.c;
        if (onAudioFocusChangeListener != null) {
            onAudioFocusChangeListener.onAudioFocusChange(i);
        }
    }
}
