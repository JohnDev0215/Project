package org.chromium.media.midi;

import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media::midi")
class MidiDeviceAndroid {
    private final MidiDevice mDevice;
    private final MidiInputPortAndroid[] mInputPorts;
    private boolean mIsOpen = true;
    private final MidiOutputPortAndroid[] mOutputPorts = new MidiOutputPortAndroid[getInfo().getInputPortCount()];

    MidiDeviceAndroid(MidiDevice midiDevice) {
        this.mDevice = midiDevice;
        int i = 0;
        int i2 = 0;
        while (true) {
            MidiOutputPortAndroid[] midiOutputPortAndroidArr = this.mOutputPorts;
            if (i2 >= midiOutputPortAndroidArr.length) {
                break;
            }
            midiOutputPortAndroidArr[i2] = new MidiOutputPortAndroid(midiDevice, i2);
            i2++;
        }
        this.mInputPorts = new MidiInputPortAndroid[getInfo().getOutputPortCount()];
        while (true) {
            MidiInputPortAndroid[] midiInputPortAndroidArr = this.mInputPorts;
            if (i >= midiInputPortAndroidArr.length) {
                return;
            }
            midiInputPortAndroidArr[i] = new MidiInputPortAndroid(midiDevice, i);
            i++;
        }
    }

    boolean isOpen() {
        return this.mIsOpen;
    }

    void close() {
        this.mIsOpen = false;
        for (MidiInputPortAndroid midiInputPortAndroid : this.mInputPorts) {
            midiInputPortAndroid.close();
        }
        for (MidiOutputPortAndroid midiOutputPortAndroid : this.mOutputPorts) {
            midiOutputPortAndroid.close();
        }
    }

    MidiDevice getDevice() {
        return this.mDevice;
    }

    MidiDeviceInfo getInfo() {
        return this.mDevice.getInfo();
    }

    String getManufacturer() {
        return getProperty("manufacturer");
    }

    String getProduct() {
        return getProperty("product");
    }

    String getVersion() {
        return getProperty("version");
    }

    MidiInputPortAndroid[] getInputPorts() {
        return this.mInputPorts;
    }

    MidiOutputPortAndroid[] getOutputPorts() {
        return this.mOutputPorts;
    }

    private String getProperty(String str) {
        return this.mDevice.getInfo().getProperties().getString(str);
    }
}
