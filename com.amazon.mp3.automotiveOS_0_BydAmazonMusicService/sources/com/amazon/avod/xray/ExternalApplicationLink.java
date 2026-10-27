package com.amazon.avod.xray;

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
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class ExternalApplicationLink {
    public final String applicationId;
    public final String asin;
    public final String productType;

    private ExternalApplicationLink(Builder builder) {
        this.productType = (String) Preconditions.checkNotNull(builder.productType, "Unexpected null field: productType");
        this.applicationId = (String) Preconditions.checkNotNull(builder.applicationId, "Unexpected null field: applicationId");
        this.asin = (String) Preconditions.checkNotNull(builder.asin, "Unexpected null field: asin");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExternalApplicationLink)) {
            return false;
        }
        ExternalApplicationLink externalApplicationLink = (ExternalApplicationLink) obj;
        return Objects.equal(this.productType, externalApplicationLink.productType) && Objects.equal(this.applicationId, externalApplicationLink.applicationId) && Objects.equal(this.asin, externalApplicationLink.asin);
    }

    public int hashCode() {
        return Objects.hashCode(this.productType, this.applicationId, this.asin);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("productType", this.productType).add("applicationId", this.applicationId).add("asin", this.asin).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String applicationId;
        public String asin;
        public String productType;

        public ExternalApplicationLink build() {
            return new ExternalApplicationLink(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ExternalApplicationLink> {
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ExternalApplicationLink parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ExternalApplicationLink:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ExternalApplicationLink parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ExternalApplicationLink:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private ExternalApplicationLink parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ExternalApplicationLink");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1491615543) {
                        if (iHashCode != -1011352949) {
                            if (iHashCode == 3003607 && next.equals("asin")) {
                                b = 2;
                            }
                        } else if (next.equals("applicationId")) {
                            b = 1;
                        }
                    } else if (next.equals("productType")) {
                        b = 0;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.productType = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.applicationId = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.asin = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ExternalApplicationLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.productType, this, "productType");
            JsonParsingUtils.checkNotNull(builder.applicationId, this, "applicationId");
            JsonParsingUtils.checkNotNull(builder.asin, this, "asin");
            return builder.build();
        }

        @Nonnull
        private ExternalApplicationLink parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1491615543) {
                                if (iHashCode != -1011352949) {
                                    if (iHashCode == 3003607 && currentName.equals("asin")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("applicationId")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("productType")) {
                                b = 0;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.productType = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.applicationId = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.asin = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ExternalApplicationLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.productType, this, "productType");
                    JsonParsingUtils.checkNotNull(builder.applicationId, this, "applicationId");
                    JsonParsingUtils.checkNotNull(builder.asin, this, "asin");
                    return builder.build();
                }
            }
        }
    }
}
