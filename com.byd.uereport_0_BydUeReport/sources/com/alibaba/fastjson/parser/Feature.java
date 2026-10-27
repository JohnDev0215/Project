package com.alibaba.fastjson.parser;

/* JADX INFO: loaded from: classes.dex */
public enum Feature {
    AutoCloseSource,
    AllowComment,
    AllowUnQuotedFieldNames,
    AllowSingleQuotes,
    InternFieldNames,
    AllowISO8601DateFormat,
    AllowArbitraryCommas,
    UseBigDecimal,
    IgnoreNotMatch,
    SortFeidFastMatch,
    DisableASM,
    DisableCircularReferenceDetect,
    InitStringFieldAsEmpty,
    SupportArrayToBean,
    OrderedField,
    DisableSpecialKeyDetect,
    UseObjectArray,
    SupportNonPublicField,
    IgnoreAutoType,
    DisableFieldSmartMatch,
    SupportAutoType,
    NonStringKeyAsString,
    CustomMapDeserializer,
    ErrorOnEnumNotMatch,
    SafeMode,
    TrimStringFieldValue;

    public final int mask = 1 << ordinal();

    Feature() {
    }

    public static int config(int i8, Feature feature, boolean z7) {
        return z7 ? i8 | feature.mask : i8 & (~feature.mask);
    }

    public static boolean isEnabled(int i8, Feature feature) {
        return (i8 & feature.mask) != 0;
    }

    public static int of(Feature[] featureArr) {
        if (featureArr == null) {
            return 0;
        }
        int i8 = 0;
        for (Feature feature : featureArr) {
            i8 |= feature.mask;
        }
        return i8;
    }

    public final int getMask() {
        return this.mask;
    }
}
