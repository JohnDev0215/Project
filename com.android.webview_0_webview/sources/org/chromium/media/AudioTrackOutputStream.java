package org.chromium.media;

import J.N;
import android.media.AudioTrack;
import android.os.Build;
import defpackage.C0485aH;
import defpackage.Ts;
import defpackage.YG;
import defpackage.ZG;
import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AudioTrackOutputStream {
    public long a;
    public ZG b;
    public AudioTrack c;
    public int d;
    public C0485aH e;
    public int f;
    public long g;
    public long h;
    public ByteBuffer i;
    public ByteBuffer j;
    public int k;

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class AudioBufferInfo {
        public final int a;
        public final int b;

        public AudioBufferInfo(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public AudioTrackOutputStream(ZG zg) {
        this.b = null;
        this.b = new YG(this);
    }

    public static AudioTrackOutputStream create() {
        return new AudioTrackOutputStream(null);
    }

    public void close() {
        AudioTrack audioTrack = this.c;
        if (audioTrack != null) {
            audioTrack.release();
            this.c = null;
        }
    }

    public AudioBufferInfo createAudioBufferInfo(int i, int i2) {
        return new AudioBufferInfo(i, i2);
    }

    public boolean open(int i, int i2, int i3) {
        int i4 = i != 1 ? i != 2 ? i != 4 ? i != 6 ? i != 8 ? 1 : Build.VERSION.SDK_INT >= 23 ? 6396 : 1020 : 252 : 204 : 12 : 4;
        Objects.requireNonNull((YG) this.b);
        this.d = AudioTrack.getMinBufferSize(i2, i4, i3) * 3;
        try {
            ZG zg = this.b;
            int i5 = this.d;
            Objects.requireNonNull((YG) zg);
            AudioTrack audioTrack = new AudioTrack(3, i2, i4, i3, i5, 1);
            this.c = audioTrack;
            if (audioTrack.getState() == 0) {
                Ts.a("AudioTrackOutput", "Cannot create AudioTrack", new Object[0]);
                this.c = null;
                return false;
            }
            this.f = 0;
            this.g = 0L;
            return true;
        } catch (IllegalArgumentException e) {
            Ts.a("AudioTrackOutput", "Exception creating AudioTrack for playback: ", e);
            return false;
        }
    }

    public void setVolume(double d) {
        float maxVolume = (float) (d * ((double) AudioTrack.getMaxVolume()));
        this.c.setStereoVolume(maxVolume, maxVolume);
    }

    public void start(long j) {
        if (this.e != null) {
            return;
        }
        this.a = j;
        this.h = 0L;
        int i = this.d;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i + 15);
        AudioTrackOutputStream audioTrackOutputStream = ((YG) this.b).a;
        int iMMQ1O_vA = 15 & (16 - ((int) (N.MMQ1O_vA(audioTrackOutputStream.a, audioTrackOutputStream, byteBufferAllocateDirect) & ((long) 15))));
        byteBufferAllocateDirect.position(iMMQ1O_vA);
        byteBufferAllocateDirect.limit(iMMQ1O_vA + i);
        this.i = byteBufferAllocateDirect.slice();
        this.c.play();
        C0485aH c0485aH = new C0485aH(this);
        this.e = c0485aH;
        c0485aH.start();
    }

    public void stop() {
        C0485aH c0485aH = this.e;
        if (c0485aH != null) {
            c0485aH.s = true;
            try {
                this.e.interrupt();
                this.e.join();
            } catch (InterruptedException e) {
                Ts.a("AudioTrackOutput", "Exception while waiting for AudioTrack worker thread finished: ", e);
            } catch (SecurityException e2) {
                Ts.a("AudioTrackOutput", "Exception while waiting for AudioTrack worker thread finished: ", e2);
            }
            this.e = null;
        }
        this.c.pause();
        this.c.flush();
        this.f = 0;
        this.g = 0L;
        this.a = 0L;
    }
}
