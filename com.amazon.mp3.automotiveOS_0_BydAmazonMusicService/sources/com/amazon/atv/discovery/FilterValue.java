package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.PrefetchPolicy;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
public class FilterValue {
    public final Optional<Analytics> analytics;
    public final boolean currentlyApplied;
    public final Optional<String> debugAttribute;
    public final boolean enabled;
    public final int expectedItemCount;
    public final String id;
    public final ImmutableMap<String, String> parameters;
    public final Optional<PrefetchPolicy> prefetchPolicy;
    public final Optional<String> refTag;
    public final String text;
    public final String value;

    private FilterValue(Builder builder) {
        this.analytics = Optional.fromNullable(builder.analytics);
        this.parameters = (ImmutableMap) Preconditions.checkNotNull(builder.parameters, "Unexpected null field: parameters");
        this.prefetchPolicy = Optional.fromNullable(builder.prefetchPolicy);
        this.expectedItemCount = builder.expectedItemCount;
        this.value = (String) Preconditions.checkNotNull(builder.value, "Unexpected null field: value");
        this.id = (String) Preconditions.checkNotNull(builder.id, "Unexpected null field: id");
        this.refTag = Optional.fromNullable(builder.refTag);
        this.debugAttribute = Optional.fromNullable(builder.debugAttribute);
        this.currentlyApplied = builder.currentlyApplied;
        this.text = (String) Preconditions.checkNotNull(builder.text, "Unexpected null field: text");
        this.enabled = builder.enabled;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FilterValue)) {
            return false;
        }
        FilterValue filterValue = (FilterValue) obj;
        return Objects.equal(this.analytics, filterValue.analytics) && Objects.equal(this.parameters, filterValue.parameters) && Objects.equal(this.prefetchPolicy, filterValue.prefetchPolicy) && Objects.equal(Integer.valueOf(this.expectedItemCount), Integer.valueOf(filterValue.expectedItemCount)) && Objects.equal(this.value, filterValue.value) && Objects.equal(this.id, filterValue.id) && Objects.equal(this.refTag, filterValue.refTag) && Objects.equal(this.debugAttribute, filterValue.debugAttribute) && Objects.equal(Boolean.valueOf(this.currentlyApplied), Boolean.valueOf(filterValue.currentlyApplied)) && Objects.equal(this.text, filterValue.text) && Objects.equal(Boolean.valueOf(this.enabled), Boolean.valueOf(filterValue.enabled));
    }

    public int hashCode() {
        return Objects.hashCode(this.analytics, this.parameters, this.prefetchPolicy, Integer.valueOf(this.expectedItemCount), this.value, this.id, this.refTag, this.debugAttribute, Boolean.valueOf(this.currentlyApplied), this.text, Boolean.valueOf(this.enabled));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("analytics", this.analytics).add("parameters", this.parameters).add("prefetchPolicy", this.prefetchPolicy).add("expectedItemCount", this.expectedItemCount).add("value", this.value).add("id", this.id).add("refTag", this.refTag).add("debugAttribute", this.debugAttribute).add("currentlyApplied", this.currentlyApplied).add("text", this.text).add("enabled", this.enabled).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Analytics analytics;
        public boolean currentlyApplied;
        public String debugAttribute;
        public boolean enabled;
        public int expectedItemCount;
        public String id;
        public ImmutableMap<String, String> parameters;
        public PrefetchPolicy prefetchPolicy;
        public String refTag;
        public String text;
        public String value;

        public FilterValue build() {
            return new FilterValue(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<FilterValue> {
        private final Analytics.Parser mAnalyticsParser;
        private final PrefetchPolicy.Parser mPrefetchPolicyParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mPrefetchPolicyParser = new PrefetchPolicy.Parser(objectMapper);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public FilterValue parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("FilterValue:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public FilterValue parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("FilterValue:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private FilterValue parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "FilterValue");
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
                        case -1609594047:
                            if (next.equals("enabled")) {
                                b = 10;
                            }
                            break;
                        case -1498600233:
                            if (next.equals("currentlyApplied")) {
                                b = 8;
                            }
                            break;
                        case -934845945:
                            if (next.equals("refTag")) {
                                b = 6;
                            }
                            break;
                        case -700530204:
                            if (next.equals("expectedItemCount")) {
                                b = 3;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 5;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 9;
                            }
                            break;
                        case 111972721:
                            if (next.equals("value")) {
                                b = 4;
                            }
                            break;
                        case 346136969:
                            if (next.equals("debugAttribute")) {
                                b = 7;
                            }
                            break;
                        case 458736106:
                            if (next.equals("parameters")) {
                                b = 1;
                            }
                            break;
                        case 738295305:
                            if (next.equals("prefetchPolicy")) {
                                b = 2;
                            }
                            break;
                    }
                    Analytics analytics = null;
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    String str4 = null;
                    String str5 = null;
                    PrefetchPolicy prefetchPolicy = null;
                    ImmutableMap<String, String> immutableMap = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                analytics = this.mAnalyticsParser.parse(jsonNode2);
                            }
                            builder.analytics = analytics;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                immutableMap = this.mStringMapParser.parse(jsonNode2);
                            }
                            builder.parameters = immutableMap;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                prefetchPolicy = this.mPrefetchPolicyParser.parse(jsonNode2);
                            }
                            builder.prefetchPolicy = prefetchPolicy;
                            break;
                        case 3:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field expectedItemCount can't be null");
                            }
                            builder.expectedItemCount = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                str5 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.value = str5;
                            break;
                        case 5:
                            if (!jsonNode2.isNull()) {
                                str4 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.id = str4;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                str3 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.refTag = str3;
                            break;
                        case 7:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.debugAttribute = str2;
                            break;
                        case 8:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field currentlyApplied can't be null");
                            }
                            builder.currentlyApplied = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 9:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.text = str;
                            break;
                        case 10:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field enabled can't be null");
                            }
                            builder.enabled = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing FilterValue so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.parameters, this, "parameters");
            JsonParsingUtils.checkNotNull(builder.value, this, "value");
            JsonParsingUtils.checkNotNull(builder.id, this, "id");
            JsonParsingUtils.checkNotNull(builder.text, this, "text");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private FilterValue parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1609594047:
                                    if (currentName.equals("enabled")) {
                                        b = 10;
                                    }
                                    break;
                                case -1498600233:
                                    if (currentName.equals("currentlyApplied")) {
                                        b = 8;
                                    }
                                    break;
                                case -934845945:
                                    if (currentName.equals("refTag")) {
                                        b = 6;
                                    }
                                    break;
                                case -700530204:
                                    if (currentName.equals("expectedItemCount")) {
                                        b = 3;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 5;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 9;
                                    }
                                    break;
                                case 111972721:
                                    if (currentName.equals("value")) {
                                        b = 4;
                                    }
                                    break;
                                case 346136969:
                                    if (currentName.equals("debugAttribute")) {
                                        b = 7;
                                    }
                                    break;
                                case 458736106:
                                    if (currentName.equals("parameters")) {
                                        b = 1;
                                    }
                                    break;
                                case 738295305:
                                    if (currentName.equals("prefetchPolicy")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            Analytics analytics = null;
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            String str5 = null;
                            PrefetchPolicy prefetchPolicy = null;
                            ImmutableMap<String, String> immutableMap = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.parameters = immutableMap;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        prefetchPolicy = this.mPrefetchPolicyParser.parse(jsonParser);
                                    }
                                    builder.prefetchPolicy = prefetchPolicy;
                                    break;
                                case 3:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field expectedItemCount can't be null");
                                    }
                                    builder.expectedItemCount = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.value = str5;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str4;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.refTag = str3;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttribute = str2;
                                    break;
                                case 8:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field currentlyApplied can't be null");
                                    }
                                    builder.currentlyApplied = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str;
                                    break;
                                case 10:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field enabled can't be null");
                                    }
                                    builder.enabled = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing FilterValue so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.parameters, this, "parameters");
                    JsonParsingUtils.checkNotNull(builder.value, this, "value");
                    JsonParsingUtils.checkNotNull(builder.id, this, "id");
                    JsonParsingUtils.checkNotNull(builder.text, this, "text");
                    return builder.build();
                }
            }
        }
    }
}
