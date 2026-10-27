package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AudioTrackChangeEvent extends PlaybackEvent {

    @Nullable
    private final String mAudioTrackId;

    @Nullable
    private final String mConsumptionId;

    @Nullable
    private final String mLanguageCode;

    public AudioTrackChangeEvent(@Nonnull TimeSpan timeSpan, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        super(timeSpan);
        this.mLanguageCode = str;
        this.mAudioTrackId = str3;
        this.mConsumptionId = str2;
    }

    @Nullable
    public String getAudioTrackId() {
        return this.mAudioTrackId;
    }

    @Nullable
    public String getLanguageCode() {
        return this.mLanguageCode;
    }

    @Override // com.amazon.avod.playback.event.PlaybackEvent
    @Nullable
    public String getConsumptionId() {
        return this.mConsumptionId;
    }
}
