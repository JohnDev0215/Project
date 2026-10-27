package com.fasterxml.jackson.dataformat.xml.util;

import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector;
import java.io.Serializable;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes6.dex */
public class XmlRootNameLookup implements Serializable {
    public static final QName ROOT_NAME_FOR_NULL = new QName("null");
    private static final long serialVersionUID = 1;
    protected final transient LRUMap<ClassKey, QName> _rootNames = new LRUMap<>(40, 200);

    protected Object readResolve() {
        return this._rootNames == null ? new XmlRootNameLookup() : this;
    }

    public QName findRootName(JavaType javaType, MapperConfig<?> mapperConfig) {
        return findRootName(javaType.getRawClass(), mapperConfig);
    }

    public QName findRootName(Class<?> cls, MapperConfig<?> mapperConfig) {
        QName qName;
        ClassKey classKey = new ClassKey(cls);
        synchronized (this._rootNames) {
            qName = this._rootNames.get(classKey);
        }
        if (qName != null) {
            return qName;
        }
        QName qName_findRootName = _findRootName(cls, mapperConfig);
        synchronized (this._rootNames) {
            this._rootNames.put(classKey, qName_findRootName);
        }
        return qName_findRootName;
    }

    protected QName _findRootName(Class<?> cls, MapperConfig<?> mapperConfig) {
        String str_findNamespace;
        BeanDescription beanDescriptionIntrospectClassAnnotations = mapperConfig.introspectClassAnnotations(cls);
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        AnnotatedClass classInfo = beanDescriptionIntrospectClassAnnotations.getClassInfo();
        PropertyName propertyNameFindRootName = annotationIntrospector.findRootName(classInfo);
        String simpleName = null;
        if (propertyNameFindRootName != null) {
            simpleName = propertyNameFindRootName.getSimpleName();
            str_findNamespace = propertyNameFindRootName.getNamespace();
        } else {
            str_findNamespace = null;
        }
        if (simpleName == null || simpleName.length() == 0) {
            return _qname(str_findNamespace, StaxUtil.sanitizeXmlTypeName(cls.getSimpleName()));
        }
        if (str_findNamespace == null || str_findNamespace.isEmpty()) {
            str_findNamespace = _findNamespace(annotationIntrospector, classInfo);
        }
        return _qname(str_findNamespace, simpleName);
    }

    private QName _qname(String str, String str2) {
        if (str == null) {
            str = "";
        }
        return new QName(str, str2);
    }

    private String _findNamespace(AnnotationIntrospector annotationIntrospector, AnnotatedClass annotatedClass) {
        String strFindNamespace;
        for (Versioned versioned : annotationIntrospector.allIntrospectors()) {
            if ((versioned instanceof XmlAnnotationIntrospector) && (strFindNamespace = ((XmlAnnotationIntrospector) versioned).findNamespace(annotatedClass)) != null) {
                return strFindNamespace;
            }
        }
        return null;
    }
}
