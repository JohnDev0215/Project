package org.chromium.midi;

import android.media.midi.MidiDevice;
import android.media.midi.MidiInputPort;
import android.util.Log;
import java.io.IOException;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class MidiOutputPortAndroid {
    public MidiInputPort a;
    public final MidiDevice b;
    public final int c;

    public MidiOutputPortAndroid(MidiDevice midiDevice, int i) {
        this.b = midiDevice;
        this.c = i;
    }

    @CalledByNative
    public void close() {
        MidiInputPort midiInputPort = this.a;
        if (midiInputPort == null) {
            return;
        }
        try {
            midiInputPort.close();
        } catch (IOException unused) {
        }
        this.a = null;
    }

    @CalledByNative
    public boolean open() {
        if (this.a != null) {
            return true;
        }
        MidiInputPort midiInputPortOpenInputPort = this.b.openInputPort(this.c);
        this.a = midiInputPortOpenInputPort;
        return midiInputPortOpenInputPort != null;
    }

    @CalledByNative
    public void send(byte[] bArr) {
        MidiInputPort midiInputPort = this.a;
        if (midiInputPort == null) {
            return;
        }
        try {
            midiInputPort.send(bArr, 0, bArr.length);
        } catch (IOException e) {
            Log.e("cr_midi", "MidiOutputPortAndroid.send: " + e);
        }
    }
}
