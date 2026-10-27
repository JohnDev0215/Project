package com.byd.filter2.h;

import android.content.Context;
import android.location.Location;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.view.Surface;
import com.byd.util.w;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2858a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2859b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private MediaMuxer f2860c;
    private MediaCodec d;
    private Surface e;
    private int f;
    private a g;
    private boolean h = false;
    private boolean i = false;
    private long j;
    private long k;
    private int l;

    public interface a {
        void a(int i);
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int iDequeueOutputBuffer;
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            k.this.l = 0;
            w.c("====video encode start====");
            int iAddTrack = -1;
            while (true) {
                try {
                    iDequeueOutputBuffer = k.this.d.dequeueOutputBuffer(bufferInfo, 1000L);
                } catch (Exception unused) {
                    iDequeueOutputBuffer = -1;
                }
                if (iDequeueOutputBuffer >= 0) {
                    ByteBuffer outputBuffer = k.this.d.getOutputBuffer(iDequeueOutputBuffer);
                    if (!k.this.i || iAddTrack == -1) {
                        k.this.d.releaseOutputBuffer(iDequeueOutputBuffer, false);
                    } else {
                        if ((bufferInfo.flags & 2) != 0) {
                            bufferInfo.size = 0;
                        }
                        if (bufferInfo.size != 0) {
                            outputBuffer.position(bufferInfo.offset);
                            outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                            bufferInfo.presentationTimeUs = k.this.k;
                            k.this.x(iAddTrack, outputBuffer, bufferInfo);
                            k.c(k.this);
                            k kVar = k.this;
                            k.i(kVar, kVar.j);
                        }
                        k.this.d.releaseOutputBuffer(iDequeueOutputBuffer, false);
                        if ((bufferInfo.flags & 4) != 0) {
                            break;
                        }
                    }
                } else if (iDequeueOutputBuffer != -1) {
                    if (iDequeueOutputBuffer != -2) {
                        w.c("Video -- else : " + iDequeueOutputBuffer);
                    } else if (iAddTrack == -1 && !k.this.h) {
                        k.this.h = true;
                        iAddTrack = k.this.f2860c.addTrack(k.this.d.getOutputFormat());
                        w.c("add video track : " + iAddTrack);
                        k.this.w();
                    }
                }
            }
            w.c("Video -- end of stream");
            w.c("Video -- after while , video encode count : " + k.this.l);
            if (k.this.e != null) {
                k.this.e.release();
                k.this.e = null;
            }
            if (k.this.d != null) {
                k.this.d.stop();
                k.this.d.release();
                k.this.d = null;
            }
            k.this.v();
        }
    }

    public k(Context context, int i, int i2, String str, Location location, int i3) {
        this.j = 33000L;
        this.f2858a = i;
        this.f2859b = i2;
        this.f = i3;
        this.j = 1000000 / i3;
        s();
        t(str, location);
    }

    static /* synthetic */ int c(k kVar) {
        int i = kVar.l;
        kVar.l = i + 1;
        return i;
    }

    static /* synthetic */ long i(k kVar, long j) {
        long j2 = kVar.k + j;
        kVar.k = j2;
        return j2;
    }

    private void s() {
        this.h = false;
        this.i = false;
        u();
    }

    private void t(String str, Location location) {
        try {
            MediaMuxer mediaMuxer = new MediaMuxer(str, 0);
            this.f2860c = mediaMuxer;
            if (location != null) {
                mediaMuxer.setLocation((float) location.getLatitude(), (float) location.getLongitude());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void u() {
        try {
            this.d = MediaCodec.createEncoderByType("video/avc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = this.f2858a;
        int i2 = this.f2859b;
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat("video/avc", i, i2);
        mediaFormatCreateVideoFormat.setInteger("bitrate", i * i2 * 4);
        mediaFormatCreateVideoFormat.setInteger("frame-rate", this.f);
        mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 1);
        this.d.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.e = this.d.createInputSurface();
        this.d.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void v() {
        MediaMuxer mediaMuxer;
        StringBuilder sb = new StringBuilder();
        sb.append("muxerRelease -- videoMediaCodec==null : ");
        sb.append(this.d == null);
        w.c(sb.toString());
        if (this.d == null && (mediaMuxer = this.f2860c) != null) {
            try {
                mediaMuxer.stop();
            } catch (Exception unused) {
            }
            try {
                this.f2860c.release();
            } catch (Exception unused2) {
            }
            this.f2860c = null;
            a aVar = this.g;
            if (aVar != null) {
                aVar.a(this.l);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void w() {
        if (this.h && !this.i) {
            this.i = true;
            this.f2860c.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void x(int i, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        if (this.i) {
            this.f2860c.writeSampleData(i, byteBuffer, bufferInfo);
        }
    }

    public void A() {
        w.c("stop in VideoEncoder");
        this.d.signalEndOfInputStream();
    }

    public Surface r() {
        return this.e;
    }

    public void y(a aVar) {
        this.g = aVar;
    }

    public void z() {
        new Thread(new b()).start();
    }
}
