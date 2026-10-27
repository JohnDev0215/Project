package com.amazon.mp3.video.stories;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt___StringsKt;

/* JADX INFO: compiled from: VideoStoryFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0005J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0006R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoryArtistMapProvider;", "", "()V", "artistMap", "", "", "", "getAsinFromWidgetId", "widgetId", "isArtistBeingFollowed", "artistAsin", "isArtistPresent", "setArtistFollowState", "", "followState", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoStoryArtistMapProvider {
    public static final VideoStoryArtistMapProvider INSTANCE = new VideoStoryArtistMapProvider();
    private static final Map<String, Boolean> artistMap = new LinkedHashMap();

    private VideoStoryArtistMapProvider() {
    }

    public final void setArtistFollowState(String artistAsin, boolean followState) {
        Intrinsics.checkNotNullParameter(artistAsin, "artistAsin");
        artistMap.put(artistAsin, Boolean.valueOf(followState));
    }

    public final boolean isArtistPresent(String artistAsin) {
        Intrinsics.checkNotNullParameter(artistAsin, "artistAsin");
        return artistMap.containsKey(artistAsin);
    }

    public final boolean isArtistBeingFollowed(String artistAsin) {
        Intrinsics.checkNotNullParameter(artistAsin, "artistAsin");
        return Intrinsics.areEqual(artistMap.get(artistAsin), Boolean.TRUE);
    }

    public final String getAsinFromWidgetId(String widgetId) {
        Intrinsics.checkNotNullParameter(widgetId, "widgetId");
        return StringsKt___StringsKt.drop(widgetId, StringsKt__StringsKt.lastIndexOf$default((CharSequence) widgetId, "-", 0, false, 6, (Object) null) + 1);
    }
}
