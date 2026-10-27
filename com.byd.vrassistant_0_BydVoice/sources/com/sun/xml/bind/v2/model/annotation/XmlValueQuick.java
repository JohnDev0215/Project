package com.sun.xml.bind.v2.model.annotation;

import java.lang.annotation.Annotation;
import javax.xml.bind.annotation.XmlValue;

/* JADX INFO: loaded from: classes3.dex */
final class XmlValueQuick extends Quick implements XmlValue {
    private final XmlValue core;

    public XmlValueQuick(Locatable locatable, XmlValue xmlValue) {
        super(locatable);
        this.core = xmlValue;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Annotation getAnnotation() {
        return this.core;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Quick newInstance(Locatable locatable, Annotation annotation) {
        return new XmlValueQuick(locatable, (XmlValue) annotation);
    }

    @Override // java.lang.annotation.Annotation
    public Class<XmlValue> annotationType() {
        return XmlValue.class;
    }
}
