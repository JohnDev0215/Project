package android.media;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes2.dex */
public class AudioDevicePort extends AudioPort {
    private final String mAddress;
    private final int[] mEncapsulationMetadataTypes;
    private final int[] mEncapsulationModes;
    private final int mType;

    AudioDevicePort(AudioHandle audioHandle, String str, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, AudioGain[] audioGainArr, int i, String str2, int[] iArr5, int[] iArr6) {
        super(audioHandle, AudioManager.isInputDevice(i) ? 1 : 2, str, iArr, iArr2, iArr3, iArr4, audioGainArr);
        this.mType = i;
        this.mAddress = str2;
        this.mEncapsulationModes = iArr5;
        this.mEncapsulationMetadataTypes = iArr6;
    }

    public int type() {
        return this.mType;
    }

    public String address() {
        return this.mAddress;
    }

    public int[] encapsulationModes() {
        int[] iArr = this.mEncapsulationModes;
        return iArr == null ? new int[0] : Arrays.stream(iArr).boxed().filter(new Predicate() { // from class: android.media.-$$Lambda$AudioDevicePort$seMFZuMR9bUiEzD-eQz1grS86fI
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AudioDevicePort.lambda$encapsulationModes$0((Integer) obj);
            }
        }).mapToInt(new ToIntFunction() { // from class: android.media.-$$Lambda$AudioDevicePort$UV1wDVoVlbcxpr8zevj_aMFtUGw
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).toArray();
    }

    static /* synthetic */ boolean lambda$encapsulationModes$0(Integer num) {
        return num.intValue() != 2;
    }

    public int[] encapsulationMetadataTypes() {
        int[] iArr = this.mEncapsulationMetadataTypes;
        if (iArr == null) {
            return new int[0];
        }
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    @Override // android.media.AudioPort
    public AudioDevicePortConfig buildConfig(int i, int i2, int i3, AudioGainConfig audioGainConfig) {
        return new AudioDevicePortConfig(this, i, i2, i3, audioGainConfig);
    }

    @Override // android.media.AudioPort
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AudioDevicePort)) {
            return false;
        }
        AudioDevicePort audioDevicePort = (AudioDevicePort) obj;
        if (this.mType != audioDevicePort.type()) {
            return false;
        }
        if ((this.mAddress != null || audioDevicePort.address() == null) && this.mAddress.equals(audioDevicePort.address())) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // android.media.AudioPort
    public String toString() {
        String outputDeviceName;
        if (this.mRole == 1) {
            outputDeviceName = AudioSystem.getInputDeviceName(this.mType);
        } else {
            outputDeviceName = AudioSystem.getOutputDeviceName(this.mType);
        }
        return "{" + super.toString() + ", mType: " + outputDeviceName + ", mAddress: " + this.mAddress + "}";
    }
}
