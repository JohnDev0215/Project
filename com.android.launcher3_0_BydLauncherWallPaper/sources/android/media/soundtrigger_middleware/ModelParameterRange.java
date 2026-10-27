package android.media.soundtrigger_middleware;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class ModelParameterRange implements Parcelable {
    public static final Parcelable.Creator<ModelParameterRange> CREATOR = new Parcelable.Creator<ModelParameterRange>() { // from class: android.media.soundtrigger_middleware.ModelParameterRange.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModelParameterRange createFromParcel(Parcel parcel) {
            ModelParameterRange modelParameterRange = new ModelParameterRange();
            modelParameterRange.readFromParcel(parcel);
            return modelParameterRange;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModelParameterRange[] newArray(int i) {
            return new ModelParameterRange[i];
        }
    };
    public int maxInclusive;
    public int minInclusive;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.minInclusive);
        parcel.writeInt(this.maxInclusive);
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    public final void readFromParcel(Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        int i = parcel.readInt();
        if (i < 0) {
            return;
        }
        try {
            this.minInclusive = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.maxInclusive = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
