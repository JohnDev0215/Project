package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.Client;
import com.amazon.atv.parentalcontrols.InternationalCustomerClientRequest;
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
public class GetIntlTitleRestrictionsRequest extends InternationalCustomerClientRequest {
    public final String currentTerritory;
    public final ImmutableList<String> identifiers;
    public final Optional<RequestIntent> intent;

    private GetIntlTitleRestrictionsRequest(Builder builder) {
        super(builder);
        this.intent = Optional.fromNullable(builder.intent);
        this.identifiers = (ImmutableList) Preconditions.checkNotNull(builder.identifiers, "Unexpected null field: identifiers");
        this.currentTerritory = (String) Preconditions.checkNotNull(builder.currentTerritory, "Unexpected null field: currentTerritory");
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerClientRequest, com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetIntlTitleRestrictionsRequest)) {
            return false;
        }
        GetIntlTitleRestrictionsRequest getIntlTitleRestrictionsRequest = (GetIntlTitleRestrictionsRequest) obj;
        return super.equals(obj) && Objects.equal(this.intent, getIntlTitleRestrictionsRequest.intent) && Objects.equal(this.identifiers, getIntlTitleRestrictionsRequest.identifiers) && Objects.equal(this.currentTerritory, getIntlTitleRestrictionsRequest.currentTerritory);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerClientRequest, com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.intent, this.identifiers, this.currentTerritory);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerClientRequest, com.amazon.atv.parentalcontrols.InternationalCustomerRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add(MAPAccountManager.KEY_INTENT, this.intent).add("identifiers", this.identifiers).add("currentTerritory", this.currentTerritory).toString();
    }

    @NotThreadSafe
    public static class Builder extends InternationalCustomerClientRequest.Builder {
        public String currentTerritory;
        public ImmutableList<String> identifiers;
        public RequestIntent intent;

        public GetIntlTitleRestrictionsRequest build() {
            return new GetIntlTitleRestrictionsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetIntlTitleRestrictionsRequest> {
        private final Client.Parser mClientParser;
        private final SimpleParsers.StringParser mcurrentTerritoryParser;
        private final ListParser<String> midentifierListParser;
        private final EnumParser<RequestIntent> mrequestIntentParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mrequestIntentParser = EnumParser.newParser(RequestIntent.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.midentifierListParser = ListParser.newParser(stringParser);
            this.mClientParser = new Client.Parser(objectMapper);
            this.mcurrentTerritoryParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetIntlTitleRestrictionsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetIntlTitleRestrictionsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetIntlTitleRestrictionsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetIntlTitleRestrictionsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetIntlTitleRestrictionsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetIntlTitleRestrictionsRequest");
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
                                b = 0;
                            }
                            break;
                        case -1183762788:
                            if (next.equals(MAPAccountManager.KEY_INTENT)) {
                                b = 1;
                            }
                            break;
                        case -976788831:
                            if (next.equals("currentTerritory")) {
                                b = 3;
                            }
                            break;
                        case 1368189162:
                            if (next.equals("identifiers")) {
                                b = 2;
                            }
                            break;
                    }
                    String str = null;
                    Client client = null;
                    RequestIntent requestIntent = null;
                    ImmutableList<String> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            client = this.mClientParser.parse(jsonNode2);
                        }
                        builder.client = client;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            requestIntent = (RequestIntent) this.mrequestIntentParser.parse(jsonNode2);
                        }
                        builder.intent = requestIntent;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.midentifierListParser.parse(jsonNode2);
                        }
                        builder.identifiers = immutableList;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mcurrentTerritoryParser.parse(jsonNode2);
                        }
                        builder.currentTerritory = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetIntlTitleRestrictionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.client, this, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
            JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
            JsonParsingUtils.checkNotNull(builder.currentTerritory, this, "currentTerritory");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetIntlTitleRestrictionsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 0;
                                    }
                                    break;
                                case -1183762788:
                                    if (currentName.equals(MAPAccountManager.KEY_INTENT)) {
                                        b = 1;
                                    }
                                    break;
                                case -976788831:
                                    if (currentName.equals("currentTerritory")) {
                                        b = 3;
                                    }
                                    break;
                                case 1368189162:
                                    if (currentName.equals("identifiers")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            String str = null;
                            Client client = null;
                            RequestIntent requestIntent = null;
                            ImmutableList<String> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    client = this.mClientParser.parse(jsonParser);
                                }
                                builder.client = client;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    requestIntent = (RequestIntent) this.mrequestIntentParser.parse(jsonParser);
                                }
                                builder.intent = requestIntent;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.midentifierListParser.parse(jsonParser);
                                }
                                builder.identifiers = immutableList;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mcurrentTerritoryParser.parse(jsonParser);
                                }
                                builder.currentTerritory = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetIntlTitleRestrictionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.client, this, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
                    JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
                    JsonParsingUtils.checkNotNull(builder.currentTerritory, this, "currentTerritory");
                    return builder.build();
                }
            }
        }
    }
}
