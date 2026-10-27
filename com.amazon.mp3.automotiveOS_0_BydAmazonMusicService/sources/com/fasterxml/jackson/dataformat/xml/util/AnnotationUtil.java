package com.fasterxml.jackson.dataformat.xml.util;

import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

/* JADX INFO: loaded from: classes6.dex */
public class AnnotationUtil {
    public static String findNamespaceAnnotation(AnnotationIntrospector annotationIntrospector, AnnotatedMember annotatedMember) {
        String strFindNamespace;
        for (Versioned versioned : annotationIntrospector.allIntrospectors()) {
            if (versioned instanceof XmlAnnotationIntrospector) {
                String strFindNamespace2 = ((XmlAnnotationIntrospector) versioned).findNamespace(annotatedMember);
                if (strFindNamespace2 != null) {
                    return strFindNamespace2;
                }
            } else if ((versioned instanceof JaxbAnnotationIntrospector) && (strFindNamespace = ((JaxbAnnotationIntrospector) versioned).findNamespace(annotatedMember)) != null) {
                return strFindNamespace;
            }
        }
        return null;
    }

    public static Boolean findIsAttributeAnnotation(AnnotationIntrospector annotationIntrospector, AnnotatedMember annotatedMember) {
        Boolean boolIsOutputAsAttribute;
        for (Versioned versioned : annotationIntrospector.allIntrospectors()) {
            if (versioned instanceof XmlAnnotationIntrospector) {
                Boolean boolIsOutputAsAttribute2 = ((XmlAnnotationIntrospector) versioned).isOutputAsAttribute(annotatedMember);
                if (boolIsOutputAsAttribute2 != null) {
                    return boolIsOutputAsAttribute2;
                }
            } else if ((versioned instanceof JaxbAnnotationIntrospector) && (boolIsOutputAsAttribute = ((JaxbAnnotationIntrospector) versioned).isOutputAsAttribute(annotatedMember)) != null) {
                return boolIsOutputAsAttribute;
            }
        }
        return null;
    }

    public static Boolean findIsTextAnnotation(AnnotationIntrospector annotationIntrospector, AnnotatedMember annotatedMember) {
        Boolean boolIsOutputAsText;
        for (Versioned versioned : annotationIntrospector.allIntrospectors()) {
            if (versioned instanceof XmlAnnotationIntrospector) {
                Boolean boolIsOutputAsText2 = ((XmlAnnotationIntrospector) versioned).isOutputAsText(annotatedMember);
                if (boolIsOutputAsText2 != null) {
                    return boolIsOutputAsText2;
                }
            } else if ((versioned instanceof JaxbAnnotationIntrospector) && (boolIsOutputAsText = ((JaxbAnnotationIntrospector) versioned).isOutputAsText(annotatedMember)) != null) {
                return boolIsOutputAsText;
            }
        }
        return null;
    }

    public static Boolean findIsCDataAnnotation(AnnotationIntrospector annotationIntrospector, AnnotatedMember annotatedMember) {
        Boolean boolIsOutputAsCData;
        for (Versioned versioned : annotationIntrospector.allIntrospectors()) {
            if ((versioned instanceof XmlAnnotationIntrospector) && (boolIsOutputAsCData = ((XmlAnnotationIntrospector) versioned).isOutputAsCData(annotatedMember)) != null) {
                return boolIsOutputAsCData;
            }
        }
        return null;
    }
}
