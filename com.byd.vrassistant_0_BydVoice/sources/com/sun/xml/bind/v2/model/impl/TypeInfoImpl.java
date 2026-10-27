package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.api.impl.NameConverter;
import com.sun.xml.bind.v2.model.annotation.AnnotationReader;
import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.core.TypeInfo;
import com.sun.xml.bind.v2.model.nav.Navigator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
abstract class TypeInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> implements TypeInfo<TypeT, ClassDeclT>, Locatable {
    protected ModelBuilder<TypeT, ClassDeclT, FieldT, MethodT> builder;
    protected final TypeInfoSetImpl<TypeT, ClassDeclT, FieldT, MethodT> owner;
    private final Locatable upstream;

    protected TypeInfoImpl(ModelBuilder<TypeT, ClassDeclT, FieldT, MethodT> modelBuilder, Locatable locatable) {
        this.builder = modelBuilder;
        this.owner = modelBuilder.typeInfoSet;
        this.upstream = locatable;
    }

    public Locatable getUpstream() {
        return this.upstream;
    }

    void link() {
        this.builder = null;
    }

    protected final Navigator<TypeT, ClassDeclT, FieldT, MethodT> nav() {
        return this.owner.nav;
    }

    protected final AnnotationReader<TypeT, ClassDeclT, FieldT, MethodT> reader() {
        return this.owner.reader;
    }

    protected final QName parseElementName(ClassDeclT classdeclt) {
        XmlRootElement xmlRootElement = (XmlRootElement) reader().getClassAnnotation(XmlRootElement.class, classdeclt, this);
        if (xmlRootElement == null) {
            return null;
        }
        String strName = xmlRootElement.name();
        if (strName.equals("##default")) {
            strName = NameConverter.standard.toVariableName(nav().getClassShortName(classdeclt));
        }
        String strNamespace = xmlRootElement.namespace();
        if (strNamespace.equals("##default")) {
            XmlSchema xmlSchema = (XmlSchema) reader().getPackageAnnotation(XmlSchema.class, classdeclt, this);
            if (xmlSchema != null) {
                strNamespace = xmlSchema.namespace();
            } else {
                strNamespace = this.builder.defaultNsUri;
            }
        }
        return new QName(strNamespace.intern(), strName.intern());
    }

    protected final QName parseTypeName(ClassDeclT classdeclt) {
        return parseTypeName(classdeclt, (XmlType) reader().getClassAnnotation(XmlType.class, classdeclt, this));
    }

    protected final QName parseTypeName(ClassDeclT classdeclt, XmlType xmlType) {
        String variableName;
        String strNamespace;
        if (xmlType != null) {
            strNamespace = xmlType.namespace();
            variableName = xmlType.name();
        } else {
            variableName = "##default";
            strNamespace = variableName;
        }
        if (variableName.length() == 0) {
            return null;
        }
        if (variableName.equals("##default")) {
            variableName = NameConverter.standard.toVariableName(nav().getClassShortName(classdeclt));
        }
        if (strNamespace.equals("##default")) {
            XmlSchema xmlSchema = (XmlSchema) reader().getPackageAnnotation(XmlSchema.class, classdeclt, this);
            if (xmlSchema != null) {
                strNamespace = xmlSchema.namespace();
            } else {
                strNamespace = this.builder.defaultNsUri;
            }
        }
        return new QName(strNamespace.intern(), variableName.intern());
    }
}
