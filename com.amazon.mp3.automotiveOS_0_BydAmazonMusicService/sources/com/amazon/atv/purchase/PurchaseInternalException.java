package com.amazon.atv.purchase;

import com.amazon.atv.purchase.ErrorAction;
import com.amazon.atv.purchase.PurchaseServiceException;
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
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PurchaseInternalException extends PurchaseServiceException {
    private PurchaseInternalException(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.atv.purchase.PurchaseServiceException
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PurchaseInternalException)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.atv.purchase.PurchaseServiceException
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    @Override // com.amazon.atv.purchase.PurchaseServiceException
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @NotThreadSafe
    public static class Builder extends PurchaseServiceException.Builder {
        @Override // com.amazon.atv.purchase.PurchaseServiceException.Builder
        public PurchaseInternalException build() {
            return new PurchaseInternalException(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PurchaseInternalException> {
        private final ErrorAction.Parser mErrorActionParser;
        private final SimpleParsers.StringParser mexceptionMessageParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mErrorActionParser = new ErrorAction.Parser(objectMapper);
            this.mexceptionMessageParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PurchaseInternalException parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PurchaseInternalException:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PurchaseInternalException parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PurchaseInternalException:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private PurchaseInternalException parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PurchaseInternalException");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1692035490) {
                        if (iHashCode == 954925063 && next.equals("message")) {
                            b = 1;
                        }
                    } else if (next.equals("errorAction")) {
                        b = 0;
                    }
                    String str = null;
                    ErrorAction errorAction = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            errorAction = this.mErrorActionParser.parse(jsonNode2);
                        }
                        builder.errorAction = errorAction;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mexceptionMessageParser.parse(jsonNode2);
                        }
                        builder.message = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PurchaseInternalException so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.errorAction, this, "errorAction");
            return builder.build();
        }

        @Nonnull
        private PurchaseInternalException parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1692035490) {
                                if (iHashCode == 954925063 && currentName.equals("message")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("errorAction")) {
                                b = 0;
                            }
                            String str = null;
                            ErrorAction errorAction = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    errorAction = this.mErrorActionParser.parse(jsonParser);
                                }
                                builder.errorAction = errorAction;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mexceptionMessageParser.parse(jsonParser);
                                }
                                builder.message = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PurchaseInternalException so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.errorAction, this, "errorAction");
                    return builder.build();
                }
            }
        }
    }
}
