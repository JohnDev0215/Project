package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.xrayv2.Blueprint;
import com.amazon.atv.xrayv2.BlueprintedItem;
import com.amazon.atv.xrayv2.CollectionV2;
import com.amazon.atv.xrayv2.ItemWithBlueprint;
import com.amazon.atv.xrayv2.ItemsV2;
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
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class XRayScoreboardItem extends ItemWithBlueprint {
    public final Optional<ItemsV2> expandableCollection;
    public final BlueprintedItem gameInfo;
    public final BlueprintedItem leftTeam;
    public final Optional<CollectionV2> leftTeamIcons;
    public final BlueprintedItem rightTeam;
    public final Optional<CollectionV2> rightTeamIcons;

    private XRayScoreboardItem(Builder builder) {
        super(builder);
        this.rightTeam = (BlueprintedItem) Preconditions.checkNotNull(builder.rightTeam, "Unexpected null field: rightTeam");
        this.expandableCollection = Optional.fromNullable(builder.expandableCollection);
        this.gameInfo = (BlueprintedItem) Preconditions.checkNotNull(builder.gameInfo, "Unexpected null field: gameInfo");
        this.rightTeamIcons = Optional.fromNullable(builder.rightTeamIcons);
        this.leftTeam = (BlueprintedItem) Preconditions.checkNotNull(builder.leftTeam, "Unexpected null field: leftTeam");
        this.leftTeamIcons = Optional.fromNullable(builder.leftTeamIcons);
    }

    @Override // com.amazon.atv.xrayv2.ItemWithBlueprint, com.amazon.atv.xrayv2.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XRayScoreboardItem)) {
            return false;
        }
        XRayScoreboardItem xRayScoreboardItem = (XRayScoreboardItem) obj;
        return super.equals(obj) && Objects.equal(this.rightTeam, xRayScoreboardItem.rightTeam) && Objects.equal(this.expandableCollection, xRayScoreboardItem.expandableCollection) && Objects.equal(this.gameInfo, xRayScoreboardItem.gameInfo) && Objects.equal(this.rightTeamIcons, xRayScoreboardItem.rightTeamIcons) && Objects.equal(this.leftTeam, xRayScoreboardItem.leftTeam) && Objects.equal(this.leftTeamIcons, xRayScoreboardItem.leftTeamIcons);
    }

    @Override // com.amazon.atv.xrayv2.ItemWithBlueprint, com.amazon.atv.xrayv2.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.rightTeam, this.expandableCollection, this.gameInfo, this.rightTeamIcons, this.leftTeam, this.leftTeamIcons);
    }

    @Override // com.amazon.atv.xrayv2.ItemWithBlueprint, com.amazon.atv.xrayv2.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("rightTeam", this.rightTeam).add("expandableCollection", this.expandableCollection).add("gameInfo", this.gameInfo).add("rightTeamIcons", this.rightTeamIcons).add("leftTeam", this.leftTeam).add("leftTeamIcons", this.leftTeamIcons).toString();
    }

    @NotThreadSafe
    public static class Builder extends ItemWithBlueprint.Builder {
        public ItemsV2 expandableCollection;
        public BlueprintedItem gameInfo;
        public BlueprintedItem leftTeam;
        public CollectionV2 leftTeamIcons;
        public BlueprintedItem rightTeam;
        public CollectionV2 rightTeamIcons;

        @Override // com.amazon.atv.xrayv2.ItemWithBlueprint.Builder
        public XRayScoreboardItem build() {
            return new XRayScoreboardItem(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XRayScoreboardItem> {
        private final Analytics.Parser mAnalyticsParser;
        private final Blueprint.Parser mBlueprintParser;
        private final BlueprintedItem.Parser mBlueprintedItemParser;
        private final CollectionV2.Parser mCollectionV2Parser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final ItemsV2.Parser mItemsV2Parser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            this.mBlueprintedItemParser = new BlueprintedItem.Parser(objectMapper);
            this.mCollectionV2Parser = new CollectionV2.Parser(objectMapper);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mBlueprintParser = new Blueprint.Parser(objectMapper);
            this.mItemsV2Parser = new ItemsV2.Parser(objectMapper);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XRayScoreboardItem parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayScoreboardItem:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XRayScoreboardItem parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayScoreboardItem:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRayScoreboardItem parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Analytics analytics;
            Blueprint blueprint;
            ImmutableMap<String, String> immutableMap;
            CollectionV2 collectionV2;
            BlueprintedItem blueprintedItem;
            ItemType itemType;
            CollectionV2 collectionV22;
            BlueprintedItem blueprintedItem2;
            ItemsV2 itemsV2;
            BlueprintedItem blueprintedItem3;
            String str;
            String str2;
            JsonParsingUtils.throwIfNotObject(jsonNode, "XRayScoreboardItem");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1769481280:
                            if (next.equals("gameInfo")) {
                                b = 10;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 0;
                            }
                            break;
                        case -1569125735:
                            if (next.equals("rightTeam")) {
                                b = 8;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 2;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 5;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 7;
                            }
                            break;
                        case 410954358:
                            if (next.equals("leftTeamIcons")) {
                                b = 6;
                            }
                            break;
                        case 451284993:
                            if (next.equals("rightTeamIcons")) {
                                b = 3;
                            }
                            break;
                        case 1353711794:
                            if (next.equals("expandableCollection")) {
                                b = 9;
                            }
                            break;
                        case 1718418180:
                            if (next.equals("leftTeam")) {
                                b = 4;
                            }
                            break;
                        case 1965271699:
                            if (next.equals("blueprint")) {
                                b = 1;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                    }
                    analytics = null;
                    str2 = null;
                    str = null;
                    blueprintedItem3 = null;
                    itemsV2 = null;
                    blueprintedItem2 = null;
                    collectionV22 = null;
                    itemType = null;
                    blueprintedItem = null;
                    collectionV2 = null;
                    immutableMap = null;
                    blueprint = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRayScoreboardItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            blueprint = this.mBlueprintParser.parse(jsonNode2);
                        }
                        builder.blueprint = blueprint;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mStringMapParser.parse(jsonNode2);
                        }
                        builder.metadata = immutableMap;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            collectionV2 = this.mCollectionV2Parser.parse(jsonNode2);
                        }
                        builder.rightTeamIcons = collectionV2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            blueprintedItem = this.mBlueprintedItemParser.parse(jsonNode2);
                        }
                        builder.leftTeam = blueprintedItem;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            itemType = (ItemType) this.mItemTypeParser.parse(jsonNode2);
                        }
                        builder.type = itemType;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            collectionV22 = this.mCollectionV2Parser.parse(jsonNode2);
                        }
                        builder.leftTeamIcons = collectionV22;
                        continue;
                    case 7:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        continue;
                        break;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            blueprintedItem2 = this.mBlueprintedItemParser.parse(jsonNode2);
                        }
                        builder.rightTeam = blueprintedItem2;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            itemsV2 = this.mItemsV2Parser.parse(jsonNode2);
                        }
                        builder.expandableCollection = itemsV2;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            blueprintedItem3 = this.mBlueprintedItemParser.parse(jsonNode2);
                        }
                        builder.gameInfo = blueprintedItem3;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = str;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str2;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRayScoreboardItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.blueprint, this, "blueprint");
            JsonParsingUtils.checkNotNull(builder.leftTeam, this, "leftTeam");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.rightTeam, this, "rightTeam");
            JsonParsingUtils.checkNotNull(builder.gameInfo, this, "gameInfo");
            JsonParsingUtils.checkNotNull(builder.id, this, "id");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRayScoreboardItem parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1769481280:
                                    if (currentName.equals("gameInfo")) {
                                        b = 10;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 0;
                                    }
                                    break;
                                case -1569125735:
                                    if (currentName.equals("rightTeam")) {
                                        b = 8;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 2;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 5;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 7;
                                    }
                                    break;
                                case 410954358:
                                    if (currentName.equals("leftTeamIcons")) {
                                        b = 6;
                                    }
                                    break;
                                case 451284993:
                                    if (currentName.equals("rightTeamIcons")) {
                                        b = 3;
                                    }
                                    break;
                                case 1353711794:
                                    if (currentName.equals("expandableCollection")) {
                                        b = 9;
                                    }
                                    break;
                                case 1718418180:
                                    if (currentName.equals("leftTeam")) {
                                        b = 4;
                                    }
                                    break;
                                case 1965271699:
                                    if (currentName.equals("blueprint")) {
                                        b = 1;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                            }
                            Analytics analytics = null;
                            String str = null;
                            String str2 = null;
                            BlueprintedItem blueprintedItem = null;
                            ItemsV2 itemsV2 = null;
                            BlueprintedItem blueprintedItem2 = null;
                            CollectionV2 collectionV2 = null;
                            ItemType itemType = null;
                            BlueprintedItem blueprintedItem3 = null;
                            CollectionV2 collectionV22 = null;
                            ImmutableMap<String, String> immutableMap = null;
                            Blueprint blueprint = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprint = this.mBlueprintParser.parse(jsonParser);
                                    }
                                    builder.blueprint = blueprint;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        collectionV22 = this.mCollectionV2Parser.parse(jsonParser);
                                    }
                                    builder.rightTeamIcons = collectionV22;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprintedItem3 = this.mBlueprintedItemParser.parse(jsonParser);
                                    }
                                    builder.leftTeam = blueprintedItem3;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        collectionV2 = this.mCollectionV2Parser.parse(jsonParser);
                                    }
                                    builder.leftTeamIcons = collectionV2;
                                    continue;
                                case 7:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    continue;
                                    break;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprintedItem2 = this.mBlueprintedItemParser.parse(jsonParser);
                                    }
                                    builder.rightTeam = blueprintedItem2;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemsV2 = this.mItemsV2Parser.parse(jsonParser);
                                    }
                                    builder.expandableCollection = itemsV2;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprintedItem = this.mBlueprintedItemParser.parse(jsonParser);
                                    }
                                    builder.gameInfo = blueprintedItem;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str2;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRayScoreboardItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRayScoreboardItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.blueprint, this, "blueprint");
                    JsonParsingUtils.checkNotNull(builder.leftTeam, this, "leftTeam");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.rightTeam, this, "rightTeam");
                    JsonParsingUtils.checkNotNull(builder.gameInfo, this, "gameInfo");
                    JsonParsingUtils.checkNotNull(builder.id, this, "id");
                    return builder.build();
                }
            }
        }
    }
}
