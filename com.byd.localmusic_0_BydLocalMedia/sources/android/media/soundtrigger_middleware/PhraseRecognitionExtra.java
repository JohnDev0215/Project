package android.media.soundtrigger_middleware;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class PhraseRecognitionExtra implements Parcelable {
    public static final Parcelable.Creator<PhraseRecognitionExtra> CREATOR = new Parcelable.Creator<PhraseRecognitionExtra>() { // from class: android.media.soundtrigger_middleware.PhraseRecognitionExtra.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PhraseRecognitionExtra createFromParcel(Parcel parcel) {
            PhraseRecognitionExtra phraseRecognitionExtra = new PhraseRecognitionExtra();
            phraseRecognitionExtra.readFromParcel(parcel);
            return phraseRecognitionExtra;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PhraseRecognitionExtra[] newArray(int i) {
            return new PhraseRecognitionExtra[i];
        }
    };
    public int confidenceLevel;
    public int id;
    public ConfidenceLevel[] levels;
    public int recognitionModes;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.id);
        parcel.writeInt(this.recognitionModes);
        parcel.writeInt(this.confidenceLevel);
        parcel.writeTypedArray(this.levels, 0);
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
            this.id = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.recognitionModes = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.confidenceLevel = parcel.readInt();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.levels = (ConfidenceLevel[]) parcel.createTypedArray(ConfidenceLevel.CREATOR);
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
