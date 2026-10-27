package com.amazon.atv.discovery;

import com.amazon.atv.discovery.SortBase;
import com.amazon.atv.discovery.SortsBase;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class SortsV2 extends SortsBase {
    public final ImmutableList<SortBase> sortList;

    private SortsV2(Builder builder) {
        super(builder);
        this.sortList = (ImmutableList) Preconditions.checkNotNull(builder.sortList, "Unexpected null field: sortList");
    }

    @Override // com.amazon.atv.discovery.SortsBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SortsV2) {
            return super.equals(obj) && Objects.equal(this.sortList, ((SortsV2) obj).sortList);
        }
        return false;
    }

    @Override // com.amazon.atv.discovery.SortsBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.sortList);
    }

    @Override // com.amazon.atv.discovery.SortsBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("sortList", this.sortList).toString();
    }

    @NotThreadSafe
    public static class Builder extends SortsBase.Builder {
        public ImmutableList<SortBase> sortList;

        public SortsV2 build() {
            return new SortsV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SortsV2> {
        private final ListParser<SortBase> mSortBaseListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSortBaseListParser = ListParser.newParser(new SortBase.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SortsV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SortsV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SortsV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SortsV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private SortsV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SortsV2");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 351608024) {
                        if (iHashCode == 1661606748 && next.equals("sortList")) {
                            b = 0;
                        }
                    } else if (next.equals("version")) {
                        b = 1;
                    }
                    if (b == 0) {
                        builder.sortList = jsonNode2.isNull() ? null : this.mSortBaseListParser.parse(jsonNode2);
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SortsV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.sortList, this, "sortList");
            return builder.build();
        }

        @Nonnull
        private SortsV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Builder builder = new Builder();
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            while (true) {
                JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                    if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                        String currentName = jsonParser.getCurrentName();
                        jsonParser.nextToken();
                        JsonToken currentToken = jsonParser.getCurrentToken();
                        byte b = -1;
                        try {
                            int iHashCode = currentName.hashCode();
                            if (iHashCode != 351608024) {
                                if (iHashCode == 1661606748 && currentName.equals("sortList")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("version")) {
                                b = 1;
                            }
                            if (b == 0) {
                                builder.sortList = currentToken == JsonToken.VALUE_NULL ? null : this.mSortBaseListParser.parse(jsonParser);
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field version can't be null");
                                }
                                builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SortsV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.sortList, this, "sortList");
                    return builder.build();
                }
            }
        }
    }
}
