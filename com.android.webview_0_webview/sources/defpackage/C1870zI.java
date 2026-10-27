package defpackage;

import J.N;
import android.media.midi.MidiReceiver;
import org.chromium.midi.MidiInputPortAndroid;

/* JADX INFO: renamed from: zI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1870zI extends MidiReceiver {
    public final /* synthetic */ MidiInputPortAndroid a;

    public C1870zI(MidiInputPortAndroid midiInputPortAndroid) {
        this.a = midiInputPortAndroid;
    }

    @Override // android.media.midi.MidiReceiver
    public void onSend(byte[] bArr, int i, int i2, long j) {
        synchronized (this.a) {
            if (this.a.a == null) {
                return;
            }
            N.MzDIdqgH(this.a.b, bArr, i, i2, j);
        }
    }
}
