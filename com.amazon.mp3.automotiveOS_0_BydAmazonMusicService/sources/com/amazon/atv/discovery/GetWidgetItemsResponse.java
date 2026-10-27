package com.amazon.atv.discovery;

import com.amazon.atv.discovery.BaseResponse;
import com.amazon.atv.discovery.DebugAttribute;
import com.amazon.atv.discovery.ItemsBase;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetWidgetItemsResponse extends BaseResponse {
    public final ItemsBase items;

    private GetWidgetItemsResponse(Builder builder) {
        super(builder);
        this.items = (ItemsBase) Preconditions.checkNotNull(builder.items, "Unexpected null field: items");
    }

    @Override // com.amazon.atv.discovery.BaseResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GetWidgetItemsResponse) {
            return super.equals(obj) && Objects.equal(this.items, ((GetWidgetItemsResponse) obj).items);
        }
        return false;
    }

    @Override // com.amazon.atv.discovery.BaseResponse
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.items);
    }

    @Override // com.amazon.atv.discovery.BaseResponse
    public String toString() {
        return MoreObjects.toStringHelper(this).add(ContextMappingConstants.ITEMS, this.items).toString();
    }

    @NotThreadSafe
    public static class Builder extends BaseResponse.Builder {
        public ItemsBase items;

        public GetWidgetItemsResponse build() {
            return new GetWidgetItemsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetWidgetItemsResponse> {
        private final MapParser<DebugType, ImmutableList<DebugAttribute>> mDebugAttributesParser;
        private final ItemsBase.Parser mItemsBaseParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDebugAttributesParser = MapParser.newParser(EnumParser.newParser(DebugType.class), ListParser.newParser(new DebugAttribute.Parser(objectMapper)));
            this.mItemsBaseParser = new ItemsBase.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetWidgetItemsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetWidgetItemsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetWidgetItemsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetWidgetItemsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetWidgetItemsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetWidgetItemsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 100526016) {
                        if (iHashCode == 2140311562 && next.equals("debugAttributes")) {
                            b = 1;
                        }
                    } else if (next.equals(ContextMappingConstants.ITEMS)) {
                        b = 0;
                    }
                    ImmutableMap<DebugType, ImmutableList<DebugAttribute>> immutableMap = null;
                    ItemsBase itemsBase = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            itemsBase = this.mItemsBaseParser.parse(jsonNode2);
                        }
                        builder.items = itemsBase;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mDebugAttributesParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = immutableMap;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetWidgetItemsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.items, this, ContextMappingConstants.ITEMS);
            return builder.build();
        }

        @Nonnull
        private GetWidgetItemsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 100526016) {
                                if (iHashCode == 2140311562 && currentName.equals("debugAttributes")) {
                                    b = 1;
                                }
                            } else if (currentName.equals(ContextMappingConstants.ITEMS)) {
                                b = 0;
                            }
                            ImmutableMap<DebugType, ImmutableList<DebugAttribute>> immutableMap = null;
                            ItemsBase itemsBase = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    itemsBase = this.mItemsBaseParser.parse(jsonParser);
                                }
                                builder.items = itemsBase;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mDebugAttributesParser.parse(jsonParser);
                                }
                                builder.debugAttributes = immutableMap;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetWidgetItemsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.items, this, ContextMappingConstants.ITEMS);
                    return builder.build();
                }
            }
        }
    }
}
