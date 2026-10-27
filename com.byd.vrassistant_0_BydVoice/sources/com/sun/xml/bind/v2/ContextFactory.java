package com.sun.xml.bind.v2;

import cerence.ark.assistant.core.dcc.DccConstant;
import com.android.multidex.ClassPathElement;
import com.sun.istack.FinalArrayList;
import com.sun.xml.bind.Util;
import com.sun.xml.bind.api.JAXBRIContext;
import com.sun.xml.bind.api.TypeReference;
import com.sun.xml.bind.v2.model.annotation.RuntimeAnnotationReader;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.util.TypeCast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.pool.TypePool;
import org.apache.commons.compress.utils.CharsetNames;

/* JADX INFO: loaded from: classes3.dex */
public class ContextFactory {
    public static final String USE_JAXB_PROPERTIES = "_useJAXBProperties";

    public static JAXBContext createContext(Class[] clsArr, Map<String, Object> map) throws JAXBException {
        Map map2;
        if (map == null) {
            map2 = Collections.emptyMap();
        } else {
            map2 = new HashMap(map);
        }
        String str = (String) getPropertyValue(map2, JAXBRIContext.DEFAULT_NAMESPACE_REMAP, String.class);
        Boolean bool = (Boolean) getPropertyValue(map2, JAXBRIContext.CANONICALIZATION_SUPPORT, Boolean.class);
        if (bool == null) {
            bool = false;
        }
        Boolean bool2 = (Boolean) getPropertyValue(map2, JAXBRIContext.DISABLE_XML_SECURITY, Boolean.class);
        if (bool2 == null) {
            bool2 = false;
        }
        Boolean bool3 = (Boolean) getPropertyValue(map2, JAXBRIContext.TREAT_EVERYTHING_NILLABLE, Boolean.class);
        if (bool3 == null) {
            bool3 = false;
        }
        Boolean bool4 = (Boolean) getPropertyValue(map2, JAXBRIContext.RETAIN_REFERENCE_TO_INFO, Boolean.class);
        if (bool4 == null) {
            bool4 = false;
        }
        Boolean bool5 = (Boolean) getPropertyValue(map2, JAXBRIContext.SUPRESS_ACCESSOR_WARNINGS, Boolean.class);
        if (bool5 == null) {
            bool5 = false;
        }
        Boolean boolValueOf = (Boolean) getPropertyValue(map2, JAXBRIContext.IMPROVED_XSI_TYPE_HANDLING, Boolean.class);
        if (boolValueOf == null) {
            String systemProperty = Util.getSystemProperty(JAXBRIContext.IMPROVED_XSI_TYPE_HANDLING);
            if (systemProperty == null) {
                boolValueOf = true;
            } else {
                boolValueOf = Boolean.valueOf(systemProperty);
            }
        }
        Boolean bool6 = (Boolean) getPropertyValue(map2, JAXBRIContext.XMLACCESSORFACTORY_SUPPORT, Boolean.class);
        if (bool6 == null) {
            bool6 = false;
            Util.getClassLogger().log(Level.FINE, "Property com.sun.xml.bind.XmlAccessorFactoryis not active.  Using JAXB's implementation");
        }
        Boolean bool7 = (Boolean) getPropertyValue(map2, JAXBRIContext.BACKUP_WITH_PARENT_NAMESPACE, Boolean.class);
        RuntimeAnnotationReader runtimeAnnotationReader = (RuntimeAnnotationReader) getPropertyValue(map2, JAXBRIContext.ANNOTATION_READER, RuntimeAnnotationReader.class);
        Collection<TypeReference> collectionEmptyList = (Collection) getPropertyValue(map2, JAXBRIContext.TYPE_REFERENCES, Collection.class);
        if (collectionEmptyList == null) {
            collectionEmptyList = Collections.emptyList();
        }
        try {
            Map<Class, Class> mapCheckedCast = TypeCast.checkedCast((Map) getPropertyValue(map2, JAXBRIContext.SUBCLASS_REPLACEMENTS, Map.class), Class.class, Class.class);
            if (!map2.isEmpty()) {
                throw new JAXBException(Messages.UNSUPPORTED_PROPERTY.format(map2.keySet().iterator().next()));
            }
            JAXBContextImpl.JAXBContextBuilder jAXBContextBuilder = new JAXBContextImpl.JAXBContextBuilder();
            jAXBContextBuilder.setClasses(clsArr);
            jAXBContextBuilder.setTypeRefs(collectionEmptyList);
            jAXBContextBuilder.setSubclassReplacements(mapCheckedCast);
            jAXBContextBuilder.setDefaultNsUri(str);
            jAXBContextBuilder.setC14NSupport(bool.booleanValue());
            jAXBContextBuilder.setAnnotationReader(runtimeAnnotationReader);
            jAXBContextBuilder.setXmlAccessorFactorySupport(bool6.booleanValue());
            jAXBContextBuilder.setAllNillable(bool3.booleanValue());
            jAXBContextBuilder.setRetainPropertyInfo(bool4.booleanValue());
            jAXBContextBuilder.setSupressAccessorWarnings(bool5.booleanValue());
            jAXBContextBuilder.setImprovedXsiTypeHandling(boolValueOf.booleanValue());
            jAXBContextBuilder.setDisableSecurityProcessing(bool2.booleanValue());
            jAXBContextBuilder.setBackupWithParentNamespace(bool7);
            return jAXBContextBuilder.build();
        } catch (ClassCastException e) {
            throw new JAXBException(Messages.INVALID_TYPE_IN_MAP.format(new Object[0]), e);
        }
    }

    private static <T> T getPropertyValue(Map<String, Object> map, String str, Class<T> cls) throws JAXBException {
        Object obj = map.get(str);
        if (obj == null) {
            return null;
        }
        map.remove(str);
        if (!cls.isInstance(obj)) {
            throw new JAXBException(Messages.INVALID_PROPERTY_VALUE.format(str, obj));
        }
        return cls.cast(obj);
    }

    @Deprecated
    public static JAXBRIContext createContext(Class[] clsArr, Collection<TypeReference> collection, Map<Class, Class> map, String str, boolean z, RuntimeAnnotationReader runtimeAnnotationReader, boolean z2, boolean z3, boolean z4) throws JAXBException {
        return createContext(clsArr, collection, map, str, z, runtimeAnnotationReader, z2, z3, z4, false);
    }

    @Deprecated
    public static JAXBRIContext createContext(Class[] clsArr, Collection<TypeReference> collection, Map<Class, Class> map, String str, boolean z, RuntimeAnnotationReader runtimeAnnotationReader, boolean z2, boolean z3, boolean z4, boolean z5) throws JAXBException {
        JAXBContextImpl.JAXBContextBuilder jAXBContextBuilder = new JAXBContextImpl.JAXBContextBuilder();
        jAXBContextBuilder.setClasses(clsArr);
        jAXBContextBuilder.setTypeRefs(collection);
        jAXBContextBuilder.setSubclassReplacements(map);
        jAXBContextBuilder.setDefaultNsUri(str);
        jAXBContextBuilder.setC14NSupport(z);
        jAXBContextBuilder.setAnnotationReader(runtimeAnnotationReader);
        jAXBContextBuilder.setXmlAccessorFactorySupport(z2);
        jAXBContextBuilder.setAllNillable(z3);
        jAXBContextBuilder.setRetainPropertyInfo(z4);
        jAXBContextBuilder.setImprovedXsiTypeHandling(z5);
        return jAXBContextBuilder.build();
    }

    public static JAXBContext createContext(String str, ClassLoader classLoader, Map<String, Object> map) throws JAXBException {
        boolean z;
        boolean z2;
        FinalArrayList finalArrayList = new FinalArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(str, ":");
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            try {
                finalArrayList.add(classLoader.loadClass(strNextToken + ".ObjectFactory"));
                z = true;
            } catch (ClassNotFoundException unused) {
                z = false;
            }
            try {
                List<Class> listLoadIndexedClasses = loadIndexedClasses(strNextToken, classLoader);
                if (listLoadIndexedClasses != null) {
                    finalArrayList.addAll(listLoadIndexedClasses);
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (!z && !z2) {
                    throw new JAXBException(Messages.BROKEN_CONTEXTPATH.format(strNextToken));
                }
            } catch (IOException e) {
                throw new JAXBException(e);
            }
        }
        return createContext((Class[]) finalArrayList.toArray(new Class[finalArrayList.size()]), map);
    }

    private static List<Class> loadIndexedClasses(String str, ClassLoader classLoader) throws IOException, JAXBException {
        String str2 = str.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + "/jaxb.index";
        InputStream resourceAsStream = classLoader.getResourceAsStream(str2);
        if (resourceAsStream == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, CharsetNames.UTF_8));
        try {
            FinalArrayList finalArrayList = new FinalArrayList();
            String line = bufferedReader.readLine();
            while (line != null) {
                String strTrim = line.trim();
                if (strTrim.startsWith(DccConstant.SEPARATOR) || strTrim.length() == 0) {
                    line = bufferedReader.readLine();
                } else {
                    if (strTrim.endsWith(ClassFileLocator.CLASS_FILE_EXTENSION)) {
                        throw new JAXBException(Messages.ILLEGAL_ENTRY.format(strTrim));
                    }
                    try {
                        finalArrayList.add(classLoader.loadClass(str + TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH + strTrim));
                        line = bufferedReader.readLine();
                    } catch (ClassNotFoundException e) {
                        throw new JAXBException(Messages.ERROR_LOADING_CLASS.format(strTrim, str2), e);
                    }
                }
            }
            return finalArrayList;
        } finally {
            bufferedReader.close();
        }
    }
}
