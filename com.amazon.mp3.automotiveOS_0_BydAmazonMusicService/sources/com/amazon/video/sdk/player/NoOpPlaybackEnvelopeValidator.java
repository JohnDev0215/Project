package com.amazon.video.sdk.player;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoOpPlaybackEnvelopeValidator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/amazon/video/sdk/player/NoOpPlaybackEnvelopeValidator;", "Lcom/amazon/video/sdk/player/PlaybackEnvelopeValidator;", "()V", "validate", "Lcom/amazon/video/sdk/player/PlaybackEnvelope;", "playbackEnvelope", "ATVPlaybackFramework_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NoOpPlaybackEnvelopeValidator implements PlaybackEnvelopeValidator {
    @Override // com.amazon.video.sdk.player.PlaybackEnvelopeValidator
    public PlaybackEnvelope validate(PlaybackEnvelope playbackEnvelope) {
        Intrinsics.checkNotNullParameter(playbackEnvelope, "playbackEnvelope");
        return playbackEnvelope;
    }
}
