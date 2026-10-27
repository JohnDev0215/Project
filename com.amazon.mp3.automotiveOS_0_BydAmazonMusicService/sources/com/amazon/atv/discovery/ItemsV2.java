package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Item;
import com.amazon.atv.discovery.ItemsBase;
import com.amazon.atv.discovery.PaginationActionBase;
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
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class ItemsV2 extends ItemsBase {
    public final int maxItems;
    public final Optional<PaginationActionBase> paginationLink;

    private ItemsV2(Builder builder) {
        super(builder);
        this.maxItems = builder.maxItems;
        this.paginationLink = Optional.fromNullable(builder.paginationLink);
    }

    @Override // com.amazon.atv.discovery.ItemsBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ItemsV2)) {
            return false;
        }
        ItemsV2 itemsV2 = (ItemsV2) obj;
        return super.equals(obj) && Objects.equal(Integer.valueOf(this.maxItems), Integer.valueOf(itemsV2.maxItems)) && Objects.equal(this.paginationLink, itemsV2.paginationLink);
    }

    @Override // com.amazon.atv.discovery.ItemsBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), Integer.valueOf(this.maxItems), this.paginationLink);
    }

    @Override // com.amazon.atv.discovery.ItemsBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("maxItems", this.maxItems).add("paginationLink", this.paginationLink).toString();
    }

    @NotThreadSafe
    public static class Builder extends ItemsBase.Builder {
        public int maxItems;
        public PaginationActionBase paginationLink;

        public ItemsV2 build() {
            return new ItemsV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ItemsV2> {
        private final ListParser<Item> mItemListParser;
        private final PaginationActionBase.Parser mPaginationActionBaseParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mItemListParser = ListParser.newParser(new Item.Parser(objectMapper));
            this.mPaginationActionBaseParser = new PaginationActionBase.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ItemsV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ItemsV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ItemsV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ItemsV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ItemsV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ItemsV2");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case 142868148:
                            if (next.equals("paginationLink")) {
                                b = 3;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 2;
                            }
                            break;
                        case 387780796:
                            if (next.equals("maxItems")) {
                                b = 0;
                            }
                            break;
                        case 1177280081:
                            if (next.equals("itemList")) {
                                b = 1;
                            }
                            break;
                    }
                    if (b != 0) {
                        PaginationActionBase paginationActionBase = null;
                        ImmutableList<Item> immutableList = null;
                        if (b == 1) {
                            if (!jsonNode2.isNull()) {
                                immutableList = this.mItemListParser.parse(jsonNode2);
                            }
                            builder.itemList = immutableList;
                        } else if (b == 2) {
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        } else if (b == 3) {
                            if (!jsonNode2.isNull()) {
                                paginationActionBase = this.mPaginationActionBaseParser.parse(jsonNode2);
                            }
                            builder.paginationLink = paginationActionBase;
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field maxItems can't be null");
                        }
                        builder.maxItems = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ItemsV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.maxItems), this, "maxItems");
            JsonParsingUtils.checkNotNull(builder.itemList, this, "itemList");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ItemsV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            switch (currentName.hashCode()) {
                                case 142868148:
                                    if (currentName.equals("paginationLink")) {
                                        b = 3;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 2;
                                    }
                                    break;
                                case 387780796:
                                    if (currentName.equals("maxItems")) {
                                        b = 0;
                                    }
                                    break;
                                case 1177280081:
                                    if (currentName.equals("itemList")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            if (b != 0) {
                                PaginationActionBase paginationActionBase = null;
                                ImmutableList<Item> immutableList = null;
                                if (b == 1) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mItemListParser.parse(jsonParser);
                                    }
                                    builder.itemList = immutableList;
                                } else if (b == 2) {
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                } else if (b == 3) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        paginationActionBase = this.mPaginationActionBaseParser.parse(jsonParser);
                                    }
                                    builder.paginationLink = paginationActionBase;
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field maxItems can't be null");
                                }
                                builder.maxItems = SimpleParsers.parsePrimitiveInt(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ItemsV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.maxItems), this, "maxItems");
                    JsonParsingUtils.checkNotNull(builder.itemList, this, "itemList");
                    return builder.build();
                }
            }
        }
    }
}
