package com.amazon.av.clientdownloadservice;

import com.amazon.av.clientdownloadservice.Message;
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
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class OnDeviceDownloadActionData {
    public final Optional<ImmutableList<Message>> messages;
    public final Optional<String> reason;

    private OnDeviceDownloadActionData(Builder builder) {
        this.reason = Optional.fromNullable(builder.reason);
        this.messages = Optional.fromNullable(builder.messages);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OnDeviceDownloadActionData)) {
            return false;
        }
        OnDeviceDownloadActionData onDeviceDownloadActionData = (OnDeviceDownloadActionData) obj;
        return Objects.equal(this.reason, onDeviceDownloadActionData.reason) && Objects.equal(this.messages, onDeviceDownloadActionData.messages);
    }

    public int hashCode() {
        return Objects.hashCode(this.reason, this.messages);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("reason", this.reason).add("messages", this.messages).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<Message> messages;
        public String reason;

        public OnDeviceDownloadActionData build() {
            return new OnDeviceDownloadActionData(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<OnDeviceDownloadActionData> {
        private final ListParser<Message> mMessageListParser;
        private final SimpleParsers.StringParser mstringTypeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mMessageListParser = ListParser.newParser(new Message.Parser(objectMapper));
            this.mstringTypeParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public OnDeviceDownloadActionData parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("OnDeviceDownloadActionData:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public OnDeviceDownloadActionData parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("OnDeviceDownloadActionData:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private OnDeviceDownloadActionData parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "OnDeviceDownloadActionData");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -934964668) {
                        if (iHashCode == -462094004 && next.equals("messages")) {
                            b = 1;
                        }
                    } else if (next.equals("reason")) {
                        b = 0;
                    }
                    ImmutableList<Message> immutableList = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTypeParser.parse(jsonNode2);
                        }
                        builder.reason = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mMessageListParser.parse(jsonNode2);
                        }
                        builder.messages = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing OnDeviceDownloadActionData so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private OnDeviceDownloadActionData parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -934964668) {
                                if (iHashCode == -462094004 && currentName.equals("messages")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("reason")) {
                                b = 0;
                            }
                            ImmutableList<Message> immutableList = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTypeParser.parse(jsonParser);
                                }
                                builder.reason = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mMessageListParser.parse(jsonParser);
                                }
                                builder.messages = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing OnDeviceDownloadActionData so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
