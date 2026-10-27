package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.Item;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class LiveCard extends Item {
    public final String channelId;
    public final Optional<String> image;
    public final Optional<String> subtext;
    public final String text;

    private LiveCard(Builder builder) {
        super(builder);
        this.image = Optional.fromNullable(builder.image);
        this.text = (String) Preconditions.checkNotNull(builder.text, "Unexpected null field: text");
        this.channelId = (String) Preconditions.checkNotNull(builder.channelId, "Unexpected null field: channelId");
        this.subtext = Optional.fromNullable(builder.subtext);
    }

    @Override // com.amazon.atv.discovery.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LiveCard)) {
            return false;
        }
        LiveCard liveCard = (LiveCard) obj;
        return super.equals(obj) && Objects.equal(this.image, liveCard.image) && Objects.equal(this.text, liveCard.text) && Objects.equal(this.channelId, liveCard.channelId) && Objects.equal(this.subtext, liveCard.subtext);
    }

    @Override // com.amazon.atv.discovery.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.image, this.text, this.channelId, this.subtext);
    }

    @Override // com.amazon.atv.discovery.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add(ContextMappingConstants.IMAGE, this.image).add("text", this.text).add("channelId", this.channelId).add("subtext", this.subtext).toString();
    }

    @NotThreadSafe
    public static class Builder extends Item.Builder {
        public String channelId;
        public String image;
        public String subtext;
        public String text;

        public LiveCard build() {
            return new LiveCard(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LiveCard> {
        private final Analytics.Parser mAnalyticsParser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LiveCard parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LiveCard:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LiveCard parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LiveCard:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private LiveCard parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            String str2;
            ItemType itemType;
            String str3;
            String str4;
            ImmutableMap<String, String> immutableMap;
            Analytics analytics;
            String str5;
            JsonParsingUtils.throwIfNotObject(jsonNode, "LiveCard");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1867586707:
                            if (next.equals("subtext")) {
                                b = 5;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 7;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 6;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 1;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 2;
                            }
                            break;
                        case 100313435:
                            if (next.equals(ContextMappingConstants.IMAGE)) {
                                b = 0;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 4;
                            }
                            break;
                        case 1461735806:
                            if (next.equals("channelId")) {
                                b = 3;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 8;
                            }
                            break;
                    }
                    str = null;
                    str5 = null;
                    analytics = null;
                    immutableMap = null;
                    str4 = null;
                    str3 = null;
                    itemType = null;
                    str2 = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LiveCard so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.image = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.text = str2;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            itemType = (ItemType) this.mItemTypeParser.parse(jsonNode2);
                        }
                        builder.type = itemType;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.channelId = str3;
                        continue;
                    case 4:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        continue;
                        break;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.subtext = str4;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mStringMapParser.parse(jsonNode2);
                        }
                        builder.metadata = immutableMap;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = str5;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LiveCard so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.text, this, "text");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(builder.channelId, this, "channelId");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private LiveCard parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1867586707:
                                    if (currentName.equals("subtext")) {
                                        b = 5;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 7;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 6;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 1;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 2;
                                    }
                                    break;
                                case 100313435:
                                    if (currentName.equals(ContextMappingConstants.IMAGE)) {
                                        b = 0;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 4;
                                    }
                                    break;
                                case 1461735806:
                                    if (currentName.equals("channelId")) {
                                        b = 3;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = 8;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            Analytics analytics = null;
                            ImmutableMap<String, String> immutableMap = null;
                            String str3 = null;
                            String str4 = null;
                            ItemType itemType = null;
                            String str5 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.image = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str5;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.channelId = str4;
                                    continue;
                                case 4:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    continue;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.subtext = str3;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LiveCard so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LiveCard so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.text, this, "text");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(builder.channelId, this, "channelId");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    return builder.build();
                }
            }
        }
    }
}
