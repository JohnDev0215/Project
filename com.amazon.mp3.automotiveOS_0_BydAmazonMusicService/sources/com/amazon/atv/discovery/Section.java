package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.CacheControlPolicy;
import com.amazon.atv.discovery.Collections;
import com.amazon.atv.discovery.SectionBase;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
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
public class Section extends SectionBase {
    public final Collections collections;
    public final int initialPageSize;
    public final int totalCollections;

    private Section(Builder builder) {
        super(builder);
        this.initialPageSize = builder.initialPageSize;
        this.collections = (Collections) Preconditions.checkNotNull(builder.collections, "Unexpected null field: collections");
        this.totalCollections = builder.totalCollections;
    }

    @Override // com.amazon.atv.discovery.SectionBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Section)) {
            return false;
        }
        Section section = (Section) obj;
        return super.equals(obj) && Objects.equal(Integer.valueOf(this.initialPageSize), Integer.valueOf(section.initialPageSize)) && Objects.equal(this.collections, section.collections) && Objects.equal(Integer.valueOf(this.totalCollections), Integer.valueOf(section.totalCollections));
    }

    @Override // com.amazon.atv.discovery.SectionBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), Integer.valueOf(this.initialPageSize), this.collections, Integer.valueOf(this.totalCollections));
    }

    @Override // com.amazon.atv.discovery.SectionBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("initialPageSize", this.initialPageSize).add("collections", this.collections).add("totalCollections", this.totalCollections).toString();
    }

    @NotThreadSafe
    public static class Builder extends SectionBase.Builder {
        public Collections collections;
        public int initialPageSize;
        public int totalCollections;

        public Section build() {
            return new Section(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Section> {
        private final Analytics.Parser mAnalyticsParser;
        private final CacheControlPolicy.Parser mCacheControlPolicyParser;
        private final Collections.Parser mCollectionsParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mCacheControlPolicyParser = new CacheControlPolicy.Parser(objectMapper);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mCollectionsParser = new Collections.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Section parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Section:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Section parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Section:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Section parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Section");
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
                                b = 6;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 1;
                            }
                            break;
                        case -1642328428:
                            if (next.equals("initialPageSize")) {
                                b = 0;
                            }
                            break;
                        case -180617295:
                            if (next.equals("totalCollections")) {
                                b = 5;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 4;
                            }
                            break;
                        case 94416770:
                            if (next.equals("cache")) {
                                b = 7;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 2;
                            }
                            break;
                        case 1853891989:
                            if (next.equals("collections")) {
                                b = 3;
                            }
                            break;
                    }
                    Analytics analytics = null;
                    CacheControlPolicy cacheControlPolicy = null;
                    String str = null;
                    String str2 = null;
                    Collections collections = null;
                    switch (b) {
                        case 0:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field initialPageSize can't be null");
                            }
                            builder.initialPageSize = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                analytics = this.mAnalyticsParser.parse(jsonNode2);
                            }
                            builder.analytics = analytics;
                            break;
                        case 2:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                collections = this.mCollectionsParser.parse(jsonNode2);
                            }
                            builder.collections = collections;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.text = str2;
                            break;
                        case 5:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field totalCollections can't be null");
                            }
                            builder.totalCollections = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.subtext = str;
                            break;
                        case 7:
                            if (!jsonNode2.isNull()) {
                                cacheControlPolicy = this.mCacheControlPolicyParser.parse(jsonNode2);
                            }
                            builder.cache = cacheControlPolicy;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Section so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.initialPageSize), this, "initialPageSize");
            JsonParsingUtils.checkNotNull(builder.collections, this, "collections");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.totalCollections), this, "totalCollections");
            JsonParsingUtils.checkNotNull(builder.cache, this, "cache");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Section parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 6;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 1;
                                    }
                                    break;
                                case -1642328428:
                                    if (currentName.equals("initialPageSize")) {
                                        b = 0;
                                    }
                                    break;
                                case -180617295:
                                    if (currentName.equals("totalCollections")) {
                                        b = 5;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 4;
                                    }
                                    break;
                                case 94416770:
                                    if (currentName.equals("cache")) {
                                        b = 7;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 2;
                                    }
                                    break;
                                case 1853891989:
                                    if (currentName.equals("collections")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            Analytics analytics = null;
                            CacheControlPolicy cacheControlPolicy = null;
                            String str = null;
                            String str2 = null;
                            Collections collections = null;
                            switch (b) {
                                case 0:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field initialPageSize can't be null");
                                    }
                                    builder.initialPageSize = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 2:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        collections = this.mCollectionsParser.parse(jsonParser);
                                    }
                                    builder.collections = collections;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str2;
                                    break;
                                case 5:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field totalCollections can't be null");
                                    }
                                    builder.totalCollections = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.subtext = str;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        cacheControlPolicy = this.mCacheControlPolicyParser.parse(jsonParser);
                                    }
                                    builder.cache = cacheControlPolicy;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Section so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.initialPageSize), this, "initialPageSize");
                    JsonParsingUtils.checkNotNull(builder.collections, this, "collections");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.totalCollections), this, "totalCollections");
                    JsonParsingUtils.checkNotNull(builder.cache, this, "cache");
                    return builder.build();
                }
            }
        }
    }
}
