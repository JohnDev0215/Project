package com.sun.xml.bind.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public final class TypeReference {
    public final Annotation[] annotations;
    public final QName tagName;
    public final Type type;

    public TypeReference(QName qName, Type type, Annotation... annotationArr) {
        if (qName == null || type == null || annotationArr == null) {
            String strConcat = qName == null ? "tagName" : "";
            if (type == null) {
                strConcat = strConcat.concat(strConcat.length() > 0 ? ", type" : "type");
            }
            if (annotationArr == null) {
                strConcat = strConcat + (strConcat.length() > 0 ? ", annotations" : "annotations");
            }
            Messages.ARGUMENT_CANT_BE_NULL.format(strConcat);
            throw new IllegalArgumentException(Messages.ARGUMENT_CANT_BE_NULL.format(strConcat));
        }
        this.tagName = new QName(qName.getNamespaceURI().intern(), qName.getLocalPart().intern(), qName.getPrefix());
        this.type = type;
        this.annotations = annotationArr;
    }

    public <A extends Annotation> A get(Class<A> cls) {
        for (Annotation annotation : this.annotations) {
            if (annotation.annotationType() == cls) {
                return cls.cast(annotation);
            }
        }
        return null;
    }

    public TypeReference toItemType() {
        Type baseClass = Utils.REFLECTION_NAVIGATOR.getBaseClass(this.type, Collection.class);
        return baseClass == null ? this : new TypeReference(this.tagName, Utils.REFLECTION_NAVIGATOR.getTypeArgument(baseClass, 0), new Annotation[0]);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TypeReference typeReference = (TypeReference) obj;
        return Arrays.equals(this.annotations, typeReference.annotations) && this.tagName.equals(typeReference.tagName) && this.type.equals(typeReference.type);
    }

    public int hashCode() {
        return (((this.tagName.hashCode() * 31) + this.type.hashCode()) * 31) + Arrays.hashCode(this.annotations);
    }
}
