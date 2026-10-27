package org.chromium.midi;

import android.media.midi.MidiDevice;
import android.media.midi.MidiOutputPort;
import defpackage.C1870zI;
import java.io.IOException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MidiInputPortAndroid {
    public MidiOutputPort a;
    public long b;
    public final MidiDevice c;
    public final int d;

    public MidiInputPortAndroid(MidiDevice midiDevice, int i) {
        this.c = midiDevice;
        this.d = i;
    }

    public synchronized void close() {
        if (this.a == null) {
            return;
        }
        try {
            this.a.close();
        } catch (IOException unused) {
        }
        this.b = 0L;
        this.a = null;
    }

    public boolean open(long j) {
        if (this.a != null) {
            return true;
        }
        MidiOutputPort midiOutputPortOpenOutputPort = this.c.openOutputPort(this.d);
        this.a = midiOutputPortOpenOutputPort;
        if (midiOutputPortOpenOutputPort == null) {
            return false;
        }
        this.b = j;
        midiOutputPortOpenOutputPort.connect(new C1870zI(this));
        return true;
    }
}
