package defpackage;

import J.N;
import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import org.chromium.midi.MidiDeviceAndroid;
import org.chromium.midi.MidiManagerAndroid;

/* JADX INFO: compiled from: MidiManagerAndroid.java */
/* JADX INFO: loaded from: classes3.dex */
public final class rh1 implements MidiManager.OnDeviceOpenedListener {
    public final /* synthetic */ MidiDeviceInfo a;
    public final /* synthetic */ MidiManagerAndroid b;

    public rh1(MidiManagerAndroid midiManagerAndroid, MidiDeviceInfo midiDeviceInfo) {
        this.b = midiManagerAndroid;
        this.a = midiDeviceInfo;
    }

    @Override // android.media.midi.MidiManager.OnDeviceOpenedListener
    public final void onDeviceOpened(MidiDevice midiDevice) {
        MidiManagerAndroid midiManagerAndroid = this.b;
        MidiDeviceInfo midiDeviceInfo = this.a;
        synchronized (midiManagerAndroid) {
            if (midiManagerAndroid.g) {
                return;
            }
            midiManagerAndroid.c.remove(midiDeviceInfo);
            if (midiDevice != null) {
                MidiDeviceAndroid midiDeviceAndroid = new MidiDeviceAndroid(midiDevice);
                midiManagerAndroid.b.add(midiDeviceAndroid);
                if (midiManagerAndroid.a) {
                    N.MEOWUhL5(midiManagerAndroid.f, midiDeviceAndroid);
                }
            }
            if (!midiManagerAndroid.a && midiManagerAndroid.c.isEmpty()) {
                N.M3znzcE9(midiManagerAndroid.f, (MidiDeviceAndroid[]) midiManagerAndroid.b.toArray(new MidiDeviceAndroid[0]));
                midiManagerAndroid.a = true;
            }
        }
    }
}
