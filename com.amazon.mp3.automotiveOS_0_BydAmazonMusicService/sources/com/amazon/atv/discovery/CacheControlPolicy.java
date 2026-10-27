package com.amazon.atv.discovery;

import com.amazon.atv.discovery.CacheRefreshEvent;
import com.amazon.atv.discovery.TimeToLive;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
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
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class CacheControlPolicy {
    public final Optional<ImmutableList<CacheRefreshEvent>> refreshEvents;
    public final TimeToLive ttl;
    public final String type;
    public final int version;

    private CacheControlPolicy(Builder builder) {
        this.type = (String) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
        this.refreshEvents = Optional.fromNullable(builder.refreshEvents);
        this.ttl = (TimeToLive) Preconditions.checkNotNull(builder.ttl, "Unexpected null field: ttl");
        this.version = builder.version;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CacheControlPolicy)) {
            return false;
        }
        CacheControlPolicy cacheControlPolicy = (CacheControlPolicy) obj;
        return Objects.equal(this.type, cacheControlPolicy.type) && Objects.equal(this.refreshEvents, cacheControlPolicy.refreshEvents) && Objects.equal(this.ttl, cacheControlPolicy.ttl) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(cacheControlPolicy.version));
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.refreshEvents, this.ttl, Integer.valueOf(this.version));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("refreshEvents", this.refreshEvents).add("ttl", this.ttl).add("version", this.version).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<CacheRefreshEvent> refreshEvents;
        public TimeToLive ttl;
        public String type;
        public int version;

        public CacheControlPolicy build() {
            return new CacheControlPolicy(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CacheControlPolicy> {
        private final ListParser<CacheRefreshEvent> mCacheRefreshEventsParser;
        private final SimpleParsers.StringParser mStringParser;
        private final TimeToLive.Parser mTimeToLiveParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTimeToLiveParser = new TimeToLive.Parser(objectMapper);
            this.mCacheRefreshEventsParser = ListParser.newParser(new CacheRefreshEvent.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CacheControlPolicy parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CacheControlPolicy:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CacheControlPolicy parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CacheControlPolicy:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CacheControlPolicy parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "CacheControlPolicy");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case 115180:
                            if (next.equals("ttl")) {
                                b = 2;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 0;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 3;
                            }
                            break;
                        case 2120126420:
                            if (next.equals("refreshEvents")) {
                                b = 1;
                            }
                            break;
                    }
                    TimeToLive timeToLive = null;
                    String str = null;
                    ImmutableList<CacheRefreshEvent> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.type = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mCacheRefreshEventsParser.parse(jsonNode2);
                        }
                        builder.refreshEvents = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            timeToLive = this.mTimeToLiveParser.parse(jsonNode2);
                        }
                        builder.ttl = timeToLive;
                    } else if (b == 3) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CacheControlPolicy so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(builder.ttl, this, "ttl");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CacheControlPolicy parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case 115180:
                                    if (currentName.equals("ttl")) {
                                        b = 2;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 0;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 3;
                                    }
                                    break;
                                case 2120126420:
                                    if (currentName.equals("refreshEvents")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            TimeToLive timeToLive = null;
                            String str = null;
                            ImmutableList<CacheRefreshEvent> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.type = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mCacheRefreshEventsParser.parse(jsonParser);
                                }
                                builder.refreshEvents = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    timeToLive = this.mTimeToLiveParser.parse(jsonParser);
                                }
                                builder.ttl = timeToLive;
                            } else if (b == 3) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field version can't be null");
                                }
                                builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CacheControlPolicy so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(builder.ttl, this, "ttl");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    return builder.build();
                }
            }
        }
    }
}
