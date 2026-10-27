package com.amazon.avod.playbackresourcev2;

import android.annotation.SuppressLint;
import com.amazon.atvplaybackresource.PRSException;
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
public class Prsv2Error {
    private final Optional<String> mCode;
    private final Optional<String> mMessage;

    Prsv2Error(@Nonnull Builder builder) {
        this.mCode = Optional.fromNullable(builder.mCode);
        this.mMessage = Optional.fromNullable(builder.mMessage);
    }

    @Nonnull
    public Optional<String> getCode() {
        return this.mCode;
    }

    @Nonnull
    public Optional<String> getMessage() {
        return this.mMessage;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("code", this.mCode).add("message", this.mMessage).toString();
    }

    public static class Builder {
        public String mCode;
        public String mMessage;

        @Nonnull
        Prsv2Error build() {
            return new Prsv2Error(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<Prsv2Error> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, Prsv2Error.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        public Prsv2Error parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Prsv2Error");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 3059181) {
                        if (iHashCode == 954925063 && next.equals("message")) {
                            b = 1;
                        }
                    } else if (next.equals("code")) {
                        b = 0;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mCode = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mMessage = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Prsv2Error so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        public Prsv2Error parseInternal(@Nonnull JsonParser jsonParser) throws IOException {
            Builder builder = new Builder();
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
                        if (iHashCode != 3059181) {
                            if (iHashCode == 954925063 && currentName.equals("message")) {
                                b = 1;
                            }
                        } else if (currentName.equals("code")) {
                            b = 0;
                        }
                        String str = null;
                        if (b == 0) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                str = this.mStringParser.parse(jsonParser);
                            }
                            builder.mCode = str;
                        } else if (b == 1) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                str = this.mStringParser.parse(jsonParser);
                            }
                            builder.mMessage = str;
                        } else {
                            jsonParser.skipChildren();
                        }
                    } catch (JsonContractException e) {
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Prsv2Error so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                }
                jsonTokenNextToken = jsonParser.nextToken();
            }
            return builder.build();
        }
    }

    @Nonnull
    @SuppressLint({"VisibleForTests"})
    public static PRSException transformToPRSException(@Nonnull Prsv2Error prsv2Error) {
        PRSException.Builder builder = new PRSException.Builder();
        builder.errorCode = prsv2Error.getCode().orNull();
        builder.message = prsv2Error.getMessage().orNull();
        return builder.build();
    }
}
