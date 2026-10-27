package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.Util;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.api.JAXBRIContext;
import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.runtime.ClassBeanInfoImpl;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.property.AttributeProperty;
import com.sun.xml.bind.v2.runtime.property.Property;
import com.sun.xml.bind.v2.runtime.property.StructureLoaderBuilder;
import com.sun.xml.bind.v2.runtime.property.UnmarshallerChain;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import com.sun.xml.bind.v2.util.QNameMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.namespace.QName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class StructureLoader extends Loader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final QNameMap<TransducedAccessor> EMPTY = new QNameMap<>();
    private Accessor<Object, Map<QName, String>> attCatchAll;
    private QNameMap<TransducedAccessor> attUnmarshallers;
    private final JaxBeanInfo beanInfo;
    private ChildLoader catchAll;
    private final QNameMap<ChildLoader> childUnmarshallers;
    private int frameSize;
    private ChildLoader textHandler;

    public StructureLoader(ClassBeanInfoImpl classBeanInfoImpl) {
        super(true);
        this.childUnmarshallers = new QNameMap<>();
        this.beanInfo = classBeanInfoImpl;
    }

    public void init(JAXBContextImpl jAXBContextImpl, ClassBeanInfoImpl classBeanInfoImpl, Accessor<?, Map<QName, String>> accessor) {
        UnmarshallerChain unmarshallerChain = new UnmarshallerChain(jAXBContextImpl);
        while (classBeanInfoImpl != null) {
            for (int length = classBeanInfoImpl.properties.length - 1; length >= 0; length--) {
                Property property = classBeanInfoImpl.properties[length];
                int i = AnonymousClass1.$SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[property.getKind().ordinal()];
                if (i == 1) {
                    if (this.attUnmarshallers == null) {
                        this.attUnmarshallers = new QNameMap<>();
                    }
                    AttributeProperty attributeProperty = (AttributeProperty) property;
                    this.attUnmarshallers.put(attributeProperty.attName.toQName(), attributeProperty.xacc);
                } else if (i == 2 || i == 3 || i == 4 || i == 5) {
                    property.buildChildElementUnmarshallers(unmarshallerChain, this.childUnmarshallers);
                }
            }
            classBeanInfoImpl = classBeanInfoImpl.superClazz;
        }
        this.frameSize = unmarshallerChain.getScopeSize();
        this.textHandler = this.childUnmarshallers.get(StructureLoaderBuilder.TEXT_HANDLER);
        this.catchAll = this.childUnmarshallers.get(StructureLoaderBuilder.CATCH_ALL);
        if (accessor != null) {
            this.attCatchAll = accessor;
            if (this.attUnmarshallers == null) {
                this.attUnmarshallers = EMPTY;
                return;
            }
            return;
        }
        this.attCatchAll = null;
    }

    /* JADX INFO: renamed from: com.sun.xml.bind.v2.runtime.unmarshaller.StructureLoader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind;

        static {
            int[] iArr = new int[PropertyKind.values().length];
            $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind = iArr;
            try {
                iArr[PropertyKind.ATTRIBUTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.ELEMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.REFERENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.MAP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.VALUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void startElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        UnmarshallingContext context = state.getContext();
        Object innerPeer = context.getInnerPeer();
        if (innerPeer != null && this.beanInfo.jaxbType != innerPeer.getClass()) {
            innerPeer = null;
        }
        if (innerPeer != null) {
            this.beanInfo.reset(innerPeer, context);
        }
        if (innerPeer == null) {
            innerPeer = context.createInstance(this.beanInfo);
        }
        context.recordInnerPeer(innerPeer);
        state.setTarget(innerPeer);
        fireBeforeUnmarshal(this.beanInfo, innerPeer, state);
        context.startScope(this.frameSize);
        if (this.attUnmarshallers != null) {
            Attributes attributes = tagName.atts;
            for (int i = 0; i < attributes.getLength(); i++) {
                String uri = attributes.getURI(i);
                String localName = attributes.getLocalName(i);
                String strSubstring = "";
                if ("".equals(localName)) {
                    localName = attributes.getQName(i);
                }
                String value = attributes.getValue(i);
                TransducedAccessor transducedAccessor = this.attUnmarshallers.get(uri, localName);
                if (transducedAccessor != null) {
                    try {
                        transducedAccessor.parse(innerPeer, value);
                    } catch (AccessorException e) {
                        handleGenericException(e, true);
                    }
                } else if (this.attCatchAll != null) {
                    String qName = attributes.getQName(i);
                    if (!attributes.getURI(i).equals(WellKnownNamespace.XML_SCHEMA_INSTANCE)) {
                        Object target = state.getTarget();
                        Map<QName, String> map = this.attCatchAll.get(target);
                        if (map == null) {
                            if (!this.attCatchAll.valueType.isAssignableFrom(HashMap.class)) {
                                context.handleError(Messages.UNABLE_TO_CREATE_MAP.format(this.attCatchAll.valueType));
                                return;
                            } else {
                                map = new HashMap<>();
                                this.attCatchAll.set(target, map);
                            }
                        }
                        int iIndexOf = qName.indexOf(58);
                        if (iIndexOf >= 0) {
                            strSubstring = qName.substring(0, iIndexOf);
                        }
                        map.put(new QName(uri, localName, strSubstring), value);
                    }
                } else {
                    continue;
                }
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void childElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        Iterator<QName> it;
        ChildLoader childLoader = this.childUnmarshallers.get(tagName.uri, tagName.local);
        if (childLoader == null) {
            Boolean bool = state.getContext().getJAXBContext().backupWithParentNamespace;
            Boolean boolValueOf = Boolean.valueOf(bool != null ? bool.booleanValue() : Boolean.parseBoolean(Util.getSystemProperty(JAXBRIContext.BACKUP_WITH_PARENT_NAMESPACE)));
            JaxBeanInfo jaxBeanInfo = this.beanInfo;
            if (jaxBeanInfo != null && jaxBeanInfo.getTypeNames() != null && boolValueOf.booleanValue() && (it = this.beanInfo.getTypeNames().iterator()) != null && it.hasNext() && this.catchAll == null) {
                childLoader = this.childUnmarshallers.get(it.next().getNamespaceURI(), tagName.local);
            }
            if (childLoader == null && (childLoader = this.catchAll) == null) {
                super.childElement(state, tagName);
                return;
            }
        }
        state.setLoader(childLoader.loader);
        state.setReceiver(childLoader.receiver);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public Collection<QName> getExpectedChildElements() {
        return this.childUnmarshallers.keySet();
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public Collection<QName> getExpectedAttributes() {
        return this.attUnmarshallers.keySet();
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void text(UnmarshallingContext.State state, CharSequence charSequence) throws SAXException {
        ChildLoader childLoader = this.textHandler;
        if (childLoader != null) {
            childLoader.loader.text(state, charSequence);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
    public void leaveElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        state.getContext().endScope(this.frameSize);
        fireAfterUnmarshal(this.beanInfo, state.getTarget(), state.getPrev());
    }

    public JaxBeanInfo getBeanInfo() {
        return this.beanInfo;
    }
}
