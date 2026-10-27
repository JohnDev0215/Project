package com.amazon.atv.title.v1.fragment.playback.v1;

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
public class ExpirationTimestamp {
    public final long millisFromEpoch;
    public final long millisFromNow;

    private ExpirationTimestamp(Builder builder) {
        this.millisFromEpoch = builder.millisFromEpoch;
        this.millisFromNow = builder.millisFromNow;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExpirationTimestamp)) {
            return false;
        }
        ExpirationTimestamp expirationTimestamp = (ExpirationTimestamp) obj;
        return Objects.equal(Long.valueOf(this.millisFromEpoch), Long.valueOf(expirationTimestamp.millisFromEpoch)) && Objects.equal(Long.valueOf(this.millisFromNow), Long.valueOf(expirationTimestamp.millisFromNow));
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.millisFromEpoch), Long.valueOf(this.millisFromNow));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("millisFromEpoch", this.millisFromEpoch).add("millisFromNow", this.millisFromNow).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public long millisFromEpoch;
        public long millisFromNow;

        public ExpirationTimestamp build() {
            return new ExpirationTimestamp(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ExpirationTimestamp> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ExpirationTimestamp parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ExpirationTimestamp:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ExpirationTimestamp parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ExpirationTimestamp:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private ExpirationTimestamp parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ExpirationTimestamp");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 238601049) {
                        if (iHashCode == 1327629734 && next.equals("millisFromNow")) {
                            b = 1;
                        }
                    } else if (next.equals("millisFromEpoch")) {
                        b = 0;
                    }
                    if (b != 0) {
                        if (b == 1) {
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field millisFromNow can't be null");
                            }
                            builder.millisFromNow = SimpleParsers.parsePrimitiveLong(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field millisFromEpoch can't be null");
                        }
                        builder.millisFromEpoch = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ExpirationTimestamp so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private ExpirationTimestamp parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 238601049) {
                                if (iHashCode == 1327629734 && currentName.equals("millisFromNow")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("millisFromEpoch")) {
                                b = 0;
                            }
                            if (b != 0) {
                                if (b == 1) {
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field millisFromNow can't be null");
                                    }
                                    builder.millisFromNow = SimpleParsers.parsePrimitiveLong(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field millisFromEpoch can't be null");
                                }
                                builder.millisFromEpoch = SimpleParsers.parsePrimitiveLong(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ExpirationTimestamp so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
