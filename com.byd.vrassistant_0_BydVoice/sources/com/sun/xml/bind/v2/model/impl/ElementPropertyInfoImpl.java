package com.sun.xml.bind.v2.model.impl;

import com.sun.istack.FinalArrayList;
import com.sun.xml.bind.v2.model.core.ElementPropertyInfo;
import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.core.TypeInfo;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlList;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
class ElementPropertyInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> extends ERPropertyInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> implements ElementPropertyInfo<TypeT, ClassDeclT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Boolean isRequired;
    private final boolean isValueList;
    private final List<TypeInfo<TypeT, ClassDeclT>> ref;
    private List<TypeRefImpl<TypeT, ClassDeclT>> types;

    ElementPropertyInfoImpl(ClassInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> classInfoImpl, PropertySeed<TypeT, ClassDeclT, FieldT, MethodT> propertySeed) {
        super(classInfoImpl, propertySeed);
        this.ref = new AbstractList<TypeInfo<TypeT, ClassDeclT>>() { // from class: com.sun.xml.bind.v2.model.impl.ElementPropertyInfoImpl.1
            @Override // java.util.AbstractList, java.util.List
            public TypeInfo<TypeT, ClassDeclT> get(int i) {
                return ElementPropertyInfoImpl.this.getTypes().get(i).getTarget();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return ElementPropertyInfoImpl.this.getTypes().size();
            }
        };
        this.isValueList = this.seed.hasAnnotation(XmlList.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.model.core.ElementPropertyInfo
    public List<? extends TypeRefImpl<TypeT, ClassDeclT>> getTypes() {
        XmlElement[] xmlElementArrValue;
        if (this.types == null) {
            this.types = new FinalArrayList();
            XmlElement xmlElement = (XmlElement) this.seed.readAnnotation(XmlElement.class);
            XmlElements xmlElements = (XmlElements) this.seed.readAnnotation(XmlElements.class);
            if (xmlElement != null && xmlElements != null) {
                this.parent.builder.reportError(new IllegalAnnotationException(Messages.MUTUALLY_EXCLUSIVE_ANNOTATIONS.format(nav().getClassName(this.parent.getClazz()) + '#' + this.seed.getName(), xmlElement.annotationType().getName(), xmlElements.annotationType().getName()), xmlElement, xmlElements));
            }
            this.isRequired = true;
            if (xmlElement != null) {
                xmlElementArrValue = new XmlElement[]{xmlElement};
            } else {
                xmlElementArrValue = xmlElements != null ? xmlElements.value() : null;
            }
            if (xmlElementArrValue == null) {
                Object individualType = getIndividualType();
                if (!nav().isPrimitive(individualType) || isCollection()) {
                    this.isRequired = false;
                }
                this.types.add(createTypeRef(calcXmlName((XmlElement) null), individualType, isCollection(), null));
            } else {
                for (XmlElement xmlElement2 : xmlElementArrValue) {
                    QName qNameCalcXmlName = calcXmlName(xmlElement2);
                    Object classValue = reader().getClassValue(xmlElement2, "type");
                    if (nav().isSameType(classValue, nav().ref(XmlElement.DEFAULT.class))) {
                        classValue = getIndividualType();
                    }
                    if ((!nav().isPrimitive(classValue) || isCollection()) && !xmlElement2.required()) {
                        this.isRequired = false;
                    }
                    this.types.add(createTypeRef(qNameCalcXmlName, classValue, xmlElement2.nillable(), getDefaultValue(xmlElement2.defaultValue())));
                }
            }
            this.types = Collections.unmodifiableList(this.types);
        }
        return this.types;
    }

    private String getDefaultValue(String str) {
        if (str.equals("\u0000")) {
            return null;
        }
        return str;
    }

    protected TypeRefImpl<TypeT, ClassDeclT> createTypeRef(QName qName, TypeT typet, boolean z, String str) {
        return new TypeRefImpl<>(this, qName, typet, z, str);
    }

    @Override // com.sun.xml.bind.v2.model.core.ElementPropertyInfo
    public boolean isValueList() {
        return this.isValueList;
    }

    @Override // com.sun.xml.bind.v2.model.core.ElementPropertyInfo
    public boolean isRequired() {
        if (this.isRequired == null) {
            getTypes();
        }
        return this.isRequired.booleanValue();
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public List<? extends TypeInfo<TypeT, ClassDeclT>> ref() {
        return this.ref;
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public final PropertyKind kind() {
        return PropertyKind.ELEMENT;
    }

    @Override // com.sun.xml.bind.v2.model.impl.PropertyInfoImpl
    protected void link() {
        super.link();
        Iterator<? extends TypeRefImpl<TypeT, ClassDeclT>> it = getTypes().iterator();
        while (it.hasNext()) {
            it.next().link();
        }
        if (isValueList()) {
            if (id() != ID.IDREF) {
                Iterator<TypeRefImpl<TypeT, ClassDeclT>> it2 = this.types.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    TypeRefImpl<TypeT, ClassDeclT> next = it2.next();
                    if (!next.getTarget().isSimpleType()) {
                        this.parent.builder.reportError(new IllegalAnnotationException(Messages.XMLLIST_NEEDS_SIMPLETYPE.format(nav().getTypeName(next.getTarget().getType2())), this));
                        break;
                    }
                }
            }
            if (isCollection()) {
                return;
            }
            this.parent.builder.reportError(new IllegalAnnotationException(Messages.XMLLIST_ON_SINGLE_PROPERTY.format(new Object[0]), this));
        }
    }
}
