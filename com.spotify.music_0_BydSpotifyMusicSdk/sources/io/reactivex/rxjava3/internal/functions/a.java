package io.reactivex.rxjava3.internal.functions;

import io.reactivex.rxjava3.functions.q;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class a implements q {
    public final int c;

    public a(int i) {
        this.c = i;
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return new ArrayList(this.c);
    }
}
