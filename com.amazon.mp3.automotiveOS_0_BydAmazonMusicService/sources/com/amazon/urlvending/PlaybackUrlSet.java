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
public class PlaybackUrlSet {
    public final Optional<ImmutableMap<String, Failover>> failover;
    public final Optional<String> urlSetId;
    public final Optional<Urls> urls;

    private PlaybackUrlSet(Builder builder) {
        this.urls = Optional.fromNullable(builder.urls);
        this.failover = Optional.fromNullable(builder.failover);
        this.urlSetId = Optional.fromNullable(builder.urlSetId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackUrlSet)) {
            return false;
        }
        PlaybackUrlSet playbackUrlSet = (PlaybackUrlSet) obj;
        return Objects.equal(this.urls, playbackUrlSet.urls) && Objects.equal(this.failover, playbackUrlSet.failover) && Objects.equal(this.urlSetId, playbackUrlSet.urlSetId);
    }

    public int hashCode() {
        return Objects.hashCode(this.urls, this.failover, this.urlSetId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("urls", this.urls).add("failover", this.failover).add("urlSetId", this.urlSetId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableMap<String, Failover> failover;
        public String urlSetId;
        public Urls urls;

        public PlaybackUrlSet build() {
            return new PlaybackUrlSet(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackUrlSet> {
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
        public PlaybackUrlSet parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackUrlSet:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackUrlSet parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackUrlSet:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PlaybackUrlSet parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackUrlSet");
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
                            b = 0;
                        }
                    } else if (next.equals("urlSetId")) {
                        b = 2;
                    }
                    String str = null;
                    Urls urls = null;
                    ImmutableMap<String, Failover> immutableMap = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            urls = this.mUrlsParser.parse(jsonNode2);
                        }
                        builder.urls = urls;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mFailoverMapParser.parse(jsonNode2);
                        }
                        builder.failover = immutableMap;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.urlSetId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackUrlSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private PlaybackUrlSet parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                    b = 0;
                                }
                            } else if (currentName.equals("urlSetId")) {
                                b = 2;
                            }
                            String str = null;
                            Urls urls = null;
                            ImmutableMap<String, Failover> immutableMap = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    urls = this.mUrlsParser.parse(jsonParser);
                                }
                                builder.urls = urls;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mFailoverMapParser.parse(jsonParser);
                                }
                                builder.failover = immutableMap;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.urlSetId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackUrlSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
