package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.xrayv2.BlockedSegment;
import com.amazon.atv.xrayv2.Blueprint;
import com.amazon.atv.xrayv2.ImageV2;
import com.amazon.atv.xrayv2.LimitationMetadata;
import com.amazon.atv.xrayv2.NavigationalAction;
import com.amazon.atv.xrayv2.Widget;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class ClipEditorWidget extends Widget {
    public final Optional<ImmutableMap<String, String>> accessibilityMap;
    public final Optional<ImmutableMap<String, String>> blockedMessageMap;
    public final ImmutableList<BlockedSegment> blockedSegments;
    public final int clipShiftingIntervalMillis;
    public final String defaultBlockedMessage;
    public final Optional<NavigationalAction> helpAction;
    public final ImmutableList<ImageV2> imageList;
    public final Optional<String> limitationMessage;
    public final Optional<LimitationMetadata> limitationMetadata;
    public final int maxClipDurationMillis;
    public final Optional<NavigationalAction> shareAction;
    public final int thumbnailIntervalMillis;

    private ClipEditorWidget(Builder builder) {
        super(builder);
        this.maxClipDurationMillis = builder.maxClipDurationMillis;
        this.imageList = (ImmutableList) Preconditions.checkNotNull(builder.imageList, "Unexpected null field: imageList");
        this.limitationMetadata = Optional.fromNullable(builder.limitationMetadata);
        this.thumbnailIntervalMillis = builder.thumbnailIntervalMillis;
        this.helpAction = Optional.fromNullable(builder.helpAction);
        this.shareAction = Optional.fromNullable(builder.shareAction);
        this.blockedMessageMap = Optional.fromNullable(builder.blockedMessageMap);
        this.limitationMessage = Optional.fromNullable(builder.limitationMessage);
        this.accessibilityMap = Optional.fromNullable(builder.accessibilityMap);
        this.defaultBlockedMessage = (String) Preconditions.checkNotNull(builder.defaultBlockedMessage, "Unexpected null field: defaultBlockedMessage");
        this.clipShiftingIntervalMillis = builder.clipShiftingIntervalMillis;
        this.blockedSegments = (ImmutableList) Preconditions.checkNotNull(builder.blockedSegments, "Unexpected null field: blockedSegments");
    }

    @Override // com.amazon.atv.xrayv2.Widget
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClipEditorWidget)) {
            return false;
        }
        ClipEditorWidget clipEditorWidget = (ClipEditorWidget) obj;
        return super.equals(obj) && Objects.equal(Integer.valueOf(this.maxClipDurationMillis), Integer.valueOf(clipEditorWidget.maxClipDurationMillis)) && Objects.equal(this.imageList, clipEditorWidget.imageList) && Objects.equal(this.limitationMetadata, clipEditorWidget.limitationMetadata) && Objects.equal(Integer.valueOf(this.thumbnailIntervalMillis), Integer.valueOf(clipEditorWidget.thumbnailIntervalMillis)) && Objects.equal(this.helpAction, clipEditorWidget.helpAction) && Objects.equal(this.shareAction, clipEditorWidget.shareAction) && Objects.equal(this.blockedMessageMap, clipEditorWidget.blockedMessageMap) && Objects.equal(this.limitationMessage, clipEditorWidget.limitationMessage) && Objects.equal(this.accessibilityMap, clipEditorWidget.accessibilityMap) && Objects.equal(this.defaultBlockedMessage, clipEditorWidget.defaultBlockedMessage) && Objects.equal(Integer.valueOf(this.clipShiftingIntervalMillis), Integer.valueOf(clipEditorWidget.clipShiftingIntervalMillis)) && Objects.equal(this.blockedSegments, clipEditorWidget.blockedSegments);
    }

    @Override // com.amazon.atv.xrayv2.Widget
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), Integer.valueOf(this.maxClipDurationMillis), this.imageList, this.limitationMetadata, Integer.valueOf(this.thumbnailIntervalMillis), this.helpAction, this.shareAction, this.blockedMessageMap, this.limitationMessage, this.accessibilityMap, this.defaultBlockedMessage, Integer.valueOf(this.clipShiftingIntervalMillis), this.blockedSegments);
    }

    @Override // com.amazon.atv.xrayv2.Widget
    public String toString() {
        return MoreObjects.toStringHelper(this).add("maxClipDurationMillis", this.maxClipDurationMillis).add("imageList", this.imageList).add("limitationMetadata", this.limitationMetadata).add("thumbnailIntervalMillis", this.thumbnailIntervalMillis).add("helpAction", this.helpAction).add("shareAction", this.shareAction).add("blockedMessageMap", this.blockedMessageMap).add("limitationMessage", this.limitationMessage).add("accessibilityMap", this.accessibilityMap).add("defaultBlockedMessage", this.defaultBlockedMessage).add("clipShiftingIntervalMillis", this.clipShiftingIntervalMillis).add("blockedSegments", this.blockedSegments).toString();
    }

    @NotThreadSafe
    public static class Builder extends Widget.Builder {
        public ImmutableMap<String, String> accessibilityMap;
        public ImmutableMap<String, String> blockedMessageMap;
        public ImmutableList<BlockedSegment> blockedSegments;
        public int clipShiftingIntervalMillis;
        public String defaultBlockedMessage;
        public NavigationalAction helpAction;
        public ImmutableList<ImageV2> imageList;
        public String limitationMessage;
        public LimitationMetadata limitationMetadata;
        public int maxClipDurationMillis;
        public NavigationalAction shareAction;
        public int thumbnailIntervalMillis;

        public ClipEditorWidget build() {
            return new ClipEditorWidget(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ClipEditorWidget> {
        private final Analytics.Parser mAnalyticsParser;
        private final ListParser<BlockedSegment> mBlockedSegmentsListParser;
        private final Blueprint.Parser mBlueprintParser;
        private final ListParser<ImageV2> mClipEditorWidgetListParser;
        private final LimitationMetadata.Parser mLimitationMetadataParser;
        private final NavigationalAction.Parser mNavigationalActionParser;
        private final SimpleParsers.StringParser mStringParser;
        private final MapParser<String, String> mTextMapParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mClipEditorWidgetListParser = ListParser.newParser(new ImageV2.Parser(objectMapper));
            this.mBlockedSegmentsListParser = ListParser.newParser(new BlockedSegment.Parser(objectMapper));
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mTextMapParser = MapParser.newParser(stringParser, stringParser);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mBlueprintParser = new Blueprint.Parser(objectMapper);
            this.mStringParser = stringParser;
            this.mLimitationMetadataParser = new LimitationMetadata.Parser(objectMapper);
            this.mNavigationalActionParser = new NavigationalAction.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ClipEditorWidget parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ClipEditorWidget:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ClipEditorWidget parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ClipEditorWidget:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ClipEditorWidget parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ClipEditorWidget");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2133718802:
                            if (next.equals("maxClipDurationMillis")) {
                                b = 7;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 9;
                            }
                            break;
                        case -1665842898:
                            if (next.equals("accessibilityMap")) {
                                b = 4;
                            }
                            break;
                        case -1562273139:
                            if (next.equals("limitationMessage")) {
                                b = 2;
                            }
                            break;
                        case -1365782757:
                            if (next.equals("clipShiftingIntervalMillis")) {
                                b = 17;
                            }
                            break;
                        case -1173737111:
                            if (next.equals("limitationMetadata")) {
                                b = 0;
                            }
                            break;
                        case -878401383:
                            if (next.equals("imageList")) {
                                b = 8;
                            }
                            break;
                        case -567811668:
                            if (next.equals("blockedSegments")) {
                                b = 5;
                            }
                            break;
                        case -466610443:
                            if (next.equals("shareAction")) {
                                b = 1;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 6;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 15;
                            }
                            break;
                        case 152244508:
                            if (next.equals("defaultBlockedMessage")) {
                                b = 16;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case 690160855:
                            if (next.equals("helpAction")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 1285493623:
                            if (next.equals("thumbnailIntervalMillis")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 1320395489:
                            if (next.equals("blockedMessageMap")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case 1965271699:
                            if (next.equals("blueprint")) {
                                b = 10;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 3;
                            }
                            break;
                    }
                    LimitationMetadata limitationMetadata = null;
                    String str = null;
                    String str2 = null;
                    ImmutableMap<String, String> immutableMap = null;
                    NavigationalAction navigationalAction = null;
                    Blueprint blueprint = null;
                    Analytics analytics = null;
                    ImmutableList<ImageV2> immutableList = null;
                    String str3 = null;
                    ImmutableList<BlockedSegment> immutableList2 = null;
                    ImmutableMap<String, String> immutableMap2 = null;
                    String str4 = null;
                    String str5 = null;
                    NavigationalAction navigationalAction2 = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                limitationMetadata = this.mLimitationMetadataParser.parse(jsonNode2);
                            }
                            builder.limitationMetadata = limitationMetadata;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                navigationalAction2 = this.mNavigationalActionParser.parse(jsonNode2);
                            }
                            builder.shareAction = navigationalAction2;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                str5 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.limitationMessage = str5;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                str4 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.debugAttributes = str4;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                immutableMap2 = this.mTextMapParser.parse(jsonNode2);
                            }
                            builder.accessibilityMap = immutableMap2;
                            break;
                        case 5:
                            if (!jsonNode2.isNull()) {
                                immutableList2 = this.mBlockedSegmentsListParser.parse(jsonNode2);
                            }
                            builder.blockedSegments = immutableList2;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                str3 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.id = str3;
                            break;
                        case 7:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field maxClipDurationMillis can't be null");
                            }
                            builder.maxClipDurationMillis = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 8:
                            if (!jsonNode2.isNull()) {
                                immutableList = this.mClipEditorWidgetListParser.parse(jsonNode2);
                            }
                            builder.imageList = immutableList;
                            break;
                        case 9:
                            if (!jsonNode2.isNull()) {
                                analytics = this.mAnalyticsParser.parse(jsonNode2);
                            }
                            builder.analytics = analytics;
                            break;
                        case 10:
                            if (!jsonNode2.isNull()) {
                                blueprint = this.mBlueprintParser.parse(jsonNode2);
                            }
                            builder.blueprint = blueprint;
                            break;
                        case 11:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field thumbnailIntervalMillis can't be null");
                            }
                            builder.thumbnailIntervalMillis = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 12:
                            if (!jsonNode2.isNull()) {
                                navigationalAction = this.mNavigationalActionParser.parse(jsonNode2);
                            }
                            builder.helpAction = navigationalAction;
                            break;
                        case 13:
                            if (!jsonNode2.isNull()) {
                                immutableMap = this.mTextMapParser.parse(jsonNode2);
                            }
                            builder.blockedMessageMap = immutableMap;
                            break;
                        case 14:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 15:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.type = str2;
                            break;
                        case 16:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.defaultBlockedMessage = str;
                            break;
                        case 17:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field clipShiftingIntervalMillis can't be null");
                            }
                            builder.clipShiftingIntervalMillis = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ClipEditorWidget so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.blockedSegments, this, "blockedSegments");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.maxClipDurationMillis), this, "maxClipDurationMillis");
            JsonParsingUtils.checkNotNull(builder.imageList, this, "imageList");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.thumbnailIntervalMillis), this, "thumbnailIntervalMillis");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(builder.defaultBlockedMessage, this, "defaultBlockedMessage");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.clipShiftingIntervalMillis), this, "clipShiftingIntervalMillis");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ClipEditorWidget parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2133718802:
                                    if (currentName.equals("maxClipDurationMillis")) {
                                        b = 7;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 9;
                                    }
                                    break;
                                case -1665842898:
                                    if (currentName.equals("accessibilityMap")) {
                                        b = 4;
                                    }
                                    break;
                                case -1562273139:
                                    if (currentName.equals("limitationMessage")) {
                                        b = 2;
                                    }
                                    break;
                                case -1365782757:
                                    if (currentName.equals("clipShiftingIntervalMillis")) {
                                        b = 17;
                                    }
                                    break;
                                case -1173737111:
                                    if (currentName.equals("limitationMetadata")) {
                                        b = 0;
                                    }
                                    break;
                                case -878401383:
                                    if (currentName.equals("imageList")) {
                                        b = 8;
                                    }
                                    break;
                                case -567811668:
                                    if (currentName.equals("blockedSegments")) {
                                        b = 5;
                                    }
                                    break;
                                case -466610443:
                                    if (currentName.equals("shareAction")) {
                                        b = 1;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 6;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 15;
                                    }
                                    break;
                                case 152244508:
                                    if (currentName.equals("defaultBlockedMessage")) {
                                        b = 16;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case 690160855:
                                    if (currentName.equals("helpAction")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 1285493623:
                                    if (currentName.equals("thumbnailIntervalMillis")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 1320395489:
                                    if (currentName.equals("blockedMessageMap")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case 1965271699:
                                    if (currentName.equals("blueprint")) {
                                        b = 10;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            LimitationMetadata limitationMetadata = null;
                            String str = null;
                            String str2 = null;
                            ImmutableMap<String, String> immutableMap = null;
                            NavigationalAction navigationalAction = null;
                            Blueprint blueprint = null;
                            Analytics analytics = null;
                            ImmutableList<ImageV2> immutableList = null;
                            String str3 = null;
                            ImmutableList<BlockedSegment> immutableList2 = null;
                            ImmutableMap<String, String> immutableMap2 = null;
                            String str4 = null;
                            String str5 = null;
                            NavigationalAction navigationalAction2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        limitationMetadata = this.mLimitationMetadataParser.parse(jsonParser);
                                    }
                                    builder.limitationMetadata = limitationMetadata;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        navigationalAction2 = this.mNavigationalActionParser.parse(jsonParser);
                                    }
                                    builder.shareAction = navigationalAction2;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.limitationMessage = str5;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str4;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.accessibilityMap = immutableMap2;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mBlockedSegmentsListParser.parse(jsonParser);
                                    }
                                    builder.blockedSegments = immutableList2;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str3;
                                    break;
                                case 7:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field maxClipDurationMillis can't be null");
                                    }
                                    builder.maxClipDurationMillis = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mClipEditorWidgetListParser.parse(jsonParser);
                                    }
                                    builder.imageList = immutableList;
                                    break;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprint = this.mBlueprintParser.parse(jsonParser);
                                    }
                                    builder.blueprint = blueprint;
                                    break;
                                case 11:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field thumbnailIntervalMillis can't be null");
                                    }
                                    builder.thumbnailIntervalMillis = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        navigationalAction = this.mNavigationalActionParser.parse(jsonParser);
                                    }
                                    builder.helpAction = navigationalAction;
                                    break;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mTextMapParser.parse(jsonParser);
                                    }
                                    builder.blockedMessageMap = immutableMap;
                                    break;
                                case 14:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.type = str2;
                                    break;
                                case 16:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.defaultBlockedMessage = str;
                                    break;
                                case 17:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field clipShiftingIntervalMillis can't be null");
                                    }
                                    builder.clipShiftingIntervalMillis = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ClipEditorWidget so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.blockedSegments, this, "blockedSegments");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.maxClipDurationMillis), this, "maxClipDurationMillis");
                    JsonParsingUtils.checkNotNull(builder.imageList, this, "imageList");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.thumbnailIntervalMillis), this, "thumbnailIntervalMillis");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(builder.defaultBlockedMessage, this, "defaultBlockedMessage");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.clipShiftingIntervalMillis), this, "clipShiftingIntervalMillis");
                    return builder.build();
                }
            }
        }
    }
}
