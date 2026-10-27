package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.ManifestUrl;
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
public class GetLivePlaybackUrlsUrls {
    public final Optional<String> adsTrackingEndpoint;
    public final Optional<ManifestUrl> manifest;

    private GetLivePlaybackUrlsUrls(Builder builder) {
        this.manifest = Optional.fromNullable(builder.manifest);
        this.adsTrackingEndpoint = Optional.fromNullable(builder.adsTrackingEndpoint);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetLivePlaybackUrlsUrls)) {
            return false;
        }
        GetLivePlaybackUrlsUrls getLivePlaybackUrlsUrls = (GetLivePlaybackUrlsUrls) obj;
        return Objects.equal(this.manifest, getLivePlaybackUrlsUrls.manifest) && Objects.equal(this.adsTrackingEndpoint, getLivePlaybackUrlsUrls.adsTrackingEndpoint);
    }

    public int hashCode() {
        return Objects.hashCode(this.manifest, this.adsTrackingEndpoint);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("manifest", this.manifest).add("adsTrackingEndpoint", this.adsTrackingEndpoint).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String adsTrackingEndpoint;
        public ManifestUrl manifest;

        public GetLivePlaybackUrlsUrls build() {
            return new GetLivePlaybackUrlsUrls(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetLivePlaybackUrlsUrls> {
        private final ManifestUrl.Parser mManifestUrlParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mManifestUrlParser = new ManifestUrl.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetLivePlaybackUrlsUrls parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsUrls:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetLivePlaybackUrlsUrls parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetLivePlaybackUrlsUrls:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private GetLivePlaybackUrlsUrls parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetLivePlaybackUrlsUrls");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 130625071) {
                        if (iHashCode == 535254588 && next.equals("adsTrackingEndpoint")) {
                            b = 1;
                        }
                    } else if (next.equals("manifest")) {
                        b = 0;
                    }
                    String str = null;
                    ManifestUrl manifestUrl = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            manifestUrl = this.mManifestUrlParser.parse(jsonNode2);
                        }
                        builder.manifest = manifestUrl;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.adsTrackingEndpoint = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetLivePlaybackUrlsUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private GetLivePlaybackUrlsUrls parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 130625071) {
                                if (iHashCode == 535254588 && currentName.equals("adsTrackingEndpoint")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("manifest")) {
                                b = 0;
                            }
                            String str = null;
                            ManifestUrl manifestUrl = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    manifestUrl = this.mManifestUrlParser.parse(jsonParser);
                                }
                                builder.manifest = manifestUrl;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.adsTrackingEndpoint = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetLivePlaybackUrlsUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
