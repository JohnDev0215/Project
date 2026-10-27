package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.xrayv2.NavigationalActionBase;
import com.amazon.atv.xrayv2.PrefetchPolicy;
import com.amazon.atv.xrayv2.SortBase;
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
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class SortV2 extends SortBase {
    public final Optional<Analytics> analytics;
    public final boolean currentlyApplied;
    public final NavigationalActionBase link;
    public final Optional<PrefetchPolicy> prefetchPolicy;
    public final String sortKey;

    private SortV2(Builder builder) {
        super(builder);
        this.link = (NavigationalActionBase) Preconditions.checkNotNull(builder.link, "Unexpected null field: link");
        this.analytics = Optional.fromNullable(builder.analytics);
        this.currentlyApplied = builder.currentlyApplied;
        this.sortKey = (String) Preconditions.checkNotNull(builder.sortKey, "Unexpected null field: sortKey");
        this.prefetchPolicy = Optional.fromNullable(builder.prefetchPolicy);
    }

    @Override // com.amazon.atv.xrayv2.SortBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SortV2)) {
            return false;
        }
        SortV2 sortV2 = (SortV2) obj;
        return super.equals(obj) && Objects.equal(this.link, sortV2.link) && Objects.equal(this.analytics, sortV2.analytics) && Objects.equal(Boolean.valueOf(this.currentlyApplied), Boolean.valueOf(sortV2.currentlyApplied)) && Objects.equal(this.sortKey, sortV2.sortKey) && Objects.equal(this.prefetchPolicy, sortV2.prefetchPolicy);
    }

    @Override // com.amazon.atv.xrayv2.SortBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.link, this.analytics, Boolean.valueOf(this.currentlyApplied), this.sortKey, this.prefetchPolicy);
    }

    @Override // com.amazon.atv.xrayv2.SortBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("link", this.link).add("analytics", this.analytics).add("currentlyApplied", this.currentlyApplied).add("sortKey", this.sortKey).add("prefetchPolicy", this.prefetchPolicy).toString();
    }

    @NotThreadSafe
    public static class Builder extends SortBase.Builder {
        public Analytics analytics;
        public boolean currentlyApplied;
        public NavigationalActionBase link;
        public PrefetchPolicy prefetchPolicy;
        public String sortKey;

        public SortV2 build() {
            return new SortV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SortV2> {
        private final Analytics.Parser mAnalyticsParser;
        private final NavigationalActionBase.Parser mNavigationalActionBaseParser;
        private final PrefetchPolicy.Parser mPrefetchPolicyParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPrefetchPolicyParser = new PrefetchPolicy.Parser(objectMapper);
            this.mNavigationalActionBaseParser = new NavigationalActionBase.Parser(objectMapper);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SortV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SortV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SortV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SortV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SortV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SortV2");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2024610847:
                            if (next.equals("sortKey")) {
                                b = 5;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 3;
                            }
                            break;
                        case -1498600233:
                            if (next.equals("currentlyApplied")) {
                                b = 4;
                            }
                            break;
                        case 3321850:
                            if (next.equals("link")) {
                                b = 0;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 1;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 2;
                            }
                            break;
                        case 738295305:
                            if (next.equals("prefetchPolicy")) {
                                b = 6;
                            }
                            break;
                    }
                    NavigationalActionBase navigationalActionBase = null;
                    PrefetchPolicy prefetchPolicy = null;
                    String str = null;
                    Analytics analytics = null;
                    String str2 = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                navigationalActionBase = this.mNavigationalActionBaseParser.parse(jsonNode2);
                            }
                            builder.link = navigationalActionBase;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.text = str2;
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
                                analytics = this.mAnalyticsParser.parse(jsonNode2);
                            }
                            builder.analytics = analytics;
                            break;
                        case 4:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field currentlyApplied can't be null");
                            }
                            builder.currentlyApplied = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 5:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.sortKey = str;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                prefetchPolicy = this.mPrefetchPolicyParser.parse(jsonNode2);
                            }
                            builder.prefetchPolicy = prefetchPolicy;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SortV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.link, this, "link");
            JsonParsingUtils.checkNotNull(builder.text, this, "text");
            JsonParsingUtils.checkNotNull(builder.sortKey, this, "sortKey");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SortV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2024610847:
                                    if (currentName.equals("sortKey")) {
                                        b = 5;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 3;
                                    }
                                    break;
                                case -1498600233:
                                    if (currentName.equals("currentlyApplied")) {
                                        b = 4;
                                    }
                                    break;
                                case 3321850:
                                    if (currentName.equals("link")) {
                                        b = 0;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 1;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 2;
                                    }
                                    break;
                                case 738295305:
                                    if (currentName.equals("prefetchPolicy")) {
                                        b = 6;
                                    }
                                    break;
                            }
                            NavigationalActionBase navigationalActionBase = null;
                            PrefetchPolicy prefetchPolicy = null;
                            String str = null;
                            Analytics analytics = null;
                            String str2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        navigationalActionBase = this.mNavigationalActionBaseParser.parse(jsonParser);
                                    }
                                    builder.link = navigationalActionBase;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str2;
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
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 4:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field currentlyApplied can't be null");
                                    }
                                    builder.currentlyApplied = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.sortKey = str;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        prefetchPolicy = this.mPrefetchPolicyParser.parse(jsonParser);
                                    }
                                    builder.prefetchPolicy = prefetchPolicy;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SortV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.link, this, "link");
                    JsonParsingUtils.checkNotNull(builder.text, this, "text");
                    JsonParsingUtils.checkNotNull(builder.sortKey, this, "sortKey");
                    return builder.build();
                }
            }
        }
    }
}
