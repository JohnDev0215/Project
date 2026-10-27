package com.fasterxml.jackson.dataformat.xml.jaxb;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public class XmlJaxbAnnotationIntrospector extends JaxbAnnotationIntrospector implements XmlAnnotationIntrospector {
    private static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
    public Boolean isOutputAsCData(Annotated annotated) {
        return null;
    }

    @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
    public void setDefaultUseWrapper(boolean z) {
    }

    @Deprecated
    public XmlJaxbAnnotationIntrospector() {
    }

    public XmlJaxbAnnotationIntrospector(TypeFactory typeFactory) {
        super(typeFactory);
    }

    @Override // com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector, com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
    public String findNamespace(Annotated annotated) {
        return super.findNamespace(annotated);
    }

    @Override // com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector, com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
    public Boolean isOutputAsAttribute(Annotated annotated) {
        return super.isOutputAsAttribute(annotated);
    }

    @Override // com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector, com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
    public Boolean isOutputAsText(Annotated annotated) {
        return super.isOutputAsText(annotated);
    }
}
