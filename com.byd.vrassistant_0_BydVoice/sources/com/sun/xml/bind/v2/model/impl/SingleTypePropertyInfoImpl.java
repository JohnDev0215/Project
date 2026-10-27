package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.impl.RuntimeClassInfoImpl;
import com.sun.xml.bind.v2.model.runtime.RuntimeNonElementRef;
import com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import com.sun.xml.bind.v2.runtime.Transducer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlList;

/* JADX INFO: loaded from: classes3.dex */
abstract class SingleTypePropertyInfoImpl<T, C, F, M> extends PropertyInfoImpl<T, C, F, M> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Accessor acc;
    private NonElement<T, C> type;
    private Transducer xducer;

    public PropertyInfo<T, C> getSource() {
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SingleTypePropertyInfoImpl(ClassInfoImpl<T, C, F, M> classInfoImpl, PropertySeed<T, C, F, M> propertySeed) {
        super(classInfoImpl, propertySeed);
        if (this instanceof RuntimePropertyInfo) {
            Accessor accessor = ((RuntimeClassInfoImpl.RuntimePropertySeed) propertySeed).getAccessor();
            if (getAdapter() != null && !isCollection()) {
                accessor = accessor.adapt(((RuntimePropertyInfo) this).getAdapter());
            }
            this.acc = accessor;
            return;
        }
        this.acc = null;
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public List<? extends NonElement<T, C>> ref() {
        return Collections.singletonList(getTarget());
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public NonElement<T, C> getTarget() {
        if (this.type == null) {
            this.type = this.parent.builder.getTypeInfo((TypeT) getIndividualType(), this);
        }
        return this.type;
    }

    @Override // com.sun.xml.bind.v2.model.impl.PropertyInfoImpl
    public void link() {
        super.link();
        if (!NonElement.ANYTYPE_NAME.equals(this.type.getTypeName()) && !this.type.isSimpleType() && id() != ID.IDREF) {
            this.parent.builder.reportError(new IllegalAnnotationException(Messages.SIMPLE_TYPE_IS_REQUIRED.format(new Object[0]), this.seed));
        }
        if (isCollection() || !this.seed.hasAnnotation(XmlList.class)) {
            return;
        }
        this.parent.builder.reportError(new IllegalAnnotationException(Messages.XMLLIST_ON_SINGLE_PROPERTY.format(new Object[0]), this));
    }

    public Accessor getAccessor() {
        return this.acc;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Transducer getTransducer() {
        if (this.xducer == null) {
            Transducer transducerCreateTransducer = RuntimeModelBuilder.createTransducer((RuntimeNonElementRef) this);
            this.xducer = transducerCreateTransducer;
            if (transducerCreateTransducer == null) {
                this.xducer = RuntimeBuiltinLeafInfoImpl.STRING;
            }
        }
        return this.xducer;
    }
}
