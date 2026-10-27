package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.GetTitleRestrictionsResponseMetadata;
import com.amazon.atv.parentalcontrols.RestrictedTitle;
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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetTitleRestrictionsResponse {
    public final GetTitleRestrictionsResponseMetadata metadata;
    public final ImmutableList<RestrictedTitle> titles;

    private GetTitleRestrictionsResponse(Builder builder) {
        this.titles = (ImmutableList) Preconditions.checkNotNull(builder.titles, "Unexpected null field: titles");
        this.metadata = (GetTitleRestrictionsResponseMetadata) Preconditions.checkNotNull(builder.metadata, "Unexpected null field: metadata");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetTitleRestrictionsResponse)) {
            return false;
        }
        GetTitleRestrictionsResponse getTitleRestrictionsResponse = (GetTitleRestrictionsResponse) obj;
        return Objects.equal(this.titles, getTitleRestrictionsResponse.titles) && Objects.equal(this.metadata, getTitleRestrictionsResponse.metadata);
    }

    public int hashCode() {
        return Objects.hashCode(this.titles, this.metadata);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("titles", this.titles).add("metadata", this.metadata).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public GetTitleRestrictionsResponseMetadata metadata;
        public ImmutableList<RestrictedTitle> titles;

        public GetTitleRestrictionsResponse build() {
            return new GetTitleRestrictionsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetTitleRestrictionsResponse> {
        private final GetTitleRestrictionsResponseMetadata.Parser mGetTitleRestrictionsResponseMetadataParser;
        private final ListParser<RestrictedTitle> mrestrictedTitlesListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mGetTitleRestrictionsResponseMetadataParser = new GetTitleRestrictionsResponseMetadata.Parser(objectMapper);
            this.mrestrictedTitlesListParser = ListParser.newParser(new RestrictedTitle.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetTitleRestrictionsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetTitleRestrictionsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetTitleRestrictionsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetTitleRestrictionsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetTitleRestrictionsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetTitleRestrictionsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -873453285) {
                        if (iHashCode == -450004177 && next.equals("metadata")) {
                            b = 1;
                        }
                    } else if (next.equals("titles")) {
                        b = 0;
                    }
                    GetTitleRestrictionsResponseMetadata getTitleRestrictionsResponseMetadata = null;
                    ImmutableList<RestrictedTitle> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mrestrictedTitlesListParser.parse(jsonNode2);
                        }
                        builder.titles = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            getTitleRestrictionsResponseMetadata = this.mGetTitleRestrictionsResponseMetadataParser.parse(jsonNode2);
                        }
                        builder.metadata = getTitleRestrictionsResponseMetadata;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetTitleRestrictionsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.titles, this, "titles");
            JsonParsingUtils.checkNotNull(builder.metadata, this, "metadata");
            return builder.build();
        }

        @Nonnull
        private GetTitleRestrictionsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -873453285) {
                                if (iHashCode == -450004177 && currentName.equals("metadata")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("titles")) {
                                b = 0;
                            }
                            GetTitleRestrictionsResponseMetadata getTitleRestrictionsResponseMetadata = null;
                            ImmutableList<RestrictedTitle> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mrestrictedTitlesListParser.parse(jsonParser);
                                }
                                builder.titles = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    getTitleRestrictionsResponseMetadata = this.mGetTitleRestrictionsResponseMetadataParser.parse(jsonParser);
                                }
                                builder.metadata = getTitleRestrictionsResponseMetadata;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetTitleRestrictionsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.titles, this, "titles");
                    JsonParsingUtils.checkNotNull(builder.metadata, this, "metadata");
                    return builder.build();
                }
            }
        }
    }
}
