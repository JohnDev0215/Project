package com.sun.xml.bind.v2.model.annotation;

import java.lang.annotation.Annotation;
import javax.xml.bind.annotation.XmlRootElement;

/* JADX INFO: loaded from: classes3.dex */
final class XmlRootElementQuick extends Quick implements XmlRootElement {
    private final XmlRootElement core;

    public XmlRootElementQuick(Locatable locatable, XmlRootElement xmlRootElement) {
        super(locatable);
        this.core = xmlRootElement;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Annotation getAnnotation() {
        return this.core;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Quick newInstance(Locatable locatable, Annotation annotation) {
        return new XmlRootElementQuick(locatable, (XmlRootElement) annotation);
    }

    @Override // java.lang.annotation.Annotation
    public Class<XmlRootElement> annotationType() {
        return XmlRootElement.class;
    }

    @Override // javax.xml.bind.annotation.XmlRootElement
    public String name() {
        return this.core.name();
    }

    @Override // javax.xml.bind.annotation.XmlRootElement
    public String namespace() {
        return this.core.namespace();
    }
}
