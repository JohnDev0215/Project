package com.sun.xml.bind.v2.model.annotation;

import com.sun.xml.bind.v2.model.nav.Navigator;
import com.sun.xml.bind.v2.runtime.Location;

/* JADX INFO: loaded from: classes3.dex */
public class ClassLocatable<C> implements Locatable {
    private final C clazz;
    private final Navigator<?, C, ?, ?> nav;
    private final Locatable upstream;

    public ClassLocatable(Locatable locatable, C c, Navigator<?, C, ?, ?> navigator) {
        this.upstream = locatable;
        this.clazz = c;
        this.nav = navigator;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Locatable getUpstream() {
        return this.upstream;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Location getLocation() {
        return this.nav.getClassLocation(this.clazz);
    }
}
