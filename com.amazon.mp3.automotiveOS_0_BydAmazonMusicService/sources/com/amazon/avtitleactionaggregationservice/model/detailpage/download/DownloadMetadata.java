package com.amazon.avtitleactionaggregationservice.model.detailpage.download;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.avtitleactionaggregationservice.model.VideoMaterialType;
import com.amazon.avtitleactionaggregationservice.model.VideoQuality;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadMetadata {
    public final Optional<Date> bookmarkUpdateTime;
    public final Optional<VideoMaterialType> videoMaterialType;
    public final Optional<VideoQuality> videoQuality;

    private DownloadMetadata(Builder builder) {
        this.videoQuality = Optional.fromNullable(builder.videoQuality);
        this.videoMaterialType = Optional.fromNullable(builder.videoMaterialType);
        this.bookmarkUpdateTime = Optional.fromNullable(builder.bookmarkUpdateTime);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DownloadMetadata)) {
            return false;
        }
        DownloadMetadata downloadMetadata = (DownloadMetadata) obj;
        return Objects.equal(this.videoQuality, downloadMetadata.videoQuality) && Objects.equal(this.videoMaterialType, downloadMetadata.videoMaterialType) && Objects.equal(this.bookmarkUpdateTime, downloadMetadata.bookmarkUpdateTime);
    }

    public int hashCode() {
        return Objects.hashCode(this.videoQuality, this.videoMaterialType, this.bookmarkUpdateTime);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("videoQuality", this.videoQuality).add("videoMaterialType", this.videoMaterialType).add("bookmarkUpdateTime", this.bookmarkUpdateTime).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Date bookmarkUpdateTime;
        public VideoMaterialType videoMaterialType;
        public VideoQuality videoQuality;

        public DownloadMetadata build() {
            return new DownloadMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DownloadMetadata> {
        private final SimpleParsers.DateParser mDateTimeParser;
        private final EnumParser<VideoMaterialType> mVideoMaterialTypeParser;
        private final EnumParser<VideoQuality> mVideoQualityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mVideoMaterialTypeParser = EnumParser.newParser(VideoMaterialType.class);
            this.mDateTimeParser = SimpleParsers.DateParser.INSTANCE;
            this.mVideoQualityParser = EnumParser.newParser(VideoQuality.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DownloadMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DownloadMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DownloadMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DownloadMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private DownloadMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DownloadMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -539491044) {
                        if (iHashCode != 504058884) {
                            if (iHashCode == 795437324 && next.equals("bookmarkUpdateTime")) {
                                b = 2;
                            }
                        } else if (next.equals("videoQuality")) {
                            b = 0;
                        }
                    } else if (next.equals("videoMaterialType")) {
                        b = 1;
                    }
                    Date date = null;
                    VideoQuality videoQuality = null;
                    VideoMaterialType videoMaterialType = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonNode2);
                        }
                        builder.videoQuality = videoQuality;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            videoMaterialType = (VideoMaterialType) this.mVideoMaterialTypeParser.parse(jsonNode2);
                        }
                        builder.videoMaterialType = videoMaterialType;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            date = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.bookmarkUpdateTime = date;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DownloadMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private DownloadMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -539491044) {
                                if (iHashCode != 504058884) {
                                    if (iHashCode == 795437324 && currentName.equals("bookmarkUpdateTime")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("videoQuality")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("videoMaterialType")) {
                                b = 1;
                            }
                            Date date = null;
                            VideoQuality videoQuality = null;
                            VideoMaterialType videoMaterialType = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonParser);
                                }
                                builder.videoQuality = videoQuality;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    videoMaterialType = (VideoMaterialType) this.mVideoMaterialTypeParser.parse(jsonParser);
                                }
                                builder.videoMaterialType = videoMaterialType;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.bookmarkUpdateTime = date;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DownloadMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
