package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends Maybe {
    public final MaybeSource c;

    public a(MaybeSource maybeSource) {
        this.c = maybeSource;
    }
}
