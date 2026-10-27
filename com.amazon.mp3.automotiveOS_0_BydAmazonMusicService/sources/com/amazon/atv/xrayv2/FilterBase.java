package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FilterBase {
    public final String filterKey;
    public final String text;
    public final FilterType type;
    public final int version;

    @NotThreadSafe
    public static abstract class Builder {
        public String filterKey;
        public String text;
        public FilterType type;
        public int version;
    }

    protected FilterBase(Builder builder) {
        this.text = (String) Preconditions.checkNotNull(builder.text, "Unexpected null field: text");
        this.type = (FilterType) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
        this.version = builder.version;
        this.filterKey = (String) Preconditions.checkNotNull(builder.filterKey, "Unexpected null field: filterKey");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FilterBase)) {
            return false;
        }
        FilterBase filterBase = (FilterBase) obj;
        return Objects.equal(this.text, filterBase.text) && Objects.equal(this.type, filterBase.type) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(filterBase.version)) && Objects.equal(this.filterKey, filterBase.filterKey);
    }

    public int hashCode() {
        return Objects.hashCode(this.text, this.type, Integer.valueOf(this.version), this.filterKey);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("text", this.text).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("version", this.version).add("filterKey", this.filterKey).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<FilterBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.FILTERBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
