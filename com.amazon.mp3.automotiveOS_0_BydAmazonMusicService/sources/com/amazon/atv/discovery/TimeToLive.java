package com.amazon.atv.discovery;

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
public class TimeToLive {
    public final int maxAgeSeconds;
    public final UpdatePolicyType updatePolicy;

    private TimeToLive(Builder builder) {
        this.updatePolicy = (UpdatePolicyType) Preconditions.checkNotNull(builder.updatePolicy, "Unexpected null field: updatePolicy");
        this.maxAgeSeconds = builder.maxAgeSeconds;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimeToLive)) {
            return false;
        }
        TimeToLive timeToLive = (TimeToLive) obj;
        return Objects.equal(this.updatePolicy, timeToLive.updatePolicy) && Objects.equal(Integer.valueOf(this.maxAgeSeconds), Integer.valueOf(timeToLive.maxAgeSeconds));
    }

    public int hashCode() {
        return Objects.hashCode(this.updatePolicy, Integer.valueOf(this.maxAgeSeconds));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("updatePolicy", this.updatePolicy).add("maxAgeSeconds", this.maxAgeSeconds).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public int maxAgeSeconds;
        public UpdatePolicyType updatePolicy;

        public TimeToLive build() {
            return new TimeToLive(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TimeToLive> {
        private final EnumParser<UpdatePolicyType> mUpdatePolicyTypeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mUpdatePolicyTypeParser = EnumParser.newParser(UpdatePolicyType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TimeToLive parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimeToLive:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TimeToLive parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimeToLive:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TimeToLive parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TimeToLive");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1030926501) {
                        if (iHashCode == -973590268 && next.equals("maxAgeSeconds")) {
                            b = 1;
                        }
                    } else if (next.equals("updatePolicy")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.updatePolicy = jsonNode2.isNull() ? null : (UpdatePolicyType) this.mUpdatePolicyTypeParser.parse(jsonNode2);
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field maxAgeSeconds can't be null");
                        }
                        builder.maxAgeSeconds = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TimeToLive so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.updatePolicy, this, "updatePolicy");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.maxAgeSeconds), this, "maxAgeSeconds");
            return builder.build();
        }

        @Nonnull
        private TimeToLive parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1030926501) {
                                if (iHashCode == -973590268 && currentName.equals("maxAgeSeconds")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("updatePolicy")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.updatePolicy = currentToken == JsonToken.VALUE_NULL ? null : (UpdatePolicyType) this.mUpdatePolicyTypeParser.parse(jsonParser);
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field maxAgeSeconds can't be null");
                                }
                                builder.maxAgeSeconds = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TimeToLive so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.updatePolicy, this, "updatePolicy");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.maxAgeSeconds), this, "maxAgeSeconds");
                    return builder.build();
                }
            }
        }
    }
}
