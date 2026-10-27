package com.amazon.mp3.api.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
class LoaderContext implements Parcelable {
    public static final Parcelable.Creator<LoaderContext> CREATOR = new Parcelable.Creator<LoaderContext>() { // from class: com.amazon.mp3.api.data.LoaderContext.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LoaderContext createFromParcel(Parcel in) {
            return new LoaderContext(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LoaderContext[] newArray(int size) {
            return new LoaderContext[size];
        }
    };
    private int mFlags;
    private final int mId;
    private final Bundle mParameters;
    private final int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return this.mId;
    }

    public int getType() {
        return this.mType;
    }

    public Bundle getParameters() {
        if (this.mParameters == null) {
            return null;
        }
        return new Bundle(this.mParameters);
    }

    public LoaderContext(int id, int type, Bundle params, int flags) {
        this.mId = id;
        this.mType = type;
        this.mParameters = params == null ? null : new Bundle(params);
        this.mFlags = flags;
    }

    public boolean hasFlag(int flag) {
        return (this.mFlags & flag) == flag;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeInt(this.mType);
        dest.writeBundle(this.mParameters);
        dest.writeInt(this.mFlags);
    }

    private LoaderContext(Parcel in) {
        this.mId = in.readInt();
        this.mType = in.readInt();
        this.mParameters = in.readBundle();
        this.mFlags = in.readInt();
    }
}
