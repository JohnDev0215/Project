package com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avtitleactionaggregationservice.model.VideoQuality;
import com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition.Price;
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

/* JADX INFO: loaded from: classes2.dex */
public class TvodOfferMetadata {
    public final Optional<Family> family;
    public final Optional<Price> price;
    public final Optional<VideoQuality> videoQuality;

    private TvodOfferMetadata(Builder builder) {
        this.price = Optional.fromNullable(builder.price);
        this.videoQuality = Optional.fromNullable(builder.videoQuality);
        this.family = Optional.fromNullable(builder.family);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TvodOfferMetadata)) {
            return false;
        }
        TvodOfferMetadata tvodOfferMetadata = (TvodOfferMetadata) obj;
        return Objects.equal(this.price, tvodOfferMetadata.price) && Objects.equal(this.videoQuality, tvodOfferMetadata.videoQuality) && Objects.equal(this.family, tvodOfferMetadata.family);
    }

    public int hashCode() {
        return Objects.hashCode(this.price, this.videoQuality, this.family);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("price", this.price).add("videoQuality", this.videoQuality).add("family", this.family).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Family family;
        public Price price;
        public VideoQuality videoQuality;

        public TvodOfferMetadata build() {
            return new TvodOfferMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TvodOfferMetadata> {
        private final EnumParser<Family> mFamilyParser;
        private final Price.Parser mPriceParser;
        private final EnumParser<VideoQuality> mVideoQualityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPriceParser = new Price.Parser(objectMapper);
            this.mFamilyParser = EnumParser.newParser(Family.class);
            this.mVideoQualityParser = EnumParser.newParser(VideoQuality.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TvodOfferMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TvodOfferMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TvodOfferMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TvodOfferMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TvodOfferMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TvodOfferMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1281860764) {
                        if (iHashCode != 106934601) {
                            if (iHashCode == 504058884 && next.equals("videoQuality")) {
                                b = 1;
                            }
                        } else if (next.equals("price")) {
                            b = 0;
                        }
                    } else if (next.equals("family")) {
                        b = 2;
                    }
                    Family family = null;
                    Price price = null;
                    VideoQuality videoQuality = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            price = this.mPriceParser.parse(jsonNode2);
                        }
                        builder.price = price;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonNode2);
                        }
                        builder.videoQuality = videoQuality;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            family = (Family) this.mFamilyParser.parse(jsonNode2);
                        }
                        builder.family = family;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TvodOfferMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private TvodOfferMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1281860764) {
                                if (iHashCode != 106934601) {
                                    if (iHashCode == 504058884 && currentName.equals("videoQuality")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("price")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("family")) {
                                b = 2;
                            }
                            Family family = null;
                            Price price = null;
                            VideoQuality videoQuality = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    price = this.mPriceParser.parse(jsonParser);
                                }
                                builder.price = price;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonParser);
                                }
                                builder.videoQuality = videoQuality;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    family = (Family) this.mFamilyParser.parse(jsonParser);
                                }
                                builder.family = family;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TvodOfferMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
