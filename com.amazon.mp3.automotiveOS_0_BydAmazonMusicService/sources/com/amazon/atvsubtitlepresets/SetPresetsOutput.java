package com.amazon.atvsubtitlepresets;

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
public class SetPresetsOutput {
    public final Optional<String> customerId;
    public final Optional<String> marketplaceId;
    public final Optional<String> message;
    public final Optional<Boolean> succeeded;

    private SetPresetsOutput(Builder builder) {
        this.succeeded = Optional.fromNullable(builder.succeeded);
        this.message = Optional.fromNullable(builder.message);
        this.marketplaceId = Optional.fromNullable(builder.marketplaceId);
        this.customerId = Optional.fromNullable(builder.customerId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetPresetsOutput)) {
            return false;
        }
        SetPresetsOutput setPresetsOutput = (SetPresetsOutput) obj;
        return Objects.equal(this.succeeded, setPresetsOutput.succeeded) && Objects.equal(this.message, setPresetsOutput.message) && Objects.equal(this.marketplaceId, setPresetsOutput.marketplaceId) && Objects.equal(this.customerId, setPresetsOutput.customerId);
    }

    public int hashCode() {
        return Objects.hashCode(this.succeeded, this.message, this.marketplaceId, this.customerId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("succeeded", this.succeeded).add("message", this.message).add("marketplaceId", this.marketplaceId).add("customerId", this.customerId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String customerId;
        public String marketplaceId;
        public String message;
        public Boolean succeeded;

        public SetPresetsOutput build() {
            return new SetPresetsOutput(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SetPresetsOutput> {
        private final SimpleParsers.StringParser mcustomerIdParser;
        private final SimpleParsers.StringParser mmarketplaceIdParser;
        private final SimpleParsers.StringParser mmessageParser;
        private final SimpleParsers.BooleanParser msucceededParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.msucceededParser = SimpleParsers.BooleanParser.INSTANCE;
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mmessageParser = stringParser;
            this.mmarketplaceIdParser = stringParser;
            this.mcustomerIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SetPresetsOutput parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SetPresetsOutput:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SetPresetsOutput parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SetPresetsOutput:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SetPresetsOutput parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SetPresetsOutput");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1581184615:
                            if (next.equals("customerId")) {
                                b = 3;
                            }
                            break;
                        case 945734241:
                            if (next.equals("succeeded")) {
                                b = 0;
                            }
                            break;
                        case 954925063:
                            if (next.equals("message")) {
                                b = 1;
                            }
                            break;
                        case 1412836550:
                            if (next.equals("marketplaceId")) {
                                b = 2;
                            }
                            break;
                    }
                    String str = null;
                    Boolean bool = null;
                    String str2 = null;
                    String str3 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            bool = this.msucceededParser.parse(jsonNode2);
                        }
                        builder.succeeded = bool;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mmessageParser.parse(jsonNode2);
                        }
                        builder.message = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mmarketplaceIdParser.parse(jsonNode2);
                        }
                        builder.marketplaceId = str3;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mcustomerIdParser.parse(jsonNode2);
                        }
                        builder.customerId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SetPresetsOutput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SetPresetsOutput parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            switch (currentName.hashCode()) {
                                case -1581184615:
                                    if (currentName.equals("customerId")) {
                                        b = 3;
                                    }
                                    break;
                                case 945734241:
                                    if (currentName.equals("succeeded")) {
                                        b = 0;
                                    }
                                    break;
                                case 954925063:
                                    if (currentName.equals("message")) {
                                        b = 1;
                                    }
                                    break;
                                case 1412836550:
                                    if (currentName.equals("marketplaceId")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            String str = null;
                            Boolean bool = null;
                            String str2 = null;
                            String str3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.msucceededParser.parse(jsonParser);
                                }
                                builder.succeeded = bool;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mmessageParser.parse(jsonParser);
                                }
                                builder.message = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mmarketplaceIdParser.parse(jsonParser);
                                }
                                builder.marketplaceId = str3;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mcustomerIdParser.parse(jsonParser);
                                }
                                builder.customerId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SetPresetsOutput so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
