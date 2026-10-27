package com.spotify.concurrency.asyncimpl;

import com.spotify.concurrency.async.NativeTimerManagerThread;
import com.spotify.concurrency.async.Scheduler;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class NativeTimerManagerThreadImpl implements NativeTimerManagerThread, Scheduler {
    public static final Companion Companion = new Companion(null);
    private final boolean isCurrentThread;
    private long nThis;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativeTimerManagerThreadImpl create(String str) {
            return NativeTimerManagerThreadImpl.create(str);
        }
    }

    private NativeTimerManagerThreadImpl() {
    }

    public static final native NativeTimerManagerThreadImpl create(String str);

    @Override // com.spotify.concurrency.async.NativeTimerManagerThread
    public native void destroy();

    @Override // com.spotify.concurrency.async.NativeTimerManagerThread
    public long getNThis() {
        return this.nThis;
    }

    @Override // com.spotify.concurrency.async.Scheduler
    public native boolean isCurrentThread();

    @Override // com.spotify.concurrency.async.Scheduler
    public native void kill(int i);

    @Override // com.spotify.concurrency.async.Scheduler
    public native void post(Runnable runnable);

    @Override // com.spotify.concurrency.async.Scheduler
    public void runBlocking(final Runnable runnable) {
        v03.j(runnable, "callback");
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        if (isCurrentThread()) {
            throw new RuntimeException("Not allowed to run blocking task on core thread from the core thread.");
        }
        final Exception[] excArr = new Exception[1];
        post(new Runnable() { // from class: com.spotify.concurrency.asyncimpl.NativeTimerManagerThreadImpl.runBlocking.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    runnable.run();
                } catch (Exception e) {
                    excArr[0] = e;
                }
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e2) {
                    throw new IllegalStateException("Interrupted exception awaiting barrier (on core thread)", e2);
                } catch (BrokenBarrierException e3) {
                    throw new IllegalStateException("Broken barrier exception awaiting barrier (on core thread)", e3);
                }
            }
        });
        try {
            cyclicBarrier.await();
            if (excArr[0] != null) {
                throw new IllegalStateException("Callback to NativeTimerManagerThreadImpl.run(...) threw", excArr[0]);
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException("Interrupted exception awaiting barrier", e);
        } catch (BrokenBarrierException e2) {
            throw new IllegalStateException("Broken barrier exception awaiting barrier", e2);
        }
    }

    public final native void start();

    public final native void stop();
}
