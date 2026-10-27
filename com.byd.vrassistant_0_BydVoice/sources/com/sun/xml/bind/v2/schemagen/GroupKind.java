package com.sun.xml.bind.v2.schemagen;

import com.sun.xml.bind.v2.schemagen.xmlschema.ContentModelContainer;
import com.sun.xml.bind.v2.schemagen.xmlschema.Particle;

/* JADX INFO: loaded from: classes3.dex */
enum GroupKind {
    ALL("all"),
    SEQUENCE("sequence"),
    CHOICE("choice");

    private final String name;

    GroupKind(String str) {
        this.name = str;
    }

    Particle write(ContentModelContainer contentModelContainer) {
        return (Particle) contentModelContainer._element(this.name, Particle.class);
    }
}
