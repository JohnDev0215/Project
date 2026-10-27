package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.NavigationalActionBase;
import com.amazon.atv.discovery.PrefetchPolicy;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
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
public class NavigationalActionV2 extends NavigationalActionBase {
    public final Optional<String> api;
    public final Optional<String> cacheKey;
    public final LinkType linkType;
    public final Optional<String> nonSupportedText;
    public final Optional<ImmutableMap<String, String>> parameters;
    public final Optional<PrefetchPolicy> prefetchPolicy;

    private NavigationalActionV2(Builder builder) {
        super(builder);
        this.parameters = Optional.fromNullable(builder.parameters);
        this.cacheKey = Optional.fromNullable(builder.cacheKey);
        this.nonSupportedText = Optional.fromNullable(builder.nonSupportedText);
        this.api = Optional.fromNullable(builder.api);
        this.linkType = (LinkType) Preconditions.checkNotNull(builder.linkType, "Unexpected null field: linkType");
        this.prefetchPolicy = Optional.fromNullable(builder.prefetchPolicy);
    }

    @Override // com.amazon.atv.discovery.NavigationalActionBase, com.amazon.atv.discovery.Action
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NavigationalActionV2)) {
            return false;
        }
        NavigationalActionV2 navigationalActionV2 = (NavigationalActionV2) obj;
        return super.equals(obj) && Objects.equal(this.parameters, navigationalActionV2.parameters) && Objects.equal(this.cacheKey, navigationalActionV2.cacheKey) && Objects.equal(this.nonSupportedText, navigationalActionV2.nonSupportedText) && Objects.equal(this.api, navigationalActionV2.api) && Objects.equal(this.linkType, navigationalActionV2.linkType) && Objects.equal(this.prefetchPolicy, navigationalActionV2.prefetchPolicy);
    }

    @Override // com.amazon.atv.discovery.NavigationalActionBase, com.amazon.atv.discovery.Action
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.parameters, this.cacheKey, this.nonSupportedText, this.api, this.linkType, this.prefetchPolicy);
    }

    @Override // com.amazon.atv.discovery.NavigationalActionBase, com.amazon.atv.discovery.Action
    public String toString() {
        return MoreObjects.toStringHelper(this).add("parameters", this.parameters).add("cacheKey", this.cacheKey).add("nonSupportedText", this.nonSupportedText).add("api", this.api).add("linkType", this.linkType).add("prefetchPolicy", this.prefetchPolicy).toString();
    }

    @NotThreadSafe
    public static class Builder extends NavigationalActionBase.Builder {
        public String api;
        public String cacheKey;
        public LinkType linkType;
        public String nonSupportedText;
        public ImmutableMap<String, String> parameters;
        public PrefetchPolicy prefetchPolicy;

        public NavigationalActionV2 build() {
            return new NavigationalActionV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<NavigationalActionV2> {
        private final EnumParser<ActionType> mActionTypeParser;
        private final Analytics.Parser mAnalyticsParser;
        private final SimpleParsers.StringParser mCacheKeyStringParser;
        private final EnumParser<LinkType> mLinkTypeParser;
        private final PrefetchPolicy.Parser mPrefetchPolicyParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mActionTypeParser = EnumParser.newParser(ActionType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mPrefetchPolicyParser = new PrefetchPolicy.Parser(objectMapper);
            this.mCacheKeyStringParser = stringParser;
            this.mStringParser = stringParser;
            this.mLinkTypeParser = EnumParser.newParser(LinkType.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public NavigationalActionV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("NavigationalActionV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public NavigationalActionV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("NavigationalActionV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private NavigationalActionV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ImmutableMap<String, String> immutableMap;
            String str;
            String str2;
            String str3;
            Analytics analytics;
            ActionType actionType;
            String str4;
            LinkType linkType;
            PrefetchPolicy prefetchPolicy;
            JsonParsingUtils.throwIfNotObject(jsonNode, "NavigationalActionV2");
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
                                b = 4;
                            }
                            break;
                        case -433508483:
                            if (next.equals("cacheKey")) {
                                b = 1;
                            }
                            break;
                        case 96794:
                            if (next.equals("api")) {
                                b = 6;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 3;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 5;
                            }
                            break;
                        case 54509870:
                            if (next.equals("nonSupportedText")) {
                                b = 2;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 9;
                            }
                            break;
                        case 458736106:
                            if (next.equals("parameters")) {
                                b = 0;
                            }
                            break;
                        case 738295305:
                            if (next.equals("prefetchPolicy")) {
                                b = 8;
                            }
                            break;
                        case 1194206804:
                            if (next.equals("linkType")) {
                                b = 7;
                            }
                            break;
                    }
                    immutableMap = null;
                    prefetchPolicy = null;
                    linkType = null;
                    str4 = null;
                    actionType = null;
                    analytics = null;
                    str3 = null;
                    str2 = null;
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing NavigationalActionV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mStringMapParser.parse(jsonNode2);
                        }
                        builder.parameters = immutableMap;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            str = this.mCacheKeyStringParser.parse(jsonNode2);
                        }
                        builder.cacheKey = str;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.nonSupportedText = str2;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.text = str3;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            actionType = (ActionType) this.mActionTypeParser.parse(jsonNode2);
                        }
                        builder.type = actionType;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.api = str4;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            linkType = (LinkType) this.mLinkTypeParser.parse(jsonNode2);
                        }
                        builder.linkType = linkType;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            prefetchPolicy = this.mPrefetchPolicyParser.parse(jsonNode2);
                        }
                        builder.prefetchPolicy = prefetchPolicy;
                        continue;
                    case 9:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        continue;
                        break;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing NavigationalActionV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(builder.linkType, this, "linkType");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private NavigationalActionV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 4;
                                    }
                                    break;
                                case -433508483:
                                    if (currentName.equals("cacheKey")) {
                                        b = 1;
                                    }
                                    break;
                                case 96794:
                                    if (currentName.equals("api")) {
                                        b = 6;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 3;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 5;
                                    }
                                    break;
                                case 54509870:
                                    if (currentName.equals("nonSupportedText")) {
                                        b = 2;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 9;
                                    }
                                    break;
                                case 458736106:
                                    if (currentName.equals("parameters")) {
                                        b = 0;
                                    }
                                    break;
                                case 738295305:
                                    if (currentName.equals("prefetchPolicy")) {
                                        b = 8;
                                    }
                                    break;
                                case 1194206804:
                                    if (currentName.equals("linkType")) {
                                        b = 7;
                                    }
                                    break;
                            }
                            ImmutableMap<String, String> immutableMap = null;
                            PrefetchPolicy prefetchPolicy = null;
                            LinkType linkType = null;
                            String str = null;
                            ActionType actionType = null;
                            Analytics analytics = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.parameters = immutableMap;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mCacheKeyStringParser.parse(jsonParser);
                                    }
                                    builder.cacheKey = str4;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.nonSupportedText = str3;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        actionType = (ActionType) this.mActionTypeParser.parse(jsonParser);
                                    }
                                    builder.type = actionType;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.api = str;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        linkType = (LinkType) this.mLinkTypeParser.parse(jsonParser);
                                    }
                                    builder.linkType = linkType;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        prefetchPolicy = this.mPrefetchPolicyParser.parse(jsonParser);
                                    }
                                    builder.prefetchPolicy = prefetchPolicy;
                                    continue;
                                case 9:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    continue;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing NavigationalActionV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing NavigationalActionV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(builder.linkType, this, "linkType");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    return builder.build();
                }
            }
        }
    }
}
