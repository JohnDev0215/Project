package com.sun.xml.bind.v2.model.annotation;

import java.lang.annotation.Annotation;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;

/* JADX INFO: loaded from: classes3.dex */
final class XmlElementRefsQuick extends Quick implements XmlElementRefs {
    private final XmlElementRefs core;

    public XmlElementRefsQuick(Locatable locatable, XmlElementRefs xmlElementRefs) {
        super(locatable);
        this.core = xmlElementRefs;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Annotation getAnnotation() {
        return this.core;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Quick newInstance(Locatable locatable, Annotation annotation) {
        return new XmlElementRefsQuick(locatable, (XmlElementRefs) annotation);
    }

    @Override // java.lang.annotation.Annotation
    public Class<XmlElementRefs> annotationType() {
        return XmlElementRefs.class;
    }

    @Override // javax.xml.bind.annotation.XmlElementRefs
    public XmlElementRef[] value() {
        return this.core.value();
    }
}
