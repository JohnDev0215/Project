package com.amazon.avod.ads.parser.vmap;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class VmapBreakType {
    private final Set<BreakType> mBreakTypes;

    public enum BreakType {
        linear,
        nonlinear,
        display
    }

    VmapBreakType(Set<BreakType> set) {
        this.mBreakTypes = set;
    }

    public String toString() {
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(3);
        for (BreakType breakType : BreakType.values()) {
            if (this.mBreakTypes.contains(breakType)) {
                arrayListNewArrayListWithCapacity.add(breakType.name());
            }
        }
        return Joiner.on(",").join(arrayListNewArrayListWithCapacity);
    }

    public static VmapBreakType parseXmlString(String str) {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        for (String str2 : str.split(",")) {
            try {
                builder.add(BreakType.valueOf(str2.trim()));
            } catch (IllegalArgumentException unused) {
            }
        }
        return new VmapBreakType(builder.build());
    }
}
