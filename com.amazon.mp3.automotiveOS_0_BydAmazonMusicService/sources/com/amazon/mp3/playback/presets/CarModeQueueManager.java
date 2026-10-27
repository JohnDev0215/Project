package com.amazon.mp3.playback.presets;

import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeQueueManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u001f\u0010 J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u0014\u0010\u0006\u001a\u00020\u00022\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u0006\u0010\n\u001a\u00020\u0002J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0016\u0010\u000b\u001a\u0004\u0018\u00010\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u0006\u0010\f\u001a\u00020\u0002R\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0011\u001a\n \u0010*\u0004\u0018\u00010\u00040\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R0\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0013j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R<\u0010\u0017\u001a*\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0004\u0012\u00020\u00040\u0013j\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0004\u0012\u00020\u0004`\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0012R\u001e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001d¨\u0006!"}, d2 = {"Lcom/amazon/mp3/playback/presets/CarModeQueueManager;", "", "", "registerListener", "", "asin", "setCurrentPreset", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "listPreset", "setNoCurrentPreset", "getCurrentTrack", "cleanup", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "mCurrentTracksSingleEntity", "Ljava/util/HashMap;", "mCurrentTracksMultipleEntity", "mCurrentPresetSingleEntity", "mCurrentPresetMultipleEntity", "Ljava/util/List;", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "mCurrentPlaybackListener", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "mPlaybackEventListener", "<init>", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarModeQueueManager {
    private final String TAG;
    private OnPlaybackEventListener mCurrentPlaybackListener;
    private List<? extends ContentMetadata> mCurrentPresetMultipleEntity;
    private String mCurrentPresetSingleEntity;
    private final HashMap<List<ContentMetadata>, String> mCurrentTracksMultipleEntity;
    private final HashMap<String, String> mCurrentTracksSingleEntity;
    private final OnPlaybackEventListener mPlaybackEventListener;
    private final PageType pageType;

    public CarModeQueueManager(PageType pageType) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.pageType = pageType;
        this.TAG = CarModeQueueManager.class.getSimpleName();
        this.mCurrentTracksSingleEntity = new HashMap<>();
        this.mCurrentTracksMultipleEntity = new HashMap<>();
        this.mPlaybackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.mp3.playback.presets.CarModeQueueManager$mPlaybackEventListener$1
            @Override // com.amazon.music.media.playback.OnPlaybackEventListener
            public void onPlaybackEvent(int event, ControlSource controlSource) {
                Intrinsics.checkNotNullParameter(controlSource, "controlSource");
                if (event == 1 || event == 2 || event == 11) {
                    MediaItem currentMediaItem = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI).getCurrentMediaItem();
                    if (currentMediaItem == null) {
                        Log.debug(this.this$0.TAG, "currentMediaItem is null, ignoring playback event");
                        return;
                    }
                    String id = currentMediaItem.getMediaItemId().getId();
                    Intrinsics.checkNotNullExpressionValue(id, "currentMediaItem.mediaItemId.id");
                    String str = this.this$0.mCurrentPresetSingleEntity;
                    if (str != null) {
                        this.this$0.mCurrentTracksSingleEntity.put(str, id);
                    }
                    List list = this.this$0.mCurrentPresetMultipleEntity;
                    if (list != null) {
                        CarModeQueueManager carModeQueueManager = this.this$0;
                        if (true ^ list.isEmpty()) {
                            carModeQueueManager.mCurrentTracksMultipleEntity.put(list, id);
                        }
                    }
                    Log.warning(this.this$0.TAG, "No preset selected but listener is still registered.");
                }
            }
        };
    }

    public final void setCurrentPreset(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        registerListener();
        this.mCurrentPresetSingleEntity = asin;
        this.mCurrentPresetMultipleEntity = null;
    }

    public final void setCurrentPreset(List<? extends ContentMetadata> listPreset) {
        Intrinsics.checkNotNullParameter(listPreset, "listPreset");
        registerListener();
        this.mCurrentPresetMultipleEntity = ImmutableList.copyOf((Collection) listPreset);
        this.mCurrentPresetSingleEntity = null;
    }

    public final void setNoCurrentPreset() {
        this.mCurrentPresetSingleEntity = null;
        this.mCurrentPresetMultipleEntity = null;
        this.mCurrentPlaybackListener = null;
        PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI).removeOnPlaybackEventListener(this.mPlaybackEventListener);
    }

    public final String getCurrentTrack(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        if (this.mCurrentTracksSingleEntity.containsKey(asin)) {
            return this.mCurrentTracksSingleEntity.get(asin);
        }
        return null;
    }

    public final String getCurrentTrack(List<? extends ContentMetadata> listPreset) {
        Intrinsics.checkNotNullParameter(listPreset, "listPreset");
        if (this.mCurrentTracksMultipleEntity.containsKey(listPreset)) {
            return this.mCurrentTracksMultipleEntity.get(listPreset);
        }
        return null;
    }

    public final void cleanup() {
        setNoCurrentPreset();
    }

    private final void registerListener() {
        if (this.mCurrentPlaybackListener != null) {
            return;
        }
        PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI).addOnPlaybackEventListener(this.mPlaybackEventListener);
        this.mCurrentPlaybackListener = this.mPlaybackEventListener;
    }
}
