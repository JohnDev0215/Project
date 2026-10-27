package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.core.TypeInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeAttributePropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeElementPropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeNonElement;
import com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeValuePropertyInfo;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public abstract class PropertyFactory {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Constructor<? extends Property>[] propImpls = new Constructor[12];

    private PropertyFactory() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        Class[] clsArr = {SingleElementLeafProperty.class, null, null, ArrayElementLeafProperty.class, null, null, SingleElementNodeProperty.class, SingleReferenceNodeProperty.class, SingleMapNodeProperty.class, ArrayElementNodeProperty.class, ArrayReferenceNodeProperty.class, null};
        int i = 0;
        while (true) {
            Constructor<? extends Property>[] constructorArr = propImpls;
            if (i >= constructorArr.length) {
                return;
            }
            Class cls = clsArr[i];
            if (cls != null) {
                constructorArr[i] = cls.getConstructors()[0];
            }
            i++;
        }
    }

    public static Property create(JAXBContextImpl jAXBContextImpl, RuntimePropertyInfo runtimePropertyInfo) {
        PropertyKind propertyKindKind = runtimePropertyInfo.kind();
        int i = AnonymousClass1.$SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[propertyKindKind.ordinal()];
        if (i == 1) {
            return new AttributeProperty(jAXBContextImpl, (RuntimeAttributePropertyInfo) runtimePropertyInfo);
        }
        if (i == 2) {
            return new ValueProperty(jAXBContextImpl, (RuntimeValuePropertyInfo) runtimePropertyInfo);
        }
        if (i == 3) {
            RuntimeElementPropertyInfo runtimeElementPropertyInfo = (RuntimeElementPropertyInfo) runtimePropertyInfo;
            if (runtimeElementPropertyInfo.isValueList()) {
                return new ListElementProperty(jAXBContextImpl, runtimeElementPropertyInfo);
            }
        }
        boolean zIsCollection = runtimePropertyInfo.isCollection();
        try {
            return propImpls[(isLeaf(runtimePropertyInfo) ? 0 : 6) + (zIsCollection ? 3 : 0) + propertyKindKind.propertyIndex].newInstance(jAXBContextImpl, runtimePropertyInfo);
        } catch (IllegalAccessException e) {
            throw new IllegalAccessError(e.getMessage());
        } catch (InstantiationException e2) {
            throw new InstantiationError(e2.getMessage());
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new AssertionError(cause);
        }
    }

    /* JADX INFO: renamed from: com.sun.xml.bind.v2.runtime.property.PropertyFactory$1, reason: invalid class name */
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
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.ELEMENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.REFERENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.MAP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    static boolean isLeaf(RuntimePropertyInfo runtimePropertyInfo) {
        Collection<? extends TypeInfo<Type, Class>> collectionRef = runtimePropertyInfo.ref();
        if (collectionRef.size() != 1) {
            return false;
        }
        RuntimeTypeInfo runtimeTypeInfo = (RuntimeTypeInfo) collectionRef.iterator().next();
        if (!(runtimeTypeInfo instanceof RuntimeNonElement)) {
            return false;
        }
        if (runtimePropertyInfo.id() == ID.IDREF) {
            return true;
        }
        return ((RuntimeNonElement) runtimeTypeInfo).getTransducer() != null && runtimePropertyInfo.getIndividualType().equals(runtimeTypeInfo.getType2());
    }
}
