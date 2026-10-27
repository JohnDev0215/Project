package a.b.a.a.g;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextPaint;
import android.view.View;
import com.netinsight.sye.syeClient.SyeAPI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class e implements a.b.a.a.g.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f56a;
    public static final b b = new b();
    public Thread e;
    public final a.b.a.a.n.c<a.b.a.a.g.b> c = new a.b.a.a.n.c<>();
    public final List<d> d = Collections.synchronizedList(new ArrayList());
    public final AtomicBoolean g = new AtomicBoolean(false);
    public boolean h = true;
    public final Runnable f = new a();

    public static final class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Handler f57a = new Handler(Looper.getMainLooper());

        /* JADX INFO: renamed from: a.b.a.a.g.e$a$a, reason: collision with other inner class name */
        public static final class RunnableC0009a implements Runnable {
            public final /* synthetic */ a.b.a.a.g.b b;

            public RunnableC0009a(a.b.a.a.g.b bVar) {
                this.b = bVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                List<d> renderers = e.this.d;
                Intrinsics.checkExpressionValueIsNotNull(renderers, "renderers");
                synchronized (renderers) {
                    Iterator<d> it = e.this.d.iterator();
                    while (it.hasNext()) {
                        it.next().render(this.b);
                    }
                }
            }
        }

        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean zIsEmpty;
            a.b.a.a.g.b bVarPeekFirst;
            a.b.a.a.g.b bVar;
            a.b.a.a.g.b bVarPoll;
            while (e.this.g.get()) {
                a.b.a.a.n.c<a.b.a.a.g.b> cVar = e.this.c;
                synchronized (cVar) {
                    zIsEmpty = cVar.f87a.isEmpty();
                }
                if (!zIsEmpty) {
                    long syncTimeMicros = SyeAPI.INSTANCE.getSyncTimeMicros();
                    a.b.a.a.n.c<a.b.a.a.g.b> cVar2 = e.this.c;
                    synchronized (cVar2) {
                        bVarPeekFirst = cVar2.f87a.peekFirst();
                    }
                    a.b.a.a.g.b bVar2 = bVarPeekFirst;
                    if (bVar2 == null || bVar2.a() > syncTimeMicros) {
                        bVar = null;
                    } else {
                        a.b.a.a.n.c<a.b.a.a.g.b> cVar3 = e.this.c;
                        synchronized (cVar3) {
                            bVarPoll = cVar3.f87a.poll();
                        }
                        bVar = bVarPoll;
                    }
                    if (bVar != null && (e.this.h || bVar.b())) {
                        this.f57a.post(new RunnableC0009a(bVar));
                    }
                }
                if (e.this.g.get()) {
                    try {
                        Thread.sleep(5L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
    }

    public static final class b {
        public static final void a(b bVar, View view, boolean z) {
            if (Intrinsics.areEqual(Looper.getMainLooper(), Looper.myLooper())) {
                view.setVisibility(z ? 0 : 4);
                return;
            }
            Handler handler = view.getHandler();
            if (handler != null) {
                handler.post(new f(view, z));
            }
        }

        public final String a(TextPaint textPaint, Typeface typeface) {
            textPaint.setTypeface(typeface);
            return String.valueOf(((textPaint.measureText("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm") / ((float) 1.3333333333333333d)) / 32.0f) / 64.0f);
        }
    }

    public static final class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            List<d> renderers = e.this.d;
            Intrinsics.checkExpressionValueIsNotNull(renderers, "renderers");
            synchronized (renderers) {
                Iterator<d> it = e.this.d.iterator();
                while (it.hasNext()) {
                    it.next().clearText();
                }
            }
        }
    }

    @Override // a.b.a.a.g.a
    public synchronized void a() {
        if (this.g.compareAndSet(false, true)) {
            Thread thread = new Thread(this.f, "CC Manager");
            this.e = thread;
            thread.start();
        }
    }

    @Override // a.b.a.a.g.a
    public void a(d dVar) {
        this.d.remove(dVar);
    }

    @Override // a.b.a.a.g.a
    public synchronized void a(a.b.a.a.g.g.a aVar) {
        if (this.g.get()) {
            a.b.a.a.n.c<a.b.a.a.g.b> cVar = this.c;
            synchronized (cVar) {
                cVar.f87a.offer(aVar);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // a.b.a.a.g.a
    public void b(d dVar) {
        this.d.add(dVar);
        if (dVar instanceof View) {
            b.a(b, (View) dVar, this.h);
        }
    }

    @Override // a.b.a.a.g.a
    public synchronized void stop() {
        if (this.g.compareAndSet(true, false)) {
            try {
                a.b.a.a.n.c<a.b.a.a.g.b> cVar = this.c;
                synchronized (cVar) {
                    cVar.f87a.clear();
                }
                Thread thread = this.e;
                if (thread != null) {
                    thread.join();
                }
                new Handler(Looper.getMainLooper()).post(new c());
                this.e = null;
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread was interrupted while waiting for handlerThread to join.", e);
            }
        }
    }
}
