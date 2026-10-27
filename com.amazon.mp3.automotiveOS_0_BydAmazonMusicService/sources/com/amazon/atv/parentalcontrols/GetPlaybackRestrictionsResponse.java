package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.InvalidTitleIdentifier;
import com.amazon.atv.parentalcontrols.PlaybackRestrictedTitle;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
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
public class GetPlaybackRestrictionsResponse {
    public final Optional<ImmutableList<InvalidTitleIdentifier>> invalidTitles;
    public final Optional<ImmutableList<PlaybackRestrictedTitle>> titles;

    private GetPlaybackRestrictionsResponse(Builder builder) {
        this.invalidTitles = Optional.fromNullable(builder.invalidTitles);
        this.titles = Optional.fromNullable(builder.titles);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetPlaybackRestrictionsResponse)) {
            return false;
        }
        GetPlaybackRestrictionsResponse getPlaybackRestrictionsResponse = (GetPlaybackRestrictionsResponse) obj;
        return Objects.equal(this.invalidTitles, getPlaybackRestrictionsResponse.invalidTitles) && Objects.equal(this.titles, getPlaybackRestrictionsResponse.titles);
    }

    public int hashCode() {
        return Objects.hashCode(this.invalidTitles, this.titles);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("invalidTitles", this.invalidTitles).add("titles", this.titles).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<InvalidTitleIdentifier> invalidTitles;
        public ImmutableList<PlaybackRestrictedTitle> titles;

        public GetPlaybackRestrictionsResponse build() {
            return new GetPlaybackRestrictionsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetPlaybackRestrictionsResponse> {
        private final ListParser<InvalidTitleIdentifier> minvalidTitlesListParser;
        private final ListParser<PlaybackRestrictedTitle> mplaybackRestrictedTitlesListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.minvalidTitlesListParser = ListParser.newParser(new InvalidTitleIdentifier.Parser(objectMapper));
            this.mplaybackRestrictedTitlesListParser = ListParser.newParser(new PlaybackRestrictedTitle.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetPlaybackRestrictionsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetPlaybackRestrictionsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetPlaybackRestrictionsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetPlaybackRestrictionsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetPlaybackRestrictionsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetPlaybackRestrictionsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1160809998) {
                        if (iHashCode == -873453285 && next.equals("titles")) {
                            b = 1;
                        }
                    } else if (next.equals("invalidTitles")) {
                        b = 0;
                    }
                    ImmutableList<PlaybackRestrictedTitle> immutableList = null;
                    ImmutableList<InvalidTitleIdentifier> immutableList2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.minvalidTitlesListParser.parse(jsonNode2);
                        }
                        builder.invalidTitles = immutableList2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mplaybackRestrictedTitlesListParser.parse(jsonNode2);
                        }
                        builder.titles = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetPlaybackRestrictionsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetPlaybackRestrictionsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1160809998) {
                                if (iHashCode == -873453285 && currentName.equals("titles")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("invalidTitles")) {
                                b = 0;
                            }
                            ImmutableList<PlaybackRestrictedTitle> immutableList = null;
                            ImmutableList<InvalidTitleIdentifier> immutableList2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.minvalidTitlesListParser.parse(jsonParser);
                                }
                                builder.invalidTitles = immutableList2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mplaybackRestrictedTitlesListParser.parse(jsonParser);
                                }
                                builder.titles = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetPlaybackRestrictionsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
