package com.amazon.atv.xrayv2;

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
public class TimeRange {
    public final long endTime;
    public final long startTime;

    private TimeRange(Builder builder) {
        this.endTime = builder.endTime;
        this.startTime = builder.startTime;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimeRange)) {
            return false;
        }
        TimeRange timeRange = (TimeRange) obj;
        return Objects.equal(Long.valueOf(this.endTime), Long.valueOf(timeRange.endTime)) && Objects.equal(Long.valueOf(this.startTime), Long.valueOf(timeRange.startTime));
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.endTime), Long.valueOf(this.startTime));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("endTime", this.endTime).add("startTime", this.startTime).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public long endTime;
        public long startTime;

        public TimeRange build() {
            return new TimeRange(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TimeRange> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TimeRange parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimeRange:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TimeRange parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimeRange:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TimeRange parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TimeRange");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2129294769) {
                        if (iHashCode == -1607243192 && next.equals("endTime")) {
                            b = 0;
                        }
                    } else if (next.equals("startTime")) {
                        b = 1;
                    }
                    if (b != 0) {
                        if (b == 1) {
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field startTime can't be null");
                            }
                            builder.startTime = SimpleParsers.parsePrimitiveLong(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field endTime can't be null");
                        }
                        builder.endTime = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TimeRange so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Long.valueOf(builder.endTime), this, "endTime");
            JsonParsingUtils.checkNotNull(Long.valueOf(builder.startTime), this, "startTime");
            return builder.build();
        }

        @Nonnull
        private TimeRange parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                if (iHashCode == -1607243192 && currentName.equals("endTime")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("startTime")) {
                                b = 1;
                            }
                            if (b != 0) {
                                if (b == 1) {
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field startTime can't be null");
                                    }
                                    builder.startTime = SimpleParsers.parsePrimitiveLong(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field endTime can't be null");
                                }
                                builder.endTime = SimpleParsers.parsePrimitiveLong(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TimeRange so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.endTime), this, "endTime");
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.startTime), this, "startTime");
                    return builder.build();
                }
            }
        }
    }
}
