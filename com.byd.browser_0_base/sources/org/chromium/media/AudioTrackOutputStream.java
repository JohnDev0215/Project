package org.chromium.media;

import J.N;
import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class AudioTrackOutputStream {
    public long a;
    public final a b;
    public AudioTrack c;
    public int d;
    public b e;
    public int f;
    public long g;
    public long h;
    public ByteBuffer i;
    public ByteBuffer j;
    public int k;

    public static class AudioBufferInfo {
        public final int a;
        public final int b;

        public AudioBufferInfo(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public interface a {
    }

    public class b extends Thread {
        public volatile boolean e;

        public b() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            int i;
            while (!this.e) {
                AudioTrackOutputStream audioTrackOutputStream = AudioTrackOutputStream.this;
                int i2 = audioTrackOutputStream.k;
                int iWrite = 0;
                if (i2 != 0) {
                    iWrite = audioTrackOutputStream.c.write(audioTrackOutputStream.j, i2, 0);
                    if (iWrite < 0) {
                        Log.e("cr_AudioTrackOutput", "AudioTrack.write() failed. Error:" + iWrite);
                        AudioTrackOutputStream audioTrackOutputStream2 = ((h) audioTrackOutputStream.b).a;
                        N.Mr6$Ko2f(audioTrackOutputStream2.a, audioTrackOutputStream2);
                    } else {
                        iWrite = audioTrackOutputStream.k - iWrite;
                        audioTrackOutputStream.k = iWrite;
                    }
                }
                if (iWrite < 0) {
                    return;
                }
                if (iWrite <= 0) {
                    AudioTrackOutputStream audioTrackOutputStream3 = AudioTrackOutputStream.this;
                    int playbackHeadPosition = audioTrackOutputStream3.c.getPlaybackHeadPosition();
                    long j = audioTrackOutputStream3.g + ((long) (playbackHeadPosition - audioTrackOutputStream3.f));
                    audioTrackOutputStream3.g = j;
                    audioTrackOutputStream3.f = playbackHeadPosition;
                    long j2 = audioTrackOutputStream3.h - j;
                    long j3 = j2 < 0 ? 0L : j2;
                    ByteBuffer byteBufferDuplicate = audioTrackOutputStream3.i.duplicate();
                    AudioTrackOutputStream audioTrackOutputStream4 = ((h) audioTrackOutputStream3.b).a;
                    AudioBufferInfo audioBufferInfo = (AudioBufferInfo) N.MEPH2V3G(audioTrackOutputStream4.a, audioTrackOutputStream4, byteBufferDuplicate, j3);
                    if (audioBufferInfo != null && (i = audioBufferInfo.b) > 0) {
                        audioTrackOutputStream3.h += (long) audioBufferInfo.a;
                        audioTrackOutputStream3.j = audioTrackOutputStream3.i.asReadOnlyBuffer();
                        audioTrackOutputStream3.k = i;
                    }
                }
            }
        }
    }

    public AudioTrackOutputStream() {
        this.b = null;
        this.b = new h(this);
    }

    @CalledByNative
    public static AudioTrackOutputStream create() {
        return new AudioTrackOutputStream();
    }

    @CalledByNative
    public void close() {
        AudioTrack audioTrack = this.c;
        if (audioTrack != null) {
            audioTrack.release();
            this.c = null;
        }
    }

    @CalledByNative
    public AudioBufferInfo createAudioBufferInfo(int i, int i2) {
        return new AudioBufferInfo(i, i2);
    }

    @CalledByNative
    public boolean open(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6 = 4;
        if (i == 1) {
            i4 = i6;
        } else if (i != 2) {
            if (i == 4) {
                i5 = 204;
            } else if (i == 6) {
                i5 = 252;
            } else if (i != 8) {
                i4 = 1;
            } else {
                i5 = 6396;
            }
            i4 = i5;
        } else {
            i6 = 12;
            i4 = i6;
        }
        a aVar = this.b;
        ((h) aVar).getClass();
        this.d = AudioTrack.getMinBufferSize(i2, i4, i3) * 3;
        try {
            int i7 = this.d;
            ((h) aVar).getClass();
            AudioTrack audioTrack = new AudioTrack(3, i2, i4, i3, i7, 1);
            this.c = audioTrack;
            if (audioTrack.getState() == 0) {
                Log.e("cr_AudioTrackOutput", "Cannot create AudioTrack");
                this.c = null;
                return false;
            }
            this.f = 0;
            this.g = 0L;
            return true;
        } catch (IllegalArgumentException e) {
            Log.e("cr_AudioTrackOutput", "Exception creating AudioTrack for playback: ", e);
            return false;
        }
    }

    @CalledByNative
    public void setVolume(double d) {
        float maxVolume = (float) (d * ((double) AudioTrack.getMaxVolume()));
        this.c.setStereoVolume(maxVolume, maxVolume);
    }

    @CalledByNative
    public void start(long j) {
        if (this.e != null) {
            return;
        }
        this.a = j;
        this.h = 0L;
        int i = this.d;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i + 15);
        AudioTrackOutputStream audioTrackOutputStream = ((h) this.b).a;
        int iMMQ1O_vA = (16 - ((int) (N.MMQ1O_vA(audioTrackOutputStream.a, audioTrackOutputStream, byteBufferAllocateDirect) & ((long) 15)))) & 15;
        this.i = byteBufferAllocateDirect.slice();
        this.c.play();
        b bVar = new b();
        this.e = bVar;
        bVar.start();
    }

    @CalledByNative
    public void stop() {
        b bVar = this.e;
        if (bVar != null) {
            bVar.e = true;
            try {
                this.e.interrupt();
                this.e.join();
            } catch (InterruptedException e) {
                Log.e("cr_AudioTrackOutput", "Exception while waiting for AudioTrack worker thread finished: ", e);
            } catch (SecurityException e2) {
                Log.e("cr_AudioTrackOutput", "Exception while waiting for AudioTrack worker thread finished: ", e2);
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
