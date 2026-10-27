package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Action;
import com.amazon.atv.discovery.ActionConsequence;
import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.Collection;
import com.amazon.atv.discovery.DiagnosticContainer;
import com.amazon.atv.discovery.Items;
import com.amazon.atv.discovery.NavigationalAction;
import com.amazon.atv.discovery.PresentationData;
import com.amazon.atv.discovery.TimeToLive;
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
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class AdsCarousel extends Collection {
    private AdsCarousel(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.atv.discovery.Collection
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdsCarousel)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.atv.discovery.Collection
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    @Override // com.amazon.atv.discovery.Collection
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @NotThreadSafe
    public static class Builder extends Collection.Builder {
        public AdsCarousel build() {
            return new AdsCarousel(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AdsCarousel> {
        private final ListParser<ActionConsequence> mActionConsequencesParser;
        private final ListParser<Action> mActionListParser;
        private final Analytics.Parser mAnalyticsParser;
        private final EnumParser<CollectionType> mCollectionTypeParser;
        private final DiagnosticContainer.Parser mDiagnosticContainerParser;
        private final MapParser<String, ImmutableList<Action>> mItemTypeToActionMapParser;
        private final Items.Parser mItemsParser;
        private final NavigationalAction.Parser mNavigationalActionParser;
        private final PresentationData.Parser mPresentationDataParser;
        private final SimpleParsers.StringParser mStringParser;
        private final TimeToLive.Parser mTimeToLiveParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTimeToLiveParser = new TimeToLive.Parser(objectMapper);
            this.mCollectionTypeParser = EnumParser.newParser(CollectionType.class);
            this.mNavigationalActionParser = new NavigationalAction.Parser(objectMapper);
            this.mDiagnosticContainerParser = new DiagnosticContainer.Parser(objectMapper);
            this.mItemsParser = new Items.Parser(objectMapper);
            this.mActionListParser = ListParser.newParser(new Action.Parser(objectMapper));
            this.mActionConsequencesParser = ListParser.newParser(new ActionConsequence.Parser(objectMapper));
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mItemTypeToActionMapParser = MapParser.newParser(stringParser, ListParser.newParser(new Action.Parser(objectMapper)));
            this.mPresentationDataParser = new PresentationData.Parser(objectMapper);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AdsCarousel parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AdsCarousel:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AdsCarousel parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AdsCarousel:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AdsCarousel parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ImmutableList<Action> immutableList;
            Analytics analytics;
            ImmutableMap<String, ImmutableList<Action>> immutableMap;
            ImmutableList<ActionConsequence> immutableList2;
            String str;
            NavigationalAction navigationalAction;
            PresentationData presentationData;
            DiagnosticContainer diagnosticContainer;
            String str2;
            TimeToLive timeToLive;
            NavigationalAction navigationalAction2;
            String str3;
            Items items;
            CollectionType collectionType;
            String str4;
            JsonParsingUtils.throwIfNotObject(jsonNode, "AdsCarousel");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2036626155:
                            if (next.equals("zoomOutLink")) {
                                b = 10;
                            }
                            break;
                        case -1867586707:
                            if (next.equals("subtext")) {
                                b = 15;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 1;
                            }
                            break;
                        case -937837454:
                            if (next.equals("zoomInLink")) {
                                b = 5;
                            }
                            break;
                        case -708184782:
                            if (next.equals("collectionActionList")) {
                                b = 0;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case 100526016:
                            if (next.equals(ContextMappingConstants.ITEMS)) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 816164660:
                            if (next.equals("timeToLive")) {
                                b = 9;
                            }
                            break;
                        case 897526750:
                            if (next.equals("itemTypeToActionMap")) {
                                b = 2;
                            }
                            break;
                        case 1583725722:
                            if (next.equals("diagnosticContainer")) {
                                b = 7;
                            }
                            break;
                        case 1602373092:
                            if (next.equals("presentationData")) {
                                b = 6;
                            }
                            break;
                        case 1636075609:
                            if (next.equals("collectionId")) {
                                b = 8;
                            }
                            break;
                        case 1901773542:
                            if (next.equals("actionConsequences")) {
                                b = 3;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 4;
                            }
                            break;
                    }
                    immutableList = null;
                    str4 = null;
                    collectionType = null;
                    items = null;
                    str3 = null;
                    navigationalAction2 = null;
                    timeToLive = null;
                    str2 = null;
                    diagnosticContainer = null;
                    presentationData = null;
                    navigationalAction = null;
                    str = null;
                    immutableList2 = null;
                    immutableMap = null;
                    analytics = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AdsCarousel so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mActionListParser.parse(jsonNode2);
                        }
                        builder.collectionActionList = immutableList;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mItemTypeToActionMapParser.parse(jsonNode2);
                        }
                        builder.itemTypeToActionMap = immutableMap;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mActionConsequencesParser.parse(jsonNode2);
                        }
                        builder.actionConsequences = immutableList2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = str;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            navigationalAction = this.mNavigationalActionParser.parse(jsonNode2);
                        }
                        builder.zoomInLink = navigationalAction;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            presentationData = this.mPresentationDataParser.parse(jsonNode2);
                        }
                        builder.presentationData = presentationData;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            diagnosticContainer = this.mDiagnosticContainerParser.parse(jsonNode2);
                        }
                        builder.diagnosticContainer = diagnosticContainer;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.collectionId = str2;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            timeToLive = this.mTimeToLiveParser.parse(jsonNode2);
                        }
                        builder.timeToLive = timeToLive;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            navigationalAction2 = this.mNavigationalActionParser.parse(jsonNode2);
                        }
                        builder.zoomOutLink = navigationalAction2;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.text = str3;
                        continue;
                    case 12:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        continue;
                        break;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            items = this.mItemsParser.parse(jsonNode2);
                        }
                        builder.items = items;
                        continue;
                    case 14:
                        if (!jsonNode2.isNull()) {
                            collectionType = (CollectionType) this.mCollectionTypeParser.parse(jsonNode2);
                        }
                        builder.type = collectionType;
                        continue;
                    case 15:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.subtext = str4;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AdsCarousel so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AdsCarousel parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2036626155:
                                    if (currentName.equals("zoomOutLink")) {
                                        b = 10;
                                    }
                                    break;
                                case -1867586707:
                                    if (currentName.equals("subtext")) {
                                        b = 15;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 1;
                                    }
                                    break;
                                case -937837454:
                                    if (currentName.equals("zoomInLink")) {
                                        b = 5;
                                    }
                                    break;
                                case -708184782:
                                    if (currentName.equals("collectionActionList")) {
                                        b = 0;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case 100526016:
                                    if (currentName.equals(ContextMappingConstants.ITEMS)) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 816164660:
                                    if (currentName.equals("timeToLive")) {
                                        b = 9;
                                    }
                                    break;
                                case 897526750:
                                    if (currentName.equals("itemTypeToActionMap")) {
                                        b = 2;
                                    }
                                    break;
                                case 1583725722:
                                    if (currentName.equals("diagnosticContainer")) {
                                        b = 7;
                                    }
                                    break;
                                case 1602373092:
                                    if (currentName.equals("presentationData")) {
                                        b = 6;
                                    }
                                    break;
                                case 1636075609:
                                    if (currentName.equals("collectionId")) {
                                        b = 8;
                                    }
                                    break;
                                case 1901773542:
                                    if (currentName.equals("actionConsequences")) {
                                        b = 3;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            ImmutableList<Action> immutableList = null;
                            String str = null;
                            CollectionType collectionType = null;
                            Items items = null;
                            String str2 = null;
                            NavigationalAction navigationalAction = null;
                            TimeToLive timeToLive = null;
                            String str3 = null;
                            DiagnosticContainer diagnosticContainer = null;
                            PresentationData presentationData = null;
                            NavigationalAction navigationalAction2 = null;
                            String str4 = null;
                            ImmutableList<ActionConsequence> immutableList2 = null;
                            ImmutableMap<String, ImmutableList<Action>> immutableMap = null;
                            Analytics analytics = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mActionListParser.parse(jsonParser);
                                    }
                                    builder.collectionActionList = immutableList;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mItemTypeToActionMapParser.parse(jsonParser);
                                    }
                                    builder.itemTypeToActionMap = immutableMap;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mActionConsequencesParser.parse(jsonParser);
                                    }
                                    builder.actionConsequences = immutableList2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str4;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        navigationalAction2 = this.mNavigationalActionParser.parse(jsonParser);
                                    }
                                    builder.zoomInLink = navigationalAction2;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        presentationData = this.mPresentationDataParser.parse(jsonParser);
                                    }
                                    builder.presentationData = presentationData;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        diagnosticContainer = this.mDiagnosticContainerParser.parse(jsonParser);
                                    }
                                    builder.diagnosticContainer = diagnosticContainer;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.collectionId = str3;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        timeToLive = this.mTimeToLiveParser.parse(jsonParser);
                                    }
                                    builder.timeToLive = timeToLive;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        navigationalAction = this.mNavigationalActionParser.parse(jsonParser);
                                    }
                                    builder.zoomOutLink = navigationalAction;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str2;
                                    continue;
                                case 12:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    continue;
                                    break;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        items = this.mItemsParser.parse(jsonParser);
                                    }
                                    builder.items = items;
                                    continue;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        collectionType = (CollectionType) this.mCollectionTypeParser.parse(jsonParser);
                                    }
                                    builder.type = collectionType;
                                    continue;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.subtext = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AdsCarousel so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AdsCarousel so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    return builder.build();
                }
            }
        }
    }
}
