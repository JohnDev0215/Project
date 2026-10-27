package io.reactivex.rxjava3.internal.operators.observable;

/* JADX INFO: loaded from: classes.dex */
public final class r3 implements p3 {
    public final int a;
    public final boolean b;

    public r3(int i, boolean z) {
        this.a = i;
        this.b = z;
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.p3
    public final q3 call() {
        final int i = this.a;
        final boolean z = this.b;
        return new ObservableReplay$BoundedReplayBuffer<T>(i, z) { // from class: io.reactivex.rxjava3.internal.operators.observable.ObservableReplay$SizeBoundReplayBuffer
            private static final long serialVersionUID = -5898283885385201806L;
            final int limit;

            {
                super(z);
                this.limit = i;
            }

            @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay$BoundedReplayBuffer
            public final void i() {
                if (this.size > this.limit) {
                    this.size--;
                    g(get().get());
                }
            }
        };
    }
}
