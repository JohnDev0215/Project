package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition.TvodAcquisition;
import com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition.TvodAcquisitionGroupLevelSuppression;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class TvodAcquisitions {
    public final Optional<ImmutableList<TvodAcquisition>> offers;
    public final Optional<ImmutableList<TvodAcquisitionGroupLevelSuppression>> suppression;

    private TvodAcquisitions(Builder builder) {
        this.offers = Optional.fromNullable(builder.offers);
        this.suppression = Optional.fromNullable(builder.suppression);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TvodAcquisitions)) {
            return false;
        }
        TvodAcquisitions tvodAcquisitions = (TvodAcquisitions) obj;
        return Objects.equal(this.offers, tvodAcquisitions.offers) && Objects.equal(this.suppression, tvodAcquisitions.suppression);
    }

    public int hashCode() {
        return Objects.hashCode(this.offers, this.suppression);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("offers", this.offers).add("suppression", this.suppression).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<TvodAcquisition> offers;
        public ImmutableList<TvodAcquisitionGroupLevelSuppression> suppression;

        public TvodAcquisitions build() {
            return new TvodAcquisitions(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TvodAcquisitions> {
        private final ListParser<TvodAcquisitionGroupLevelSuppression> mTvodAcquisitionGroupLevelSuppressionListParser;
        private final ListParser<TvodAcquisition> mTvodAcquisitionListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTvodAcquisitionListParser = ListParser.newParser(new TvodAcquisition.Parser(objectMapper));
            this.mTvodAcquisitionGroupLevelSuppressionListParser = ListParser.newParser(new TvodAcquisitionGroupLevelSuppression.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TvodAcquisitions parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TvodAcquisitions:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TvodAcquisitions parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TvodAcquisitions:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private TvodAcquisitions parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TvodAcquisitions");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1019793001) {
                        if (iHashCode == 439056691 && next.equals("suppression")) {
                            b = 1;
                        }
                    } else if (next.equals("offers")) {
                        b = 0;
                    }
                    ImmutableList<TvodAcquisitionGroupLevelSuppression> immutableList = null;
                    ImmutableList<TvodAcquisition> immutableList2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mTvodAcquisitionListParser.parse(jsonNode2);
                        }
                        builder.offers = immutableList2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mTvodAcquisitionGroupLevelSuppressionListParser.parse(jsonNode2);
                        }
                        builder.suppression = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TvodAcquisitions so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private TvodAcquisitions parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1019793001) {
                                if (iHashCode == 439056691 && currentName.equals("suppression")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("offers")) {
                                b = 0;
                            }
                            ImmutableList<TvodAcquisitionGroupLevelSuppression> immutableList = null;
                            ImmutableList<TvodAcquisition> immutableList2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mTvodAcquisitionListParser.parse(jsonParser);
                                }
                                builder.offers = immutableList2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mTvodAcquisitionGroupLevelSuppressionListParser.parse(jsonParser);
                                }
                                builder.suppression = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TvodAcquisitions so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
