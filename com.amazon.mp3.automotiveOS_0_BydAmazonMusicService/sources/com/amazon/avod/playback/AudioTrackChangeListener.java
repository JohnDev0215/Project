package com.amazon.avod.playback;

import com.google.common.base.Optional;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface AudioTrackChangeListener {

    public enum Status {
        SUCCESS,
        FAILURE
    }

    void onAudioTrackChangeCompleted(@Nonnull Status status);

    void onAudioTrackChangeStarted(@Nonnull Optional<String> optional, @Nonnull Optional<String> optional2);
}
