package org.chromium.content.browser;

import J.N;
import android.media.AudioManager;
import defpackage.Is;
import defpackage.Ts;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AudioFocusDelegate implements AudioManager.OnAudioFocusChangeListener {
    public int a;
    public boolean b;
    public long c;

    public AudioFocusDelegate(long j) {
        this.c = j;
    }

    private void abandonAudioFocus() {
        ((AudioManager) Is.a.getSystemService("audio")).abandonAudioFocus(this);
    }

    public static AudioFocusDelegate create(long j) {
        return new AudioFocusDelegate(j);
    }

    private boolean isFocusTransient() {
        return this.a == 3;
    }

    private boolean requestAudioFocus(boolean z) {
        this.a = z ? 3 : 1;
        return ((AudioManager) Is.a.getSystemService("audio")).requestAudioFocus(this, 3, this.a) == 1;
    }

    private void tearDown() {
        abandonAudioFocus();
        this.c = 0L;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        long j = this.c;
        if (j == 0) {
            return;
        }
        if (i == -3) {
            this.b = true;
            N.Mn3dG6eh(j, this);
            N.My_SIOp6(this.c, this);
            return;
        }
        if (i == -2) {
            N.MUFA7yj7(j, this);
            return;
        }
        if (i == -1) {
            abandonAudioFocus();
            N.MUFA7yj7(this.c, this);
        } else if (i != 1) {
            Ts.f("MediaSession", "onAudioFocusChange called with unexpected value %d", Integer.valueOf(i));
        } else if (!this.b) {
            N.MqbF3KiE(j, this);
        } else {
            N.MeoE5HbI(j, this);
            this.b = false;
        }
    }
}
