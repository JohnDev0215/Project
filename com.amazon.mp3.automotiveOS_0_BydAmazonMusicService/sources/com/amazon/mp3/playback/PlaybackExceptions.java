package com.amazon.mp3.playback;

import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.metrics.CloudQueueFlexMetricsManager;
import com.amazon.mp3.featuregate.Feature;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: PlaybackExceptions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/playback/PlaybackExceptions;", "", "()V", "exceptionList", "", "Lcom/amazon/mp3/playback/ExceptionData;", "getExceptionList", "()Ljava/util/List;", "getMessageResourceId", "", "exception", "Exceptions", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaybackExceptions {
    public static final PlaybackExceptions INSTANCE = new PlaybackExceptions();
    private static final List<ExceptionData> exceptionList;

    public final int getMessageResourceId(int exception) {
        if (exception == 180) {
            return R.string.dmusic_playback_generic_error_buydrm_player_dialog_detail;
        }
        switch (exception) {
            case 243:
                return R.string.dmusic_playback_cq_interaction_pattern_error_buydrm_player_dialog_detail;
            case 244:
                return R.string.dmusic_playback_track_list_error_buydrm_player_dialog_detail;
            case 245:
                return R.string.dmusic_playback_legacy_mpqs_station_support_error_buydrm_player_dialog_detail;
            default:
                return R.string.dmusic_playback_error_media_player_dialog_header;
        }
    }

    private PlaybackExceptions() {
    }

    /* JADX INFO: compiled from: PlaybackExceptions.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/playback/PlaybackExceptions$Exceptions;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "PLAYBACK_EXCEPTION", "CQ_INTERACTION_PATTERN", "NULL_TRACK_LIST", "NO_MPQS_LEGACY_SUPPORT", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum Exceptions {
        PLAYBACK_EXCEPTION(180),
        CQ_INTERACTION_PATTERN(243),
        NULL_TRACK_LIST(244),
        NO_MPQS_LEGACY_SUPPORT(245);

        private final int value;

        Exceptions(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }
    }

    public final List<ExceptionData> getExceptionList() {
        return exceptionList;
    }

    static {
        int value = Exceptions.CQ_INTERACTION_PATTERN.getValue();
        Feature feature = Feature.error_cloud_queue_flex_event_enabled;
        exceptionList = CollectionsKt__CollectionsKt.listOf((Object[]) new ExceptionData[]{new ExceptionData(Exceptions.PLAYBACK_EXCEPTION.getValue(), new PlaybackExceptions$exceptionList$1(Feature.error_180_flex_event_enabled), CloudQueueFlexMetricsManager.Event.PLAYBACK_EXCEPTION), new ExceptionData(value, new PlaybackExceptions$exceptionList$2(feature), CloudQueueFlexMetricsManager.Event.CQ_INTERACTION_PATTERN), new ExceptionData(Exceptions.NULL_TRACK_LIST.getValue(), new PlaybackExceptions$exceptionList$3(feature), CloudQueueFlexMetricsManager.Event.NULL_TRACK_LIST), new ExceptionData(Exceptions.NO_MPQS_LEGACY_SUPPORT.getValue(), new PlaybackExceptions$exceptionList$4(feature), CloudQueueFlexMetricsManager.Event.NO_MPQS_LEGACY_SUPPORT)});
    }
}
