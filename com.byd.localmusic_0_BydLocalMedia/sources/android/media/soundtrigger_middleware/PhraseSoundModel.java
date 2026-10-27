package android.media.soundtrigger_middleware;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class PhraseSoundModel implements Parcelable {
    public static final Parcelable.Creator<PhraseSoundModel> CREATOR = new Parcelable.Creator<PhraseSoundModel>() { // from class: android.media.soundtrigger_middleware.PhraseSoundModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PhraseSoundModel createFromParcel(Parcel parcel) {
            PhraseSoundModel phraseSoundModel = new PhraseSoundModel();
            phraseSoundModel.readFromParcel(parcel);
            return phraseSoundModel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PhraseSoundModel[] newArray(int i) {
            return new PhraseSoundModel[i];
        }
    };
    public SoundModel common;
    public Phrase[] phrases;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        if (this.common != null) {
            parcel.writeInt(1);
            this.common.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeTypedArray(this.phrases, 0);
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
            if (parcel.readInt() != 0) {
                this.common = SoundModel.CREATOR.createFromParcel(parcel);
            } else {
                this.common = null;
            }
            if (parcel.dataPosition() - iDataPosition < i) {
                this.phrases = (Phrase[]) parcel.createTypedArray(Phrase.CREATOR);
                if (parcel.dataPosition() - iDataPosition < i) {
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
