package ts.car.service.carplay;

import java.util.List;
import ts.car.service.carplay.data.NowPlayingInfo;
import ts.car.service.carplay.data.PlaybackMediaItemInfo;

/* JADX INFO: loaded from: classes5.dex */
public interface INowPlayingListener {
    default void onArtworkUpdate(NowPlayingInfo nowPlayingInfo) {
    }

    void onNowPlayingInfoChanged(NowPlayingInfo nowPlayingInfo);

    void onPlaybackListUpdated(int i, int i2, List<PlaybackMediaItemInfo> list);
}
