package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.Item;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class IdBasedItem {
    public final String id;
    public final Item item;

    private IdBasedItem(Builder builder) {
        this.item = (Item) Preconditions.checkNotNull(builder.item, "Unexpected null field: item");
        this.id = (String) Preconditions.checkNotNull(builder.id, "Unexpected null field: id");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IdBasedItem)) {
            return false;
        }
        IdBasedItem idBasedItem = (IdBasedItem) obj;
        return Objects.equal(this.item, idBasedItem.item) && Objects.equal(this.id, idBasedItem.id);
    }

    public int hashCode() {
        return Objects.hashCode(this.item, this.id);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("item", this.item).add("id", this.id).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String id;
        public Item item;

        public IdBasedItem build() {
            return new IdBasedItem(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<IdBasedItem> {
        private final Item.Parser mItemParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mItemParser = new Item.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public IdBasedItem parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("IdBasedItem:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public IdBasedItem parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("IdBasedItem:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private IdBasedItem parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "IdBasedItem");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 3355) {
                        if (iHashCode == 3242771 && next.equals("item")) {
                            b = 0;
                        }
                    } else if (next.equals("id")) {
                        b = 1;
                    }
                    String str = null;
                    Item item = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            item = this.mItemParser.parse(jsonNode2);
                        }
                        builder.item = item;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing IdBasedItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.item, this, "item");
            JsonParsingUtils.checkNotNull(builder.id, this, "id");
            return builder.build();
        }

        @Nonnull
        private IdBasedItem parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 3355) {
                                if (iHashCode == 3242771 && currentName.equals("item")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("id")) {
                                b = 1;
                            }
                            String str = null;
                            Item item = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    item = this.mItemParser.parse(jsonParser);
                                }
                                builder.item = item;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.id = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing IdBasedItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.item, this, "item");
                    JsonParsingUtils.checkNotNull(builder.id, this, "id");
                    return builder.build();
                }
            }
        }
    }
}
