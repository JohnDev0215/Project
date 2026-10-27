package android.media.audio.common;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class AudioConfig implements Parcelable {
    public static final Parcelable.Creator<AudioConfig> CREATOR = new Parcelable.Creator<AudioConfig>() { // from class: android.media.audio.common.AudioConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioConfig createFromParcel(Parcel parcel) {
            AudioConfig audioConfig = new AudioConfig();
            audioConfig.readFromParcel(parcel);
            return audioConfig;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioConfig[] newArray(int i) {
            return new AudioConfig[i];
        }
    };
    public int channelMask;
    public int format;
    public long frameCount;
    public AudioOffloadInfo offloadInfo;
    public int sampleRateHz;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.sampleRateHz);
        parcel.writeInt(this.channelMask);
        parcel.writeInt(this.format);
        if (this.offloadInfo != null) {
            parcel.writeInt(1);
            this.offloadInfo.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeLong(this.frameCount);
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
            this.sampleRateHz = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.channelMask = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.format = parcel.readInt();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        if (parcel.readInt() != 0) {
                            this.offloadInfo = AudioOffloadInfo.CREATOR.createFromParcel(parcel);
                        } else {
                            this.offloadInfo = null;
                        }
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.frameCount = parcel.readLong();
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
