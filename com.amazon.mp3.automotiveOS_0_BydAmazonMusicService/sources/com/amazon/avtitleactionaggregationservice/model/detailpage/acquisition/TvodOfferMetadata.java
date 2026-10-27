package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.VideoQuality;
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
    public final Optional<Boolean> preorder;
    public final Optional<Long> preorderReleaseDate;
    public final Optional<VideoQuality> videoQuality;

    private TvodOfferMetadata(Builder builder) {
        this.videoQuality = Optional.fromNullable(builder.videoQuality);
        this.preorderReleaseDate = Optional.fromNullable(builder.preorderReleaseDate);
        this.family = Optional.fromNullable(builder.family);
        this.preorder = Optional.fromNullable(builder.preorder);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TvodOfferMetadata)) {
            return false;
        }
        TvodOfferMetadata tvodOfferMetadata = (TvodOfferMetadata) obj;
        return Objects.equal(this.videoQuality, tvodOfferMetadata.videoQuality) && Objects.equal(this.preorderReleaseDate, tvodOfferMetadata.preorderReleaseDate) && Objects.equal(this.family, tvodOfferMetadata.family) && Objects.equal(this.preorder, tvodOfferMetadata.preorder);
    }

    public int hashCode() {
        return Objects.hashCode(this.videoQuality, this.preorderReleaseDate, this.family, this.preorder);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("videoQuality", this.videoQuality).add("preorderReleaseDate", this.preorderReleaseDate).add("family", this.family).add("preorder", this.preorder).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Family family;
        public Boolean preorder;
        public Long preorderReleaseDate;
        public VideoQuality videoQuality;

        public TvodOfferMetadata build() {
            return new TvodOfferMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TvodOfferMetadata> {
        private final SimpleParsers.BooleanParser mBooleanParser;
        private final EnumParser<Family> mFamilyParser;
        private final SimpleParsers.LongParser mLongParser;
        private final EnumParser<VideoQuality> mVideoQualityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mFamilyParser = EnumParser.newParser(Family.class);
            this.mBooleanParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mLongParser = SimpleParsers.LongParser.INSTANCE;
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

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
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
                    switch (next.hashCode()) {
                        case -1281860764:
                            if (next.equals("family")) {
                                b = 2;
                            }
                            break;
                        case -1279982965:
                            if (next.equals("preorder")) {
                                b = 3;
                            }
                            break;
                        case 504058884:
                            if (next.equals("videoQuality")) {
                                b = 0;
                            }
                            break;
                        case 1510236362:
                            if (next.equals("preorderReleaseDate")) {
                                b = 1;
                            }
                            break;
                    }
                    Boolean bool = null;
                    VideoQuality videoQuality = null;
                    Long l = null;
                    Family family = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonNode2);
                        }
                        builder.videoQuality = videoQuality;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            l = this.mLongParser.parse(jsonNode2);
                        }
                        builder.preorderReleaseDate = l;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            family = (Family) this.mFamilyParser.parse(jsonNode2);
                        }
                        builder.family = family;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            bool = this.mBooleanParser.parse(jsonNode2);
                        }
                        builder.preorder = bool;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TvodOfferMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
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
                            switch (currentName.hashCode()) {
                                case -1281860764:
                                    if (currentName.equals("family")) {
                                        b = 2;
                                    }
                                    break;
                                case -1279982965:
                                    if (currentName.equals("preorder")) {
                                        b = 3;
                                    }
                                    break;
                                case 504058884:
                                    if (currentName.equals("videoQuality")) {
                                        b = 0;
                                    }
                                    break;
                                case 1510236362:
                                    if (currentName.equals("preorderReleaseDate")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            Boolean bool = null;
                            VideoQuality videoQuality = null;
                            Long l = null;
                            Family family = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonParser);
                                }
                                builder.videoQuality = videoQuality;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mLongParser.parse(jsonParser);
                                }
                                builder.preorderReleaseDate = l;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    family = (Family) this.mFamilyParser.parse(jsonParser);
                                }
                                builder.family = family;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    bool = this.mBooleanParser.parse(jsonParser);
                                }
                                builder.preorder = bool;
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
