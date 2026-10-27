package android.media.soundtrigger_middleware;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.FileDescriptor;

/* JADX INFO: loaded from: classes2.dex */
public class SoundModel implements Parcelable {
    public static final Parcelable.Creator<SoundModel> CREATOR = new Parcelable.Creator<SoundModel>() { // from class: android.media.soundtrigger_middleware.SoundModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SoundModel createFromParcel(Parcel parcel) {
            SoundModel soundModel = new SoundModel();
            soundModel.readFromParcel(parcel);
            return soundModel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SoundModel[] newArray(int i) {
            return new SoundModel[i];
        }
    };
    public FileDescriptor data;
    public int dataSize;
    public int type;
    public String uuid;
    public String vendorUuid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.type);
        parcel.writeString(this.uuid);
        parcel.writeString(this.vendorUuid);
        parcel.writeRawFileDescriptor(this.data);
        parcel.writeInt(this.dataSize);
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
                this.uuid = parcel.readString();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.vendorUuid = parcel.readString();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.data = parcel.readRawFileDescriptor();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.dataSize = parcel.readInt();
                            if (parcel.dataPosition() - iDataPosition < i) {
                            }
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
