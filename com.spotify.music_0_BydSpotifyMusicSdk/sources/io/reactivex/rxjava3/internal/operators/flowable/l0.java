package io.reactivex.rxjava3.internal.operators.flowable;

/* JADX INFO: loaded from: classes.dex */
public final class l0 implements io.reactivex.rxjava3.functions.q {
    public final int c = 1;
    public final boolean e = false;

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return new FlowableReplay$SizeBoundReplayBuffer(this.c, this.e);
    }
}
