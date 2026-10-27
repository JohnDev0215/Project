package com.amazon.music.pager;

/* JADX INFO: loaded from: classes4.dex */
final class NoReplay<T> implements Replay<T> {
    @Override // com.amazon.music.pager.Replay
    public void replay(PagerObserver pagerObserver) {
    }

    @Override // com.amazon.music.pager.Replay
    public void setComplete() {
    }

    @Override // com.amazon.music.pager.Replay
    public void setError(Throwable th) {
    }

    @Override // com.amazon.music.pager.Replay
    public void setNext(T t) {
    }

    NoReplay() {
    }
}
