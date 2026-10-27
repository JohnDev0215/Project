package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.BrowseNode;
import com.amazon.atv.discovery.CacheControlPolicy;
import com.amazon.atv.discovery.Filters;
import com.amazon.atv.discovery.Item;
import com.amazon.atv.discovery.Page;
import com.amazon.atv.discovery.PagePresentationData;
import com.amazon.atv.discovery.Section;
import com.amazon.atv.discovery.Sorts;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class BrowsePage extends Page {
    public final Optional<ImmutableList<BrowseNode>> refinements;
    public final Optional<Sorts> sorts;

    private BrowsePage(Builder builder) {
        super(builder);
        this.refinements = Optional.fromNullable(builder.refinements);
        this.sorts = Optional.fromNullable(builder.sorts);
    }

    @Override // com.amazon.atv.discovery.Page, com.amazon.atv.discovery.PageBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BrowsePage)) {
            return false;
        }
        BrowsePage browsePage = (BrowsePage) obj;
        return super.equals(obj) && Objects.equal(this.refinements, browsePage.refinements) && Objects.equal(this.sorts, browsePage.sorts);
    }

    @Override // com.amazon.atv.discovery.Page, com.amazon.atv.discovery.PageBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.refinements, this.sorts);
    }

    @Override // com.amazon.atv.discovery.Page, com.amazon.atv.discovery.PageBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("refinements", this.refinements).add("sorts", this.sorts).toString();
    }

    @NotThreadSafe
    public static class Builder extends Page.Builder {
        public ImmutableList<BrowseNode> refinements;
        public Sorts sorts;

        @Override // com.amazon.atv.discovery.Page.Builder
        public BrowsePage build() {
            return new BrowsePage(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<BrowsePage> {
        private final Analytics.Parser mAnalyticsParser;
        private final ListParser<BrowseNode> mBrowseNodeListParser;
        private final CacheControlPolicy.Parser mCacheControlPolicyParser;
        private final ListParser<ContentTrait> mContentTraitsParser;
        private final Filters.Parser mFiltersParser;
        private final ListParser<Item> mItemListParser;
        private final PagePresentationData.Parser mPagePresentationDataParser;
        private final MapParser<SectionType, Section> mSectionMapParser;
        private final Sorts.Parser mSortsParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mFiltersParser = new Filters.Parser(objectMapper);
            this.mCacheControlPolicyParser = new CacheControlPolicy.Parser(objectMapper);
            this.mBrowseNodeListParser = ListParser.newParser(new BrowseNode.Parser(objectMapper));
            this.mPagePresentationDataParser = new PagePresentationData.Parser(objectMapper);
            this.mItemListParser = ListParser.newParser(new Item.Parser(objectMapper));
            this.mContentTraitsParser = ListParser.newParser(EnumParser.newParser(ContentTrait.class));
            this.mSectionMapParser = MapParser.newParser(EnumParser.newParser(SectionType.class), new Section.Parser(objectMapper));
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mSortsParser = new Sorts.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public BrowsePage parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BrowsePage:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public BrowsePage parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BrowsePage:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private BrowsePage parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            PagePresentationData pagePresentationData;
            ImmutableList<Item> immutableList;
            CacheControlPolicy cacheControlPolicy;
            Sorts sorts;
            ImmutableMap<SectionType, Section> immutableMap;
            String str;
            Analytics analytics;
            ImmutableList<BrowseNode> immutableList2;
            String str2;
            String str3;
            String str4;
            ImmutableList<ContentTrait> immutableList3;
            Filters filters;
            JsonParsingUtils.throwIfNotObject(jsonNode, "BrowsePage");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1867586707:
                            if (next.equals("subtext")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 7;
                            }
                            break;
                        case -890806136:
                            if (next.equals("refinements")) {
                                b = 8;
                            }
                            break;
                        case -854547461:
                            if (next.equals("filters")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case -16211514:
                            if (next.equals("referenceId")) {
                                b = 6;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 9;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 10;
                            }
                            break;
                        case 94416770:
                            if (next.equals("cache")) {
                                b = 3;
                            }
                            break;
                        case 109624981:
                            if (next.equals("sorts")) {
                                b = 4;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 1;
                            }
                            break;
                        case 605757359:
                            if (next.equals("pageContentTraits")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 947936814:
                            if (next.equals("sections")) {
                                b = 5;
                            }
                            break;
                        case 1602373092:
                            if (next.equals("presentationData")) {
                                b = 0;
                            }
                            break;
                        case 1908095199:
                            if (next.equals("navigations")) {
                                b = 2;
                            }
                            break;
                    }
                    pagePresentationData = null;
                    filters = null;
                    immutableList3 = null;
                    str4 = null;
                    str3 = null;
                    str2 = null;
                    immutableList2 = null;
                    analytics = null;
                    str = null;
                    immutableMap = null;
                    sorts = null;
                    cacheControlPolicy = null;
                    immutableList = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing BrowsePage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            pagePresentationData = this.mPagePresentationDataParser.parse(jsonNode2);
                        }
                        builder.presentationData = pagePresentationData;
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
                            immutableList = this.mItemListParser.parse(jsonNode2);
                        }
                        builder.navigations = immutableList;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            cacheControlPolicy = this.mCacheControlPolicyParser.parse(jsonNode2);
                        }
                        builder.cache = cacheControlPolicy;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            sorts = this.mSortsParser.parse(jsonNode2);
                        }
                        builder.sorts = sorts;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mSectionMapParser.parse(jsonNode2);
                        }
                        builder.sections = immutableMap;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.referenceId = str;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mBrowseNodeListParser.parse(jsonNode2);
                        }
                        builder.refinements = immutableList2;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str2;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.text = str3;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.subtext = str4;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            immutableList3 = this.mContentTraitsParser.parse(jsonNode2);
                        }
                        builder.pageContentTraits = immutableList3;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            filters = this.mFiltersParser.parse(jsonNode2);
                        }
                        builder.filters = filters;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing BrowsePage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.cache, this, "cache");
            JsonParsingUtils.checkNotNull(builder.sections, this, "sections");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private BrowsePage parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1867586707:
                                    if (currentName.equals("subtext")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 7;
                                    }
                                    break;
                                case -890806136:
                                    if (currentName.equals("refinements")) {
                                        b = 8;
                                    }
                                    break;
                                case -854547461:
                                    if (currentName.equals("filters")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case -16211514:
                                    if (currentName.equals("referenceId")) {
                                        b = 6;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 9;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 10;
                                    }
                                    break;
                                case 94416770:
                                    if (currentName.equals("cache")) {
                                        b = 3;
                                    }
                                    break;
                                case 109624981:
                                    if (currentName.equals("sorts")) {
                                        b = 4;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 1;
                                    }
                                    break;
                                case 605757359:
                                    if (currentName.equals("pageContentTraits")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 947936814:
                                    if (currentName.equals("sections")) {
                                        b = 5;
                                    }
                                    break;
                                case 1602373092:
                                    if (currentName.equals("presentationData")) {
                                        b = 0;
                                    }
                                    break;
                                case 1908095199:
                                    if (currentName.equals("navigations")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            PagePresentationData pagePresentationData = null;
                            Filters filters = null;
                            ImmutableList<ContentTrait> immutableList = null;
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            ImmutableList<BrowseNode> immutableList2 = null;
                            Analytics analytics = null;
                            String str4 = null;
                            ImmutableMap<SectionType, Section> immutableMap = null;
                            Sorts sorts = null;
                            CacheControlPolicy cacheControlPolicy = null;
                            ImmutableList<Item> immutableList3 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        pagePresentationData = this.mPagePresentationDataParser.parse(jsonParser);
                                    }
                                    builder.presentationData = pagePresentationData;
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
                                        immutableList3 = this.mItemListParser.parse(jsonParser);
                                    }
                                    builder.navigations = immutableList3;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        cacheControlPolicy = this.mCacheControlPolicyParser.parse(jsonParser);
                                    }
                                    builder.cache = cacheControlPolicy;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        sorts = this.mSortsParser.parse(jsonParser);
                                    }
                                    builder.sorts = sorts;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mSectionMapParser.parse(jsonParser);
                                    }
                                    builder.sections = immutableMap;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.referenceId = str4;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mBrowseNodeListParser.parse(jsonParser);
                                    }
                                    builder.refinements = immutableList2;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str3;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str2;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.subtext = str;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mContentTraitsParser.parse(jsonParser);
                                    }
                                    builder.pageContentTraits = immutableList;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        filters = this.mFiltersParser.parse(jsonParser);
                                    }
                                    builder.filters = filters;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing BrowsePage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing BrowsePage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.cache, this, "cache");
                    JsonParsingUtils.checkNotNull(builder.sections, this, "sections");
                    return builder.build();
                }
            }
        }
    }
}
