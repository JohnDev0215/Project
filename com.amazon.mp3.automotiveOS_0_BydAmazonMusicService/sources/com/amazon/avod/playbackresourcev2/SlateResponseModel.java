package com.amazon.avod.playbackresourcev2;

import com.amazon.avod.playbackresourcev2.SlateUrls;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SlateResponseModel {
    private final SlateUrls mHDSlateUrls;
    private final SlateUrls mSDSlateUrls;
    private final Integer mSlateMaxDisplayDurationInSeconds;

    SlateResponseModel(@Nonnull Builder builder) {
        Preconditions.checkNotNull(builder, "builder");
        this.mSlateMaxDisplayDurationInSeconds = builder.mSlateMaxDisplayDurationInSeconds;
        this.mSDSlateUrls = builder.mSDSlateUrls;
        this.mHDSlateUrls = builder.mHDSlateUrls;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("maxDisplayDurationInSec", this.mSlateMaxDisplayDurationInSeconds).add("sdSlateUrls", this.mSDSlateUrls.toString()).add("hdSlateUrls", this.mHDSlateUrls).toString();
    }

    static class Builder {
        private SlateUrls mHDSlateUrls;
        private SlateUrls mSDSlateUrls;
        private Integer mSlateMaxDisplayDurationInSeconds;

        Builder() {
        }

        SlateResponseModel build() {
            return new SlateResponseModel(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<SlateResponseModel> {
        private static final String HD_SLATE_IMAGE_URLS_RESPONSE_KEY = "hd";
        private static final String SD_SLATE_IMAGE_URLS_RESPONSE_KEY = "sd";
        private static final String SLATE_MAX_DISPLAY_DURATION_RESPONSE_KEY = "maxDuration";
        private final SimpleParsers.IntegerParser mIntegerParser;
        private final SlateUrls.Parser mSlateUrlsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, SlateResponseModel.class);
            this.mIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mSlateUrlsParser = new SlateUrls.Parser(objectMapper);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        public SlateResponseModel parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SlateResponseModel");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 3324) {
                        if (iHashCode != 3665) {
                            if (iHashCode == 40284952 && next.equals(SLATE_MAX_DISPLAY_DURATION_RESPONSE_KEY)) {
                                b = 0;
                            }
                        } else if (next.equals(SD_SLATE_IMAGE_URLS_RESPONSE_KEY)) {
                            b = 1;
                        }
                    } else if (next.equals(HD_SLATE_IMAGE_URLS_RESPONSE_KEY)) {
                        b = 2;
                    }
                    SlateUrls slateUrls = null;
                    Integer num = null;
                    SlateUrls slateUrls2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            num = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.mSlateMaxDisplayDurationInSeconds = num;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            slateUrls2 = this.mSlateUrlsParser.parse(jsonNode2);
                        }
                        builder.mSDSlateUrls = slateUrls2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            slateUrls = this.mSlateUrlsParser.parse(jsonNode2);
                        }
                        builder.mHDSlateUrls = slateUrls;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SlatesResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        public SlateResponseModel parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            return parseInternal(jsonParser, new Builder());
        }

        protected SlateResponseModel parseInternal(@Nonnull JsonParser jsonParser, @Nonnull Builder builder) throws IOException {
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            while (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    jsonParser.nextToken();
                    JsonToken currentToken = jsonParser.getCurrentToken();
                    byte b = -1;
                    try {
                        int iHashCode = currentName.hashCode();
                        if (iHashCode != 3324) {
                            if (iHashCode != 3665) {
                                if (iHashCode == 40284952 && currentName.equals(SLATE_MAX_DISPLAY_DURATION_RESPONSE_KEY)) {
                                    b = 0;
                                }
                            } else if (currentName.equals(SD_SLATE_IMAGE_URLS_RESPONSE_KEY)) {
                                b = 1;
                            }
                        } else if (currentName.equals(HD_SLATE_IMAGE_URLS_RESPONSE_KEY)) {
                            b = 2;
                        }
                        Integer num = null;
                        SlateUrls slateUrls = null;
                        SlateUrls slateUrls2 = null;
                        if (b == 0) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                num = this.mIntegerParser.parse(jsonParser);
                            }
                            builder.mSlateMaxDisplayDurationInSeconds = num;
                        } else if (b == 1) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                slateUrls2 = this.mSlateUrlsParser.parse(jsonParser);
                            }
                            builder.mSDSlateUrls = slateUrls2;
                        } else if (b == 2) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                slateUrls = this.mSlateUrlsParser.parse(jsonParser);
                            }
                            builder.mHDSlateUrls = slateUrls;
                        } else {
                            jsonParser.skipChildren();
                        }
                    } catch (JsonContractException e) {
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SlatesResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                }
                jsonTokenNextToken = jsonParser.nextToken();
            }
            return builder.build();
        }
    }
}
