package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Action;
import com.amazon.atv.discovery.Item;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Seed {
    public final Optional<ImmutableList<Action>> seedActions;
    public final ImmutableList<Item> seedItems;
    public final String seedText;

    private Seed(Builder builder) {
        this.seedItems = (ImmutableList) Preconditions.checkNotNull(builder.seedItems, "Unexpected null field: seedItems");
        this.seedActions = Optional.fromNullable(builder.seedActions);
        this.seedText = (String) Preconditions.checkNotNull(builder.seedText, "Unexpected null field: seedText");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Seed)) {
            return false;
        }
        Seed seed = (Seed) obj;
        return Objects.equal(this.seedItems, seed.seedItems) && Objects.equal(this.seedActions, seed.seedActions) && Objects.equal(this.seedText, seed.seedText);
    }

    public int hashCode() {
        return Objects.hashCode(this.seedItems, this.seedActions, this.seedText);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("seedItems", this.seedItems).add("seedActions", this.seedActions).add("seedText", this.seedText).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<Action> seedActions;
        public ImmutableList<Item> seedItems;
        public String seedText;

        public Seed build() {
            return new Seed(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Seed> {
        private final ListParser<Action> mActionListParser;
        private final ListParser<Item> mItemListParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mItemListParser = ListParser.newParser(new Item.Parser(objectMapper));
            this.mActionListParser = ListParser.newParser(new Action.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Seed parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Seed:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Seed parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Seed:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Seed parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Seed");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 608798671) {
                        if (iHashCode != 989783870) {
                            if (iHashCode == 1956913484 && next.equals("seedActions")) {
                                b = 1;
                            }
                        } else if (next.equals("seedText")) {
                            b = 2;
                        }
                    } else if (next.equals("seedItems")) {
                        b = 0;
                    }
                    String str = null;
                    ImmutableList<Item> immutableList = null;
                    ImmutableList<Action> immutableList2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mItemListParser.parse(jsonNode2);
                        }
                        builder.seedItems = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mActionListParser.parse(jsonNode2);
                        }
                        builder.seedActions = immutableList2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.seedText = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Seed so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.seedItems, this, "seedItems");
            JsonParsingUtils.checkNotNull(builder.seedText, this, "seedText");
            return builder.build();
        }

        @Nonnull
        private Seed parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 608798671) {
                                if (iHashCode != 989783870) {
                                    if (iHashCode == 1956913484 && currentName.equals("seedActions")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("seedText")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("seedItems")) {
                                b = 0;
                            }
                            String str = null;
                            ImmutableList<Item> immutableList = null;
                            ImmutableList<Action> immutableList2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mItemListParser.parse(jsonParser);
                                }
                                builder.seedItems = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mActionListParser.parse(jsonParser);
                                }
                                builder.seedActions = immutableList2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.seedText = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Seed so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.seedItems, this, "seedItems");
                    JsonParsingUtils.checkNotNull(builder.seedText, this, "seedText");
                    return builder.build();
                }
            }
        }
    }
}
