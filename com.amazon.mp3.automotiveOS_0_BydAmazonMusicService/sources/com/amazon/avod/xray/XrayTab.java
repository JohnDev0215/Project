package com.amazon.avod.xray;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.Blueprint;
import com.amazon.atv.discovery.WidgetGroup;
import com.amazon.atv.discovery.Widgets;
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
public class XrayTab extends WidgetGroup {
    public final String accessibilityDescription;
    public final boolean hideNavigationBar;
    public final String tabType;

    private XrayTab(Builder builder) {
        super(builder);
        this.tabType = (String) Preconditions.checkNotNull(builder.tabType, "Unexpected null field: tabType");
        this.accessibilityDescription = (String) Preconditions.checkNotNull(builder.accessibilityDescription, "Unexpected null field: accessibilityDescription");
        this.hideNavigationBar = builder.hideNavigationBar;
    }

    @Override // com.amazon.atv.discovery.WidgetGroup, com.amazon.atv.discovery.Widget
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XrayTab)) {
            return false;
        }
        XrayTab xrayTab = (XrayTab) obj;
        return super.equals(obj) && Objects.equal(this.tabType, xrayTab.tabType) && Objects.equal(this.accessibilityDescription, xrayTab.accessibilityDescription) && Objects.equal(Boolean.valueOf(this.hideNavigationBar), Boolean.valueOf(xrayTab.hideNavigationBar));
    }

    @Override // com.amazon.atv.discovery.WidgetGroup, com.amazon.atv.discovery.Widget
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.tabType, this.accessibilityDescription, Boolean.valueOf(this.hideNavigationBar));
    }

    @Override // com.amazon.atv.discovery.WidgetGroup, com.amazon.atv.discovery.Widget
    public String toString() {
        return MoreObjects.toStringHelper(this).add("tabType", this.tabType).add("accessibilityDescription", this.accessibilityDescription).add("hideNavigationBar", this.hideNavigationBar).toString();
    }

    @NotThreadSafe
    public static class Builder extends WidgetGroup.Builder {
        public String accessibilityDescription;
        public boolean hideNavigationBar;
        public String tabType;

        @Override // com.amazon.atv.discovery.WidgetGroup.Builder
        public XrayTab build() {
            return new XrayTab(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XrayTab> {
        private final Analytics.Parser mAnalyticsParser;
        private final Blueprint.Parser mBlueprintParser;
        private final SimpleParsers.StringParser mStringParser;
        private final Widgets.Parser mWidgetsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mWidgetsParser = new Widgets.Parser(objectMapper);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mBlueprintParser = new Blueprint.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XrayTab parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XrayTab:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XrayTab parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XrayTab:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XrayTab parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "XrayTab");
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
                                b = 0;
                            }
                            break;
                        case -1619312835:
                            if (next.equals("hideNavigationBar")) {
                                b = 8;
                            }
                            break;
                        case -1553600401:
                            if (next.equals("tabType")) {
                                b = 2;
                            }
                            break;
                        case -499044914:
                            if (next.equals("accessibilityDescription")) {
                                b = 3;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 6;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 9;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 1;
                            }
                            break;
                        case 1340337839:
                            if (next.equals("widgets")) {
                                b = 7;
                            }
                            break;
                        case 1965271699:
                            if (next.equals("blueprint")) {
                                b = 4;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 5;
                            }
                            break;
                    }
                    Analytics analytics = null;
                    String str = null;
                    Widgets widgets = null;
                    String str2 = null;
                    String str3 = null;
                    Blueprint blueprint = null;
                    String str4 = null;
                    String str5 = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                analytics = this.mAnalyticsParser.parse(jsonNode2);
                            }
                            builder.analytics = analytics;
                            break;
                        case 1:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                str5 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.tabType = str5;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                str4 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.accessibilityDescription = str4;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                blueprint = this.mBlueprintParser.parse(jsonNode2);
                            }
                            builder.blueprint = blueprint;
                            break;
                        case 5:
                            if (!jsonNode2.isNull()) {
                                str3 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.debugAttributes = str3;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.id = str2;
                            break;
                        case 7:
                            if (!jsonNode2.isNull()) {
                                widgets = this.mWidgetsParser.parse(jsonNode2);
                            }
                            builder.widgets = widgets;
                            break;
                        case 8:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field hideNavigationBar can't be null");
                            }
                            builder.hideNavigationBar = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 9:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.type = str;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing XrayTab so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.tabType, this, "tabType");
            JsonParsingUtils.checkNotNull(builder.accessibilityDescription, this, "accessibilityDescription");
            JsonParsingUtils.checkNotNull(builder.widgets, this, "widgets");
            JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.hideNavigationBar), this, "hideNavigationBar");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private XrayTab parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 0;
                                    }
                                    break;
                                case -1619312835:
                                    if (currentName.equals("hideNavigationBar")) {
                                        b = 8;
                                    }
                                    break;
                                case -1553600401:
                                    if (currentName.equals("tabType")) {
                                        b = 2;
                                    }
                                    break;
                                case -499044914:
                                    if (currentName.equals("accessibilityDescription")) {
                                        b = 3;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 6;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 9;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 1;
                                    }
                                    break;
                                case 1340337839:
                                    if (currentName.equals("widgets")) {
                                        b = 7;
                                    }
                                    break;
                                case 1965271699:
                                    if (currentName.equals("blueprint")) {
                                        b = 4;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = 5;
                                    }
                                    break;
                            }
                            Analytics analytics = null;
                            String str = null;
                            Widgets widgets = null;
                            String str2 = null;
                            String str3 = null;
                            Blueprint blueprint = null;
                            String str4 = null;
                            String str5 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 1:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.tabType = str5;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.accessibilityDescription = str4;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprint = this.mBlueprintParser.parse(jsonParser);
                                    }
                                    builder.blueprint = blueprint;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str3;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str2;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        widgets = this.mWidgetsParser.parse(jsonParser);
                                    }
                                    builder.widgets = widgets;
                                    break;
                                case 8:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field hideNavigationBar can't be null");
                                    }
                                    builder.hideNavigationBar = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.type = str;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing XrayTab so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.tabType, this, "tabType");
                    JsonParsingUtils.checkNotNull(builder.accessibilityDescription, this, "accessibilityDescription");
                    JsonParsingUtils.checkNotNull(builder.widgets, this, "widgets");
                    JsonParsingUtils.checkNotNull(Boolean.valueOf(builder.hideNavigationBar), this, "hideNavigationBar");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    return builder.build();
                }
            }
        }
    }
}
