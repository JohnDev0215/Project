package io.reactivex.rxjava3.internal.util;

import java.util.List;
import spi.o1;

/* JADX INFO: loaded from: classes2.dex */
public enum ListAddBiConsumer implements o1<List, Object, List> {
    INSTANCE;

    public static <T> o1<List<T>, T, List<T>> instance() {
        return INSTANCE;
    }

    public List apply(List list, Object obj) {
        list.add(obj);
        return list;
    }
}
