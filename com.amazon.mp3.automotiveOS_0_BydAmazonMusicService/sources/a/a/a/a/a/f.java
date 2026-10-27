package a.a.a.a.a;

import a.b.a.a.e.c;
import a.b.a.a.n.b;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTimestamp;
import android.media.AudioTrack;
import android.media.MediaFormat;
import androidx.compose.runtime.ComposerKt;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class f implements c.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f16a = 1;
    public static final int b = 6396;
    public String c;
    public final a.b.a.a.i.b d;
    public final int e;
    public final int f;
    public final AudioTrack g;
    public final long h;
    public boolean i;
    public int j;
    public final a.b.a.a.n.d k;
    public long l;
    public long m;
    public final MediaFormat n;
    public final int o;
    public final float p;
    public final boolean q;

    public f(MediaFormat mediaFormat, int i, float f, boolean z) {
        this.n = mediaFormat;
        this.o = i;
        this.p = f;
        this.q = z;
        StringBuilder sb = new StringBuilder();
        sb.append("AudioRenderer_A");
        int i2 = f16a;
        f16a = i2 + 1;
        sb.append(i2);
        String string = sb.toString();
        this.c = string;
        this.d = a.b.a.a.i.b.d.a(string, b.a.Audio);
        this.e = mediaFormat.getInteger("sample-rate");
        this.f = mediaFormat.getInteger("channel-count");
        this.h = TimeUnit.SECONDS.toNanos(2L);
        this.k = new a.b.a.a.n.d(10, 0, 2);
        this.g = c();
        a(f);
    }

    @Override // a.b.a.a.e.c.a
    public void a() {
        this.g.pause();
        this.g.flush();
        this.g.release();
    }

    @Override // a.b.a.a.e.c.a
    public void a(float f) {
        this.g.setVolume(f);
    }

    @Override // a.b.a.a.e.c.a
    public void a(byte[] bArr, long j) throws InterruptedException {
        a.b.a.a.i.b bVar;
        String str;
        StringBuilder sb;
        if (bArr == null) {
            this.g.pause();
            this.g.flush();
            this.g.release();
            return;
        }
        int length = (int) ((((double) bArr.length) / ((double) (((this.f * 2) * this.e) / 1000))) * ((double) 1000));
        int i = 0;
        if (this.o != 2) {
            length = 0;
        }
        long j2 = 1000;
        long jNanoTime = (System.nanoTime() / j2) + ((long) this.j) + ((long) length);
        if (!this.i) {
            this.i = j >= jNanoTime;
        }
        if (!this.i || j < jNanoTime) {
            return;
        }
        Thread.sleep((j - jNanoTime) / j2);
        int iWrite = this.g.write(bArr, 0, bArr.length, 1);
        if (iWrite >= 0 && iWrite < bArr.length) {
            int iWrite2 = this.g.write(bArr, iWrite, bArr.length - iWrite);
            this.d.d(this.c, "Performing blocking write. Sent " + iWrite + " of total " + bArr.length + " bytes, ret " + iWrite2);
            if (iWrite2 >= 0) {
                iWrite += iWrite2;
                if (iWrite != bArr.length) {
                    bVar = this.d;
                    str = this.c;
                    sb = new StringBuilder();
                    sb.append("Failed to write all bytes in blocking call ");
                    sb.append(iWrite);
                    sb.append(" out of ");
                    sb.append(bArr.length);
                    sb.append(", ret ");
                    sb.append(iWrite2);
                }
            } else {
                bVar = this.d;
                str = this.c;
                sb = new StringBuilder();
                sb.append("Error when writing remaining bytes using blocking call: ");
                sb.append(iWrite2);
                sb.append(" (");
                sb.append(iWrite);
                sb.append(" of ");
                sb.append(bArr.length);
                sb.append(')');
            }
            bVar.b(str, sb.toString());
        }
        this.l += (long) (iWrite / (this.f * 2));
        if (!this.q || this.m + this.h >= System.nanoTime()) {
            return;
        }
        if (this.o == 2) {
            AudioTimestamp audioTimestamp = new AudioTimestamp();
            if (this.g.getTimestamp(audioTimestamp)) {
                int micros = (int) TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - audioTimestamp.nanoTime);
                long j3 = audioTimestamp.framePosition;
                if (j3 != 0) {
                    long j4 = this.l;
                    if (j4 <= Integer.MAX_VALUE) {
                        long j5 = j4 - j3;
                        int i2 = (int) (((1000000000 * j5) / ((long) this.e)) / j2);
                        this.k.a(i2);
                        this.d.a(this.c, "Latency: " + i2 + " frameIndexDelta: " + j5 + " frameTimeDelta: " + i2 + " timeDelta: " + micros);
                        i = (int) this.k.b;
                    }
                }
            }
        }
        if (i > 0) {
            this.d.a(this.c, "New audio latency: " + i);
            this.j = i;
        }
        this.m = System.nanoTime();
    }

    @Override // a.b.a.a.e.c.a
    public void b() {
        this.g.play();
    }

    public final AudioTrack c() {
        int i;
        Object objInvoke;
        int i2 = this.o;
        MediaFormat mediaFormat = this.n;
        this.d.c(this.c, "getAudioTrack from format: " + mediaFormat);
        int integer = mediaFormat.getInteger("sample-rate");
        if (integer < 4000) {
            integer = 4000;
        } else if (integer > 192000) {
            integer = 192000;
        }
        int integer2 = mediaFormat.getInteger("channel-count");
        switch (integer2) {
            case 1:
                i = 4;
                break;
            case 2:
                i = 12;
                break;
            case 3:
                i = 28;
                break;
            case 4:
                i = ComposerKt.providerMapsKey;
                break;
            case 5:
                i = 220;
                break;
            case 6:
                i = 252;
                break;
            case 7:
                i = 1276;
                break;
            case 8:
                i = b;
                break;
            default:
                throw new RuntimeException(integer2 + " audio channels in one track not yet implemented.");
        }
        int minBufferSize = AudioTrack.getMinBufferSize(integer, i, i2);
        if (minBufferSize < 0) {
            throw new RuntimeException("SampleRate: " + integer + " not supported!");
        }
        AudioTrack audioTrack = new AudioTrack(new AudioAttributes.Builder().setLegacyStreamType(3).build(), new AudioFormat.Builder().setChannelMask(i).setEncoding(i2).setSampleRate(integer).build(), minBufferSize * 2, 1, 0);
        audioTrack.attachAuxEffect(-1);
        if (audioTrack.getState() != 1) {
            throw new RuntimeException("AudioTrack has wrong state: " + audioTrack.getState());
        }
        try {
            Method method = AudioTrack.class.getMethod("getLatency", new Class[0]);
            Intrinsics.checkExpressionValueIsNotNull(method, "android.media.AudioTrack…a.getMethod(\"getLatency\")");
            objInvoke = method.invoke(audioTrack, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            this.d.b(this.c, "getLatency reflexion exception " + e.getMessage());
        }
        if (objInvoke == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
        }
        int iIntValue = ((Integer) objInvoke).intValue() * 1000;
        this.j = iIntValue;
        this.k.a(iIntValue);
        this.d.a(this.c, "getLatency: " + this.j);
        return audioTrack;
    }
}
