package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class DataLoaderParamsParcel implements Parcelable {
    public static final Parcelable.Creator<DataLoaderParamsParcel> CREATOR = new Parcelable.Creator<DataLoaderParamsParcel>() { // from class: android.content.pm.DataLoaderParamsParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataLoaderParamsParcel createFromParcel(Parcel parcel) {
            DataLoaderParamsParcel dataLoaderParamsParcel = new DataLoaderParamsParcel();
            dataLoaderParamsParcel.readFromParcel(parcel);
            return dataLoaderParamsParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataLoaderParamsParcel[] newArray(int i) {
            return new DataLoaderParamsParcel[i];
        }
    };
    public String arguments;
    public String className;
    public String packageName;
    public int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.type);
        parcel.writeString(this.packageName);
        parcel.writeString(this.className);
        parcel.writeString(this.arguments);
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
            this.type = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.packageName = parcel.readString();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.className = parcel.readString();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.arguments = parcel.readString();
                        if (parcel.dataPosition() - iDataPosition < i) {
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
