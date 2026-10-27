package com.amazon.av.clientdownloadservice;

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
public class Message {
    public final Optional<ImmutableList<String>> actions;
    public final Optional<String> body;
    public final Optional<String> location;
    public final Optional<String> title;

    private Message(Builder builder) {
        this.location = Optional.fromNullable(builder.location);
        this.body = Optional.fromNullable(builder.body);
        this.actions = Optional.fromNullable(builder.actions);
        this.title = Optional.fromNullable(builder.title);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Message)) {
            return false;
        }
        Message message = (Message) obj;
        return Objects.equal(this.location, message.location) && Objects.equal(this.body, message.body) && Objects.equal(this.actions, message.actions) && Objects.equal(this.title, message.title);
    }

    public int hashCode() {
        return Objects.hashCode(this.location, this.body, this.actions, this.title);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("location", this.location).add("body", this.body).add("actions", this.actions).add("title", this.title).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<String> actions;
        public String body;
        public String location;
        public String title;

        public Message build() {
            return new Message(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Message> {
        private final ListParser<String> mStringListParser;
        private final SimpleParsers.StringParser mstringTypeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringListParser = ListParser.newParser(stringParser);
            this.mstringTypeParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Message parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Message:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Message parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Message:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Message parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Message");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1161803523:
                            if (next.equals("actions")) {
                                b = 2;
                            }
                            break;
                        case 3029410:
                            if (next.equals("body")) {
                                b = 1;
                            }
                            break;
                        case 110371416:
                            if (next.equals("title")) {
                                b = 3;
                            }
                            break;
                        case 1901043637:
                            if (next.equals("location")) {
                                b = 0;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    ImmutableList<String> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mstringTypeParser.parse(jsonNode2);
                        }
                        builder.location = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mstringTypeParser.parse(jsonNode2);
                        }
                        builder.body = str3;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mStringListParser.parse(jsonNode2);
                        }
                        builder.actions = immutableList;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTypeParser.parse(jsonNode2);
                        }
                        builder.title = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Message so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Message parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1161803523:
                                    if (currentName.equals("actions")) {
                                        b = 2;
                                    }
                                    break;
                                case 3029410:
                                    if (currentName.equals("body")) {
                                        b = 1;
                                    }
                                    break;
                                case 110371416:
                                    if (currentName.equals("title")) {
                                        b = 3;
                                    }
                                    break;
                                case 1901043637:
                                    if (currentName.equals("location")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            ImmutableList<String> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mstringTypeParser.parse(jsonParser);
                                }
                                builder.location = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mstringTypeParser.parse(jsonParser);
                                }
                                builder.body = str3;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mStringListParser.parse(jsonParser);
                                }
                                builder.actions = immutableList;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTypeParser.parse(jsonParser);
                                }
                                builder.title = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Message so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
