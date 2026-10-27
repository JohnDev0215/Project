package a.b.a.a.o.g;

import a.b.a.a.n.b;
import a.b.a.a.o.d.b.e;
import a.b.a.a.o.d.b.f;
import a.b.a.a.o.d.b.i;
import a.b.a.a.o.d.b.k;
import a.b.a.a.o.e.b;
import a.b.a.a.o.e.h;
import a.b.a.a.o.g.a;
import android.view.Surface;
import com.netinsight.sye.syeClient.SyeAPI;
import com.netinsight.sye.syeClient.video.SyeVideoSample;
import com.netinsight.sye.syeClient.video.SyeVideoStreamInfo;
import com.netinsight.sye.syeClient.view.ISyeDisplaySettings;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.time.DurationKt;

/* JADX INFO: loaded from: classes.dex */
public final class b implements a.b.a.a.o.g.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f141a;
    public final a.b.a.a.i.b b;
    public long d;
    public final int j;
    public final a.InterfaceC0023a k;
    public final LinkedList<SyeVideoSample> c = new LinkedList<>();
    public volatile h e = new h();
    public final HashMap<a.b.a.a.o.e.b, a.b.a.a.o.d.b.a> f = new HashMap<>();
    public final Object g = new Object();
    public boolean h = true;
    public boolean i = true;

    public b(int i, a.InterfaceC0023a interfaceC0023a) {
        this.j = i;
        this.k = interfaceC0023a;
        String str = "SyeVideoManager_P" + i;
        this.f141a = str;
        this.b = a.b.a.a.i.b.d.a(str, b.a.Video);
    }

    @Override // a.b.a.a.o.g.a
    public synchronized void a() {
        synchronized (this.g) {
            Iterator<a.b.a.a.o.e.b> it = this.f.keySet().iterator();
            while (it.hasNext()) {
                a.b.a.a.o.d.b.a aVar = this.f.get(it.next());
                if (aVar != null) {
                    ExecutorService decodeThread = aVar.n;
                    Intrinsics.checkExpressionValueIsNotNull(decodeThread, "decodeThread");
                    aVar.a(decodeThread, new a.b.a.a.o.d.b.h(aVar));
                }
            }
        }
        synchronized (this.c) {
            this.c.clear();
        }
    }

    @Override // a.b.a.a.o.g.a
    public synchronized void a(a.b.a.a.o.e.b bVar) {
        this.b.a(this.f141a, "removeDisplaySurface");
        bVar.setSurfaceReceiver(null);
        c(bVar);
    }

    @Override // a.b.a.a.o.g.a
    public void a(SyeVideoStreamInfo syeVideoStreamInfo) {
    }

    public final void a(boolean z) {
        this.i = z;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [T, java.util.Iterator, java.util.Iterator<a.b.a.a.o.e.b>] */
    @Override // a.b.a.a.o.g.a
    public Iterator<a.b.a.a.o.e.b> b() {
        ?? it;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        synchronized (this.g) {
            it = new HashMap(this.f).keySet().iterator();
            objectRef.element = it;
        }
        if (it == 0) {
            Intrinsics.throwNpe();
        }
        return it;
    }

    @Override // a.b.a.a.o.g.a
    public synchronized void b(a.b.a.a.o.e.b bVar) {
        this.b.a(this.f141a, "addDisplaySurface");
        bVar.setSurfaceReceiver(new a(bVar));
    }

    public final void b(boolean z) {
        this.h = z;
    }

    public final synchronized void c(a.b.a.a.o.e.b bVar) {
        int andSet;
        this.b.a(this.f141a, "stopDecoder");
        synchronized (this.g) {
            a.b.a.a.o.d.b.a aVar = this.f.get(bVar);
            andSet = 0;
            if (aVar != null) {
                andSet = aVar.g.getAndSet(0);
                ExecutorService decodeThread = aVar.n;
                Intrinsics.checkExpressionValueIsNotNull(decodeThread, "decodeThread");
                aVar.a(decodeThread, new i(aVar));
            }
            this.f.remove(bVar);
        }
        if (andSet > 0) {
            this.k.a(a.b.a.a.k.e.f.b.DroppedFrames, andSet);
        }
    }

    @Override // a.b.a.a.o.g.a
    public void onVideoSample(SyeVideoSample syeVideoSample) {
        long syncTimeMicros = SyeAPI.INSTANCE.getSyncTimeMicros();
        if (syeVideoSample.getIsDiscontinuity()) {
            a(syeVideoSample);
        }
        synchronized (this.c) {
            Integer numA = a.b.a.a.n.a.a(this.c, syncTimeMicros);
            if (numA != null) {
                this.c.subList(0, numA.intValue()).clear();
            }
            this.c.offer(syeVideoSample);
        }
        synchronized (this.g) {
            for (a.b.a.a.o.d.b.a aVar : this.f.values()) {
                aVar.getClass();
                ExecutorService decodeThread = aVar.n;
                Intrinsics.checkExpressionValueIsNotNull(decodeThread, "decodeThread");
                aVar.a(decodeThread, new e(aVar, syeVideoSample));
            }
            if (syncTimeMicros - this.d >= ((long) DurationKt.NANOS_IN_MILLIS)) {
                Iterator<a.b.a.a.o.d.b.a> it = this.f.values().iterator();
                int i = 0;
                while (it.hasNext()) {
                    int andSet = it.next().g.getAndSet(0);
                    if (andSet > i) {
                        i = andSet;
                    }
                }
                if (i > 0) {
                    this.k.a(a.b.a.a.k.e.f.b.DroppedFrames, i);
                }
                this.d = syncTimeMicros;
            }
        }
    }

    public final void a(SyeVideoSample syeVideoSample) {
        int i;
        int i2;
        synchronized (this.c) {
            int width = syeVideoSample.getWidth();
            h hVar = this.e;
            synchronized (hVar) {
                i = hVar.f139a;
            }
            if (width > i) {
                a.b.a.a.o.e.c cVarA = k.b.a(syeVideoSample.getAndroidMimeType$syeClient_release(), syeVideoSample.getWidth(), syeVideoSample.getHeight());
                h hVar2 = this.e;
                h hVar3 = (h) cVarA;
                synchronized (hVar3) {
                    i2 = hVar3.f139a;
                }
                hVar2.a(i2, ((h) cVarA).getHeight());
            }
        }
        synchronized (this.g) {
            for (a.b.a.a.o.d.b.a aVar : this.f.values()) {
                h hVar4 = this.e;
                aVar.getClass();
                ExecutorService decodeThread = aVar.n;
                Intrinsics.checkExpressionValueIsNotNull(decodeThread, "decodeThread");
                aVar.a(decodeThread, new f(aVar, hVar4));
            }
        }
    }

    public static final class a implements b.a {
        public final /* synthetic */ a.b.a.a.o.e.b b;

        public a(a.b.a.a.o.e.b bVar) {
            this.b = bVar;
        }

        @Override // a.b.a.a.o.e.b.a
        public void a(ISyeDisplaySettings iSyeDisplaySettings) {
            a.b.a.a.o.d.b.a aVar;
            synchronized (b.this.g) {
                aVar = b.this.f.get(this.b);
            }
            if (aVar != null) {
                ISyeDisplaySettings.VideoGravity gravity = iSyeDisplaySettings.getGravity();
                ExecutorService decodeThread = aVar.n;
                Intrinsics.checkExpressionValueIsNotNull(decodeThread, "decodeThread");
                aVar.a(decodeThread, new a.b.a.a.o.d.b.b(aVar, gravity));
            }
        }

        @Override // a.b.a.a.o.e.b.a
        public void a(Surface surface) {
            b bVar = b.this;
            bVar.b.a(bVar.f141a, "onSurface available");
            b bVar2 = b.this;
            a.b.a.a.o.e.b bVar3 = this.b;
            bVar2.getClass();
            try {
                a.b.a.a.o.d.b.a aVar = new a.b.a.a.o.d.b.a(bVar2.j, bVar3.getDisplaySettings().getGravity(), surface, bVar2.e, bVar2.h, bVar2.i, new c(bVar2, bVar3));
                synchronized (bVar2.c) {
                    bVar2.b.a(bVar2.f141a, "Feeding newly attached decoder with " + bVar2.c.size() + " samples");
                    for (SyeVideoSample sample : bVar2.c) {
                        Intrinsics.checkExpressionValueIsNotNull(sample, "sample");
                        ExecutorService decodeThread = aVar.n;
                        Intrinsics.checkExpressionValueIsNotNull(decodeThread, "decodeThread");
                        aVar.a(decodeThread, new e(aVar, sample));
                    }
                }
                synchronized (bVar2.g) {
                    bVar2.f.put(bVar3, aVar);
                }
            } catch (Exception e) {
                throw new a.b.a.a.j.a(e.getMessage());
            }
        }

        @Override // a.b.a.a.o.e.b.a
        public void a() {
            b bVar = b.this;
            bVar.b.a(bVar.f141a, "onSurfaceDestroyed");
            b.this.c(this.b);
        }
    }
}
