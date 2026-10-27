package com.amazon.avod.playbackclient.subtitle.internal;

import com.amazon.avod.core.subtitle.SubtitleType;
import com.amazon.avod.playbackclient.subtitle.SubtitlePresetSource;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitlePreferences {
    private boolean mEnabled;
    private int mFontScaleInPercent;
    private String mLanguageCode;
    private int mPresetNumber;
    private SubtitlePresetSource mPresetSource;
    private boolean mSettingsEnabled;
    private final SubtitleTextSizeRationalizer mTextSizeRationalizer;
    private SubtitleType mType;

    public SubtitlePreferences() {
        this(new SubtitleTextSizeRationalizer());
    }

    SubtitlePreferences(@Nonnull SubtitleTextSizeRationalizer subtitleTextSizeRationalizer) {
        this.mSettingsEnabled = true;
        this.mLanguageCode = null;
        this.mType = null;
        this.mFontScaleInPercent = 100;
        this.mPresetSource = SubtitlePresetSource.DEFAULT;
        this.mPresetNumber = 0;
        this.mTextSizeRationalizer = (SubtitleTextSizeRationalizer) Preconditions.checkNotNull(subtitleTextSizeRationalizer, "textSizeRationalizer");
    }

    public boolean areSubtitlesEnabled() {
        return this.mEnabled;
    }

    public void setSubtitlesEnabled(boolean z) {
        this.mEnabled = z;
    }

    public boolean areSubtitlesSettingsEnabled() {
        return this.mSettingsEnabled;
    }

    public void setSubtitlesSettingsEnabled(boolean z) {
        this.mSettingsEnabled = z;
    }

    @Nonnull
    public SubtitleLanguagePreference getLanguage() {
        return new SubtitleLanguagePreference(this.mLanguageCode, this.mType);
    }

    @Nullable
    public String getLanguageCode() {
        String str = this.mLanguageCode;
        if (str != null) {
            return str.toLowerCase(Locale.US);
        }
        return null;
    }

    public void setLanguageCode(@Nonnull String str) {
        this.mLanguageCode = (String) Preconditions.checkNotNull(str);
    }

    @Nullable
    public SubtitleType getSubtitleType() {
        return this.mType;
    }

    public void setSubtitleType(@Nonnull SubtitleType subtitleType) {
        this.mType = (SubtitleType) Preconditions.checkNotNull(subtitleType);
    }

    public int getFontScaleInPercent() {
        return this.mFontScaleInPercent;
    }

    public void setFontScaleInPercent(int i) {
        this.mFontScaleInPercent = this.mTextSizeRationalizer.rationalize(i);
    }

    @Nonnull
    public SubtitlePresetSource getPresetSource() {
        return this.mPresetSource;
    }

    public void setPresetSource(@Nonnull SubtitlePresetSource subtitlePresetSource) {
        this.mPresetSource = (SubtitlePresetSource) Preconditions.checkNotNull(subtitlePresetSource);
    }

    public int getPresetNumber() {
        return this.mPresetNumber;
    }

    public void setPresetNumber(int i) {
        this.mPresetNumber = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubtitlePreferences)) {
            return false;
        }
        SubtitlePreferences subtitlePreferences = (SubtitlePreferences) obj;
        return Objects.equal(Boolean.valueOf(this.mEnabled), Boolean.valueOf(subtitlePreferences.mEnabled)) && Objects.equal(this.mLanguageCode, subtitlePreferences.mLanguageCode) && Objects.equal(this.mPresetSource, subtitlePreferences.mPresetSource) && Objects.equal(Integer.valueOf(this.mPresetNumber), Integer.valueOf(subtitlePreferences.mPresetNumber)) && Objects.equal(Integer.valueOf(this.mFontScaleInPercent), Integer.valueOf(subtitlePreferences.mFontScaleInPercent)) && Objects.equal(this.mType, subtitlePreferences.mType);
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.mEnabled), this.mLanguageCode, this.mPresetSource, Integer.valueOf(this.mPresetNumber), Integer.valueOf(this.mFontScaleInPercent), this.mType);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("enabled", this.mEnabled).add("languageCode", this.mLanguageCode).add("presetSource", this.mPresetSource).add("presetNumber", this.mPresetNumber).add("fontScaleInPercent", this.mFontScaleInPercent).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.mType).toString();
    }
}
