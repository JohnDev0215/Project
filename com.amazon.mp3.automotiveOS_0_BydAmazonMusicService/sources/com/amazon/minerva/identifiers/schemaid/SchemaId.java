package com.amazon.minerva.identifiers.schemaid;

import com.amazon.minerva.identifiers.schemaid.attribute.attributes.VersionedAttributes;
import com.amazon.minerva.identifiers.schemaid.attribute.parser.Version2AttributesParser;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class SchemaId {
    private Integer formatVersion;
    private String identifier;
    private VersionedAttributes versionedAttributes;

    public SchemaId(String str) {
        Objects.requireNonNull(str, "Metric Schema ID cannot be empty.");
        String[] strArrSplitMetricSchemaId = splitMetricSchemaId(str);
        String str2 = strArrSplitMetricSchemaId[0];
        int i = Integer.parseInt(strArrSplitMetricSchemaId[1]);
        initialize(str2, i, parseControlBits(i, strArrSplitMetricSchemaId[2]));
    }

    private void initialize(String str, int i, VersionedAttributes versionedAttributes) {
        this.identifier = str;
        this.formatVersion = Integer.valueOf(i);
        this.versionedAttributes = versionedAttributes;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getControlBits() {
        return getControlBits(this.formatVersion.intValue(), this.versionedAttributes);
    }

    public VersionedAttributes getVersionedAttributes() {
        return this.versionedAttributes;
    }

    public String toString() {
        return getMetricSchemaId(this.identifier, this.formatVersion.intValue(), this.versionedAttributes);
    }

    private static String getMetricSchemaId(String str, int i, VersionedAttributes versionedAttributes) {
        return str + "/" + i + "/" + getControlBits(i, versionedAttributes);
    }

    private static String[] splitMetricSchemaId(String str) {
        Objects.requireNonNull(str, "Metric Schema ID cannot be empty.");
        String[] strArrSplit = str.split("/");
        if (strArrSplit.length == 3) {
            return strArrSplit;
        }
        throw new IllegalArgumentException("SchemaId string should have a format of luid/version/controlBits.");
    }

    protected static VersionedAttributes parseControlBits(int i, String str) {
        Objects.requireNonNull(str, "ControlBits can not be null");
        if (i == 2) {
            return Version2AttributesParser.parseAttributes(str);
        }
        throw new UnsupportedOperationException("Format version is not supported.");
    }

    protected static String getControlBits(int i, VersionedAttributes versionedAttributes) {
        if (i == 2) {
            return Version2AttributesParser.getAttributesHexString(versionedAttributes);
        }
        throw new UnsupportedOperationException("Format version is not supported.");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SchemaId schemaId = (SchemaId) obj;
        return Objects.equals(this.identifier, schemaId.identifier) && Objects.equals(this.formatVersion, schemaId.formatVersion) && Objects.equals(getControlBits(), schemaId.getControlBits());
    }

    public int hashCode() {
        return Objects.hash(this.identifier, this.formatVersion, getControlBits());
    }
}
