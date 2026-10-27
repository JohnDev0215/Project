package com.amazon.minerva.identifiers.schemaid.attribute.attributes;

/* JADX INFO: loaded from: classes3.dex */
public enum AttributeEnumV2 implements IAttributeEnum {
    ALLOW_DSN_INFO(0, 0),
    ALLOW_CUSTOMER_INFO(1, 1),
    ALLOW_CHILD_PROFILE(4, 4),
    EXEMPT_FROM_OPT_OUT(5, 5),
    METRIC_BUSINESS_TYPE(8, 10),
    UPLOAD_PRIORITY(16, 19),
    STORAGE_PRIORITY(20, 23),
    SCHEMA_REVISION(24, 31);

    private int endIndex;
    private int startIndex;

    AttributeEnumV2(int i, int i2) {
        this.startIndex = i;
        this.endIndex = i2;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }
}
