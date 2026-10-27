package com.sun.xml.bind.v2.model.annotation;

import java.lang.annotation.Annotation;
import javax.xml.bind.annotation.XmlAttribute;

/* JADX INFO: loaded from: classes3.dex */
final class XmlAttributeQuick extends Quick implements XmlAttribute {
    private final XmlAttribute core;

    public XmlAttributeQuick(Locatable locatable, XmlAttribute xmlAttribute) {
        super(locatable);
        this.core = xmlAttribute;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Annotation getAnnotation() {
        return this.core;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Quick newInstance(Locatable locatable, Annotation annotation) {
        return new XmlAttributeQuick(locatable, (XmlAttribute) annotation);
    }

    @Override // java.lang.annotation.Annotation
    public Class<XmlAttribute> annotationType() {
        return XmlAttribute.class;
    }

    @Override // javax.xml.bind.annotation.XmlAttribute
    public String name() {
        return this.core.name();
    }

    @Override // javax.xml.bind.annotation.XmlAttribute
    public String namespace() {
        return this.core.namespace();
    }

    @Override // javax.xml.bind.annotation.XmlAttribute
    public boolean required() {
        return this.core.required();
    }
}
