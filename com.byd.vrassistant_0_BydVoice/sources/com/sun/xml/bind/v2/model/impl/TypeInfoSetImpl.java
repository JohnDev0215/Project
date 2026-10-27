package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.annotation.AnnotationReader;
import com.sun.xml.bind.v2.model.core.BuiltinLeafInfo;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.Ref;
import com.sun.xml.bind.v2.model.core.TypeInfoSet;
import com.sun.xml.bind.v2.model.nav.Navigator;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import com.sun.xml.bind.v2.runtime.RuntimeUtil;
import com.sun.xml.bind.v2.util.FlattenIterator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;

/* JADX INFO: loaded from: classes3.dex */
class TypeInfoSetImpl<T, C, F, M> implements TypeInfoSet<T, C, F, M> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Iterable<? extends ElementInfoImpl<T, C, F, M>> allElements;
    private final NonElement<T, C> anyType;
    private final Map<T, ArrayInfoImpl<T, C, F, M>> arrays;

    @XmlJavaTypeAdapter(RuntimeUtil.ToStringAdapter.class)
    private final Map<C, ClassInfoImpl<T, C, F, M>> beans;

    @XmlTransient
    private final Map<C, ClassInfoImpl<T, C, F, M>> beansView;
    private final Map<T, BuiltinLeafInfo<T, C>> builtins;
    private final Map<C, Map<QName, ElementInfoImpl<T, C, F, M>>> elementMappings;
    private final Map<C, EnumLeafInfoImpl<T, C, F, M>> enums;

    @XmlTransient
    public final Navigator<T, C, F, M> nav;

    @XmlTransient
    public final AnnotationReader<T, C, F, M> reader;
    private Map<String, Map<String, String>> xmlNsCache;

    public TypeInfoSetImpl(Navigator<T, C, F, M> navigator, AnnotationReader<T, C, F, M> annotationReader, Map<T, ? extends BuiltinLeafInfoImpl<T, C>> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.builtins = linkedHashMap;
        this.enums = new LinkedHashMap();
        this.arrays = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        this.beans = linkedHashMap2;
        this.beansView = Collections.unmodifiableMap(linkedHashMap2);
        this.elementMappings = new LinkedHashMap();
        this.allElements = new Iterable<ElementInfoImpl<T, C, F, M>>() { // from class: com.sun.xml.bind.v2.model.impl.TypeInfoSetImpl.1
            @Override // java.lang.Iterable
            public Iterator<ElementInfoImpl<T, C, F, M>> iterator() {
                return new FlattenIterator(TypeInfoSetImpl.this.elementMappings.values());
            }
        };
        this.nav = navigator;
        this.reader = annotationReader;
        linkedHashMap.putAll(map);
        this.anyType = createAnyType();
        for (Map.Entry<Class, Class> entry : RuntimeUtil.primitiveToBox.entrySet()) {
            this.builtins.put(navigator.getPrimitive(entry.getKey()), map.get(navigator.ref(entry.getValue())));
        }
        this.elementMappings.put(null, new LinkedHashMap());
    }

    protected NonElement<T, C> createAnyType() {
        return new AnyTypeImpl(this.nav);
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet
    public Navigator<T, C, F, M> getNavigator() {
        return this.nav;
    }

    public void add(ClassInfoImpl<T, C, F, M> classInfoImpl) {
        this.beans.put(classInfoImpl.getClazz(), classInfoImpl);
    }

    public void add(EnumLeafInfoImpl<T, C, F, M> enumLeafInfoImpl) {
        this.enums.put(enumLeafInfoImpl.clazz, enumLeafInfoImpl);
    }

    public void add(ArrayInfoImpl<T, C, F, M> arrayInfoImpl) {
        this.arrays.put(arrayInfoImpl.getType2(), arrayInfoImpl);
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet
    public NonElement<T, C> getTypeInfo(T t) {
        T tErasure = this.nav.erasure(t);
        BuiltinLeafInfo<T, C> builtinLeafInfo = this.builtins.get(tErasure);
        if (builtinLeafInfo != null) {
            return builtinLeafInfo;
        }
        if (this.nav.isArray(tErasure)) {
            return this.arrays.get(tErasure);
        }
        C cAsDecl = this.nav.asDecl(tErasure);
        if (cAsDecl == null) {
            return null;
        }
        return getClassInfo(cAsDecl);
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet
    public NonElement<T, C> getAnyTypeInfo() {
        return this.anyType;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet
    public NonElement<T, C> getTypeInfo(Ref<T, C> ref) {
        C cAsDecl = this.nav.asDecl(ref.type);
        if (cAsDecl == null || this.reader.getClassAnnotation(XmlRegistry.class, cAsDecl, null) == null) {
            return getTypeInfo(ref.type);
        }
        return null;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet
    public Map<C, ? extends ClassInfoImpl<T, C, F, M>> beans() {
        return this.beansView;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet
    public Map<T, ? extends BuiltinLeafInfo<T, C>> builtins() {
        return this.builtins;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet
    public Map<C, ? extends EnumLeafInfoImpl<T, C, F, M>> enums() {
        return this.enums;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet
    public Map<? extends T, ? extends ArrayInfoImpl<T, C, F, M>> arrays() {
        return this.arrays;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet
    public NonElement<T, C> getClassInfo(C c) {
        BuiltinLeafInfo<T, C> builtinLeafInfo = this.builtins.get(this.nav.use(c));
        if (builtinLeafInfo != null) {
            return builtinLeafInfo;
        }
        EnumLeafInfoImpl<T, C, F, M> enumLeafInfoImpl = this.enums.get(c);
        if (enumLeafInfoImpl != null) {
            return enumLeafInfoImpl;
        }
        if (this.nav.asDecl(Object.class).equals(c)) {
            return this.anyType;
        }
        return this.beans.get(c);
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet
    public ElementInfoImpl<T, C, F, M> getElementInfo(C c, QName qName) {
        ElementInfoImpl<T, C, F, M> elementInfoImpl;
        while (c != null) {
            Map<QName, ElementInfoImpl<T, C, F, M>> map = this.elementMappings.get(c);
            if (map != null && (elementInfoImpl = map.get(qName)) != null) {
                return elementInfoImpl;
            }
            c = this.nav.getSuperClass(c);
        }
        return this.elementMappings.get(null).get(qName);
    }

    public final void add(ElementInfoImpl<T, C, F, M> elementInfoImpl, ModelBuilder<T, C, F, M> modelBuilder) {
        C clazz = elementInfoImpl.getScope() != null ? elementInfoImpl.getScope().getClazz() : null;
        Map<QName, ElementInfoImpl<T, C, F, M>> map = this.elementMappings.get(clazz);
        if (map == null) {
            Map<C, Map<QName, ElementInfoImpl<T, C, F, M>>> map2 = this.elementMappings;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            map2.put(clazz, linkedHashMap);
            map = linkedHashMap;
        }
        ElementInfoImpl<T, C, F, M> elementInfoImplPut = map.put(elementInfoImpl.getElementName(), elementInfoImpl);
        if (elementInfoImplPut != null) {
            QName elementName = elementInfoImpl.getElementName();
            modelBuilder.reportError(new IllegalAnnotationException(Messages.CONFLICTING_XML_ELEMENT_MAPPING.format(elementName.getNamespaceURI(), elementName.getLocalPart()), elementInfoImpl, elementInfoImplPut));
        }
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet
    public Map<QName, ? extends ElementInfoImpl<T, C, F, M>> getElementMappings(C c) {
        return this.elementMappings.get(c);
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet
    public Iterable<? extends ElementInfoImpl<T, C, F, M>> getAllElements() {
        return this.allElements;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet
    public Map<String, String> getXmlNs(String str) {
        if (this.xmlNsCache == null) {
            this.xmlNsCache = new HashMap();
            Iterator<? extends ClassInfoImpl<T, C, F, M>> it = beans().values().iterator();
            while (it.hasNext()) {
                XmlSchema xmlSchema = (XmlSchema) this.reader.getPackageAnnotation(XmlSchema.class, it.next().getClazz(), null);
                if (xmlSchema != null) {
                    String strNamespace = xmlSchema.namespace();
                    Map<String, String> map = this.xmlNsCache.get(strNamespace);
                    if (map == null) {
                        Map<String, Map<String, String>> map2 = this.xmlNsCache;
                        HashMap map3 = new HashMap();
                        map2.put(strNamespace, map3);
                        map = map3;
                    }
                    for (XmlNs xmlNs : xmlSchema.xmlns()) {
                        map.put(xmlNs.prefix(), xmlNs.namespaceURI());
                    }
                }
            }
        }
        Map<String, String> map4 = this.xmlNsCache.get(str);
        return map4 != null ? map4 : Collections.emptyMap();
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet
    public Map<String, String> getSchemaLocations() {
        HashMap map = new HashMap();
        Iterator<? extends ClassInfoImpl<T, C, F, M>> it = beans().values().iterator();
        while (it.hasNext()) {
            XmlSchema xmlSchema = (XmlSchema) this.reader.getPackageAnnotation(XmlSchema.class, it.next().getClazz(), null);
            if (xmlSchema != null) {
                String strLocation = xmlSchema.location();
                if (!strLocation.equals(XmlSchema.NO_LOCATION)) {
                    map.put(xmlSchema.namespace(), strLocation);
                }
            }
        }
        return map;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet
    public final XmlNsForm getElementFormDefault(String str) {
        XmlNsForm xmlNsFormElementFormDefault;
        Iterator<? extends ClassInfoImpl<T, C, F, M>> it = beans().values().iterator();
        while (it.hasNext()) {
            XmlSchema xmlSchema = (XmlSchema) this.reader.getPackageAnnotation(XmlSchema.class, it.next().getClazz(), null);
            if (xmlSchema != null && xmlSchema.namespace().equals(str) && (xmlNsFormElementFormDefault = xmlSchema.elementFormDefault()) != XmlNsForm.UNSET) {
                return xmlNsFormElementFormDefault;
            }
        }
        return XmlNsForm.UNSET;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet
    public final XmlNsForm getAttributeFormDefault(String str) {
        XmlNsForm xmlNsFormAttributeFormDefault;
        Iterator<? extends ClassInfoImpl<T, C, F, M>> it = beans().values().iterator();
        while (it.hasNext()) {
            XmlSchema xmlSchema = (XmlSchema) this.reader.getPackageAnnotation(XmlSchema.class, it.next().getClazz(), null);
            if (xmlSchema != null && xmlSchema.namespace().equals(str) && (xmlNsFormAttributeFormDefault = xmlSchema.attributeFormDefault()) != XmlNsForm.UNSET) {
                return xmlNsFormAttributeFormDefault;
            }
        }
        return XmlNsForm.UNSET;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfoSet
    public void dump(Result result) throws JAXBException {
        JAXBContext.newInstance((Class<?>[]) new Class[]{getClass()}).createMarshaller().marshal(this, result);
    }
}
