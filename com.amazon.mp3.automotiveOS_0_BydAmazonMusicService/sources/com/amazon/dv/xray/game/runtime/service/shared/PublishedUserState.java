package com.amazon.dv.xray.game.runtime.service.shared;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.dv.xray.game.runtime.service.shared.QuestionHash;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PublishedUserState {
    public final Optional<ImmutableList<String>> notificationIds;
    public final Optional<ImmutableMap<String, QuestionHash>> questionHashMap;
    public final Optional<ImmutableList<String>> removedItemIds;

    private PublishedUserState(Builder builder) {
        this.notificationIds = Optional.fromNullable(builder.notificationIds);
        this.removedItemIds = Optional.fromNullable(builder.removedItemIds);
        this.questionHashMap = Optional.fromNullable(builder.questionHashMap);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PublishedUserState)) {
            return false;
        }
        PublishedUserState publishedUserState = (PublishedUserState) obj;
        return Objects.equal(this.notificationIds, publishedUserState.notificationIds) && Objects.equal(this.removedItemIds, publishedUserState.removedItemIds) && Objects.equal(this.questionHashMap, publishedUserState.questionHashMap);
    }

    public int hashCode() {
        return Objects.hashCode(this.notificationIds, this.removedItemIds, this.questionHashMap);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("notificationIds", this.notificationIds).add("removedItemIds", this.removedItemIds).add("questionHashMap", this.questionHashMap).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<String> notificationIds;
        public ImmutableMap<String, QuestionHash> questionHashMap;
        public ImmutableList<String> removedItemIds;

        public PublishedUserState build() {
            return new PublishedUserState(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PublishedUserState> {
        private final ListParser<String> mNotificationIdListParser;
        private final MapParser<String, QuestionHash> mQuestionHashMapParser;
        private final ListParser<String> mRemovedItemIdListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mNotificationIdListParser = ListParser.newParser(stringParser);
            this.mRemovedItemIdListParser = ListParser.newParser(stringParser);
            this.mQuestionHashMapParser = MapParser.newParser(stringParser, new QuestionHash.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PublishedUserState parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PublishedUserState:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PublishedUserState parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PublishedUserState:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PublishedUserState parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PublishedUserState");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1783662936) {
                        if (iHashCode != -1333499443) {
                            if (iHashCode == 309115429 && next.equals("removedItemIds")) {
                                b = 1;
                            }
                        } else if (next.equals("notificationIds")) {
                            b = 0;
                        }
                    } else if (next.equals("questionHashMap")) {
                        b = 2;
                    }
                    ImmutableMap<String, QuestionHash> immutableMap = null;
                    ImmutableList<String> immutableList = null;
                    ImmutableList<String> immutableList2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mNotificationIdListParser.parse(jsonNode2);
                        }
                        builder.notificationIds = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mRemovedItemIdListParser.parse(jsonNode2);
                        }
                        builder.removedItemIds = immutableList2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mQuestionHashMapParser.parse(jsonNode2);
                        }
                        builder.questionHashMap = immutableMap;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PublishedUserState so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private PublishedUserState parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1783662936) {
                                if (iHashCode != -1333499443) {
                                    if (iHashCode == 309115429 && currentName.equals("removedItemIds")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("notificationIds")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("questionHashMap")) {
                                b = 2;
                            }
                            ImmutableMap<String, QuestionHash> immutableMap = null;
                            ImmutableList<String> immutableList = null;
                            ImmutableList<String> immutableList2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mNotificationIdListParser.parse(jsonParser);
                                }
                                builder.notificationIds = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mRemovedItemIdListParser.parse(jsonParser);
                                }
                                builder.removedItemIds = immutableList2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mQuestionHashMapParser.parse(jsonParser);
                                }
                                builder.questionHashMap = immutableMap;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PublishedUserState so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
