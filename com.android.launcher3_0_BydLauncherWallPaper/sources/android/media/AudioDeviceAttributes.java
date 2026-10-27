package android.media;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class AudioDeviceAttributes implements Parcelable {
    public static final Parcelable.Creator<AudioDeviceAttributes> CREATOR = new Parcelable.Creator<AudioDeviceAttributes>() { // from class: android.media.AudioDeviceAttributes.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioDeviceAttributes createFromParcel(Parcel parcel) {
            return new AudioDeviceAttributes(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioDeviceAttributes[] newArray(int i) {
            return new AudioDeviceAttributes[i];
        }
    };
    public static final int ROLE_INPUT = 1;
    public static final int ROLE_OUTPUT = 2;
    private final String mAddress;
    private final int mRole;
    private final int mType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Role {
    }

    public static String roleToString(int i) {
        return i == 2 ? "output" : "input";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @SystemApi
    public AudioDeviceAttributes(AudioDeviceInfo audioDeviceInfo) {
        Objects.requireNonNull(audioDeviceInfo);
        this.mRole = audioDeviceInfo.isSink() ? 2 : 1;
        this.mType = audioDeviceInfo.getType();
        this.mAddress = audioDeviceInfo.getAddress();
    }

    @SystemApi
    public AudioDeviceAttributes(int i, int i2, String str) {
        Objects.requireNonNull(str);
        if (i != 2 && i != 1) {
            throw new IllegalArgumentException("Invalid role " + i);
        }
        if (i == 2) {
            AudioDeviceInfo.enforceValidAudioDeviceTypeOut(i2);
        }
        if (i == 1) {
            AudioDeviceInfo.enforceValidAudioDeviceTypeIn(i2);
        }
        this.mRole = i;
        this.mType = i2;
        this.mAddress = str;
    }

    AudioDeviceAttributes(int i, String str) {
        this.mRole = (Integer.MIN_VALUE & i) != 0 ? 1 : 2;
        this.mType = AudioDeviceInfo.convertInternalDeviceToDeviceType(i);
        this.mAddress = str;
    }

    @SystemApi
    public int getRole() {
        return this.mRole;
    }

    @SystemApi
    public int getType() {
        return this.mType;
    }

    @SystemApi
    public String getAddress() {
        return this.mAddress;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mRole), Integer.valueOf(this.mType), this.mAddress);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AudioDeviceAttributes audioDeviceAttributes = (AudioDeviceAttributes) obj;
        return this.mRole == audioDeviceAttributes.mRole && this.mType == audioDeviceAttributes.mType && this.mAddress.equals(audioDeviceAttributes.mAddress);
    }

    public String toString() {
        String inputDeviceName;
        StringBuilder sbAppend = new StringBuilder().append("AudioDeviceAttributes: role:").append(roleToString(this.mRole)).append(" type:");
        if (this.mRole == 2) {
            inputDeviceName = AudioSystem.getOutputDeviceName(AudioDeviceInfo.convertDeviceTypeToInternalDevice(this.mType));
        } else {
            inputDeviceName = AudioSystem.getInputDeviceName(AudioDeviceInfo.convertDeviceTypeToInternalDevice(this.mType));
        }
        return new String(sbAppend.append(inputDeviceName).append(" addr:").append(this.mAddress).toString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mRole);
        parcel.writeInt(this.mType);
        parcel.writeString(this.mAddress);
    }

    private AudioDeviceAttributes(Parcel parcel) {
        this.mRole = parcel.readInt();
        this.mType = parcel.readInt();
        this.mAddress = parcel.readString();
    }
}
