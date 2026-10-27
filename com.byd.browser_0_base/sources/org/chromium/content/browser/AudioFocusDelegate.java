package org.chromium.content.browser;

import J.N;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Handler;
import defpackage.fa1;
import defpackage.xz;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class AudioFocusDelegate implements AudioManager.OnAudioFocusChangeListener {
    public int a;
    public boolean b;
    public AudioFocusRequest c;
    public long d;
    public final Handler e = new Handler(ThreadUtils.d());

    public AudioFocusDelegate(long j) {
        this.d = j;
    }

    @CalledByNative
    public static AudioFocusDelegate create(long j) {
        return new AudioFocusDelegate(j);
    }

    @CalledByNative
    public final void abandonAudioFocus() {
        AudioManager audioManager = (AudioManager) xz.a.getSystemService("audio");
        AudioFocusRequest audioFocusRequest = this.c;
        if (audioFocusRequest != null) {
            audioManager.abandonAudioFocusRequest(audioFocusRequest);
            this.c = null;
        }
    }

    @CalledByNative
    public final boolean isFocusTransient() {
        return this.a == 3;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public final void onAudioFocusChange(int i) {
        long j = this.d;
        if (j == 0) {
            return;
        }
        if (i == -3) {
            this.b = true;
            N.Mn3dG6eh(j, this);
            N.My_SIOp6(this.d, this);
            return;
        }
        if (i == -2) {
            N.MUFA7yj7(j, this);
            return;
        }
        if (i == -1) {
            abandonAudioFocus();
            N.MUFA7yj7(this.d, this);
        } else if (i != 1) {
            fa1.k("MediaSession", "onAudioFocusChange called with unexpected value %d", Integer.valueOf(i));
        } else if (!this.b) {
            N.MqbF3KiE(j, this);
        } else {
            N.MeoE5HbI(j, this);
            this.b = false;
        }
    }

    @CalledByNative
    public final boolean requestAudioFocus(boolean z) {
        this.a = z ? 3 : 1;
        AudioManager audioManager = (AudioManager) xz.a.getSystemService("audio");
        AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(this.a).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(0).build()).setAcceptsDelayedFocusGain(false).setWillPauseWhenDucked(false).setOnAudioFocusChangeListener(this, this.e).build();
        this.c = audioFocusRequestBuild;
        return audioManager.requestAudioFocus(audioFocusRequestBuild) == 1;
    }

    @CalledByNative
    public final void tearDown() {
        abandonAudioFocus();
        this.d = 0L;
    }
}
