package com.amazon.avod.content;

import com.amazon.avod.media.playback.VideoSpecification;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class PlayableContent {
    private final Optional<String> mAudioTrackId;
    private final int mHashCode;
    private final boolean mIsTrailer;
    private final String mTitleId;
    private final String mUniqueIdentifier;

    public PlayableContent(String str, boolean z) {
        this(str, z, Optional.absent());
    }

    public PlayableContent(String str, boolean z, @Nonnull Optional<String> optional) {
        this.mTitleId = str;
        this.mIsTrailer = z;
        Optional<String> optional2 = (Optional) Preconditions.checkNotNull(optional);
        this.mAudioTrackId = optional2;
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        objArr[0] = str;
        objArr[1] = z ? "-T" : "";
        this.mUniqueIdentifier = String.format(locale, "%s%s", objArr);
        this.mHashCode = Objects.hashCode(Boolean.valueOf(z), str, optional2);
    }

    public String getTitleId() {
        return this.mTitleId;
    }

    public boolean isTrailer() {
        return this.mIsTrailer;
    }

    @Nonnull
    public Optional<String> getAudioTrackId() {
        return this.mAudioTrackId;
    }

    public static PlayableContent newPlayableContent(@Nonnull VideoSpecification videoSpecification) {
        Optional optionalOf;
        if (videoSpecification.getAudioTrackIds().size() == 0) {
            optionalOf = Optional.absent();
        } else {
            optionalOf = Optional.of(videoSpecification.getAudioTrackIds().get(0));
        }
        return new PlayableContent(videoSpecification.getTitleId(), videoSpecification.isTrailer(), optionalOf);
    }

    public int hashCode() {
        return this.mHashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayableContent)) {
            return false;
        }
        PlayableContent playableContent = (PlayableContent) obj;
        return this.mIsTrailer == playableContent.mIsTrailer && this.mTitleId.equals(playableContent.mTitleId) && this.mAudioTrackId.equals(playableContent.getAudioTrackId());
    }

    @Nonnull
    public String getUniqueIdentifier() {
        return this.mUniqueIdentifier;
    }

    public String toString() {
        return getUniqueIdentifier();
    }
}
