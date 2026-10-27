package com.amazon.atvplaybackresource.playready;

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
public class OutputProtection {
    public final Optional<Boolean> requireHdcp22;
    public final Optional<Integer> uncompressedDigitalVideoOpl;

    private OutputProtection(Builder builder) {
        this.requireHdcp22 = Optional.fromNullable(builder.requireHdcp22);
        this.uncompressedDigitalVideoOpl = Optional.fromNullable(builder.uncompressedDigitalVideoOpl);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OutputProtection)) {
            return false;
        }
        OutputProtection outputProtection = (OutputProtection) obj;
        return Objects.equal(this.requireHdcp22, outputProtection.requireHdcp22) && Objects.equal(this.uncompressedDigitalVideoOpl, outputProtection.uncompressedDigitalVideoOpl);
    }

    public int hashCode() {
        return Objects.hashCode(this.requireHdcp22, this.uncompressedDigitalVideoOpl);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("requireHdcp22", this.requireHdcp22).add("uncompressedDigitalVideoOpl", this.uncompressedDigitalVideoOpl).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Boolean requireHdcp22;
        public Integer uncompressedDigitalVideoOpl;

        public OutputProtection build() {
            return new OutputProtection(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<OutputProtection> {
        private final SimpleParsers.IntegerParser mOutputProtectionLevelParser;
        private final SimpleParsers.BooleanParser mRequireHdcp22Parser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRequireHdcp22Parser = SimpleParsers.BooleanParser.INSTANCE;
            this.mOutputProtectionLevelParser = SimpleParsers.IntegerParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public OutputProtection parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("OutputProtection:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public OutputProtection parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("OutputProtection:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private OutputProtection parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "OutputProtection");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -267082706) {
                        if (iHashCode == 262957102 && next.equals("requireHdcp22")) {
                            b = 0;
                        }
                    } else if (next.equals("uncompressedDigitalVideoOpl")) {
                        b = 1;
                    }
                    Integer num = null;
                    Boolean bool = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mRequireHdcp22Parser.parse(jsonNode2);
                        }
                        builder.requireHdcp22 = bool;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            num = this.mOutputProtectionLevelParser.parse(jsonNode2);
                        }
                        builder.uncompressedDigitalVideoOpl = num;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing OutputProtection so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private OutputProtection parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -267082706) {
                                if (iHashCode == 262957102 && currentName.equals("requireHdcp22")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("uncompressedDigitalVideoOpl")) {
                                b = 1;
                            }
                            Integer num = null;
                            Boolean bool = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mRequireHdcp22Parser.parse(jsonParser);
                                }
                                builder.requireHdcp22 = bool;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mOutputProtectionLevelParser.parse(jsonParser);
                                }
                                builder.uncompressedDigitalVideoOpl = num;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing OutputProtection so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
