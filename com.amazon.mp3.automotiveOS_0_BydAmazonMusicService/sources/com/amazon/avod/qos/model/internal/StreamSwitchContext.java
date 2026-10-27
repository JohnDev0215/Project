package com.amazon.avod.qos.model.internal;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class StreamSwitchContext {

    @Nonnull
    private final String mLanguage;

    @Nonnull
    private final String mNewAudioTrackId;

    @Nonnull
    private final String mNewLanguage;

    @Nonnull
    private final String mOldAudioTrackId;

    public StreamSwitchContext(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nonnull String str4) {
        this.mLanguage = (String) Preconditions.checkNotNull(str, "currentLanguage");
        this.mNewLanguage = (String) Preconditions.checkNotNull(str2, "newLanguage");
        this.mOldAudioTrackId = (String) Preconditions.checkNotNull(str3, "oldAudioTrackId");
        this.mNewAudioTrackId = (String) Preconditions.checkNotNull(str4, "newAudioTrackId");
    }

    @Nonnull
    public String getLanguage() {
        return this.mLanguage;
    }

    @Nonnull
    public String getOldAudioTrackId() {
        return this.mOldAudioTrackId;
    }

    @Nonnull
    public String getNewAudioTrackId() {
        return this.mNewAudioTrackId;
    }

    public String toString() {
        return String.format(Locale.US, "ChangeAudioTrack from: %s to %s; Currently playing audio track id: %s, switching to track id: %s", this.mLanguage, this.mNewLanguage, this.mOldAudioTrackId, this.mNewAudioTrackId);
    }
}
