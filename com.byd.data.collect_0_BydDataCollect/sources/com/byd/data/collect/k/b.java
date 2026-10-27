package com.byd.data.collect.k;

import android.util.Log;
import com.byd.data.collect.k.b.C0061b;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: CanDataStreamManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b<T extends C0061b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Thread f3184c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f3182a = b.class.getName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BlockingQueue<T> f3183b = new LinkedBlockingQueue();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3185d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ReentrantLock f3186e = new ReentrantLock();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final List<b<T>.c<T>> f3187f = new CopyOnWriteArrayList();

    /* JADX INFO: compiled from: CanDataStreamManager.java */
    class a extends Thread {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            while (b.this.f3185d) {
                if (b.this.f3183b != null) {
                    b.this.f3186e.lock();
                    try {
                        if (!b.this.f3183b.isEmpty()) {
                            C0061b c0061b = (C0061b) b.this.f3183b.take();
                            Iterator it = b.this.f3187f.iterator();
                            while (it.hasNext()) {
                                ((c) it.next()).b(c0061b);
                            }
                        }
                        b.this.f3186e.unlock();
                    } catch (InterruptedException e2) {
                        throw new RuntimeException(e2);
                    }
                }
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.byd.data.collect.k.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CanDataStreamManager.java */
    public static class C0061b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public short f3189a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public byte f3190b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public byte[] f3191c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f3192d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3193e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public byte[] f3194f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f3195g;

        public String toString() {
            return "CanData{canId=" + Integer.toHexString(this.f3189a) + ", subCanId=" + Integer.toHexString(this.f3190b) + ", canData=" + c.a.a.g.d.g(this.f3191c, false) + ", isProp=" + this.f3192d + ", propId=" + Integer.toHexString(this.f3193e) + ", rawData=" + c.a.a.g.d.g(this.f3194f, false) + ", rawLen=" + this.f3195g + '}';
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: CanDataStreamManager.java */
    class c<T extends C0061b> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final d f3197b;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final BlockingQueue<T> f3196a = new LinkedBlockingQueue();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f3198c = true;

        public c(final d dVar) {
            this.f3197b = dVar;
            new Thread(new Runnable() { // from class: com.byd.data.collect.k.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3180b.d(dVar);
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d(d dVar) {
            while (this.f3198c) {
                try {
                    if (!this.f3196a.isEmpty()) {
                        dVar.a(this.f3196a.take());
                    }
                    try {
                        Thread.sleep(1L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                } catch (InterruptedException e3) {
                    throw new RuntimeException(e3);
                }
            }
        }

        public void b(T t) {
            try {
                this.f3196a.put(t);
            } catch (InterruptedException e2) {
                throw new RuntimeException(e2);
            }
        }

        public void e() {
            this.f3198c = false;
            this.f3196a.clear();
        }
    }

    /* JADX INFO: compiled from: CanDataStreamManager.java */
    public interface d {
        void a(C0061b c0061b);
    }

    /* JADX INFO: compiled from: CanDataStreamManager.java */
    public interface e {
        C0061b a(int i2, byte[] bArr, int i3, boolean z);
    }

    private void f() {
        this.f3184c = new a();
    }

    private c g(d dVar) {
        for (b<T>.c<T> cVar : this.f3187f) {
            if (((c) cVar).f3197b.equals(dVar)) {
                return cVar;
            }
        }
        return null;
    }

    public void e(d dVar) {
        if (this.f3187f.contains(g(dVar))) {
            return;
        }
        this.f3187f.add(new c<>(dVar));
    }

    public int h() {
        return this.f3187f.size();
    }

    public void i(T t) {
        try {
            this.f3183b.put(t);
        } catch (InterruptedException e2) {
            throw new RuntimeException(e2);
        }
    }

    public void j() {
        m();
        Iterator<b<T>.c<T>> it = this.f3187f.iterator();
        while (it.hasNext()) {
            it.next().e();
        }
        this.f3186e = null;
        this.f3187f.clear();
    }

    public void k(d dVar) {
        c cVarG = g(dVar);
        if (cVarG != null) {
            cVarG.e();
            this.f3186e.lock();
            this.f3187f.remove(cVarG);
            this.f3186e.unlock();
        }
    }

    public void l() {
        if (this.f3185d) {
            return;
        }
        this.f3185d = true;
        if (this.f3184c == null) {
            f();
        }
        this.f3184c.start();
    }

    public void m() {
        this.f3185d = false;
        this.f3183b.clear();
        Thread thread = this.f3184c;
        if (thread != null) {
            try {
                thread.join();
                this.f3184c = null;
            } catch (InterruptedException e2) {
                Log.e(this.f3182a, e2.toString());
            }
        }
    }
}
