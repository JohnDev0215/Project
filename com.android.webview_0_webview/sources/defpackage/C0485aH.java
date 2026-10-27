package defpackage;

import J.N;
import java.nio.ByteBuffer;
import org.chromium.media.AudioTrackOutputStream;

/* JADX INFO: renamed from: aH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0485aH extends Thread {
    public volatile boolean s;
    public final /* synthetic */ AudioTrackOutputStream t;

    public C0485aH(AudioTrackOutputStream audioTrackOutputStream) {
        this.t = audioTrackOutputStream;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (!this.s) {
            AudioTrackOutputStream audioTrackOutputStream = this.t;
            int i = audioTrackOutputStream.k;
            int i2 = 0;
            if (i != 0) {
                int iWrite = audioTrackOutputStream.c.write(audioTrackOutputStream.j, i, 0);
                if (iWrite < 0) {
                    Ts.a("AudioTrackOutput", Z7.a("AudioTrack.write() failed. Error:", iWrite), new Object[0]);
                    AudioTrackOutputStream audioTrackOutputStream2 = ((YG) audioTrackOutputStream.b).a;
                    N.Mr6$Ko2f(audioTrackOutputStream2.a, audioTrackOutputStream2);
                    i2 = iWrite;
                } else {
                    i2 = audioTrackOutputStream.k - iWrite;
                    audioTrackOutputStream.k = i2;
                }
            }
            if (i2 < 0) {
                return;
            }
            if (i2 <= 0) {
                AudioTrackOutputStream audioTrackOutputStream3 = this.t;
                int playbackHeadPosition = audioTrackOutputStream3.c.getPlaybackHeadPosition();
                long j = audioTrackOutputStream3.g + ((long) (playbackHeadPosition - audioTrackOutputStream3.f));
                audioTrackOutputStream3.g = j;
                audioTrackOutputStream3.f = playbackHeadPosition;
                long j2 = audioTrackOutputStream3.h - j;
                long j3 = j2 < 0 ? 0L : j2;
                ZG zg = audioTrackOutputStream3.b;
                ByteBuffer byteBufferDuplicate = audioTrackOutputStream3.i.duplicate();
                AudioTrackOutputStream audioTrackOutputStream4 = ((YG) zg).a;
                AudioTrackOutputStream.AudioBufferInfo audioBufferInfo = (AudioTrackOutputStream.AudioBufferInfo) N.MEPH2V3G(audioTrackOutputStream4.a, audioTrackOutputStream4, byteBufferDuplicate, j3);
                if (audioBufferInfo != null && audioBufferInfo.b > 0) {
                    audioTrackOutputStream3.h += (long) audioBufferInfo.a;
                    audioTrackOutputStream3.j = audioTrackOutputStream3.i.asReadOnlyBuffer();
                    audioTrackOutputStream3.k = audioBufferInfo.b;
                }
            }
        }
    }
}
