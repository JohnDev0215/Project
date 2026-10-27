package com.amazon.avtitleactionaggregationservice.model.detailpage;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avtitleactionaggregationservice.model.VideoQuality;
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
public class DetailPageFeatures {
    public final Optional<ImmutableList<ClientCapability>> clientCapabilities;
    public final Optional<ImmutableList<FormFactor>> formFactor;
    public final Optional<ImmutableList<OfferFlag>> offerFlags;
    public final Optional<ImmutableList<VideoQuality>> videoQualities;

    private DetailPageFeatures(Builder builder) {
        this.clientCapabilities = Optional.fromNullable(builder.clientCapabilities);
        this.videoQualities = Optional.fromNullable(builder.videoQualities);
        this.formFactor = Optional.fromNullable(builder.formFactor);
        this.offerFlags = Optional.fromNullable(builder.offerFlags);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DetailPageFeatures)) {
            return false;
        }
        DetailPageFeatures detailPageFeatures = (DetailPageFeatures) obj;
        return Objects.equal(this.clientCapabilities, detailPageFeatures.clientCapabilities) && Objects.equal(this.videoQualities, detailPageFeatures.videoQualities) && Objects.equal(this.formFactor, detailPageFeatures.formFactor) && Objects.equal(this.offerFlags, detailPageFeatures.offerFlags);
    }

    public int hashCode() {
        return Objects.hashCode(this.clientCapabilities, this.videoQualities, this.formFactor, this.offerFlags);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("clientCapabilities", this.clientCapabilities).add("videoQualities", this.videoQualities).add("formFactor", this.formFactor).add("offerFlags", this.offerFlags).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<ClientCapability> clientCapabilities;
        public ImmutableList<FormFactor> formFactor;
        public ImmutableList<OfferFlag> offerFlags;
        public ImmutableList<VideoQuality> videoQualities;

        public DetailPageFeatures build() {
            return new DetailPageFeatures(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DetailPageFeatures> {
        private final ListParser<ClientCapability> mClientCapabilitiesListParser;
        private final ListParser<FormFactor> mFormFactorListParser;
        private final ListParser<OfferFlag> mOfferFlagsListParser;
        private final ListParser<VideoQuality> mVideoQualityListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mClientCapabilitiesListParser = ListParser.newParser(EnumParser.newParser(ClientCapability.class));
            this.mVideoQualityListParser = ListParser.newParser(EnumParser.newParser(VideoQuality.class));
            this.mFormFactorListParser = ListParser.newParser(EnumParser.newParser(FormFactor.class));
            this.mOfferFlagsListParser = ListParser.newParser(EnumParser.newParser(OfferFlag.class));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DetailPageFeatures parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DetailPageFeatures:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DetailPageFeatures parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DetailPageFeatures:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DetailPageFeatures parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DetailPageFeatures");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -930729054:
                            if (next.equals("videoQualities")) {
                                b = 1;
                            }
                            break;
                        case -700121311:
                            if (next.equals("clientCapabilities")) {
                                b = 0;
                            }
                            break;
                        case -466268365:
                            if (next.equals("formFactor")) {
                                b = 2;
                            }
                            break;
                        case 138345867:
                            if (next.equals("offerFlags")) {
                                b = 3;
                            }
                            break;
                    }
                    ImmutableList<OfferFlag> immutableList = null;
                    ImmutableList<ClientCapability> immutableList2 = null;
                    ImmutableList<VideoQuality> immutableList3 = null;
                    ImmutableList<FormFactor> immutableList4 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mClientCapabilitiesListParser.parse(jsonNode2);
                        }
                        builder.clientCapabilities = immutableList2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList3 = this.mVideoQualityListParser.parse(jsonNode2);
                        }
                        builder.videoQualities = immutableList3;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList4 = this.mFormFactorListParser.parse(jsonNode2);
                        }
                        builder.formFactor = immutableList4;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mOfferFlagsListParser.parse(jsonNode2);
                        }
                        builder.offerFlags = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DetailPageFeatures so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DetailPageFeatures parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -930729054:
                                    if (currentName.equals("videoQualities")) {
                                        b = 1;
                                    }
                                    break;
                                case -700121311:
                                    if (currentName.equals("clientCapabilities")) {
                                        b = 0;
                                    }
                                    break;
                                case -466268365:
                                    if (currentName.equals("formFactor")) {
                                        b = 2;
                                    }
                                    break;
                                case 138345867:
                                    if (currentName.equals("offerFlags")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            ImmutableList<OfferFlag> immutableList = null;
                            ImmutableList<ClientCapability> immutableList2 = null;
                            ImmutableList<VideoQuality> immutableList3 = null;
                            ImmutableList<FormFactor> immutableList4 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mClientCapabilitiesListParser.parse(jsonParser);
                                }
                                builder.clientCapabilities = immutableList2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList3 = this.mVideoQualityListParser.parse(jsonParser);
                                }
                                builder.videoQualities = immutableList3;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList4 = this.mFormFactorListParser.parse(jsonParser);
                                }
                                builder.formFactor = immutableList4;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mOfferFlagsListParser.parse(jsonParser);
                                }
                                builder.offerFlags = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DetailPageFeatures so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
