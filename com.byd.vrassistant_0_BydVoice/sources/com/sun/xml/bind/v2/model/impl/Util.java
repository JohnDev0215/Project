package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.annotation.AnnotationReader;
import com.sun.xml.bind.v2.model.annotation.AnnotationSource;
import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSchemaTypes;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
final class Util {
    Util() {
    }

    static <T, C, F, M> QName calcSchemaType(AnnotationReader<T, C, F, M> annotationReader, AnnotationSource annotationSource, C c, T t, Locatable locatable) {
        XmlSchemaType[] xmlSchemaTypeArrValue;
        XmlSchemaType xmlSchemaType = (XmlSchemaType) annotationSource.readAnnotation(XmlSchemaType.class);
        if (xmlSchemaType != null) {
            return new QName(xmlSchemaType.namespace(), xmlSchemaType.name());
        }
        XmlSchemaTypes xmlSchemaTypes = (XmlSchemaTypes) annotationReader.getPackageAnnotation(XmlSchemaTypes.class, c, locatable);
        if (xmlSchemaTypes != null) {
            xmlSchemaTypeArrValue = xmlSchemaTypes.value();
        } else {
            XmlSchemaType xmlSchemaType2 = (XmlSchemaType) annotationReader.getPackageAnnotation(XmlSchemaType.class, c, locatable);
            xmlSchemaTypeArrValue = xmlSchemaType2 != null ? new XmlSchemaType[]{xmlSchemaType2} : null;
        }
        if (xmlSchemaTypeArrValue != null) {
            for (XmlSchemaType xmlSchemaType3 : xmlSchemaTypeArrValue) {
                if (annotationReader.getClassValue(xmlSchemaType3, "type").equals(t)) {
                    return new QName(xmlSchemaType3.namespace(), xmlSchemaType3.name());
                }
            }
        }
        return null;
    }

    static MimeType calcExpectedMediaType(AnnotationSource annotationSource, ModelBuilder modelBuilder) {
        XmlMimeType xmlMimeType = (XmlMimeType) annotationSource.readAnnotation(XmlMimeType.class);
        if (xmlMimeType == null) {
            return null;
        }
        try {
            return new MimeType(xmlMimeType.value());
        } catch (MimeTypeParseException e) {
            modelBuilder.reportError(new IllegalAnnotationException(Messages.ILLEGAL_MIME_TYPE.format(xmlMimeType.value(), e.getMessage()), xmlMimeType));
            return null;
        }
    }
}
