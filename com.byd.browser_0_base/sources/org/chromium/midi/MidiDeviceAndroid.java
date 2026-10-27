package org.chromium.midi;

import android.media.midi.MidiDevice;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class MidiDeviceAndroid {
    public final MidiDevice a;
    public final MidiInputPortAndroid[] b;
    public final MidiOutputPortAndroid[] c;
    public boolean d = true;

    public MidiDeviceAndroid(MidiDevice midiDevice) {
        this.a = midiDevice;
        this.c = new MidiOutputPortAndroid[midiDevice.getInfo().getInputPortCount()];
        int i = 0;
        int i2 = 0;
        while (true) {
            MidiOutputPortAndroid[] midiOutputPortAndroidArr = this.c;
            if (i2 >= midiOutputPortAndroidArr.length) {
                break;
            }
            midiOutputPortAndroidArr[i2] = new MidiOutputPortAndroid(midiDevice, i2);
            i2++;
        }
        this.b = new MidiInputPortAndroid[this.a.getInfo().getOutputPortCount()];
        while (true) {
            MidiInputPortAndroid[] midiInputPortAndroidArr = this.b;
            if (i >= midiInputPortAndroidArr.length) {
                return;
            }
            midiInputPortAndroidArr[i] = new MidiInputPortAndroid(midiDevice, i);
            i++;
        }
    }

    public final String a(String str) {
        return this.a.getInfo().getProperties().getString(str);
    }

    @CalledByNative
    public MidiInputPortAndroid[] getInputPorts() {
        return this.b;
    }

    @CalledByNative
    public String getManufacturer() {
        return a("manufacturer");
    }

    @CalledByNative
    public MidiOutputPortAndroid[] getOutputPorts() {
        return this.c;
    }

    @CalledByNative
    public String getProduct() {
        String strA = a("product");
        return (strA == null || strA.isEmpty()) ? a("name") : strA;
    }

    @CalledByNative
    public String getVersion() {
        return a("version");
    }
}
