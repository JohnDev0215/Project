package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.core.TypeRef;
import com.sun.xml.bind.v2.model.runtime.RuntimeElementPropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeRef;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.RuntimeUtil;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.property.ArrayERProperty;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.reflect.ListIterator;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import com.sun.xml.bind.v2.runtime.reflect.NullSafeAccessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.ChildLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.DefaultValueLoaderDecorator;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.TextLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.sun.xml.bind.v2.util.QNameMap;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
abstract class ArrayElementProperty<BeanT, ListT, ItemT> extends ArrayERProperty<BeanT, ListT, ItemT> {
    private final Name nillableTagName;
    protected RuntimeElementPropertyInfo prop;
    private Map<TypeRef<Type, Class>, JaxBeanInfo> refs;
    private final Map<Class, TagAndType> typeMap;

    protected abstract void serializeItem(JaxBeanInfo jaxBeanInfo, ItemT itemt, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException, AccessorException;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.sun.xml.bind.v2.model.runtime.RuntimeNonElement, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo] */
    protected ArrayElementProperty(JAXBContextImpl jAXBContextImpl, RuntimeElementPropertyInfo runtimeElementPropertyInfo) {
        super(jAXBContextImpl, runtimeElementPropertyInfo, runtimeElementPropertyInfo.getXmlName(), runtimeElementPropertyInfo.isCollectionNillable());
        this.typeMap = new HashMap();
        this.refs = new HashMap();
        this.prop = runtimeElementPropertyInfo;
        Iterator<? extends TypeRef<Type, Class>> it = runtimeElementPropertyInfo.getTypes().iterator();
        Name name = null;
        while (it.hasNext()) {
            RuntimeTypeRef runtimeTypeRef = (RuntimeTypeRef) it.next();
            Class cls = (Class) runtimeTypeRef.getTarget().getType2();
            cls = cls.isPrimitive() ? RuntimeUtil.primitiveToBox.get(cls) : cls;
            JaxBeanInfo orCreate = jAXBContextImpl.getOrCreate((RuntimeTypeInfo) runtimeTypeRef.getTarget());
            TagAndType tagAndType = new TagAndType(jAXBContextImpl.nameBuilder.createElementName(runtimeTypeRef.getTagName()), orCreate);
            this.typeMap.put(cls, tagAndType);
            this.refs.put(runtimeTypeRef, orCreate);
            if (runtimeTypeRef.isNillable() && name == null) {
                name = tagAndType.tagName;
            }
        }
        this.nillableTagName = name;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public void wrapUp() {
        super.wrapUp();
        this.refs = null;
        this.prop = null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.property.ArrayERProperty
    protected void serializeListBody(BeanT beant, XMLSerializer xMLSerializer, ListT listt) throws SAXException, XMLStreamException, IOException, AccessorException {
        ListIterator<ItemT> it = this.lister.iterator(listt, xMLSerializer);
        boolean z = it instanceof Lister.IDREFSIterator;
        while (it.hasNext()) {
            try {
                ItemT next = it.next();
                if (next != null) {
                    Class<?> superclass = next.getClass();
                    if (z) {
                        superclass = ((Lister.IDREFSIterator) it).last().getClass();
                    }
                    TagAndType tagAndType = this.typeMap.get(superclass);
                    while (tagAndType == null && superclass != null) {
                        superclass = superclass.getSuperclass();
                        tagAndType = this.typeMap.get(superclass);
                    }
                    if (tagAndType == null) {
                        xMLSerializer.startElement(this.typeMap.values().iterator().next().tagName, null);
                        xMLSerializer.childAsXsiType(next, this.fieldName, xMLSerializer.grammar.getBeanInfo(Object.class), false);
                    } else {
                        xMLSerializer.startElement(tagAndType.tagName, null);
                        serializeItem(tagAndType.beanInfo, next, xMLSerializer);
                    }
                    xMLSerializer.endElement();
                } else {
                    Name name = this.nillableTagName;
                    if (name != null) {
                        xMLSerializer.startElement(name, null);
                        xMLSerializer.writeXsiNilTrue();
                        xMLSerializer.endElement();
                    }
                }
            } catch (JAXBException e) {
                xMLSerializer.reportError(this.fieldName, e);
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.property.ArrayERProperty
    public void createBodyUnmarshaller(UnmarshallerChain unmarshallerChain, QNameMap<ChildLoader> qNameMap) {
        ArrayERProperty.ReceiverImpl receiverImpl = new ArrayERProperty.ReceiverImpl(unmarshallerChain.allocateOffset());
        Iterator<? extends TypeRef<Type, Class>> it = this.prop.getTypes().iterator();
        while (it.hasNext()) {
            RuntimeTypeRef runtimeTypeRef = (RuntimeTypeRef) it.next();
            Name nameCreateElementName = unmarshallerChain.context.nameBuilder.createElementName(runtimeTypeRef.getTagName());
            Loader loaderCreateItemUnmarshaller = createItemUnmarshaller(unmarshallerChain, runtimeTypeRef);
            if (runtimeTypeRef.isNillable() || unmarshallerChain.context.allNillable) {
                loaderCreateItemUnmarshaller = new XsiNilLoader.Array(loaderCreateItemUnmarshaller);
            }
            if (runtimeTypeRef.getDefaultValue() != null) {
                loaderCreateItemUnmarshaller = new DefaultValueLoaderDecorator(loaderCreateItemUnmarshaller, runtimeTypeRef.getDefaultValue());
            }
            qNameMap.put(nameCreateElementName, new ChildLoader(loaderCreateItemUnmarshaller, receiverImpl));
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public final PropertyKind getKind() {
        return PropertyKind.ELEMENT;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo] */
    private Loader createItemUnmarshaller(UnmarshallerChain unmarshallerChain, RuntimeTypeRef runtimeTypeRef) {
        if (PropertyFactory.isLeaf(runtimeTypeRef.getSource())) {
            return new TextLoader(runtimeTypeRef.getTransducer());
        }
        return this.refs.get(runtimeTypeRef).getLoader(unmarshallerChain.context, true);
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public Accessor getElementPropertyAccessor(String str, String str2) {
        if (this.wrapperTagName != null) {
            if (this.wrapperTagName.equals(str, str2)) {
                return this.acc;
            }
            return null;
        }
        Iterator<TagAndType> it = this.typeMap.values().iterator();
        while (it.hasNext()) {
            if (it.next().tagName.equals(str, str2)) {
                return new NullSafeAccessor(this.acc, this.lister);
            }
        }
        return null;
    }
}
