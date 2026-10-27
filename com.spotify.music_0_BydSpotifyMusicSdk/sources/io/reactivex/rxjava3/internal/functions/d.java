package io.reactivex.rxjava3.internal.functions;

import io.reactivex.rxjava3.functions.o;

/* JADX INFO: loaded from: classes.dex */
public final class d implements o {
    public final /* synthetic */ int c;

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        switch (this.c) {
            case 0:
                return obj;
            default:
                return System.getProperty((String) obj);
        }
    }

    public final String toString() {
        switch (this.c) {
            case 0:
                return "IdentityFunction";
            default:
                return super.toString();
        }
    }
}
