package org.chromium.media;

import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;
import defpackage.l9;
import defpackage.xz;
import org.chromium.media.e;

/* JADX INFO: compiled from: AudioDeviceSelectorPreS.java */
/* JADX INFO: loaded from: classes3.dex */
public final class g extends e {
    public int d;
    public boolean e;
    public final boolean[] f;
    public l9 g;

    public g(AudioManager audioManager) {
        super(audioManager);
        this.d = -1;
        this.f = new boolean[5];
    }

    @Override // org.chromium.media.e
    public final void a() {
        this.b.a();
        if (this.e) {
            xz.a.unregisterReceiver(this.g);
            this.g = null;
        }
    }

    @Override // org.chromium.media.e
    public final boolean[] b() {
        boolean[] zArr = (boolean[]) this.f.clone();
        if (zArr[1]) {
            zArr[4] = false;
            zArr[2] = false;
        } else if (zArr[4]) {
            zArr[2] = false;
        }
        return zArr;
    }

    @Override // org.chromium.media.e
    public final void c() {
        boolean z = xz.a.checkSelfPermission("android.permission.BLUETOOTH") == 0;
        this.e = z;
        this.b.d(z);
        if (this.e) {
            IntentFilter intentFilter = new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
            l9 l9Var = new l9(this);
            this.g = l9Var;
            xz.g(xz.a, l9Var, intentFilter);
        }
    }

    @Override // org.chromium.media.e
    public final boolean d() {
        return this.c.isSpeakerphoneOn();
    }

    @Override // org.chromium.media.e
    public final void f(int i) {
        int i2;
        if (i != 3) {
            j();
        } else if (this.e && (i2 = this.d) != 1 && i2 != 2) {
            AudioManager audioManager = this.c;
            if (audioManager.isBluetoothScoOn()) {
                this.d = 1;
            } else {
                this.d = 2;
                audioManager.startBluetoothSco();
            }
        }
        if (i == 0) {
            i(true);
            return;
        }
        if (i == 1) {
            i(false);
            return;
        }
        if (i == 2) {
            i(false);
        } else if (i != 3) {
            if (i != 4) {
                Log.e("cr_media", "Invalid audio device selection");
            } else {
                i(false);
            }
        }
    }

    @Override // org.chromium.media.e
    public final void g(boolean z) {
        if (z) {
            return;
        }
        j();
        e.b bVar = this.a;
        synchronized (bVar.a) {
            bVar.b = -1;
        }
    }

    @Override // org.chromium.media.e
    public final void h(int i, boolean z) {
        this.f[i] = z;
    }

    @Override // org.chromium.media.e
    public final void i(boolean z) {
        AudioManager audioManager = this.c;
        if (audioManager.isSpeakerphoneOn() == z) {
            return;
        }
        audioManager.setSpeakerphoneOn(z);
    }

    public final void j() {
        if (this.e) {
            int i = this.d;
            if (i == 1 || i == 2) {
                AudioManager audioManager = this.c;
                if (audioManager.isBluetoothScoOn()) {
                    this.d = 3;
                    audioManager.stopBluetoothSco();
                } else {
                    Log.e("cr_media", "Unable to stop BT SCO since it is already disabled");
                    this.d = 0;
                }
            }
        }
    }
}
