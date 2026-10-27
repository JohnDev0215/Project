package com.sun.xml.bind.v2.model.annotation;

import com.sun.xml.bind.v2.model.nav.Navigator;
import com.sun.xml.bind.v2.runtime.Location;

/* JADX INFO: loaded from: classes3.dex */
public class FieldLocatable<F> implements Locatable {
    private final F field;
    private final Navigator<?, ?, F, ?> nav;
    private final Locatable upstream;

    public FieldLocatable(Locatable locatable, F f, Navigator<?, ?, F, ?> navigator) {
        this.upstream = locatable;
        this.field = f;
        this.nav = navigator;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Locatable getUpstream() {
        return this.upstream;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Location getLocation() {
        return this.nav.getFieldLocation(this.field);
    }
}
