package com.sun.xml.bind.v2.model.annotation;

import java.lang.annotation.Annotation;
import javax.xml.bind.annotation.XmlType;

/* JADX INFO: loaded from: classes3.dex */
final class XmlTypeQuick extends Quick implements XmlType {
    private final XmlType core;

    public XmlTypeQuick(Locatable locatable, XmlType xmlType) {
        super(locatable);
        this.core = xmlType;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Annotation getAnnotation() {
        return this.core;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Quick newInstance(Locatable locatable, Annotation annotation) {
        return new XmlTypeQuick(locatable, (XmlType) annotation);
    }

    @Override // java.lang.annotation.Annotation
    public Class<XmlType> annotationType() {
        return XmlType.class;
    }

    @Override // javax.xml.bind.annotation.XmlType
    public String name() {
        return this.core.name();
    }

    @Override // javax.xml.bind.annotation.XmlType
    public String namespace() {
        return this.core.namespace();
    }

    @Override // javax.xml.bind.annotation.XmlType
    public String[] propOrder() {
        return this.core.propOrder();
    }

    @Override // javax.xml.bind.annotation.XmlType
    public Class factoryClass() {
        return this.core.factoryClass();
    }

    @Override // javax.xml.bind.annotation.XmlType
    public String factoryMethod() {
        return this.core.factoryMethod();
    }
}
