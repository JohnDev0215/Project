package com.amazon.avtitleactionaggregationservice.model.visualcues;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avtitleactionaggregationservice.model.TitleActionAggregationException;
import com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition.Acquisition;
import com.amazon.avtitleactionaggregationservice.model.visualcues.entitlement.Entitlement;
import com.amazon.avtitleactionaggregationservice.model.visualcues.metadata.TitleMetadata;
import com.amazon.avtitleactionaggregationservice.model.visualcues.titleattributes.TitleAttributes;
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
public class TitleActionsCuesResponse {
    public final Optional<Acquisition> acquisition;
    public final Optional<Entitlement> entitlement;
    public final Optional<ImmutableList<TitleActionAggregationException>> exceptions;
    public final Optional<TitleAttributes> titleAttributes;
    public final Optional<TitleMetadata> titleMetadata;

    private TitleActionsCuesResponse(Builder builder) {
        this.titleMetadata = Optional.fromNullable(builder.titleMetadata);
        this.exceptions = Optional.fromNullable(builder.exceptions);
        this.acquisition = Optional.fromNullable(builder.acquisition);
        this.entitlement = Optional.fromNullable(builder.entitlement);
        this.titleAttributes = Optional.fromNullable(builder.titleAttributes);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleActionsCuesResponse)) {
            return false;
        }
        TitleActionsCuesResponse titleActionsCuesResponse = (TitleActionsCuesResponse) obj;
        return Objects.equal(this.titleMetadata, titleActionsCuesResponse.titleMetadata) && Objects.equal(this.exceptions, titleActionsCuesResponse.exceptions) && Objects.equal(this.acquisition, titleActionsCuesResponse.acquisition) && Objects.equal(this.entitlement, titleActionsCuesResponse.entitlement) && Objects.equal(this.titleAttributes, titleActionsCuesResponse.titleAttributes);
    }

    public int hashCode() {
        return Objects.hashCode(this.titleMetadata, this.exceptions, this.acquisition, this.entitlement, this.titleAttributes);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("titleMetadata", this.titleMetadata).add("exceptions", this.exceptions).add("acquisition", this.acquisition).add("entitlement", this.entitlement).add("titleAttributes", this.titleAttributes).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Acquisition acquisition;
        public Entitlement entitlement;
        public ImmutableList<TitleActionAggregationException> exceptions;
        public TitleAttributes titleAttributes;
        public TitleMetadata titleMetadata;

        public TitleActionsCuesResponse build() {
            return new TitleActionsCuesResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TitleActionsCuesResponse> {
        private final Acquisition.Parser mAcquisitionParser;
        private final Entitlement.Parser mEntitlementParser;
        private final ListParser<TitleActionAggregationException> mExceptionListParser;
        private final TitleAttributes.Parser mTitleAttributesParser;
        private final TitleMetadata.Parser mTitleMetadataParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAcquisitionParser = new Acquisition.Parser(objectMapper);
            this.mTitleMetadataParser = new TitleMetadata.Parser(objectMapper);
            this.mExceptionListParser = ListParser.newParser(new TitleActionAggregationException.Parser(objectMapper));
            this.mEntitlementParser = new Entitlement.Parser(objectMapper);
            this.mTitleAttributesParser = new TitleAttributes.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TitleActionsCuesResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleActionsCuesResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TitleActionsCuesResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleActionsCuesResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TitleActionsCuesResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TitleActionsCuesResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1314244092:
                            if (next.equals("exceptions")) {
                                b = 1;
                            }
                            break;
                        case -85026353:
                            if (next.equals("titleAttributes")) {
                                b = 4;
                            }
                            break;
                        case 702556583:
                            if (next.equals("titleMetadata")) {
                                b = 0;
                            }
                            break;
                        case 914844973:
                            if (next.equals("entitlement")) {
                                b = 3;
                            }
                            break;
                        case 1272352653:
                            if (next.equals("acquisition")) {
                                b = 2;
                            }
                            break;
                    }
                    TitleAttributes titleAttributes = null;
                    TitleMetadata titleMetadata = null;
                    ImmutableList<TitleActionAggregationException> immutableList = null;
                    Acquisition acquisition = null;
                    Entitlement entitlement = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            titleMetadata = this.mTitleMetadataParser.parse(jsonNode2);
                        }
                        builder.titleMetadata = titleMetadata;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mExceptionListParser.parse(jsonNode2);
                        }
                        builder.exceptions = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            acquisition = this.mAcquisitionParser.parse(jsonNode2);
                        }
                        builder.acquisition = acquisition;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            entitlement = this.mEntitlementParser.parse(jsonNode2);
                        }
                        builder.entitlement = entitlement;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            titleAttributes = this.mTitleAttributesParser.parse(jsonNode2);
                        }
                        builder.titleAttributes = titleAttributes;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleActionsCuesResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TitleActionsCuesResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            switch (currentName.hashCode()) {
                                case -1314244092:
                                    if (currentName.equals("exceptions")) {
                                        b = 1;
                                    }
                                    break;
                                case -85026353:
                                    if (currentName.equals("titleAttributes")) {
                                        b = 4;
                                    }
                                    break;
                                case 702556583:
                                    if (currentName.equals("titleMetadata")) {
                                        b = 0;
                                    }
                                    break;
                                case 914844973:
                                    if (currentName.equals("entitlement")) {
                                        b = 3;
                                    }
                                    break;
                                case 1272352653:
                                    if (currentName.equals("acquisition")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            TitleAttributes titleAttributes = null;
                            TitleMetadata titleMetadata = null;
                            ImmutableList<TitleActionAggregationException> immutableList = null;
                            Acquisition acquisition = null;
                            Entitlement entitlement = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    titleMetadata = this.mTitleMetadataParser.parse(jsonParser);
                                }
                                builder.titleMetadata = titleMetadata;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mExceptionListParser.parse(jsonParser);
                                }
                                builder.exceptions = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    acquisition = this.mAcquisitionParser.parse(jsonParser);
                                }
                                builder.acquisition = acquisition;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    entitlement = this.mEntitlementParser.parse(jsonParser);
                                }
                                builder.entitlement = entitlement;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    titleAttributes = this.mTitleAttributesParser.parse(jsonParser);
                                }
                                builder.titleAttributes = titleAttributes;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleActionsCuesResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
