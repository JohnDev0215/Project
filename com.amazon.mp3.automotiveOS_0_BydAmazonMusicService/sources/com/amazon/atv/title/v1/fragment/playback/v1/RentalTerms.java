package com.amazon.atv.title.v1.fragment.playback.v1;

import com.amazon.atv.title.v1.fragment.playback.v1.ExpirationTimestamp;
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
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class RentalTerms {
    public final boolean isStarted;
    public final Optional<ExpirationTimestamp> timeRemaining;
    public final Optional<Long> watchWindowMillis;

    private RentalTerms(Builder builder) {
        this.timeRemaining = Optional.fromNullable(builder.timeRemaining);
        this.isStarted = builder.isStarted;
        this.watchWindowMillis = Optional.fromNullable(builder.watchWindowMillis);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RentalTerms)) {
            return false;
        }
        RentalTerms rentalTerms = (RentalTerms) obj;
        return Objects.equal(this.timeRemaining, rentalTerms.timeRemaining) && Objects.equal(Boolean.valueOf(this.isStarted), Boolean.valueOf(rentalTerms.isStarted)) && Objects.equal(this.watchWindowMillis, rentalTerms.watchWindowMillis);
    }

    public int hashCode() {
        return Objects.hashCode(this.timeRemaining, Boolean.valueOf(this.isStarted), this.watchWindowMillis);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("timeRemaining", this.timeRemaining).add("isStarted", this.isStarted).add("watchWindowMillis", this.watchWindowMillis).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public boolean isStarted;
        public ExpirationTimestamp timeRemaining;
        public Long watchWindowMillis;

        public RentalTerms build() {
            return new RentalTerms(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RentalTerms> {
        private final ExpirationTimestamp.Parser mExpirationTimestampParser;
        private final SimpleParsers.LongParser mLongParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mExpirationTimestampParser = new ExpirationTimestamp.Parser(objectMapper);
            this.mLongParser = SimpleParsers.LongParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RentalTerms parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RentalTerms:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RentalTerms parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RentalTerms:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private RentalTerms parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RentalTerms");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1457190715) {
                        if (iHashCode != -1394585175) {
                            if (iHashCode == 1818003447 && next.equals("isStarted")) {
                                b = 1;
                            }
                        } else if (next.equals("timeRemaining")) {
                            b = 0;
                        }
                    } else if (next.equals("watchWindowMillis")) {
                        b = 2;
                    }
                    Long l = null;
                    ExpirationTimestamp expirationTimestamp = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            expirationTimestamp = this.mExpirationTimestampParser.parse(jsonNode2);
                        }
                        builder.timeRemaining = expirationTimestamp;
                    } else if (b != 1) {
                        if (b == 2) {
                            if (!jsonNode2.isNull()) {
                                l = this.mLongParser.parse(jsonNode2);
                            }
                            builder.watchWindowMillis = l;
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field isStarted can't be null");
                        }
                        builder.isStarted = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RentalTerms so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private RentalTerms parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1457190715) {
                                if (iHashCode != -1394585175) {
                                    if (iHashCode == 1818003447 && currentName.equals("isStarted")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("timeRemaining")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("watchWindowMillis")) {
                                b = 2;
                            }
                            Long l = null;
                            ExpirationTimestamp expirationTimestamp = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    expirationTimestamp = this.mExpirationTimestampParser.parse(jsonParser);
                                }
                                builder.timeRemaining = expirationTimestamp;
                            } else if (b != 1) {
                                if (b == 2) {
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        l = this.mLongParser.parse(jsonParser);
                                    }
                                    builder.watchWindowMillis = l;
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field isStarted can't be null");
                                }
                                builder.isStarted = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RentalTerms so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
