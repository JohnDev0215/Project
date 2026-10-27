package com.amazon.avod.playbackclient.subtitle.internal;

import com.amazon.avod.core.Subtitle;
import com.amazon.avod.core.subtitle.SubtitleSubtype;
import com.amazon.avod.core.subtitle.SubtitleType;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleLanguage {
    private final String mDisplayName;
    private final boolean mIsForced;
    private final String mLanguageCode;
    private String mSampleText;
    private final SubtitleSubtype mSubtitleSubtype;
    private final String mTrackGroupId;
    private final SubtitleType mType;

    public SubtitleLanguage(@Nonnull Subtitle subtitle) {
        this(subtitle.getLanguageCode(), subtitle.getType(), subtitle.getSubType(), subtitle.getTrackGroupId(), subtitle.isForcedNarrative(), subtitle.getDisplayName());
    }

    public SubtitleLanguage(@Nonnull String str, @Nonnull SubtitleType subtitleType, @Nullable SubtitleSubtype subtitleSubtype, @Nullable String str2, boolean z, @Nonnull String str3) {
        this.mLanguageCode = (String) Preconditions.checkNotNull(str, "languageCode");
        this.mType = (SubtitleType) Preconditions.checkNotNull(subtitleType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        this.mSubtitleSubtype = subtitleSubtype;
        this.mTrackGroupId = str2;
        this.mIsForced = z;
        this.mDisplayName = (String) Preconditions.checkNotNull(str3, "displayName");
    }

    @Nonnull
    public String getLanguageCode() {
        return this.mLanguageCode;
    }

    @Nonnull
    public SubtitleType getSubtitleType() {
        return this.mType;
    }

    @Nullable
    public SubtitleSubtype getSubtitleSubtype() {
        return this.mSubtitleSubtype;
    }

    @Nullable
    public String getTrackGroupId() {
        return this.mTrackGroupId;
    }

    public boolean isForced() {
        return this.mIsForced;
    }

    @Nullable
    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[6];
        objArr[0] = this.mIsForced ? "Narrative" : "Subtitle";
        objArr[1] = this.mTrackGroupId;
        objArr[2] = this.mLanguageCode;
        objArr[3] = this.mType.name();
        objArr[4] = this.mDisplayName;
        objArr[5] = this.mSampleText;
        return String.format(locale, "%s trackGroupId(%s) language (%s) type (%s) DisplayName (%s) SampleText(%s)", objArr);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubtitleLanguage)) {
            return false;
        }
        SubtitleLanguage subtitleLanguage = (SubtitleLanguage) obj;
        return Objects.equal(this.mLanguageCode, subtitleLanguage.mLanguageCode) && Objects.equal(this.mTrackGroupId, subtitleLanguage.mTrackGroupId) && this.mType == subtitleLanguage.mType && Objects.equal(this.mDisplayName, subtitleLanguage.mDisplayName) && this.mIsForced == subtitleLanguage.mIsForced;
    }

    public boolean doesFitLanguagePreference(SubtitleLanguagePreference subtitleLanguagePreference) {
        return Objects.equal(this.mLanguageCode, subtitleLanguagePreference.getLanguageCode()) && this.mType == subtitleLanguagePreference.getSubtitleType();
    }

    public int hashCode() {
        return Objects.hashCode(this.mTrackGroupId, this.mLanguageCode, this.mType, Boolean.valueOf(this.mIsForced), this.mDisplayName);
    }
}
