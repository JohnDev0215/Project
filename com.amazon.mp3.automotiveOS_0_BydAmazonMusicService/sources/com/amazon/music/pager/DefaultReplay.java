package com.amazon.music.pager;

/* JADX INFO: loaded from: classes4.dex */
final class DefaultReplay<T> implements Replay<T> {
    private boolean complete;
    private Throwable error;
    private T next;

    DefaultReplay() {
    }

    @Override // com.amazon.music.pager.Replay
    public synchronized void replay(PagerObserver<T> pagerObserver) {
        Throwable th = this.error;
        if (th != null) {
            pagerObserver.onError(th);
        } else {
            T t = this.next;
            if (t != null) {
                pagerObserver.onNext(t);
            } else if (this.complete) {
                pagerObserver.onCompleted();
            }
        }
    }

    @Override // com.amazon.music.pager.Replay
    public synchronized void setError(Throwable th) {
        setAll(th, null, false);
    }

    @Override // com.amazon.music.pager.Replay
    public synchronized void setNext(T t) {
        setAll(null, t, false);
    }

    @Override // com.amazon.music.pager.Replay
    public synchronized void setComplete() {
        setAll(null, null, true);
    }

    private synchronized void setAll(Throwable th, T t, boolean z) {
        this.error = th;
        this.next = t;
        this.complete = z;
    }
}
