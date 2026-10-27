package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.Util;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallerImpl;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class JaxBeanInfo<BeanT> {
    private static final short FLAG_HAS_AFTER_MARSHAL_METHOD = 64;
    private static final short FLAG_HAS_AFTER_UNMARSHAL_METHOD = 16;
    private static final short FLAG_HAS_BEFORE_MARSHAL_METHOD = 32;
    private static final short FLAG_HAS_BEFORE_UNMARSHAL_METHOD = 8;
    private static final short FLAG_HAS_ELEMENT_ONLY_CONTENTMODEL = 4;
    private static final short FLAG_HAS_LIFECYCLE_EVENTS = 128;
    private static final short FLAG_IS_ELEMENT = 1;
    private static final short FLAG_IS_IMMUTABLE = 2;
    protected short flag;
    protected boolean isNilIncluded;
    public final Class<BeanT> jaxbType;
    private LifecycleMethods lcm;
    private final Object typeName;
    private static final Class[] unmarshalEventParams = {Unmarshaller.class, Object.class};
    private static Class[] marshalEventParams = {Marshaller.class};
    private static final Logger logger = Util.getClassLogger();

    public abstract BeanT createInstance(UnmarshallingContext unmarshallingContext) throws IllegalAccessException, SAXException, InstantiationException, InvocationTargetException;

    public abstract String getElementLocalName(BeanT beant);

    public abstract String getElementNamespaceURI(BeanT beant);

    public abstract String getId(BeanT beant, XMLSerializer xMLSerializer) throws SAXException;

    public abstract Loader getLoader(JAXBContextImpl jAXBContextImpl, boolean z);

    public abstract Transducer<BeanT> getTransducer();

    protected void link(JAXBContextImpl jAXBContextImpl) {
    }

    public abstract boolean reset(BeanT beant, UnmarshallingContext unmarshallingContext) throws SAXException;

    public abstract void serializeAttributes(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException;

    public abstract void serializeBody(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException;

    public abstract void serializeRoot(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException;

    public abstract void serializeURIs(BeanT beant, XMLSerializer xMLSerializer) throws SAXException;

    public void wrapUp() {
    }

    protected JaxBeanInfo(JAXBContextImpl jAXBContextImpl, RuntimeTypeInfo runtimeTypeInfo, Class<BeanT> cls, QName[] qNameArr, boolean z, boolean z2, boolean z3) {
        this(jAXBContextImpl, runtimeTypeInfo, (Class) cls, (Object) qNameArr, z, z2, z3);
    }

    protected JaxBeanInfo(JAXBContextImpl jAXBContextImpl, RuntimeTypeInfo runtimeTypeInfo, Class<BeanT> cls, QName qName, boolean z, boolean z2, boolean z3) {
        this(jAXBContextImpl, runtimeTypeInfo, (Class) cls, (Object) qName, z, z2, z3);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    protected JaxBeanInfo(JAXBContextImpl jAXBContextImpl, RuntimeTypeInfo runtimeTypeInfo, Class<BeanT> cls, boolean z, boolean z2, boolean z3) {
        this(jAXBContextImpl, runtimeTypeInfo, cls, (Object) null, z, z2, z3);
    }

    private JaxBeanInfo(JAXBContextImpl jAXBContextImpl, RuntimeTypeInfo runtimeTypeInfo, Class<BeanT> cls, Object obj, boolean z, boolean z2, boolean z3) {
        this.isNilIncluded = false;
        this.lcm = null;
        jAXBContextImpl.beanInfos.put(runtimeTypeInfo, this);
        this.jaxbType = cls;
        this.typeName = obj;
        this.flag = (short) ((z2 ? 2 : 0) | (z ? 1 : 0) | (z3 ? 128 : 0));
    }

    public final boolean hasBeforeUnmarshalMethod() {
        return (this.flag & 8) != 0;
    }

    public final boolean hasAfterUnmarshalMethod() {
        return (this.flag & 16) != 0;
    }

    public final boolean hasBeforeMarshalMethod() {
        return (this.flag & FLAG_HAS_BEFORE_MARSHAL_METHOD) != 0;
    }

    public final boolean hasAfterMarshalMethod() {
        return (this.flag & 64) != 0;
    }

    public final boolean isElement() {
        return (this.flag & 1) != 0;
    }

    public final boolean isImmutable() {
        return (this.flag & 2) != 0;
    }

    public final boolean hasElementOnlyContentModel() {
        return (this.flag & 4) != 0;
    }

    protected final void hasElementOnlyContentModel(boolean z) {
        if (z) {
            this.flag = (short) (this.flag | 4);
        } else {
            this.flag = (short) (this.flag & (-5));
        }
    }

    public boolean isNilIncluded() {
        return this.isNilIncluded;
    }

    public boolean lookForLifecycleMethods() {
        return (this.flag & FLAG_HAS_LIFECYCLE_EVENTS) != 0;
    }

    public Collection<QName> getTypeNames() {
        Object obj = this.typeName;
        return obj == null ? Collections.emptyList() : obj instanceof QName ? Collections.singletonList((QName) obj) : Arrays.asList((QName[]) obj);
    }

    public QName getTypeName(BeanT beant) {
        Object obj = this.typeName;
        if (obj == null) {
            return null;
        }
        return obj instanceof QName ? (QName) obj : ((QName[]) obj)[0];
    }

    private Method[] getDeclaredMethods(final Class<BeanT> cls) {
        return (Method[]) AccessController.doPrivileged(new PrivilegedAction<Method[]>() { // from class: com.sun.xml.bind.v2.runtime.JaxBeanInfo.1
            @Override // java.security.PrivilegedAction
            public Method[] run() {
                return cls.getDeclaredMethods();
            }
        });
    }

    protected final void setLifecycleFlags() {
        try {
            if (this.lcm == null) {
                this.lcm = new LifecycleMethods();
            }
            for (Class<BeanT> superclass = this.jaxbType; superclass != null; superclass = superclass.getSuperclass()) {
                for (Method method : getDeclaredMethods(superclass)) {
                    String name = method.getName();
                    if (this.lcm.beforeUnmarshal == null && name.equals("beforeUnmarshal") && match(method, unmarshalEventParams)) {
                        cacheLifecycleMethod(method, (short) 8);
                    }
                    if (this.lcm.afterUnmarshal == null && name.equals("afterUnmarshal") && match(method, unmarshalEventParams)) {
                        cacheLifecycleMethod(method, (short) 16);
                    }
                    if (this.lcm.beforeMarshal == null && name.equals("beforeMarshal") && match(method, marshalEventParams)) {
                        cacheLifecycleMethod(method, FLAG_HAS_BEFORE_MARSHAL_METHOD);
                    }
                    if (this.lcm.afterMarshal == null && name.equals("afterMarshal") && match(method, marshalEventParams)) {
                        cacheLifecycleMethod(method, (short) 64);
                    }
                }
            }
        } catch (SecurityException e) {
            logger.log(Level.WARNING, Messages.UNABLE_TO_DISCOVER_EVENTHANDLER.format(this.jaxbType.getName(), e), (Throwable) e);
        }
    }

    private boolean match(Method method, Class[] clsArr) {
        return Arrays.equals(method.getParameterTypes(), clsArr);
    }

    private void cacheLifecycleMethod(Method method, short s) {
        if (this.lcm == null) {
            this.lcm = new LifecycleMethods();
        }
        method.setAccessible(true);
        this.flag = (short) (this.flag | s);
        if (s == 8) {
            this.lcm.beforeUnmarshal = method;
            return;
        }
        if (s == 16) {
            this.lcm.afterUnmarshal = method;
        } else if (s == 32) {
            this.lcm.beforeMarshal = method;
        } else {
            if (s != 64) {
                return;
            }
            this.lcm.afterMarshal = method;
        }
    }

    public final LifecycleMethods getLifecycleMethods() {
        return this.lcm;
    }

    public final void invokeBeforeUnmarshalMethod(UnmarshallerImpl unmarshallerImpl, Object obj, Object obj2) throws SAXException {
        invokeUnmarshallCallback(getLifecycleMethods().beforeUnmarshal, obj, unmarshallerImpl, obj2);
    }

    public final void invokeAfterUnmarshalMethod(UnmarshallerImpl unmarshallerImpl, Object obj, Object obj2) throws SAXException {
        invokeUnmarshallCallback(getLifecycleMethods().afterUnmarshal, obj, unmarshallerImpl, obj2);
    }

    private void invokeUnmarshallCallback(Method method, Object obj, UnmarshallerImpl unmarshallerImpl, Object obj2) throws SAXException {
        try {
            method.invoke(obj, unmarshallerImpl, obj2);
        } catch (IllegalAccessException e) {
            UnmarshallingContext.getInstance().handleError(e, false);
        } catch (InvocationTargetException e2) {
            UnmarshallingContext.getInstance().handleError(e2, false);
        }
    }
}
