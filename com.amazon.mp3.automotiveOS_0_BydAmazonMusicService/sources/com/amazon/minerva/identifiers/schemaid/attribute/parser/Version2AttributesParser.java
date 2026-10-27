package com.amazon.minerva.identifiers.schemaid.attribute.parser;

import com.amazon.minerva.identifiers.schemaid.attribute.attributes.AttributeEnumV2;
import com.amazon.minerva.identifiers.schemaid.attribute.attributes.VersionedAttributes;
import java.util.BitSet;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class Version2AttributesParser extends BaseAttributesParser {
    public static VersionedAttributes parseAttributes(String str) {
        VersionedAttributes versionedAttributes = new VersionedAttributes();
        BitSet bitSetCreateBitSet = BaseAttributesParser.createBitSet(str);
        Objects.requireNonNull(bitSetCreateBitSet, "attrBitSet can not be null");
        AttributeEnumV2 attributeEnumV2 = AttributeEnumV2.ALLOW_DSN_INFO;
        versionedAttributes.setBooleanValue(attributeEnumV2, Boolean.valueOf(BaseAttributesParser.getBoolean(bitSetCreateBitSet, attributeEnumV2.getStartIndex())));
        AttributeEnumV2 attributeEnumV22 = AttributeEnumV2.ALLOW_CUSTOMER_INFO;
        versionedAttributes.setBooleanValue(attributeEnumV22, Boolean.valueOf(BaseAttributesParser.getBoolean(bitSetCreateBitSet, attributeEnumV22.getStartIndex())));
        AttributeEnumV2 attributeEnumV23 = AttributeEnumV2.ALLOW_CHILD_PROFILE;
        versionedAttributes.setBooleanValue(attributeEnumV23, Boolean.valueOf(BaseAttributesParser.getBoolean(bitSetCreateBitSet, attributeEnumV23.getStartIndex())));
        AttributeEnumV2 attributeEnumV24 = AttributeEnumV2.EXEMPT_FROM_OPT_OUT;
        versionedAttributes.setBooleanValue(attributeEnumV24, Boolean.valueOf(BaseAttributesParser.getBoolean(bitSetCreateBitSet, attributeEnumV24.getStartIndex())));
        AttributeEnumV2 attributeEnumV25 = AttributeEnumV2.METRIC_BUSINESS_TYPE;
        versionedAttributes.setIntegerValue(attributeEnumV25, Integer.valueOf(BaseAttributesParser.getInteger(bitSetCreateBitSet, attributeEnumV25.getStartIndex(), attributeEnumV25.getEndIndex())));
        AttributeEnumV2 attributeEnumV26 = AttributeEnumV2.UPLOAD_PRIORITY;
        versionedAttributes.setIntegerValue(attributeEnumV26, Integer.valueOf(BaseAttributesParser.getInteger(bitSetCreateBitSet, attributeEnumV26.getStartIndex(), attributeEnumV26.getEndIndex())));
        AttributeEnumV2 attributeEnumV27 = AttributeEnumV2.STORAGE_PRIORITY;
        versionedAttributes.setIntegerValue(attributeEnumV27, Integer.valueOf(BaseAttributesParser.getInteger(bitSetCreateBitSet, attributeEnumV27.getStartIndex(), attributeEnumV27.getEndIndex())));
        AttributeEnumV2 attributeEnumV28 = AttributeEnumV2.SCHEMA_REVISION;
        versionedAttributes.setIntegerValue(attributeEnumV28, Integer.valueOf(BaseAttributesParser.getInteger(bitSetCreateBitSet, attributeEnumV28.getStartIndex(), attributeEnumV28.getEndIndex())));
        return versionedAttributes;
    }

    public static String getAttributesHexString(VersionedAttributes versionedAttributes) {
        BitSet bitSetCreateEmptyBitSet = BaseAttributesParser.createEmptyBitSet();
        AttributeEnumV2 attributeEnumV2 = AttributeEnumV2.ALLOW_DSN_INFO;
        boolean zBooleanValue = versionedAttributes.getBooleanValue(attributeEnumV2).booleanValue();
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, zBooleanValue ? 1 : 0, attributeEnumV2.getStartIndex(), attributeEnumV2.getEndIndex());
        AttributeEnumV2 attributeEnumV22 = AttributeEnumV2.ALLOW_CUSTOMER_INFO;
        boolean zBooleanValue2 = versionedAttributes.getBooleanValue(attributeEnumV22).booleanValue();
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, zBooleanValue2 ? 1 : 0, attributeEnumV22.getStartIndex(), attributeEnumV22.getEndIndex());
        AttributeEnumV2 attributeEnumV23 = AttributeEnumV2.ALLOW_CHILD_PROFILE;
        boolean zBooleanValue3 = versionedAttributes.getBooleanValue(attributeEnumV23).booleanValue();
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, zBooleanValue3 ? 1 : 0, attributeEnumV23.getStartIndex(), attributeEnumV23.getEndIndex());
        AttributeEnumV2 attributeEnumV24 = AttributeEnumV2.EXEMPT_FROM_OPT_OUT;
        boolean zBooleanValue4 = versionedAttributes.getBooleanValue(attributeEnumV24).booleanValue();
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, zBooleanValue4 ? 1 : 0, attributeEnumV24.getStartIndex(), attributeEnumV24.getEndIndex());
        AttributeEnumV2 attributeEnumV25 = AttributeEnumV2.METRIC_BUSINESS_TYPE;
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, versionedAttributes.getIntegerValue(attributeEnumV25).intValue(), attributeEnumV25.getStartIndex(), attributeEnumV25.getEndIndex());
        AttributeEnumV2 attributeEnumV26 = AttributeEnumV2.UPLOAD_PRIORITY;
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, versionedAttributes.getIntegerValue(attributeEnumV26).intValue(), attributeEnumV26.getStartIndex(), attributeEnumV26.getEndIndex());
        AttributeEnumV2 attributeEnumV27 = AttributeEnumV2.STORAGE_PRIORITY;
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, versionedAttributes.getIntegerValue(attributeEnumV27).intValue(), attributeEnumV27.getStartIndex(), attributeEnumV27.getEndIndex());
        AttributeEnumV2 attributeEnumV28 = AttributeEnumV2.SCHEMA_REVISION;
        BaseAttributesParser.setBitPositionsForValue(bitSetCreateEmptyBitSet, versionedAttributes.getIntegerValue(attributeEnumV28).intValue(), attributeEnumV28.getStartIndex(), attributeEnumV28.getEndIndex());
        return BaseAttributesParser.getAttributesHexString(bitSetCreateEmptyBitSet);
    }
}
