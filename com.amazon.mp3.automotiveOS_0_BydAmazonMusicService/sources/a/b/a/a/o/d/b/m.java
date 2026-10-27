package a.b.a.a.o.d.b;

import a.b.a.a.n.b;
import a.b.a.a.o.d.b.a;
import a.b.a.a.o.d.b.j;
import android.annotation.TargetApi;
import android.media.MediaCodec;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(21)
public final class m {
    public final String c;
    public final a.b.a.a.i.b d;
    public final ScheduledExecutorService e = Executors.newSingleThreadScheduledExecutor();
    public final Pair<Long, Long>[] f;
    public int g;
    public final a.b.a.a.n.d h;
    public boolean i;
    public final Object j;
    public final LinkedList<c> k;
    public final a l;
    public final j m;
    public final l n;
    public static final b b = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f125a = (int) TimeUnit.MILLISECONDS.toNanos(30);

    public interface a {
        void a();

        void b();
    }

    public static final class b {
    }

    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MediaCodec f126a;
        public final int b;
        public final MediaCodec.BufferInfo c;
        public final a.g d;

        public c(MediaCodec mediaCodec, int i, MediaCodec.BufferInfo bufferInfo, a.g gVar) {
            this.f126a = mediaCodec;
            this.b = i;
            this.c = bufferInfo;
            this.d = gVar;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return Intrinsics.areEqual(this.f126a, cVar.f126a) && this.b == cVar.b && Intrinsics.areEqual(this.c, cVar.c) && Intrinsics.areEqual(this.d, cVar.d);
        }

        public int hashCode() {
            MediaCodec mediaCodec = this.f126a;
            int iHashCode = (((mediaCodec != null ? mediaCodec.hashCode() : 0) * 31) + this.b) * 31;
            MediaCodec.BufferInfo bufferInfo = this.c;
            int iHashCode2 = (iHashCode + (bufferInfo != null ? bufferInfo.hashCode() : 0)) * 31;
            a.g gVar = this.d;
            return iHashCode2 + (gVar != null ? gVar.hashCode() : 0);
        }

        public String toString() {
            return "DecodedBuffer(codec=" + this.f126a + ", index=" + this.b + ", info=" + this.c + ", displayParams=" + this.d + ")";
        }
    }

    public static final class d implements Runnable {
        public d(Pair pair, MediaCodec.BufferInfo bufferInfo, MediaCodec mediaCodec, int i, c cVar) {
        }

        @Override // java.lang.Runnable
        public final void run() {
            m.this.a();
        }
    }

    public m(int i, a aVar, j jVar, l lVar) {
        this.l = aVar;
        this.m = jVar;
        this.n = lVar;
        this.c = "VideoRenderer_P" + i;
        Pair<Long, Long>[] pairArr = new Pair[10];
        for (int i2 = 0; i2 < 10; i2++) {
            pairArr[i2] = new Pair<>(0L, 0L);
        }
        this.f = pairArr;
        this.h = new a.b.a.a.n.d(20, 20);
        this.i = true;
        this.j = new Object();
        a.b.a.a.i.b bVarA = a.b.a.a.i.b.d.a(this.c, b.a.Video);
        this.d = bVarA;
        bVarA.a(this.c, "Creating new video renderer");
        this.k = new LinkedList<>();
    }

    public final void a() {
        a.b.a.a.i.b bVar;
        String str;
        StringBuilder sb;
        String str2;
        if (this.k.isEmpty()) {
            return;
        }
        c cVarRemoveFirst = this.k.removeFirst();
        MediaCodec mediaCodec = cVarRemoveFirst.f126a;
        int i = cVarRemoveFirst.b;
        MediaCodec.BufferInfo bufferInfo = cVarRemoveFirst.c;
        a.g gVar = cVarRemoveFirst.d;
        int i2 = 0;
        if ((bufferInfo.flags & 4) != 0) {
            this.l.a();
            return;
        }
        try {
            Pair<j.a, Long> pairA = this.m.a(bufferInfo.presentationTimeUs, (int) this.h.b, gVar.f105a, gVar.b, this.n);
            synchronized (this.j) {
                Thread threadCurrentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "Thread.currentThread()");
                if (threadCurrentThread.isInterrupted()) {
                    throw new InterruptedException();
                }
                int iOrdinal = pairA.getFirst().ordinal();
                if (iOrdinal == 0) {
                    this.l.b();
                } else if (iOrdinal != 1) {
                    if (iOrdinal == 2) {
                        Pair<Long, Long>[] pairArr = this.f;
                        int i3 = this.g;
                        this.g = i3 + 1;
                        pairArr[i3] = new Pair<>(Long.valueOf(bufferInfo.presentationTimeUs), Long.valueOf(System.nanoTime()));
                        int i4 = this.g;
                        if (i4 < this.f.length) {
                            i2 = i4;
                        }
                        this.g = i2;
                    } else if (iOrdinal == 3) {
                        Pair<Long, Long>[] pairArr2 = this.f;
                        int i5 = this.g;
                        this.g = i5 + 1;
                        pairArr2[i5] = new Pair<>(Long.valueOf(bufferInfo.presentationTimeUs), Long.valueOf(System.nanoTime()));
                        int i6 = this.g;
                        if (i6 < this.f.length) {
                            i2 = i6;
                        }
                        this.g = i2;
                        if (pairA.getSecond().longValue() > 0) {
                            mediaCodec.releaseOutputBuffer(i, pairA.getSecond().longValue());
                        }
                    } else {
                        if (iOrdinal != 4) {
                            throw new NoWhenBranchMatchedException();
                        }
                        this.k.addFirst(cVarRemoveFirst);
                        this.e.schedule(new d(pairA, bufferInfo, mediaCodec, i, cVarRemoveFirst), pairA.getSecond().longValue(), TimeUnit.NANOSECONDS);
                    }
                    mediaCodec.releaseOutputBuffer(i, true);
                }
                mediaCodec.releaseOutputBuffer(i, false);
            }
        } catch (MediaCodec.CodecException e) {
            e = e;
            bVar = this.d;
            str = this.c;
            sb = new StringBuilder();
            str2 = "Error releasing output buffer: ";
            sb.append(str2);
            sb.append(e.getMessage());
            bVar.e(str, sb.toString());
        } catch (IllegalStateException e2) {
            e = e2;
            bVar = this.d;
            str = this.c;
            sb = new StringBuilder();
            str2 = "Error releasing output buffer. We have been probably been stopped: ";
            sb.append(str2);
            sb.append(e.getMessage());
            bVar.e(str, sb.toString());
        } catch (InterruptedException unused) {
            this.d.a(this.c, "Render thread has been interrupted - We have been probably been stopped");
            Thread.currentThread().interrupt();
        }
    }

    public final void a(ExecutorService executorService, Runnable runnable) {
        try {
            executorService.submit(runnable);
        } catch (RejectedExecutionException unused) {
            this.d.a(this.c, Thread.currentThread() + ": Unable to submit task on render thread");
        }
    }

    public final void a(boolean z) {
        this.d.a(this.c, "Render latency compensation: " + z);
        this.i = z;
    }
}
