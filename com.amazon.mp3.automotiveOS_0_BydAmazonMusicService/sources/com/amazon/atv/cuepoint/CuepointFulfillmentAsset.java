package com.amazon.atv.cuepoint;

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
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class CuepointFulfillmentAsset {
    public final Optional<CardType> cardType;
    public final Optional<String> contentId;
    public final long duration;
    public final long playhead;

    private CuepointFulfillmentAsset(Builder builder) {
        this.duration = builder.duration;
        this.cardType = Optional.fromNullable(builder.cardType);
        this.playhead = builder.playhead;
        this.contentId = Optional.fromNullable(builder.contentId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CuepointFulfillmentAsset)) {
            return false;
        }
        CuepointFulfillmentAsset cuepointFulfillmentAsset = (CuepointFulfillmentAsset) obj;
        return Objects.equal(Long.valueOf(this.duration), Long.valueOf(cuepointFulfillmentAsset.duration)) && Objects.equal(this.cardType, cuepointFulfillmentAsset.cardType) && Objects.equal(Long.valueOf(this.playhead), Long.valueOf(cuepointFulfillmentAsset.playhead)) && Objects.equal(this.contentId, cuepointFulfillmentAsset.contentId);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.duration), this.cardType, Long.valueOf(this.playhead), this.contentId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("duration", this.duration).add("cardType", this.cardType).add("playhead", this.playhead).add("contentId", this.contentId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public CardType cardType;
        public String contentId;
        public long duration;
        public long playhead;

        public CuepointFulfillmentAsset build() {
            return new CuepointFulfillmentAsset(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CuepointFulfillmentAsset> {
        private final EnumParser<CardType> mCardTypeParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCardTypeParser = EnumParser.newParser(CardType.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CuepointFulfillmentAsset parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CuepointFulfillmentAsset:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CuepointFulfillmentAsset parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CuepointFulfillmentAsset:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CuepointFulfillmentAsset parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CuepointFulfillmentAsset");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1992012396:
                            if (next.equals("duration")) {
                                b = 0;
                            }
                            break;
                        case -407108748:
                            if (next.equals("contentId")) {
                                b = 3;
                            }
                            break;
                        case -8227222:
                            if (next.equals("cardType")) {
                                b = 1;
                            }
                            break;
                        case 1879351060:
                            if (next.equals("playhead")) {
                                b = 2;
                            }
                            break;
                    }
                    if (b != 0) {
                        String str = null;
                        CardType cardType = null;
                        if (b == 1) {
                            if (!jsonNode2.isNull()) {
                                cardType = (CardType) this.mCardTypeParser.parse(jsonNode2);
                            }
                            builder.cardType = cardType;
                        } else if (b == 2) {
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field playhead can't be null");
                            }
                            builder.playhead = SimpleParsers.parsePrimitiveLong(jsonNode2);
                        } else if (b == 3) {
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.contentId = str;
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field duration can't be null");
                        }
                        builder.duration = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CuepointFulfillmentAsset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CuepointFulfillmentAsset parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1992012396:
                                    if (currentName.equals("duration")) {
                                        b = 0;
                                    }
                                    break;
                                case -407108748:
                                    if (currentName.equals("contentId")) {
                                        b = 3;
                                    }
                                    break;
                                case -8227222:
                                    if (currentName.equals("cardType")) {
                                        b = 1;
                                    }
                                    break;
                                case 1879351060:
                                    if (currentName.equals("playhead")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            if (b != 0) {
                                String str = null;
                                CardType cardType = null;
                                if (b == 1) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        cardType = (CardType) this.mCardTypeParser.parse(jsonParser);
                                    }
                                    builder.cardType = cardType;
                                } else if (b == 2) {
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field playhead can't be null");
                                    }
                                    builder.playhead = SimpleParsers.parsePrimitiveLong(jsonParser);
                                } else if (b == 3) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.contentId = str;
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field duration can't be null");
                                }
                                builder.duration = SimpleParsers.parsePrimitiveLong(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CuepointFulfillmentAsset so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
