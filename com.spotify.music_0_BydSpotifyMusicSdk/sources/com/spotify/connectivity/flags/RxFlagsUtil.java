package com.spotify.connectivity.flags;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.operators.flowable.d0;
import io.reactivex.rxjava3.internal.operators.flowable.h;
import java.io.Serializable;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class RxFlagsUtil {
    public static final RxFlagsUtil INSTANCE = new RxFlagsUtil();

    private RxFlagsUtil() {
    }

    public final <T extends Serializable> Flowable<T> flag(Flowable<Flags> flowable, final Flag<T> flag) {
        v03.j(flowable, "flagsIn");
        v03.j(flag, "flag");
        return new h(new d0(flowable, new o() { // from class: com.spotify.connectivity.flags.RxFlagsUtil.flag.1
            /* JADX WARN: Incorrect return type in method signature: (Lcom/spotify/connectivity/flags/Flags;)TT; */
            @Override // io.reactivex.rxjava3.functions.o
            public final Serializable apply(Flags flags) {
                v03.j(flags, "flags");
                return flags.get(flag);
            }
        }, 3));
    }
}
