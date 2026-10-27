package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.GeoCustomerRequest;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.inappmessaging.internal.model.Splash;
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
public class GetDownloadsRestrictionsRequest extends GeoCustomerRequest {
    public final ImmutableList<String> identifiers;
    public final String locale;
    public final ImmutableList<String> profileACIs;

    private GetDownloadsRestrictionsRequest(Builder builder) {
        super(builder);
        this.locale = (String) Preconditions.checkNotNull(builder.locale, "Unexpected null field: locale");
        this.profileACIs = (ImmutableList) Preconditions.checkNotNull(builder.profileACIs, "Unexpected null field: profileACIs");
        this.identifiers = (ImmutableList) Preconditions.checkNotNull(builder.identifiers, "Unexpected null field: identifiers");
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetDownloadsRestrictionsRequest)) {
            return false;
        }
        GetDownloadsRestrictionsRequest getDownloadsRestrictionsRequest = (GetDownloadsRestrictionsRequest) obj;
        return super.equals(obj) && Objects.equal(this.locale, getDownloadsRestrictionsRequest.locale) && Objects.equal(this.profileACIs, getDownloadsRestrictionsRequest.profileACIs) && Objects.equal(this.identifiers, getDownloadsRestrictionsRequest.identifiers);
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.locale, this.profileACIs, this.identifiers);
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add(Splash.PARAMS_LOCALE, this.locale).add("profileACIs", this.profileACIs).add("identifiers", this.identifiers).toString();
    }

    @NotThreadSafe
    public static class Builder extends GeoCustomerRequest.Builder {
        public ImmutableList<String> identifiers;
        public String locale;
        public ImmutableList<String> profileACIs;

        public GetDownloadsRestrictionsRequest build() {
            return new GetDownloadsRestrictionsRequest(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetDownloadsRestrictionsRequest> {
        private final ListParser<String> mboundedIdentifierListParser;
        private final SimpleParsers.StringParser mgeoTokenParser;
        private final SimpleParsers.StringParser mlocaleParser;
        private final ListParser<String> mprofileACIListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mprofileACIListParser = ListParser.newParser(stringParser);
            this.mboundedIdentifierListParser = ListParser.newParser(stringParser);
            this.mlocaleParser = stringParser;
            this.mgeoTokenParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetDownloadsRestrictionsRequest parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetDownloadsRestrictionsRequest:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetDownloadsRestrictionsRequest parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetDownloadsRestrictionsRequest:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetDownloadsRestrictionsRequest parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetDownloadsRestrictionsRequest");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1097462182:
                            if (next.equals(Splash.PARAMS_LOCALE)) {
                                b = 0;
                            }
                            break;
                        case 177085973:
                            if (next.equals("profileACIs")) {
                                b = 1;
                            }
                            break;
                        case 1368189162:
                            if (next.equals("identifiers")) {
                                b = 3;
                            }
                            break;
                        case 1823221032:
                            if (next.equals("geoToken")) {
                                b = 2;
                            }
                            break;
                    }
                    ImmutableList<String> immutableList = null;
                    String str = null;
                    ImmutableList<String> immutableList2 = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mlocaleParser.parse(jsonNode2);
                        }
                        builder.locale = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mprofileACIListParser.parse(jsonNode2);
                        }
                        builder.profileACIs = immutableList2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mgeoTokenParser.parse(jsonNode2);
                        }
                        builder.geoToken = str2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mboundedIdentifierListParser.parse(jsonNode2);
                        }
                        builder.identifiers = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetDownloadsRestrictionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.locale, this, Splash.PARAMS_LOCALE);
            JsonParsingUtils.checkNotNull(builder.profileACIs, this, "profileACIs");
            JsonParsingUtils.checkNotNull(builder.geoToken, this, "geoToken");
            JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetDownloadsRestrictionsRequest parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1097462182:
                                    if (currentName.equals(Splash.PARAMS_LOCALE)) {
                                        b = 0;
                                    }
                                    break;
                                case 177085973:
                                    if (currentName.equals("profileACIs")) {
                                        b = 1;
                                    }
                                    break;
                                case 1368189162:
                                    if (currentName.equals("identifiers")) {
                                        b = 3;
                                    }
                                    break;
                                case 1823221032:
                                    if (currentName.equals("geoToken")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            ImmutableList<String> immutableList = null;
                            String str = null;
                            ImmutableList<String> immutableList2 = null;
                            String str2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mlocaleParser.parse(jsonParser);
                                }
                                builder.locale = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mprofileACIListParser.parse(jsonParser);
                                }
                                builder.profileACIs = immutableList2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mgeoTokenParser.parse(jsonParser);
                                }
                                builder.geoToken = str2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mboundedIdentifierListParser.parse(jsonParser);
                                }
                                builder.identifiers = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetDownloadsRestrictionsRequest so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.locale, this, Splash.PARAMS_LOCALE);
                    JsonParsingUtils.checkNotNull(builder.profileACIs, this, "profileACIs");
                    JsonParsingUtils.checkNotNull(builder.geoToken, this, "geoToken");
                    JsonParsingUtils.checkNotNull(builder.identifiers, this, "identifiers");
                    return builder.build();
                }
            }
        }
    }
}
