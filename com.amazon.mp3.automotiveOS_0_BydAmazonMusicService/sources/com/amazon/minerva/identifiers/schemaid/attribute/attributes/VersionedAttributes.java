package com.amazon.minerva.identifiers.schemaid.attribute.attributes;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class VersionedAttributes {
    protected final Map<IAttributeEnum, Boolean> booleanMap = new HashMap();
    protected final Map<IAttributeEnum, Integer> integerMap = new HashMap();

    public Boolean getBooleanValue(IAttributeEnum iAttributeEnum) {
        if (this.booleanMap.containsKey(iAttributeEnum)) {
            return this.booleanMap.get(iAttributeEnum);
        }
        throw new IllegalStateException("Unexpected value: " + iAttributeEnum);
    }

    public Integer getIntegerValue(IAttributeEnum iAttributeEnum) {
        if (this.integerMap.containsKey(iAttributeEnum)) {
            return this.integerMap.get(iAttributeEnum);
        }
        throw new IllegalStateException("Unexpected value: " + iAttributeEnum);
    }

    public void setBooleanValue(IAttributeEnum iAttributeEnum, Boolean bool) throws IllegalStateException {
        this.booleanMap.put(iAttributeEnum, bool);
    }

    public void setIntegerValue(IAttributeEnum iAttributeEnum, Integer num) throws IllegalStateException {
        this.integerMap.put(iAttributeEnum, num);
    }
}
