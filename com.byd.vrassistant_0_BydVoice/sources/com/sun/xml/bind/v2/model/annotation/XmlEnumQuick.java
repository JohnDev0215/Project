package com.sun.xml.bind.v2.model.annotation;

import java.lang.annotation.Annotation;
import javax.xml.bind.annotation.XmlEnum;

/* JADX INFO: loaded from: classes3.dex */
final class XmlEnumQuick extends Quick implements XmlEnum {
    private final XmlEnum core;

    public XmlEnumQuick(Locatable locatable, XmlEnum xmlEnum) {
        super(locatable);
        this.core = xmlEnum;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Annotation getAnnotation() {
        return this.core;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Quick
    protected Quick newInstance(Locatable locatable, Annotation annotation) {
        return new XmlEnumQuick(locatable, (XmlEnum) annotation);
    }

    @Override // java.lang.annotation.Annotation
    public Class<XmlEnum> annotationType() {
        return XmlEnum.class;
    }

    @Override // javax.xml.bind.annotation.XmlEnum
    public Class value() {
        return this.core.value();
    }
}
