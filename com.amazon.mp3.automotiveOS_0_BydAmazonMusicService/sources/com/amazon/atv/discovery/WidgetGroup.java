package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.Blueprint;
import com.amazon.atv.discovery.Widget;
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
public class WidgetGroup extends Widget {
    public final Widgets widgets;

    protected WidgetGroup(Builder builder) {
        super(builder);
        this.widgets = (Widgets) Preconditions.checkNotNull(builder.widgets, "Unexpected null field: widgets");
    }

    @Override // com.amazon.atv.discovery.Widget
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof WidgetGroup) {
            return super.equals(obj) && Objects.equal(this.widgets, ((WidgetGroup) obj).widgets);
        }
        return false;
    }

    @Override // com.amazon.atv.discovery.Widget
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.widgets);
    }

    @Override // com.amazon.atv.discovery.Widget
    public String toString() {
        return MoreObjects.toStringHelper(this).add("widgets", this.widgets).toString();
    }

    @NotThreadSafe
    public static class Builder extends Widget.Builder {
        public Widgets widgets;

        public WidgetGroup build() {
            return new WidgetGroup(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<WidgetGroup> {
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
        public WidgetGroup parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidgetGroup:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public WidgetGroup parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidgetGroup:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private WidgetGroup parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Analytics analytics;
            Blueprint blueprint;
            String str;
            String str2;
            Widgets widgets;
            String str3;
            JsonParsingUtils.throwIfNotObject(jsonNode, "WidgetGroup");
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
                        case 3355:
                            if (next.equals("id")) {
                                b = 4;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 6;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 1;
                            }
                            break;
                        case 1340337839:
                            if (next.equals("widgets")) {
                                b = 5;
                            }
                            break;
                        case 1965271699:
                            if (next.equals("blueprint")) {
                                b = 2;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 3;
                            }
                            break;
                    }
                    analytics = null;
                    str3 = null;
                    widgets = null;
                    str2 = null;
                    str = null;
                    blueprint = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing WidgetGroup so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 1:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        continue;
                        break;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            blueprint = this.mBlueprintParser.parse(jsonNode2);
                        }
                        builder.blueprint = blueprint;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = str;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str2;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            widgets = this.mWidgetsParser.parse(jsonNode2);
                        }
                        builder.widgets = widgets;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.type = str3;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing WidgetGroup so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.widgets, this, "widgets");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private WidgetGroup parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 4;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 6;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 1;
                                    }
                                    break;
                                case 1340337839:
                                    if (currentName.equals("widgets")) {
                                        b = 5;
                                    }
                                    break;
                                case 1965271699:
                                    if (currentName.equals("blueprint")) {
                                        b = 2;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            Analytics analytics = null;
                            String str = null;
                            Widgets widgets = null;
                            String str2 = null;
                            String str3 = null;
                            Blueprint blueprint = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 1:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    continue;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprint = this.mBlueprintParser.parse(jsonParser);
                                    }
                                    builder.blueprint = blueprint;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str3;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str2;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        widgets = this.mWidgetsParser.parse(jsonParser);
                                    }
                                    builder.widgets = widgets;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.type = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing WidgetGroup so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing WidgetGroup so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.widgets, this, "widgets");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    return builder.build();
                }
            }
        }
    }
}
