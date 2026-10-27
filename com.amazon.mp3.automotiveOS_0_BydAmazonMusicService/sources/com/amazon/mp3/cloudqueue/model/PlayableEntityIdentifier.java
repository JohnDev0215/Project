package com.amazon.mp3.cloudqueue.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class PlayableEntityIdentifier implements Parcelable {
    public static final Parcelable.Creator<PlayableEntityIdentifier> CREATOR = new Parcelable.Creator<PlayableEntityIdentifier>() { // from class: com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlayableEntityIdentifier createFromParcel(Parcel in) {
            return new PlayableEntityIdentifier(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlayableEntityIdentifier[] newArray(int size) {
            return new PlayableEntityIdentifier[size];
        }
    };
    String identifier;
    PlayableEntityIdentifierType playableEntityIdentifierType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlayableEntityIdentifier(String identifier, PlayableEntityIdentifierType playableEntityIdentifierType) {
        this.identifier = identifier;
        this.playableEntityIdentifierType = playableEntityIdentifierType;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public PlayableEntityIdentifierType getPlayableEntityIdentifierType() {
        return this.playableEntityIdentifierType;
    }

    protected PlayableEntityIdentifier(Parcel in) {
        this.identifier = in.readString();
        this.playableEntityIdentifierType = (PlayableEntityIdentifierType) in.readValue(PlayableEntityIdentifierType.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.identifier);
        dest.writeValue(this.playableEntityIdentifierType);
    }
}
