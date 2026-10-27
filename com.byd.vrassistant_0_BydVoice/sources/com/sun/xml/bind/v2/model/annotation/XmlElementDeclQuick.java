package com.sun.xml.bind.v2.model.annotation;

import java.lang.annotation.Annotation;
import javax.xml.bind.annotation.XmlElementDecl;

/* JADX INFO: loaded from: classes3.dex */
final class XmlElementDeclQuick extends Quick implements XmlElementDecl {
    private final XmlElementDecl core;

    public XmlElementDeclQuick(Locatable locatable, XmlElementDecl xmlElementDecl) {
        super(locatable);
        this.core = xmlElementDecl;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Annotation getAnnotation() {
        return this.core;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Quick newInstance(Locatable locatable, Annotation annotation) {
        return new XmlElementDeclQuick(locatable, (XmlElementDecl) annotation);
    }

    @Override // java.lang.annotation.Annotation
    public Class<XmlElementDecl> annotationType() {
        return XmlElementDecl.class;
    }

    @Override // javax.xml.bind.annotation.XmlElementDecl
    public String name() {
        return this.core.name();
    }

    @Override // javax.xml.bind.annotation.XmlElementDecl
    public Class scope() {
        return this.core.scope();
    }

    @Override // javax.xml.bind.annotation.XmlElementDecl
    public String namespace() {
        return this.core.namespace();
    }

    @Override // javax.xml.bind.annotation.XmlElementDecl
    public String defaultValue() {
        return this.core.defaultValue();
    }

    @Override // javax.xml.bind.annotation.XmlElementDecl
    public String substitutionHeadNamespace() {
        return this.core.substitutionHeadNamespace();
    }

    @Override // javax.xml.bind.annotation.XmlElementDecl
    public String substitutionHeadName() {
        return this.core.substitutionHeadName();
    }
}
