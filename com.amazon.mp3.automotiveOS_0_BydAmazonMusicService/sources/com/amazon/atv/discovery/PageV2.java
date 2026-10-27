package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.CacheControlPolicy;
import com.amazon.atv.discovery.FiltersBase;
import com.amazon.atv.discovery.NavigationsBase;
import com.amazon.atv.discovery.PageBase;
import com.amazon.atv.discovery.SectionBase;
import com.amazon.atv.discovery.SortsBase;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
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
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PageV2 extends PageBase {
    public final Optional<FiltersBase> filters;
    public final Optional<NavigationsBase> navigations;
    public final ImmutableMap<SectionType, SectionBase> sections;
    public final Optional<SortsBase> sorts;

    private PageV2(Builder builder) {
        super(builder);
        this.navigations = Optional.fromNullable(builder.navigations);
        this.sections = (ImmutableMap) Preconditions.checkNotNull(builder.sections, "Unexpected null field: sections");
        this.filters = Optional.fromNullable(builder.filters);
        this.sorts = Optional.fromNullable(builder.sorts);
    }

    @Override // com.amazon.atv.discovery.PageBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PageV2)) {
            return false;
        }
        PageV2 pageV2 = (PageV2) obj;
        return super.equals(obj) && Objects.equal(this.navigations, pageV2.navigations) && Objects.equal(this.sections, pageV2.sections) && Objects.equal(this.filters, pageV2.filters) && Objects.equal(this.sorts, pageV2.sorts);
    }

    @Override // com.amazon.atv.discovery.PageBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.navigations, this.sections, this.filters, this.sorts);
    }

    @Override // com.amazon.atv.discovery.PageBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("navigations", this.navigations).add("sections", this.sections).add("filters", this.filters).add("sorts", this.sorts).toString();
    }

    @NotThreadSafe
    public static class Builder extends PageBase.Builder {
        public FiltersBase filters;
        public NavigationsBase navigations;
        public ImmutableMap<SectionType, SectionBase> sections;
        public SortsBase sorts;

        public PageV2 build() {
            return new PageV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PageV2> {
        private final Analytics.Parser mAnalyticsParser;
        private final CacheControlPolicy.Parser mCacheControlPolicyParser;
        private final FiltersBase.Parser mFiltersBaseParser;
        private final NavigationsBase.Parser mNavigationsBaseParser;
        private final MapParser<SectionType, SectionBase> mSectionMapBaseParser;
        private final SortsBase.Parser mSortsBaseParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSortsBaseParser = new SortsBase.Parser(objectMapper);
            this.mCacheControlPolicyParser = new CacheControlPolicy.Parser(objectMapper);
            this.mNavigationsBaseParser = new NavigationsBase.Parser(objectMapper);
            this.mSectionMapBaseParser = MapParser.newParser(EnumParser.newParser(SectionType.class), new SectionBase.Parser(objectMapper));
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mFiltersBaseParser = new FiltersBase.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PageV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PageV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PageV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PageV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PageV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Analytics analytics;
            NavigationsBase navigationsBase;
            String str;
            ImmutableMap<SectionType, SectionBase> immutableMap;
            String str2;
            CacheControlPolicy cacheControlPolicy;
            FiltersBase filtersBase;
            SortsBase sortsBase;
            JsonParsingUtils.throwIfNotObject(jsonNode, "PageV2");
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
                                b = 0;
                            }
                            break;
                        case -854547461:
                            if (next.equals("filters")) {
                                b = 7;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 2;
                            }
                            break;
                        case 94416770:
                            if (next.equals("cache")) {
                                b = 6;
                            }
                            break;
                        case 109624981:
                            if (next.equals("sorts")) {
                                b = 8;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 3;
                            }
                            break;
                        case 947936814:
                            if (next.equals("sections")) {
                                b = 4;
                            }
                            break;
                        case 1908095199:
                            if (next.equals("navigations")) {
                                b = 1;
                            }
                            break;
                    }
                    analytics = null;
                    sortsBase = null;
                    filtersBase = null;
                    cacheControlPolicy = null;
                    str2 = null;
                    immutableMap = null;
                    str = null;
                    navigationsBase = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PageV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
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
                            navigationsBase = this.mNavigationsBaseParser.parse(jsonNode2);
                        }
                        builder.navigations = navigationsBase;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.text = str;
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
                            immutableMap = this.mSectionMapBaseParser.parse(jsonNode2);
                        }
                        builder.sections = immutableMap;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.subtext = str2;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            cacheControlPolicy = this.mCacheControlPolicyParser.parse(jsonNode2);
                        }
                        builder.cache = cacheControlPolicy;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            filtersBase = this.mFiltersBaseParser.parse(jsonNode2);
                        }
                        builder.filters = filtersBase;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            sortsBase = this.mSortsBaseParser.parse(jsonNode2);
                        }
                        builder.sorts = sortsBase;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PageV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.sections, this, "sections");
            JsonParsingUtils.checkNotNull(builder.cache, this, "cache");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PageV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 0;
                                    }
                                    break;
                                case -854547461:
                                    if (currentName.equals("filters")) {
                                        b = 7;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 2;
                                    }
                                    break;
                                case 94416770:
                                    if (currentName.equals("cache")) {
                                        b = 6;
                                    }
                                    break;
                                case 109624981:
                                    if (currentName.equals("sorts")) {
                                        b = 8;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 3;
                                    }
                                    break;
                                case 947936814:
                                    if (currentName.equals("sections")) {
                                        b = 4;
                                    }
                                    break;
                                case 1908095199:
                                    if (currentName.equals("navigations")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            Analytics analytics = null;
                            SortsBase sortsBase = null;
                            FiltersBase filtersBase = null;
                            CacheControlPolicy cacheControlPolicy = null;
                            String str = null;
                            ImmutableMap<SectionType, SectionBase> immutableMap = null;
                            String str2 = null;
                            NavigationsBase navigationsBase = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        navigationsBase = this.mNavigationsBaseParser.parse(jsonParser);
                                    }
                                    builder.navigations = navigationsBase;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str2;
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
                                        immutableMap = this.mSectionMapBaseParser.parse(jsonParser);
                                    }
                                    builder.sections = immutableMap;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.subtext = str;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        cacheControlPolicy = this.mCacheControlPolicyParser.parse(jsonParser);
                                    }
                                    builder.cache = cacheControlPolicy;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        filtersBase = this.mFiltersBaseParser.parse(jsonParser);
                                    }
                                    builder.filters = filtersBase;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        sortsBase = this.mSortsBaseParser.parse(jsonParser);
                                    }
                                    builder.sorts = sortsBase;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PageV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PageV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.sections, this, "sections");
                    JsonParsingUtils.checkNotNull(builder.cache, this, "cache");
                    return builder.build();
                }
            }
        }
    }
}
