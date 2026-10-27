package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.IntlPinInfoLegacyUsages;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
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
public class IntlPinInfoMetadata {
    public final Optional<IntlPinInfoLegacyUsages> legacyUsages;

    private IntlPinInfoMetadata(Builder builder) {
        this.legacyUsages = Optional.fromNullable(builder.legacyUsages);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof IntlPinInfoMetadata) {
            return Objects.equal(this.legacyUsages, ((IntlPinInfoMetadata) obj).legacyUsages);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.legacyUsages);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("legacyUsages", this.legacyUsages).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public IntlPinInfoLegacyUsages legacyUsages;

        public IntlPinInfoMetadata build() {
            return new IntlPinInfoMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<IntlPinInfoMetadata> {
        private final IntlPinInfoLegacyUsages.Parser mIntlPinInfoLegacyUsagesParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mIntlPinInfoLegacyUsagesParser = new IntlPinInfoLegacyUsages.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public IntlPinInfoMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("IntlPinInfoMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public IntlPinInfoMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("IntlPinInfoMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private IntlPinInfoMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "IntlPinInfoMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == 958094363 && next.equals("legacyUsages")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.legacyUsages = jsonNode2.isNull() ? null : this.mIntlPinInfoLegacyUsagesParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing IntlPinInfoMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private IntlPinInfoMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == 958094363 && currentName.equals("legacyUsages")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.legacyUsages = currentToken == JsonToken.VALUE_NULL ? null : this.mIntlPinInfoLegacyUsagesParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing IntlPinInfoMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
