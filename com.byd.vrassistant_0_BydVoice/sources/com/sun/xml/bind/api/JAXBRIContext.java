package com.sun.xml.bind.api;

import com.sun.xml.bind.api.impl.NameConverter;
import com.sun.xml.bind.v2.ContextFactory;
import com.sun.xml.bind.v2.model.annotation.RuntimeAnnotationReader;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;

/* JADX INFO: loaded from: classes3.dex */
public abstract class JAXBRIContext extends JAXBContext {
    public static final String ANNOTATION_READER = RuntimeAnnotationReader.class.getName();
    public static final String BACKUP_WITH_PARENT_NAMESPACE = "com.sun.xml.bind.backupWithParentNamespace";
    public static final String CANONICALIZATION_SUPPORT = "com.sun.xml.bind.c14n";
    public static final String DEFAULT_NAMESPACE_REMAP = "com.sun.xml.bind.defaultNamespaceRemap";
    public static final String DISABLE_XML_SECURITY = "com.sun.xml.bind.disableXmlSecurity";
    public static final String ENABLE_XOP = "com.sun.xml.bind.XOP";
    public static final String IMPROVED_XSI_TYPE_HANDLING = "com.sun.xml.bind.improvedXsiTypeHandling";
    public static final String RETAIN_REFERENCE_TO_INFO = "retainReferenceToInfo";
    public static final String SUBCLASS_REPLACEMENTS = "com.sun.xml.bind.subclassReplacements";
    public static final String SUPRESS_ACCESSOR_WARNINGS = "supressAccessorWarnings";
    public static final String TREAT_EVERYTHING_NILLABLE = "com.sun.xml.bind.treatEverythingNillable";
    public static final String TYPE_REFERENCES = "com.sun.xml.bind.typeReferences";
    public static final String XMLACCESSORFACTORY_SUPPORT = "com.sun.xml.bind.XmlAccessorFactory";

    public abstract Bridge createBridge(TypeReference typeReference);

    public abstract BridgeContext createBridgeContext();

    public abstract void generateEpisode(Result result);

    @Override // javax.xml.bind.JAXBContext
    public abstract void generateSchema(SchemaOutputResolver schemaOutputResolver) throws IOException;

    public abstract String getBuildId();

    public abstract QName getElementName(Class cls) throws JAXBException;

    public abstract QName getElementName(Object obj) throws JAXBException;

    public abstract <B, V> RawAccessor<B, V> getElementPropertyAccessor(Class<B> cls, String str, String str2) throws JAXBException;

    public abstract List<String> getKnownNamespaceURIs();

    public abstract RuntimeTypeInfoSet getRuntimeTypeInfoSet();

    public abstract QName getTypeName(TypeReference typeReference);

    public abstract boolean hasSwaRef();

    protected JAXBRIContext() {
    }

    public static JAXBRIContext newInstance(Class[] clsArr, Collection<TypeReference> collection, Map<Class, Class> map, String str, boolean z, RuntimeAnnotationReader runtimeAnnotationReader) throws JAXBException {
        return newInstance(clsArr, collection, map, str, z, runtimeAnnotationReader, false, false, false, false);
    }

    public static JAXBRIContext newInstance(Class[] clsArr, Collection<TypeReference> collection, Map<Class, Class> map, String str, boolean z, RuntimeAnnotationReader runtimeAnnotationReader, boolean z2, boolean z3, boolean z4, boolean z5) throws JAXBException {
        HashMap map2 = new HashMap();
        if (collection != null) {
            map2.put(TYPE_REFERENCES, collection);
        }
        if (map != null) {
            map2.put(SUBCLASS_REPLACEMENTS, map);
        }
        if (str != null) {
            map2.put(DEFAULT_NAMESPACE_REMAP, str);
        }
        if (runtimeAnnotationReader != null) {
            map2.put(ANNOTATION_READER, runtimeAnnotationReader);
        }
        map2.put(CANONICALIZATION_SUPPORT, Boolean.valueOf(z));
        map2.put(XMLACCESSORFACTORY_SUPPORT, Boolean.valueOf(z2));
        map2.put(TREAT_EVERYTHING_NILLABLE, Boolean.valueOf(z3));
        map2.put(RETAIN_REFERENCE_TO_INFO, Boolean.valueOf(z4));
        map2.put(SUPRESS_ACCESSOR_WARNINGS, Boolean.valueOf(z5));
        return (JAXBRIContext) ContextFactory.createContext(clsArr, map2);
    }

    public static JAXBRIContext newInstance(Class[] clsArr, Collection<TypeReference> collection, String str, boolean z) throws JAXBException {
        return newInstance(clsArr, collection, Collections.emptyMap(), str, z, null);
    }

    public static String mangleNameToVariableName(String str) {
        return NameConverter.standard.toVariableName(str);
    }

    public static String mangleNameToClassName(String str) {
        return NameConverter.standard.toClassName(str);
    }

    public static String mangleNameToPropertyName(String str) {
        return NameConverter.standard.toPropertyName(str);
    }

    public static Type getBaseType(Type type, Class cls) {
        return Utils.REFLECTION_NAVIGATOR.getBaseClass(type, cls);
    }
}
