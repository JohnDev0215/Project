package p;

import android.media.AudioManager;
import com.spotify.audiomanager.audiomanagerproxy.AudioManagerProxyUser;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public final class ld implements Function {
    public final /* synthetic */ md a;
    public final /* synthetic */ AudioManagerProxyUser b;
    public final /* synthetic */ AudioManager.OnAudioFocusChangeListener c;

    public ld(md mdVar, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        AudioManagerProxyUser audioManagerProxyUser = AudioManagerProxyUser.c;
        this.a = mdVar;
        this.b = audioManagerProxyUser;
        this.c = onAudioFocusChangeListener;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        v03.j((jd) obj, "it");
        return new kd(this.a, this.b, this.c);
    }
}
