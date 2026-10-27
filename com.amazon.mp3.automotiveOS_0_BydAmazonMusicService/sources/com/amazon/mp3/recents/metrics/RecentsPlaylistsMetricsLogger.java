package com.amazon.mp3.recents.metrics;

import android.content.Context;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentsPlaylistsMetricsLogger.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0011\u0010\u0012J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/recents/metrics/RecentsPlaylistsMetricsLogger;", "", "", "playlistId", "", "sendUIPageViewMetrics", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "sendUIClickMetrics", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "", "TAG", "Ljava/lang/String;", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RecentsPlaylistsMetricsLogger {
    private final String TAG;
    private final Context context;

    public RecentsPlaylistsMetricsLogger(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        String simpleName = RecentsPlaylistsMetricsLogger.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "RecentsPlaylistsMetricsL…er::class.java.simpleName");
        this.TAG = simpleName;
    }

    public final void sendUIPageViewMetrics(long playlistId) {
        PageType pageType;
        if (playlistId == 3) {
            pageType = PageType.RECENTLY_PLAYED_SONGS;
        } else if (playlistId == 1) {
            pageType = PageType.RECENTLY_ADDED_SONGS;
        } else if (playlistId == 2) {
            pageType = PageType.RECENTLY_DOWNLOADED_SONGS;
        } else {
            Log.error(this.TAG, Intrinsics.stringPlus("UiPageView, unknown Recents Playlist selection. playlist ID: ", Long.valueOf(playlistId)));
            return;
        }
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(pageType.getMetricsValue()).withViewType(ScreenUtil.getScreenViewType(this.context)).build());
    }

    public final void sendUIClickMetrics(long playlistId, PageType pageType) {
        ActionType actionType;
        if (playlistId == 3) {
            actionType = ActionType.GO_SONG_HISTORY;
        } else if (playlistId == 1) {
            actionType = ActionType.GO_RECENTLY_ADDED;
        } else if (playlistId == 2) {
            actionType = ActionType.GO_RECENTLY_DOWNLOADED;
        } else {
            Log.error(this.TAG, Intrinsics.stringPlus("UiClick, Unknown Recents Playlist selection. playlist ID: ", Long.valueOf(playlistId)));
            return;
        }
        UiClickEvent.Builder builder = UiClickEvent.builder(actionType);
        if (pageType != null) {
            builder.withPageType(pageType);
        }
        MetricsHolder.getManager().handleEvent(builder.build());
    }
}
