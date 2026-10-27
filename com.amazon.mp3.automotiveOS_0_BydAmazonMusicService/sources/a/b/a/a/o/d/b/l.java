package a.b.a.a.o.d.b;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f123a = new a();
    public final b b = b.b.a();
    public final long c;
    public final long d;
    public long e;
    public long f;
    public long g;
    public boolean h;
    public long i;
    public long j;
    public long k;

    public static final class a {
    }

    public static final class b implements Choreographer.FrameCallback, Handler.Callback {
        public volatile long c;
        public final Handler d;
        public final HandlerThread e;
        public Choreographer f;
        public static final a b = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f124a = new b();

        public static final class a {
            public final b a() {
                return b.f124a;
            }
        }

        public b() {
            HandlerThread handlerThread = new HandlerThread("ChoreographerOwner:Handler");
            this.e = handlerThread;
            handlerThread.start();
            Handler handler = new Handler(handlerThread.getLooper(), this);
            this.d = handler;
            handler.sendEmptyMessage(0);
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            this.c = j;
            Choreographer choreographer = this.f;
            if (choreographer == null) {
                Intrinsics.throwNpe();
            }
            choreographer.postFrameCallbackDelayed(this, 500L);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                this.f = Choreographer.getInstance();
                return true;
            }
            if (i == 1) {
                Choreographer choreographer = this.f;
                if (choreographer == null) {
                    Intrinsics.throwNpe();
                }
                choreographer.postFrameCallback(this);
                return true;
            }
            if (i != 2) {
                return false;
            }
            Choreographer choreographer2 = this.f;
            if (choreographer2 == null) {
                Intrinsics.throwNpe();
            }
            choreographer2.removeFrameCallback(this);
            return true;
        }
    }

    public l(float f) {
        long nanos = (long) (TimeUnit.SECONDS.toNanos(1L) / ((double) f));
        this.c = nanos;
        this.d = (nanos * 80) / ((long) 100);
    }

    public final void a() {
        this.h = false;
        this.b.d.sendEmptyMessage(1);
    }

    public final boolean a(long j, long j2) {
        return Math.abs((j2 - this.i) - (j - this.j)) > 20000000;
    }
}
