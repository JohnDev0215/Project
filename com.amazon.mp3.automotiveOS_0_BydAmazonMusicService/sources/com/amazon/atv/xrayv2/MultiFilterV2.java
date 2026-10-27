package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.FilterBase;
import com.amazon.atv.xrayv2.FilterValueBase;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class MultiFilterV2 extends FilterBase {
    public final ImmutableList<FilterValueBase> filterValueList;
    public final Optional<SelectionType> selection;

    private MultiFilterV2(Builder builder) {
        super(builder);
        this.selection = Optional.fromNullable(builder.selection);
        this.filterValueList = (ImmutableList) Preconditions.checkNotNull(builder.filterValueList, "Unexpected null field: filterValueList");
    }

    @Override // com.amazon.atv.xrayv2.FilterBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MultiFilterV2)) {
            return false;
        }
        MultiFilterV2 multiFilterV2 = (MultiFilterV2) obj;
        return super.equals(obj) && Objects.equal(this.selection, multiFilterV2.selection) && Objects.equal(this.filterValueList, multiFilterV2.filterValueList);
    }

    @Override // com.amazon.atv.xrayv2.FilterBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.selection, this.filterValueList);
    }

    @Override // com.amazon.atv.xrayv2.FilterBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("selection", this.selection).add("filterValueList", this.filterValueList).toString();
    }

    @NotThreadSafe
    public static class Builder extends FilterBase.Builder {
        public ImmutableList<FilterValueBase> filterValueList;
        public SelectionType selection;

        public MultiFilterV2 build() {
            return new MultiFilterV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<MultiFilterV2> {
        private final EnumParser<FilterType> mFilterTypeParser;
        private final ListParser<FilterValueBase> mFilterValueBaseListParser;
        private final EnumParser<SelectionType> mSelectionTypeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSelectionTypeParser = EnumParser.newParser(SelectionType.class);
            this.mFilterTypeParser = EnumParser.newParser(FilterType.class);
            this.mFilterValueBaseListParser = ListParser.newParser(new FilterValueBase.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public MultiFilterV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("MultiFilterV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public MultiFilterV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("MultiFilterV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:27:0x006f  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atv.xrayv2.MultiFilterV2 parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.databind.JsonNode r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 330
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.xrayv2.MultiFilterV2.Parser.parseInternal(com.fasterxml.jackson.databind.JsonNode):com.amazon.atv.xrayv2.MultiFilterV2");
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0079  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atv.xrayv2.MultiFilterV2 parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.core.JsonParser r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 332
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.xrayv2.MultiFilterV2.Parser.parseInternal(com.fasterxml.jackson.core.JsonParser):com.amazon.atv.xrayv2.MultiFilterV2");
        }
    }
}
