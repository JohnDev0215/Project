package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.SyeCustomerLocation;
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

/* JADX INFO: loaded from: classes5.dex */
public class SyeCustomer {
    public final Optional<String> customerIdentityContext;
    public final Optional<SyeCustomerLocation> location;

    private SyeCustomer(Builder builder) {
        this.customerIdentityContext = Optional.fromNullable(builder.customerIdentityContext);
        this.location = Optional.fromNullable(builder.location);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SyeCustomer)) {
            return false;
        }
        SyeCustomer syeCustomer = (SyeCustomer) obj;
        return Objects.equal(this.customerIdentityContext, syeCustomer.customerIdentityContext) && Objects.equal(this.location, syeCustomer.location);
    }

    public int hashCode() {
        return Objects.hashCode(this.customerIdentityContext, this.location);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("customerIdentityContext", this.customerIdentityContext).add("location", this.location).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String customerIdentityContext;
        public SyeCustomerLocation location;

        public SyeCustomer build() {
            return new SyeCustomer(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SyeCustomer> {
        private final SyeCustomerLocation.Parser mSyeCustomerLocationParser;
        private final SimpleParsers.StringParser midentityContextParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSyeCustomerLocationParser = new SyeCustomerLocation.Parser(objectMapper);
            this.midentityContextParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SyeCustomer parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeCustomer:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SyeCustomer parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeCustomer:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private SyeCustomer parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SyeCustomer");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1079350445) {
                        if (iHashCode == 1901043637 && next.equals("location")) {
                            b = 1;
                        }
                    } else if (next.equals("customerIdentityContext")) {
                        b = 0;
                    }
                    SyeCustomerLocation syeCustomerLocation = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.midentityContextParser.parse(jsonNode2);
                        }
                        builder.customerIdentityContext = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            syeCustomerLocation = this.mSyeCustomerLocationParser.parse(jsonNode2);
                        }
                        builder.location = syeCustomerLocation;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SyeCustomer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private SyeCustomer parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1079350445) {
                                if (iHashCode == 1901043637 && currentName.equals("location")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("customerIdentityContext")) {
                                b = 0;
                            }
                            SyeCustomerLocation syeCustomerLocation = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.midentityContextParser.parse(jsonParser);
                                }
                                builder.customerIdentityContext = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    syeCustomerLocation = this.mSyeCustomerLocationParser.parse(jsonParser);
                                }
                                builder.location = syeCustomerLocation;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SyeCustomer so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
