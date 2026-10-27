package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes7.dex */
public final class CompletableTimeout extends Completable {
    final CompletableSource other;
    final Scheduler scheduler;
    final CompletableSource source;
    final long timeout;
    final TimeUnit unit;

    public CompletableTimeout(CompletableSource source, long timeout, TimeUnit unit, Scheduler scheduler, CompletableSource other) {
        this.source = source;
        this.timeout = timeout;
        this.unit = unit;
        this.scheduler = scheduler;
        this.other = other;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public void subscribeActual(final CompletableObserver observer) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        observer.onSubscribe(compositeDisposable);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        compositeDisposable.add(this.scheduler.scheduleDirect(new DisposeTask(atomicBoolean, compositeDisposable, observer), this.timeout, this.unit));
        this.source.subscribe(new TimeOutObserver(compositeDisposable, atomicBoolean, observer));
    }

    static final class TimeOutObserver implements CompletableObserver {
        private final CompletableObserver downstream;
        private final AtomicBoolean once;
        private final CompositeDisposable set;

        TimeOutObserver(CompositeDisposable set, AtomicBoolean once, CompletableObserver observer) {
            this.set = set;
            this.once = once;
            this.downstream = observer;
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable d) {
            this.set.add(d);
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable e) {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.downstream.onError(e);
            } else {
                RxJavaPlugins.onError(e);
            }
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver, io.reactivex.rxjava3.core.MaybeObserver
        public void onComplete() {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.downstream.onComplete();
            }
        }
    }

    final class DisposeTask implements Runnable {
        final CompletableObserver downstream;
        private final AtomicBoolean once;
        final CompositeDisposable set;

        DisposeTask(AtomicBoolean once, CompositeDisposable set, CompletableObserver observer) {
            this.once = once;
            this.set = set;
            this.downstream = observer;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.once.compareAndSet(false, true)) {
                this.set.clear();
                CompletableSource completableSource = CompletableTimeout.this.other;
                if (completableSource == null) {
                    CompletableObserver completableObserver = this.downstream;
                    CompletableTimeout completableTimeout = CompletableTimeout.this;
                    completableObserver.onError(new TimeoutException(ExceptionHelper.timeoutMessage(completableTimeout.timeout, completableTimeout.unit)));
                    return;
                }
                completableSource.subscribe(new DisposeObserver());
            }
        }

        final class DisposeObserver implements CompletableObserver {
            DisposeObserver() {
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable d) {
                DisposeTask.this.set.add(d);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable e) {
                DisposeTask.this.set.dispose();
                DisposeTask.this.downstream.onError(e);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver, io.reactivex.rxjava3.core.MaybeObserver
            public void onComplete() {
                DisposeTask.this.set.dispose();
                DisposeTask.this.downstream.onComplete();
            }
        }
    }
}
