package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.CacheControlPolicy;
import com.amazon.atv.xrayv2.Blueprint;
import com.amazon.atv.xrayv2.SectionBase;
import com.amazon.atv.xrayv2.Widgets;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class SectionV2 extends SectionBase {
    public final Optional<Blueprint> blueprint;
    public final Optional<ImmutableList<ContentTrait>> pageContentTraits;
    public final Widgets widgets;

    private SectionV2(Builder builder) {
        super(builder);
        this.pageContentTraits = Optional.fromNullable(builder.pageContentTraits);
        this.blueprint = Optional.fromNullable(builder.blueprint);
        this.widgets = (Widgets) Preconditions.checkNotNull(builder.widgets, "Unexpected null field: widgets");
    }

    @Override // com.amazon.atv.xrayv2.SectionBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SectionV2)) {
            return false;
        }
        SectionV2 sectionV2 = (SectionV2) obj;
        return super.equals(obj) && Objects.equal(this.pageContentTraits, sectionV2.pageContentTraits) && Objects.equal(this.blueprint, sectionV2.blueprint) && Objects.equal(this.widgets, sectionV2.widgets);
    }

    @Override // com.amazon.atv.xrayv2.SectionBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.pageContentTraits, this.blueprint, this.widgets);
    }

    @Override // com.amazon.atv.xrayv2.SectionBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("pageContentTraits", this.pageContentTraits).add("blueprint", this.blueprint).add("widgets", this.widgets).toString();
    }

    @NotThreadSafe
    public static class Builder extends SectionBase.Builder {
        public Blueprint blueprint;
        public ImmutableList<ContentTrait> pageContentTraits;
        public Widgets widgets;

        public SectionV2 build() {
            return new SectionV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SectionV2> {
        private final Analytics.Parser mAnalyticsParser;
        private final Blueprint.Parser mBlueprintParser;
        private final CacheControlPolicy.Parser mCacheControlPolicyParser;
        private final ListParser<ContentTrait> mContentTraitsParser;
        private final SimpleParsers.StringParser mStringParser;
        private final Widgets.Parser mWidgetsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCacheControlPolicyParser = new CacheControlPolicy.Parser(objectMapper);
            this.mWidgetsParser = new Widgets.Parser(objectMapper);
            this.mContentTraitsParser = ListParser.newParser(EnumParser.newParser(ContentTrait.class));
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mBlueprintParser = new Blueprint.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SectionV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SectionV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SectionV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SectionV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SectionV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ImmutableList<ContentTrait> immutableList;
            Blueprint blueprint;
            CacheControlPolicy cacheControlPolicy;
            String str;
            Widgets widgets;
            Analytics analytics;
            String str2;
            JsonParsingUtils.throwIfNotObject(jsonNode, "SectionV2");
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
                                b = 7;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 6;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 3;
                            }
                            break;
                        case 94416770:
                            if (next.equals("cache")) {
                                b = 2;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 4;
                            }
                            break;
                        case 605757359:
                            if (next.equals("pageContentTraits")) {
                                b = 0;
                            }
                            break;
                        case 1340337839:
                            if (next.equals("widgets")) {
                                b = 5;
                            }
                            break;
                        case 1965271699:
                            if (next.equals("blueprint")) {
                                b = 1;
                            }
                            break;
                    }
                    immutableList = null;
                    str2 = null;
                    analytics = null;
                    widgets = null;
                    str = null;
                    cacheControlPolicy = null;
                    blueprint = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SectionV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mContentTraitsParser.parse(jsonNode2);
                        }
                        builder.pageContentTraits = immutableList;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            blueprint = this.mBlueprintParser.parse(jsonNode2);
                        }
                        builder.blueprint = blueprint;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            cacheControlPolicy = this.mCacheControlPolicyParser.parse(jsonNode2);
                        }
                        builder.cache = cacheControlPolicy;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.text = str;
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
                            widgets = this.mWidgetsParser.parse(jsonNode2);
                        }
                        builder.widgets = widgets;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.subtext = str2;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SectionV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.cache, this, "cache");
            JsonParsingUtils.checkNotNull(builder.widgets, this, "widgets");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SectionV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 7;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 6;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 3;
                                    }
                                    break;
                                case 94416770:
                                    if (currentName.equals("cache")) {
                                        b = 2;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 4;
                                    }
                                    break;
                                case 605757359:
                                    if (currentName.equals("pageContentTraits")) {
                                        b = 0;
                                    }
                                    break;
                                case 1340337839:
                                    if (currentName.equals("widgets")) {
                                        b = 5;
                                    }
                                    break;
                                case 1965271699:
                                    if (currentName.equals("blueprint")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            ImmutableList<ContentTrait> immutableList = null;
                            String str = null;
                            Analytics analytics = null;
                            Widgets widgets = null;
                            String str2 = null;
                            CacheControlPolicy cacheControlPolicy = null;
                            Blueprint blueprint = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mContentTraitsParser.parse(jsonParser);
                                    }
                                    builder.pageContentTraits = immutableList;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        blueprint = this.mBlueprintParser.parse(jsonParser);
                                    }
                                    builder.blueprint = blueprint;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        cacheControlPolicy = this.mCacheControlPolicyParser.parse(jsonParser);
                                    }
                                    builder.cache = cacheControlPolicy;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str2;
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
                                        widgets = this.mWidgetsParser.parse(jsonParser);
                                    }
                                    builder.widgets = widgets;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 7:
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
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SectionV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SectionV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.cache, this, "cache");
                    JsonParsingUtils.checkNotNull(builder.widgets, this, "widgets");
                    return builder.build();
                }
            }
        }
    }
}
