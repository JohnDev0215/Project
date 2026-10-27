package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.schedule.ChannelSchedule;
import com.amazon.atv.xrayv2.Image;
import com.amazon.atv.xrayv2.LinkItem;
import com.amazon.atv.xrayv2.NavigationalAction;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class LiveChannelCard extends LinkItem {
    public final String channelId;
    public final ChannelSchedule channelSchedule;
    public final Image image;
    public final String text;

    private LiveChannelCard(Builder builder) {
        super(builder);
        this.channelSchedule = (ChannelSchedule) Preconditions.checkNotNull(builder.channelSchedule, "Unexpected null field: channelSchedule");
        this.image = (Image) Preconditions.checkNotNull(builder.image, "Unexpected null field: image");
        this.channelId = (String) Preconditions.checkNotNull(builder.channelId, "Unexpected null field: channelId");
        this.text = (String) Preconditions.checkNotNull(builder.text, "Unexpected null field: text");
    }

    @Override // com.amazon.atv.xrayv2.LinkItem, com.amazon.atv.xrayv2.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LiveChannelCard)) {
            return false;
        }
        LiveChannelCard liveChannelCard = (LiveChannelCard) obj;
        return super.equals(obj) && Objects.equal(this.channelSchedule, liveChannelCard.channelSchedule) && Objects.equal(this.image, liveChannelCard.image) && Objects.equal(this.channelId, liveChannelCard.channelId) && Objects.equal(this.text, liveChannelCard.text);
    }

    @Override // com.amazon.atv.xrayv2.LinkItem, com.amazon.atv.xrayv2.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.channelSchedule, this.image, this.channelId, this.text);
    }

    @Override // com.amazon.atv.xrayv2.LinkItem, com.amazon.atv.xrayv2.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("channelSchedule", this.channelSchedule).add(ContextMappingConstants.IMAGE, this.image).add("channelId", this.channelId).add("text", this.text).toString();
    }

    @NotThreadSafe
    public static class Builder extends LinkItem.Builder {
        public String channelId;
        public ChannelSchedule channelSchedule;
        public Image image;
        public String text;

        public LiveChannelCard build() {
            return new LiveChannelCard(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LiveChannelCard> {
        private final Analytics.Parser mAnalyticsParser;
        private final ChannelSchedule.Parser mChannelScheduleParser;
        private final Image.Parser mImageParser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final NavigationalAction.Parser mNavigationalActionParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mChannelScheduleParser = new ChannelSchedule.Parser(objectMapper);
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            this.mImageParser = new Image.Parser(objectMapper);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mStringParser = stringParser;
            this.mNavigationalActionParser = new NavigationalAction.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LiveChannelCard parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LiveChannelCard:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LiveChannelCard parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LiveChannelCard:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private LiveChannelCard parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LiveChannelCard");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 1;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 5;
                            }
                            break;
                        case -229850950:
                            if (next.equals("channelSchedule")) {
                                b = 0;
                            }
                            break;
                        case 3321850:
                            if (next.equals("link")) {
                                b = 4;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 10;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 7;
                            }
                            break;
                        case 100313435:
                            if (next.equals(ContextMappingConstants.IMAGE)) {
                                b = 3;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 9;
                            }
                            break;
                        case 1191572123:
                            if (next.equals("selected")) {
                                b = 8;
                            }
                            break;
                        case 1461735806:
                            if (next.equals("channelId")) {
                                b = 6;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 2;
                            }
                            break;
                    }
                    ChannelSchedule channelSchedule = null;
                    String str = null;
                    ItemType itemType = null;
                    String str2 = null;
                    ImmutableMap<String, String> immutableMap = null;
                    NavigationalAction navigationalAction = null;
                    Image image = null;
                    String str3 = null;
                    Analytics analytics = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                channelSchedule = this.mChannelScheduleParser.parse(jsonNode2);
                            }
                            builder.channelSchedule = channelSchedule;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                analytics = this.mAnalyticsParser.parse(jsonNode2);
                            }
                            builder.analytics = analytics;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                str3 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.debugAttributes = str3;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                image = this.mImageParser.parse(jsonNode2);
                            }
                            builder.image = image;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                navigationalAction = this.mNavigationalActionParser.parse(jsonNode2);
                            }
                            builder.link = navigationalAction;
                            break;
                        case 5:
                            if (!jsonNode2.isNull()) {
                                immutableMap = this.mStringMapParser.parse(jsonNode2);
                            }
                            builder.metadata = immutableMap;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.channelId = str2;
                            break;
                        case 7:
                            if (!jsonNode2.isNull()) {
                                itemType = (ItemType) this.mItemTypeParser.parse(jsonNode2);
                            }
                            builder.type = itemType;
                            break;
                        case 8:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field selected can't be null");
                            }
                            builder.selected = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 9:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 10:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.text = str;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LiveChannelCard so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.channelSchedule, this, "channelSchedule");
            JsonParsingUtils.checkNotNull(builder.image, this, ContextMappingConstants.IMAGE);
            JsonParsingUtils.checkNotNull(builder.link, this, "link");
            JsonParsingUtils.checkNotNull(builder.channelId, this, "channelId");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.text, this, "text");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private LiveChannelCard parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 1;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 5;
                                    }
                                    break;
                                case -229850950:
                                    if (currentName.equals("channelSchedule")) {
                                        b = 0;
                                    }
                                    break;
                                case 3321850:
                                    if (currentName.equals("link")) {
                                        b = 4;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 10;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 7;
                                    }
                                    break;
                                case 100313435:
                                    if (currentName.equals(ContextMappingConstants.IMAGE)) {
                                        b = 3;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 9;
                                    }
                                    break;
                                case 1191572123:
                                    if (currentName.equals("selected")) {
                                        b = 8;
                                    }
                                    break;
                                case 1461735806:
                                    if (currentName.equals("channelId")) {
                                        b = 6;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            ChannelSchedule channelSchedule = null;
                            String str = null;
                            ItemType itemType = null;
                            String str2 = null;
                            ImmutableMap<String, String> immutableMap = null;
                            NavigationalAction navigationalAction = null;
                            Image image = null;
                            String str3 = null;
                            Analytics analytics = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        channelSchedule = this.mChannelScheduleParser.parse(jsonParser);
                                    }
                                    builder.channelSchedule = channelSchedule;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str3;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        image = this.mImageParser.parse(jsonParser);
                                    }
                                    builder.image = image;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        navigationalAction = this.mNavigationalActionParser.parse(jsonParser);
                                    }
                                    builder.link = navigationalAction;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.channelId = str2;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    break;
                                case 8:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field selected can't be null");
                                    }
                                    builder.selected = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 9:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LiveChannelCard so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.channelSchedule, this, "channelSchedule");
                    JsonParsingUtils.checkNotNull(builder.image, this, ContextMappingConstants.IMAGE);
                    JsonParsingUtils.checkNotNull(builder.link, this, "link");
                    JsonParsingUtils.checkNotNull(builder.channelId, this, "channelId");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.text, this, "text");
                    return builder.build();
                }
            }
        }
    }
}
