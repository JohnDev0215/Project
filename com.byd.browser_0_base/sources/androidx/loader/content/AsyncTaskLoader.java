package androidx.loader.content;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.SystemClock;
import defpackage.gi1;
import defpackage.gq2;
import defpackage.us1;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public abstract class AsyncTaskLoader<D> extends Loader<D> {
    public Executor f;
    public volatile AsyncTaskLoader<D>.a g;
    public volatile AsyncTaskLoader<D>.a h;

    public final class a extends gi1<D> implements Runnable {
        public a() {
        }

        @Override // defpackage.gi1
        public final D a() {
            try {
                return (D) AsyncTaskLoader.this.e();
            } catch (us1 e) {
                if (this.g.get()) {
                    return null;
                }
                throw e;
            }
        }

        @Override // defpackage.gi1
        public final void b(D d) {
            AsyncTaskLoader asyncTaskLoader = AsyncTaskLoader.this;
            asyncTaskLoader.g(d);
            if (asyncTaskLoader.h == this) {
                if (asyncTaskLoader.e) {
                    if (asyncTaskLoader.b) {
                        asyncTaskLoader.b();
                    } else {
                        asyncTaskLoader.getClass();
                    }
                }
                SystemClock.uptimeMillis();
                asyncTaskLoader.h = null;
                asyncTaskLoader.d();
            }
        }

        @Override // defpackage.gi1
        public final void c(D d) {
            AsyncTaskLoader asyncTaskLoader = AsyncTaskLoader.this;
            if (asyncTaskLoader.g == this) {
                if (asyncTaskLoader.c) {
                    asyncTaskLoader.g(d);
                    return;
                }
                asyncTaskLoader.e = false;
                SystemClock.uptimeMillis();
                asyncTaskLoader.g = null;
                asyncTaskLoader.a(d);
                return;
            }
            asyncTaskLoader.g(d);
            if (asyncTaskLoader.h == this) {
                if (asyncTaskLoader.e) {
                    if (asyncTaskLoader.b) {
                        asyncTaskLoader.b();
                    } else {
                        asyncTaskLoader.getClass();
                    }
                }
                SystemClock.uptimeMillis();
                asyncTaskLoader.h = null;
                asyncTaskLoader.d();
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            AsyncTaskLoader.this.d();
        }
    }

    public AsyncTaskLoader(Context context) {
        super(context);
    }

    @Override // androidx.loader.content.Loader
    public final void b() {
        f();
        this.g = new a();
        d();
    }

    public final void d() {
        if (this.h != null || this.g == null) {
            return;
        }
        this.g.getClass();
        if (this.f == null) {
            this.f = AsyncTask.THREAD_POOL_EXECUTOR;
        }
        AsyncTaskLoader<D>.a aVar = this.g;
        Executor executor = this.f;
        if (aVar.f == 1) {
            aVar.f = 2;
            executor.execute(aVar.e);
            return;
        }
        int iM = gq2.m(aVar.f);
        if (iM == 1) {
            throw new IllegalStateException("Cannot execute task: the task is already running.");
        }
        if (iM == 2) {
            throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
        }
        throw new IllegalStateException("We should never reach this state");
    }

    public abstract Cursor e();

    public final boolean f() {
        if (this.g == null) {
            return false;
        }
        boolean z = this.b;
        if (!z && z) {
            b();
        }
        if (this.h != null) {
            this.g.getClass();
            this.g = null;
            return false;
        }
        this.g.getClass();
        AsyncTaskLoader<D>.a aVar = this.g;
        aVar.g.set(true);
        boolean zCancel = aVar.e.cancel(false);
        if (zCancel) {
            this.h = this.g;
            c();
        }
        this.g = null;
        return zCancel;
    }

    public void c() {
    }

    public void g(D d) {
    }
}
