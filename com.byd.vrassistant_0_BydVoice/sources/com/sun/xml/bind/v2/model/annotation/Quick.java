package com.sun.xml.bind.v2.model.annotation;

import com.sun.xml.bind.v2.runtime.Location;
import java.lang.annotation.Annotation;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Quick implements Annotation, Locatable, Location {
    private final Locatable upstream;

    protected abstract Annotation getAnnotation();

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public final Location getLocation() {
        return this;
    }

    protected abstract Quick newInstance(Locatable locatable, Annotation annotation);

    protected Quick(Locatable locatable) {
        this.upstream = locatable;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public final Locatable getUpstream() {
        return this.upstream;
    }

    @Override // java.lang.annotation.Annotation, com.sun.xml.bind.v2.runtime.Location
    public final String toString() {
        return getAnnotation().toString();
    }
}
