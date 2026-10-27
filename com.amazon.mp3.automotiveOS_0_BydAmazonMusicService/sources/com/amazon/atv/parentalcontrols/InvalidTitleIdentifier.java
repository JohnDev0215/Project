package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.NonRetryableException;
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
public class InvalidTitleIdentifier extends NonRetryableException {
    public final Optional<String> titleIdentifier;

    private InvalidTitleIdentifier(Builder builder) {
        super(builder);
        this.titleIdentifier = Optional.fromNullable(builder.titleIdentifier);
    }

    @Override // com.amazon.atv.parentalcontrols.NonRetryableException, com.amazon.atv.parentalcontrols.PCSException
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InvalidTitleIdentifier) {
            return super.equals(obj) && Objects.equal(this.titleIdentifier, ((InvalidTitleIdentifier) obj).titleIdentifier);
        }
        return false;
    }

    @Override // com.amazon.atv.parentalcontrols.NonRetryableException, com.amazon.atv.parentalcontrols.PCSException
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.titleIdentifier);
    }

    @Override // com.amazon.atv.parentalcontrols.NonRetryableException, com.amazon.atv.parentalcontrols.PCSException
    public String toString() {
        return MoreObjects.toStringHelper(this).add("titleIdentifier", this.titleIdentifier).toString();
    }

    @NotThreadSafe
    public static class Builder extends NonRetryableException.Builder {
        public String titleIdentifier;

        @Override // com.amazon.atv.parentalcontrols.NonRetryableException.Builder, com.amazon.atv.parentalcontrols.PCSException.Builder
        public InvalidTitleIdentifier build() {
            return new InvalidTitleIdentifier(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<InvalidTitleIdentifier> {
        private final SimpleParsers.StringParser mbasicStringParser;
        private final SimpleParsers.StringParser merrorMessageParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mbasicStringParser = stringParser;
            this.merrorMessageParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public InvalidTitleIdentifier parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("InvalidTitleIdentifier:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public InvalidTitleIdentifier parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("InvalidTitleIdentifier:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private InvalidTitleIdentifier parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "InvalidTitleIdentifier");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2109104863) {
                        if (iHashCode == 954925063 && next.equals("message")) {
                            b = 0;
                        }
                    } else if (next.equals("titleIdentifier")) {
                        b = 1;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.merrorMessageParser.parse(jsonNode2);
                        }
                        builder.message = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.titleIdentifier = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing InvalidTitleIdentifier so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private InvalidTitleIdentifier parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -2109104863) {
                                if (iHashCode == 954925063 && currentName.equals("message")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("titleIdentifier")) {
                                b = 1;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.merrorMessageParser.parse(jsonParser);
                                }
                                builder.message = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.titleIdentifier = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing InvalidTitleIdentifier so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
