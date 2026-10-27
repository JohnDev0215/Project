package com.sun.xml.bind.v2.model.annotation;

import java.lang.annotation.Annotation;
import javax.xml.bind.annotation.XmlSchemaType;

/* JADX INFO: loaded from: classes3.dex */
final class XmlSchemaTypeQuick extends Quick implements XmlSchemaType {
    private final XmlSchemaType core;

    public XmlSchemaTypeQuick(Locatable locatable, XmlSchemaType xmlSchemaType) {
        super(locatable);
        this.core = xmlSchemaType;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Annotation getAnnotation() {
        return this.core;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Quick newInstance(Locatable locatable, Annotation annotation) {
        return new XmlSchemaTypeQuick(locatable, (XmlSchemaType) annotation);
    }

    @Override // java.lang.annotation.Annotation
    public Class<XmlSchemaType> annotationType() {
        return XmlSchemaType.class;
    }

    @Override // javax.xml.bind.annotation.XmlSchemaType
    public String name() {
        return this.core.name();
    }

    @Override // javax.xml.bind.annotation.XmlSchemaType
    public Class type() {
        return this.core.type();
    }

    @Override // javax.xml.bind.annotation.XmlSchemaType
    public String namespace() {
        return this.core.namespace();
    }
}
