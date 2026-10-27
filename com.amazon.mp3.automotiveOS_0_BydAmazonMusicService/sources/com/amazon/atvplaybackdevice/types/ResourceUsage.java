package com.amazon.atvplaybackdevice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ResourceUsage implements NamedEnum {
    OptimisticFulfillment("OptimisticFulfillment"),
    CacheResources("CacheResources"),
    ImmediateConsumption("ImmediateConsumption");

    private final String strValue;

    ResourceUsage(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ResourceUsage forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ResourceUsage resourceUsage : values()) {
            if (resourceUsage.strValue.equals(str)) {
                return resourceUsage;
            }
        }
        return null;
    }
}
