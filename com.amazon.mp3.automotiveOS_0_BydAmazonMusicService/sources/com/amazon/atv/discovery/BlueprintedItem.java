package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.Blueprint;
import com.amazon.atv.discovery.Image;
import com.amazon.atv.discovery.Item;
import com.amazon.atv.discovery.NavigationalActionBase;
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

/* JADX INFO: loaded from: classes2.dex */
public class BlueprintedItem extends Item {
    public final Optional<ImmutableMap<String, String>> accessibilityMap;
    public final Blueprint blueprint;
    public final String id;
    public final Optional<ImmutableMap<String, Image>> imageMap;
    public final Optional<ImmutableMap<String, NavigationalActionBase>> linkActionMap;
    public final Optional<ImmutableMap<String, String>> textMap;

    protected BlueprintedItem(Builder builder) {
        super(builder);
        this.accessibilityMap = Optional.fromNullable(builder.accessibilityMap);
        this.imageMap = Optional.fromNullable(builder.imageMap);
        this.id = (String) Preconditions.checkNotNull(builder.id, "Unexpected null field: id");
        this.linkActionMap = Optional.fromNullable(builder.linkActionMap);
        this.textMap = Optional.fromNullable(builder.textMap);
        this.blueprint = (Blueprint) Preconditions.checkNotNull(builder.blueprint, "Unexpected null field: blueprint");
    }

    @Override // com.amazon.atv.discovery.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BlueprintedItem)) {
            return false;
        }
        BlueprintedItem blueprintedItem = (BlueprintedItem) obj;
        return super.equals(obj) && Objects.equal(this.accessibilityMap, blueprintedItem.accessibilityMap) && Objects.equal(this.imageMap, blueprintedItem.imageMap) && Objects.equal(this.id, blueprintedItem.id) && Objects.equal(this.linkActionMap, blueprintedItem.linkActionMap) && Objects.equal(this.textMap, blueprintedItem.textMap) && Objects.equal(this.blueprint, blueprintedItem.blueprint);
    }

    @Override // com.amazon.atv.discovery.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.accessibilityMap, this.imageMap, this.id, this.linkActionMap, this.textMap, this.blueprint);
    }

    @Override // com.amazon.atv.discovery.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("accessibilityMap", this.accessibilityMap).add("imageMap", this.imageMap).add("id", this.id).add("linkActionMap", this.linkActionMap).add("textMap", this.textMap).add("blueprint", this.blueprint).toString();
    }

    @NotThreadSafe
    public static class Builder extends Item.Builder {
        public ImmutableMap<String, String> accessibilityMap;
        public Blueprint blueprint;
        public String id;
        public ImmutableMap<String, Image> imageMap;
        public ImmutableMap<String, NavigationalActionBase> linkActionMap;
        public ImmutableMap<String, String> textMap;

        public BlueprintedItem build() {
            return new BlueprintedItem(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<BlueprintedItem> {
        private final Analytics.Parser mAnalyticsParser;
        private final Blueprint.Parser mBlueprintParser;
        private final MapParser<String, Image> mImageMapParser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final MapParser<String, NavigationalActionBase> mLinkActionMapParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;
        private final MapParser<String, String> mTextMapParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mTextMapParser = MapParser.newParser(stringParser, stringParser);
            this.mImageMapParser = MapParser.newParser(stringParser, new Image.Parser(objectMapper));
            this.mLinkActionMapParser = MapParser.newParser(stringParser, new NavigationalActionBase.Parser(objectMapper));
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mStringParser = stringParser;
            this.mBlueprintParser = new Blueprint.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public BlueprintedItem parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BlueprintedItem:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public BlueprintedItem parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BlueprintedItem:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private BlueprintedItem parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ImmutableMap<String, String> immutableMap;
            ItemType itemType;
            ImmutableMap<String, Image> immutableMap2;
            String str;
            ImmutableMap<String, NavigationalActionBase> immutableMap3;
            ImmutableMap<String, String> immutableMap4;
            ImmutableMap<String, String> immutableMap5;
            Blueprint blueprint;
            Analytics analytics;
            String str2;
            JsonParsingUtils.throwIfNotObject(jsonNode, "BlueprintedItem");
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
                                b = 9;
                            }
                            break;
                        case -1665842898:
                            if (next.equals("accessibilityMap")) {
                                b = 0;
                            }
                            break;
                        case -1417855761:
                            if (next.equals("textMap")) {
                                b = 6;
                            }
                            break;
                        case -859618815:
                            if (next.equals("imageMap")) {
                                b = 2;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 7;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 4;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 1;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 3;
                            }
                            break;
                        case 1600013420:
                            if (next.equals("linkActionMap")) {
                                b = 5;
                            }
                            break;
                        case 1965271699:
                            if (next.equals("blueprint")) {
                                b = 8;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 10;
                            }
                            break;
                    }
                    immutableMap = null;
                    str2 = null;
                    analytics = null;
                    blueprint = null;
                    immutableMap5 = null;
                    immutableMap4 = null;
                    immutableMap3 = null;
                    str = null;
                    immutableMap2 = null;
                    itemType = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing BlueprintedItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mTextMapParser.parse(jsonNode2);
                        }
                        builder.accessibilityMap = immutableMap;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            itemType = (ItemType) this.mItemTypeParser.parse(jsonNode2);
                        }
                        builder.type = itemType;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            immutableMap2 = this.mImageMapParser.parse(jsonNode2);
                        }
                        builder.imageMap = immutableMap2;
                        continue;
                    case 3:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        continue;
                        break;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            immutableMap3 = this.mLinkActionMapParser.parse(jsonNode2);
                        }
                        builder.linkActionMap = immutableMap3;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableMap4 = this.mTextMapParser.parse(jsonNode2);
                        }
                        builder.textMap = immutableMap4;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            immutableMap5 = this.mStringMapParser.parse(jsonNode2);
                        }
                        builder.metadata = immutableMap5;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            blueprint = this.mBlueprintParser.parse(jsonNode2);
                        }
                        builder.blueprint = blueprint;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = str2;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing BlueprintedItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.id, this, "id");
            JsonParsingUtils.checkNotNull(builder.blueprint, this, "blueprint");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private BlueprintedItem parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 9;
                                    }
                                    break;
                                case -1665842898:
                                    if (currentName.equals("accessibilityMap")) {
                                        b = 0;
                                    }
                                    break;
                                case -1417855761:
                                    if (currentName.equals("textMap")) {
                                        b = 6;
                                    }
                                    break;
                                case -859618815:
                                    if (currentName.equals("imageMap")) {
                                        b = 2;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 7;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 4;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 1;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 3;
                                    }
                                    break;
                                case 1600013420:
                                    if (currentName.equals("linkActionMap")) {
                                        b = 5;
                                    }
                                    break;
                                case 1965271699:
                                    if (currentName.equals("blueprint")) {
                                        b = 8;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = 10;
                                    }
                                    break;
                            }
                            ImmutableMap<String, String> immutableMap = null;
                            String str = null;
                            Analytics analytics = null;
                            Blueprint blueprint = null;
                            ImmutableMap<String, String> immutableMap2 = null;
                            ImmutableMap<String, String> immutableMap3 = null;
                            ImmutableMap<String, NavigationalActionBase> immutableMap4 = null;
                            String str2 = null;
                            ImmutableMap<String, Image> immutableMap5 = null;
                            ItemType itemType = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.accessibilityMap = immutableMap;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap5 = this.mImageMapParser.parse(jsonParser);
                                    }
                                    builder.imageMap = immutableMap5;
                                    continue;
                                case 3:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    continue;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str2;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap4 = this.mLinkActionMapParser.parse(jsonParser);
                                    }
                                    builder.linkActionMap = immutableMap4;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap3 = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.textMap = immutableMap3;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap2;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprint = this.mBlueprintParser.parse(jsonParser);
                                    }
                                    builder.blueprint = blueprint;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing BlueprintedItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing BlueprintedItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.id, this, "id");
                    JsonParsingUtils.checkNotNull(builder.blueprint, this, "blueprint");
                    return builder.build();
                }
            }
        }
    }
}
