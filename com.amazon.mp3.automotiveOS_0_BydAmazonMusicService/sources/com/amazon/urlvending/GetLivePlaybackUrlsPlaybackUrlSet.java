package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.Failover;
import com.amazon.urlvending.Urls;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public class GetLivePlaybackUrlsPlaybackUrlSet {
    public final Optional<ImmutableMap<String, Failover>> failover;
    public final Optional<String> urlSetId;
    public final Optional<Urls> urls;

    private GetLivePlaybackUrlsPlaybackUrlSet(Builder builder) {
        this.urlSetId = Optional.fromNullable(builder.urlSetId);
        this.failover = Optional.fromNullable(builder.failover);
        this.urls = Optional.fromNullable(builder.urls);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLivePlaybackUrlsPlaybackUrlSet)) {
            return false;
        }
        GetLivePlaybackUrlsPlaybackUrlSet getLivePlaybackUrlsPlaybackUrlSet = (GetLivePlaybackUrlsPlaybackUrlSet) obj;
        return Objects.equal(this.urlSetId, getLivePlaybackUrlsPlaybackUrlSet.urlSetId) && Objects.equal(this.failover, getLivePlaybackUrlsPlaybackUrlSet.failover) && Objects.equal(this.urls, getLivePlaybackUrlsPlaybackUrlSet.urls);
    }

    public int hashCode() {
        return Objects.hashCode(this.urlSetId, this.failover, this.urls);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("urlSetId", this.urlSetId).add("failover", this.failover).add("urls", this.urls).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableMap<String, Failover> failover;
        public String urlSetId;
        public Urls urls;

        public GetLivePlaybackUrlsPlaybackUrlSet build() {
            return new GetLivePlaybackUrlsPlaybackUrlSet(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLivePlaybackUrlsPlaybackUrlSet> {
        private final MapParser<String, Failover> mFailoverMapParser;
        private final SimpleParsers.StringParser mStringParser;
        private final Urls.Parser mUrlsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mFailoverMapParser = MapParser.newParser(stringParser, new Failover.Parser(objectMapper));
            this.mUrlsParser = new Urls.Parser(objectMapper);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLivePlaybackUrlsPlaybackUrlSet parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsPlaybackUrlSet:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLivePlaybackUrlsPlaybackUrlSet parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsPlaybackUrlSet:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetLivePlaybackUrlsPlaybackUrlSet parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLivePlaybackUrlsPlaybackUrlSet");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -981693202) {
                        if (iHashCode != 3598564) {
                            if (iHashCode == 675763442 && next.equals("failover")) {
                                b = 1;
                            }
                        } else if (next.equals("urls")) {
                            b = 2;
                        }
                    } else if (next.equals("urlSetId")) {
                        b = 0;
                    }
                    Urls urls = null;
                    String str = null;
                    ImmutableMap<String, Failover> immutableMap = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.urlSetId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mFailoverMapParser.parse(jsonNode2);
                        }
                        builder.failover = immutableMap;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            urls = this.mUrlsParser.parse(jsonNode2);
                        }
                        builder.urls = urls;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLivePlaybackUrlsPlaybackUrlSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetLivePlaybackUrlsPlaybackUrlSet parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -981693202) {
                                if (iHashCode != 3598564) {
                                    if (iHashCode == 675763442 && currentName.equals("failover")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("urls")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("urlSetId")) {
                                b = 0;
                            }
                            Urls urls = null;
                            String str = null;
                            ImmutableMap<String, Failover> immutableMap = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.urlSetId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mFailoverMapParser.parse(jsonParser);
                                }
                                builder.failover = immutableMap;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    urls = this.mUrlsParser.parse(jsonParser);
                                }
                                builder.urls = urls;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLivePlaybackUrlsPlaybackUrlSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
