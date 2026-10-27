package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.PrefetchPolicy;
import com.amazon.atv.discovery.SortOptionBase;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class SortOption extends SortOptionBase {
    public final ImmutableMap<String, String> parameters;

    private SortOption(Builder builder) {
        super(builder);
        this.parameters = (ImmutableMap) Preconditions.checkNotNull(builder.parameters, "Unexpected null field: parameters");
    }

    @Override // com.amazon.atv.discovery.SortOptionBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SortOption) {
            return super.equals(obj) && Objects.equal(this.parameters, ((SortOption) obj).parameters);
        }
        return false;
    }

    @Override // com.amazon.atv.discovery.SortOptionBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.parameters);
    }

    @Override // com.amazon.atv.discovery.SortOptionBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("parameters", this.parameters).toString();
    }

    @NotThreadSafe
    public static class Builder extends SortOptionBase.Builder {
        public ImmutableMap<String, String> parameters;

        public SortOption build() {
            return new SortOption(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SortOption> {
        private final Analytics.Parser mAnalyticsParser;
        private final PrefetchPolicy.Parser mPrefetchPolicyParser;
        private final EnumParser<SortOrder> mSortOrderParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSortOrderParser = EnumParser.newParser(SortOrder.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mPrefetchPolicyParser = new PrefetchPolicy.Parser(objectMapper);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SortOption parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SortOption:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SortOption parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SortOption:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SortOption parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SortOption");
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
                                b = 5;
                            }
                            break;
                        case -1498600233:
                            if (next.equals("currentlyApplied")) {
                                b = 6;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 0;
                            }
                            break;
                        case 106006350:
                            if (next.equals("order")) {
                                b = 2;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 1;
                            }
                            break;
                        case 458736106:
                            if (next.equals("parameters")) {
                                b = 4;
                            }
                            break;
                        case 738295305:
                            if (next.equals("prefetchPolicy")) {
                                b = 3;
                            }
                            break;
                    }
                    String str = null;
                    Analytics analytics = null;
                    ImmutableMap<String, String> immutableMap = null;
                    PrefetchPolicy prefetchPolicy = null;
                    SortOrder sortOrder = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.text = str;
                            break;
                        case 1:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                sortOrder = (SortOrder) this.mSortOrderParser.parse(jsonNode2);
                            }
                            builder.order = sortOrder;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                prefetchPolicy = this.mPrefetchPolicyParser.parse(jsonNode2);
                            }
                            builder.prefetchPolicy = prefetchPolicy;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                immutableMap = this.mStringMapParser.parse(jsonNode2);
                            }
                            builder.parameters = immutableMap;
                            break;
                        case 5:
                            if (!jsonNode2.isNull()) {
                                analytics = this.mAnalyticsParser.parse(jsonNode2);
                            }
                            builder.analytics = analytics;
                            break;
                        case 6:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field currentlyApplied can't be null");
                            }
                            builder.currentlyApplied = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SortOption so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.text, this, "text");
            JsonParsingUtils.checkNotNull(builder.order, this, "order");
            JsonParsingUtils.checkNotNull(builder.parameters, this, "parameters");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SortOption parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 5;
                                    }
                                    break;
                                case -1498600233:
                                    if (currentName.equals("currentlyApplied")) {
                                        b = 6;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 0;
                                    }
                                    break;
                                case 106006350:
                                    if (currentName.equals("order")) {
                                        b = 2;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 1;
                                    }
                                    break;
                                case 458736106:
                                    if (currentName.equals("parameters")) {
                                        b = 4;
                                    }
                                    break;
                                case 738295305:
                                    if (currentName.equals("prefetchPolicy")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            Analytics analytics = null;
                            ImmutableMap<String, String> immutableMap = null;
                            PrefetchPolicy prefetchPolicy = null;
                            SortOrder sortOrder = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str;
                                    break;
                                case 1:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        sortOrder = (SortOrder) this.mSortOrderParser.parse(jsonParser);
                                    }
                                    builder.order = sortOrder;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        prefetchPolicy = this.mPrefetchPolicyParser.parse(jsonParser);
                                    }
                                    builder.prefetchPolicy = prefetchPolicy;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.parameters = immutableMap;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 6:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field currentlyApplied can't be null");
                                    }
                                    builder.currentlyApplied = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SortOption so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.text, this, "text");
                    JsonParsingUtils.checkNotNull(builder.order, this, "order");
                    JsonParsingUtils.checkNotNull(builder.parameters, this, "parameters");
                    return builder.build();
                }
            }
        }
    }
}
