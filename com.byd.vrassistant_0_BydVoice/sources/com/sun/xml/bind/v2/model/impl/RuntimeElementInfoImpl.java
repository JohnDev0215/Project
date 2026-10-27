package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.core.Adapter;
import com.sun.xml.bind.v2.model.core.ClassInfo;
import com.sun.xml.bind.v2.model.core.ElementPropertyInfo;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.core.TypeInfo;
import com.sun.xml.bind.v2.model.core.TypeRef;
import com.sun.xml.bind.v2.model.runtime.RuntimeClassInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeElementInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeElementPropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeNonElement;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeRef;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import com.sun.xml.bind.v2.runtime.Transducer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/* JADX INFO: loaded from: classes3.dex */
final class RuntimeElementInfoImpl extends ElementInfoImpl<Type, Class, Field, Method> implements RuntimeElementInfo {
    private final Class<? extends XmlAdapter> adapterType;

    public RuntimeElementInfoImpl(RuntimeModelBuilder runtimeModelBuilder, RegistryInfoImpl registryInfoImpl, Method method) throws IllegalAnnotationException {
        super(runtimeModelBuilder, registryInfoImpl, method);
        Adapter<Type, Class> adapter = getProperty().getAdapter();
        if (adapter != null) {
            this.adapterType = adapter.adapterType;
        } else {
            this.adapterType = null;
        }
    }

    @Override // com.sun.xml.bind.v2.model.impl.ElementInfoImpl
    protected ElementInfoImpl<Type, Class, Field, Method>.PropertyImpl createPropertyImpl() {
        return new RuntimePropertyImpl();
    }

    class RuntimePropertyImpl extends ElementInfoImpl<Type, Class, Field, Method>.PropertyImpl implements RuntimeElementPropertyInfo, RuntimeTypeRef {
        @Override // com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo
        public boolean elementOnlyContent() {
            return false;
        }

        @Override // com.sun.xml.bind.v2.model.impl.ElementInfoImpl.PropertyImpl, com.sun.xml.bind.v2.model.core.NonElementRef
        public PropertyInfo<Type, Class> getSource() {
            return this;
        }

        RuntimePropertyImpl() {
            super();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo
        public Accessor getAccessor() {
            if (RuntimeElementInfoImpl.this.adapterType != null) {
                return Accessor.JAXB_ELEMENT_VALUE.adapt((Class) getAdapter().defaultType, RuntimeElementInfoImpl.this.adapterType);
            }
            return Accessor.JAXB_ELEMENT_VALUE;
        }

        @Override // com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo
        public Type getRawType() {
            return Collection.class;
        }

        @Override // com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo
        public Type getIndividualType() {
            return RuntimeElementInfoImpl.this.getContentType().getType2();
        }

        @Override // com.sun.xml.bind.v2.model.impl.ElementInfoImpl.PropertyImpl, com.sun.xml.bind.v2.model.core.ElementPropertyInfo
        public List<? extends TypeRef<Type, Class>> getTypes() {
            return Collections.singletonList(this);
        }

        @Override // com.sun.xml.bind.v2.model.impl.ElementInfoImpl.PropertyImpl, com.sun.xml.bind.v2.model.core.PropertyInfo
        public Collection<? extends TypeInfo<Type, Class>> ref() {
            return super.ref();
        }

        @Override // com.sun.xml.bind.v2.model.impl.ElementInfoImpl.PropertyImpl, com.sun.xml.bind.v2.model.core.NonElementRef
        public NonElement<Type, Class> getTarget() {
            return (RuntimeNonElement) super.getTarget();
        }

        @Override // com.sun.xml.bind.v2.model.runtime.RuntimeNonElementRef
        public Transducer getTransducer() {
            return RuntimeModelBuilder.createTransducer(this);
        }
    }

    @Override // com.sun.xml.bind.v2.model.impl.ElementInfoImpl, com.sun.xml.bind.v2.model.core.ElementInfo
    public ElementPropertyInfo<Type, Class> getProperty() {
        return (RuntimeElementPropertyInfo) super.getProperty();
    }

    @Override // com.sun.xml.bind.v2.model.impl.ElementInfoImpl, com.sun.xml.bind.v2.model.core.TypeInfo
    /* JADX INFO: renamed from: getType */
    public Type getType2() {
        return (Class) Utils.REFLECTION_NAVIGATOR.erasure((Type) super.getType2());
    }

    @Override // com.sun.xml.bind.v2.model.impl.ElementInfoImpl, com.sun.xml.bind.v2.model.core.Element
    public ClassInfo<Type, Class> getScope() {
        return (RuntimeClassInfo) super.getScope();
    }

    @Override // com.sun.xml.bind.v2.model.impl.ElementInfoImpl, com.sun.xml.bind.v2.model.core.ElementInfo
    public NonElement<Type, Class> getContentType() {
        return (RuntimeNonElement) super.getContentType();
    }
}
