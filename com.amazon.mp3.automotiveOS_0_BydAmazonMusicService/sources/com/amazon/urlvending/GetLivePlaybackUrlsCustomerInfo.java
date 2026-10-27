package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public class GetLivePlaybackUrlsCustomerInfo {
    public final Optional<String> geoToken;
    public final Optional<String> identityToken;
    public final Optional<String> ip;
    public final Optional<String> preferredLanguage;

    private GetLivePlaybackUrlsCustomerInfo(Builder builder) {
        this.identityToken = Optional.fromNullable(builder.identityToken);
        this.geoToken = Optional.fromNullable(builder.geoToken);
        this.preferredLanguage = Optional.fromNullable(builder.preferredLanguage);
        this.ip = Optional.fromNullable(builder.ip);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLivePlaybackUrlsCustomerInfo)) {
            return false;
        }
        GetLivePlaybackUrlsCustomerInfo getLivePlaybackUrlsCustomerInfo = (GetLivePlaybackUrlsCustomerInfo) obj;
        return Objects.equal(this.identityToken, getLivePlaybackUrlsCustomerInfo.identityToken) && Objects.equal(this.geoToken, getLivePlaybackUrlsCustomerInfo.geoToken) && Objects.equal(this.preferredLanguage, getLivePlaybackUrlsCustomerInfo.preferredLanguage) && Objects.equal(this.ip, getLivePlaybackUrlsCustomerInfo.ip);
    }

    public int hashCode() {
        return Objects.hashCode(this.identityToken, this.geoToken, this.preferredLanguage, this.ip);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("identityToken", this.identityToken).add("geoToken", this.geoToken).add("preferredLanguage", this.preferredLanguage).add("ip", this.ip).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String geoToken;
        public String identityToken;
        public String ip;
        public String preferredLanguage;

        public GetLivePlaybackUrlsCustomerInfo build() {
            return new GetLivePlaybackUrlsCustomerInfo(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLivePlaybackUrlsCustomerInfo> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLivePlaybackUrlsCustomerInfo parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsCustomerInfo:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLivePlaybackUrlsCustomerInfo parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsCustomerInfo:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetLivePlaybackUrlsCustomerInfo parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLivePlaybackUrlsCustomerInfo");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -214174983) {
                        if (iHashCode != 3367) {
                            if (iHashCode != 552214491) {
                                if (iHashCode == 1823221032 && next.equals("geoToken")) {
                                    b = 1;
                                }
                            } else if (next.equals("identityToken")) {
                                b = 0;
                            }
                        } else if (next.equals("ip")) {
                            b = 3;
                        }
                    } else if (next.equals("preferredLanguage")) {
                        b = 2;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.identityToken = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.geoToken = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.preferredLanguage = str;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.ip = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLivePlaybackUrlsCustomerInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetLivePlaybackUrlsCustomerInfo parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -214174983) {
                                if (iHashCode != 3367) {
                                    if (iHashCode != 552214491) {
                                        if (iHashCode == 1823221032 && currentName.equals("geoToken")) {
                                            b = 1;
                                        }
                                    } else if (currentName.equals("identityToken")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("ip")) {
                                    b = 3;
                                }
                            } else if (currentName.equals("preferredLanguage")) {
                                b = 2;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.identityToken = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.geoToken = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.preferredLanguage = str;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.ip = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLivePlaybackUrlsCustomerInfo so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
