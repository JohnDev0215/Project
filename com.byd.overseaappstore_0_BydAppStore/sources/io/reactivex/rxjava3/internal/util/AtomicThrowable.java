package io.reactivex.rxjava3.internal.util;

import io.reactivex.rxjava3.exceptions.CompositeException;
import java.util.concurrent.atomic.AtomicReference;
import spi.bk;
import spi.c8;
import spi.di;
import spi.mp;
import spi.os;
import spi.s3;
import spi.v8;
import spi.vn;

/* JADX INFO: loaded from: classes2.dex */
public final class AtomicThrowable extends AtomicReference<Throwable> {
    private static final long serialVersionUID = 3949248817947090603L;

    public boolean isTerminated() {
        return get() == v8.a;
    }

    public Throwable terminate() {
        v8.a aVar = v8.a;
        Throwable th = get();
        v8.a aVar2 = v8.a;
        return th != aVar2 ? getAndSet(aVar2) : th;
    }

    public boolean tryAddThrowable(Throwable th) {
        Throwable th2;
        v8.a aVar = v8.a;
        do {
            th2 = get();
            if (th2 == v8.a) {
                return false;
            }
        } while (!compareAndSet(th2, th2 == null ? th : new CompositeException(th2, th)));
        return true;
    }

    public boolean tryAddThrowableOrReport(Throwable th) {
        if (tryAddThrowable(th)) {
            return true;
        }
        vn.a(th);
        return false;
    }

    public void tryTerminateAndReport() {
        Throwable thTerminate = terminate();
        if (thTerminate == null || thTerminate == v8.a) {
            return;
        }
        vn.a(thTerminate);
    }

    public void tryTerminateConsumer(bk<?> bkVar) {
        Throwable thTerminate = terminate();
        if (thTerminate == null) {
            bkVar.onComplete();
        } else if (thTerminate != v8.a) {
            bkVar.onError(thTerminate);
        }
    }

    public void tryTerminateConsumer(c8<?> c8Var) {
        Throwable thTerminate = terminate();
        if (thTerminate == null) {
            c8Var.onComplete();
        } else if (thTerminate != v8.a) {
            c8Var.a();
        }
    }

    public void tryTerminateConsumer(di<?> diVar) {
        Throwable thTerminate = terminate();
        if (thTerminate == null) {
            diVar.onComplete();
        } else if (thTerminate != v8.a) {
            diVar.onError(thTerminate);
        }
    }

    public void tryTerminateConsumer(mp<?> mpVar) {
        Throwable thTerminate = terminate();
        if (thTerminate == null || thTerminate == v8.a) {
            return;
        }
        mpVar.onError(thTerminate);
    }

    public void tryTerminateConsumer(os<?> osVar) {
        Throwable thTerminate = terminate();
        if (thTerminate == null) {
            osVar.onComplete();
        } else if (thTerminate != v8.a) {
            osVar.onError(thTerminate);
        }
    }

    public void tryTerminateConsumer(s3 s3Var) {
        Throwable thTerminate = terminate();
        if (thTerminate == null) {
            s3Var.onComplete();
        } else if (thTerminate != v8.a) {
            s3Var.onError(thTerminate);
        }
    }
}
