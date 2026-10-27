package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.CacheControlPolicy;
import com.amazon.atv.discovery.Filters;
import com.amazon.atv.discovery.Item;
import com.amazon.atv.discovery.PageBase;
import com.amazon.atv.discovery.PagePresentationData;
import com.amazon.atv.discovery.Section;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class Page extends PageBase {
    public final Optional<Filters> filters;
    public final Optional<String> id;
    public final Optional<ImmutableList<Item>> navigations;
    public final Optional<ImmutableList<ContentTrait>> pageContentTraits;
    public final Optional<PagePresentationData> presentationData;
    public final Optional<String> referenceId;
    public final ImmutableMap<SectionType, Section> sections;

    protected Page(Builder builder) {
        super(builder);
        this.presentationData = Optional.fromNullable(builder.presentationData);
        this.id = Optional.fromNullable(builder.id);
        this.navigations = Optional.fromNullable(builder.navigations);
        this.sections = (ImmutableMap) Preconditions.checkNotNull(builder.sections, "Unexpected null field: sections");
        this.pageContentTraits = Optional.fromNullable(builder.pageContentTraits);
        this.filters = Optional.fromNullable(builder.filters);
        this.referenceId = Optional.fromNullable(builder.referenceId);
    }

    @Override // com.amazon.atv.discovery.PageBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Page)) {
            return false;
        }
        Page page = (Page) obj;
        return super.equals(obj) && Objects.equal(this.presentationData, page.presentationData) && Objects.equal(this.id, page.id) && Objects.equal(this.navigations, page.navigations) && Objects.equal(this.sections, page.sections) && Objects.equal(this.pageContentTraits, page.pageContentTraits) && Objects.equal(this.filters, page.filters) && Objects.equal(this.referenceId, page.referenceId);
    }

    @Override // com.amazon.atv.discovery.PageBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.presentationData, this.id, this.navigations, this.sections, this.pageContentTraits, this.filters, this.referenceId);
    }

    @Override // com.amazon.atv.discovery.PageBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("presentationData", this.presentationData).add("id", this.id).add("navigations", this.navigations).add("sections", this.sections).add("pageContentTraits", this.pageContentTraits).add("filters", this.filters).add("referenceId", this.referenceId).toString();
    }

    @NotThreadSafe
    public static class Builder extends PageBase.Builder {
        public Filters filters;
        public String id;
        public ImmutableList<Item> navigations;
        public ImmutableList<ContentTrait> pageContentTraits;
        public PagePresentationData presentationData;
        public String referenceId;
        public ImmutableMap<SectionType, Section> sections;

        public Page build() {
            return new Page(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Page> {
        private final Analytics.Parser mAnalyticsParser;
        private final CacheControlPolicy.Parser mCacheControlPolicyParser;
        private final ListParser<ContentTrait> mContentTraitsParser;
        private final Filters.Parser mFiltersParser;
        private final ListParser<Item> mItemListParser;
        private final PagePresentationData.Parser mPagePresentationDataParser;
        private final MapParser<SectionType, Section> mSectionMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mFiltersParser = new Filters.Parser(objectMapper);
            this.mCacheControlPolicyParser = new CacheControlPolicy.Parser(objectMapper);
            this.mPagePresentationDataParser = new PagePresentationData.Parser(objectMapper);
            this.mItemListParser = ListParser.newParser(new Item.Parser(objectMapper));
            this.mContentTraitsParser = ListParser.newParser(EnumParser.newParser(ContentTrait.class));
            this.mSectionMapParser = MapParser.newParser(EnumParser.newParser(SectionType.class), new Section.Parser(objectMapper));
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Page parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Page:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Page parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Page:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Page parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            PagePresentationData pagePresentationData;
            Analytics analytics;
            String str;
            String str2;
            String str3;
            ImmutableList<Item> immutableList;
            CacheControlPolicy cacheControlPolicy;
            ImmutableMap<SectionType, Section> immutableMap;
            ImmutableList<ContentTrait> immutableList2;
            Filters filters;
            String str4;
            JsonParsingUtils.throwIfNotObject(jsonNode, "Page");
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
                                b = 5;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 1;
                            }
                            break;
                        case -854547461:
                            if (next.equals("filters")) {
                                b = 10;
                            }
                            break;
                        case -16211514:
                            if (next.equals("referenceId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 2;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 3;
                            }
                            break;
                        case 94416770:
                            if (next.equals("cache")) {
                                b = 7;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 4;
                            }
                            break;
                        case 605757359:
                            if (next.equals("pageContentTraits")) {
                                b = 9;
                            }
                            break;
                        case 947936814:
                            if (next.equals("sections")) {
                                b = 8;
                            }
                            break;
                        case 1602373092:
                            if (next.equals("presentationData")) {
                                b = 0;
                            }
                            break;
                        case 1908095199:
                            if (next.equals("navigations")) {
                                b = 6;
                            }
                            break;
                    }
                    pagePresentationData = null;
                    str4 = null;
                    filters = null;
                    immutableList2 = null;
                    immutableMap = null;
                    cacheControlPolicy = null;
                    immutableList = null;
                    str3 = null;
                    str2 = null;
                    str = null;
                    analytics = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Page so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            pagePresentationData = this.mPagePresentationDataParser.parse(jsonNode2);
                        }
                        builder.presentationData = pagePresentationData;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.text = str2;
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
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.subtext = str3;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mItemListParser.parse(jsonNode2);
                        }
                        builder.navigations = immutableList;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            cacheControlPolicy = this.mCacheControlPolicyParser.parse(jsonNode2);
                        }
                        builder.cache = cacheControlPolicy;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mSectionMapParser.parse(jsonNode2);
                        }
                        builder.sections = immutableMap;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mContentTraitsParser.parse(jsonNode2);
                        }
                        builder.pageContentTraits = immutableList2;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            filters = this.mFiltersParser.parse(jsonNode2);
                        }
                        builder.filters = filters;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.referenceId = str4;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Page so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.cache, this, "cache");
            JsonParsingUtils.checkNotNull(builder.sections, this, "sections");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Page parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 5;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 1;
                                    }
                                    break;
                                case -854547461:
                                    if (currentName.equals("filters")) {
                                        b = 10;
                                    }
                                    break;
                                case -16211514:
                                    if (currentName.equals("referenceId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 2;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 3;
                                    }
                                    break;
                                case 94416770:
                                    if (currentName.equals("cache")) {
                                        b = 7;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 4;
                                    }
                                    break;
                                case 605757359:
                                    if (currentName.equals("pageContentTraits")) {
                                        b = 9;
                                    }
                                    break;
                                case 947936814:
                                    if (currentName.equals("sections")) {
                                        b = 8;
                                    }
                                    break;
                                case 1602373092:
                                    if (currentName.equals("presentationData")) {
                                        b = 0;
                                    }
                                    break;
                                case 1908095199:
                                    if (currentName.equals("navigations")) {
                                        b = 6;
                                    }
                                    break;
                            }
                            PagePresentationData pagePresentationData = null;
                            String str = null;
                            Filters filters = null;
                            ImmutableList<ContentTrait> immutableList = null;
                            ImmutableMap<SectionType, Section> immutableMap = null;
                            CacheControlPolicy cacheControlPolicy = null;
                            ImmutableList<Item> immutableList2 = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            Analytics analytics = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        pagePresentationData = this.mPagePresentationDataParser.parse(jsonParser);
                                    }
                                    builder.presentationData = pagePresentationData;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str4;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str3;
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
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.subtext = str2;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mItemListParser.parse(jsonParser);
                                    }
                                    builder.navigations = immutableList2;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        cacheControlPolicy = this.mCacheControlPolicyParser.parse(jsonParser);
                                    }
                                    builder.cache = cacheControlPolicy;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mSectionMapParser.parse(jsonParser);
                                    }
                                    builder.sections = immutableMap;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mContentTraitsParser.parse(jsonParser);
                                    }
                                    builder.pageContentTraits = immutableList;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        filters = this.mFiltersParser.parse(jsonParser);
                                    }
                                    builder.filters = filters;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.referenceId = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Page so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Page so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
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
