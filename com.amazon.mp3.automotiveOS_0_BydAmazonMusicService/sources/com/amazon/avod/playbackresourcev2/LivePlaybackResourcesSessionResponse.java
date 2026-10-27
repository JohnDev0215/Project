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
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class LivePlaybackResourcesSessionResponse {
    private final Optional<String> mToken;

    LivePlaybackResourcesSessionResponse(@Nonnull Builder builder) {
        this.mToken = Optional.fromNullable(builder.mToken);
    }

    @Nonnull
    public Optional<String> getToken() {
        return this.mToken;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("token", this.mToken).toString();
    }

    static class Builder {
        private String mToken;

        Builder() {
        }

        LivePlaybackResourcesSessionResponse build() {
            return new LivePlaybackResourcesSessionResponse(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<LivePlaybackResourcesSessionResponse> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, LivePlaybackResourcesSessionResponse.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        public LivePlaybackResourcesSessionResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LivePlaybackResourcesSessionResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                try {
                    if (next.equals("token")) {
                        builder.mToken = jsonNode2.isNull() ? null : this.mStringParser.parse(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse LivePlaybackResourcesSessionResponseso we may drop this from the response (if field was required).Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        public LivePlaybackResourcesSessionResponse parseInternal(@Nonnull JsonParser jsonParser) throws IOException {
            Builder builder = new Builder();
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            while (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    jsonParser.nextToken();
                    JsonToken currentToken = jsonParser.getCurrentToken();
                    try {
                        if (currentName.equals("token")) {
                            builder.mToken = currentToken == JsonToken.VALUE_NULL ? null : this.mStringParser.parse(jsonParser);
                        }
                    } catch (JsonContractException e) {
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse LivePlaybackResourcesSessionResponseso we may drop this from the response (if field was required).Will try to continue parsing.", new Object[0]);
                    }
                }
                jsonTokenNextToken = jsonParser.nextToken();
            }
            return builder.build();
        }
    }
}
