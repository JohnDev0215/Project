package com.sun.xml.bind.v2.model.annotation;

import java.lang.annotation.Annotation;
import javax.xml.bind.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
final class XmlElementQuick extends Quick implements XmlElement {
    private final XmlElement core;

    public XmlElementQuick(Locatable locatable, XmlElement xmlElement) {
        super(locatable);
        this.core = xmlElement;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Annotation getAnnotation() {
        return this.core;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Quick newInstance(Locatable locatable, Annotation annotation) {
        return new XmlElementQuick(locatable, (XmlElement) annotation);
    }

    @Override // java.lang.annotation.Annotation
    public Class<XmlElement> annotationType() {
        return XmlElement.class;
    }

    @Override // javax.xml.bind.annotation.XmlElement
    public String name() {
        return this.core.name();
    }

    @Override // javax.xml.bind.annotation.XmlElement
    public Class type() {
        return this.core.type();
    }

    @Override // javax.xml.bind.annotation.XmlElement
    public String namespace() {
        return this.core.namespace();
    }

    @Override // javax.xml.bind.annotation.XmlElement
    public String defaultValue() {
        return this.core.defaultValue();
    }

    @Override // javax.xml.bind.annotation.XmlElement
    public boolean required() {
        return this.core.required();
    }

    @Override // javax.xml.bind.annotation.XmlElement
    public boolean nillable() {
        return this.core.nillable();
    }
}
