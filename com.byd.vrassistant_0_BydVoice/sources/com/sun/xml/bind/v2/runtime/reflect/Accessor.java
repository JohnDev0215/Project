package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.Util;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.core.Adapter;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.reflect.opt.OptimizedAccessorFactory;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.Receiver;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import java.awt.Image;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Source;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Accessor<BeanT, ValueT> implements Receiver {
    private static final Map<Class, Object> uninitializedValues;
    public final Class<ValueT> valueType;
    private static List<Class> nonAbstractableClasses = Arrays.asList(Object.class, Calendar.class, Duration.class, XMLGregorianCalendar.class, Image.class, DataHandler.class, Source.class, Date.class, File.class, URI.class, URL.class, Class.class, String.class, Source.class);
    private static boolean accessWarned = false;
    private static final Accessor ERROR = new Accessor<Object, Object>(Object.class) { // from class: com.sun.xml.bind.v2.runtime.reflect.Accessor.1
        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
        public Object get(Object obj) {
            return null;
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
        public void set(Object obj, Object obj2) {
        }
    };
    public static final Accessor<JAXBElement, Object> JAXB_ELEMENT_VALUE = new Accessor<JAXBElement, Object>(Object.class) { // from class: com.sun.xml.bind.v2.runtime.reflect.Accessor.2
        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
        public Object get(JAXBElement jAXBElement) {
            return jAXBElement.getValue();
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
        public void set(JAXBElement jAXBElement, Object obj) {
            jAXBElement.setValue(obj);
        }
    };

    public abstract ValueT get(BeanT beant) throws AccessorException;

    public boolean isAdapted() {
        return false;
    }

    public Accessor<BeanT, ValueT> optimize(JAXBContextImpl jAXBContextImpl) {
        return this;
    }

    public abstract void set(BeanT beant, ValueT valuet) throws AccessorException;

    public Class<ValueT> getValueType() {
        return this.valueType;
    }

    protected Accessor(Class<ValueT> cls) {
        this.valueType = cls;
    }

    public Object getUnadapted(BeanT beant) throws AccessorException {
        return get(beant);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setUnadapted(BeanT beant, Object obj) throws AccessorException {
        set(beant, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Receiver
    public void receive(UnmarshallingContext.State state, Object obj) throws SAXException {
        try {
            set(state.getTarget(), obj);
        } catch (AccessorException e) {
            Loader.handleGenericException(e, true);
        } catch (IllegalAccessError e2) {
            Loader.handleGenericError(e2);
        }
    }

    static {
        HashMap map = new HashMap();
        uninitializedValues = map;
        map.put(Byte.TYPE, (byte) 0);
        map.put(Boolean.TYPE, false);
        map.put(Character.TYPE, (char) 0);
        map.put(Float.TYPE, Float.valueOf(0.0f));
        map.put(Double.TYPE, Double.valueOf(0.0d));
        map.put(Integer.TYPE, 0);
        map.put(Long.TYPE, 0L);
        map.put(Short.TYPE, (short) 0);
    }

    public boolean isValueTypeAbstractable() {
        return !nonAbstractableClasses.contains(getValueType());
    }

    public boolean isAbstractable(Class cls) {
        return !nonAbstractableClasses.contains(cls);
    }

    public final <T> Accessor<BeanT, T> adapt(Class<T> cls, Class<? extends XmlAdapter<T, ValueT>> cls2) {
        return new AdaptedAccessor(cls, this, cls2);
    }

    public final <T> Accessor<BeanT, T> adapt(Adapter<Type, Class> adapter) {
        return new AdaptedAccessor((Class) Utils.REFLECTION_NAVIGATOR.erasure(adapter.defaultType), this, adapter.adapterType);
    }

    public static class FieldReflection<BeanT, ValueT> extends Accessor<BeanT, ValueT> {
        private static final Logger logger = Util.getClassLogger();
        public final Field f;

        public FieldReflection(Field field) {
            this(field, false);
        }

        public FieldReflection(Field field, boolean z) {
            super(field.getType());
            this.f = field;
            int modifiers = field.getModifiers();
            if (Modifier.isPublic(modifiers) && !Modifier.isFinal(modifiers) && Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
                return;
            }
            try {
                field.setAccessible(true);
            } catch (SecurityException e) {
                if (!Accessor.accessWarned && !z) {
                    logger.log(Level.WARNING, Messages.UNABLE_TO_ACCESS_NON_PUBLIC_FIELD.format(field.getDeclaringClass().getName(), field.getName()), (Throwable) e);
                }
                boolean unused = Accessor.accessWarned = true;
            }
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
        public ValueT get(BeanT beant) {
            try {
                return (ValueT) this.f.get(beant);
            } catch (IllegalAccessException e) {
                throw new IllegalAccessError(e.getMessage());
            }
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
        public void set(BeanT beant, ValueT valuet) {
            if (valuet == null) {
                try {
                    valuet = (ValueT) Accessor.uninitializedValues.get(this.valueType);
                } catch (IllegalAccessException e) {
                    throw new IllegalAccessError(e.getMessage());
                }
            }
            this.f.set(beant, valuet);
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
        public Accessor<BeanT, ValueT> optimize(JAXBContextImpl jAXBContextImpl) {
            Accessor<BeanT, ValueT> accessor;
            return ((jAXBContextImpl == null || !jAXBContextImpl.fastBoot) && (accessor = OptimizedAccessorFactory.get(this.f)) != null) ? accessor : this;
        }
    }

    public static final class ReadOnlyFieldReflection<BeanT, ValueT> extends FieldReflection<BeanT, ValueT> {
        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor.FieldReflection, com.sun.xml.bind.v2.runtime.reflect.Accessor
        public Accessor<BeanT, ValueT> optimize(JAXBContextImpl jAXBContextImpl) {
            return this;
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor.FieldReflection, com.sun.xml.bind.v2.runtime.reflect.Accessor
        public void set(BeanT beant, ValueT valuet) {
        }

        public ReadOnlyFieldReflection(Field field, boolean z) {
            super(field, z);
        }

        public ReadOnlyFieldReflection(Field field) {
            super(field);
        }
    }

    public static class GetterSetterReflection<BeanT, ValueT> extends Accessor<BeanT, ValueT> {
        private static final Logger logger = Util.getClassLogger();
        public final Method getter;
        public final Method setter;

        public GetterSetterReflection(Method method, Method method2) {
            super(method != null ? method.getReturnType() : method2.getParameterTypes()[0]);
            this.getter = method;
            this.setter = method2;
            if (method != null) {
                makeAccessible(method);
            }
            if (method2 != null) {
                makeAccessible(method2);
            }
        }

        private void makeAccessible(Method method) {
            if (Modifier.isPublic(method.getModifiers()) && Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
                return;
            }
            try {
                method.setAccessible(true);
            } catch (SecurityException e) {
                if (!Accessor.accessWarned) {
                    logger.log(Level.WARNING, Messages.UNABLE_TO_ACCESS_NON_PUBLIC_FIELD.format(method.getDeclaringClass().getName(), method.getName()), (Throwable) e);
                }
                boolean unused = Accessor.accessWarned = true;
            }
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
        public ValueT get(BeanT beant) throws AccessorException {
            try {
                return (ValueT) this.getter.invoke(beant, new Object[0]);
            } catch (IllegalAccessException e) {
                throw new IllegalAccessError(e.getMessage());
            } catch (InvocationTargetException e2) {
                throw handleInvocationTargetException(e2);
            }
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
        public void set(BeanT beant, ValueT valuet) throws AccessorException {
            if (valuet == null) {
                try {
                    valuet = (ValueT) Accessor.uninitializedValues.get(this.valueType);
                } catch (IllegalAccessException e) {
                    throw new IllegalAccessError(e.getMessage());
                } catch (InvocationTargetException e2) {
                    throw handleInvocationTargetException(e2);
                }
            }
            this.setter.invoke(beant, valuet);
        }

        private AccessorException handleInvocationTargetException(InvocationTargetException invocationTargetException) {
            Throwable targetException = invocationTargetException.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            if (targetException instanceof Error) {
                throw ((Error) targetException);
            }
            return new AccessorException(targetException);
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
        public Accessor<BeanT, ValueT> optimize(JAXBContextImpl jAXBContextImpl) {
            Accessor<BeanT, ValueT> accessor;
            return (this.getter == null || this.setter == null || (jAXBContextImpl != null && jAXBContextImpl.fastBoot) || (accessor = OptimizedAccessorFactory.get(this.getter, this.setter)) == null) ? this : accessor;
        }
    }

    public static class GetterOnlyReflection<BeanT, ValueT> extends GetterSetterReflection<BeanT, ValueT> {
        public GetterOnlyReflection(Method method) {
            super(method, null);
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor.GetterSetterReflection, com.sun.xml.bind.v2.runtime.reflect.Accessor
        public void set(BeanT beant, ValueT valuet) throws AccessorException {
            throw new AccessorException(Messages.NO_SETTER.format(this.getter.toString()));
        }
    }

    public static class SetterOnlyReflection<BeanT, ValueT> extends GetterSetterReflection<BeanT, ValueT> {
        public SetterOnlyReflection(Method method) {
            super(null, method);
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor.GetterSetterReflection, com.sun.xml.bind.v2.runtime.reflect.Accessor
        public ValueT get(BeanT beant) throws AccessorException {
            throw new AccessorException(Messages.NO_GETTER.format(this.setter.toString()));
        }
    }

    public static <A, B> Accessor<A, B> getErrorInstance() {
        return ERROR;
    }
}
