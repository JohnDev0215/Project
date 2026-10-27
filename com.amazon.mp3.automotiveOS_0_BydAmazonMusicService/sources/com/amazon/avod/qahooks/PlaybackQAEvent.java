package com.amazon.avod.qahooks;

import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.qahooks.QALog;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.firebase.perf.metrics.resource.ResourceType;

/* JADX INFO: loaded from: classes2.dex */
public enum PlaybackQAEvent implements QALog.QALoggableEvent {
    DOWNLOAD_STARTED("Download", "Start"),
    DOWNLOAD_FINISHED("Download", "Finish"),
    DOWNLOAD_PAUSED("Download", "Pause"),
    DOWNLOAD_ERROR("Download", "Error"),
    DOWNLOAD_PROGRESS_UPDATE("Download", "ProgressUpdate"),
    DOWNLOAD_ACQUIRE_LICENSE("Download", "AcquireLicense"),
    DOWNLOAD_RELEASE_RIGHT_QUEUED("Download", "ReleaseRightQueued"),
    DOWNLOAD_RELEASE_RIGHT_SENDING("Download", "ReleaseRightSending"),
    DOWNLOAD_RELEASE_RIGHT_CALL_SUCCESS("Download", "ReleaseRightCallSuccess"),
    DOWNLOAD_RELEASE_RIGHT_CALL_FAILURE("Download", "ReleaseRightCallFailure"),
    DOWNLOAD_STATE_TRANSITION("Download", "StateTransition"),
    DOWNLOAD_STORAGE_LOCATION("Download", "StorageLocation"),
    BUFFERING_STARTED("Buffering", "Start"),
    BUFFERING_ENDED("Buffering", "End"),
    PLAYBACK_STARTED("Playback", "Start"),
    PLAYBACK_STOP("Playback", "Stop"),
    PLAYBACK_RESUME("Playback", "Resume"),
    PLAYBACK_PAUSE("Playback", "Pause"),
    PLAYBACK_BITRATE_CHANGED("Playback", "BitrateChanged"),
    PLAYBACK_AUDIO_CHANGED("Playback", "AudioChanged"),
    PLAYBACK_AUDIO_TRACK_CHANGED("Playback", "AudioTrackChanged"),
    PLAYBACK_ACQUIRE_LICENSE("Playback", "AcquireLicense"),
    UNEXPECTED_PLAYBACK_FAILURE("Playback", "Unexpected Failure"),
    PLAYBACK_AUDIO_BITRATE_CHANGED("Playback", "AudioBitrateChanged"),
    PLAYBACK_AUDIO_CODEC_CHANGED("Playback", "AudioCodecChanged"),
    PLAYBACK_VIDEO_CODEC_CHANGED("Playback", "VideoCodecChanged"),
    PLAYBACK_SUBTITLE_MENU_VISIBILITY("Playback", "SubtitleMenuVisibility"),
    PLAYBACK_SUBTITLES_CHANGED("Playback", "SubtitlesChanged"),
    PLAYBACK_GET_SUBTITLES_LIST("Playback", "AvailableSubtitleLanguageCodes"),
    PLAYBACK_JUMP_TO_LIVE_ENABLED("Playback", "JumpToLiveEnabled"),
    PLAYBACK_IS_LIVE("Playback", "isLive"),
    WATCH_FROM_BEGINNING_AVAILABLE("Playback", "WatchFromBeginningAvailable"),
    NEXT_UP_AVAILABLE("Playback", "NextUpAvailable"),
    NEXT_UP_CARD_SHOWN("Playback", "NextUpCardShown"),
    HEURISTIC_NETWORK_INFO_SAVE("Heuristic", "NetworkInfoSave"),
    HEURISTIC_NETWORK_INFO_LOAD("Heuristic", "NetworkInfoLoad"),
    PLAYBACK_HEURISTICS_SETTINGS_DETAILS("Playback", "Heuristics settings details"),
    PLAYBACK_HEURISTICS_SETTINGS_PARSE_ERROR("Playback", "Heuristics settings parse error"),
    PARSE_VIDEO_METADATA("Parse", "VideoMetadata"),
    MEDIASYSTEM_INITIALIZE("MediaSystem", "Initialize"),
    RENDERER_REUSE_NOT_SUPPORTED("RendererReuse", VideoRenderer.RendererStatus.RENDERER_REUSE_NOT_SUPPORTED),
    RENDERER_NOT_REUSED("RendererReuse", VideoRenderer.RendererStatus.RENDERER_NOT_REUSED),
    RENDERER_REUSED("RendererReuse", VideoRenderer.RendererStatus.RENDERER_REUSED),
    CONNECTION_STATE(ResourceType.NETWORK, "ConnectivityState"),
    CONNECTION_CHANGE(ResourceType.NETWORK, "ConnectivityChange"),
    AUDIT_PING_SENT("AuditPing", "PingSuccess"),
    AUDIT_PING_ERROR("AuditPing", "PingNetworkError");

    private final String mEvent;
    private final String mFeature;

    public QALog.QALoggableEvent getQAEvent() {
        return this;
    }

    PlaybackQAEvent(String str, String str2) {
        this.mFeature = str;
        this.mEvent = str2;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mFeature + (Attributes.PREDEFINED_ATTRIBUTE_PREFIX + this.mEvent);
    }
}
