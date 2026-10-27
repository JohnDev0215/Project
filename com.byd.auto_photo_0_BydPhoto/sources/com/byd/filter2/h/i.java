package com.byd.filter2.h;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.byd.util.w;
import java.io.FileDescriptor;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f2844a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f2845b = new b(this);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private MediaExtractor f2846c;
    private MediaCodec d;
    private int e;
    private int f;
    private boolean g;
    private h h;
    private long i;
    private long j;
    private f k;

    class a extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f2847a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ FileDescriptor f2848b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Surface f2849c;

        a(String str, FileDescriptor fileDescriptor, Surface surface) {
            this.f2847a = str;
            this.f2848b = fileDescriptor;
            this.f2849c = surface;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (i.this.y(this.f2847a, this.f2848b, this.f2849c)) {
                i.this.g = true;
                new c(i.this, null).start();
            }
        }
    }

    private static class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<i> f2850a;

        public b(i iVar) {
            super(Looper.getMainLooper());
            this.f2850a = new WeakReference<>(iVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f2850a.get() == null) {
                return;
            }
            int i = message.what;
            if (i == 1) {
                int i2 = message.arg1;
                int i3 = message.arg2;
                this.f2850a.get().v(i2 >> 16, i2 & 65535, i3);
                return;
            }
            if (i == 2) {
                this.f2850a.get().A();
                sendEmptyMessageDelayed(2, 100L);
            } else if (i == 3) {
                this.f2850a.get().s();
            } else {
                if (i != 4) {
                    return;
                }
                this.f2850a.get().t();
            }
        }
    }

    private class c extends Thread {
        private c() {
        }

        /* synthetic */ c(i iVar, a aVar) {
            this();
        }

        /* JADX WARN: Removed duplicated region for block: B:56:0x0239 A[PHI: r0 r4
  0x0239: PHI (r0v15 boolean) = (r0v13 boolean), (r0v21 boolean) binds: [B:17:0x0133, B:42:0x01b4] A[DONT_GENERATE, DONT_INLINE]
  0x0239: PHI (r4v5 boolean) = (r4v4 boolean), (r4v16 boolean) binds: [B:17:0x0133, B:42:0x01b4] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 637
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.filter2.h.i.c.run():void");
        }
    }

    public i(h hVar) {
        this.h = hVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A() {
        f fVar = this.k;
        if (fVar != null) {
            fVar.d(this.f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(boolean z) {
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int iDequeueOutputBuffer = this.d.dequeueOutputBuffer(bufferInfo, 0L);
        if (iDequeueOutputBuffer >= 0) {
            if ((bufferInfo.flags & 4) == 0) {
                this.d.releaseOutputBuffer(iDequeueOutputBuffer, z);
            } else {
                Log.e("m_tag", "video end of stream");
                this.f2845b.sendEmptyMessage(3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        this.f2845b.removeMessages(2);
        this.e = 0;
        this.f = 0;
        f fVar = this.k;
        if (fVar != null) {
            fVar.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        this.f2845b.removeMessages(2);
        f fVar = this.k;
        if (fVar != null) {
            fVar.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(int i, int i2, int i3) {
        f fVar = this.k;
        if (fVar != null) {
            fVar.b(i, i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean y(String str, FileDescriptor fileDescriptor, Surface surface) {
        StringBuilder sb;
        String str2;
        MediaCodec mediaCodec;
        MediaExtractor mediaExtractor = new MediaExtractor();
        this.f2846c = mediaExtractor;
        try {
            if (str != null) {
                mediaExtractor.setDataSource(str);
            } else {
                if (fileDescriptor == null) {
                    this.f2845b.sendEmptyMessage(4);
                    w.c(getClass().getName() + "->1111");
                    return false;
                }
                mediaExtractor.setDataSource(fileDescriptor);
            }
            int i = 0;
            while (true) {
                if (i >= this.f2846c.getTrackCount()) {
                    break;
                }
                MediaFormat trackFormat = this.f2846c.getTrackFormat(i);
                String string = trackFormat.getString("mime");
                w.c("mime : " + string);
                if (string == null || !string.startsWith("video/")) {
                    i++;
                } else {
                    this.f2846c.selectTrack(i);
                    try {
                        this.d = MediaCodec.createDecoderByType(string);
                        int integer = trackFormat.getInteger("width");
                        int integer2 = trackFormat.getInteger("height");
                        this.e = (int) (trackFormat.getLong("durationUs") / 1000);
                        w.c("video info in decoder : " + integer + "x" + integer2 + " duration(ms) : " + this.e);
                        this.f2845b.obtainMessage(1, (integer << 16) | integer2, this.e).sendToTarget();
                        this.d.configure(trackFormat, surface, (MediaCrypto) null, 0);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            mediaCodec = this.d;
        } catch (Exception e2) {
            e2.printStackTrace();
            this.f2845b.sendEmptyMessage(4);
            sb = new StringBuilder();
            sb.append(i.class.getName());
            str2 = "->2222";
        }
        if (mediaCodec != null) {
            mediaCodec.start();
            return true;
        }
        this.f2845b.sendEmptyMessage(4);
        sb = new StringBuilder();
        sb.append(i.class.getName());
        str2 = "->3333";
        sb.append(str2);
        w.c(sb.toString());
        return false;
    }

    public void u() {
        Object obj = f2844a;
        synchronized (obj) {
            try {
                obj.notifyAll();
                w.c("start state notify");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void w() {
        this.f2845b.removeCallbacksAndMessages(null);
        this.g = false;
        MediaCodec mediaCodec = this.d;
        if (mediaCodec != null) {
            try {
                mediaCodec.stop();
            } catch (IllegalStateException unused) {
            }
            this.d.release();
            this.d = null;
        }
        MediaExtractor mediaExtractor = this.f2846c;
        if (mediaExtractor != null) {
            mediaExtractor.release();
            this.f2846c = null;
        }
    }

    public void x(f fVar) {
        this.k = fVar;
    }

    public void z(String str, FileDescriptor fileDescriptor, Surface surface, long j, long j2) {
        this.i = j;
        this.j = j2;
        new a(str, fileDescriptor, surface).start();
    }
}
