package android.hardware.lights;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class LightState implements Parcelable {
    public static final Parcelable.Creator<LightState> CREATOR = new Parcelable.Creator<LightState>() { // from class: android.hardware.lights.LightState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LightState createFromParcel(Parcel parcel) {
            return new LightState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LightState[] newArray(int i) {
            return new LightState[i];
        }
    };
    private final int mColor;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LightState(int i) {
        this.mColor = i;
    }

    private LightState(Parcel parcel) {
        this.mColor = parcel.readInt();
    }

    public int getColor() {
        return this.mColor;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mColor);
    }
}
