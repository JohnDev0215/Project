package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.xrayv2.Blueprint;
import com.amazon.atv.xrayv2.CollectionV2;
import com.amazon.atv.xrayv2.Image;
import com.amazon.atv.xrayv2.ItemsBase;
import com.amazon.atv.xrayv2.NavigationalActionBase;
import com.amazon.atv.xrayv2.RelatedCollectionBlueprintedItem;
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
public class RelatedCollectionWithIconsBlueprintedItem extends RelatedCollectionBlueprintedItem {
    public final CollectionV2 icons;

    private RelatedCollectionWithIconsBlueprintedItem(Builder builder) {
        super(builder);
        this.icons = (CollectionV2) Preconditions.checkNotNull(builder.icons, "Unexpected null field: icons");
    }

    @Override // com.amazon.atv.xrayv2.RelatedCollectionBlueprintedItem, com.amazon.atv.xrayv2.BlueprintedItem, com.amazon.atv.xrayv2.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RelatedCollectionWithIconsBlueprintedItem) {
            return super.equals(obj) && Objects.equal(this.icons, ((RelatedCollectionWithIconsBlueprintedItem) obj).icons);
        }
        return false;
    }

    @Override // com.amazon.atv.xrayv2.RelatedCollectionBlueprintedItem, com.amazon.atv.xrayv2.BlueprintedItem, com.amazon.atv.xrayv2.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.icons);
    }

    @Override // com.amazon.atv.xrayv2.RelatedCollectionBlueprintedItem, com.amazon.atv.xrayv2.BlueprintedItem, com.amazon.atv.xrayv2.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("icons", this.icons).toString();
    }

    @NotThreadSafe
    public static class Builder extends RelatedCollectionBlueprintedItem.Builder {
        public CollectionV2 icons;

        @Override // com.amazon.atv.xrayv2.RelatedCollectionBlueprintedItem.Builder, com.amazon.atv.xrayv2.BlueprintedItem.Builder
        public RelatedCollectionWithIconsBlueprintedItem build() {
            return new RelatedCollectionWithIconsBlueprintedItem(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RelatedCollectionWithIconsBlueprintedItem> {
        private final Analytics.Parser mAnalyticsParser;
        private final Blueprint.Parser mBlueprintParser;
        private final CollectionV2.Parser mCollectionV2Parser;
        private final MapParser<String, Image> mImageMapParser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final ItemsBase.Parser mItemsBaseParser;
        private final MapParser<String, NavigationalActionBase> mLinkActionMapParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;
        private final MapParser<String, String> mTextMapParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            this.mCollectionV2Parser = new CollectionV2.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mTextMapParser = MapParser.newParser(stringParser, stringParser);
            this.mImageMapParser = MapParser.newParser(stringParser, new Image.Parser(objectMapper));
            this.mLinkActionMapParser = MapParser.newParser(stringParser, new NavigationalActionBase.Parser(objectMapper));
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mBlueprintParser = new Blueprint.Parser(objectMapper);
            this.mItemsBaseParser = new ItemsBase.Parser(objectMapper);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RelatedCollectionWithIconsBlueprintedItem parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RelatedCollectionWithIconsBlueprintedItem:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RelatedCollectionWithIconsBlueprintedItem parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RelatedCollectionWithIconsBlueprintedItem:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RelatedCollectionWithIconsBlueprintedItem parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ImmutableMap<String, String> immutableMap;
            ItemsBase itemsBase;
            String str;
            Analytics analytics;
            ImmutableMap<String, String> immutableMap2;
            ItemType itemType;
            Blueprint blueprint;
            ImmutableMap<String, String> immutableMap3;
            ImmutableMap<String, Image> immutableMap4;
            String str2;
            ImmutableMap<String, NavigationalActionBase> immutableMap5;
            CollectionV2 collectionV2;
            JsonParsingUtils.throwIfNotObject(jsonNode, "RelatedCollectionWithIconsBlueprintedItem");
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
                                b = 3;
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
                        case -1130484439:
                            if (next.equals("relatedCollection")) {
                                b = 1;
                            }
                            break;
                        case -859618815:
                            if (next.equals("imageMap")) {
                                b = 9;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 4;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 2;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 5;
                            }
                            break;
                        case 100029210:
                            if (next.equals("icons")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 6;
                            }
                            break;
                        case 1600013420:
                            if (next.equals("linkActionMap")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 1965271699:
                            if (next.equals("blueprint")) {
                                b = 7;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 10;
                            }
                            break;
                    }
                    immutableMap = null;
                    collectionV2 = null;
                    immutableMap5 = null;
                    str2 = null;
                    immutableMap4 = null;
                    immutableMap3 = null;
                    blueprint = null;
                    itemType = null;
                    immutableMap2 = null;
                    analytics = null;
                    str = null;
                    itemsBase = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RelatedCollectionWithIconsBlueprintedItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mTextMapParser.parse(jsonNode2);
                        }
                        builder.textMap = immutableMap;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            itemsBase = this.mItemsBaseParser.parse(jsonNode2);
                        }
                        builder.relatedCollection = itemsBase;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            immutableMap2 = this.mStringMapParser.parse(jsonNode2);
                        }
                        builder.metadata = immutableMap2;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            itemType = (ItemType) this.mItemTypeParser.parse(jsonNode2);
                        }
                        builder.type = itemType;
                        continue;
                    case 6:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        continue;
                        break;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            blueprint = this.mBlueprintParser.parse(jsonNode2);
                        }
                        builder.blueprint = blueprint;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            immutableMap3 = this.mTextMapParser.parse(jsonNode2);
                        }
                        builder.accessibilityMap = immutableMap3;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            immutableMap4 = this.mImageMapParser.parse(jsonNode2);
                        }
                        builder.imageMap = immutableMap4;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = str2;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            immutableMap5 = this.mLinkActionMapParser.parse(jsonNode2);
                        }
                        builder.linkActionMap = immutableMap5;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            collectionV2 = this.mCollectionV2Parser.parse(jsonNode2);
                        }
                        builder.icons = collectionV2;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RelatedCollectionWithIconsBlueprintedItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.relatedCollection, this, "relatedCollection");
            JsonParsingUtils.checkNotNull(builder.id, this, "id");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.blueprint, this, "blueprint");
            JsonParsingUtils.checkNotNull(builder.icons, this, "icons");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RelatedCollectionWithIconsBlueprintedItem parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 3;
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
                                case -1130484439:
                                    if (currentName.equals("relatedCollection")) {
                                        b = 1;
                                    }
                                    break;
                                case -859618815:
                                    if (currentName.equals("imageMap")) {
                                        b = 9;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 4;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 2;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 5;
                                    }
                                    break;
                                case 100029210:
                                    if (currentName.equals("icons")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 6;
                                    }
                                    break;
                                case 1600013420:
                                    if (currentName.equals("linkActionMap")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 1965271699:
                                    if (currentName.equals("blueprint")) {
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
                            CollectionV2 collectionV2 = null;
                            ImmutableMap<String, NavigationalActionBase> immutableMap2 = null;
                            String str = null;
                            ImmutableMap<String, Image> immutableMap3 = null;
                            ImmutableMap<String, String> immutableMap4 = null;
                            Blueprint blueprint = null;
                            ItemType itemType = null;
                            ImmutableMap<String, String> immutableMap5 = null;
                            Analytics analytics = null;
                            String str2 = null;
                            ItemsBase itemsBase = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.textMap = immutableMap;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemsBase = this.mItemsBaseParser.parse(jsonParser);
                                    }
                                    builder.relatedCollection = itemsBase;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str2;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap5 = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap5;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    continue;
                                case 6:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    continue;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprint = this.mBlueprintParser.parse(jsonParser);
                                    }
                                    builder.blueprint = blueprint;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap4 = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.accessibilityMap = immutableMap4;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap3 = this.mImageMapParser.parse(jsonParser);
                                    }
                                    builder.imageMap = immutableMap3;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mLinkActionMapParser.parse(jsonParser);
                                    }
                                    builder.linkActionMap = immutableMap2;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        collectionV2 = this.mCollectionV2Parser.parse(jsonParser);
                                    }
                                    builder.icons = collectionV2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RelatedCollectionWithIconsBlueprintedItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RelatedCollectionWithIconsBlueprintedItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.relatedCollection, this, "relatedCollection");
                    JsonParsingUtils.checkNotNull(builder.id, this, "id");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.blueprint, this, "blueprint");
                    JsonParsingUtils.checkNotNull(builder.icons, this, "icons");
                    return builder.build();
                }
            }
        }
    }
}
