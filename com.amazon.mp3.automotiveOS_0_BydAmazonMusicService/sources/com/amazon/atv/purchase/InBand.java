package com.amazon.atv.purchase;

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
public class InBand {
    public final Optional<String> authenticationSourceId;
    public final Optional<String> paymentMethodId;

    private InBand(Builder builder) {
        this.paymentMethodId = Optional.fromNullable(builder.paymentMethodId);
        this.authenticationSourceId = Optional.fromNullable(builder.authenticationSourceId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InBand)) {
            return false;
        }
        InBand inBand = (InBand) obj;
        return Objects.equal(this.paymentMethodId, inBand.paymentMethodId) && Objects.equal(this.authenticationSourceId, inBand.authenticationSourceId);
    }

    public int hashCode() {
        return Objects.hashCode(this.paymentMethodId, this.authenticationSourceId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("paymentMethodId", this.paymentMethodId).add("authenticationSourceId", this.authenticationSourceId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String authenticationSourceId;
        public String paymentMethodId;

        public InBand build() {
            return new InBand(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<InBand> {
        private final SimpleParsers.StringParser mauthenticationSourceIdParser;
        private final SimpleParsers.StringParser mpaymentMethodIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mpaymentMethodIdParser = stringParser;
            this.mauthenticationSourceIdParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public InBand parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("InBand:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public InBand parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("InBand:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private InBand parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "InBand");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1244375550) {
                        if (iHashCode == -659872466 && next.equals("authenticationSourceId")) {
                            b = 1;
                        }
                    } else if (next.equals("paymentMethodId")) {
                        b = 0;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mpaymentMethodIdParser.parse(jsonNode2);
                        }
                        builder.paymentMethodId = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mauthenticationSourceIdParser.parse(jsonNode2);
                        }
                        builder.authenticationSourceId = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing InBand so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private InBand parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1244375550) {
                                if (iHashCode == -659872466 && currentName.equals("authenticationSourceId")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("paymentMethodId")) {
                                b = 0;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mpaymentMethodIdParser.parse(jsonParser);
                                }
                                builder.paymentMethodId = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mauthenticationSourceIdParser.parse(jsonParser);
                                }
                                builder.authenticationSourceId = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing InBand so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
