package l3;

import android.media.session.MediaSession;
import android.os.Bundle;
import com.stingray.karaoke.car.player.PlayerService;

/* JADX INFO: loaded from: classes.dex */
public final class L extends K {
    @Override // l3.InterfaceC1744I
    public final void a(T t10) {
    }

    @Override // l3.K
    public final MediaSession c(PlayerService playerService, String str, Bundle bundle) {
        return new MediaSession(playerService, str, bundle);
    }
}
