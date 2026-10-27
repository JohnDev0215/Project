package com.amazon.atvplaybackresource;

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
public class LegacyMetadata {
    public final Optional<String> textTitle;

    private LegacyMetadata(Builder builder) {
        this.textTitle = Optional.fromNullable(builder.textTitle);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LegacyMetadata) {
            return Objects.equal(this.textTitle, ((LegacyMetadata) obj).textTitle);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.textTitle);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("textTitle", this.textTitle).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String textTitle;

        public LegacyMetadata build() {
            return new LegacyMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LegacyMetadata> {
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LegacyMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LegacyMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LegacyMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LegacyMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private LegacyMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LegacyMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    if (next.hashCode() == -1048043221 && next.equals("textTitle")) {
                        b = 0;
                    }
                    if (b == 0) {
                        builder.textTitle = jsonNode2.isNull() ? null : this.mstringTargetParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LegacyMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private LegacyMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (currentName.hashCode() == -1048043221 && currentName.equals("textTitle")) {
                                b = 0;
                            }
                            if (b == 0) {
                                builder.textTitle = currentToken == JsonToken.VALUE_NULL ? null : this.mstringTargetParser.parse(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LegacyMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
