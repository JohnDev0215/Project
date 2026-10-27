package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.FilterBase;
import com.amazon.atv.xrayv2.FilterValueBase;
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
public class SingleFilterV2 extends FilterBase {
    public final FilterValueBase filterValue;

    private SingleFilterV2(Builder builder) {
        super(builder);
        this.filterValue = (FilterValueBase) Preconditions.checkNotNull(builder.filterValue, "Unexpected null field: filterValue");
    }

    @Override // com.amazon.atv.xrayv2.FilterBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SingleFilterV2) {
            return super.equals(obj) && Objects.equal(this.filterValue, ((SingleFilterV2) obj).filterValue);
        }
        return false;
    }

    @Override // com.amazon.atv.xrayv2.FilterBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.filterValue);
    }

    @Override // com.amazon.atv.xrayv2.FilterBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("filterValue", this.filterValue).toString();
    }

    @NotThreadSafe
    public static class Builder extends FilterBase.Builder {
        public FilterValueBase filterValue;

        public SingleFilterV2 build() {
            return new SingleFilterV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SingleFilterV2> {
        private final EnumParser<FilterType> mFilterTypeParser;
        private final FilterValueBase.Parser mFilterValueBaseParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mFilterTypeParser = EnumParser.newParser(FilterType.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mFilterValueBaseParser = new FilterValueBase.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SingleFilterV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SingleFilterV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SingleFilterV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SingleFilterV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atv.xrayv2.SingleFilterV2 parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.databind.JsonNode r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 294
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.xrayv2.SingleFilterV2.Parser.parseInternal(com.fasterxml.jackson.databind.JsonNode):com.amazon.atv.xrayv2.SingleFilterV2");
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:27:0x006e  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atv.xrayv2.SingleFilterV2 parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.core.JsonParser r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 298
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.xrayv2.SingleFilterV2.Parser.parseInternal(com.fasterxml.jackson.core.JsonParser):com.amazon.atv.xrayv2.SingleFilterV2");
        }
    }
}
