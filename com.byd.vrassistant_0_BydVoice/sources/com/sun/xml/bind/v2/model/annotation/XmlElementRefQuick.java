package com.sun.xml.bind.v2.model.annotation;

import java.lang.annotation.Annotation;
import javax.xml.bind.annotation.XmlElementRef;

/* JADX INFO: loaded from: classes3.dex */
final class XmlElementRefQuick extends Quick implements XmlElementRef {
    private final XmlElementRef core;

    public XmlElementRefQuick(Locatable locatable, XmlElementRef xmlElementRef) {
        super(locatable);
        this.core = xmlElementRef;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Annotation getAnnotation() {
        return this.core;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Quick newInstance(Locatable locatable, Annotation annotation) {
        return new XmlElementRefQuick(locatable, (XmlElementRef) annotation);
    }

    @Override // java.lang.annotation.Annotation
    public Class<XmlElementRef> annotationType() {
        return XmlElementRef.class;
    }

    @Override // javax.xml.bind.annotation.XmlElementRef
    public String name() {
        return this.core.name();
    }

    @Override // javax.xml.bind.annotation.XmlElementRef
    public Class type() {
        return this.core.type();
    }

    @Override // javax.xml.bind.annotation.XmlElementRef
    public String namespace() {
        return this.core.namespace();
    }

    @Override // javax.xml.bind.annotation.XmlElementRef
    public boolean required() {
        return this.core.required();
    }
}
