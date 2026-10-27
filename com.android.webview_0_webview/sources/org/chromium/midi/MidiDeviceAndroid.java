package org.chromium.midi;

import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MidiDeviceAndroid {
    public final MidiDevice a;
    public final MidiInputPortAndroid[] b;
    public boolean d = true;
    public final MidiOutputPortAndroid[] c = new MidiOutputPortAndroid[a().getInputPortCount()];

    public MidiDeviceAndroid(MidiDevice midiDevice) {
        this.a = midiDevice;
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
        this.b = new MidiInputPortAndroid[a().getOutputPortCount()];
        while (true) {
            MidiInputPortAndroid[] midiInputPortAndroidArr = this.b;
            if (i >= midiInputPortAndroidArr.length) {
                return;
            }
            midiInputPortAndroidArr[i] = new MidiInputPortAndroid(midiDevice, i);
            i++;
        }
    }

    public MidiDeviceInfo a() {
        return this.a.getInfo();
    }

    public final String b(String str) {
        return this.a.getInfo().getProperties().getString(str);
    }

    public MidiInputPortAndroid[] getInputPorts() {
        return this.b;
    }

    public String getManufacturer() {
        return b("manufacturer");
    }

    public MidiOutputPortAndroid[] getOutputPorts() {
        return this.c;
    }

    public String getProduct() {
        String strB = b("product");
        return (strB == null || strB.isEmpty()) ? b("name") : strB;
    }

    public String getVersion() {
        return b("version");
    }
}
