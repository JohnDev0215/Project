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
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class BenefitAvailabilityWindow {
    public final Optional<VideoQuality> videoQuality;
    public final Optional<Date> windowEnd;
    public final Optional<Date> windowStart;

    private BenefitAvailabilityWindow(Builder builder) {
        this.windowStart = Optional.fromNullable(builder.windowStart);
        this.windowEnd = Optional.fromNullable(builder.windowEnd);
        this.videoQuality = Optional.fromNullable(builder.videoQuality);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BenefitAvailabilityWindow)) {
            return false;
        }
        BenefitAvailabilityWindow benefitAvailabilityWindow = (BenefitAvailabilityWindow) obj;
        return Objects.equal(this.windowStart, benefitAvailabilityWindow.windowStart) && Objects.equal(this.windowEnd, benefitAvailabilityWindow.windowEnd) && Objects.equal(this.videoQuality, benefitAvailabilityWindow.videoQuality);
    }

    public int hashCode() {
        return Objects.hashCode(this.windowStart, this.windowEnd, this.videoQuality);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("windowStart", this.windowStart).add("windowEnd", this.windowEnd).add("videoQuality", this.videoQuality).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public VideoQuality videoQuality;
        public Date windowEnd;
        public Date windowStart;

        public BenefitAvailabilityWindow build() {
            return new BenefitAvailabilityWindow(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<BenefitAvailabilityWindow> {
        private final SimpleParsers.DateParser mDateTimeParser;
        private final EnumParser<VideoQuality> mVideoQualityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDateTimeParser = SimpleParsers.DateParser.INSTANCE;
            this.mVideoQualityParser = EnumParser.newParser(VideoQuality.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public BenefitAvailabilityWindow parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BenefitAvailabilityWindow:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public BenefitAvailabilityWindow parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BenefitAvailabilityWindow:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private BenefitAvailabilityWindow parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "BenefitAvailabilityWindow");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -217026869) {
                        if (iHashCode != 504058884) {
                            if (iHashCode == 1903685202 && next.equals("windowStart")) {
                                b = 0;
                            }
                        } else if (next.equals("videoQuality")) {
                            b = 2;
                        }
                    } else if (next.equals("windowEnd")) {
                        b = 1;
                    }
                    VideoQuality videoQuality = null;
                    Date date = null;
                    Date date2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            date = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.windowStart = date;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            date2 = this.mDateTimeParser.parse(jsonNode2);
                        }
                        builder.windowEnd = date2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonNode2);
                        }
                        builder.videoQuality = videoQuality;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing BenefitAvailabilityWindow so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private BenefitAvailabilityWindow parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -217026869) {
                                if (iHashCode != 504058884) {
                                    if (iHashCode == 1903685202 && currentName.equals("windowStart")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("videoQuality")) {
                                    b = 2;
                                }
                            } else if (currentName.equals("windowEnd")) {
                                b = 1;
                            }
                            VideoQuality videoQuality = null;
                            Date date = null;
                            Date date2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.windowStart = date;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date2 = this.mDateTimeParser.parse(jsonParser);
                                }
                                builder.windowEnd = date2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    videoQuality = (VideoQuality) this.mVideoQualityParser.parse(jsonParser);
                                }
                                builder.videoQuality = videoQuality;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing BenefitAvailabilityWindow so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
