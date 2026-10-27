package com.amazon.atv.schedule.distribution;

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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class AiringsRequest {
    public final long endTime;
    public final int numberOfUpcomingAirings;
    public final long startTime;

    private AiringsRequest(Builder builder) {
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.numberOfUpcomingAirings = builder.numberOfUpcomingAirings;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AiringsRequest)) {
            return false;
        }
        AiringsRequest airingsRequest = (AiringsRequest) obj;
        return Objects.equal(Long.valueOf(this.startTime), Long.valueOf(airingsRequest.startTime)) && Objects.equal(Long.valueOf(this.endTime), Long.valueOf(airingsRequest.endTime)) && Objects.equal(Integer.valueOf(this.numberOfUpcomingAirings), Integer.valueOf(airingsRequest.numberOfUpcomingAirings));
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.startTime), Long.valueOf(this.endTime), Integer.valueOf(this.numberOfUpcomingAirings));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("startTime", this.startTime).add("endTime", this.endTime).add("numberOfUpcomingAirings", this.numberOfUpcomingAirings).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public long endTime;
        public int numberOfUpcomingAirings;
        public long startTime;

        public AiringsRequest build() {
            return new AiringsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AiringsRequest> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AiringsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AiringsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AiringsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AiringsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private AiringsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AiringsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2129294769) {
                        if (iHashCode != -1607243192) {
                            if (iHashCode == 1365942591 && next.equals("numberOfUpcomingAirings")) {
                                b = 2;
                            }
                        } else if (next.equals("endTime")) {
                            b = 1;
                        }
                    } else if (next.equals("startTime")) {
                        b = 0;
                    }
                    if (b != 0) {
                        if (b != 1) {
                            if (b == 2) {
                                if (jsonNode2.isNull()) {
                                    throw new JsonContractException("primitive field numberOfUpcomingAirings can't be null");
                                }
                                builder.numberOfUpcomingAirings = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            }
                        } else {
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field endTime can't be null");
                            }
                            builder.endTime = SimpleParsers.parsePrimitiveLong(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field startTime can't be null");
                        }
                        builder.startTime = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AiringsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Long.valueOf(builder.startTime), this, "startTime");
            JsonParsingUtils.checkNotNull(Long.valueOf(builder.endTime), this, "endTime");
            return builder.build();
        }

        @Nonnull
        private AiringsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -2129294769) {
                                if (iHashCode != -1607243192) {
                                    if (iHashCode == 1365942591 && currentName.equals("numberOfUpcomingAirings")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("endTime")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("startTime")) {
                                b = 0;
                            }
                            if (b != 0) {
                                if (b != 1) {
                                    if (b == 2) {
                                        if (currentToken == JsonToken.VALUE_NULL) {
                                            throw new JsonContractException("primitive field numberOfUpcomingAirings can't be null");
                                        }
                                        builder.numberOfUpcomingAirings = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    } else {
                                        jsonParser.skipChildren();
                                    }
                                } else {
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field endTime can't be null");
                                    }
                                    builder.endTime = SimpleParsers.parsePrimitiveLong(jsonParser);
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field startTime can't be null");
                                }
                                builder.startTime = SimpleParsers.parsePrimitiveLong(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AiringsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.startTime), this, "startTime");
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.endTime), this, "endTime");
                    return builder.build();
                }
            }
        }
    }
}
