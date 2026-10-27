package org.chromium.midi;

import J.N;
import android.media.midi.MidiDevice;
import android.media.midi.MidiOutputPort;
import android.media.midi.MidiReceiver;
import java.io.IOException;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class MidiInputPortAndroid {
    public MidiOutputPort a;
    public long b;
    public final MidiDevice c;
    public final int d;

    public class a extends MidiReceiver {
        public a() {
        }

        @Override // android.media.midi.MidiReceiver
        public final void onSend(byte[] bArr, int i, int i2, long j) {
            synchronized (MidiInputPortAndroid.this) {
                MidiInputPortAndroid midiInputPortAndroid = MidiInputPortAndroid.this;
                if (midiInputPortAndroid.a == null) {
                    return;
                }
                N.MzDIdqgH(midiInputPortAndroid.b, bArr, i, i2, j);
            }
        }
    }

    public MidiInputPortAndroid(MidiDevice midiDevice, int i) {
        this.c = midiDevice;
        this.d = i;
    }

    @CalledByNative
    public synchronized void close() {
        MidiOutputPort midiOutputPort = this.a;
        if (midiOutputPort == null) {
            return;
        }
        try {
            midiOutputPort.close();
        } catch (IOException unused) {
        }
        this.b = 0L;
        this.a = null;
    }

    @CalledByNative
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
        midiOutputPortOpenOutputPort.connect(new a());
        return true;
    }
}
