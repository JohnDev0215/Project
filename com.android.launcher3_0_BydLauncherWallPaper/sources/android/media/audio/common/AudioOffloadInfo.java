package android.media.audio.common;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class AudioOffloadInfo implements Parcelable {
    public static final Parcelable.Creator<AudioOffloadInfo> CREATOR = new Parcelable.Creator<AudioOffloadInfo>() { // from class: android.media.audio.common.AudioOffloadInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioOffloadInfo createFromParcel(Parcel parcel) {
            AudioOffloadInfo audioOffloadInfo = new AudioOffloadInfo();
            audioOffloadInfo.readFromParcel(parcel);
            return audioOffloadInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioOffloadInfo[] newArray(int i) {
            return new AudioOffloadInfo[i];
        }
    };
    public int bitRatePerSecond;
    public int bitWidth;
    public int bufferSize;
    public int channelMask;
    public long durationMicroseconds;
    public int format;
    public boolean hasVideo;
    public boolean isStreaming;
    public int sampleRateHz;
    public int streamType;
    public int usage;

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
        parcel.writeInt(this.streamType);
        parcel.writeInt(this.bitRatePerSecond);
        parcel.writeLong(this.durationMicroseconds);
        parcel.writeInt(this.hasVideo ? 1 : 0);
        parcel.writeInt(this.isStreaming ? 1 : 0);
        parcel.writeInt(this.bitWidth);
        parcel.writeInt(this.bufferSize);
        parcel.writeInt(this.usage);
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
                        this.streamType = parcel.readInt();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.bitRatePerSecond = parcel.readInt();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                this.durationMicroseconds = parcel.readLong();
                                if (parcel.dataPosition() - iDataPosition < i) {
                                    boolean z = true;
                                    this.hasVideo = parcel.readInt() != 0;
                                    if (parcel.dataPosition() - iDataPosition < i) {
                                        if (parcel.readInt() == 0) {
                                            z = false;
                                        }
                                        this.isStreaming = z;
                                        if (parcel.dataPosition() - iDataPosition < i) {
                                            this.bitWidth = parcel.readInt();
                                            if (parcel.dataPosition() - iDataPosition < i) {
                                                this.bufferSize = parcel.readInt();
                                                if (parcel.dataPosition() - iDataPosition < i) {
                                                    this.usage = parcel.readInt();
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
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
