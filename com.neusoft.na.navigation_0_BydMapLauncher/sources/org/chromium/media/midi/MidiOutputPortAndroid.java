package org.chromium.media.midi;

import android.media.midi.MidiDevice;
import android.media.midi.MidiInputPort;
import java.io.IOException;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media::midi")
class MidiOutputPortAndroid {
    private static final String TAG = "media_midi";
    private final MidiDevice mDevice;
    private final int mIndex;
    private MidiInputPort mPort;

    MidiOutputPortAndroid(MidiDevice midiDevice, int i) {
        this.mDevice = midiDevice;
        this.mIndex = i;
    }

    boolean open() {
        if (this.mPort != null) {
            return true;
        }
        MidiInputPort midiInputPortOpenInputPort = this.mDevice.openInputPort(this.mIndex);
        this.mPort = midiInputPortOpenInputPort;
        return midiInputPortOpenInputPort != null;
    }

    void send(byte[] bArr) {
        MidiInputPort midiInputPort = this.mPort;
        if (midiInputPort == null) {
            return;
        }
        try {
            midiInputPort.send(bArr, 0, bArr.length);
        } catch (IOException e) {
            Log.e(TAG, "MidiOutputPortAndroid.send: " + e, new Object[0]);
        }
    }

    void close() {
        MidiInputPort midiInputPort = this.mPort;
        if (midiInputPort == null) {
            return;
        }
        try {
            midiInputPort.close();
        } catch (IOException unused) {
        }
        this.mPort = null;
    }
}
