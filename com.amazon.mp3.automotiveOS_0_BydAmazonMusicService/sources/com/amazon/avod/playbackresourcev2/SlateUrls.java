package com.amazon.avod.playbackresourcev2;

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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SlateUrls {
    private final String mIntroSlateImageUrls;
    private final String mOutroSlateImageUrls;

    SlateUrls(@Nonnull Builder builder) {
        this.mIntroSlateImageUrls = builder.mIntroSlateImageUrls;
        this.mOutroSlateImageUrls = builder.mOutroSlateImageUrls;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("introSlate", this.mIntroSlateImageUrls).add("outroSlate", this.mOutroSlateImageUrls).toString();
    }

    static class Builder {
        private String mIntroSlateImageUrls;
        private String mOutroSlateImageUrls;

        Builder() {
        }

        SlateUrls build() {
            return new SlateUrls(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<SlateUrls> {
        private static final String SLATE_INTRO_IMAGE_URL_RESPONSE_KEY = "intro";
        private static final String SLATE_OUTRO_IMAGE_URL_RESPONSE_KEY = "outro";
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, SlateUrls.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        public SlateUrls parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SlateUrls");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 100361836) {
                        if (iHashCode == 106111499 && next.equals(SLATE_OUTRO_IMAGE_URL_RESPONSE_KEY)) {
                            b = 1;
                        }
                    } else if (next.equals(SLATE_INTRO_IMAGE_URL_RESPONSE_KEY)) {
                        b = 0;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mIntroSlateImageUrls = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mOutroSlateImageUrls = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SlateUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        public SlateUrls parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            return parseInternal(jsonParser, new Builder());
        }

        protected SlateUrls parseInternal(JsonParser jsonParser, Builder builder) throws IOException {
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
                        if (iHashCode != 100361836) {
                            if (iHashCode == 106111499 && currentName.equals(SLATE_OUTRO_IMAGE_URL_RESPONSE_KEY)) {
                                b = 1;
                            }
                        } else if (currentName.equals(SLATE_INTRO_IMAGE_URL_RESPONSE_KEY)) {
                            b = 0;
                        }
                        String str = null;
                        if (b == 0) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                str = this.mStringParser.parse(jsonParser);
                            }
                            builder.mIntroSlateImageUrls = str;
                        } else if (b == 1) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                str = this.mStringParser.parse(jsonParser);
                            }
                            builder.mOutroSlateImageUrls = str;
                        } else {
                            jsonParser.skipChildren();
                        }
                    } catch (JsonContractException e) {
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SlateUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                }
                jsonTokenNextToken = jsonParser.nextToken();
            }
            return builder.build();
        }
    }
}
