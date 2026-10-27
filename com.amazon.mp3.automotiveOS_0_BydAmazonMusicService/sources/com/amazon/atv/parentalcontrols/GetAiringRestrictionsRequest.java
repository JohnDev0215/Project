package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.ClientDetails;
import com.amazon.atv.parentalcontrols.GeoCustomerWithClientDetailsRequest;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
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
public class GetAiringRestrictionsRequest extends GeoCustomerWithClientDetailsRequest {
    public final ImmutableList<String> identifiers;
    public final Optional<RequestIntent> intent;

    private GetAiringRestrictionsRequest(Builder builder) {
        super(builder);
        this.intent = Optional.fromNullable(builder.intent);
        this.identifiers = (ImmutableList) Preconditions.checkNotNull(builder.identifiers, "Unexpected null field: identifiers");
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerWithClientDetailsRequest, com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetAiringRestrictionsRequest)) {
            return false;
        }
        GetAiringRestrictionsRequest getAiringRestrictionsRequest = (GetAiringRestrictionsRequest) obj;
        return super.equals(obj) && Objects.equal(this.intent, getAiringRestrictionsRequest.intent) && Objects.equal(this.identifiers, getAiringRestrictionsRequest.identifiers);
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerWithClientDetailsRequest, com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.intent, this.identifiers);
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerWithClientDetailsRequest, com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add(MAPAccountManager.KEY_INTENT, this.intent).add("identifiers", this.identifiers).toString();
    }

    @NotThreadSafe
    public static class Builder extends GeoCustomerWithClientDetailsRequest.Builder {
        public ImmutableList<String> identifiers;
        public RequestIntent intent;

        public GetAiringRestrictionsRequest build() {
            return new GetAiringRestrictionsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetAiringRestrictionsRequest> {
        private final ClientDetails.Parser mClientDetailsParser;
        private final ListParser<String> mboundedIdentifierListParser;
        private final SimpleParsers.StringParser mgeoTokenParser;
        private final EnumParser<RequestIntent> mrequestIntentParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mrequestIntentParser = EnumParser.newParser(RequestIntent.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mboundedIdentifierListParser = ListParser.newParser(stringParser);
            this.mClientDetailsParser = new ClientDetails.Parser(objectMapper);
            this.mgeoTokenParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetAiringRestrictionsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAiringRestrictionsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetAiringRestrictionsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetAiringRestrictionsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetAiringRestrictionsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetAiringRestrictionsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1357712437:
                            if (next.equals(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT)) {
                                b = 2;
                            }
                            break;
                        case -1183762788:
                            if (next.equals(MAPAccountManager.KEY_INTENT)) {
                                b = 0;
                            }
                            break;
                        case 1368189162:
                            if (next.equals("identifiers")) {
                                b = 3;
                            }
                            break;
                        case 1823221032:
                            if (next.equals("geoToken")) {
                                b = 1;
                            }
                            break;
                    }
                    ImmutableList<String> immutableList = null;
                    RequestIntent requestIntent = null;
                    String str = null;
                    ClientDetails clientDetails = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            requestIntent = (RequestIntent) this.mrequestIntentParser.parse(jsonNode2);
                        }
                        builder.intent = requestIntent;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mgeoTokenParser.parse(jsonNode2);
                        }
                        builder.geoToken = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            clientDetails = this.mClientDetailsParser.parse(jsonNode2);
                        }
                        builder.client = clientDetails;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mboundedIdentifierListParser.parse(jsonNode2);
                        }
                        builder.identifiers = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetAiringRestrictionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.geoToken, this, "geoToken");
            JsonParsingUtils.checkNotNull(builder.client, this, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
            JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetAiringRestrictionsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1183762788:
                                    if (currentName.equals(MAPAccountManager.KEY_INTENT)) {
                                        b = 0;
                                    }
                                    break;
                                case 1368189162:
                                    if (currentName.equals("identifiers")) {
                                        b = 3;
                                    }
                                    break;
                                case 1823221032:
                                    if (currentName.equals("geoToken")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            ImmutableList<String> immutableList = null;
                            RequestIntent requestIntent = null;
                            String str = null;
                            ClientDetails clientDetails = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    requestIntent = (RequestIntent) this.mrequestIntentParser.parse(jsonParser);
                                }
                                builder.intent = requestIntent;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mgeoTokenParser.parse(jsonParser);
                                }
                                builder.geoToken = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    clientDetails = this.mClientDetailsParser.parse(jsonParser);
                                }
                                builder.client = clientDetails;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mboundedIdentifierListParser.parse(jsonParser);
                                }
                                builder.identifiers = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetAiringRestrictionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.geoToken, this, "geoToken");
                    JsonParsingUtils.checkNotNull(builder.client, this, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
                    JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
                    return builder.build();
                }
            }
        }
    }
}
