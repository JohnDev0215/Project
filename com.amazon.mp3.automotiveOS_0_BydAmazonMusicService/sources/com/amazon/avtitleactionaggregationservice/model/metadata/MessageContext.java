package com.amazon.avtitleactionaggregationservice.model.metadata;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.metadata.AccessMetadata;
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
public class MessageContext {
    public final Optional<ImmutableList<AccessMetadata>> accessMetadataList;
    public final Optional<Action> action;
    public final int rank;
    public final Optional<String> sourceId;
    public final Optional<String> sourceName;

    private MessageContext(Builder builder) {
        this.sourceName = Optional.fromNullable(builder.sourceName);
        this.rank = builder.rank;
        this.action = Optional.fromNullable(builder.action);
        this.accessMetadataList = Optional.fromNullable(builder.accessMetadataList);
        this.sourceId = Optional.fromNullable(builder.sourceId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MessageContext)) {
            return false;
        }
        MessageContext messageContext = (MessageContext) obj;
        return Objects.equal(this.sourceName, messageContext.sourceName) && Objects.equal(Integer.valueOf(this.rank), Integer.valueOf(messageContext.rank)) && Objects.equal(this.action, messageContext.action) && Objects.equal(this.accessMetadataList, messageContext.accessMetadataList) && Objects.equal(this.sourceId, messageContext.sourceId);
    }

    public int hashCode() {
        return Objects.hashCode(this.sourceName, Integer.valueOf(this.rank), this.action, this.accessMetadataList, this.sourceId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("sourceName", this.sourceName).add("rank", this.rank).add("action", this.action).add("accessMetadataList", this.accessMetadataList).add("sourceId", this.sourceId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<AccessMetadata> accessMetadataList;
        public Action action;
        public int rank;
        public String sourceId;
        public String sourceName;

        public MessageContext build() {
            return new MessageContext(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<MessageContext> {
        private final ListParser<AccessMetadata> mAccessMetadataListParser;
        private final EnumParser<Action> mActionParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAccessMetadataListParser = ListParser.newParser(new AccessMetadata.Parser(objectMapper));
            this.mActionParser = EnumParser.newParser(Action.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public MessageContext parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("MessageContext:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public MessageContext parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("MessageContext:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private MessageContext parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "MessageContext");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1422950858:
                            if (next.equals("action")) {
                                b = 2;
                            }
                            break;
                        case -1111633594:
                            if (next.equals("sourceName")) {
                                b = 0;
                            }
                            break;
                        case 3492908:
                            if (next.equals("rank")) {
                                b = 1;
                            }
                            break;
                        case 1340900337:
                            if (next.equals("accessMetadataList")) {
                                b = 3;
                            }
                            break;
                        case 1746327190:
                            if (next.equals("sourceId")) {
                                b = 4;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    Action action = null;
                    ImmutableList<AccessMetadata> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.sourceName = str2;
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field rank can't be null");
                        }
                        builder.rank = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            action = (Action) this.mActionParser.parse(jsonNode2);
                        }
                        builder.action = action;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mAccessMetadataListParser.parse(jsonNode2);
                        }
                        builder.accessMetadataList = immutableList;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.sourceId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing MessageContext so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private MessageContext parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1422950858:
                                    if (currentName.equals("action")) {
                                        b = 2;
                                    }
                                    break;
                                case -1111633594:
                                    if (currentName.equals("sourceName")) {
                                        b = 0;
                                    }
                                    break;
                                case 3492908:
                                    if (currentName.equals("rank")) {
                                        b = 1;
                                    }
                                    break;
                                case 1340900337:
                                    if (currentName.equals("accessMetadataList")) {
                                        b = 3;
                                    }
                                    break;
                                case 1746327190:
                                    if (currentName.equals("sourceId")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            Action action = null;
                            ImmutableList<AccessMetadata> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.sourceName = str2;
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field rank can't be null");
                                }
                                builder.rank = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    action = (Action) this.mActionParser.parse(jsonParser);
                                }
                                builder.action = action;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mAccessMetadataListParser.parse(jsonParser);
                                }
                                builder.accessMetadataList = immutableList;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.sourceId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing MessageContext so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
