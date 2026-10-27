package com.amazon.atv.title.v1.fragment.playback.v2;

import com.amazon.atv.title.v1.fragment.playback.v2.DisplayTreatments;
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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class StreamingOption {
    public final Optional<String> asin;
    public final DisplayTreatments displayTreatments;

    private StreamingOption(Builder builder) {
        this.asin = Optional.fromNullable(builder.asin);
        this.displayTreatments = (DisplayTreatments) Preconditions.checkNotNull(builder.displayTreatments, "Unexpected null field: displayTreatments");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StreamingOption)) {
            return false;
        }
        StreamingOption streamingOption = (StreamingOption) obj;
        return Objects.equal(this.asin, streamingOption.asin) && Objects.equal(this.displayTreatments, streamingOption.displayTreatments);
    }

    public int hashCode() {
        return Objects.hashCode(this.asin, this.displayTreatments);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("asin", this.asin).add("displayTreatments", this.displayTreatments).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String asin;
        public DisplayTreatments displayTreatments;

        public StreamingOption build() {
            return new StreamingOption(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<StreamingOption> {
        private final DisplayTreatments.Parser mDisplayTreatmentsParser;
        private final SimpleParsers.StringParser mbasicStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mbasicStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mDisplayTreatmentsParser = new DisplayTreatments.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public StreamingOption parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("StreamingOption:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public StreamingOption parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("StreamingOption:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private StreamingOption parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "StreamingOption");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 3003607) {
                        if (iHashCode == 557711709 && next.equals("displayTreatments")) {
                            b = 1;
                        }
                    } else if (next.equals("asin")) {
                        b = 0;
                    }
                    DisplayTreatments displayTreatments = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.asin = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            displayTreatments = this.mDisplayTreatmentsParser.parse(jsonNode2);
                        }
                        builder.displayTreatments = displayTreatments;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing StreamingOption so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.displayTreatments, this, "displayTreatments");
            return builder.build();
        }

        @Nonnull
        private StreamingOption parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 3003607) {
                                if (iHashCode == 557711709 && currentName.equals("displayTreatments")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("asin")) {
                                b = 0;
                            }
                            DisplayTreatments displayTreatments = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.asin = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    displayTreatments = this.mDisplayTreatmentsParser.parse(jsonParser);
                                }
                                builder.displayTreatments = displayTreatments;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing StreamingOption so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.displayTreatments, this, "displayTreatments");
                    return builder.build();
                }
            }
        }
    }
}
