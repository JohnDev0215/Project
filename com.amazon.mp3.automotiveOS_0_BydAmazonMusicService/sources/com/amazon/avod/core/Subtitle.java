package com.amazon.avod.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.avod.core.subtitle.SubtitleFormat;
import com.amazon.avod.core.subtitle.SubtitleSubtype;
import com.amazon.avod.core.subtitle.SubtitleType;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class Subtitle implements Parcelable {
    public static final Parcelable.Creator<Subtitle> CREATOR = new Parcelable.Creator<Subtitle>() { // from class: com.amazon.avod.core.Subtitle.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Subtitle createFromParcel(Parcel parcel) {
            return new Subtitle(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Subtitle[] newArray(int i) {
            return new Subtitle[i];
        }
    };
    private final String mDisplayName;
    private final SubtitleFormat mFormat;
    private final boolean mIsForcedNarrative;
    private final String mLanguageCode;
    private final SubtitleSubtype mSubType;
    private final String mTrackGroupId;
    private final SubtitleType mType;
    private final String mURL;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @JsonCreator
    public Subtitle(@JsonProperty("displayName") @Nonnull String str, @JsonProperty("languageCode") @Nonnull String str2, @JsonProperty(ImagesContract.URL) @Nonnull String str3, @JsonProperty(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE) @Nonnull String str4, @JsonProperty("subType") @Nonnull String str5, @JsonProperty("format") @Nonnull String str6, @JsonProperty("trackGroupId") @Nullable String str7, @JsonProperty("forcedNarrative") boolean z) {
        this.mDisplayName = (String) Preconditions.checkNotNull(str, "displayName");
        this.mLanguageCode = ((String) Preconditions.checkNotNull(str2, "languageCode")).toLowerCase(Locale.US);
        this.mURL = (String) Preconditions.checkNotNull(str3, "URL");
        this.mType = SubtitleType.lookup((String) Preconditions.checkNotNull(str4, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE));
        this.mSubType = SubtitleSubtype.lookup((String) Preconditions.checkNotNull(str5, "subtype"));
        this.mFormat = SubtitleFormat.lookup((String) Preconditions.checkNotNull(str6, "format"));
        this.mTrackGroupId = str7;
        this.mIsForcedNarrative = z;
    }

    @Nonnull
    public String getDisplayName() {
        return this.mDisplayName;
    }

    @Nonnull
    public String getLanguageCode() {
        return this.mLanguageCode;
    }

    @Nonnull
    public String getURL() {
        return this.mURL;
    }

    @Nullable
    public String getTrackGroupId() {
        return this.mTrackGroupId;
    }

    public boolean isForcedNarrative() {
        return this.mIsForcedNarrative;
    }

    public SubtitleType getType() {
        return this.mType;
    }

    public SubtitleSubtype getSubType() {
        return this.mSubType;
    }

    public SubtitleFormat getFormat() {
        return this.mFormat;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("displayName", this.mDisplayName).add("languageCode", this.mLanguageCode).add("URL", this.mURL).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.mType).add("subtype", this.mSubType).add("format", this.mFormat).add("trackGroupId", this.mTrackGroupId).add("isForced", this.mIsForcedNarrative).toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Subtitle)) {
            return false;
        }
        Subtitle subtitle = (Subtitle) obj;
        return Objects.equal(this.mDisplayName, subtitle.getDisplayName()) && Objects.equal(this.mLanguageCode, subtitle.getLanguageCode()) && Objects.equal(this.mURL, subtitle.getURL()) && Objects.equal(this.mType, subtitle.getType()) && Objects.equal(this.mSubType, subtitle.getSubType()) && Objects.equal(this.mFormat, subtitle.getFormat()) && Objects.equal(this.mTrackGroupId, subtitle.getTrackGroupId()) && this.mIsForcedNarrative == subtitle.isForcedNarrative();
    }

    public int hashCode() {
        return Objects.hashCode(this.mDisplayName, this.mLanguageCode, this.mURL, this.mType, this.mSubType, this.mFormat, this.mTrackGroupId, Boolean.valueOf(this.mIsForcedNarrative));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDisplayName);
        parcel.writeString(this.mLanguageCode);
        parcel.writeString(this.mURL);
        parcel.writeString(this.mType.name());
        parcel.writeString(this.mSubType.name());
        parcel.writeString(this.mFormat.name());
        parcel.writeString(this.mTrackGroupId);
        parcel.writeInt(this.mIsForcedNarrative ? 1 : 0);
    }

    private Subtitle(Parcel parcel) {
        this.mDisplayName = parcel.readString();
        this.mLanguageCode = parcel.readString();
        this.mURL = parcel.readString();
        this.mType = SubtitleType.valueOf(parcel.readString());
        this.mSubType = SubtitleSubtype.valueOf(parcel.readString());
        this.mFormat = SubtitleFormat.valueOf(parcel.readString());
        this.mTrackGroupId = parcel.readString();
        this.mIsForcedNarrative = parcel.readInt() == 1;
    }
}
