package com.sun.xml.bind.v2.model.annotation;

import com.sun.xml.bind.v2.model.nav.Navigator;
import com.sun.xml.bind.v2.runtime.Location;

/* JADX INFO: loaded from: classes3.dex */
public class MethodLocatable<M> implements Locatable {
    private final M method;
    private final Navigator<?, ?, ?, M> nav;
    private final Locatable upstream;

    public MethodLocatable(Locatable locatable, M m, Navigator<?, ?, ?, M> navigator) {
        this.upstream = locatable;
        this.method = m;
        this.nav = navigator;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Locatable getUpstream() {
        return this.upstream;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Location getLocation() {
        return this.nav.getMethodLocation(this.method);
    }
}
