package p;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import com.spotify.audiomanager.audiomanagerproxy.AudioManagerProxyUser;
import com.spotify.messages.AudioManagerProxyEvent;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class md {
    public final AudioManager a;
    public final id b;
    public final ConcurrentHashMap c;

    public md(AudioManager audioManager, id idVar) {
        this.a = audioManager;
        this.b = idVar;
        new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
    }

    public final void a(pv0 pv0Var) {
        v03.j(pv0Var, "audioFocusRequest");
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = (AudioManager.OnAudioFocusChangeListener) this.c.remove(new jd((AudioManager.OnAudioFocusChangeListener) pv0Var.e));
        if (onAudioFocusChangeListener == null) {
            return;
        }
        AudioFocusRequest audioFocusRequestC = ci1.c(pv0Var, onAudioFocusChangeListener);
        id idVar = this.b;
        idVar.getClass();
        AudioManagerProxyUser audioManagerProxyUser = AudioManagerProxyUser.c;
        hd hdVarM = AudioManagerProxyEvent.m();
        hdVarM.f(audioManagerProxyUser.a());
        hdVarM.l("ABANDON_AUDIO_FOCUS");
        idVar.a.a(hdVarM.build());
        this.a.abandonAudioFocusRequest(audioFocusRequestC);
    }
}
