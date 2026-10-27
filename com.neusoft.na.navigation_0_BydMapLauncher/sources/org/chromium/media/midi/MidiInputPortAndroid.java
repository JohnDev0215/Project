package org.chromium.media.midi;

import android.media.midi.MidiDevice;
import android.media.midi.MidiOutputPort;
import android.media.midi.MidiReceiver;
import java.io.IOException;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media::midi")
class MidiInputPortAndroid {
    private final MidiDevice mDevice;
    private final int mIndex;
    private long mNativeReceiverPointer;
    private MidiOutputPort mPort;

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeOnData(long j, byte[] bArr, int i, int i2, long j2);

    MidiInputPortAndroid(MidiDevice midiDevice, int i) {
        this.mDevice = midiDevice;
        this.mIndex = i;
    }

    boolean open(long j) {
        if (this.mPort != null) {
            return true;
        }
        MidiOutputPort midiOutputPortOpenOutputPort = this.mDevice.openOutputPort(this.mIndex);
        this.mPort = midiOutputPortOpenOutputPort;
        if (midiOutputPortOpenOutputPort == null) {
            return false;
        }
        this.mNativeReceiverPointer = j;
        midiOutputPortOpenOutputPort.connect(new MidiReceiver() { // from class: org.chromium.media.midi.MidiInputPortAndroid.1
            @Override // android.media.midi.MidiReceiver
            public void onSend(byte[] bArr, int i, int i2, long j2) {
                MidiInputPortAndroid.nativeOnData(MidiInputPortAndroid.this.mNativeReceiverPointer, bArr, i, i2, j2);
            }
        });
        return true;
    }

    void close() {
        MidiOutputPort midiOutputPort = this.mPort;
        if (midiOutputPort == null) {
            return;
        }
        try {
            midiOutputPort.close();
        } catch (IOException unused) {
        }
        this.mNativeReceiverPointer = 0L;
        this.mPort = null;
    }
}
