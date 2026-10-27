package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.xrayv2.Blueprint;
import com.amazon.atv.xrayv2.BlueprintedItem;
import com.amazon.atv.xrayv2.Image;
import com.amazon.atv.xrayv2.NavigationalActionBase;
import com.amazon.atv.xrayv2.TimeRange;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
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
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class XrayTrickplayItem extends BlueprintedItem {
    public final TimeRange trickPlayTimeRange;
    public final long trickplayFramePositionMillis;

    private XrayTrickplayItem(Builder builder) {
        super(builder);
        this.trickplayFramePositionMillis = builder.trickplayFramePositionMillis;
        this.trickPlayTimeRange = (TimeRange) Preconditions.checkNotNull(builder.trickPlayTimeRange, "Unexpected null field: trickPlayTimeRange");
    }

    @Override // com.amazon.atv.xrayv2.BlueprintedItem, com.amazon.atv.xrayv2.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XrayTrickplayItem)) {
            return false;
        }
        XrayTrickplayItem xrayTrickplayItem = (XrayTrickplayItem) obj;
        return super.equals(obj) && Objects.equal(Long.valueOf(this.trickplayFramePositionMillis), Long.valueOf(xrayTrickplayItem.trickplayFramePositionMillis)) && Objects.equal(this.trickPlayTimeRange, xrayTrickplayItem.trickPlayTimeRange);
    }

    @Override // com.amazon.atv.xrayv2.BlueprintedItem, com.amazon.atv.xrayv2.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), Long.valueOf(this.trickplayFramePositionMillis), this.trickPlayTimeRange);
    }

    @Override // com.amazon.atv.xrayv2.BlueprintedItem, com.amazon.atv.xrayv2.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("trickplayFramePositionMillis", this.trickplayFramePositionMillis).add("trickPlayTimeRange", this.trickPlayTimeRange).toString();
    }

    @NotThreadSafe
    public static class Builder extends BlueprintedItem.Builder {
        public TimeRange trickPlayTimeRange;
        public long trickplayFramePositionMillis;

        @Override // com.amazon.atv.xrayv2.BlueprintedItem.Builder
        public XrayTrickplayItem build() {
            return new XrayTrickplayItem(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XrayTrickplayItem> {
        private final Analytics.Parser mAnalyticsParser;
        private final Blueprint.Parser mBlueprintParser;
        private final MapParser<String, Image> mImageMapParser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final MapParser<String, NavigationalActionBase> mLinkActionMapParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;
        private final MapParser<String, String> mTextMapParser;
        private final TimeRange.Parser mTimeRangeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mTextMapParser = MapParser.newParser(stringParser, stringParser);
            this.mImageMapParser = MapParser.newParser(stringParser, new Image.Parser(objectMapper));
            this.mLinkActionMapParser = MapParser.newParser(stringParser, new NavigationalActionBase.Parser(objectMapper));
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mTimeRangeParser = new TimeRange.Parser(objectMapper);
            this.mBlueprintParser = new Blueprint.Parser(objectMapper);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XrayTrickplayItem parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XrayTrickplayItem:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XrayTrickplayItem parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XrayTrickplayItem:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XrayTrickplayItem parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XrayTrickplayItem");
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
                                b = 2;
                            }
                            break;
                        case -1665842898:
                            if (next.equals("accessibilityMap")) {
                                b = 8;
                            }
                            break;
                        case -1417855761:
                            if (next.equals("textMap")) {
                                b = 0;
                            }
                            break;
                        case -867926775:
                            if (next.equals("trickPlayTimeRange")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case -859618815:
                            if (next.equals("imageMap")) {
                                b = 9;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 3;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 1;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 4;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 5;
                            }
                            break;
                        case 1600013420:
                            if (next.equals("linkActionMap")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 1965271699:
                            if (next.equals("blueprint")) {
                                b = 6;
                            }
                            break;
                        case 2104630037:
                            if (next.equals("trickplayFramePositionMillis")) {
                                b = 7;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 10;
                            }
                            break;
                    }
                    ImmutableMap<String, String> immutableMap = null;
                    TimeRange timeRange = null;
                    ImmutableMap<String, NavigationalActionBase> immutableMap2 = null;
                    String str = null;
                    ImmutableMap<String, Image> immutableMap3 = null;
                    ImmutableMap<String, String> immutableMap4 = null;
                    Blueprint blueprint = null;
                    ItemType itemType = null;
                    ImmutableMap<String, String> immutableMap5 = null;
                    Analytics analytics = null;
                    String str2 = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                immutableMap = this.mTextMapParser.parse(jsonNode2);
                            }
                            builder.textMap = immutableMap;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.id = str2;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                analytics = this.mAnalyticsParser.parse(jsonNode2);
                            }
                            builder.analytics = analytics;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                immutableMap5 = this.mStringMapParser.parse(jsonNode2);
                            }
                            builder.metadata = immutableMap5;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                itemType = (ItemType) this.mItemTypeParser.parse(jsonNode2);
                            }
                            builder.type = itemType;
                            break;
                        case 5:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                blueprint = this.mBlueprintParser.parse(jsonNode2);
                            }
                            builder.blueprint = blueprint;
                            break;
                        case 7:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field trickplayFramePositionMillis can't be null");
                            }
                            builder.trickplayFramePositionMillis = SimpleParsers.parsePrimitiveLong(jsonNode2);
                            break;
                            break;
                        case 8:
                            if (!jsonNode2.isNull()) {
                                immutableMap4 = this.mTextMapParser.parse(jsonNode2);
                            }
                            builder.accessibilityMap = immutableMap4;
                            break;
                        case 9:
                            if (!jsonNode2.isNull()) {
                                immutableMap3 = this.mImageMapParser.parse(jsonNode2);
                            }
                            builder.imageMap = immutableMap3;
                            break;
                        case 10:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.debugAttributes = str;
                            break;
                        case 11:
                            if (!jsonNode2.isNull()) {
                                immutableMap2 = this.mLinkActionMapParser.parse(jsonNode2);
                            }
                            builder.linkActionMap = immutableMap2;
                            break;
                        case 12:
                            if (!jsonNode2.isNull()) {
                                timeRange = this.mTimeRangeParser.parse(jsonNode2);
                            }
                            builder.trickPlayTimeRange = timeRange;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XrayTrickplayItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.id, this, "id");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.blueprint, this, "blueprint");
            JsonParsingUtils.checkNotNull(Long.valueOf(builder.trickplayFramePositionMillis), this, "trickplayFramePositionMillis");
            JsonParsingUtils.checkNotNull(builder.trickPlayTimeRange, this, "trickPlayTimeRange");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XrayTrickplayItem parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 2;
                                    }
                                    break;
                                case -1665842898:
                                    if (currentName.equals("accessibilityMap")) {
                                        b = 8;
                                    }
                                    break;
                                case -1417855761:
                                    if (currentName.equals("textMap")) {
                                        b = 0;
                                    }
                                    break;
                                case -867926775:
                                    if (currentName.equals("trickPlayTimeRange")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case -859618815:
                                    if (currentName.equals("imageMap")) {
                                        b = 9;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 3;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 1;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 4;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 5;
                                    }
                                    break;
                                case 1600013420:
                                    if (currentName.equals("linkActionMap")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 1965271699:
                                    if (currentName.equals("blueprint")) {
                                        b = 6;
                                    }
                                    break;
                                case 2104630037:
                                    if (currentName.equals("trickplayFramePositionMillis")) {
                                        b = 7;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = 10;
                                    }
                                    break;
                            }
                            ImmutableMap<String, String> immutableMap = null;
                            TimeRange timeRange = null;
                            ImmutableMap<String, NavigationalActionBase> immutableMap2 = null;
                            String str = null;
                            ImmutableMap<String, Image> immutableMap3 = null;
                            ImmutableMap<String, String> immutableMap4 = null;
                            Blueprint blueprint = null;
                            ItemType itemType = null;
                            ImmutableMap<String, String> immutableMap5 = null;
                            Analytics analytics = null;
                            String str2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.textMap = immutableMap;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str2;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap5 = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap5;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    break;
                                case 5:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprint = this.mBlueprintParser.parse(jsonParser);
                                    }
                                    builder.blueprint = blueprint;
                                    break;
                                case 7:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field trickplayFramePositionMillis can't be null");
                                    }
                                    builder.trickplayFramePositionMillis = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap4 = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.accessibilityMap = immutableMap4;
                                    break;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap3 = this.mImageMapParser.parse(jsonParser);
                                    }
                                    builder.imageMap = immutableMap3;
                                    break;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str;
                                    break;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mLinkActionMapParser.parse(jsonParser);
                                    }
                                    builder.linkActionMap = immutableMap2;
                                    break;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        timeRange = this.mTimeRangeParser.parse(jsonParser);
                                    }
                                    builder.trickPlayTimeRange = timeRange;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XrayTrickplayItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.id, this, "id");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.blueprint, this, "blueprint");
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.trickplayFramePositionMillis), this, "trickplayFramePositionMillis");
                    JsonParsingUtils.checkNotNull(builder.trickPlayTimeRange, this, "trickPlayTimeRange");
                    return builder.build();
                }
            }
        }
    }
}
