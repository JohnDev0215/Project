package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.ClientDetails;
import com.amazon.atv.parentalcontrols.GeoCustomerWithClientDetailsRequest;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.inappmessaging.internal.model.Splash;
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
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackRestrictionsRequest extends GeoCustomerWithClientDetailsRequest {
    public final ImmutableList<String> identifiers;
    public final String locale;
    public final Optional<String> pinProof;

    private GetPlaybackRestrictionsRequest(Builder builder) {
        super(builder);
        this.locale = (String) Preconditions.checkNotNull(builder.locale, "Unexpected null field: locale");
        this.pinProof = Optional.fromNullable(builder.pinProof);
        this.identifiers = (ImmutableList) Preconditions.checkNotNull(builder.identifiers, "Unexpected null field: identifiers");
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerWithClientDetailsRequest, com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetPlaybackRestrictionsRequest)) {
            return false;
        }
        GetPlaybackRestrictionsRequest getPlaybackRestrictionsRequest = (GetPlaybackRestrictionsRequest) obj;
        return super.equals(obj) && Objects.equal(this.locale, getPlaybackRestrictionsRequest.locale) && Objects.equal(this.pinProof, getPlaybackRestrictionsRequest.pinProof) && Objects.equal(this.identifiers, getPlaybackRestrictionsRequest.identifiers);
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerWithClientDetailsRequest, com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.locale, this.pinProof, this.identifiers);
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerWithClientDetailsRequest, com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add(Splash.PARAMS_LOCALE, this.locale).add("pinProof", this.pinProof).add("identifiers", this.identifiers).toString();
    }

    @NotThreadSafe
    public static class Builder extends GeoCustomerWithClientDetailsRequest.Builder {
        public ImmutableList<String> identifiers;
        public String locale;
        public String pinProof;

        public GetPlaybackRestrictionsRequest build() {
            return new GetPlaybackRestrictionsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetPlaybackRestrictionsRequest> {
        private final ClientDetails.Parser mClientDetailsParser;
        private final ListParser<String> mboundedIdentifierListParser;
        private final SimpleParsers.StringParser mgeoTokenParser;
        private final SimpleParsers.StringParser mlocaleParser;
        private final SimpleParsers.StringParser mpinProofParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mboundedIdentifierListParser = ListParser.newParser(stringParser);
            this.mClientDetailsParser = new ClientDetails.Parser(objectMapper);
            this.mlocaleParser = stringParser;
            this.mpinProofParser = stringParser;
            this.mgeoTokenParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetPlaybackRestrictionsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetPlaybackRestrictionsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetPlaybackRestrictionsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetPlaybackRestrictionsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0063  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atv.parentalcontrols.GetPlaybackRestrictionsRequest parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.databind.JsonNode r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 272
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.parentalcontrols.GetPlaybackRestrictionsRequest.Parser.parseInternal(com.fasterxml.jackson.databind.JsonNode):com.amazon.atv.parentalcontrols.GetPlaybackRestrictionsRequest");
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetPlaybackRestrictionsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1357712437:
                                    if (currentName.equals(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT)) {
                                        b = 2;
                                    }
                                    break;
                                case -1097462182:
                                    if (currentName.equals(Splash.PARAMS_LOCALE)) {
                                        b = 0;
                                    }
                                    break;
                                case -448537425:
                                    if (currentName.equals("pinProof")) {
                                        b = 3;
                                    }
                                    break;
                                case 1368189162:
                                    if (currentName.equals("identifiers")) {
                                        b = 4;
                                    }
                                    break;
                                case 1823221032:
                                    if (currentName.equals("geoToken")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            ImmutableList<String> immutableList = null;
                            String str = null;
                            String str2 = null;
                            ClientDetails clientDetails = null;
                            String str3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mlocaleParser.parse(jsonParser);
                                }
                                builder.locale = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mgeoTokenParser.parse(jsonParser);
                                }
                                builder.geoToken = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    clientDetails = this.mClientDetailsParser.parse(jsonParser);
                                }
                                builder.client = clientDetails;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mpinProofParser.parse(jsonParser);
                                }
                                builder.pinProof = str3;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mboundedIdentifierListParser.parse(jsonParser);
                                }
                                builder.identifiers = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetPlaybackRestrictionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.locale, this, Splash.PARAMS_LOCALE);
                    JsonParsingUtils.checkNotNull(builder.geoToken, this, "geoToken");
                    JsonParsingUtils.checkNotNull(builder.client, this, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
                    JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
                    return builder.build();
                }
            }
        }
    }
}
