package com.amazon.music.pager;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public final class Pager<T> {
    private final ExecutorService executorService;
    private final PagerIterator<T> pagerIterator;
    private PagerObserver<T> pagerObserver;
    private Replay<T> replay;

    public static final <T> Builder<T> create(PagerIterator<T> pagerIterator) {
        return new Builder<>(pagerIterator);
    }

    public static final class Builder<T> {
        private final PagerIterator<T> pagerIterator;
        private boolean replay;

        private Builder(PagerIterator<T> pagerIterator) {
            this.replay = false;
            this.pagerIterator = pagerIterator;
        }

        public Builder<T> replay() {
            this.replay = true;
            return this;
        }

        public Pager<T> build() {
            return new Pager<>(this.pagerIterator, this.replay);
        }
    }

    public void next() {
        this.executorService.submit(new Runnable() { // from class: com.amazon.music.pager.Pager.1
            @Override // java.lang.Runnable
            public void run() {
                if (Pager.this.pagerIterator.hasNext()) {
                    try {
                        Pager.this.onNext(Pager.this.pagerIterator.next());
                        return;
                    } catch (ExecutionException e) {
                        Pager.this.onError(e.getCause());
                        return;
                    } catch (Exception e2) {
                        Pager.this.onError(e2);
                        return;
                    }
                }
                Pager.this.onCompleted();
            }
        });
    }

    public synchronized void subscribe(final PagerObserver<T> pagerObserver) {
        Validate.notNull(pagerObserver, "pagerObserver can't be null", new Object[0]);
        this.pagerObserver = pagerObserver;
        this.executorService.submit(new Runnable() { // from class: com.amazon.music.pager.Pager.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (this) {
                    Pager.this.replay.replay(pagerObserver);
                }
            }
        });
    }

    public synchronized void unsubscribe() {
        this.pagerObserver = null;
    }

    private Pager(PagerIterator<T> pagerIterator, boolean z) {
        Validate.notNull(pagerIterator, "pagerIterator can't be null", new Object[0]);
        this.executorService = Executors.newSingleThreadExecutor();
        this.pagerIterator = pagerIterator;
        this.replay = z ? new DefaultReplay<>() : new NoReplay<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onError(Throwable th) {
        PagerObserver<T> pagerObserver = this.pagerObserver;
        if (pagerObserver != null) {
            pagerObserver.onError(th);
        } else {
            this.replay.setError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onNext(T t) {
        PagerObserver<T> pagerObserver = this.pagerObserver;
        if (pagerObserver != null) {
            pagerObserver.onNext(t);
        } else {
            this.replay.setNext(t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onCompleted() {
        PagerObserver<T> pagerObserver = this.pagerObserver;
        if (pagerObserver != null) {
            pagerObserver.onCompleted();
        } else {
            this.replay.setComplete();
        }
    }
}
