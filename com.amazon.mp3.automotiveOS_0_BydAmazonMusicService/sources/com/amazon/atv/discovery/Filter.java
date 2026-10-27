package com.amazon.atv.discovery;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Filter {
    public final Optional<String> debugAttribute;
    public final String filterKey;
    public final String text;
    public final FilterType type;
    public final String version;

    @NotThreadSafe
    public static abstract class Builder {
        public String debugAttribute;
        public String filterKey;
        public String text;
        public FilterType type;
        public String version;
    }

    protected Filter(Builder builder) {
        this.text = (String) Preconditions.checkNotNull(builder.text, "Unexpected null field: text");
        this.type = (FilterType) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
        this.filterKey = (String) Preconditions.checkNotNull(builder.filterKey, "Unexpected null field: filterKey");
        this.debugAttribute = Optional.fromNullable(builder.debugAttribute);
        this.version = (String) Preconditions.checkNotNull(builder.version, "Unexpected null field: version");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Filter)) {
            return false;
        }
        Filter filter = (Filter) obj;
        return Objects.equal(this.text, filter.text) && Objects.equal(this.type, filter.type) && Objects.equal(this.filterKey, filter.filterKey) && Objects.equal(this.debugAttribute, filter.debugAttribute) && Objects.equal(this.version, filter.version);
    }

    public int hashCode() {
        return Objects.hashCode(this.text, this.type, this.filterKey, this.debugAttribute, this.version);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("text", this.text).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("filterKey", this.filterKey).add("debugAttribute", this.debugAttribute).add("version", this.version).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<Filter> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.FILTER_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
