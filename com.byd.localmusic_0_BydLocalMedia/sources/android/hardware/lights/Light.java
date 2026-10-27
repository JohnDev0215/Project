package android.hardware.lights;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class Light implements Parcelable {
    public static final Parcelable.Creator<Light> CREATOR = new Parcelable.Creator<Light>() { // from class: android.hardware.lights.Light.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Light createFromParcel(Parcel parcel) {
            return new Light(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Light[] newArray(int i) {
            return new Light[i];
        }
    };
    private final int mId;
    private final int mOrdinal;
    private final int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Light(int i, int i2, int i3) {
        this.mId = i;
        this.mOrdinal = i2;
        this.mType = i3;
    }

    private Light(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mOrdinal = parcel.readInt();
        this.mType = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mOrdinal);
        parcel.writeInt(this.mType);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Light)) {
            return false;
        }
        Light light = (Light) obj;
        return this.mId == light.mId && this.mOrdinal == light.mOrdinal && this.mType == light.mType;
    }

    public int hashCode() {
        return this.mId;
    }

    public int getId() {
        return this.mId;
    }

    public int getOrdinal() {
        return this.mOrdinal;
    }

    public int getType() {
        return this.mType;
    }
}
