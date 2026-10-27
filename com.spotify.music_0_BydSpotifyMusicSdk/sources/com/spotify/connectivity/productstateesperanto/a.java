package com.spotify.connectivity.productstateesperanto;

import com.google.common.base.Optional;
import io.reactivex.rxjava3.functions.c;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements c {
    @Override // io.reactivex.rxjava3.functions.c
    public final Object k(Object obj, Object obj2) {
        return ProductStateAccumulator.toAccumulatedMap((Map) obj, (Optional) obj2);
    }
}
