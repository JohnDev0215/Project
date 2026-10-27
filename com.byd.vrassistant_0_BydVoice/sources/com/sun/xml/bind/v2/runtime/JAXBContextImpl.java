package com.sun.xml.bind.v2.runtime;

import com.sun.istack.Pool;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.api.Bridge;
import com.sun.xml.bind.api.BridgeContext;
import com.sun.xml.bind.api.CompositeStructure;
import com.sun.xml.bind.api.ErrorListener;
import com.sun.xml.bind.api.JAXBRIContext;
import com.sun.xml.bind.api.RawAccessor;
import com.sun.xml.bind.api.TypeReference;
import com.sun.xml.bind.unmarshaller.DOMScanner;
import com.sun.xml.bind.util.Which;
import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.model.annotation.RuntimeAnnotationReader;
import com.sun.xml.bind.v2.model.annotation.RuntimeInlineAnnotationReader;
import com.sun.xml.bind.v2.model.core.Adapter;
import com.sun.xml.bind.v2.model.core.ArrayInfo;
import com.sun.xml.bind.v2.model.core.ClassInfo;
import com.sun.xml.bind.v2.model.core.ElementInfo;
import com.sun.xml.bind.v2.model.core.EnumLeafInfo;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.Ref;
import com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl;
import com.sun.xml.bind.v2.model.impl.RuntimeModelBuilder;
import com.sun.xml.bind.v2.model.nav.Navigator;
import com.sun.xml.bind.v2.model.runtime.RuntimeArrayInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeClassInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeElementInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeEnumLeafInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeLeafInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationsException;
import com.sun.xml.bind.v2.runtime.output.Encoded;
import com.sun.xml.bind.v2.runtime.property.AttributeProperty;
import com.sun.xml.bind.v2.runtime.property.Property;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.TagName;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallerImpl;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator;
import com.sun.xml.bind.v2.util.EditDistance;
import com.sun.xml.bind.v2.util.QNameMap;
import com.sun.xml.bind.v2.util.XmlFactory;
import com.sun.xml.txw2.output.ResultFactory;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.bind.Binder;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Validator;
import javax.xml.bind.annotation.XmlAttachmentRef;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* JADX INFO: loaded from: classes3.dex */
public final class JAXBContextImpl extends JAXBRIContext {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Comparator<QName> QNAME_COMPARATOR = new Comparator<QName>() { // from class: com.sun.xml.bind.v2.runtime.JAXBContextImpl.6
        @Override // java.util.Comparator
        public int compare(QName qName, QName qName2) {
            int iCompareTo = qName.getLocalPart().compareTo(qName2.getLocalPart());
            return iCompareTo != 0 ? iCompareTo : qName.getNamespaceURI().compareTo(qName2.getNamespaceURI());
        }
    };
    private static DocumentBuilder db;
    public final boolean allNillable;
    private RuntimeAnnotationReader annotationReader;
    public Boolean backupWithParentNamespace;
    private final Map<Class, JaxBeanInfo> beanInfoMap;
    protected Map<RuntimeTypeInfo, JaxBeanInfo> beanInfos;
    private final Map<TypeReference, Bridge> bridges;
    protected final boolean c14nSupport;
    private final Class[] classes;
    private final String defaultNsUri;
    public final boolean disableSecurityProcessing;
    private final Map<Class, Map<QName, ElementBeanInfoImpl>> elements;
    public final boolean fastBoot;
    private boolean hasSwaRef;
    public final boolean improvedXsiTypeHandling;
    public final Pool<Marshaller> marshallerPool;
    public NameBuilder nameBuilder;
    public final NameList nameList;
    public final boolean retainPropertyInfo;
    private final QNameMap<JaxBeanInfo> rootMap;
    private final Map<Class, Class> subclassReplacements;
    public final boolean supressAccessorWarnings;
    private WeakReference<RuntimeTypeInfoSet> typeInfoSetCache;
    private final HashMap<QName, JaxBeanInfo> typeMap;
    public final Pool<Unmarshaller> unmarshallerPool;
    private Encoded[] utf8nameTable;
    public final boolean xmlAccessorFactorySupport;
    private Set<XmlNs> xmlNsSet;

    public Set<XmlNs> getXmlNsSet() {
        return this.xmlNsSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v16, types: [com.sun.xml.bind.v2.model.runtime.RuntimeNonElement, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo] */
    private JAXBContextImpl(JAXBContextBuilder jAXBContextBuilder) throws JAXBException {
        boolean z;
        InternalBridge bridgeImpl;
        this.bridges = new LinkedHashMap();
        this.rootMap = new QNameMap<>();
        this.typeMap = new HashMap<>();
        this.beanInfoMap = new LinkedHashMap();
        this.beanInfos = new LinkedHashMap();
        this.elements = new LinkedHashMap();
        this.marshallerPool = new Pool.Impl<Marshaller>() { // from class: com.sun.xml.bind.v2.runtime.JAXBContextImpl.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sun.istack.Pool.Impl
            public Marshaller create() {
                return JAXBContextImpl.this.createMarshaller();
            }
        };
        this.unmarshallerPool = new Pool.Impl<Unmarshaller>() { // from class: com.sun.xml.bind.v2.runtime.JAXBContextImpl.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sun.istack.Pool.Impl
            public Unmarshaller create() {
                return JAXBContextImpl.this.createUnmarshaller();
            }
        };
        this.nameBuilder = new NameBuilder();
        this.xmlNsSet = null;
        this.backupWithParentNamespace = null;
        this.defaultNsUri = jAXBContextBuilder.defaultNsUri;
        this.retainPropertyInfo = jAXBContextBuilder.retainPropertyInfo;
        this.annotationReader = jAXBContextBuilder.annotationReader;
        this.subclassReplacements = jAXBContextBuilder.subclassReplacements;
        this.c14nSupport = jAXBContextBuilder.c14nSupport;
        this.classes = jAXBContextBuilder.classes;
        this.xmlAccessorFactorySupport = jAXBContextBuilder.xmlAccessorFactorySupport;
        this.allNillable = jAXBContextBuilder.allNillable;
        this.supressAccessorWarnings = jAXBContextBuilder.supressAccessorWarnings;
        this.improvedXsiTypeHandling = jAXBContextBuilder.improvedXsiTypeHandling;
        this.disableSecurityProcessing = jAXBContextBuilder.disableSecurityProcessing;
        this.backupWithParentNamespace = jAXBContextBuilder.backupWithParentNamespace;
        Collection<TypeReference> collection = jAXBContextBuilder.typeRefs;
        try {
            z = Boolean.getBoolean(JAXBContextImpl.class.getName() + ".fastBoot");
        } catch (SecurityException unused) {
            z = false;
        }
        this.fastBoot = z;
        RuntimeTypeInfoSet typeInfoSet = getTypeInfoSet();
        this.elements.put(null, new LinkedHashMap());
        for (RuntimeBuiltinLeafInfoImpl<?> runtimeBuiltinLeafInfoImpl : RuntimeBuiltinLeafInfoImpl.builtinBeanInfos) {
            LeafBeanInfoImpl leafBeanInfoImpl = new LeafBeanInfoImpl(this, runtimeBuiltinLeafInfoImpl);
            this.beanInfoMap.put(runtimeBuiltinLeafInfoImpl.getClazz(), leafBeanInfoImpl);
            Iterator<QName> it = leafBeanInfoImpl.getTypeNames().iterator();
            while (it.hasNext()) {
                this.typeMap.put(it.next(), leafBeanInfoImpl);
            }
        }
        Iterator<? extends EnumLeafInfo<Type, Class>> it2 = typeInfoSet.enums().values().iterator();
        while (it2.hasNext()) {
            RuntimeEnumLeafInfo runtimeEnumLeafInfo = (RuntimeEnumLeafInfo) it2.next();
            JaxBeanInfo orCreate = getOrCreate(runtimeEnumLeafInfo);
            Iterator<QName> it3 = orCreate.getTypeNames().iterator();
            while (it3.hasNext()) {
                this.typeMap.put(it3.next(), orCreate);
            }
            if (runtimeEnumLeafInfo.isElement()) {
                this.rootMap.put(runtimeEnumLeafInfo.getElementName(), orCreate);
            }
        }
        Iterator<? extends ArrayInfo<Type, Class>> it4 = typeInfoSet.arrays().values().iterator();
        while (it4.hasNext()) {
            JaxBeanInfo orCreate2 = getOrCreate((RuntimeArrayInfo) it4.next());
            Iterator<QName> it5 = orCreate2.getTypeNames().iterator();
            while (it5.hasNext()) {
                this.typeMap.put(it5.next(), orCreate2);
            }
        }
        for (Map.Entry<Class, ? extends ClassInfo<Type, Class>> entry : typeInfoSet.beans().entrySet()) {
            ClassBeanInfoImpl orCreate3 = getOrCreate((RuntimeClassInfo) entry.getValue());
            XmlSchema xmlSchema = (XmlSchema) this.annotationReader.getPackageAnnotation(XmlSchema.class, entry.getKey(), null);
            if (xmlSchema != null && xmlSchema.xmlns() != null && xmlSchema.xmlns().length > 0) {
                if (this.xmlNsSet == null) {
                    this.xmlNsSet = new HashSet();
                }
                this.xmlNsSet.addAll(Arrays.asList(xmlSchema.xmlns()));
            }
            if (orCreate3.isElement()) {
                this.rootMap.put(((RuntimeClassInfo) entry.getValue()).getElementName(), orCreate3);
            }
            Iterator<QName> it6 = orCreate3.getTypeNames().iterator();
            while (it6.hasNext()) {
                this.typeMap.put(it6.next(), orCreate3);
            }
        }
        Iterator<? extends ElementInfo<Type, Class>> it7 = typeInfoSet.getAllElements().iterator();
        while (it7.hasNext()) {
            RuntimeElementInfo runtimeElementInfo = (RuntimeElementInfo) it7.next();
            ElementBeanInfoImpl orCreate4 = getOrCreate(runtimeElementInfo);
            if (runtimeElementInfo.getScope() == null) {
                this.rootMap.put(runtimeElementInfo.getElementName(), orCreate4);
            }
            ClassInfo<Type, Class> scope = runtimeElementInfo.getScope();
            Class clazz = scope == null ? null : scope.getClazz();
            Map<QName, ElementBeanInfoImpl> linkedHashMap = this.elements.get(clazz);
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap<>();
                this.elements.put(clazz, linkedHashMap);
            }
            linkedHashMap.put(runtimeElementInfo.getElementName(), orCreate4);
        }
        this.beanInfoMap.put(JAXBElement.class, new ElementBeanInfoImpl(this));
        this.beanInfoMap.put(CompositeStructure.class, new CompositeStructureBeanInfo(this));
        getOrCreate((RuntimeTypeInfo) typeInfoSet.getAnyTypeInfo());
        Iterator<JaxBeanInfo> it8 = this.beanInfos.values().iterator();
        while (it8.hasNext()) {
            it8.next().link(this);
        }
        for (Map.Entry<Class, Class> entry2 : RuntimeUtil.primitiveToBox.entrySet()) {
            this.beanInfoMap.put(entry2.getKey(), this.beanInfoMap.get(entry2.getValue()));
        }
        Navigator<Type, Class, Field, Method> navigator = typeInfoSet.getNavigator();
        for (TypeReference typeReference : collection) {
            XmlJavaTypeAdapter xmlJavaTypeAdapter = (XmlJavaTypeAdapter) typeReference.get(XmlJavaTypeAdapter.class);
            XmlList xmlList = (XmlList) typeReference.get(XmlList.class);
            Class cls = (Class) navigator.erasure(typeReference.type);
            Adapter adapter = xmlJavaTypeAdapter != null ? new Adapter(xmlJavaTypeAdapter.value(), navigator) : null;
            if (typeReference.get(XmlAttachmentRef.class) != null) {
                adapter = new Adapter(SwaRefAdapter.class, navigator);
                this.hasSwaRef = true;
            }
            cls = adapter != null ? (Class) navigator.erasure(adapter.defaultType) : cls;
            Name nameCreateElementName = this.nameBuilder.createElementName(typeReference.tagName);
            if (xmlList == null) {
                bridgeImpl = new BridgeImpl(this, nameCreateElementName, getBeanInfo(cls, true), typeReference);
            } else {
                bridgeImpl = new BridgeImpl(this, nameCreateElementName, new ValueListBeanInfoImpl(this, cls), typeReference);
            }
            if (adapter != null) {
                bridgeImpl = new BridgeAdapter(bridgeImpl, (Class) adapter.adapterType);
            }
            this.bridges.put(typeReference, bridgeImpl);
        }
        this.nameList = this.nameBuilder.conclude();
        Iterator<JaxBeanInfo> it9 = this.beanInfos.values().iterator();
        while (it9.hasNext()) {
            it9.next().wrapUp();
        }
        this.nameBuilder = null;
        this.beanInfos = null;
    }

    @Override // com.sun.xml.bind.api.JAXBRIContext
    public boolean hasSwaRef() {
        return this.hasSwaRef;
    }

    @Override // com.sun.xml.bind.api.JAXBRIContext
    public RuntimeTypeInfoSet getRuntimeTypeInfoSet() {
        try {
            return getTypeInfoSet();
        } catch (IllegalAnnotationsException e) {
            throw new AssertionError(e);
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet, java.lang.Object] */
    public RuntimeTypeInfoSet getTypeInfoSet() throws IllegalAnnotationsException {
        RuntimeTypeInfoSet runtimeTypeInfoSet;
        WeakReference<RuntimeTypeInfoSet> weakReference = this.typeInfoSetCache;
        if (weakReference != null && (runtimeTypeInfoSet = weakReference.get()) != null) {
            return runtimeTypeInfoSet;
        }
        RuntimeModelBuilder runtimeModelBuilder = new RuntimeModelBuilder(this, this.annotationReader, this.subclassReplacements, this.defaultNsUri);
        IllegalAnnotationsException.Builder builder = new IllegalAnnotationsException.Builder();
        runtimeModelBuilder.setErrorHandler(builder);
        for (Class cls : this.classes) {
            if (cls != CompositeStructure.class) {
                runtimeModelBuilder.getTypeInfo(new Ref(cls));
            }
        }
        this.hasSwaRef |= runtimeModelBuilder.hasSwaRef;
        ?? Link2 = runtimeModelBuilder.link();
        builder.check();
        this.typeInfoSetCache = new WeakReference<>(Link2);
        return Link2;
    }

    public ElementBeanInfoImpl getElement(Class cls, QName qName) {
        ElementBeanInfoImpl elementBeanInfoImpl;
        Map<QName, ElementBeanInfoImpl> map = this.elements.get(cls);
        return (map == null || (elementBeanInfoImpl = map.get(qName)) == null) ? this.elements.get(null).get(qName) : elementBeanInfoImpl;
    }

    private ElementBeanInfoImpl getOrCreate(RuntimeElementInfo runtimeElementInfo) {
        JaxBeanInfo jaxBeanInfo = this.beanInfos.get(runtimeElementInfo);
        if (jaxBeanInfo != null) {
            return (ElementBeanInfoImpl) jaxBeanInfo;
        }
        return new ElementBeanInfoImpl(this, runtimeElementInfo);
    }

    protected JaxBeanInfo getOrCreate(RuntimeEnumLeafInfo runtimeEnumLeafInfo) {
        JaxBeanInfo jaxBeanInfo = this.beanInfos.get(runtimeEnumLeafInfo);
        if (jaxBeanInfo != null) {
            return jaxBeanInfo;
        }
        LeafBeanInfoImpl leafBeanInfoImpl = new LeafBeanInfoImpl(this, runtimeEnumLeafInfo);
        this.beanInfoMap.put(leafBeanInfoImpl.jaxbType, leafBeanInfoImpl);
        return leafBeanInfoImpl;
    }

    protected ClassBeanInfoImpl getOrCreate(RuntimeClassInfo runtimeClassInfo) {
        ClassBeanInfoImpl classBeanInfoImpl = (ClassBeanInfoImpl) this.beanInfos.get(runtimeClassInfo);
        if (classBeanInfoImpl != null) {
            return classBeanInfoImpl;
        }
        ClassBeanInfoImpl classBeanInfoImpl2 = new ClassBeanInfoImpl(this, runtimeClassInfo);
        this.beanInfoMap.put(classBeanInfoImpl2.jaxbType, classBeanInfoImpl2);
        return classBeanInfoImpl2;
    }

    protected JaxBeanInfo getOrCreate(RuntimeArrayInfo runtimeArrayInfo) {
        JaxBeanInfo jaxBeanInfo = this.beanInfos.get(runtimeArrayInfo);
        if (jaxBeanInfo != null) {
            return jaxBeanInfo;
        }
        ArrayBeanInfoImpl arrayBeanInfoImpl = new ArrayBeanInfoImpl(this, runtimeArrayInfo);
        this.beanInfoMap.put(runtimeArrayInfo.getType2(), arrayBeanInfoImpl);
        return arrayBeanInfoImpl;
    }

    public JaxBeanInfo getOrCreate(RuntimeTypeInfo runtimeTypeInfo) {
        if (runtimeTypeInfo instanceof RuntimeElementInfo) {
            return getOrCreate((RuntimeElementInfo) runtimeTypeInfo);
        }
        if (runtimeTypeInfo instanceof RuntimeClassInfo) {
            return getOrCreate((RuntimeClassInfo) runtimeTypeInfo);
        }
        if (runtimeTypeInfo instanceof RuntimeLeafInfo) {
            return this.beanInfos.get(runtimeTypeInfo);
        }
        if (runtimeTypeInfo instanceof RuntimeArrayInfo) {
            return getOrCreate((RuntimeArrayInfo) runtimeTypeInfo);
        }
        if (runtimeTypeInfo.getType2() == Object.class) {
            JaxBeanInfo jaxBeanInfo = this.beanInfoMap.get(Object.class);
            if (jaxBeanInfo != null) {
                return jaxBeanInfo;
            }
            AnyTypeBeanInfo anyTypeBeanInfo = new AnyTypeBeanInfo(this, runtimeTypeInfo);
            this.beanInfoMap.put(Object.class, anyTypeBeanInfo);
            return anyTypeBeanInfo;
        }
        throw new IllegalArgumentException();
    }

    public final JaxBeanInfo getBeanInfo(Object obj) {
        for (Class<?> superclass = obj.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            JaxBeanInfo jaxBeanInfo = this.beanInfoMap.get(superclass);
            if (jaxBeanInfo != null) {
                return jaxBeanInfo;
            }
        }
        if (obj instanceof Element) {
            return this.beanInfoMap.get(Object.class);
        }
        for (Class<?> cls : obj.getClass().getInterfaces()) {
            JaxBeanInfo jaxBeanInfo2 = this.beanInfoMap.get(cls);
            if (jaxBeanInfo2 != null) {
                return jaxBeanInfo2;
            }
        }
        return null;
    }

    public final JaxBeanInfo getBeanInfo(Object obj, boolean z) throws JAXBException {
        JaxBeanInfo beanInfo = getBeanInfo(obj);
        if (beanInfo != null) {
            return beanInfo;
        }
        if (!z) {
            return null;
        }
        if (obj instanceof Document) {
            throw new JAXBException(Messages.ELEMENT_NEEDED_BUT_FOUND_DOCUMENT.format(obj.getClass()));
        }
        throw new JAXBException(Messages.UNKNOWN_CLASS.format(obj.getClass()));
    }

    public final <T> JaxBeanInfo<T> getBeanInfo(Class<T> cls) {
        return this.beanInfoMap.get(cls);
    }

    public final <T> JaxBeanInfo<T> getBeanInfo(Class<T> cls, boolean z) throws JAXBException {
        JaxBeanInfo<T> beanInfo = getBeanInfo((Class) cls);
        if (beanInfo != null) {
            return beanInfo;
        }
        if (z) {
            throw new JAXBException(cls.getName() + " is not known to this context");
        }
        return null;
    }

    public final Loader selectRootLoader(UnmarshallingContext.State state, TagName tagName) {
        JaxBeanInfo jaxBeanInfo = this.rootMap.get(tagName.uri, tagName.local);
        if (jaxBeanInfo == null) {
            return null;
        }
        return jaxBeanInfo.getLoader(this, true);
    }

    public JaxBeanInfo getGlobalType(QName qName) {
        return this.typeMap.get(qName);
    }

    public String getNearestTypeName(QName qName) {
        String[] strArr = new String[this.typeMap.size()];
        int i = 0;
        for (QName qName2 : this.typeMap.keySet()) {
            if (qName2.getLocalPart().equals(qName.getLocalPart())) {
                return qName2.toString();
            }
            strArr[i] = qName2.toString();
            i++;
        }
        String strFindNearest = EditDistance.findNearest(qName.toString(), strArr);
        if (EditDistance.editDistance(strFindNearest, qName.toString()) > 10) {
            return null;
        }
        return strFindNearest;
    }

    public Set<QName> getValidRootNames() {
        TreeSet treeSet = new TreeSet(QNAME_COMPARATOR);
        Iterator<QNameMap.Entry<JaxBeanInfo>> it = this.rootMap.entrySet().iterator();
        while (it.hasNext()) {
            treeSet.add(it.next().createQName());
        }
        return treeSet;
    }

    public synchronized Encoded[] getUTF8NameTable() {
        if (this.utf8nameTable == null) {
            int length = this.nameList.localNames.length;
            Encoded[] encodedArr = new Encoded[length];
            for (int i = 0; i < length; i++) {
                Encoded encoded = new Encoded(this.nameList.localNames[i]);
                encoded.compact();
                encodedArr[i] = encoded;
            }
            this.utf8nameTable = encodedArr;
        }
        return this.utf8nameTable;
    }

    public int getNumberOfLocalNames() {
        return this.nameList.localNames.length;
    }

    public int getNumberOfElementNames() {
        return this.nameList.numberOfElementNames;
    }

    public int getNumberOfAttributeNames() {
        return this.nameList.numberOfAttributeNames;
    }

    static Transformer createTransformer(boolean z) {
        try {
            return ((SAXTransformerFactory) XmlFactory.createTransformerFactory(z)).newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new Error(e);
        }
    }

    public static TransformerHandler createTransformerHandler(boolean z) {
        try {
            return ((SAXTransformerFactory) XmlFactory.createTransformerFactory(z)).newTransformerHandler();
        } catch (TransformerConfigurationException e) {
            throw new Error(e);
        }
    }

    static Document createDom(boolean z) {
        Document documentNewDocument;
        synchronized (JAXBContextImpl.class) {
            if (db == null) {
                try {
                    db = XmlFactory.createDocumentBuilderFactory(z).newDocumentBuilder();
                } catch (ParserConfigurationException e) {
                    throw new FactoryConfigurationError(e);
                }
            }
            documentNewDocument = db.newDocument();
        }
        return documentNewDocument;
    }

    @Override // javax.xml.bind.JAXBContext
    public MarshallerImpl createMarshaller() {
        return new MarshallerImpl(this, null);
    }

    @Override // javax.xml.bind.JAXBContext
    public UnmarshallerImpl createUnmarshaller() {
        return new UnmarshallerImpl(this, null);
    }

    @Override // javax.xml.bind.JAXBContext
    public Validator createValidator() {
        throw new UnsupportedOperationException(Messages.NOT_IMPLEMENTED_IN_2_0.format(new Object[0]));
    }

    @Override // javax.xml.bind.JAXBContext
    public JAXBIntrospector createJAXBIntrospector() {
        return new JAXBIntrospector() { // from class: com.sun.xml.bind.v2.runtime.JAXBContextImpl.3
            @Override // javax.xml.bind.JAXBIntrospector
            public boolean isElement(Object obj) {
                return getElementName(obj) != null;
            }

            @Override // javax.xml.bind.JAXBIntrospector
            public QName getElementName(Object obj) {
                try {
                    return JAXBContextImpl.this.getElementName(obj);
                } catch (JAXBException unused) {
                    return null;
                }
            }
        };
    }

    private NonElement<Type, Class> getXmlType(RuntimeTypeInfoSet runtimeTypeInfoSet, TypeReference typeReference) {
        if (typeReference == null) {
            throw new IllegalArgumentException();
        }
        return runtimeTypeInfoSet.getTypeInfo(new Ref(this.annotationReader, runtimeTypeInfoSet.getNavigator(), typeReference.type, (XmlJavaTypeAdapter) typeReference.get(XmlJavaTypeAdapter.class), (XmlList) typeReference.get(XmlList.class)));
    }

    @Override // com.sun.xml.bind.api.JAXBRIContext
    public void generateEpisode(Result result) {
        if (result == null) {
            throw new IllegalArgumentException();
        }
        createSchemaGenerator().writeEpisodeFile(ResultFactory.createSerializer(result));
    }

    @Override // com.sun.xml.bind.api.JAXBRIContext, javax.xml.bind.JAXBContext
    public void generateSchema(SchemaOutputResolver schemaOutputResolver) throws IOException {
        if (schemaOutputResolver == null) {
            throw new IOException(Messages.NULL_OUTPUT_RESOLVER.format(new Object[0]));
        }
        final SAXParseException[] sAXParseExceptionArr = new SAXParseException[1];
        final SAXParseException[] sAXParseExceptionArr2 = new SAXParseException[1];
        createSchemaGenerator().write(schemaOutputResolver, new ErrorListener() { // from class: com.sun.xml.bind.v2.runtime.JAXBContextImpl.4
            @Override // com.sun.xml.bind.api.ErrorListener
            public void info(SAXParseException sAXParseException) {
            }

            @Override // com.sun.xml.bind.api.ErrorListener, org.xml.sax.ErrorHandler
            public void error(SAXParseException sAXParseException) {
                sAXParseExceptionArr[0] = sAXParseException;
            }

            @Override // com.sun.xml.bind.api.ErrorListener, org.xml.sax.ErrorHandler
            public void fatalError(SAXParseException sAXParseException) {
                sAXParseExceptionArr[0] = sAXParseException;
            }

            @Override // com.sun.xml.bind.api.ErrorListener, org.xml.sax.ErrorHandler
            public void warning(SAXParseException sAXParseException) {
                sAXParseExceptionArr2[0] = sAXParseException;
            }
        });
        if (sAXParseExceptionArr[0] != null) {
            IOException iOException = new IOException(Messages.FAILED_TO_GENERATE_SCHEMA.format(new Object[0]));
            iOException.initCause(sAXParseExceptionArr[0]);
            throw iOException;
        }
        if (sAXParseExceptionArr2[0] == null) {
            return;
        }
        IOException iOException2 = new IOException(Messages.ERROR_PROCESSING_SCHEMA.format(new Object[0]));
        iOException2.initCause(sAXParseExceptionArr2[0]);
        throw iOException2;
    }

    private XmlSchemaGenerator<Type, Class, Field, Method> createSchemaGenerator() {
        try {
            RuntimeTypeInfoSet typeInfoSet = getTypeInfoSet();
            XmlSchemaGenerator<Type, Class, Field, Method> xmlSchemaGenerator = new XmlSchemaGenerator<>(typeInfoSet.getNavigator(), typeInfoSet);
            HashSet hashSet = new HashSet();
            Iterator<? extends ElementInfo<Type, Class>> it = typeInfoSet.getAllElements().iterator();
            while (it.hasNext()) {
                hashSet.add(((RuntimeElementInfo) it.next()).getElementName());
            }
            Iterator<? extends ClassInfo<Type, Class>> it2 = typeInfoSet.beans().values().iterator();
            while (it2.hasNext()) {
                RuntimeClassInfo runtimeClassInfo = (RuntimeClassInfo) it2.next();
                if (runtimeClassInfo.isElement()) {
                    hashSet.add(runtimeClassInfo.asElement().getElementName());
                }
            }
            for (TypeReference typeReference : this.bridges.keySet()) {
                if (!hashSet.contains(typeReference.tagName)) {
                    if (typeReference.type == Void.TYPE || typeReference.type == Void.class) {
                        xmlSchemaGenerator.add(typeReference.tagName, false, null);
                    } else if (typeReference.type != CompositeStructure.class) {
                        xmlSchemaGenerator.add(typeReference.tagName, !typeInfoSet.getNavigator().isPrimitive(typeReference.type), getXmlType(typeInfoSet, typeReference));
                    }
                }
            }
            return xmlSchemaGenerator;
        } catch (IllegalAnnotationsException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.sun.xml.bind.api.JAXBRIContext
    public QName getTypeName(TypeReference typeReference) {
        try {
            NonElement<Type, Class> xmlType = getXmlType(getTypeInfoSet(), typeReference);
            if (xmlType == null) {
                throw new IllegalArgumentException();
            }
            return xmlType.getTypeName();
        } catch (IllegalAnnotationsException e) {
            throw new AssertionError(e);
        }
    }

    @Override // javax.xml.bind.JAXBContext
    public <T> Binder<T> createBinder(Class<T> cls) {
        if (cls == Node.class) {
            return (Binder<T>) createBinder();
        }
        return super.createBinder(cls);
    }

    @Override // javax.xml.bind.JAXBContext
    public Binder<Node> createBinder() {
        return new BinderImpl(this, new DOMScanner());
    }

    @Override // com.sun.xml.bind.api.JAXBRIContext
    public QName getElementName(Object obj) throws JAXBException {
        JaxBeanInfo beanInfo = getBeanInfo(obj, true);
        if (beanInfo.isElement()) {
            return new QName(beanInfo.getElementNamespaceURI(obj), beanInfo.getElementLocalName(obj));
        }
        return null;
    }

    @Override // com.sun.xml.bind.api.JAXBRIContext
    public QName getElementName(Class cls) throws JAXBException {
        JaxBeanInfo beanInfo = getBeanInfo(cls, true);
        if (beanInfo.isElement()) {
            return new QName(beanInfo.getElementNamespaceURI(cls), beanInfo.getElementLocalName(cls));
        }
        return null;
    }

    @Override // com.sun.xml.bind.api.JAXBRIContext
    public Bridge createBridge(TypeReference typeReference) {
        return this.bridges.get(typeReference);
    }

    @Override // com.sun.xml.bind.api.JAXBRIContext
    public BridgeContext createBridgeContext() {
        return new BridgeContextImpl(this);
    }

    @Override // com.sun.xml.bind.api.JAXBRIContext
    public RawAccessor getElementPropertyAccessor(Class cls, String str, String str2) throws JAXBException {
        if (!(getBeanInfo(cls, true) instanceof ClassBeanInfoImpl)) {
            throw new JAXBException(cls + " is not a bean");
        }
        for (ClassBeanInfoImpl classBeanInfoImpl = (ClassBeanInfoImpl) r0; classBeanInfoImpl != null; classBeanInfoImpl = classBeanInfoImpl.superClazz) {
            for (Property property : classBeanInfoImpl.properties) {
                final Accessor elementPropertyAccessor = property.getElementPropertyAccessor(str, str2);
                if (elementPropertyAccessor != null) {
                    return new RawAccessor() { // from class: com.sun.xml.bind.v2.runtime.JAXBContextImpl.5
                        @Override // com.sun.xml.bind.api.RawAccessor
                        public Object get(Object obj) throws AccessorException {
                            return elementPropertyAccessor.getUnadapted(obj);
                        }

                        @Override // com.sun.xml.bind.api.RawAccessor
                        public void set(Object obj, Object obj2) throws AccessorException {
                            elementPropertyAccessor.setUnadapted(obj, obj2);
                        }
                    };
                }
            }
        }
        throw new JAXBException(new QName(str, str2) + " is not a valid property on " + cls);
    }

    @Override // com.sun.xml.bind.api.JAXBRIContext
    public List<String> getKnownNamespaceURIs() {
        return Arrays.asList(this.nameList.namespaceURIs);
    }

    @Override // com.sun.xml.bind.api.JAXBRIContext
    public String getBuildId() {
        Package r0 = getClass().getPackage();
        if (r0 == null) {
            return null;
        }
        return r0.getImplementationVersion();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(Which.which(getClass()) + " Build-Id: " + getBuildId());
        sb.append("\nClasses known to this context:\n");
        TreeSet treeSet = new TreeSet();
        Iterator<Class> it = this.beanInfoMap.keySet().iterator();
        while (it.hasNext()) {
            treeSet.add(it.next().getName());
        }
        Iterator it2 = treeSet.iterator();
        while (it2.hasNext()) {
            sb.append("  ").append((String) it2.next()).append('\n');
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public String getXMIMEContentType(Object obj) {
        JaxBeanInfo beanInfo = getBeanInfo(obj);
        if (!(beanInfo instanceof ClassBeanInfoImpl)) {
            return null;
        }
        for (Object[] objArr : ((ClassBeanInfoImpl) beanInfo).properties) {
            if (objArr instanceof AttributeProperty) {
                AttributeProperty attributeProperty = (AttributeProperty) objArr;
                if (attributeProperty.attName.equals(WellKnownNamespace.XML_MIME_URI, "contentType")) {
                    try {
                        return (String) attributeProperty.xacc.print((BeanT) obj);
                    } catch (AccessorException | ClassCastException | SAXException unused) {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    public JAXBContextImpl createAugmented(Class<?> cls) throws JAXBException {
        Class[] clsArr = this.classes;
        Class[] clsArr2 = new Class[clsArr.length + 1];
        System.arraycopy(clsArr, 0, clsArr2, 0, clsArr.length);
        clsArr2[this.classes.length] = cls;
        JAXBContextBuilder jAXBContextBuilder = new JAXBContextBuilder(this);
        jAXBContextBuilder.setClasses(clsArr2);
        return jAXBContextBuilder.build();
    }

    public static class JAXBContextBuilder {
        private boolean allNillable;
        private RuntimeAnnotationReader annotationReader;
        private Boolean backupWithParentNamespace;
        private boolean c14nSupport;
        private Class[] classes;
        private String defaultNsUri;
        private boolean disableSecurityProcessing;
        private boolean improvedXsiTypeHandling;
        private boolean retainPropertyInfo;
        private Map<Class, Class> subclassReplacements;
        private boolean supressAccessorWarnings;
        private Collection<TypeReference> typeRefs;
        private boolean xmlAccessorFactorySupport;

        public JAXBContextBuilder() {
            this.retainPropertyInfo = false;
            this.supressAccessorWarnings = false;
            this.defaultNsUri = "";
            this.annotationReader = new RuntimeInlineAnnotationReader();
            this.subclassReplacements = Collections.emptyMap();
            this.c14nSupport = false;
            this.xmlAccessorFactorySupport = false;
            this.improvedXsiTypeHandling = true;
            this.disableSecurityProcessing = true;
            this.backupWithParentNamespace = null;
        }

        public JAXBContextBuilder(JAXBContextImpl jAXBContextImpl) {
            this.retainPropertyInfo = false;
            this.supressAccessorWarnings = false;
            this.defaultNsUri = "";
            this.annotationReader = new RuntimeInlineAnnotationReader();
            this.subclassReplacements = Collections.emptyMap();
            this.c14nSupport = false;
            this.xmlAccessorFactorySupport = false;
            this.improvedXsiTypeHandling = true;
            this.disableSecurityProcessing = true;
            this.backupWithParentNamespace = null;
            this.supressAccessorWarnings = jAXBContextImpl.supressAccessorWarnings;
            this.retainPropertyInfo = jAXBContextImpl.retainPropertyInfo;
            this.defaultNsUri = jAXBContextImpl.defaultNsUri;
            this.annotationReader = jAXBContextImpl.annotationReader;
            this.subclassReplacements = jAXBContextImpl.subclassReplacements;
            this.c14nSupport = jAXBContextImpl.c14nSupport;
            this.classes = jAXBContextImpl.classes;
            this.typeRefs = jAXBContextImpl.bridges.keySet();
            this.xmlAccessorFactorySupport = jAXBContextImpl.xmlAccessorFactorySupport;
            this.allNillable = jAXBContextImpl.allNillable;
            this.disableSecurityProcessing = jAXBContextImpl.disableSecurityProcessing;
            this.backupWithParentNamespace = jAXBContextImpl.backupWithParentNamespace;
        }

        public JAXBContextBuilder setRetainPropertyInfo(boolean z) {
            this.retainPropertyInfo = z;
            return this;
        }

        public JAXBContextBuilder setSupressAccessorWarnings(boolean z) {
            this.supressAccessorWarnings = z;
            return this;
        }

        public JAXBContextBuilder setC14NSupport(boolean z) {
            this.c14nSupport = z;
            return this;
        }

        public JAXBContextBuilder setXmlAccessorFactorySupport(boolean z) {
            this.xmlAccessorFactorySupport = z;
            return this;
        }

        public JAXBContextBuilder setDefaultNsUri(String str) {
            this.defaultNsUri = str;
            return this;
        }

        public JAXBContextBuilder setAllNillable(boolean z) {
            this.allNillable = z;
            return this;
        }

        public JAXBContextBuilder setClasses(Class[] clsArr) {
            this.classes = clsArr;
            return this;
        }

        public JAXBContextBuilder setAnnotationReader(RuntimeAnnotationReader runtimeAnnotationReader) {
            this.annotationReader = runtimeAnnotationReader;
            return this;
        }

        public JAXBContextBuilder setSubclassReplacements(Map<Class, Class> map) {
            this.subclassReplacements = map;
            return this;
        }

        public JAXBContextBuilder setTypeRefs(Collection<TypeReference> collection) {
            this.typeRefs = collection;
            return this;
        }

        public JAXBContextBuilder setImprovedXsiTypeHandling(boolean z) {
            this.improvedXsiTypeHandling = z;
            return this;
        }

        public JAXBContextBuilder setDisableSecurityProcessing(boolean z) {
            this.disableSecurityProcessing = z;
            return this;
        }

        public JAXBContextBuilder setBackupWithParentNamespace(Boolean bool) {
            this.backupWithParentNamespace = bool;
            return this;
        }

        public JAXBContextImpl build() throws JAXBException {
            if (this.defaultNsUri == null) {
                this.defaultNsUri = "";
            }
            if (this.subclassReplacements == null) {
                this.subclassReplacements = Collections.emptyMap();
            }
            if (this.annotationReader == null) {
                this.annotationReader = new RuntimeInlineAnnotationReader();
            }
            if (this.typeRefs == null) {
                this.typeRefs = Collections.emptyList();
            }
            return new JAXBContextImpl(this);
        }
    }
}
