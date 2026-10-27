package org.chromium.midi;

import android.media.midi.MidiDevice;
import android.media.midi.MidiInputPort;
import defpackage.Ts;
import java.io.IOException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MidiOutputPortAndroid {
    public MidiInputPort a;
    public final MidiDevice b;
    public final int c;

    public MidiOutputPortAndroid(MidiDevice midiDevice, int i) {
        this.b = midiDevice;
        this.c = i;
    }

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

    public boolean open() {
        if (this.a != null) {
            return true;
        }
        MidiInputPort midiInputPortOpenInputPort = this.b.openInputPort(this.c);
        this.a = midiInputPortOpenInputPort;
        return midiInputPortOpenInputPort != null;
    }

    public void send(byte[] bArr) {
        MidiInputPort midiInputPort = this.a;
        if (midiInputPort == null) {
            return;
        }
        try {
            midiInputPort.send(bArr, 0, bArr.length);
        } catch (IOException e) {
            Ts.a("midi", "MidiOutputPortAndroid.send: " + e, new Object[0]);
        }
    }
}
