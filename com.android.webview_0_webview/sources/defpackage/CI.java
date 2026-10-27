package defpackage;

import J.N;
import org.chromium.midi.MidiDeviceAndroid;
import org.chromium.midi.MidiManagerAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CI implements Runnable {
    public final /* synthetic */ MidiManagerAndroid s;

    public CI(MidiManagerAndroid midiManagerAndroid) {
        this.s = midiManagerAndroid;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.s) {
            if (this.s.g) {
                return;
            }
            if (this.s.c.isEmpty() && !this.s.a) {
                N.M3znzcE9(this.s.f, (MidiDeviceAndroid[]) this.s.b.toArray(new MidiDeviceAndroid[0]));
                this.s.a = true;
            }
        }
    }
}
