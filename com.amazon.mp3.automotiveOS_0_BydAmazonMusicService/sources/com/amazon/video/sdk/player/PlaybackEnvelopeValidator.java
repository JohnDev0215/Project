package com.amazon.video.sdk.player;

import kotlin.Metadata;

/* JADX INFO: compiled from: PlaybackEnvelopeValidator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u0005J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/video/sdk/player/PlaybackEnvelopeValidator;", "", "validate", "Lcom/amazon/video/sdk/player/PlaybackEnvelope;", "playbackEnvelope", "PlaybackEnvelopeValidationException", "ATVPlaybackFramework_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface PlaybackEnvelopeValidator {

    /* JADX INFO: compiled from: PlaybackEnvelopeValidator.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/video/sdk/player/PlaybackEnvelopeValidator$PlaybackEnvelopeValidationException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "ATVPlaybackFramework_release"}, k = 1, mv = {1, 6, 0})
    public static final class PlaybackEnvelopeValidationException extends Exception {
    }

    PlaybackEnvelope validate(PlaybackEnvelope playbackEnvelope) throws PlaybackEnvelopeValidationException;
}
