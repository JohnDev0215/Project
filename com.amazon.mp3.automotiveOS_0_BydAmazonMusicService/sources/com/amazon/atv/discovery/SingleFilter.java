package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Filter;
import com.amazon.atv.discovery.FilterValue;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class SingleFilter extends Filter {
    public final FilterValue filterValue;

    private SingleFilter(Builder builder) {
        super(builder);
        this.filterValue = (FilterValue) Preconditions.checkNotNull(builder.filterValue, "Unexpected null field: filterValue");
    }

    @Override // com.amazon.atv.discovery.Filter
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SingleFilter) {
            return super.equals(obj) && Objects.equal(this.filterValue, ((SingleFilter) obj).filterValue);
        }
        return false;
    }

    @Override // com.amazon.atv.discovery.Filter
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.filterValue);
    }

    @Override // com.amazon.atv.discovery.Filter
    public String toString() {
        return MoreObjects.toStringHelper(this).add("filterValue", this.filterValue).toString();
    }

    @NotThreadSafe
    public static class Builder extends Filter.Builder {
        public FilterValue filterValue;

        public SingleFilter build() {
            return new SingleFilter(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SingleFilter> {
        private final EnumParser<FilterType> mFilterTypeParser;
        private final FilterValue.Parser mFilterValueParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mFilterTypeParser = EnumParser.newParser(FilterType.class);
            this.mFilterValueParser = new FilterValue.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SingleFilter parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SingleFilter:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SingleFilter parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SingleFilter:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:27:0x006e  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atv.discovery.SingleFilter parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.databind.JsonNode r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 316
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.discovery.SingleFilter.Parser.parseInternal(com.fasterxml.jackson.databind.JsonNode):com.amazon.atv.discovery.SingleFilter");
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0078  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atv.discovery.SingleFilter parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.core.JsonParser r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 318
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.discovery.SingleFilter.Parser.parseInternal(com.fasterxml.jackson.core.JsonParser):com.amazon.atv.discovery.SingleFilter");
        }
    }
}
