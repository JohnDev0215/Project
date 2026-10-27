package com.spotify.cosmos.cosmonautatoms;

import com.spotify.cosmos.cosmonaut.Converter;
import com.spotify.cosmos.cosmonaut.Cosmonaut;
import com.spotify.cosmos.rxrouter.RxRouter;
import java.util.List;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class CosmonautImpl implements Cosmonaut {
    private final CosmonautHandler cosmonautHandler;
    private final List<Converter.Factory> factories;
    private final RxRouter rxRouter;

    /* JADX WARN: Multi-variable type inference failed */
    public CosmonautImpl(RxRouter rxRouter, List<? extends Converter.Factory> list) {
        v03.j(rxRouter, "rxRouter");
        v03.j(list, "factories");
        this.rxRouter = rxRouter;
        this.factories = list;
        this.cosmonautHandler = new CosmonautHandler(list);
    }

    @Override // com.spotify.cosmos.cosmonaut.Cosmonaut
    public <T> T createCosmosService(Class<T> cls) {
        v03.j(cls, "serviceClazz");
        return (T) this.cosmonautHandler.create(cls, this.rxRouter);
    }

    public final List<Converter.Factory> getFactories() {
        return this.factories;
    }
}
