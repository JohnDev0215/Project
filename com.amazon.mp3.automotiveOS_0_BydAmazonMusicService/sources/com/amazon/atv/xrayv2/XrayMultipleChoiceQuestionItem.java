package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.xrayv2.Action;
import com.amazon.atv.xrayv2.Blueprint;
import com.amazon.atv.xrayv2.ItemWithBlueprint;
import com.amazon.atv.xrayv2.XrayCheckableAnswerItem;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
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
        this.lastUpdatedTime = builder.lastUpdatedTime;
        this.relatedItems = (ImmutableList) Preconditions.checkNotNull(builder.relatedItems, "Unexpected null field: relatedItems");
        this.accessibilityMap = (ImmutableMap) Preconditions.checkNotNull(builder.accessibilityMap, "Unexpected null field: accessibilityMap");
        this.questionState = (XrayQuestionItemState) Preconditions.checkNotNull(builder.questionState, "Unexpected null field: questionState");
        this.linkActionMap = Optional.fromNullable(builder.linkActionMap);
        this.textMap = (ImmutableMap) Preconditions.checkNotNull(builder.textMap, "Unexpected null field: textMap");
    }

    @Override // com.amazon.atv.xrayv2.ItemWithBlueprint, com.amazon.atv.xrayv2.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XrayMultipleChoiceQuestionItem)) {
            return false;
        }
        XrayMultipleChoiceQuestionItem xrayMultipleChoiceQuestionItem = (XrayMultipleChoiceQuestionItem) obj;
        return super.equals(obj) && Objects.equal(Long.valueOf(this.lastUpdatedTime), Long.valueOf(xrayMultipleChoiceQuestionItem.lastUpdatedTime)) && Objects.equal(this.relatedItems, xrayMultipleChoiceQuestionItem.relatedItems) && Objects.equal(this.accessibilityMap, xrayMultipleChoiceQuestionItem.accessibilityMap) && Objects.equal(this.questionState, xrayMultipleChoiceQuestionItem.questionState) && Objects.equal(this.linkActionMap, xrayMultipleChoiceQuestionItem.linkActionMap) && Objects.equal(this.textMap, xrayMultipleChoiceQuestionItem.textMap);
    }

    @Override // com.amazon.atv.xrayv2.ItemWithBlueprint, com.amazon.atv.xrayv2.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), Long.valueOf(this.lastUpdatedTime), this.relatedItems, this.accessibilityMap, this.questionState, this.linkActionMap, this.textMap);
    }

    @Override // com.amazon.atv.xrayv2.ItemWithBlueprint, com.amazon.atv.xrayv2.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("lastUpdatedTime", this.lastUpdatedTime).add("relatedItems", this.relatedItems).add("accessibilityMap", this.accessibilityMap).add("questionState", this.questionState).add("linkActionMap", this.linkActionMap).add("textMap", this.textMap).toString();
    }

    @NotThreadSafe
    public static class Builder extends ItemWithBlueprint.Builder {
        public ImmutableMap<String, String> accessibilityMap;
        public long lastUpdatedTime;
        public ImmutableMap<String, Action> linkActionMap;
        public XrayQuestionItemState questionState;
        public ImmutableList<XrayCheckableAnswerItem> relatedItems;
        public ImmutableMap<String, String> textMap;

        @Override // com.amazon.atv.xrayv2.ItemWithBlueprint.Builder
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
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            this.mXrayCheckableAnswerItemListParser = ListParser.newParser(new XrayCheckableAnswerItem.Parser(objectMapper));
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mTextMapParser = MapParser.newParser(stringParser, stringParser);
            this.mActionMapParser = MapParser.newParser(stringParser, new Action.Parser(objectMapper));
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mXrayQuestionItemStateParser = EnumParser.newParser(XrayQuestionItemState.class);
            this.mBlueprintParser = new Blueprint.Parser(objectMapper);
            this.mStringParser = stringParser;
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
        private com.amazon.atv.xrayv2.XrayMultipleChoiceQuestionItem parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.databind.JsonNode r18) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 606
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.xrayv2.XrayMultipleChoiceQuestionItem.Parser.parseInternal(com.fasterxml.jackson.databind.JsonNode):com.amazon.atv.xrayv2.XrayMultipleChoiceQuestionItem");
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
                                        b = 2;
                                    }
                                    break;
                                case -1665842898:
                                    if (currentName.equals("accessibilityMap")) {
                                        b = 1;
                                    }
                                    break;
                                case -1417855761:
                                    if (currentName.equals("textMap")) {
                                        b = 8;
                                    }
                                    break;
                                case -1041426357:
                                    if (currentName.equals("questionState")) {
                                        b = 3;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 6;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 7;
                                    }
                                    break;
                                case 134037077:
                                    if (currentName.equals("relatedItems")) {
                                        b = 10;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 9;
                                    }
                                    break;
                                case 1600013420:
                                    if (currentName.equals("linkActionMap")) {
                                        b = 5;
                                    }
                                    break;
                                case 1617464754:
                                    if (currentName.equals("lastUpdatedTime")) {
                                        b = 0;
                                    }
                                    break;
                                case 1965271699:
                                    if (currentName.equals("blueprint")) {
                                        b = 4;
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
                            String str2 = null;
                            ImmutableList<XrayCheckableAnswerItem> immutableList = null;
                            ImmutableMap<String, String> immutableMap2 = null;
                            ItemType itemType = null;
                            ImmutableMap<String, String> immutableMap3 = null;
                            ImmutableMap<String, Action> immutableMap4 = null;
                            Blueprint blueprint = null;
                            XrayQuestionItemState xrayQuestionItemState = null;
                            Analytics analytics = null;
                            switch (b) {
                                case 0:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field lastUpdatedTime can't be null");
                                    }
                                    builder.lastUpdatedTime = SimpleParsers.parsePrimitiveLong(jsonParser);
                                    break;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.accessibilityMap = immutableMap;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        xrayQuestionItemState = (XrayQuestionItemState) this.mXrayQuestionItemStateParser.parse(jsonParser);
                                    }
                                    builder.questionState = xrayQuestionItemState;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprint = this.mBlueprintParser.parse(jsonParser);
                                    }
                                    builder.blueprint = blueprint;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap4 = this.mActionMapParser.parse(jsonParser);
                                    }
                                    builder.linkActionMap = immutableMap4;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap3 = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap3;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    break;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.textMap = immutableMap2;
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
                                        immutableList = this.mXrayCheckableAnswerItemListParser.parse(jsonParser);
                                    }
                                    builder.relatedItems = immutableList;
                                    break;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str2;
                                    break;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str;
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
                    JsonParsingUtils.checkNotNull(Long.valueOf(builder.lastUpdatedTime), this, "lastUpdatedTime");
                    JsonParsingUtils.checkNotNull(builder.accessibilityMap, this, "accessibilityMap");
                    JsonParsingUtils.checkNotNull(builder.questionState, this, "questionState");
                    JsonParsingUtils.checkNotNull(builder.blueprint, this, "blueprint");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(builder.textMap, this, "textMap");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.relatedItems, this, "relatedItems");
                    JsonParsingUtils.checkNotNull(builder.id, this, "id");
                    return builder.build();
                }
            }
        }
    }
}
