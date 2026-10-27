package com.amazon.avod.playbackclient.subtitle.internal;

import com.amazon.avod.core.subtitle.SubtitleType;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleLanguagePreference {
    private final String mLanguageCode;
    private final SubtitleType mSubtitleType;

    public SubtitleLanguagePreference(@Nonnull String str, @Nonnull SubtitleType subtitleType) {
        this.mLanguageCode = (String) Preconditions.checkNotNull(str, "languageCode");
        this.mSubtitleType = (SubtitleType) Preconditions.checkNotNull(subtitleType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
    }

    @Nonnull
    public String getLanguageCode() {
        return this.mLanguageCode;
    }

    @Nonnull
    public SubtitleType getSubtitleType() {
        return this.mSubtitleType;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("languageCode", this.mLanguageCode).add("subtitleType", this.mSubtitleType.name()).toString();
    }
}
