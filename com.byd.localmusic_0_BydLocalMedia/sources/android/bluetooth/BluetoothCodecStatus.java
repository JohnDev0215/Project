package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothCodecStatus implements Parcelable {
    public static final Parcelable.Creator<BluetoothCodecStatus> CREATOR = new Parcelable.Creator<BluetoothCodecStatus>() { // from class: android.bluetooth.BluetoothCodecStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothCodecStatus createFromParcel(Parcel parcel) {
            return new BluetoothCodecStatus((BluetoothCodecConfig) parcel.readTypedObject(BluetoothCodecConfig.CREATOR), (BluetoothCodecConfig[]) parcel.createTypedArray(BluetoothCodecConfig.CREATOR), (BluetoothCodecConfig[]) parcel.createTypedArray(BluetoothCodecConfig.CREATOR));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothCodecStatus[] newArray(int i) {
            return new BluetoothCodecStatus[i];
        }
    };
    public static final String EXTRA_CODEC_STATUS = "android.bluetooth.extra.CODEC_STATUS";
    private final BluetoothCodecConfig mCodecConfig;
    private final BluetoothCodecConfig[] mCodecsLocalCapabilities;
    private final BluetoothCodecConfig[] mCodecsSelectableCapabilities;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BluetoothCodecStatus(BluetoothCodecConfig bluetoothCodecConfig, BluetoothCodecConfig[] bluetoothCodecConfigArr, BluetoothCodecConfig[] bluetoothCodecConfigArr2) {
        this.mCodecConfig = bluetoothCodecConfig;
        this.mCodecsLocalCapabilities = bluetoothCodecConfigArr;
        this.mCodecsSelectableCapabilities = bluetoothCodecConfigArr2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BluetoothCodecStatus)) {
            return false;
        }
        BluetoothCodecStatus bluetoothCodecStatus = (BluetoothCodecStatus) obj;
        return Objects.equals(bluetoothCodecStatus.mCodecConfig, this.mCodecConfig) && sameCapabilities(bluetoothCodecStatus.mCodecsLocalCapabilities, this.mCodecsLocalCapabilities) && sameCapabilities(bluetoothCodecStatus.mCodecsSelectableCapabilities, this.mCodecsSelectableCapabilities);
    }

    public static boolean sameCapabilities(BluetoothCodecConfig[] bluetoothCodecConfigArr, BluetoothCodecConfig[] bluetoothCodecConfigArr2) {
        if (bluetoothCodecConfigArr == null) {
            return bluetoothCodecConfigArr2 == null;
        }
        if (bluetoothCodecConfigArr2 != null && bluetoothCodecConfigArr.length == bluetoothCodecConfigArr2.length) {
            return Arrays.asList(bluetoothCodecConfigArr).containsAll(Arrays.asList(bluetoothCodecConfigArr2));
        }
        return false;
    }

    public boolean isCodecConfigSelectable(BluetoothCodecConfig bluetoothCodecConfig) {
        if (bluetoothCodecConfig != null && bluetoothCodecConfig.hasSingleSampleRate() && bluetoothCodecConfig.hasSingleBitsPerSample() && bluetoothCodecConfig.hasSingleChannelMode()) {
            for (BluetoothCodecConfig bluetoothCodecConfig2 : this.mCodecsSelectableCapabilities) {
                if (bluetoothCodecConfig.getCodecType() == bluetoothCodecConfig2.getCodecType()) {
                    int sampleRate = bluetoothCodecConfig.getSampleRate();
                    if ((bluetoothCodecConfig2.getSampleRate() & sampleRate) != 0 || sampleRate == 0) {
                        int bitsPerSample = bluetoothCodecConfig.getBitsPerSample();
                        if ((bluetoothCodecConfig2.getBitsPerSample() & bitsPerSample) != 0 || bitsPerSample == 0) {
                            int channelMode = bluetoothCodecConfig.getChannelMode();
                            if ((bluetoothCodecConfig2.getChannelMode() & channelMode) != 0 || channelMode == 0) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        BluetoothCodecConfig[] bluetoothCodecConfigArr = this.mCodecsLocalCapabilities;
        return Objects.hash(this.mCodecConfig, bluetoothCodecConfigArr, bluetoothCodecConfigArr);
    }

    public String toString() {
        return "{mCodecConfig:" + this.mCodecConfig + ",mCodecsLocalCapabilities:" + Arrays.toString(this.mCodecsLocalCapabilities) + ",mCodecsSelectableCapabilities:" + Arrays.toString(this.mCodecsSelectableCapabilities) + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedObject(this.mCodecConfig, 0);
        parcel.writeTypedArray(this.mCodecsLocalCapabilities, 0);
        parcel.writeTypedArray(this.mCodecsSelectableCapabilities, 0);
    }

    public BluetoothCodecConfig getCodecConfig() {
        return this.mCodecConfig;
    }

    public BluetoothCodecConfig[] getCodecsLocalCapabilities() {
        return this.mCodecsLocalCapabilities;
    }

    public BluetoothCodecConfig[] getCodecsSelectableCapabilities() {
        return this.mCodecsSelectableCapabilities;
    }
}
