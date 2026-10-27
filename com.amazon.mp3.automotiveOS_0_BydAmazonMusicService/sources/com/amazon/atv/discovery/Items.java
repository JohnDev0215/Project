package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Action;
import com.amazon.atv.discovery.Item;
import com.amazon.atv.discovery.ItemsBase;
import com.amazon.atv.discovery.PaginationAction;
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
public class Items extends ItemsBase {
    public final Optional<ImmutableList<Action>> itemActionList;
    public final int maxItems;
    public final int numItems;
    public final Optional<PaginationAction> paginationLink;
    public final int startIndex;

    private Items(Builder builder) {
        super(builder);
        this.itemActionList = Optional.fromNullable(builder.itemActionList);
        this.startIndex = builder.startIndex;
        this.numItems = builder.numItems;
        this.maxItems = builder.maxItems;
        this.paginationLink = Optional.fromNullable(builder.paginationLink);
    }

    @Override // com.amazon.atv.discovery.ItemsBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Items)) {
            return false;
        }
        Items items = (Items) obj;
        return super.equals(obj) && Objects.equal(this.itemActionList, items.itemActionList) && Objects.equal(Integer.valueOf(this.startIndex), Integer.valueOf(items.startIndex)) && Objects.equal(Integer.valueOf(this.numItems), Integer.valueOf(items.numItems)) && Objects.equal(Integer.valueOf(this.maxItems), Integer.valueOf(items.maxItems)) && Objects.equal(this.paginationLink, items.paginationLink);
    }

    @Override // com.amazon.atv.discovery.ItemsBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.itemActionList, Integer.valueOf(this.startIndex), Integer.valueOf(this.numItems), Integer.valueOf(this.maxItems), this.paginationLink);
    }

    @Override // com.amazon.atv.discovery.ItemsBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("itemActionList", this.itemActionList).add("startIndex", this.startIndex).add("numItems", this.numItems).add("maxItems", this.maxItems).add("paginationLink", this.paginationLink).toString();
    }

    @NotThreadSafe
    public static class Builder extends ItemsBase.Builder {
        public ImmutableList<Action> itemActionList;
        public int maxItems;
        public int numItems;
        public PaginationAction paginationLink;
        public int startIndex;

        public Items build() {
            return new Items(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Items> {
        private final ListParser<Action> mActionListParser;
        private final ListParser<Item> mItemListParser;
        private final PaginationAction.Parser mPaginationActionParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPaginationActionParser = new PaginationAction.Parser(objectMapper);
            this.mActionListParser = ListParser.newParser(new Action.Parser(objectMapper));
            this.mItemListParser = ListParser.newParser(new Item.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Items parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Items:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Items parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Items:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Items parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Items");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1909092390:
                            if (next.equals("numItems")) {
                                b = 4;
                            }
                            break;
                        case -1593646704:
                            if (next.equals("startIndex")) {
                                b = 3;
                            }
                            break;
                        case 142868148:
                            if (next.equals("paginationLink")) {
                                b = 6;
                            }
                            break;
                        case 184316775:
                            if (next.equals("itemActionList")) {
                                b = 0;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 2;
                            }
                            break;
                        case 387780796:
                            if (next.equals("maxItems")) {
                                b = 5;
                            }
                            break;
                        case 1177280081:
                            if (next.equals("itemList")) {
                                b = 1;
                            }
                            break;
                    }
                    ImmutableList<Action> immutableList = null;
                    PaginationAction paginationAction = null;
                    ImmutableList<Item> immutableList2 = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                immutableList = this.mActionListParser.parse(jsonNode2);
                            }
                            builder.itemActionList = immutableList;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                immutableList2 = this.mItemListParser.parse(jsonNode2);
                            }
                            builder.itemList = immutableList2;
                            break;
                        case 2:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 3:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field startIndex can't be null");
                            }
                            builder.startIndex = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 4:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field numItems can't be null");
                            }
                            builder.numItems = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 5:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field maxItems can't be null");
                            }
                            builder.maxItems = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                paginationAction = this.mPaginationActionParser.parse(jsonNode2);
                            }
                            builder.paginationLink = paginationAction;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Items so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.itemList, this, "itemList");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.numItems), this, "numItems");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.maxItems), this, "maxItems");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Items parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1909092390:
                                    if (currentName.equals("numItems")) {
                                        b = 4;
                                    }
                                    break;
                                case -1593646704:
                                    if (currentName.equals("startIndex")) {
                                        b = 3;
                                    }
                                    break;
                                case 142868148:
                                    if (currentName.equals("paginationLink")) {
                                        b = 6;
                                    }
                                    break;
                                case 184316775:
                                    if (currentName.equals("itemActionList")) {
                                        b = 0;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 2;
                                    }
                                    break;
                                case 387780796:
                                    if (currentName.equals("maxItems")) {
                                        b = 5;
                                    }
                                    break;
                                case 1177280081:
                                    if (currentName.equals("itemList")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            ImmutableList<Action> immutableList = null;
                            PaginationAction paginationAction = null;
                            ImmutableList<Item> immutableList2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mActionListParser.parse(jsonParser);
                                    }
                                    builder.itemActionList = immutableList;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mItemListParser.parse(jsonParser);
                                    }
                                    builder.itemList = immutableList2;
                                    break;
                                case 2:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 3:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field startIndex can't be null");
                                    }
                                    builder.startIndex = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 4:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field numItems can't be null");
                                    }
                                    builder.numItems = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 5:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field maxItems can't be null");
                                    }
                                    builder.maxItems = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        paginationAction = this.mPaginationActionParser.parse(jsonParser);
                                    }
                                    builder.paginationLink = paginationAction;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Items so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.itemList, this, "itemList");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.numItems), this, "numItems");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.maxItems), this, "maxItems");
                    return builder.build();
                }
            }
        }
    }
}
