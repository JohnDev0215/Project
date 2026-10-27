package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.core.AttributePropertyInfo;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
class AttributePropertyInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> extends SingleTypePropertyInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> implements AttributePropertyInfo<TypeT, ClassDeclT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean isRequired;
    private final QName xmlName;

    AttributePropertyInfoImpl(ClassInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> classInfoImpl, PropertySeed<TypeT, ClassDeclT, FieldT, MethodT> propertySeed) {
        super(classInfoImpl, propertySeed);
        XmlAttribute xmlAttribute = (XmlAttribute) propertySeed.readAnnotation(XmlAttribute.class);
        if (xmlAttribute.required()) {
            this.isRequired = true;
        } else {
            this.isRequired = nav().isPrimitive(getIndividualType());
        }
        this.xmlName = calcXmlName(xmlAttribute);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private javax.xml.namespace.QName calcXmlName(javax.xml.bind.annotation.XmlAttribute r5) {
        /*
            r4 = this;
            java.lang.String r0 = r5.namespace()
            java.lang.String r5 = r5.name()
            java.lang.String r1 = "##default"
            boolean r2 = r5.equals(r1)
            if (r2 == 0) goto L1a
            com.sun.xml.bind.api.impl.NameConverter r5 = com.sun.xml.bind.api.impl.NameConverter.standard
            java.lang.String r2 = r4.getName()
            java.lang.String r5 = r5.toVariableName(r2)
        L1a:
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L64
            com.sun.xml.bind.v2.model.annotation.AnnotationReader r1 = r4.reader()
            java.lang.Class<javax.xml.bind.annotation.XmlSchema> r2 = javax.xml.bind.annotation.XmlSchema.class
            com.sun.xml.bind.v2.model.impl.ClassInfoImpl<T, C, F, M> r3 = r4.parent
            java.lang.Object r3 = r3.getClazz()
            java.lang.annotation.Annotation r1 = r1.getPackageAnnotation(r2, r3, r4)
            javax.xml.bind.annotation.XmlSchema r1 = (javax.xml.bind.annotation.XmlSchema) r1
            java.lang.String r2 = ""
            if (r1 == 0) goto L63
            int[] r3 = com.sun.xml.bind.v2.model.impl.AttributePropertyInfoImpl.AnonymousClass1.$SwitchMap$javax$xml$bind$annotation$XmlNsForm
            javax.xml.bind.annotation.XmlNsForm r1 = r1.attributeFormDefault()
            int r1 = r1.ordinal()
            r1 = r3[r1]
            r3 = 1
            if (r1 == r3) goto L4c
            r3 = 2
            if (r1 == r3) goto L63
            r3 = 3
            if (r1 == r3) goto L63
            goto L64
        L4c:
            com.sun.xml.bind.v2.model.impl.ClassInfoImpl<T, C, F, M> r0 = r4.parent
            javax.xml.namespace.QName r0 = r0.getTypeName()
            java.lang.String r0 = r0.getNamespaceURI()
            int r1 = r0.length()
            if (r1 != 0) goto L64
            com.sun.xml.bind.v2.model.impl.ClassInfoImpl<T, C, F, M> r0 = r4.parent
            com.sun.xml.bind.v2.model.impl.ModelBuilder<TypeT, ClassDeclT, FieldT, MethodT> r0 = r0.builder
            java.lang.String r0 = r0.defaultNsUri
            goto L64
        L63:
            r0 = r2
        L64:
            javax.xml.namespace.QName r1 = new javax.xml.namespace.QName
            java.lang.String r0 = r0.intern()
            java.lang.String r5 = r5.intern()
            r1.<init>(r0, r5)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.xml.bind.v2.model.impl.AttributePropertyInfoImpl.calcXmlName(javax.xml.bind.annotation.XmlAttribute):javax.xml.namespace.QName");
    }

    /* JADX INFO: renamed from: com.sun.xml.bind.v2.model.impl.AttributePropertyInfoImpl$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$xml$bind$annotation$XmlNsForm;

        static {
            int[] iArr = new int[XmlNsForm.values().length];
            $SwitchMap$javax$xml$bind$annotation$XmlNsForm = iArr;
            try {
                iArr[XmlNsForm.QUALIFIED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$xml$bind$annotation$XmlNsForm[XmlNsForm.UNQUALIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$xml$bind$annotation$XmlNsForm[XmlNsForm.UNSET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.sun.xml.bind.v2.model.core.AttributePropertyInfo
    public boolean isRequired() {
        return this.isRequired;
    }

    @Override // com.sun.xml.bind.v2.model.core.AttributePropertyInfo
    public final QName getXmlName() {
        return this.xmlName;
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public final PropertyKind kind() {
        return PropertyKind.ATTRIBUTE;
    }
}
