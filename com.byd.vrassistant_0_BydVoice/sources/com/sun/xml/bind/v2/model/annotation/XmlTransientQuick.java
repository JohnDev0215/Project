package com.sun.xml.bind.v2.model.annotation;

import java.lang.annotation.Annotation;
import javax.xml.bind.annotation.XmlTransient;

/* JADX INFO: loaded from: classes3.dex */
final class XmlTransientQuick extends Quick implements XmlTransient {
    private final XmlTransient core;

    public XmlTransientQuick(Locatable locatable, XmlTransient xmlTransient) {
        super(locatable);
        this.core = xmlTransient;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Annotation getAnnotation() {
        return this.core;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Quick newInstance(Locatable locatable, Annotation annotation) {
        return new XmlTransientQuick(locatable, (XmlTransient) annotation);
    }

    @Override // java.lang.annotation.Annotation
    public Class<XmlTransient> annotationType() {
        return XmlTransient.class;
    }
}
