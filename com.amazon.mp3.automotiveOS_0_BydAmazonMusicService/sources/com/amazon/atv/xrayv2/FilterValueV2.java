package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.xrayv2.FilterValueBase;
import com.amazon.atv.xrayv2.NavigationalActionBase;
import com.amazon.atv.xrayv2.PrefetchPolicy;
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
public class FilterValueV2 extends FilterValueBase {
    public final NavigationalActionBase link;

    private FilterValueV2(Builder builder) {
        super(builder);
        this.link = (NavigationalActionBase) Preconditions.checkNotNull(builder.link, "Unexpected null field: link");
    }

    @Override // com.amazon.atv.xrayv2.FilterValueBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FilterValueV2) {
            return super.equals(obj) && Objects.equal(this.link, ((FilterValueV2) obj).link);
        }
        return false;
    }

    @Override // com.amazon.atv.xrayv2.FilterValueBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.link);
    }

    @Override // com.amazon.atv.xrayv2.FilterValueBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("link", this.link).toString();
    }

    @NotThreadSafe
    public static class Builder extends FilterValueBase.Builder {
        public NavigationalActionBase link;

        public FilterValueV2 build() {
            return new FilterValueV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<FilterValueV2> {
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
        public FilterValueV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("FilterValueV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public FilterValueV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("FilterValueV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private FilterValueV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "FilterValueV2");
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
                                b = 6;
                            }
                            break;
                        case -1609594047:
                            if (next.equals("enabled")) {
                                b = 5;
                            }
                            break;
                        case -1498600233:
                            if (next.equals("currentlyApplied")) {
                                b = 7;
                            }
                            break;
                        case -700530204:
                            if (next.equals("expectedItemCount")) {
                                b = 8;
                            }
                            break;
                        case 3321850:
                            if (next.equals("link")) {
                                b = 1;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 2;
                            }
                            break;
                        case 111972721:
                            if (next.equals("value")) {
                                b = 4;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 3;
                            }
                            break;
                        case 738295305:
                            if (next.equals("prefetchPolicy")) {
                                b = 0;
                            }
                            break;
                    }
                    PrefetchPolicy prefetchPolicy = null;
                    Analytics analytics = null;
                    String str = null;
                    String str2 = null;
                    NavigationalActionBase navigationalActionBase = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                prefetchPolicy = this.mPrefetchPolicyParser.parse(jsonNode2);
                            }
                            builder.prefetchPolicy = prefetchPolicy;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                navigationalActionBase = this.mNavigationalActionBaseParser.parse(jsonNode2);
                            }
                            builder.link = navigationalActionBase;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.text = str2;
                            break;
                        case 3:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.value = str;
                            break;
                        case 5:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field enabled can't be null");
                            }
                            builder.enabled = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                analytics = this.mAnalyticsParser.parse(jsonNode2);
                            }
                            builder.analytics = analytics;
                            break;
                        case 7:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field currentlyApplied can't be null");
                            }
                            builder.currentlyApplied = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 8:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field expectedItemCount can't be null");
                            }
                            builder.expectedItemCount = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing FilterValueV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.link, this, "link");
            JsonParsingUtils.checkNotNull(builder.text, this, "text");
            JsonParsingUtils.checkNotNull(builder.value, this, "value");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private FilterValueV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 6;
                                    }
                                    break;
                                case -1609594047:
                                    if (currentName.equals("enabled")) {
                                        b = 5;
                                    }
                                    break;
                                case -1498600233:
                                    if (currentName.equals("currentlyApplied")) {
                                        b = 7;
                                    }
                                    break;
                                case -700530204:
                                    if (currentName.equals("expectedItemCount")) {
                                        b = 8;
                                    }
                                    break;
                                case 3321850:
                                    if (currentName.equals("link")) {
                                        b = 1;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 2;
                                    }
                                    break;
                                case 111972721:
                                    if (currentName.equals("value")) {
                                        b = 4;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 3;
                                    }
                                    break;
                                case 738295305:
                                    if (currentName.equals("prefetchPolicy")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            PrefetchPolicy prefetchPolicy = null;
                            Analytics analytics = null;
                            String str = null;
                            String str2 = null;
                            NavigationalActionBase navigationalActionBase = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        prefetchPolicy = this.mPrefetchPolicyParser.parse(jsonParser);
                                    }
                                    builder.prefetchPolicy = prefetchPolicy;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        navigationalActionBase = this.mNavigationalActionBaseParser.parse(jsonParser);
                                    }
                                    builder.link = navigationalActionBase;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str2;
                                    break;
                                case 3:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.value = str;
                                    break;
                                case 5:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field enabled can't be null");
                                    }
                                    builder.enabled = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 7:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field currentlyApplied can't be null");
                                    }
                                    builder.currentlyApplied = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 8:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field expectedItemCount can't be null");
                                    }
                                    builder.expectedItemCount = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing FilterValueV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.link, this, "link");
                    JsonParsingUtils.checkNotNull(builder.text, this, "text");
                    JsonParsingUtils.checkNotNull(builder.value, this, "value");
                    return builder.build();
                }
            }
        }
    }
}
