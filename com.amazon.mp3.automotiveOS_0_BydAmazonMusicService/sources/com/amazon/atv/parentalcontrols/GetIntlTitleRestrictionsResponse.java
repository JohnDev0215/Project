package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.GetIntlTitleRestrictionsResponseMetadata;
import com.amazon.atv.parentalcontrols.InternationalRestrictedTitle;
import com.amazon.atv.parentalcontrols.InvalidTitleIdentifier;
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
public class GetIntlTitleRestrictionsResponse {
    public final Optional<ImmutableList<InvalidTitleIdentifier>> invalidTitles;
    public final Optional<GetIntlTitleRestrictionsResponseMetadata> metadata;
    public final Optional<ImmutableList<InternationalRestrictedTitle>> titles;

    private GetIntlTitleRestrictionsResponse(Builder builder) {
        this.titles = Optional.fromNullable(builder.titles);
        this.metadata = Optional.fromNullable(builder.metadata);
        this.invalidTitles = Optional.fromNullable(builder.invalidTitles);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetIntlTitleRestrictionsResponse)) {
            return false;
        }
        GetIntlTitleRestrictionsResponse getIntlTitleRestrictionsResponse = (GetIntlTitleRestrictionsResponse) obj;
        return Objects.equal(this.titles, getIntlTitleRestrictionsResponse.titles) && Objects.equal(this.metadata, getIntlTitleRestrictionsResponse.metadata) && Objects.equal(this.invalidTitles, getIntlTitleRestrictionsResponse.invalidTitles);
    }

    public int hashCode() {
        return Objects.hashCode(this.titles, this.metadata, this.invalidTitles);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("titles", this.titles).add("metadata", this.metadata).add("invalidTitles", this.invalidTitles).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<InvalidTitleIdentifier> invalidTitles;
        public GetIntlTitleRestrictionsResponseMetadata metadata;
        public ImmutableList<InternationalRestrictedTitle> titles;

        public GetIntlTitleRestrictionsResponse build() {
            return new GetIntlTitleRestrictionsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetIntlTitleRestrictionsResponse> {
        private final GetIntlTitleRestrictionsResponseMetadata.Parser mGetIntlTitleRestrictionsResponseMetadataParser;
        private final ListParser<InternationalRestrictedTitle> minternationalRestrictedTitlesListParser;
        private final ListParser<InvalidTitleIdentifier> minvalidTitlesListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mGetIntlTitleRestrictionsResponseMetadataParser = new GetIntlTitleRestrictionsResponseMetadata.Parser(objectMapper);
            this.minternationalRestrictedTitlesListParser = ListParser.newParser(new InternationalRestrictedTitle.Parser(objectMapper));
            this.minvalidTitlesListParser = ListParser.newParser(new InvalidTitleIdentifier.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetIntlTitleRestrictionsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetIntlTitleRestrictionsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetIntlTitleRestrictionsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetIntlTitleRestrictionsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetIntlTitleRestrictionsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetIntlTitleRestrictionsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1160809998) {
                        if (iHashCode != -873453285) {
                            if (iHashCode == -450004177 && next.equals("metadata")) {
                                b = 1;
                            }
                        } else if (next.equals("titles")) {
                            b = 0;
                        }
                    } else if (next.equals("invalidTitles")) {
                        b = 2;
                    }
                    ImmutableList<InvalidTitleIdentifier> immutableList = null;
                    ImmutableList<InternationalRestrictedTitle> immutableList2 = null;
                    GetIntlTitleRestrictionsResponseMetadata getIntlTitleRestrictionsResponseMetadata = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.minternationalRestrictedTitlesListParser.parse(jsonNode2);
                        }
                        builder.titles = immutableList2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            getIntlTitleRestrictionsResponseMetadata = this.mGetIntlTitleRestrictionsResponseMetadataParser.parse(jsonNode2);
                        }
                        builder.metadata = getIntlTitleRestrictionsResponseMetadata;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.minvalidTitlesListParser.parse(jsonNode2);
                        }
                        builder.invalidTitles = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetIntlTitleRestrictionsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetIntlTitleRestrictionsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                if (iHashCode != -873453285) {
                                    if (iHashCode == -450004177 && currentName.equals("metadata")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("titles")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("invalidTitles")) {
                                b = 2;
                            }
                            ImmutableList<InvalidTitleIdentifier> immutableList = null;
                            ImmutableList<InternationalRestrictedTitle> immutableList2 = null;
                            GetIntlTitleRestrictionsResponseMetadata getIntlTitleRestrictionsResponseMetadata = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.minternationalRestrictedTitlesListParser.parse(jsonParser);
                                }
                                builder.titles = immutableList2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    getIntlTitleRestrictionsResponseMetadata = this.mGetIntlTitleRestrictionsResponseMetadataParser.parse(jsonParser);
                                }
                                builder.metadata = getIntlTitleRestrictionsResponseMetadata;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.minvalidTitlesListParser.parse(jsonParser);
                                }
                                builder.invalidTitles = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetIntlTitleRestrictionsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
