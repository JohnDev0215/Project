package com.amazon.avod.xray;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
public class TimedItemChange {
    public final ItemChangeType changeType;
    public final String itemId;
    public final long timePosition;

    private TimedItemChange(Builder builder) {
        this.changeType = (ItemChangeType) Preconditions.checkNotNull(builder.changeType, "Unexpected null field: changeType");
        this.itemId = (String) Preconditions.checkNotNull(builder.itemId, "Unexpected null field: itemId");
        this.timePosition = builder.timePosition;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimedItemChange)) {
            return false;
        }
        TimedItemChange timedItemChange = (TimedItemChange) obj;
        return Objects.equal(this.changeType, timedItemChange.changeType) && Objects.equal(this.itemId, timedItemChange.itemId) && Objects.equal(Long.valueOf(this.timePosition), Long.valueOf(timedItemChange.timePosition));
    }

    public int hashCode() {
        return Objects.hashCode(this.changeType, this.itemId, Long.valueOf(this.timePosition));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("changeType", this.changeType).add("itemId", this.itemId).add("timePosition", this.timePosition).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ItemChangeType changeType;
        public String itemId;
        public long timePosition;

        public TimedItemChange build() {
            return new TimedItemChange(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TimedItemChange> {
        private final EnumParser<ItemChangeType> mItemChangeTypeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mItemChangeTypeParser = EnumParser.newParser(ItemChangeType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TimedItemChange parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimedItemChange:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TimedItemChange parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimedItemChange:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TimedItemChange parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TimedItemChange");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2131902710) {
                        if (iHashCode != -1178662002) {
                            if (iHashCode == 1090401046 && next.equals("timePosition")) {
                                b = 2;
                            }
                        } else if (next.equals("itemId")) {
                            b = 1;
                        }
                    } else if (next.equals("changeType")) {
                        b = 0;
                    }
                    String str = null;
                    ItemChangeType itemChangeType = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            itemChangeType = (ItemChangeType) this.mItemChangeTypeParser.parse(jsonNode2);
                        }
                        builder.changeType = itemChangeType;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.itemId = str;
                    } else if (b == 2) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field timePosition can't be null");
                        }
                        builder.timePosition = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TimedItemChange so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.changeType, this, "changeType");
            JsonParsingUtils.checkNotNull(builder.itemId, this, "itemId");
            JsonParsingUtils.checkNotNull(Long.valueOf(builder.timePosition), this, "timePosition");
            return builder.build();
        }

        @Nonnull
        private TimedItemChange parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -2131902710) {
                                if (iHashCode != -1178662002) {
                                    if (iHashCode == 1090401046 && currentName.equals("timePosition")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("itemId")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("changeType")) {
                                b = 0;
                            }
                            String str = null;
                            ItemChangeType itemChangeType = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    itemChangeType = (ItemChangeType) this.mItemChangeTypeParser.parse(jsonParser);
                                }
                                builder.changeType = itemChangeType;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.itemId = str;
                            } else if (b == 2) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field timePosition can't be null");
                                }
                                builder.timePosition = SimpleParsers.parsePrimitiveLong(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TimedItemChange so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.changeType, this, "changeType");
                    JsonParsingUtils.checkNotNull(builder.itemId, this, "itemId");
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.timePosition), this, "timePosition");
                    return builder.build();
                }
            }
        }
    }
}
