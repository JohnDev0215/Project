package com.spotify.cosmos.cosmonautatoms;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.cosmos.cosmonaut.Converter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p.lu;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class RequestTransformers {
    private final List<Converter<?, byte[]>> converters;
    private final List<Converter.Factory> factories;

    /* JADX WARN: Multi-variable type inference failed */
    public RequestTransformers(List<? extends Converter.Factory> list) {
        v03.j(list, "factories");
        this.factories = list;
        ArrayList arrayList = new ArrayList(lu.W(list));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Converter.Factory) it.next()).createRequestConverter());
        }
        this.converters = arrayList;
    }

    public final Converter<?, byte[]> find(Type type) {
        v03.j(type, RxProductState.Keys.KEY_TYPE);
        for (Converter<?, byte[]> converter : this.converters) {
            if (converter.canHandle(type)) {
                return converter;
            }
        }
        throw new IllegalArgumentException(type + " is not supported by RequestTransformers");
    }

    public final List<Converter.Factory> getFactories() {
        return this.factories;
    }
}
