package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.xrayv2.Blueprint;
import com.amazon.atv.xrayv2.CollectionBase;
import com.amazon.atv.xrayv2.TimedItemChanges;
import com.amazon.atv.xrayv2.TimingModel;
import com.amazon.atv.xrayv2.Widgets;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class TimeIndexedWidgetGroup extends CollectionBase {
    public final ImmutableList<TimedItemChanges> partitionedChangeList;
    public final TimingModel timingModel;
    public final Widgets widgets;

    private TimeIndexedWidgetGroup(Builder builder) {
        super(builder);
        this.widgets = (Widgets) Preconditions.checkNotNull(builder.widgets, "Unexpected null field: widgets");
        this.timingModel = (TimingModel) Preconditions.checkNotNull(builder.timingModel, "Unexpected null field: timingModel");
        this.partitionedChangeList = (ImmutableList) Preconditions.checkNotNull(builder.partitionedChangeList, "Unexpected null field: partitionedChangeList");
    }

    @Override // com.amazon.atv.xrayv2.CollectionBase, com.amazon.atv.xrayv2.Widget
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimeIndexedWidgetGroup)) {
            return false;
        }
        TimeIndexedWidgetGroup timeIndexedWidgetGroup = (TimeIndexedWidgetGroup) obj;
        return super.equals(obj) && Objects.equal(this.widgets, timeIndexedWidgetGroup.widgets) && Objects.equal(this.timingModel, timeIndexedWidgetGroup.timingModel) && Objects.equal(this.partitionedChangeList, timeIndexedWidgetGroup.partitionedChangeList);
    }

    @Override // com.amazon.atv.xrayv2.CollectionBase, com.amazon.atv.xrayv2.Widget
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.widgets, this.timingModel, this.partitionedChangeList);
    }

    @Override // com.amazon.atv.xrayv2.CollectionBase, com.amazon.atv.xrayv2.Widget
    public String toString() {
        return MoreObjects.toStringHelper(this).add("widgets", this.widgets).add("timingModel", this.timingModel).add("partitionedChangeList", this.partitionedChangeList).toString();
    }

    @NotThreadSafe
    public static class Builder extends CollectionBase.Builder {
        public ImmutableList<TimedItemChanges> partitionedChangeList;
        public TimingModel timingModel;
        public Widgets widgets;

        public TimeIndexedWidgetGroup build() {
            return new TimeIndexedWidgetGroup(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TimeIndexedWidgetGroup> {
        private final Analytics.Parser mAnalyticsParser;
        private final Blueprint.Parser mBlueprintParser;
        private final ListParser<TimedItemChanges> mPartitionedChangeListParser;
        private final SimpleParsers.StringParser mStringParser;
        private final TimingModel.Parser mTimingModelParser;
        private final Widgets.Parser mWidgetsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTimingModelParser = new TimingModel.Parser(objectMapper);
            this.mWidgetsParser = new Widgets.Parser(objectMapper);
            this.mPartitionedChangeListParser = ListParser.newParser(new TimedItemChanges.Parser(objectMapper));
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mBlueprintParser = new Blueprint.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TimeIndexedWidgetGroup parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimeIndexedWidgetGroup:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TimeIndexedWidgetGroup parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimeIndexedWidgetGroup:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TimeIndexedWidgetGroup parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Analytics analytics;
            Widgets widgets;
            Blueprint blueprint;
            String str;
            TimingModel timingModel;
            String str2;
            ImmutableList<TimedItemChanges> immutableList;
            String str3;
            JsonParsingUtils.throwIfNotObject(jsonNode, "TimeIndexedWidgetGroup");
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
                        case -1415982505:
                            if (next.equals("partitionedChangeList")) {
                                b = 7;
                            }
                            break;
                        case -715081473:
                            if (next.equals("timingModel")) {
                                b = 5;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 8;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 6;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 4;
                            }
                            break;
                        case 1340337839:
                            if (next.equals("widgets")) {
                                b = 1;
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
                    immutableList = null;
                    str2 = null;
                    timingModel = null;
                    str = null;
                    blueprint = null;
                    widgets = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TimeIndexedWidgetGroup so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
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
                            widgets = this.mWidgetsParser.parse(jsonNode2);
                        }
                        builder.widgets = widgets;
                        continue;
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
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        continue;
                        break;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            timingModel = this.mTimingModelParser.parse(jsonNode2);
                        }
                        builder.timingModel = timingModel;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.type = str2;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mPartitionedChangeListParser.parse(jsonNode2);
                        }
                        builder.partitionedChangeList = immutableList;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str3;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TimeIndexedWidgetGroup so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.widgets, this, "widgets");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.timingModel, this, "timingModel");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(builder.partitionedChangeList, this, "partitionedChangeList");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TimeIndexedWidgetGroup parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1415982505:
                                    if (currentName.equals("partitionedChangeList")) {
                                        b = 7;
                                    }
                                    break;
                                case -715081473:
                                    if (currentName.equals("timingModel")) {
                                        b = 5;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 8;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 6;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 4;
                                    }
                                    break;
                                case 1340337839:
                                    if (currentName.equals("widgets")) {
                                        b = 1;
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
                            ImmutableList<TimedItemChanges> immutableList = null;
                            String str2 = null;
                            TimingModel timingModel = null;
                            String str3 = null;
                            Blueprint blueprint = null;
                            Widgets widgets = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        widgets = this.mWidgetsParser.parse(jsonParser);
                                    }
                                    builder.widgets = widgets;
                                    continue;
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
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    continue;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        timingModel = this.mTimingModelParser.parse(jsonParser);
                                    }
                                    builder.timingModel = timingModel;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.type = str2;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mPartitionedChangeListParser.parse(jsonParser);
                                    }
                                    builder.partitionedChangeList = immutableList;
                                    continue;
                                case 8:
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
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TimeIndexedWidgetGroup so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TimeIndexedWidgetGroup so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.widgets, this, "widgets");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.timingModel, this, "timingModel");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(builder.partitionedChangeList, this, "partitionedChangeList");
                    return builder.build();
                }
            }
        }
    }
}
