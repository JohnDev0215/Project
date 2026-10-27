package com.amazon.atv.playbackauthority.service.model;

import com.amazon.atv.playbackauthority.service.model.NotConsumableReason;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Consumability {
    public final boolean isConsumable;
    public final ImmutableList<NotConsumableReason> notConsumableReasons;

    private Consumability(Builder builder) {
        this.isConsumable = builder.isConsumable;
        this.notConsumableReasons = (ImmutableList) Preconditions.checkNotNull(builder.notConsumableReasons, "Unexpected null field: notConsumableReasons");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Consumability)) {
            return false;
        }
        Consumability consumability = (Consumability) obj;
        return Objects.equal(Boolean.valueOf(this.isConsumable), Boolean.valueOf(consumability.isConsumable)) && Objects.equal(this.notConsumableReasons, consumability.notConsumableReasons);
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.isConsumable), this.notConsumableReasons);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("isConsumable", this.isConsumable).add("notConsumableReasons", this.notConsumableReasons).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public boolean isConsumable;
        public ImmutableList<NotConsumableReason> notConsumableReasons;

        public Consumability build() {
            return new Consumability(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Consumability> {
        private final ListParser<NotConsumableReason> mNotConsumableReasonsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mNotConsumableReasonsParser = ListParser.newParser(new NotConsumableReason.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Consumability parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Consumability:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Consumability parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Consumability:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Consumability parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Consumability");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 667402297) {
                        if (iHashCode == 2004900653 && next.equals("isConsumable")) {
                            b = 0;
                        }
                    } else if (next.equals("notConsumableReasons")) {
                        b = 1;
                    }
                    if (b != 0) {
                        if (b == 1) {
                            builder.notConsumableReasons = jsonNode2.isNull() ? null : this.mNotConsumableReasonsParser.parse(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field isConsumable can't be null");
                        }
                        builder.isConsumable = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Consumability so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.isConsumable), this, "isConsumable");
            JsonParsingUtils.checkNotNull(builder.notConsumableReasons, this, "notConsumableReasons");
            return builder.build();
        }

        @Nonnull
        private Consumability parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 667402297) {
                                if (iHashCode == 2004900653 && currentName.equals("isConsumable")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("notConsumableReasons")) {
                                b = 1;
                            }
                            if (b != 0) {
                                if (b == 1) {
                                    builder.notConsumableReasons = currentToken == JsonToken.VALUE_NULL ? null : this.mNotConsumableReasonsParser.parse(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field isConsumable can't be null");
                                }
                                builder.isConsumable = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Consumability so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.isConsumable), this, "isConsumable");
                    JsonParsingUtils.checkNotNull(builder.notConsumableReasons, this, "notConsumableReasons");
                    return builder.build();
                }
            }
        }
    }
}
