package android.media.soundtrigger_middleware;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class RecognitionConfig implements Parcelable {
    public static final Parcelable.Creator<RecognitionConfig> CREATOR = new Parcelable.Creator<RecognitionConfig>() { // from class: android.media.soundtrigger_middleware.RecognitionConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecognitionConfig createFromParcel(Parcel parcel) {
            RecognitionConfig recognitionConfig = new RecognitionConfig();
            recognitionConfig.readFromParcel(parcel);
            return recognitionConfig;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecognitionConfig[] newArray(int i) {
            return new RecognitionConfig[i];
        }
    };
    public int audioCapabilities;
    public boolean captureRequested;
    public byte[] data;
    public PhraseRecognitionExtra[] phraseRecognitionExtras;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.captureRequested ? 1 : 0);
        parcel.writeTypedArray(this.phraseRecognitionExtras, 0);
        parcel.writeInt(this.audioCapabilities);
        parcel.writeByteArray(this.data);
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
            this.captureRequested = parcel.readInt() != 0;
            if (parcel.dataPosition() - iDataPosition < i) {
                this.phraseRecognitionExtras = (PhraseRecognitionExtra[]) parcel.createTypedArray(PhraseRecognitionExtra.CREATOR);
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.audioCapabilities = parcel.readInt();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.data = parcel.createByteArray();
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
