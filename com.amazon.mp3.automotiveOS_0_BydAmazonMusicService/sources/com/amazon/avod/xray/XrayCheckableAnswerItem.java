package com.amazon.avod.xray;

import com.amazon.atv.discovery.Action;
import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.Blueprint;
import com.amazon.atv.discovery.ItemType;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avod.xray.ItemWithBlueprint;
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
public class XrayCheckableAnswerItem extends ItemWithBlueprint {
    public final ImmutableMap<String, String> accessibilityMap;
    public final boolean isChecked;
    public final boolean isCorrect;
    public final ImmutableMap<String, Action> linkActionMap;
    public final ImmutableMap<String, String> textMap;

    private XrayCheckableAnswerItem(Builder builder) {
        super(builder);
        this.textMap = (ImmutableMap) Preconditions.checkNotNull(builder.textMap, "Unexpected null field: textMap");
        this.isChecked = builder.isChecked;
        this.accessibilityMap = (ImmutableMap) Preconditions.checkNotNull(builder.accessibilityMap, "Unexpected null field: accessibilityMap");
        this.linkActionMap = (ImmutableMap) Preconditions.checkNotNull(builder.linkActionMap, "Unexpected null field: linkActionMap");
        this.isCorrect = builder.isCorrect;
    }

    @Override // com.amazon.avod.xray.ItemWithBlueprint, com.amazon.atv.discovery.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XrayCheckableAnswerItem)) {
            return false;
        }
        XrayCheckableAnswerItem xrayCheckableAnswerItem = (XrayCheckableAnswerItem) obj;
        return super.equals(obj) && Objects.equal(this.textMap, xrayCheckableAnswerItem.textMap) && Objects.equal(Boolean.valueOf(this.isChecked), Boolean.valueOf(xrayCheckableAnswerItem.isChecked)) && Objects.equal(this.accessibilityMap, xrayCheckableAnswerItem.accessibilityMap) && Objects.equal(this.linkActionMap, xrayCheckableAnswerItem.linkActionMap) && Objects.equal(Boolean.valueOf(this.isCorrect), Boolean.valueOf(xrayCheckableAnswerItem.isCorrect));
    }

    @Override // com.amazon.avod.xray.ItemWithBlueprint, com.amazon.atv.discovery.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.textMap, Boolean.valueOf(this.isChecked), this.accessibilityMap, this.linkActionMap, Boolean.valueOf(this.isCorrect));
    }

    @Override // com.amazon.avod.xray.ItemWithBlueprint, com.amazon.atv.discovery.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("textMap", this.textMap).add("isChecked", this.isChecked).add("accessibilityMap", this.accessibilityMap).add("linkActionMap", this.linkActionMap).add("isCorrect", this.isCorrect).toString();
    }

    @NotThreadSafe
    public static class Builder extends ItemWithBlueprint.Builder {
        public ImmutableMap<String, String> accessibilityMap;
        public boolean isChecked;
        public boolean isCorrect;
        public ImmutableMap<String, Action> linkActionMap;
        public ImmutableMap<String, String> textMap;

        @Override // com.amazon.avod.xray.ItemWithBlueprint.Builder
        public XrayCheckableAnswerItem build() {
            return new XrayCheckableAnswerItem(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XrayCheckableAnswerItem> {
        private final MapParser<String, Action> mActionMapParser;
        private final Analytics.Parser mAnalyticsParser;
        private final Blueprint.Parser mBlueprintParser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;
        private final MapParser<String, String> mTextMapParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mTextMapParser = MapParser.newParser(stringParser, stringParser);
            this.mActionMapParser = MapParser.newParser(stringParser, new Action.Parser(objectMapper));
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mStringParser = stringParser;
            this.mBlueprintParser = new Blueprint.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XrayCheckableAnswerItem parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XrayCheckableAnswerItem:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XrayCheckableAnswerItem parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XrayCheckableAnswerItem:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XrayCheckableAnswerItem parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XrayCheckableAnswerItem");
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
                                b = 10;
                            }
                            break;
                        case -1665842898:
                            if (next.equals("accessibilityMap")) {
                                b = 5;
                            }
                            break;
                        case -1417855761:
                            if (next.equals("textMap")) {
                                b = 0;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 9;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 3;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 2;
                            }
                            break;
                        case 162535197:
                            if (next.equals("isChecked")) {
                                b = 1;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 4;
                            }
                            break;
                        case 375386080:
                            if (next.equals("isCorrect")) {
                                b = 8;
                            }
                            break;
                        case 1600013420:
                            if (next.equals("linkActionMap")) {
                                b = 7;
                            }
                            break;
                        case 1965271699:
                            if (next.equals("blueprint")) {
                                b = 6;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                    }
                    ImmutableMap<String, String> immutableMap = null;
                    String str = null;
                    Analytics analytics = null;
                    ImmutableMap<String, String> immutableMap2 = null;
                    ImmutableMap<String, Action> immutableMap3 = null;
                    Blueprint blueprint = null;
                    ImmutableMap<String, String> immutableMap4 = null;
                    String str2 = null;
                    ItemType itemType = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                immutableMap = this.mTextMapParser.parse(jsonNode2);
                            }
                            builder.textMap = immutableMap;
                            break;
                        case 1:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field isChecked can't be null");
                            }
                            builder.isChecked = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                itemType = (ItemType) this.mItemTypeParser.parse(jsonNode2);
                            }
                            builder.type = itemType;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.id = str2;
                            break;
                        case 4:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 5:
                            if (!jsonNode2.isNull()) {
                                immutableMap4 = this.mTextMapParser.parse(jsonNode2);
                            }
                            builder.accessibilityMap = immutableMap4;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                blueprint = this.mBlueprintParser.parse(jsonNode2);
                            }
                            builder.blueprint = blueprint;
                            break;
                        case 7:
                            if (!jsonNode2.isNull()) {
                                immutableMap3 = this.mActionMapParser.parse(jsonNode2);
                            }
                            builder.linkActionMap = immutableMap3;
                            break;
                        case 8:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field isCorrect can't be null");
                            }
                            builder.isCorrect = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 9:
                            if (!jsonNode2.isNull()) {
                                immutableMap2 = this.mStringMapParser.parse(jsonNode2);
                            }
                            builder.metadata = immutableMap2;
                            break;
                        case 10:
                            if (!jsonNode2.isNull()) {
                                analytics = this.mAnalyticsParser.parse(jsonNode2);
                            }
                            builder.analytics = analytics;
                            break;
                        case 11:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.debugAttributes = str;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XrayCheckableAnswerItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.textMap, this, "textMap");
            JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.isChecked), this, "isChecked");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(builder.id, this, "id");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.accessibilityMap, this, "accessibilityMap");
            JsonParsingUtils.checkNotNull(builder.blueprint, this, "blueprint");
            JsonParsingUtils.checkNotNull(builder.linkActionMap, this, "linkActionMap");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XrayCheckableAnswerItem parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 10;
                                    }
                                    break;
                                case -1665842898:
                                    if (currentName.equals("accessibilityMap")) {
                                        b = 5;
                                    }
                                    break;
                                case -1417855761:
                                    if (currentName.equals("textMap")) {
                                        b = 0;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 9;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 3;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 2;
                                    }
                                    break;
                                case 162535197:
                                    if (currentName.equals("isChecked")) {
                                        b = 1;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 4;
                                    }
                                    break;
                                case 375386080:
                                    if (currentName.equals("isCorrect")) {
                                        b = 8;
                                    }
                                    break;
                                case 1600013420:
                                    if (currentName.equals("linkActionMap")) {
                                        b = 7;
                                    }
                                    break;
                                case 1965271699:
                                    if (currentName.equals("blueprint")) {
                                        b = 6;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                            }
                            ImmutableMap<String, String> immutableMap = null;
                            String str = null;
                            Analytics analytics = null;
                            ImmutableMap<String, String> immutableMap2 = null;
                            ImmutableMap<String, Action> immutableMap3 = null;
                            Blueprint blueprint = null;
                            ImmutableMap<String, String> immutableMap4 = null;
                            String str2 = null;
                            ItemType itemType = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.textMap = immutableMap;
                                    break;
                                case 1:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field isChecked can't be null");
                                    }
                                    builder.isChecked = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str2;
                                    break;
                                case 4:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap4 = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.accessibilityMap = immutableMap4;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprint = this.mBlueprintParser.parse(jsonParser);
                                    }
                                    builder.blueprint = blueprint;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap3 = this.mActionMapParser.parse(jsonParser);
                                    }
                                    builder.linkActionMap = immutableMap3;
                                    break;
                                case 8:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field isCorrect can't be null");
                                    }
                                    builder.isCorrect = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap2;
                                    break;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XrayCheckableAnswerItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.textMap, this, "textMap");
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.isChecked), this, "isChecked");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(builder.id, this, "id");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.accessibilityMap, this, "accessibilityMap");
                    JsonParsingUtils.checkNotNull(builder.blueprint, this, "blueprint");
                    JsonParsingUtils.checkNotNull(builder.linkActionMap, this, "linkActionMap");
                    return builder.build();
                }
            }
        }
    }
}
