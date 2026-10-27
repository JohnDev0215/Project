package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.xrayv2.Blueprint;
import com.amazon.atv.xrayv2.Item;
import com.amazon.atv.xrayv2.Widget;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class XRayFloatableWidget extends Widget {
    public final int embeddedIndex;
    public final String embeddedParentId;
    public final Item item;

    private XRayFloatableWidget(Builder builder) {
        super(builder);
        this.item = (Item) Preconditions.checkNotNull(builder.item, "Unexpected null field: item");
        this.embeddedIndex = builder.embeddedIndex;
        this.embeddedParentId = (String) Preconditions.checkNotNull(builder.embeddedParentId, "Unexpected null field: embeddedParentId");
    }

    @Override // com.amazon.atv.xrayv2.Widget
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XRayFloatableWidget)) {
            return false;
        }
        XRayFloatableWidget xRayFloatableWidget = (XRayFloatableWidget) obj;
        return super.equals(obj) && Objects.equal(this.item, xRayFloatableWidget.item) && Objects.equal(Integer.valueOf(this.embeddedIndex), Integer.valueOf(xRayFloatableWidget.embeddedIndex)) && Objects.equal(this.embeddedParentId, xRayFloatableWidget.embeddedParentId);
    }

    @Override // com.amazon.atv.xrayv2.Widget
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.item, Integer.valueOf(this.embeddedIndex), this.embeddedParentId);
    }

    @Override // com.amazon.atv.xrayv2.Widget
    public String toString() {
        return MoreObjects.toStringHelper(this).add("item", this.item).add("embeddedIndex", this.embeddedIndex).add("embeddedParentId", this.embeddedParentId).toString();
    }

    @NotThreadSafe
    public static class Builder extends Widget.Builder {
        public int embeddedIndex;
        public String embeddedParentId;
        public Item item;

        public XRayFloatableWidget build() {
            return new XRayFloatableWidget(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XRayFloatableWidget> {
        private final Analytics.Parser mAnalyticsParser;
        private final Blueprint.Parser mBlueprintParser;
        private final Item.Parser mItemParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mItemParser = new Item.Parser(objectMapper);
            this.mBlueprintParser = new Blueprint.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XRayFloatableWidget parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayFloatableWidget:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XRayFloatableWidget parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayFloatableWidget:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRayFloatableWidget parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XRayFloatableWidget");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1847139313:
                            if (next.equals("embeddedParentId")) {
                                b = 7;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 0;
                            }
                            break;
                        case -851565464:
                            if (next.equals("embeddedIndex")) {
                                b = 3;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 8;
                            }
                            break;
                        case 3242771:
                            if (next.equals("item")) {
                                b = 2;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 6;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 5;
                            }
                            break;
                        case 1965271699:
                            if (next.equals("blueprint")) {
                                b = 1;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 4;
                            }
                            break;
                    }
                    Analytics analytics = null;
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    String str4 = null;
                    Item item = null;
                    Blueprint blueprint = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                analytics = this.mAnalyticsParser.parse(jsonNode2);
                            }
                            builder.analytics = analytics;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                blueprint = this.mBlueprintParser.parse(jsonNode2);
                            }
                            builder.blueprint = blueprint;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                item = this.mItemParser.parse(jsonNode2);
                            }
                            builder.item = item;
                            break;
                        case 3:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field embeddedIndex can't be null");
                            }
                            builder.embeddedIndex = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                str4 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.debugAttributes = str4;
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
                                str3 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.type = str3;
                            break;
                        case 7:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.embeddedParentId = str2;
                            break;
                        case 8:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.id = str;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XRayFloatableWidget so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.item, this, "item");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.embeddedIndex), this, "embeddedIndex");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(builder.embeddedParentId, this, "embeddedParentId");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XRayFloatableWidget parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1847139313:
                                    if (currentName.equals("embeddedParentId")) {
                                        b = 7;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 0;
                                    }
                                    break;
                                case -851565464:
                                    if (currentName.equals("embeddedIndex")) {
                                        b = 3;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 8;
                                    }
                                    break;
                                case 3242771:
                                    if (currentName.equals("item")) {
                                        b = 2;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 6;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 5;
                                    }
                                    break;
                                case 1965271699:
                                    if (currentName.equals("blueprint")) {
                                        b = 1;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            Analytics analytics = null;
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            Item item = null;
                            Blueprint blueprint = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprint = this.mBlueprintParser.parse(jsonParser);
                                    }
                                    builder.blueprint = blueprint;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        item = this.mItemParser.parse(jsonParser);
                                    }
                                    builder.item = item;
                                    break;
                                case 3:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field embeddedIndex can't be null");
                                    }
                                    builder.embeddedIndex = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str4;
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
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.type = str3;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.embeddedParentId = str2;
                                    break;
                                case 8:
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
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XRayFloatableWidget so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.item, this, "item");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.embeddedIndex), this, "embeddedIndex");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(builder.embeddedParentId, this, "embeddedParentId");
                    return builder.build();
                }
            }
        }
    }
}
