package com.spotify.connectivity.productstateesperanto;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import java.util.HashMap;
import java.util.Map;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
class ProductStateAccumulator implements ObservableTransformer<Optional<Map<String, String>>, Map<String, String>> {
    public static Map<String, String> toAccumulatedMap(Map<String, String> map, Optional<Map<String, String>> optional) {
        int iCeil;
        if (!optional.d()) {
            return ImmutableMap.d();
        }
        int size = ((Map) optional.c()).size() + map.size();
        if (size < 3) {
            he1.e(size, "expectedSize");
            iCeil = size + 1;
        } else {
            iCeil = size < 1073741824 ? (int) Math.ceil(((double) size) / 0.75d) : Integer.MAX_VALUE;
        }
        HashMap map2 = new HashMap(iCeil);
        map2.putAll(map);
        map2.putAll((Map) optional.c());
        return ImmutableMap.b(map2);
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<Map<String, String>> apply(Observable<Optional<Map<String, String>>> observable) {
        return observable.scan(ImmutableMap.d(), new a()).skip(1L);
    }
}
