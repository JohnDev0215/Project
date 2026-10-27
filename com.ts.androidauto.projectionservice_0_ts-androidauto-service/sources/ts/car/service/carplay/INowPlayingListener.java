package ts.car.service.carplay;

import java.util.List;
import ts.car.service.carplay.data.NowPlayingInfo;
import ts.car.service.carplay.data.PlaybackMediaItemInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface INowPlayingListener {
    void onNowPlayingInfoChanged(NowPlayingInfo nowPlayingInfo);

    void onPlaybackListUpdated(int i, int i2, List<PlaybackMediaItemInfo> list);

    default void onArtworkUpdate(NowPlayingInfo info) {
    }
}
