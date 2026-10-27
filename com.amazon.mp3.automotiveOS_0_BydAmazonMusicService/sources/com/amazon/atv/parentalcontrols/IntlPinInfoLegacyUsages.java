package com.amazon.atv.parentalcontrols;

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
public class IntlPinInfoLegacyUsages {
    public final boolean legacyRoku;
    public final boolean legacyWeb;

    private IntlPinInfoLegacyUsages(Builder builder) {
        this.legacyRoku = builder.legacyRoku;
        this.legacyWeb = builder.legacyWeb;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntlPinInfoLegacyUsages)) {
            return false;
        }
        IntlPinInfoLegacyUsages intlPinInfoLegacyUsages = (IntlPinInfoLegacyUsages) obj;
        return Objects.equal(Boolean.valueOf(this.legacyRoku), Boolean.valueOf(intlPinInfoLegacyUsages.legacyRoku)) && Objects.equal(Boolean.valueOf(this.legacyWeb), Boolean.valueOf(intlPinInfoLegacyUsages.legacyWeb));
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.legacyRoku), Boolean.valueOf(this.legacyWeb));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("legacyRoku", this.legacyRoku).add("legacyWeb", this.legacyWeb).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public boolean legacyRoku;
        public boolean legacyWeb;

        public IntlPinInfoLegacyUsages build() {
            return new IntlPinInfoLegacyUsages(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<IntlPinInfoLegacyUsages> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public IntlPinInfoLegacyUsages parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("IntlPinInfoLegacyUsages:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public IntlPinInfoLegacyUsages parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("IntlPinInfoLegacyUsages:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private IntlPinInfoLegacyUsages parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "IntlPinInfoLegacyUsages");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 345037776) {
                        if (iHashCode == 2089344715 && next.equals("legacyWeb")) {
                            b = 1;
                        }
                    } else if (next.equals("legacyRoku")) {
                        b = 0;
                    }
                    if (b != 0) {
                        if (b == 1) {
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field legacyWeb can't be null");
                            }
                            builder.legacyWeb = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field legacyRoku can't be null");
                        }
                        builder.legacyRoku = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing IntlPinInfoLegacyUsages so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private IntlPinInfoLegacyUsages parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 345037776) {
                                if (iHashCode == 2089344715 && currentName.equals("legacyWeb")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("legacyRoku")) {
                                b = 0;
                            }
                            if (b != 0) {
                                if (b == 1) {
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field legacyWeb can't be null");
                                    }
                                    builder.legacyWeb = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field legacyRoku can't be null");
                                }
                                builder.legacyRoku = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing IntlPinInfoLegacyUsages so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
