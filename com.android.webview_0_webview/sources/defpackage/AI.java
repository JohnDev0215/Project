package defpackage;

import J.N;
import org.chromium.midi.MidiManagerAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AI implements Runnable {
    public final /* synthetic */ MidiManagerAndroid s;

    public AI(MidiManagerAndroid midiManagerAndroid) {
        this.s = midiManagerAndroid;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.s) {
            if (this.s.g) {
                return;
            }
            N.MfmZ2$zu(this.s.f);
        }
    }
}
