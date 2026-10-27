package com.amazon.avtitleactionaggregationservice.model.playback.refreshenvelope;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.DebugMode;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class RefreshPlaybackEnvelopeRequest {
    public final Optional<ImmutableList<DebugMode>> enabledDebugModes;
    public final ImmutableMap<String, String> identifiers;
    public final Optional<ImmutableMap<String, String>> weblabOverrides;

    private RefreshPlaybackEnvelopeRequest(Builder builder) {
        this.identifiers = (ImmutableMap) Preconditions.checkNotNull(builder.identifiers, "Unexpected null field: identifiers");
        this.enabledDebugModes = Optional.fromNullable(builder.enabledDebugModes);
        this.weblabOverrides = Optional.fromNullable(builder.weblabOverrides);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RefreshPlaybackEnvelopeRequest)) {
            return false;
        }
        RefreshPlaybackEnvelopeRequest refreshPlaybackEnvelopeRequest = (RefreshPlaybackEnvelopeRequest) obj;
        return Objects.equal(this.identifiers, refreshPlaybackEnvelopeRequest.identifiers) && Objects.equal(this.enabledDebugModes, refreshPlaybackEnvelopeRequest.enabledDebugModes) && Objects.equal(this.weblabOverrides, refreshPlaybackEnvelopeRequest.weblabOverrides);
    }

    public int hashCode() {
        return Objects.hashCode(this.identifiers, this.enabledDebugModes, this.weblabOverrides);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("identifiers", this.identifiers).add("enabledDebugModes", this.enabledDebugModes).add("weblabOverrides", this.weblabOverrides).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<DebugMode> enabledDebugModes;
        public ImmutableMap<String, String> identifiers;
        public ImmutableMap<String, String> weblabOverrides;

        public RefreshPlaybackEnvelopeRequest build() {
            return new RefreshPlaybackEnvelopeRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RefreshPlaybackEnvelopeRequest> {
        private final ListParser<DebugMode> mEnabledDebugModesParser;
        private final MapParser<String, String> mRefreshIdentifiersParser;
        private final MapParser<String, String> mWeblabOverridesParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mEnabledDebugModesParser = ListParser.newParser(EnumParser.newParser(DebugMode.class));
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mRefreshIdentifiersParser = MapParser.newParser(stringParser, stringParser);
            this.mWeblabOverridesParser = MapParser.newParser(stringParser, stringParser);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RefreshPlaybackEnvelopeRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RefreshPlaybackEnvelopeRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RefreshPlaybackEnvelopeRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RefreshPlaybackEnvelopeRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private RefreshPlaybackEnvelopeRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RefreshPlaybackEnvelopeRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1474512786) {
                        if (iHashCode != 479296190) {
                            if (iHashCode == 1368189162 && next.equals("identifiers")) {
                                b = 0;
                            }
                        } else if (next.equals("enabledDebugModes")) {
                            b = 1;
                        }
                    } else if (next.equals("weblabOverrides")) {
                        b = 2;
                    }
                    ImmutableMap<String, String> immutableMap = null;
                    ImmutableMap<String, String> immutableMap2 = null;
                    ImmutableList<DebugMode> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableMap2 = this.mRefreshIdentifiersParser.parse(jsonNode2);
                        }
                        builder.identifiers = immutableMap2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mEnabledDebugModesParser.parse(jsonNode2);
                        }
                        builder.enabledDebugModes = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mWeblabOverridesParser.parse(jsonNode2);
                        }
                        builder.weblabOverrides = immutableMap;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RefreshPlaybackEnvelopeRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
            return builder.build();
        }

        @Nonnull
        private RefreshPlaybackEnvelopeRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            int iHashCode = currentName.hashCode();
                            if (iHashCode != -1474512786) {
                                if (iHashCode != 479296190) {
                                    if (iHashCode == 1368189162 && currentName.equals("identifiers")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("enabledDebugModes")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("weblabOverrides")) {
                                b = 2;
                            }
                            ImmutableMap<String, String> immutableMap = null;
                            ImmutableMap<String, String> immutableMap2 = null;
                            ImmutableList<DebugMode> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap2 = this.mRefreshIdentifiersParser.parse(jsonParser);
                                }
                                builder.identifiers = immutableMap2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mEnabledDebugModesParser.parse(jsonParser);
                                }
                                builder.enabledDebugModes = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mWeblabOverridesParser.parse(jsonParser);
                                }
                                builder.weblabOverrides = immutableMap;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RefreshPlaybackEnvelopeRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
                    return builder.build();
                }
            }
        }
    }
}
