package io.reactivex.rxjava3.internal.functions;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.q;

/* JADX INFO: loaded from: classes.dex */
public final class g implements q {
    public final /* synthetic */ int c;

    public /* synthetic */ g(int i) {
        this.c = i;
    }

    public final Scheduler a() {
        switch (this.c) {
            case 1:
                return io.reactivex.rxjava3.schedulers.a.a;
            case 2:
                return io.reactivex.rxjava3.schedulers.b.a;
            case 3:
                return io.reactivex.rxjava3.schedulers.c.a;
            default:
                return io.reactivex.rxjava3.schedulers.d.a;
        }
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        switch (this.c) {
        }
        return a();
    }
}
