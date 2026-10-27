package android.media.soundtrigger_middleware;

import android.media.audio.common.AudioConfig;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class RecognitionEvent implements Parcelable {
    public static final Parcelable.Creator<RecognitionEvent> CREATOR = new Parcelable.Creator<RecognitionEvent>() { // from class: android.media.soundtrigger_middleware.RecognitionEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecognitionEvent createFromParcel(Parcel parcel) {
            RecognitionEvent recognitionEvent = new RecognitionEvent();
            recognitionEvent.readFromParcel(parcel);
            return recognitionEvent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecognitionEvent[] newArray(int i) {
            return new RecognitionEvent[i];
        }
    };
    public AudioConfig audioConfig;
    public boolean captureAvailable;
    public int captureDelayMs;
    public int capturePreambleMs;
    public int captureSession;
    public byte[] data;
    public int status;
    public boolean triggerInData;
    public int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.status);
        parcel.writeInt(this.type);
        parcel.writeInt(this.captureAvailable ? 1 : 0);
        parcel.writeInt(this.captureSession);
        parcel.writeInt(this.captureDelayMs);
        parcel.writeInt(this.capturePreambleMs);
        parcel.writeInt(this.triggerInData ? 1 : 0);
        if (this.audioConfig != null) {
            parcel.writeInt(1);
            this.audioConfig.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
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
            this.status = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.type = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    boolean z = true;
                    this.captureAvailable = parcel.readInt() != 0;
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.captureSession = parcel.readInt();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.captureDelayMs = parcel.readInt();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                this.capturePreambleMs = parcel.readInt();
                                if (parcel.dataPosition() - iDataPosition < i) {
                                    if (parcel.readInt() == 0) {
                                        z = false;
                                    }
                                    this.triggerInData = z;
                                    if (parcel.dataPosition() - iDataPosition < i) {
                                        if (parcel.readInt() != 0) {
                                            this.audioConfig = AudioConfig.CREATOR.createFromParcel(parcel);
                                        } else {
                                            this.audioConfig = null;
                                        }
                                        if (parcel.dataPosition() - iDataPosition < i) {
                                            this.data = parcel.createByteArray();
                                            if (parcel.dataPosition() - iDataPosition < i) {
                                            }
                                        }
                                    }
                                }
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
