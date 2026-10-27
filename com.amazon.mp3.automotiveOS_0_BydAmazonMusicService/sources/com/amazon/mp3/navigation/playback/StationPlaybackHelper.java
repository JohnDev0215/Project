package com.amazon.mp3.navigation.playback;

import android.content.Context;
import androidx.annotation.Nullable;
import com.amazon.layout.music.model.StationHint;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.deeplink.StationItemProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.station.StationItem;

/* JADX INFO: loaded from: classes3.dex */
public class StationPlaybackHelper {
    private static final String TAG = "StationPlaybackHelper";
    private final Context mContext;

    public StationPlaybackHelper(final Context context) {
        this.mContext = context;
    }

    public void play(@Nullable final StationHint hint, final String stationKey, final boolean showNowPlaying, ControlSource controlSource, boolean isUserInitiated) {
        StationItem stationItemWithKey;
        Log.info(TAG, "Handle station deeplink:" + stationKey);
        ControlSource controlSource2 = controlSource == null ? ControlSource.EXTERNAL : controlSource;
        StationItemProvider stationItemProvider = new StationItemProvider();
        if (hint != null) {
            stationItemWithKey = stationItemProvider.getStationItemWithKey(hint.getStationKey(), hint.getTitle(), hint.getStationImageUrl());
        } else {
            stationItemWithKey = stationItemProvider.getStationItemWithKey(stationKey, "", "");
        }
        StationUtils.playStation(this.mContext, stationItemWithKey, controlSource2, true, showNowPlaying, PlaybackPageType.EXTERNAL, null, Clock.now(), null, null, null, false, isUserInitiated);
    }
}
