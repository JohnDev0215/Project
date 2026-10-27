package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.xrayv2.NavigationalActionBase;
import com.amazon.atv.xrayv2.PrefetchPolicy;
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
public class NavigationalAction extends NavigationalActionBase {
    public final Optional<String> cacheKey;
    public final LinkType linkType;
    public final Optional<String> nonSupportedText;
    public final Optional<ImmutableMap<String, String>> parameters;
    public final Optional<PrefetchPolicy> prefetchPolicy;

    private NavigationalAction(Builder builder) {
        super(builder);
        this.prefetchPolicy = Optional.fromNullable(builder.prefetchPolicy);
        this.parameters = Optional.fromNullable(builder.parameters);
        this.linkType = (LinkType) Preconditions.checkNotNull(builder.linkType, "Unexpected null field: linkType");
        this.cacheKey = Optional.fromNullable(builder.cacheKey);
        this.nonSupportedText = Optional.fromNullable(builder.nonSupportedText);
    }

    @Override // com.amazon.atv.xrayv2.NavigationalActionBase, com.amazon.atv.xrayv2.Action
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NavigationalAction)) {
            return false;
        }
        NavigationalAction navigationalAction = (NavigationalAction) obj;
        return super.equals(obj) && Objects.equal(this.prefetchPolicy, navigationalAction.prefetchPolicy) && Objects.equal(this.parameters, navigationalAction.parameters) && Objects.equal(this.linkType, navigationalAction.linkType) && Objects.equal(this.cacheKey, navigationalAction.cacheKey) && Objects.equal(this.nonSupportedText, navigationalAction.nonSupportedText);
    }

    @Override // com.amazon.atv.xrayv2.NavigationalActionBase, com.amazon.atv.xrayv2.Action
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.prefetchPolicy, this.parameters, this.linkType, this.cacheKey, this.nonSupportedText);
    }

    @Override // com.amazon.atv.xrayv2.NavigationalActionBase, com.amazon.atv.xrayv2.Action
    public String toString() {
        return MoreObjects.toStringHelper(this).add("prefetchPolicy", this.prefetchPolicy).add("parameters", this.parameters).add("linkType", this.linkType).add("cacheKey", this.cacheKey).add("nonSupportedText", this.nonSupportedText).toString();
    }

    @NotThreadSafe
    public static class Builder extends NavigationalActionBase.Builder {
        public String cacheKey;
        public LinkType linkType;
        public String nonSupportedText;
        public ImmutableMap<String, String> parameters;
        public PrefetchPolicy prefetchPolicy;

        public NavigationalAction build() {
            return new NavigationalAction(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<NavigationalAction> {
        private final EnumParser<ActionType> mActionTypeParser;
        private final Analytics.Parser mAnalyticsParser;
        private final SimpleParsers.StringParser mCacheKeyStringParser;
        private final EnumParser<LinkType> mLinkTypeParser;
        private final PrefetchPolicy.Parser mPrefetchPolicyParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPrefetchPolicyParser = new PrefetchPolicy.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mActionTypeParser = EnumParser.newParser(ActionType.class);
            this.mLinkTypeParser = EnumParser.newParser(LinkType.class);
            this.mCacheKeyStringParser = stringParser;
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public NavigationalAction parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("NavigationalAction:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public NavigationalAction parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("NavigationalAction:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private NavigationalAction parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Analytics analytics;
            PrefetchPolicy prefetchPolicy;
            String str;
            ActionType actionType;
            ImmutableMap<String, String> immutableMap;
            LinkType linkType;
            String str2;
            String str3;
            JsonParsingUtils.throwIfNotObject(jsonNode, "NavigationalAction");
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
                        case -433508483:
                            if (next.equals("cacheKey")) {
                                b = 6;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 2;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 3;
                            }
                            break;
                        case 54509870:
                            if (next.equals("nonSupportedText")) {
                                b = 8;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 7;
                            }
                            break;
                        case 458736106:
                            if (next.equals("parameters")) {
                                b = 4;
                            }
                            break;
                        case 738295305:
                            if (next.equals("prefetchPolicy")) {
                                b = 1;
                            }
                            break;
                        case 1194206804:
                            if (next.equals("linkType")) {
                                b = 5;
                            }
                            break;
                    }
                    analytics = null;
                    str3 = null;
                    str2 = null;
                    linkType = null;
                    immutableMap = null;
                    actionType = null;
                    str = null;
                    prefetchPolicy = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing NavigationalAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
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
                            prefetchPolicy = this.mPrefetchPolicyParser.parse(jsonNode2);
                        }
                        builder.prefetchPolicy = prefetchPolicy;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.text = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            actionType = (ActionType) this.mActionTypeParser.parse(jsonNode2);
                        }
                        builder.type = actionType;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mStringMapParser.parse(jsonNode2);
                        }
                        builder.parameters = immutableMap;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            linkType = (LinkType) this.mLinkTypeParser.parse(jsonNode2);
                        }
                        builder.linkType = linkType;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mCacheKeyStringParser.parse(jsonNode2);
                        }
                        builder.cacheKey = str2;
                        continue;
                    case 7:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        continue;
                        break;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.nonSupportedText = str3;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing NavigationalAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(builder.linkType, this, "linkType");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private NavigationalAction parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -433508483:
                                    if (currentName.equals("cacheKey")) {
                                        b = 6;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 2;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 3;
                                    }
                                    break;
                                case 54509870:
                                    if (currentName.equals("nonSupportedText")) {
                                        b = 8;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 7;
                                    }
                                    break;
                                case 458736106:
                                    if (currentName.equals("parameters")) {
                                        b = 4;
                                    }
                                    break;
                                case 738295305:
                                    if (currentName.equals("prefetchPolicy")) {
                                        b = 1;
                                    }
                                    break;
                                case 1194206804:
                                    if (currentName.equals("linkType")) {
                                        b = 5;
                                    }
                                    break;
                            }
                            Analytics analytics = null;
                            String str = null;
                            String str2 = null;
                            LinkType linkType = null;
                            ImmutableMap<String, String> immutableMap = null;
                            ActionType actionType = null;
                            String str3 = null;
                            PrefetchPolicy prefetchPolicy = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        prefetchPolicy = this.mPrefetchPolicyParser.parse(jsonParser);
                                    }
                                    builder.prefetchPolicy = prefetchPolicy;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str3;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        actionType = (ActionType) this.mActionTypeParser.parse(jsonParser);
                                    }
                                    builder.type = actionType;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.parameters = immutableMap;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        linkType = (LinkType) this.mLinkTypeParser.parse(jsonParser);
                                    }
                                    builder.linkType = linkType;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mCacheKeyStringParser.parse(jsonParser);
                                    }
                                    builder.cacheKey = str2;
                                    continue;
                                case 7:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    continue;
                                    break;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.nonSupportedText = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing NavigationalAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing NavigationalAction so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
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
