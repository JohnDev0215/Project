package com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition.PrimeAcquisitions;
import com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition.SvodAcquisitions;
import com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition.TvodAcquisitions;
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
public class Acquisition {
    public final Optional<PrimeAcquisitions> primeAcquisitions;
    public final Optional<SvodAcquisitions> svodAcquisitions;
    public final Optional<TvodAcquisitions> tvodAcquisitions;

    private Acquisition(Builder builder) {
        this.svodAcquisitions = Optional.fromNullable(builder.svodAcquisitions);
        this.tvodAcquisitions = Optional.fromNullable(builder.tvodAcquisitions);
        this.primeAcquisitions = Optional.fromNullable(builder.primeAcquisitions);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Acquisition)) {
            return false;
        }
        Acquisition acquisition = (Acquisition) obj;
        return Objects.equal(this.svodAcquisitions, acquisition.svodAcquisitions) && Objects.equal(this.tvodAcquisitions, acquisition.tvodAcquisitions) && Objects.equal(this.primeAcquisitions, acquisition.primeAcquisitions);
    }

    public int hashCode() {
        return Objects.hashCode(this.svodAcquisitions, this.tvodAcquisitions, this.primeAcquisitions);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("svodAcquisitions", this.svodAcquisitions).add("tvodAcquisitions", this.tvodAcquisitions).add("primeAcquisitions", this.primeAcquisitions).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public PrimeAcquisitions primeAcquisitions;
        public SvodAcquisitions svodAcquisitions;
        public TvodAcquisitions tvodAcquisitions;

        public Acquisition build() {
            return new Acquisition(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Acquisition> {
        private final PrimeAcquisitions.Parser mPrimeAcquisitionsParser;
        private final SvodAcquisitions.Parser mSvodAcquisitionsParser;
        private final TvodAcquisitions.Parser mTvodAcquisitionsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTvodAcquisitionsParser = new TvodAcquisitions.Parser(objectMapper);
            this.mSvodAcquisitionsParser = new SvodAcquisitions.Parser(objectMapper);
            this.mPrimeAcquisitionsParser = new PrimeAcquisitions.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Acquisition parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Acquisition:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Acquisition parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Acquisition:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Acquisition parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Acquisition");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -195350531) {
                        if (iHashCode != 315183646) {
                            if (iHashCode == 371958437 && next.equals("primeAcquisitions")) {
                                b = 2;
                            }
                        } else if (next.equals("svodAcquisitions")) {
                            b = 0;
                        }
                    } else if (next.equals("tvodAcquisitions")) {
                        b = 1;
                    }
                    PrimeAcquisitions primeAcquisitions = null;
                    SvodAcquisitions svodAcquisitions = null;
                    TvodAcquisitions tvodAcquisitions = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            svodAcquisitions = this.mSvodAcquisitionsParser.parse(jsonNode2);
                        }
                        builder.svodAcquisitions = svodAcquisitions;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            tvodAcquisitions = this.mTvodAcquisitionsParser.parse(jsonNode2);
                        }
                        builder.tvodAcquisitions = tvodAcquisitions;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            primeAcquisitions = this.mPrimeAcquisitionsParser.parse(jsonNode2);
                        }
                        builder.primeAcquisitions = primeAcquisitions;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Acquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private Acquisition parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -195350531) {
                                if (iHashCode != 315183646) {
                                    if (iHashCode == 371958437 && currentName.equals("primeAcquisitions")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("svodAcquisitions")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("tvodAcquisitions")) {
                                b = 1;
                            }
                            PrimeAcquisitions primeAcquisitions = null;
                            SvodAcquisitions svodAcquisitions = null;
                            TvodAcquisitions tvodAcquisitions = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    svodAcquisitions = this.mSvodAcquisitionsParser.parse(jsonParser);
                                }
                                builder.svodAcquisitions = svodAcquisitions;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    tvodAcquisitions = this.mTvodAcquisitionsParser.parse(jsonParser);
                                }
                                builder.tvodAcquisitions = tvodAcquisitions;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    primeAcquisitions = this.mPrimeAcquisitionsParser.parse(jsonParser);
                                }
                                builder.primeAcquisitions = primeAcquisitions;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Acquisition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
