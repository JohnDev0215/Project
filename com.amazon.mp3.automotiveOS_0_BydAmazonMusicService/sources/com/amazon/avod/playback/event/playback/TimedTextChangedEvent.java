package com.amazon.avod.playback.event.playback;

import android.text.TextUtils;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class TimedTextChangedEvent {
    private final boolean mIsSubtitlesEnabled;

    @Nullable
    private final String mLanguageCode;

    @Nullable
    private final AloysiusTimedTextSubtype mSubtitleSubtype;

    @Nullable
    private final AloysiusTimedTextType mSubtitleType;

    public enum AloysiusTimedTextSubtype {
        Dialog,
        Commentary,
        Descriptive
    }

    public enum AloysiusTimedTextType {
        Subtitle,
        Caption,
        ForcedNarrative
    }

    public TimedTextChangedEvent(boolean z, @Nullable String str, @Nullable AloysiusTimedTextType aloysiusTimedTextType, @Nullable AloysiusTimedTextSubtype aloysiusTimedTextSubtype) {
        this.mIsSubtitlesEnabled = z;
        this.mLanguageCode = str;
        this.mSubtitleType = aloysiusTimedTextType;
        this.mSubtitleSubtype = aloysiusTimedTextSubtype;
    }

    public boolean isSubtitlesEnabled() {
        return this.mIsSubtitlesEnabled;
    }

    @Nullable
    public String getLanguageCode() {
        return this.mLanguageCode;
    }

    @Nullable
    public AloysiusTimedTextType getSubtitleType() {
        return this.mSubtitleType;
    }

    @Nullable
    public AloysiusTimedTextSubtype getSubtitleSubtype() {
        return this.mSubtitleSubtype;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimedTextChangedEvent)) {
            return false;
        }
        TimedTextChangedEvent timedTextChangedEvent = (TimedTextChangedEvent) obj;
        return this.mIsSubtitlesEnabled == timedTextChangedEvent.mIsSubtitlesEnabled && TextUtils.equals(this.mLanguageCode, timedTextChangedEvent.mLanguageCode) && this.mSubtitleType == timedTextChangedEvent.mSubtitleType && this.mSubtitleSubtype == timedTextChangedEvent.mSubtitleSubtype;
    }

    public int hashCode() {
        return ("" + this.mIsSubtitlesEnabled + this.mLanguageCode + this.mSubtitleType + this.mSubtitleSubtype).hashCode();
    }
}
