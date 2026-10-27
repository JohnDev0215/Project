package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.core.BuiltinLeafInfo;
import com.sun.xml.bind.v2.model.core.Element;
import com.sun.xml.bind.v2.model.nav.Navigator;
import com.sun.xml.bind.v2.runtime.Location;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public class BuiltinLeafInfoImpl<TypeT, ClassDeclT> extends LeafInfoImpl<TypeT, ClassDeclT> implements BuiltinLeafInfo<TypeT, ClassDeclT> {
    private final QName[] typeNames;

    @Override // com.sun.xml.bind.v2.model.core.MaybeElement
    public final Element<TypeT, ClassDeclT> asElement() {
        return null;
    }

    @Override // com.sun.xml.bind.v2.model.core.MaybeElement
    public final QName getElementName() {
        return null;
    }

    @Override // com.sun.xml.bind.v2.model.core.MaybeElement
    public final boolean isElement() {
        return false;
    }

    @Override // com.sun.xml.bind.v2.model.impl.LeafInfoImpl, com.sun.xml.bind.v2.model.annotation.Locatable
    public /* bridge */ /* synthetic */ Location getLocation() {
        return super.getLocation();
    }

    @Override // com.sun.xml.bind.v2.model.impl.LeafInfoImpl, com.sun.xml.bind.v2.model.core.TypeInfo
    public /* bridge */ /* synthetic */ Object getType() {
        return super.getType();
    }

    @Override // com.sun.xml.bind.v2.model.impl.LeafInfoImpl, com.sun.xml.bind.v2.model.core.NonElement
    public /* bridge */ /* synthetic */ QName getTypeName() {
        return super.getTypeName();
    }

    @Override // com.sun.xml.bind.v2.model.impl.LeafInfoImpl, com.sun.xml.bind.v2.model.annotation.Locatable
    public /* bridge */ /* synthetic */ Locatable getUpstream() {
        return super.getUpstream();
    }

    @Override // com.sun.xml.bind.v2.model.impl.LeafInfoImpl, com.sun.xml.bind.v2.model.core.NonElement
    public /* bridge */ /* synthetic */ boolean isSimpleType() {
        return super.isSimpleType();
    }

    @Override // com.sun.xml.bind.v2.model.impl.LeafInfoImpl, com.sun.xml.bind.v2.runtime.Location
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    protected BuiltinLeafInfoImpl(TypeT typet, QName... qNameArr) {
        super(typet, qNameArr.length > 0 ? qNameArr[0] : null);
        this.typeNames = qNameArr;
    }

    public final QName[] getTypeNames() {
        return this.typeNames;
    }

    public static <TypeT, ClassDeclT> Map<TypeT, BuiltinLeafInfoImpl<TypeT, ClassDeclT>> createLeaves(Navigator<TypeT, ClassDeclT, ?, ?> navigator) {
        HashMap map = new HashMap();
        for (RuntimeBuiltinLeafInfoImpl<?> runtimeBuiltinLeafInfoImpl : RuntimeBuiltinLeafInfoImpl.builtinBeanInfos) {
            TypeT typetRef = navigator.ref(runtimeBuiltinLeafInfoImpl.getClazz());
            map.put(typetRef, new BuiltinLeafInfoImpl(typetRef, runtimeBuiltinLeafInfoImpl.getTypeNames()));
        }
        return map;
    }
}
