package com.amazon.atv.purchase;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class LocalizedMessage {
    public final Optional<String> formattedMessage;
    public final Optional<ImmutableMap<String, String>> messageArgs;
    public final String messageId;

    private LocalizedMessage(Builder builder) {
        this.messageArgs = Optional.fromNullable(builder.messageArgs);
        this.formattedMessage = Optional.fromNullable(builder.formattedMessage);
        this.messageId = (String) Preconditions.checkNotNull(builder.messageId, "Unexpected null field: messageId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocalizedMessage)) {
            return false;
        }
        LocalizedMessage localizedMessage = (LocalizedMessage) obj;
        return Objects.equal(this.messageArgs, localizedMessage.messageArgs) && Objects.equal(this.formattedMessage, localizedMessage.formattedMessage) && Objects.equal(this.messageId, localizedMessage.messageId);
    }

    public int hashCode() {
        return Objects.hashCode(this.messageArgs, this.formattedMessage, this.messageId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("messageArgs", this.messageArgs).add("formattedMessage", this.formattedMessage).add("messageId", this.messageId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String formattedMessage;
        public ImmutableMap<String, String> messageArgs;
        public String messageId;

        public LocalizedMessage build() {
            return new LocalizedMessage(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LocalizedMessage> {
        private final MapParser<String, String> mErrorMessageArgsParser;
        private final SimpleParsers.StringParser mErrorMessageIdParser;
        private final SimpleParsers.StringParser mFormattedErrorMessageParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mErrorMessageArgsParser = MapParser.newParser(stringParser, stringParser);
            this.mFormattedErrorMessageParser = stringParser;
            this.mErrorMessageIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LocalizedMessage parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LocalizedMessage:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LocalizedMessage parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LocalizedMessage:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private LocalizedMessage parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LocalizedMessage");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1440013438) {
                        if (iHashCode != -1155540053) {
                            if (iHashCode == -873666172 && next.equals("messageArgs")) {
                                b = 0;
                            }
                        } else if (next.equals("formattedMessage")) {
                            b = 1;
                        }
                    } else if (next.equals("messageId")) {
                        b = 2;
                    }
                    String str = null;
                    ImmutableMap<String, String> immutableMap = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mErrorMessageArgsParser.parse(jsonNode2);
                        }
                        builder.messageArgs = immutableMap;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mFormattedErrorMessageParser.parse(jsonNode2);
                        }
                        builder.formattedMessage = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mErrorMessageIdParser.parse(jsonNode2);
                        }
                        builder.messageId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LocalizedMessage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.messageId, this, "messageId");
            return builder.build();
        }

        @Nonnull
        private LocalizedMessage parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1440013438) {
                                if (iHashCode != -1155540053) {
                                    if (iHashCode == -873666172 && currentName.equals("messageArgs")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("formattedMessage")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("messageId")) {
                                b = 2;
                            }
                            String str = null;
                            ImmutableMap<String, String> immutableMap = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mErrorMessageArgsParser.parse(jsonParser);
                                }
                                builder.messageArgs = immutableMap;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mFormattedErrorMessageParser.parse(jsonParser);
                                }
                                builder.formattedMessage = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mErrorMessageIdParser.parse(jsonParser);
                                }
                                builder.messageId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LocalizedMessage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.messageId, this, "messageId");
                    return builder.build();
                }
            }
        }
    }
}
