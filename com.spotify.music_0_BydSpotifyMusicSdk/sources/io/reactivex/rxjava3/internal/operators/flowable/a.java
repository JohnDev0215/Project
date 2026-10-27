package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends Flowable {
    public final Flowable e;

    public a(Flowable flowable) {
        Objects.requireNonNull(flowable, "source is null");
        this.e = flowable;
    }
}
