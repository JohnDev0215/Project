package com.amazon.atvtransitiontimecodeservice;

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
public class EventTimecode {
    public final long e;
    public final long s;

    private EventTimecode(Builder builder) {
        this.e = builder.e;
        this.s = builder.s;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventTimecode)) {
            return false;
        }
        EventTimecode eventTimecode = (EventTimecode) obj;
        return Objects.equal(Long.valueOf(this.e), Long.valueOf(eventTimecode.e)) && Objects.equal(Long.valueOf(this.s), Long.valueOf(eventTimecode.s));
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.e), Long.valueOf(this.s));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("e", this.e).add("s", this.s).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public long e;
        public long s;

        public EventTimecode build() {
            return new EventTimecode(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<EventTimecode> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public EventTimecode parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("EventTimecode:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public EventTimecode parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("EventTimecode:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private EventTimecode parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "EventTimecode");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 101) {
                        if (iHashCode == 115 && next.equals("s")) {
                            b = 1;
                        }
                    } else if (next.equals("e")) {
                        b = 0;
                    }
                    if (b != 0) {
                        if (b == 1) {
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field s can't be null");
                            }
                            builder.s = SimpleParsers.parsePrimitiveLong(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field e can't be null");
                        }
                        builder.e = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing EventTimecode so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private EventTimecode parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 101) {
                                if (iHashCode == 115 && currentName.equals("s")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("e")) {
                                b = 0;
                            }
                            if (b != 0) {
                                if (b == 1) {
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field s can't be null");
                                    }
                                    builder.s = SimpleParsers.parsePrimitiveLong(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field e can't be null");
                                }
                                builder.e = SimpleParsers.parsePrimitiveLong(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing EventTimecode so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
