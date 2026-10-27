package com.amazon.atv.purchase;

import com.amazon.atv.purchase.LocalizedMessage;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
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
public class OutOfBand {
    public final Optional<LocalizedMessage> message;
    public final Optional<LocalizedMessage> title;

    private OutOfBand(Builder builder) {
        this.message = Optional.fromNullable(builder.message);
        this.title = Optional.fromNullable(builder.title);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OutOfBand)) {
            return false;
        }
        OutOfBand outOfBand = (OutOfBand) obj;
        return Objects.equal(this.message, outOfBand.message) && Objects.equal(this.title, outOfBand.title);
    }

    public int hashCode() {
        return Objects.hashCode(this.message, this.title);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("message", this.message).add("title", this.title).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public LocalizedMessage message;
        public LocalizedMessage title;

        public OutOfBand build() {
            return new OutOfBand(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<OutOfBand> {
        private final LocalizedMessage.Parser mLocalizedMessageParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mLocalizedMessageParser = new LocalizedMessage.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public OutOfBand parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("OutOfBand:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public OutOfBand parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("OutOfBand:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private OutOfBand parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "OutOfBand");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 110371416) {
                        if (iHashCode == 954925063 && next.equals("message")) {
                            b = 0;
                        }
                    } else if (next.equals("title")) {
                        b = 1;
                    }
                    LocalizedMessage localizedMessage = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            localizedMessage = this.mLocalizedMessageParser.parse(jsonNode2);
                        }
                        builder.message = localizedMessage;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            localizedMessage = this.mLocalizedMessageParser.parse(jsonNode2);
                        }
                        builder.title = localizedMessage;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing OutOfBand so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private OutOfBand parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 110371416) {
                                if (iHashCode == 954925063 && currentName.equals("message")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("title")) {
                                b = 1;
                            }
                            LocalizedMessage localizedMessage = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    localizedMessage = this.mLocalizedMessageParser.parse(jsonParser);
                                }
                                builder.message = localizedMessage;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    localizedMessage = this.mLocalizedMessageParser.parse(jsonParser);
                                }
                                builder.title = localizedMessage;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing OutOfBand so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
