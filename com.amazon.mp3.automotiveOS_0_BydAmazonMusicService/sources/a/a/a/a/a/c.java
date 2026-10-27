package a.a.a.a.a;

import a.b.a.a.e.c;
import a.b.a.a.n.b;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Process;
import android.view.Surface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class c implements a.b.a.a.e.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f10a = 1;
    public final String b;
    public final a.b.a.a.i.b c;
    public final MediaCodec d;
    public a.b.a.a.n.c<a.a.a.a.a.a> e;
    public final a.b.a.a.n.c<a.a.a.a.a.a> f;
    public final Object g;
    public AtomicBoolean h;
    public final List<Thread> i;
    public a j;
    public final MediaFormat k;
    public final String l;
    public final c.a m;

    public interface a {
        void a();
    }

    public interface b {
        void run();
    }

    /* JADX INFO: renamed from: a.a.a.a.a.c$c, reason: collision with other inner class name */
    public static final class RunnableC0000c implements Runnable {
        public RunnableC0000c() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            List<Thread> internalThreads = c.this.i;
            Intrinsics.checkExpressionValueIsNotNull(internalThreads, "internalThreads");
            synchronized (internalThreads) {
                Iterator<Thread> it = c.this.i.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                c cVar = c.this;
                cVar.c.a(cVar.b, "All threads stopped. Releasing MediaCodec...");
                c.this.d.release();
            }
        }
    }

    public static final class d implements Runnable {
        public final /* synthetic */ b b;
        public final /* synthetic */ String c;

        public d(b bVar, String str) {
            this.b = bVar;
            this.c = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Process.setThreadPriority(-19);
            while (!Thread.interrupted()) {
                try {
                    try {
                        this.b.run();
                    } catch (RuntimeException e) {
                        if (!Thread.interrupted()) {
                            throw e;
                        }
                        Thread.currentThread().interrupt();
                    }
                    Thread.sleep(1L);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
            c cVar = c.this;
            cVar.c.a(cVar.b, this.c + " killed");
        }
    }

    public static final class e implements b {
        public e() {
        }

        @Override // a.a.a.a.a.c.b
        public void run() {
            boolean zIsEmpty;
            a.a.a.a.a.a aVarPoll;
            a.b.a.a.n.c<a.a.a.a.a.a> cVar = c.this.f;
            synchronized (cVar) {
                zIsEmpty = cVar.f87a.isEmpty();
            }
            if (zIsEmpty) {
                return;
            }
            a.b.a.a.n.c<a.a.a.a.a.a> cVar2 = c.this.f;
            synchronized (cVar2) {
                aVarPoll = cVar2.f87a.poll();
            }
            if (aVarPoll == null) {
                Intrinsics.throwNpe();
            }
            a.a.a.a.a.a aVar = aVarPoll;
            a.a.a.a.a.b bVar = aVar.f8a;
            if (bVar == a.a.a.a.a.b.DECODED_DATA) {
                c.this.m.a(aVar.b, aVar.c);
                return;
            }
            if (bVar == a.a.a.a.a.b.END_OF_STREAM) {
                c.this.m.a(null, 0L);
                a aVar2 = c.this.j;
                if (aVar2 != null) {
                    aVar2.a();
                }
            }
        }
    }

    public c(MediaFormat mediaFormat, String str, c.a aVar, int i) throws IOException {
        this.k = mediaFormat;
        this.l = str;
        this.m = aVar;
        StringBuilder sb = new StringBuilder();
        sb.append("AudioDecoder_P");
        sb.append(i);
        sb.append("_A");
        int i2 = f10a;
        f10a = i2 + 1;
        sb.append(i2);
        String string = sb.toString();
        this.b = string;
        this.c = a.b.a.a.i.b.d.a(string, b.a.Audio);
        MediaCodec mediaCodecCreateDecoderByType = MediaCodec.createDecoderByType(str);
        mediaCodecCreateDecoderByType.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 0);
        mediaCodecCreateDecoderByType.start();
        Intrinsics.checkExpressionValueIsNotNull(mediaCodecCreateDecoderByType, "MediaCodec.createDecoder…    start()\n            }");
        this.d = mediaCodecCreateDecoderByType;
        this.e = new a.b.a.a.n.c<>();
        this.f = new a.b.a.a.n.c<>();
        this.g = new Object();
        this.h = new AtomicBoolean(false);
        this.i = Collections.synchronizedList(new ArrayList());
    }

    @Override // a.b.a.a.e.c
    public void a() {
        this.m.a();
    }

    @Override // a.b.a.a.e.c
    public void a(float f) {
        this.m.a(f);
    }

    public final void a(String str, b bVar) {
        Thread thread = new Thread(new d(bVar, str), "AudioDecoder job thread: " + str);
        this.i.add(thread);
        thread.start();
    }

    @Override // a.b.a.a.e.c
    public boolean a(a.a.a.a.a.a aVar) {
        a.b.a.a.n.c<a.a.a.a.a.a> cVar = this.e;
        synchronized (cVar) {
            cVar.f87a.offer(aVar);
        }
        return true;
    }

    @Override // a.b.a.a.e.c
    public void b() {
        this.m.b();
        a("OutputQueueThread", new e());
    }

    @Override // a.b.a.a.e.c
    public void c() {
        this.d.reset();
        this.d.configure(this.k, (Surface) null, (MediaCrypto) null, 0);
        this.d.start();
        this.h.set(false);
    }

    @Override // a.b.a.a.e.c
    public boolean d() {
        return this.h.get();
    }

    @Override // a.b.a.a.e.c
    public void e() {
        this.c.a(this.b, "Kill threads requested");
        List<Thread> internalThreads = this.i;
        Intrinsics.checkExpressionValueIsNotNull(internalThreads, "internalThreads");
        synchronized (internalThreads) {
            Iterator<Thread> it = this.i.iterator();
            while (it.hasNext()) {
                it.next().interrupt();
            }
        }
        this.c.a(this.b, "Threads interrupted");
        new Thread(new RunnableC0000c()).start();
    }

    public void a(Queue<a.a.a.a.a.a> queue) {
        synchronized (this.g) {
            a.b.a.a.n.c<a.a.a.a.a.a> cVar = this.e;
            LinkedList<T> linkedList = new LinkedList<>(queue);
            synchronized (cVar) {
                cVar.f87a = linkedList;
            }
        }
    }

    @Override // a.b.a.a.e.c
    public void a(a aVar) {
        a.b.a.a.n.c<a.a.a.a.a.a> cVar = this.e;
        a.a.a.a.a.a aVar2 = new a.a.a.a.a.a(a.a.a.a.a.b.END_OF_STREAM, null, 0L);
        synchronized (cVar) {
            cVar.f87a.offer(aVar2);
        }
        this.j = aVar;
    }
}
