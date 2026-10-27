package a.b.a.a.o.d.b;

import a.b.a.a.n.b;
import a.b.a.a.o.d.b.m;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import com.google.android.gms.cast.MediaError;
import com.netinsight.sye.syeClient.SyeAPI;
import com.netinsight.sye.syeClient.video.SyeVideoSample;
import com.netinsight.sye.syeClient.view.ISyeDisplaySettings;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(21)
public final class a extends MediaCodec.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f98a;
    public final String b;
    public final a.b.a.a.i.b c;
    public e d;
    public c e;
    public b f;
    public AtomicInteger g;
    public final LinkedList<Integer> h;
    public final LinkedList<SyeVideoSample> i;

    @SuppressLint({"UseSparseArrays"})
    public final HashMap<Long, g> j;
    public MediaCodec k;
    public m l;
    public final m.a m;
    public final ExecutorService n;
    public final ExecutorService o;
    public final HandlerThread p;
    public a.b.a.a.o.d.b.j q;
    public a.b.a.a.o.d.b.l r;
    public final int s;
    public ISyeDisplaySettings.VideoGravity t;
    public final Surface u;
    public a.b.a.a.o.e.c v;
    public final boolean w;
    public final boolean x;
    public final d y;

    /* JADX INFO: renamed from: a.b.a.a.o.d.b.a$a, reason: collision with other inner class name */
    public static final class C0020a implements m.a {

        /* JADX INFO: renamed from: a.b.a.a.o.d.b.a$a$a, reason: collision with other inner class name */
        public static final class RunnableC0021a implements Runnable {
            public RunnableC0021a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                a aVar = a.this;
                c cVar = aVar.e;
                SyeVideoSample syeVideoSample = cVar != null ? cVar.b : null;
                if (syeVideoSample != null) {
                    aVar.c.a(aVar.b, "Received EOS: Done draining sink with '" + a.this.j.size() + "' remaining frames: starting new decoding session");
                    a aVar2 = a.this;
                    a.a(aVar2, syeVideoSample, aVar2.v, true);
                }
            }
        }

        public C0020a() {
        }

        @Override // a.b.a.a.o.d.b.m.a
        public void a() {
            a aVar = a.this;
            ExecutorService decodeThread = aVar.n;
            Intrinsics.checkExpressionValueIsNotNull(decodeThread, "decodeThread");
            aVar.a(decodeThread, new RunnableC0021a());
        }

        @Override // a.b.a.a.o.d.b.m.a
        public void b() {
            a.this.g.incrementAndGet();
        }
    }

    public final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final SyeVideoSample f101a;
        public final long b;

        public b(a aVar, SyeVideoSample syeVideoSample, long j) {
            this.f101a = syeVideoSample;
            this.b = j;
        }
    }

    public final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f102a = 900000;
        public final SyeVideoSample b;
        public final long c;
        public final long d;

        public c(a aVar, SyeVideoSample syeVideoSample, long j, long j2) {
            this.b = syeVideoSample;
            this.c = j;
            this.d = j2;
            aVar.c.a(aVar.b, "Draining timing out in " + ((j2 - SyeAPI.INSTANCE.getSyncTimeMicros()) / ((long) 1000)) + " ms");
        }
    }

    public interface d {
        void a(int i, int i2);

        void a(long j);

        void a(a.b.a.a.k.e.f.a aVar, String str);
    }

    public enum e {
        STOPPED,
        EXECUTING,
        ERROR,
        TERMINATED
    }

    public static final class f extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public MediaCodec f104a;
        public Exception b;
        public boolean c;
        public final Object d;
        public final a.b.a.a.o.f.a e;
        public final MediaCodec.Callback f;

        public f(Looper looper, a.b.a.a.o.f.a aVar, MediaCodec.Callback callback) {
            super(looper);
            this.e = aVar;
            this.f = callback;
            this.d = new Object();
        }

        public final MediaCodec a() throws Exception {
            this.c = false;
            sendEmptyMessage(0);
            synchronized (this.d) {
                while (!this.c) {
                    try {
                        this.d.wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
            Exception exc = this.b;
            if (exc != null) {
                throw exc;
            }
            MediaCodec mediaCodec = this.f104a;
            if (mediaCodec == null) {
                Intrinsics.throwNpe();
            }
            return mediaCodec;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                MediaCodec mediaCodecA = a.b.a.a.o.d.a.b.a(this.e);
                mediaCodecA.setCallback(this.f);
                this.f104a = mediaCodecA;
            } catch (Exception e) {
                this.b = e;
            }
            synchronized (this.d) {
                this.c = true;
                this.d.notifyAll();
            }
        }
    }

    public final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f105a;
        public final boolean b;

        public g(a aVar, boolean z, boolean z2) {
            this.f105a = z;
            this.b = z2;
        }
    }

    public static final class h extends Lambda implements Function1<Long, Unit> {
        public h() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public Unit invoke(Long l) {
            long jLongValue = l.longValue();
            a aVar = a.this;
            ExecutorService eventListenerThread = aVar.o;
            Intrinsics.checkExpressionValueIsNotNull(eventListenerThread, "eventListenerThread");
            aVar.a(eventListenerThread, new a.b.a.a.o.d.b.c(this, jLongValue));
            return Unit.INSTANCE;
        }
    }

    public static final class i implements Runnable {
        public final /* synthetic */ MediaCodec.CodecException b;
        public final /* synthetic */ MediaCodec c;

        public i(MediaCodec.CodecException codecException, MediaCodec mediaCodec) {
            this.b = codecException;
            this.c = mediaCodec;
        }

        @Override // java.lang.Runnable
        public final void run() {
            b bVar = a.this.f;
            SyeVideoSample syeVideoSample = bVar != null ? bVar.f101a : null;
            if (!this.b.isRecoverable() || syeVideoSample == null) {
                String str = this.b.isTransient() ? "transient" : "fatal";
                String strValueOf = this.b.getErrorCode() == 1100 ? "insufficient resources" : this.b.getErrorCode() == 1101 ? "reclaimed resources" : String.valueOf(this.b.getErrorCode());
                a.this.a(a.b.a.a.k.e.f.a.PlatformException, -1L, "Decoder received a " + str + " error: '" + this.b.getMessage() + "' - '" + strValueOf + "' - '" + this.b.getDiagnosticInfo() + '\'');
                return;
            }
            a aVar = a.this;
            aVar.c.e(aVar.b, "Decoder received a recoverable error: " + this.b.getMessage() + ": starting new decoding session");
            try {
                this.c.reset();
                a aVar2 = a.this;
                a.a(aVar2, syeVideoSample, aVar2.v, false);
            } catch (Exception e) {
                a.this.a(a.b.a.a.k.e.f.a.PlatformException, -1L, "Decoder received error: '" + e.getMessage() + '\'');
            }
        }
    }

    public static final class j implements Runnable {
        public final /* synthetic */ MediaCodec b;
        public final /* synthetic */ int c;

        public j(MediaCodec mediaCodec, int i) {
            this.b = mediaCodec;
            this.c = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            a aVar = a.this;
            if (aVar.d != e.EXECUTING) {
                aVar.c.a(aVar.b, "Received available input buffer in state: " + a.this.d.name());
                return;
            }
            if (!Intrinsics.areEqual(this.b, aVar.k)) {
                a aVar2 = a.this;
                aVar2.c.a(aVar2.b, "Received available input buffer from old decoder");
            } else {
                a.this.h.add(Integer.valueOf(this.c));
                a.this.a(SyeAPI.INSTANCE.getSyncTimeMicros());
            }
        }
    }

    public static final class k implements Runnable {
        public final /* synthetic */ MediaCodec b;
        public final /* synthetic */ MediaCodec.BufferInfo c;
        public final /* synthetic */ int d;

        public k(MediaCodec mediaCodec, MediaCodec.BufferInfo bufferInfo, int i) {
            this.b = mediaCodec;
            this.c = bufferInfo;
            this.d = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            a aVar = a.this;
            if (aVar.d != e.EXECUTING) {
                aVar.c.a(aVar.b, "Received available output buffer in state: " + a.this.d.name());
                return;
            }
            if (!Intrinsics.areEqual(this.b, aVar.k)) {
                a aVar2 = a.this;
                aVar2.c.a(aVar2.b, "Received available output buffer from old decoder");
                return;
            }
            g gVarRemove = a.this.j.remove(Long.valueOf(this.c.presentationTimeUs));
            if (gVarRemove == null) {
                gVarRemove = new g(a.this, false, false);
            }
            g gVar = gVarRemove;
            Intrinsics.checkExpressionValueIsNotNull(gVar, "samplesAwaitingRender.re…alse, decodeOnly = false)");
            m mVar = a.this.l;
            MediaCodec mediaCodec = this.b;
            int i = this.d;
            MediaCodec.BufferInfo bufferInfo = this.c;
            mVar.getClass();
            ScheduledExecutorService renderThread = mVar.e;
            Intrinsics.checkExpressionValueIsNotNull(renderThread, "renderThread");
            mVar.a(renderThread, new n(mVar, mediaCodec, i, bufferInfo, gVar));
        }
    }

    public static final class l implements Runnable {
        public final /* synthetic */ MediaFormat b;

        public l(MediaFormat mediaFormat) {
            this.b = mediaFormat;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int integer = this.b.getInteger("width");
            if (this.b.containsKey("crop-left") && this.b.containsKey("crop-right")) {
                integer = (this.b.getInteger("crop-right") + 1) - this.b.getInteger("crop-left");
            }
            int integer2 = this.b.getInteger("height");
            if (this.b.containsKey("crop-top") && this.b.containsKey("crop-bottom")) {
                integer2 = (this.b.getInteger("crop-bottom") + 1) - this.b.getInteger("crop-top");
            }
            a aVar = a.this;
            a.b.a.a.i.b bVar = aVar.c;
            String str = aVar.b;
            StringBuilder sb = new StringBuilder();
            Thread threadCurrentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(": onOutputFormatChanged ");
            sb.append(integer);
            sb.append('x');
            sb.append(integer2);
            bVar.a(str, sb.toString());
            a.this.y.a(integer, integer2);
        }
    }

    public a(int i2, ISyeDisplaySettings.VideoGravity videoGravity, Surface surface, a.b.a.a.o.e.c cVar, boolean z, boolean z2, d dVar) {
        this.s = i2;
        this.t = videoGravity;
        this.u = surface;
        this.v = cVar;
        this.w = z;
        this.x = z2;
        this.y = dVar;
        StringBuilder sb = new StringBuilder();
        sb.append("AsyncVideoDecoder_P");
        sb.append(i2);
        sb.append("_V");
        int i3 = f98a + 1;
        f98a = i3;
        sb.append(i3);
        sb.append('}');
        String string = sb.toString();
        this.b = string;
        this.d = e.STOPPED;
        this.g = new AtomicInteger();
        this.h = new LinkedList<>();
        this.i = new LinkedList<>();
        this.j = new HashMap<>();
        this.n = Executors.newSingleThreadExecutor();
        this.o = Executors.newSingleThreadExecutor();
        this.q = b();
        a.b.a.a.i.b bVarA = a.b.a.a.i.b.d.a(string, b.a.Video);
        this.c = bVarA;
        HandlerThread handlerThread = new HandlerThread(string + "_callbackThread");
        this.p = handlerThread;
        handlerThread.start();
        if (z) {
            bVarA.a(string, "Enabling video frame release helper");
            a.b.a.a.o.d.b.l lVar = new a.b.a.a.o.d.b.l(60.0f);
            lVar.a();
            this.r = lVar;
        }
        C0020a c0020a = new C0020a();
        this.m = c0020a;
        m mVar = new m(i2, c0020a, this.q, this.r);
        this.l = mVar;
        mVar.a(z2);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0101 A[Catch: Exception -> 0x01ae, IllegalArgumentException -> 0x01c4, TryCatch #6 {IllegalArgumentException -> 0x01c4, Exception -> 0x01ae, blocks: (B:27:0x00ef, B:29:0x0101, B:30:0x0104, B:32:0x0125, B:33:0x0128), top: B:62:0x00ef }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0125 A[Catch: Exception -> 0x01ae, IllegalArgumentException -> 0x01c4, TryCatch #6 {IllegalArgumentException -> 0x01c4, Exception -> 0x01ae, blocks: (B:27:0x00ef, B:29:0x0101, B:30:0x0104, B:32:0x0125, B:33:0x0128), top: B:62:0x00ef }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0145 A[Catch: Exception -> 0x016a, TryCatch #3 {Exception -> 0x016a, blocks: (B:37:0x0141, B:39:0x0145, B:40:0x0148), top: B:60:0x0141 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x018c A[Catch: Exception -> 0x019c, TryCatch #2 {Exception -> 0x019c, blocks: (B:43:0x0188, B:45:0x018c, B:46:0x018f), top: B:58:0x0188 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(a.b.a.a.o.d.b.a r9, com.netinsight.sye.syeClient.video.SyeVideoSample r10, a.b.a.a.o.e.c r11, boolean r12) {
        /*
            Method dump skipped, instruction units count: 483
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.a.a.o.d.b.a.a(a.b.a.a.o.d.b.a, com.netinsight.sye.syeClient.video.SyeVideoSample, a.b.a.a.o.e.c, boolean):void");
    }

    public final void a() {
        a(true);
        MediaCodec mediaCodec = this.k;
        if (mediaCodec != null) {
            mediaCodec.release();
        }
        this.k = null;
    }

    public final void a(a.b.a.a.k.e.f.a aVar, long j2, String str) {
        a.b.a.a.i.b bVar;
        String str2;
        String str3;
        this.c.e(this.b, "enterErrorState begin: '" + str + "' - stopping and reporting error");
        if (this.d != e.EXECUTING) {
            bVar = this.c;
            str2 = this.b;
            str3 = "Unable to enter " + MediaError.ERROR_TYPE_ERROR + " state from state '" + this.d.name() + "' - skipping";
        } else {
            a(e.ERROR);
            a();
            if (aVar == a.b.a.a.k.e.f.a.UnsupportedVideoFormat) {
                str = String.valueOf(j2);
            }
            this.y.a(aVar, str);
            bVar = this.c;
            str2 = this.b;
            str3 = "enterErrorState end";
        }
        bVar.a(str2, str3);
    }

    public final void a(e eVar) {
        this.c.a(this.b, "Going from state '" + this.d.name() + "' --> '" + eVar.name() + '\'');
        this.d = eVar;
    }

    public final void a(ExecutorService executorService, Runnable runnable) {
        try {
            executorService.submit(runnable);
        } catch (RejectedExecutionException e2) {
            String str = Intrinsics.areEqual(executorService, this.n) ? "Decode" : "Callback";
            this.c.a(this.b, Thread.currentThread() + ": Unable to submit task on '" + str + "' thread in state " + this.d + ": " + e2.getMessage());
        }
    }

    public final a.b.a.a.o.d.b.j b() {
        return new a.b.a.a.o.d.b.j(this.s, new h());
    }

    @Override // android.media.MediaCodec.Callback
    public void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
        ExecutorService decodeThread = this.n;
        Intrinsics.checkExpressionValueIsNotNull(decodeThread, "decodeThread");
        a(decodeThread, new i(codecException, mediaCodec));
    }

    @Override // android.media.MediaCodec.Callback
    public void onInputBufferAvailable(MediaCodec mediaCodec, int i2) {
        ExecutorService decodeThread = this.n;
        Intrinsics.checkExpressionValueIsNotNull(decodeThread, "decodeThread");
        a(decodeThread, new j(mediaCodec, i2));
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputBufferAvailable(MediaCodec mediaCodec, int i2, MediaCodec.BufferInfo bufferInfo) {
        ExecutorService decodeThread = this.n;
        Intrinsics.checkExpressionValueIsNotNull(decodeThread, "decodeThread");
        a(decodeThread, new k(mediaCodec, bufferInfo, i2));
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        ExecutorService decodeThread = this.n;
        Intrinsics.checkExpressionValueIsNotNull(decodeThread, "decodeThread");
        a(decodeThread, new a.b.a.a.o.d.b.b(this, null));
        ExecutorService eventListenerThread = this.o;
        Intrinsics.checkExpressionValueIsNotNull(eventListenerThread, "eventListenerThread");
        a(eventListenerThread, new l(mediaFormat));
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x017d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(long r17) {
        /*
            Method dump skipped, instruction units count: 854
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.a.a.o.d.b.a.a(long):void");
    }

    public final void a(boolean z) {
        m mVar = this.l;
        synchronized (mVar.j) {
            try {
                mVar.d.a(mVar.c, "Shutting down");
                mVar.e.shutdownNow();
            } catch (SecurityException e2) {
                mVar.d.e(mVar.c, "Unable to shutdown render thread: " + e2);
            }
        }
        try {
            this.c.a(this.b, "stopDecoderAndFlushBuffers(" + z + "): Stopping decoder");
            MediaCodec mediaCodec = this.k;
            if (mediaCodec != null) {
                mediaCodec.stop();
            }
            this.c.a(this.b, "Decoder stopped");
        } catch (IllegalStateException e3) {
            this.c.a(this.b, "Stop decoder failed: " + e3.getMessage());
        }
        if (z) {
            this.i.clear();
        }
        this.h.clear();
        this.j.clear();
        this.e = null;
        this.f = null;
    }
}
