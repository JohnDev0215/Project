package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.ContextFactory;
import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.annotation.MethodLocatable;
import com.sun.xml.bind.v2.model.core.RegistryInfo;
import com.sun.xml.bind.v2.model.core.TypeInfo;
import com.sun.xml.bind.v2.model.nav.Navigator;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import com.sun.xml.bind.v2.runtime.Location;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlElementDecl;

/* JADX INFO: loaded from: classes3.dex */
final class RegistryInfoImpl<T, C, F, M> implements Locatable, RegistryInfo<T, C> {
    private final Navigator<T, C, F, M> nav;
    private final Set<TypeInfo<T, C>> references = new LinkedHashSet();
    final C registryClass;
    private final Locatable upstream;

    RegistryInfoImpl(ModelBuilder<T, C, F, M> modelBuilder, Locatable locatable, C c) {
        Navigator<T, C, F, M> navigator = modelBuilder.nav;
        this.nav = navigator;
        this.registryClass = c;
        this.upstream = locatable;
        modelBuilder.registries.put(getPackageName(), this);
        if (navigator.getDeclaredField(c, ContextFactory.USE_JAXB_PROPERTIES) != null) {
            modelBuilder.reportError(new IllegalAnnotationException(Messages.MISSING_JAXB_PROPERTIES.format(getPackageName()), this));
            return;
        }
        for (M m : navigator.getDeclaredMethods(c)) {
            if (((XmlElementDecl) modelBuilder.reader.getMethodAnnotation(XmlElementDecl.class, m, this)) == null) {
                if (this.nav.getMethodName(m).startsWith("create")) {
                    this.references.add(modelBuilder.getTypeInfo(this.nav.getReturnType(m), new MethodLocatable(this, m, this.nav)));
                }
            } else {
                try {
                    ElementInfoImpl<T, C, F, M> elementInfoImplCreateElementInfo = modelBuilder.createElementInfo(this, m);
                    modelBuilder.typeInfoSet.add(elementInfoImplCreateElementInfo, modelBuilder);
                    this.references.add(elementInfoImplCreateElementInfo);
                } catch (IllegalAnnotationException e) {
                    modelBuilder.reportError(e);
                }
            }
        }
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Locatable getUpstream() {
        return this.upstream;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Location getLocation() {
        return this.nav.getClassLocation(this.registryClass);
    }

    @Override // com.sun.xml.bind.v2.model.core.RegistryInfo
    public Set<TypeInfo<T, C>> getReferences() {
        return this.references;
    }

    public String getPackageName() {
        return this.nav.getPackageName(this.registryClass);
    }

    @Override // com.sun.xml.bind.v2.model.core.RegistryInfo
    public C getClazz() {
        return this.registryClass;
    }
}
