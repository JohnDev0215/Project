package org.libpag;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.SystemClock;
import org.libpag.u;

/* JADX INFO: loaded from: classes3.dex */
public class a implements u.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private MediaCodec f4049a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f4050b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ MediaFormat f4051c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ VideoSurface f4052d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ MediaCodec[] f4053e;

    public a(MediaFormat mediaFormat, VideoSurface videoSurface, MediaCodec[] mediaCodecArr) {
        this.f4051c = mediaFormat;
        this.f4052d = videoSurface;
        this.f4053e = mediaCodecArr;
    }

    @Override // org.libpag.u.b
    public void a(boolean z) {
        if (z && this.f4049a != null) {
            long jUptimeMillis = SystemClock.uptimeMillis() - this.f4050b;
            try {
                this.f4049a.stop();
            } catch (Exception unused) {
            }
            try {
                this.f4049a.release();
            } catch (Exception unused2) {
            }
            this.f4049a = null;
            this.f4052d.a();
            new RuntimeException("init decoder timeout. cost: " + jUptimeMillis + "ms").printStackTrace();
        }
        if (!z) {
            this.f4053e[0] = this.f4049a;
        }
        HardwareDecoder.f4009a.getAndDecrement();
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f4050b = SystemClock.uptimeMillis();
        try {
            MediaCodec mediaCodecCreateDecoderByType = MediaCodec.createDecoderByType(this.f4051c.getString("mime"));
            this.f4049a = mediaCodecCreateDecoderByType;
            mediaCodecCreateDecoderByType.configure(this.f4051c, this.f4052d.getInputSurface(), (MediaCrypto) null, 0);
            this.f4049a.start();
        } catch (Exception unused) {
            MediaCodec mediaCodec = this.f4049a;
            if (mediaCodec != null) {
                mediaCodec.release();
                this.f4049a = null;
                this.f4052d.a();
            }
        }
    }
}
