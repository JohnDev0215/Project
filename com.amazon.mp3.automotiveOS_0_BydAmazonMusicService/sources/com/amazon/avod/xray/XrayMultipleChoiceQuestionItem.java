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
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avod.xray.ItemWithBlueprint;
import com.amazon.avod.xray.XrayCheckableAnswerItem;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class XrayMultipleChoiceQuestionItem extends ItemWithBlueprint {
    public final ImmutableMap<String, String> accessibilityMap;
    public final long lastUpdatedTime;
    public final Optional<ImmutableMap<String, Action>> linkActionMap;
    public final XrayQuestionItemState questionState;
    public final ImmutableList<XrayCheckableAnswerItem> relatedItems;
    public final ImmutableMap<String, String> textMap;

    private XrayMultipleChoiceQuestionItem(Builder builder) {
        super(builder);
        this.relatedItems = (ImmutableList) Preconditions.checkNotNull(builder.relatedItems, "Unexpected null field: relatedItems");
        this.accessibilityMap = (ImmutableMap) Preconditions.checkNotNull(builder.accessibilityMap, "Unexpected null field: accessibilityMap");
        this.lastUpdatedTime = builder.lastUpdatedTime;
        this.textMap = (ImmutableMap) Preconditions.checkNotNull(builder.textMap, "Unexpected null field: textMap");
        this.linkActionMap = Optional.fromNullable(builder.linkActionMap);
        this.questionState = (XrayQuestionItemState) Preconditions.checkNotNull(builder.questionState, "Unexpected null field: questionState");
    }

    @Override // com.amazon.avod.xray.ItemWithBlueprint, com.amazon.atv.discovery.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XrayMultipleChoiceQuestionItem)) {
            return false;
        }
        XrayMultipleChoiceQuestionItem xrayMultipleChoiceQuestionItem = (XrayMultipleChoiceQuestionItem) obj;
        return super.equals(obj) && Objects.equal(this.relatedItems, xrayMultipleChoiceQuestionItem.relatedItems) && Objects.equal(this.accessibilityMap, xrayMultipleChoiceQuestionItem.accessibilityMap) && Objects.equal(Long.valueOf(this.lastUpdatedTime), Long.valueOf(xrayMultipleChoiceQuestionItem.lastUpdatedTime)) && Objects.equal(this.textMap, xrayMultipleChoiceQuestionItem.textMap) && Objects.equal(this.linkActionMap, xrayMultipleChoiceQuestionItem.linkActionMap) && Objects.equal(this.questionState, xrayMultipleChoiceQuestionItem.questionState);
    }

    @Override // com.amazon.avod.xray.ItemWithBlueprint, com.amazon.atv.discovery.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.relatedItems, this.accessibilityMap, Long.valueOf(this.lastUpdatedTime), this.textMap, this.linkActionMap, this.questionState);
    }

    @Override // com.amazon.avod.xray.ItemWithBlueprint, com.amazon.atv.discovery.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("relatedItems", this.relatedItems).add("accessibilityMap", this.accessibilityMap).add("lastUpdatedTime", this.lastUpdatedTime).add("textMap", this.textMap).add("linkActionMap", this.linkActionMap).add("questionState", this.questionState).toString();
    }

    @NotThreadSafe
    public static class Builder extends ItemWithBlueprint.Builder {
        public ImmutableMap<String, String> accessibilityMap;
        public long lastUpdatedTime;
        public ImmutableMap<String, Action> linkActionMap;
        public XrayQuestionItemState questionState;
        public ImmutableList<XrayCheckableAnswerItem> relatedItems;
        public ImmutableMap<String, String> textMap;

        @Override // com.amazon.avod.xray.ItemWithBlueprint.Builder
        public XrayMultipleChoiceQuestionItem build() {
            return new XrayMultipleChoiceQuestionItem(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XrayMultipleChoiceQuestionItem> {
        private final MapParser<String, Action> mActionMapParser;
        private final Analytics.Parser mAnalyticsParser;
        private final Blueprint.Parser mBlueprintParser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;
        private final MapParser<String, String> mTextMapParser;
        private final ListParser<XrayCheckableAnswerItem> mXrayCheckableAnswerItemListParser;
        private final EnumParser<XrayQuestionItemState> mXrayQuestionItemStateParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mXrayQuestionItemStateParser = EnumParser.newParser(XrayQuestionItemState.class);
            this.mXrayCheckableAnswerItemListParser = ListParser.newParser(new XrayCheckableAnswerItem.Parser(objectMapper));
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
        public XrayMultipleChoiceQuestionItem parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XrayMultipleChoiceQuestionItem:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XrayMultipleChoiceQuestionItem parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XrayMultipleChoiceQuestionItem:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:48:0x00bc  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.avod.xray.XrayMultipleChoiceQuestionItem parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.databind.JsonNode r18) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 606
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.xray.XrayMultipleChoiceQuestionItem.Parser.parseInternal(com.fasterxml.jackson.databind.JsonNode):com.amazon.avod.xray.XrayMultipleChoiceQuestionItem");
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XrayMultipleChoiceQuestionItem parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case -1665842898:
                                    if (currentName.equals("accessibilityMap")) {
                                        b = 4;
                                    }
                                    break;
                                case -1417855761:
                                    if (currentName.equals("textMap")) {
                                        b = 1;
                                    }
                                    break;
                                case -1041426357:
                                    if (currentName.equals("questionState")) {
                                        b = 3;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 10;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 7;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 5;
                                    }
                                    break;
                                case 134037077:
                                    if (currentName.equals("relatedItems")) {
                                        b = 0;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 8;
                                    }
                                    break;
                                case 1600013420:
                                    if (currentName.equals("linkActionMap")) {
                                        b = 9;
                                    }
                                    break;
                                case 1617464754:
                                    if (currentName.equals("lastUpdatedTime")) {
                                        b = 6;
                                    }
                                    break;
                                case 1965271699:
                                    if (currentName.equals("blueprint")) {
                                        b = 2;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                            }
                            ImmutableList<XrayCheckableAnswerItem> immutableList = null;
                            String str = null;
                            Analytics analytics = null;
                            ImmutableMap<String, String> immutableMap = null;
                            ImmutableMap<String, Action> immutableMap2 = null;
                            String str2 = null;
                            ItemType itemType = null;
                            ImmutableMap<String, String> immutableMap3 = null;
                            XrayQuestionItemState xrayQuestionItemState = null;
                            Blueprint blueprint = null;
                            ImmutableMap<String, String> immutableMap4 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mXrayCheckableAnswerItemListParser.parse(jsonParser);
                                    }
                                    builder.relatedItems = immutableList;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap4 = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.textMap = immutableMap4;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprint = this.mBlueprintParser.parse(jsonParser);
                                    }
                                    builder.blueprint = blueprint;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        xrayQuestionItemState = (XrayQuestionItemState) this.mXrayQuestionItemStateParser.parse(jsonParser);
                                    }
                                    builder.questionState = xrayQuestionItemState;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap3 = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.accessibilityMap = immutableMap3;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    break;
                                case 6:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field lastUpdatedTime can't be null");
                                    }
                                    builder.lastUpdatedTime = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str2;
                                    break;
                                case 8:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mActionMapParser.parse(jsonParser);
                                    }
                                    builder.linkActionMap = immutableMap2;
                                    break;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap;
                                    break;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 12:
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
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XrayMultipleChoiceQuestionItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.relatedItems, this, "relatedItems");
                    JsonParsingUtils.checkNotNull(builder.textMap, this, "textMap");
                    JsonParsingUtils.checkNotNull(builder.blueprint, this, "blueprint");
                    JsonParsingUtils.checkNotNull(builder.questionState, this, "questionState");
                    JsonParsingUtils.checkNotNull(builder.accessibilityMap, this, "accessibilityMap");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.lastUpdatedTime), this, "lastUpdatedTime");
                    JsonParsingUtils.checkNotNull(builder.id, this, "id");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    return builder.build();
                }
            }
        }
    }
}
