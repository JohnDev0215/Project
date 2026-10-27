package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.namespace.QName;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
abstract class ERPropertyInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> extends PropertyInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> {
    private final boolean wrapperNillable;
    private final boolean wrapperRequired;
    private final QName xmlName;

    public ERPropertyInfoImpl(ClassInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> classInfoImpl, PropertySeed<TypeT, ClassDeclT, FieldT, MethodT> propertySeed) {
        boolean zRequired;
        super(classInfoImpl, propertySeed);
        XmlElementWrapper xmlElementWrapper = (XmlElementWrapper) this.seed.readAnnotation(XmlElementWrapper.class);
        boolean zNillable = false;
        if (!isCollection()) {
            this.xmlName = null;
            if (xmlElementWrapper != null) {
                classInfoImpl.builder.reportError(new IllegalAnnotationException(Messages.XML_ELEMENT_WRAPPER_ON_NON_COLLECTION.format(nav().getClassName(this.parent.getClazz()) + TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH + this.seed.getName()), xmlElementWrapper));
            }
        } else {
            if (xmlElementWrapper != null) {
                this.xmlName = calcXmlName(xmlElementWrapper);
                zNillable = xmlElementWrapper.nillable();
                zRequired = xmlElementWrapper.required();
                this.wrapperNillable = zNillable;
                this.wrapperRequired = zRequired;
            }
            this.xmlName = null;
        }
        zRequired = false;
        this.wrapperNillable = zNillable;
        this.wrapperRequired = zRequired;
    }

    public final QName getXmlName() {
        return this.xmlName;
    }

    public final boolean isCollectionNillable() {
        return this.wrapperNillable;
    }

    public final boolean isCollectionRequired() {
        return this.wrapperRequired;
    }
}
