package p;

import android.media.AudioManager;
import com.spotify.audiomanager.audiomanagerproxy.AudioManagerProxyUser;

/* JADX INFO: loaded from: classes.dex */
public final class jd {
    public final AudioManagerProxyUser a = AudioManagerProxyUser.c;
    public final AudioManager.OnAudioFocusChangeListener b;

    public jd(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this.b = onAudioFocusChangeListener;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof jd)) {
            return false;
        }
        jd jdVar = (jd) obj;
        return this.a == jdVar.a && v03.b(this.b, jdVar.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = this.b;
        return iHashCode + (onAudioFocusChangeListener == null ? 0 : onAudioFocusChangeListener.hashCode());
    }

    public final String toString() {
        return "AudioFocusCallbacksKey(user=" + this.a + ", audioFocusChangeListener=" + this.b + ')';
    }
}
