package android.media.soundtrigger_middleware;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class SoundTriggerModuleProperties implements Parcelable {
    public static final Parcelable.Creator<SoundTriggerModuleProperties> CREATOR = new Parcelable.Creator<SoundTriggerModuleProperties>() { // from class: android.media.soundtrigger_middleware.SoundTriggerModuleProperties.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SoundTriggerModuleProperties createFromParcel(Parcel parcel) {
            SoundTriggerModuleProperties soundTriggerModuleProperties = new SoundTriggerModuleProperties();
            soundTriggerModuleProperties.readFromParcel(parcel);
            return soundTriggerModuleProperties;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SoundTriggerModuleProperties[] newArray(int i) {
            return new SoundTriggerModuleProperties[i];
        }
    };
    public int audioCapabilities;
    public boolean captureTransition;
    public boolean concurrentCapture;
    public String description;
    public String implementor;
    public int maxBufferMs;
    public int maxKeyPhrases;
    public int maxSoundModels;
    public int maxUsers;
    public int powerConsumptionMw;
    public int recognitionModes;
    public String supportedModelArch;
    public boolean triggerInEvent;
    public String uuid;
    public int version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.implementor);
        parcel.writeString(this.description);
        parcel.writeInt(this.version);
        parcel.writeString(this.uuid);
        parcel.writeString(this.supportedModelArch);
        parcel.writeInt(this.maxSoundModels);
        parcel.writeInt(this.maxKeyPhrases);
        parcel.writeInt(this.maxUsers);
        parcel.writeInt(this.recognitionModes);
        parcel.writeInt(this.captureTransition ? 1 : 0);
        parcel.writeInt(this.maxBufferMs);
        parcel.writeInt(this.concurrentCapture ? 1 : 0);
        parcel.writeInt(this.triggerInEvent ? 1 : 0);
        parcel.writeInt(this.powerConsumptionMw);
        parcel.writeInt(this.audioCapabilities);
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
            this.implementor = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.description = parcel.readString();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.version = parcel.readInt();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.uuid = parcel.readString();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.supportedModelArch = parcel.readString();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                this.maxSoundModels = parcel.readInt();
                                if (parcel.dataPosition() - iDataPosition < i) {
                                    this.maxKeyPhrases = parcel.readInt();
                                    if (parcel.dataPosition() - iDataPosition < i) {
                                        this.maxUsers = parcel.readInt();
                                        if (parcel.dataPosition() - iDataPosition < i) {
                                            this.recognitionModes = parcel.readInt();
                                            if (parcel.dataPosition() - iDataPosition < i) {
                                                boolean z = true;
                                                this.captureTransition = parcel.readInt() != 0;
                                                if (parcel.dataPosition() - iDataPosition < i) {
                                                    this.maxBufferMs = parcel.readInt();
                                                    if (parcel.dataPosition() - iDataPosition < i) {
                                                        this.concurrentCapture = parcel.readInt() != 0;
                                                        if (parcel.dataPosition() - iDataPosition < i) {
                                                            if (parcel.readInt() == 0) {
                                                                z = false;
                                                            }
                                                            this.triggerInEvent = z;
                                                            if (parcel.dataPosition() - iDataPosition < i) {
                                                                this.powerConsumptionMw = parcel.readInt();
                                                                if (parcel.dataPosition() - iDataPosition < i) {
                                                                    this.audioCapabilities = parcel.readInt();
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
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
