package com.fasterxml.jackson.dataformat.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import java.lang.annotation.Annotation;

/* JADX INFO: loaded from: classes6.dex */
public class JacksonXmlAnnotationIntrospector extends JacksonAnnotationIntrospector implements XmlAnnotationIntrospector {
    private static final Class<? extends Annotation>[] ANNOTATIONS_TO_INFER_XML_PROP = {JacksonXmlText.class, JacksonXmlElementWrapper.class};
    public static final boolean DEFAULT_USE_WRAPPER = true;
    private static final long serialVersionUID = 1;
    protected boolean _cfgDefaultUseWrapper;

    public JacksonXmlAnnotationIntrospector() {
        this(true);
    }

    public JacksonXmlAnnotationIntrospector(boolean z) {
        this._cfgDefaultUseWrapper = z;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public PropertyName findWrapperName(Annotated annotated) {
        JacksonXmlElementWrapper jacksonXmlElementWrapper = (JacksonXmlElementWrapper) _findAnnotation(annotated, JacksonXmlElementWrapper.class);
        if (jacksonXmlElementWrapper != null) {
            if (!jacksonXmlElementWrapper.useWrapping()) {
                return PropertyName.NO_NAME;
            }
            String strLocalName = jacksonXmlElementWrapper.localName();
            if (strLocalName == null || strLocalName.length() == 0) {
                return PropertyName.USE_DEFAULT;
            }
            return PropertyName.construct(jacksonXmlElementWrapper.localName(), jacksonXmlElementWrapper.namespace());
        }
        if (this._cfgDefaultUseWrapper) {
            return PropertyName.USE_DEFAULT;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector, com.fasterxml.jackson.databind.AnnotationIntrospector
    public PropertyName findRootName(AnnotatedClass annotatedClass) {
        JacksonXmlRootElement jacksonXmlRootElement = (JacksonXmlRootElement) _findAnnotation(annotatedClass, JacksonXmlRootElement.class);
        if (jacksonXmlRootElement != null) {
            String strLocalName = jacksonXmlRootElement.localName();
            String strNamespace = jacksonXmlRootElement.namespace();
            if (strLocalName.length() == 0 && strNamespace.length() == 0) {
                return PropertyName.USE_DEFAULT;
            }
            return new PropertyName(strLocalName, strNamespace);
        }
        return super.findRootName(annotatedClass);
    }

    @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
    public String findNamespace(Annotated annotated) {
        JacksonXmlProperty jacksonXmlProperty = (JacksonXmlProperty) _findAnnotation(annotated, JacksonXmlProperty.class);
        if (jacksonXmlProperty != null) {
            return jacksonXmlProperty.namespace();
        }
        JsonProperty jsonProperty = (JsonProperty) _findAnnotation(annotated, JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.namespace();
        }
        return null;
    }

    @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
    public Boolean isOutputAsAttribute(Annotated annotated) {
        JacksonXmlProperty jacksonXmlProperty = (JacksonXmlProperty) _findAnnotation(annotated, JacksonXmlProperty.class);
        if (jacksonXmlProperty != null) {
            return jacksonXmlProperty.isAttribute() ? Boolean.TRUE : Boolean.FALSE;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
    public Boolean isOutputAsText(Annotated annotated) {
        JacksonXmlText jacksonXmlText = (JacksonXmlText) _findAnnotation(annotated, JacksonXmlText.class);
        if (jacksonXmlText != null) {
            return jacksonXmlText.value() ? Boolean.TRUE : Boolean.FALSE;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
    public Boolean isOutputAsCData(Annotated annotated) {
        JacksonXmlCData jacksonXmlCData = (JacksonXmlCData) annotated.getAnnotation(JacksonXmlCData.class);
        if (jacksonXmlCData != null) {
            return jacksonXmlCData.value() ? Boolean.TRUE : Boolean.FALSE;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
    public void setDefaultUseWrapper(boolean z) {
        this._cfgDefaultUseWrapper = z;
    }

    @Override // com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector, com.fasterxml.jackson.databind.AnnotationIntrospector
    public PropertyName findNameForSerialization(Annotated annotated) {
        PropertyName propertyName_findXmlName = _findXmlName(annotated);
        return (propertyName_findXmlName == null && (propertyName_findXmlName = super.findNameForSerialization(annotated)) == null && _hasOneOf(annotated, ANNOTATIONS_TO_INFER_XML_PROP)) ? PropertyName.USE_DEFAULT : propertyName_findXmlName;
    }

    @Override // com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector, com.fasterxml.jackson.databind.AnnotationIntrospector
    public PropertyName findNameForDeserialization(Annotated annotated) {
        PropertyName propertyName_findXmlName = _findXmlName(annotated);
        return (propertyName_findXmlName == null && (propertyName_findXmlName = super.findNameForDeserialization(annotated)) == null && _hasOneOf(annotated, ANNOTATIONS_TO_INFER_XML_PROP)) ? PropertyName.USE_DEFAULT : propertyName_findXmlName;
    }

    @Override // com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector
    protected StdTypeResolverBuilder _constructStdTypeResolverBuilder() {
        return new XmlTypeResolverBuilder();
    }

    protected PropertyName _findXmlName(Annotated annotated) {
        JacksonXmlProperty jacksonXmlProperty = (JacksonXmlProperty) _findAnnotation(annotated, JacksonXmlProperty.class);
        if (jacksonXmlProperty != null) {
            return PropertyName.construct(jacksonXmlProperty.localName(), jacksonXmlProperty.namespace());
        }
        return null;
    }
}
