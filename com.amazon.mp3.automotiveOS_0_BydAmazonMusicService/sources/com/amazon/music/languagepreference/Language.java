package com.amazon.music.languagepreference;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class Language implements Parcelable {
    public static final Parcelable.Creator<Language> CREATOR = new Parcelable.Creator<Language>() { // from class: com.amazon.music.languagepreference.Language.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Language createFromParcel(Parcel parcel) {
            return new Language(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Language[] newArray(int i) {
            return new Language[i];
        }
    };
    private final String displayName;
    private final String languageCode;
    private final String nativeScript;
    private final int rank;
    private boolean userPreferred;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Language(String str, String str2, String str3, int i, boolean z) {
        this.languageCode = str;
        this.displayName = str2;
        this.nativeScript = str3;
        this.rank = i;
        this.userPreferred = z;
    }

    private Language(Parcel parcel) {
        this.nativeScript = parcel.readString();
        this.displayName = parcel.readString();
        this.languageCode = parcel.readString();
        this.rank = parcel.readInt();
        this.userPreferred = parcel.readInt() == 1;
    }

    public String getNativeScript() {
        return this.nativeScript;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getLanguageCode() {
        return this.languageCode;
    }

    public void setUserPreferred(boolean z) {
        this.userPreferred = z;
    }

    public int getRank() {
        return this.rank;
    }

    public boolean getUserPreferred() {
        return this.userPreferred;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.nativeScript);
        parcel.writeString(this.displayName);
        parcel.writeString(this.languageCode);
        parcel.writeInt(this.rank);
        parcel.writeInt(this.userPreferred ? 1 : 0);
    }
}
