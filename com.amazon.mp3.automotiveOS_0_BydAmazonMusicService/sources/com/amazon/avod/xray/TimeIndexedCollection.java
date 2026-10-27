package com.amazon.avod.xray;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.Blueprint;
import com.amazon.atv.discovery.CollectionBase;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avod.xray.IdBasedItem;
import com.amazon.avod.xray.TimedItemChanges;
import com.amazon.avod.xray.TimingModel;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class TimeIndexedCollection extends CollectionBase {
    public final ImmutableList<IdBasedItem> items;
    public final ImmutableList<TimedItemChanges> partitionedChangeList;
    public final TimingModel timingModel;

    private TimeIndexedCollection(Builder builder) {
        super(builder);
        this.items = (ImmutableList) Preconditions.checkNotNull(builder.items, "Unexpected null field: items");
        this.timingModel = (TimingModel) Preconditions.checkNotNull(builder.timingModel, "Unexpected null field: timingModel");
        this.partitionedChangeList = (ImmutableList) Preconditions.checkNotNull(builder.partitionedChangeList, "Unexpected null field: partitionedChangeList");
    }

    @Override // com.amazon.atv.discovery.CollectionBase, com.amazon.atv.discovery.Widget
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimeIndexedCollection)) {
            return false;
        }
        TimeIndexedCollection timeIndexedCollection = (TimeIndexedCollection) obj;
        return super.equals(obj) && Objects.equal(this.items, timeIndexedCollection.items) && Objects.equal(this.timingModel, timeIndexedCollection.timingModel) && Objects.equal(this.partitionedChangeList, timeIndexedCollection.partitionedChangeList);
    }

    @Override // com.amazon.atv.discovery.CollectionBase, com.amazon.atv.discovery.Widget
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.items, this.timingModel, this.partitionedChangeList);
    }

    @Override // com.amazon.atv.discovery.CollectionBase, com.amazon.atv.discovery.Widget
    public String toString() {
        return MoreObjects.toStringHelper(this).add(ContextMappingConstants.ITEMS, this.items).add("timingModel", this.timingModel).add("partitionedChangeList", this.partitionedChangeList).toString();
    }

    @NotThreadSafe
    public static class Builder extends CollectionBase.Builder {
        public ImmutableList<IdBasedItem> items;
        public ImmutableList<TimedItemChanges> partitionedChangeList;
        public TimingModel timingModel;

        public TimeIndexedCollection build() {
            return new TimeIndexedCollection(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TimeIndexedCollection> {
        private final Analytics.Parser mAnalyticsParser;
        private final Blueprint.Parser mBlueprintParser;
        private final ListParser<IdBasedItem> mIdBasedItemListParser;
        private final ListParser<TimedItemChanges> mPartitionedChangeListParser;
        private final SimpleParsers.StringParser mStringParser;
        private final TimingModel.Parser mTimingModelParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTimingModelParser = new TimingModel.Parser(objectMapper);
            this.mIdBasedItemListParser = ListParser.newParser(new IdBasedItem.Parser(objectMapper));
            this.mPartitionedChangeListParser = ListParser.newParser(new TimedItemChanges.Parser(objectMapper));
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mBlueprintParser = new Blueprint.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TimeIndexedCollection parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimeIndexedCollection:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TimeIndexedCollection parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TimeIndexedCollection:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TimeIndexedCollection parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ImmutableList<IdBasedItem> immutableList;
            Analytics analytics;
            TimingModel timingModel;
            Blueprint blueprint;
            String str;
            String str2;
            ImmutableList<TimedItemChanges> immutableList2;
            String str3;
            JsonParsingUtils.throwIfNotObject(jsonNode, "TimeIndexedCollection");
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
                                b = 1;
                            }
                            break;
                        case -1415982505:
                            if (next.equals("partitionedChangeList")) {
                                b = 7;
                            }
                            break;
                        case -715081473:
                            if (next.equals("timingModel")) {
                                b = 2;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 6;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 8;
                            }
                            break;
                        case 100526016:
                            if (next.equals(ContextMappingConstants.ITEMS)) {
                                b = 0;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 3;
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
                    immutableList = null;
                    str3 = null;
                    immutableList2 = null;
                    str2 = null;
                    str = null;
                    blueprint = null;
                    timingModel = null;
                    analytics = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TimeIndexedCollection so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mIdBasedItemListParser.parse(jsonNode2);
                        }
                        builder.items = immutableList;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            timingModel = this.mTimingModelParser.parse(jsonNode2);
                        }
                        builder.timingModel = timingModel;
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
                            blueprint = this.mBlueprintParser.parse(jsonNode2);
                        }
                        builder.blueprint = blueprint;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = str;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str2;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mPartitionedChangeListParser.parse(jsonNode2);
                        }
                        builder.partitionedChangeList = immutableList2;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.type = str3;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TimeIndexedCollection so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.items, this, ContextMappingConstants.ITEMS);
            JsonParsingUtils.checkNotNull(builder.timingModel, this, "timingModel");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.partitionedChangeList, this, "partitionedChangeList");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TimeIndexedCollection parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 1;
                                    }
                                    break;
                                case -1415982505:
                                    if (currentName.equals("partitionedChangeList")) {
                                        b = 7;
                                    }
                                    break;
                                case -715081473:
                                    if (currentName.equals("timingModel")) {
                                        b = 2;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 6;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 8;
                                    }
                                    break;
                                case 100526016:
                                    if (currentName.equals(ContextMappingConstants.ITEMS)) {
                                        b = 0;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 3;
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
                            ImmutableList<IdBasedItem> immutableList = null;
                            String str = null;
                            ImmutableList<TimedItemChanges> immutableList2 = null;
                            String str2 = null;
                            String str3 = null;
                            Blueprint blueprint = null;
                            TimingModel timingModel = null;
                            Analytics analytics = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mIdBasedItemListParser.parse(jsonParser);
                                    }
                                    builder.items = immutableList;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        timingModel = this.mTimingModelParser.parse(jsonParser);
                                    }
                                    builder.timingModel = timingModel;
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
                                        blueprint = this.mBlueprintParser.parse(jsonParser);
                                    }
                                    builder.blueprint = blueprint;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str3;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str2;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mPartitionedChangeListParser.parse(jsonParser);
                                    }
                                    builder.partitionedChangeList = immutableList2;
                                    continue;
                                case 8:
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
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TimeIndexedCollection so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TimeIndexedCollection so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.items, this, ContextMappingConstants.ITEMS);
                    JsonParsingUtils.checkNotNull(builder.timingModel, this, "timingModel");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.partitionedChangeList, this, "partitionedChangeList");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    return builder.build();
                }
            }
        }
    }
}
