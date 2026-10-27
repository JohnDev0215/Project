package com.amazon.atv.title.v1.fragment.acquisition.v1;

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
public class RentalTreatment {
    public final long rentalEOSMillis;
    public final long rentalWindowMillis;

    private RentalTreatment(Builder builder) {
        this.rentalWindowMillis = builder.rentalWindowMillis;
        this.rentalEOSMillis = builder.rentalEOSMillis;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RentalTreatment)) {
            return false;
        }
        RentalTreatment rentalTreatment = (RentalTreatment) obj;
        return Objects.equal(Long.valueOf(this.rentalWindowMillis), Long.valueOf(rentalTreatment.rentalWindowMillis)) && Objects.equal(Long.valueOf(this.rentalEOSMillis), Long.valueOf(rentalTreatment.rentalEOSMillis));
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.rentalWindowMillis), Long.valueOf(this.rentalEOSMillis));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("rentalWindowMillis", this.rentalWindowMillis).add("rentalEOSMillis", this.rentalEOSMillis).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public long rentalEOSMillis;
        public long rentalWindowMillis;

        public RentalTreatment build() {
            return new RentalTreatment(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RentalTreatment> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RentalTreatment parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RentalTreatment:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RentalTreatment parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RentalTreatment:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private RentalTreatment parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "RentalTreatment");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -253564518) {
                        if (iHashCode == 1201179627 && next.equals("rentalEOSMillis")) {
                            b = 1;
                        }
                    } else if (next.equals("rentalWindowMillis")) {
                        b = 0;
                    }
                    if (b != 0) {
                        if (b == 1) {
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field rentalEOSMillis can't be null");
                            }
                            builder.rentalEOSMillis = SimpleParsers.parsePrimitiveLong(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field rentalWindowMillis can't be null");
                        }
                        builder.rentalWindowMillis = SimpleParsers.parsePrimitiveLong(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RentalTreatment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private RentalTreatment parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -253564518) {
                                if (iHashCode == 1201179627 && currentName.equals("rentalEOSMillis")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("rentalWindowMillis")) {
                                b = 0;
                            }
                            if (b != 0) {
                                if (b == 1) {
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field rentalEOSMillis can't be null");
                                    }
                                    builder.rentalEOSMillis = SimpleParsers.parsePrimitiveLong(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field rentalWindowMillis can't be null");
                                }
                                builder.rentalWindowMillis = SimpleParsers.parsePrimitiveLong(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RentalTreatment so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
