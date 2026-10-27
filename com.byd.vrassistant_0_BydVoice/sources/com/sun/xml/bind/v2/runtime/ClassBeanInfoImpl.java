package com.sun.xml.bind.v2.runtime;

import com.sun.istack.FinalArrayList;
import com.sun.xml.bind.Util;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.ClassFactory;
import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeClassInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo;
import com.sun.xml.bind.v2.runtime.property.AttributeProperty;
import com.sun.xml.bind.v2.runtime.property.Property;
import com.sun.xml.bind.v2.runtime.property.PropertyFactory;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.StructureLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiTypeLoader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.helpers.ValidationEventImpl;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.LocatorImpl;

/* JADX INFO: loaded from: classes3.dex */
public final class ClassBeanInfoImpl<BeanT> extends JaxBeanInfo<BeanT> implements AttributeAccessor<BeanT> {
    private static final AttributeProperty[] EMPTY_PROPERTIES = new AttributeProperty[0];
    private static final Logger logger = Util.getClassLogger();
    private AttributeProperty<BeanT>[] attributeProperties;
    private RuntimeClassInfo ci;
    private final Method factoryMethod;
    private Property<? super BeanT> idProperty;
    private final Accessor<? super BeanT, Map<QName, String>> inheritedAttWildcard;
    private Loader loader;
    private Loader loaderWithTypeSubst;
    public final Property<BeanT>[] properties;
    private boolean retainPropertyInfo;
    public final ClassBeanInfoImpl<? super BeanT> superClazz;
    private final Name tagName;
    private Property<BeanT>[] uriProperties;
    private final Transducer<BeanT> xducer;
    private final Accessor<? super BeanT, Locator> xmlLocatorField;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.sun.xml.bind.v2.model.runtime.RuntimeClassInfo] */
    ClassBeanInfoImpl(JAXBContextImpl jAXBContextImpl, RuntimeClassInfo runtimeClassInfo) {
        Accessor<? super Object, Locator> accessor;
        super(jAXBContextImpl, (RuntimeTypeInfo) runtimeClassInfo, runtimeClassInfo.getClazz(), runtimeClassInfo.getTypeName(), runtimeClassInfo.isElement(), false, true);
        int i = 0;
        this.retainPropertyInfo = false;
        this.ci = runtimeClassInfo;
        this.inheritedAttWildcard = runtimeClassInfo.getAttributeWildcard();
        this.xducer = runtimeClassInfo.getTransducer();
        Method factoryMethod = runtimeClassInfo.getFactoryMethod();
        this.factoryMethod = factoryMethod;
        this.retainPropertyInfo = jAXBContextImpl.retainPropertyInfo;
        boolean zElementOnlyContent = true;
        if (factoryMethod != null && (!Modifier.isPublic(factoryMethod.getDeclaringClass().getModifiers()) || !Modifier.isPublic(factoryMethod.getModifiers()))) {
            try {
                factoryMethod.setAccessible(true);
            } catch (SecurityException e) {
                logger.log(Level.FINE, "Unable to make the method of " + this.factoryMethod + " accessible", (Throwable) e);
                throw e;
            }
        }
        if (runtimeClassInfo.getBaseClass2() == null) {
            this.superClazz = null;
        } else {
            this.superClazz = jAXBContextImpl.getOrCreate((RuntimeClassInfo) runtimeClassInfo.getBaseClass2());
        }
        ClassBeanInfoImpl<? super BeanT> classBeanInfoImpl = this.superClazz;
        if (classBeanInfoImpl != null && (accessor = classBeanInfoImpl.xmlLocatorField) != null) {
            this.xmlLocatorField = accessor;
        } else {
            this.xmlLocatorField = runtimeClassInfo.getLocatorField();
        }
        List<? extends PropertyInfo<Type, Class>> properties = runtimeClassInfo.getProperties();
        this.properties = new Property[properties.size()];
        Iterator<? extends PropertyInfo<Type, Class>> it = properties.iterator();
        while (it.hasNext()) {
            RuntimePropertyInfo runtimePropertyInfo = (RuntimePropertyInfo) it.next();
            Property<? super BeanT> propertyCreate = PropertyFactory.create(jAXBContextImpl, runtimePropertyInfo);
            if (runtimePropertyInfo.id() == ID.ID) {
                this.idProperty = propertyCreate;
            }
            this.properties[i] = propertyCreate;
            zElementOnlyContent &= runtimePropertyInfo.elementOnlyContent();
            checkOverrideProperties(propertyCreate);
            i++;
        }
        hasElementOnlyContentModel(zElementOnlyContent);
        if (runtimeClassInfo.isElement()) {
            this.tagName = jAXBContextImpl.nameBuilder.createElementName(runtimeClassInfo.getElementName());
        } else {
            this.tagName = null;
        }
        setLifecycleFlags();
    }

    private void checkOverrideProperties(Property property) {
        Property<BeanT>[] propertyArr;
        String fieldName;
        ClassBeanInfoImpl classBeanInfoImpl = this;
        while (true) {
            classBeanInfoImpl = classBeanInfoImpl.superClazz;
            if (classBeanInfoImpl == null || (propertyArr = classBeanInfoImpl.properties) == null) {
                return;
            }
            for (Property<BeanT> property2 : propertyArr) {
                if (property2 != null && (fieldName = property2.getFieldName()) != null && fieldName.equals(property.getFieldName())) {
                    property2.setHiddenByOverride(true);
                }
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    protected void link(JAXBContextImpl jAXBContextImpl) {
        if (this.uriProperties != null) {
            return;
        }
        super.link(jAXBContextImpl);
        ClassBeanInfoImpl<? super BeanT> classBeanInfoImpl = this.superClazz;
        if (classBeanInfoImpl != null) {
            classBeanInfoImpl.link(jAXBContextImpl);
        }
        getLoader(jAXBContextImpl, true);
        ClassBeanInfoImpl<? super BeanT> classBeanInfoImpl2 = this.superClazz;
        if (classBeanInfoImpl2 != null) {
            if (this.idProperty == null) {
                this.idProperty = classBeanInfoImpl2.idProperty;
            }
            if (!classBeanInfoImpl2.hasElementOnlyContentModel()) {
                hasElementOnlyContentModel(false);
            }
        }
        FinalArrayList finalArrayList = new FinalArrayList();
        FinalArrayList finalArrayList2 = new FinalArrayList();
        for (ClassBeanInfoImpl classBeanInfoImpl3 = this; classBeanInfoImpl3 != null; classBeanInfoImpl3 = classBeanInfoImpl3.superClazz) {
            int i = 0;
            while (true) {
                Property<BeanT>[] propertyArr = classBeanInfoImpl3.properties;
                if (i < propertyArr.length) {
                    Property<BeanT> property = propertyArr[i];
                    if (property instanceof AttributeProperty) {
                        finalArrayList.add((AttributeProperty) property);
                    }
                    if (property.hasSerializeURIAction()) {
                        finalArrayList2.add(property);
                    }
                    i++;
                }
            }
        }
        if (jAXBContextImpl.c14nSupport) {
            Collections.sort(finalArrayList);
        }
        if (finalArrayList.isEmpty()) {
            this.attributeProperties = EMPTY_PROPERTIES;
        } else {
            this.attributeProperties = (AttributeProperty[]) finalArrayList.toArray(new AttributeProperty[finalArrayList.size()]);
        }
        if (finalArrayList2.isEmpty()) {
            this.uriProperties = EMPTY_PROPERTIES;
        } else {
            this.uriProperties = (Property[]) finalArrayList2.toArray(new Property[finalArrayList2.size()]);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void wrapUp() {
        for (Property<BeanT> property : this.properties) {
            property.wrapUp();
        }
        this.ci = null;
        super.wrapUp();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public String getElementNamespaceURI(BeanT beant) {
        return this.tagName.nsUri;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public String getElementLocalName(BeanT beant) {
        return this.tagName.localName;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public BeanT createInstance(UnmarshallingContext unmarshallingContext) throws IllegalAccessException, SAXException, InstantiationException, InvocationTargetException {
        BeanT beant;
        Method method = this.factoryMethod;
        if (method == null) {
            beant = (Object) ClassFactory.create0(this.jaxbType);
        } else {
            beant = (Object) ClassFactory.create(method);
            if (!this.jaxbType.isInstance(beant)) {
                throw new InstantiationException("The factory method didn't return a correct object");
            }
        }
        Accessor<? super BeanT, Locator> accessor = this.xmlLocatorField;
        if (accessor != null) {
            try {
                accessor.set(beant, new LocatorImpl(unmarshallingContext.getLocator()));
            } catch (AccessorException e) {
                unmarshallingContext.handleError(e);
            }
        }
        return beant;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public boolean reset(BeanT beant, UnmarshallingContext unmarshallingContext) throws SAXException {
        try {
            ClassBeanInfoImpl<? super BeanT> classBeanInfoImpl = this.superClazz;
            if (classBeanInfoImpl != null) {
                classBeanInfoImpl.reset(beant, unmarshallingContext);
            }
            for (Property<BeanT> property : this.properties) {
                property.reset(beant);
            }
            return true;
        } catch (AccessorException e) {
            unmarshallingContext.handleError(e);
            return false;
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public String getId(BeanT beant, XMLSerializer xMLSerializer) throws SAXException {
        Property<? super BeanT> property = this.idProperty;
        if (property != null) {
            try {
                return property.getIdValue(beant);
            } catch (AccessorException e) {
                xMLSerializer.reportError(null, e);
            }
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeRoot(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
        String str;
        Name name = this.tagName;
        if (name == null) {
            Class<?> cls = beant.getClass();
            if (cls.isAnnotationPresent(XmlRootElement.class)) {
                str = Messages.UNABLE_TO_MARSHAL_UNBOUND_CLASS.format(cls.getName());
            } else {
                str = Messages.UNABLE_TO_MARSHAL_NON_ELEMENT.format(cls.getName());
            }
            xMLSerializer.reportError(new ValidationEventImpl(1, str, null, null));
            return;
        }
        xMLSerializer.startElement(name, beant);
        xMLSerializer.childAsSoleContent(beant, null);
        xMLSerializer.endElement();
        if (this.retainPropertyInfo) {
            xMLSerializer.currentProperty.remove();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeBody(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
        ClassBeanInfoImpl<? super BeanT> classBeanInfoImpl = this.superClazz;
        if (classBeanInfoImpl != null) {
            classBeanInfoImpl.serializeBody(beant, xMLSerializer);
        }
        try {
            for (Property<BeanT> property : this.properties) {
                if (this.retainPropertyInfo) {
                    xMLSerializer.currentProperty.set(property);
                }
                boolean zIsHiddenByOverride = property.isHiddenByOverride();
                if (!zIsHiddenByOverride || beant.getClass().equals(this.jaxbType)) {
                    property.serializeBody(beant, xMLSerializer, null);
                } else if (zIsHiddenByOverride) {
                    if (Utils.REFLECTION_NAVIGATOR.getDeclaredField(beant.getClass(), property.getFieldName()) == null) {
                        property.serializeBody(beant, xMLSerializer, null);
                    }
                }
            }
        } catch (AccessorException e) {
            xMLSerializer.reportError(null, e);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeAttributes(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
        for (AttributeProperty<BeanT> attributeProperty : this.attributeProperties) {
            try {
                if (this.retainPropertyInfo) {
                    Property currentProperty = xMLSerializer.getCurrentProperty();
                    xMLSerializer.currentProperty.set(attributeProperty);
                    attributeProperty.serializeAttributes(beant, xMLSerializer);
                    xMLSerializer.currentProperty.set(currentProperty);
                } else {
                    attributeProperty.serializeAttributes(beant, xMLSerializer);
                }
                if (attributeProperty.attName.equals(WellKnownNamespace.XML_SCHEMA_INSTANCE, "nil")) {
                    this.isNilIncluded = true;
                }
            } catch (AccessorException e) {
                xMLSerializer.reportError(null, e);
            }
        }
        try {
            Accessor<? super BeanT, Map<QName, String>> accessor = this.inheritedAttWildcard;
            if (accessor != null) {
                xMLSerializer.attWildcardAsAttributes(accessor.get(beant), null);
            }
        } catch (AccessorException e2) {
            xMLSerializer.reportError(null, e2);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeURIs(BeanT beant, XMLSerializer xMLSerializer) throws SAXException {
        try {
            int i = 0;
            if (this.retainPropertyInfo) {
                Property currentProperty = xMLSerializer.getCurrentProperty();
                Property<BeanT>[] propertyArr = this.uriProperties;
                int length = propertyArr.length;
                while (i < length) {
                    Property<BeanT> property = propertyArr[i];
                    xMLSerializer.currentProperty.set(property);
                    property.serializeURIs(beant, xMLSerializer);
                    i++;
                }
                xMLSerializer.currentProperty.set(currentProperty);
            } else {
                Property<BeanT>[] propertyArr2 = this.uriProperties;
                int length2 = propertyArr2.length;
                while (i < length2) {
                    propertyArr2[i].serializeURIs(beant, xMLSerializer);
                    i++;
                }
            }
            Accessor<? super BeanT, Map<QName, String>> accessor = this.inheritedAttWildcard;
            if (accessor != null) {
                xMLSerializer.attWildcardAsURIs(accessor.get(beant), null);
            }
        } catch (AccessorException e) {
            xMLSerializer.reportError(null, e);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public Loader getLoader(JAXBContextImpl jAXBContextImpl, boolean z) {
        if (this.loader == null) {
            StructureLoader structureLoader = new StructureLoader(this);
            this.loader = structureLoader;
            if (this.ci.hasSubClasses()) {
                this.loaderWithTypeSubst = new XsiTypeLoader(this);
            } else {
                this.loaderWithTypeSubst = this.loader;
            }
            structureLoader.init(jAXBContextImpl, this, this.ci.getAttributeWildcard());
        }
        if (z) {
            return this.loaderWithTypeSubst;
        }
        return this.loader;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public Transducer<BeanT> getTransducer() {
        return this.xducer;
    }
}
