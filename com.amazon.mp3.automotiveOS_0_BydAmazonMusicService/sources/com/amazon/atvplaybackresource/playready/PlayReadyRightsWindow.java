package com.amazon.atvplaybackresource.playready;

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

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyRightsWindow {
    public final Optional<String> endTime;
    public final Optional<String> startTime;

    private PlayReadyRightsWindow(Builder builder) {
        this.endTime = Optional.fromNullable(builder.endTime);
        this.startTime = Optional.fromNullable(builder.startTime);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlayReadyRightsWindow)) {
            return false;
        }
        PlayReadyRightsWindow playReadyRightsWindow = (PlayReadyRightsWindow) obj;
        return Objects.equal(this.endTime, playReadyRightsWindow.endTime) && Objects.equal(this.startTime, playReadyRightsWindow.startTime);
    }

    public int hashCode() {
        return Objects.hashCode(this.endTime, this.startTime);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("endTime", this.endTime).add("startTime", this.startTime).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String endTime;
        public String startTime;

        public PlayReadyRightsWindow build() {
            return new PlayReadyRightsWindow(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlayReadyRightsWindow> {
        private final SimpleParsers.StringParser mIso8601TimestampParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mIso8601TimestampParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlayReadyRightsWindow parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlayReadyRightsWindow:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlayReadyRightsWindow parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlayReadyRightsWindow:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PlayReadyRightsWindow parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlayReadyRightsWindow");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -2129294769) {
                        if (iHashCode == -1607243192 && next.equals("endTime")) {
                            b = 0;
                        }
                    } else if (next.equals("startTime")) {
                        b = 1;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mIso8601TimestampParser.parse(jsonNode2);
                        }
                        builder.endTime = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mIso8601TimestampParser.parse(jsonNode2);
                        }
                        builder.startTime = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlayReadyRightsWindow so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private PlayReadyRightsWindow parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -2129294769) {
                                if (iHashCode == -1607243192 && currentName.equals("endTime")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("startTime")) {
                                b = 1;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mIso8601TimestampParser.parse(jsonParser);
                                }
                                builder.endTime = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mIso8601TimestampParser.parse(jsonParser);
                                }
                                builder.startTime = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlayReadyRightsWindow so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
