package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackresource.PRSException;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.urlvending.PlaybackUrls;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackUrlsForTenantResponse {
    public final Optional<PRSException> error;
    public final Optional<ImmutableList<PlaybackUrls>> playbackUrlsList;

    private PlaybackUrlsForTenantResponse(Builder builder) {
        this.error = Optional.fromNullable(builder.error);
        this.playbackUrlsList = Optional.fromNullable(builder.playbackUrlsList);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackUrlsForTenantResponse)) {
            return false;
        }
        PlaybackUrlsForTenantResponse playbackUrlsForTenantResponse = (PlaybackUrlsForTenantResponse) obj;
        return Objects.equal(this.error, playbackUrlsForTenantResponse.error) && Objects.equal(this.playbackUrlsList, playbackUrlsForTenantResponse.playbackUrlsList);
    }

    public int hashCode() {
        return Objects.hashCode(this.error, this.playbackUrlsList);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("error", this.error).add("playbackUrlsList", this.playbackUrlsList).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public PRSException error;
        public ImmutableList<PlaybackUrls> playbackUrlsList;

        public PlaybackUrlsForTenantResponse build() {
            return new PlaybackUrlsForTenantResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackUrlsForTenantResponse> {
        private final PRSException.Parser mPRSExceptionParser;
        private final ListParser<PlaybackUrls> mPlaybackUrlsListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPlaybackUrlsListParser = ListParser.newParser(new PlaybackUrls.Parser(objectMapper));
            this.mPRSExceptionParser = new PRSException.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackUrlsForTenantResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackUrlsForTenantResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackUrlsForTenantResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackUrlsForTenantResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PlaybackUrlsForTenantResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackUrlsForTenantResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2112754979) {
                        if (iHashCode == 96784904 && next.equals("error")) {
                            b = 0;
                        }
                    } else if (next.equals("playbackUrlsList")) {
                        b = 1;
                    }
                    ImmutableList<PlaybackUrls> immutableList = null;
                    PRSException pRSException = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            pRSException = this.mPRSExceptionParser.parse(jsonNode2);
                        }
                        builder.error = pRSException;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mPlaybackUrlsListParser.parse(jsonNode2);
                        }
                        builder.playbackUrlsList = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackUrlsForTenantResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private PlaybackUrlsForTenantResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -2112754979) {
                                if (iHashCode == 96784904 && currentName.equals("error")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("playbackUrlsList")) {
                                b = 1;
                            }
                            ImmutableList<PlaybackUrls> immutableList = null;
                            PRSException pRSException = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pRSException = this.mPRSExceptionParser.parse(jsonParser);
                                }
                                builder.error = pRSException;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mPlaybackUrlsListParser.parse(jsonParser);
                                }
                                builder.playbackUrlsList = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackUrlsForTenantResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
