package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.core.MapPropertyInfo;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.core.TypeInfo;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
class MapPropertyInfoImpl<T, C, F, M> extends PropertyInfoImpl<T, C, F, M> implements MapPropertyInfo<T, C> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final T keyType;
    private NonElement<T, C> keyTypeInfo;
    private boolean nil;
    private final T valueType;
    private NonElement<T, C> valueTypeInfo;
    private final QName xmlName;

    public MapPropertyInfoImpl(ClassInfoImpl<T, C, F, M> classInfoImpl, PropertySeed<T, C, F, M> propertySeed) {
        super(classInfoImpl, propertySeed);
        XmlElementWrapper xmlElementWrapper = (XmlElementWrapper) propertySeed.readAnnotation(XmlElementWrapper.class);
        this.xmlName = calcXmlName(xmlElementWrapper);
        this.nil = xmlElementWrapper != null && xmlElementWrapper.nillable();
        T baseClass = nav().getBaseClass(getRawType(), nav().asDecl(Map.class));
        if (nav().isParameterizedType(baseClass)) {
            this.keyType = nav().getTypeArgument(baseClass, 0);
            this.valueType = nav().getTypeArgument(baseClass, 1);
        } else {
            T tRef = nav().ref(Object.class);
            this.valueType = tRef;
            this.keyType = tRef;
        }
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public Collection<? extends TypeInfo<T, C>> ref() {
        return Arrays.asList(getKeyType(), getValueType());
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public final PropertyKind kind() {
        return PropertyKind.MAP;
    }

    @Override // com.sun.xml.bind.v2.model.core.MapPropertyInfo
    public QName getXmlName() {
        return this.xmlName;
    }

    @Override // com.sun.xml.bind.v2.model.core.MapPropertyInfo
    public boolean isCollectionNillable() {
        return this.nil;
    }

    @Override // com.sun.xml.bind.v2.model.core.MapPropertyInfo
    public NonElement<T, C> getKeyType() {
        if (this.keyTypeInfo == null) {
            this.keyTypeInfo = getTarget(this.keyType);
        }
        return this.keyTypeInfo;
    }

    @Override // com.sun.xml.bind.v2.model.core.MapPropertyInfo
    public NonElement<T, C> getValueType() {
        if (this.valueTypeInfo == null) {
            this.valueTypeInfo = getTarget(this.valueType);
        }
        return this.valueTypeInfo;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public NonElement<T, C> getTarget(T t) {
        return this.parent.builder.getTypeInfo((TypeT) t, this);
    }
}
