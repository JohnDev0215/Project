package io.reactivex.rxjava3.internal.util;

import java.util.ArrayList;
import java.util.List;
import spi.qs;
import spi.uc;

/* JADX INFO: loaded from: classes2.dex */
public enum ArrayListSupplier implements qs<List<Object>>, uc<Object, List<Object>> {
    INSTANCE;

    public static <T, O> uc<O, List<T>> asFunction() {
        return INSTANCE;
    }

    public static <T> qs<List<T>> asSupplier() {
        return INSTANCE;
    }

    @Override // spi.uc
    public List<Object> apply(Object obj) {
        return new ArrayList();
    }

    public List<Object> get() {
        return new ArrayList();
    }
}
