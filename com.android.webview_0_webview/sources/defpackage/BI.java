package defpackage;

import J.N;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import org.chromium.midi.MidiDeviceAndroid;
import org.chromium.midi.MidiInputPortAndroid;
import org.chromium.midi.MidiManagerAndroid;
import org.chromium.midi.MidiOutputPortAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BI extends MidiManager.DeviceCallback {
    public final /* synthetic */ MidiManagerAndroid a;

    public BI(MidiManagerAndroid midiManagerAndroid) {
        this.a = midiManagerAndroid;
    }

    @Override // android.media.midi.MidiManager.DeviceCallback
    public void onDeviceAdded(MidiDeviceInfo midiDeviceInfo) {
        MidiManagerAndroid midiManagerAndroid = this.a;
        if (!midiManagerAndroid.a) {
            midiManagerAndroid.c.add(midiDeviceInfo);
        }
        midiManagerAndroid.d.openDevice(midiDeviceInfo, new DI(midiManagerAndroid, midiDeviceInfo), midiManagerAndroid.e);
    }

    @Override // android.media.midi.MidiManager.DeviceCallback
    public void onDeviceRemoved(MidiDeviceInfo midiDeviceInfo) {
        MidiManagerAndroid midiManagerAndroid = this.a;
        synchronized (midiManagerAndroid) {
            if (midiManagerAndroid.g) {
                return;
            }
            for (MidiDeviceAndroid midiDeviceAndroid : midiManagerAndroid.b) {
                if (midiDeviceAndroid.d && midiDeviceAndroid.a().getId() == midiDeviceInfo.getId()) {
                    midiDeviceAndroid.d = false;
                    for (MidiInputPortAndroid midiInputPortAndroid : midiDeviceAndroid.b) {
                        midiInputPortAndroid.close();
                    }
                    for (MidiOutputPortAndroid midiOutputPortAndroid : midiDeviceAndroid.c) {
                        midiOutputPortAndroid.close();
                    }
                    N.Md3XPFG5(midiManagerAndroid.f, midiDeviceAndroid);
                }
            }
        }
    }
}
