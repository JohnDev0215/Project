package com.amazon.avtitleactionaggregationservice.model.detailpage.download;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avtitleactionaggregationservice.model.detailpage.download.DownloadMetadata;
import com.amazon.avtitleactionaggregationservice.model.detailpage.download.Eligibility;
import com.amazon.avtitleactionaggregationservice.model.detailpage.download.EntitlementDetails;
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
public class Download {
    public final Optional<DownloadMetadata> downloadMetadata;
    public final Optional<Eligibility> eligibility;
    public final Optional<EntitlementDetails> entitlementDetails;

    private Download(Builder builder) {
        this.downloadMetadata = Optional.fromNullable(builder.downloadMetadata);
        this.eligibility = Optional.fromNullable(builder.eligibility);
        this.entitlementDetails = Optional.fromNullable(builder.entitlementDetails);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Download)) {
            return false;
        }
        Download download = (Download) obj;
        return Objects.equal(this.downloadMetadata, download.downloadMetadata) && Objects.equal(this.eligibility, download.eligibility) && Objects.equal(this.entitlementDetails, download.entitlementDetails);
    }

    public int hashCode() {
        return Objects.hashCode(this.downloadMetadata, this.eligibility, this.entitlementDetails);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("downloadMetadata", this.downloadMetadata).add("eligibility", this.eligibility).add("entitlementDetails", this.entitlementDetails).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public DownloadMetadata downloadMetadata;
        public Eligibility eligibility;
        public EntitlementDetails entitlementDetails;

        public Download build() {
            return new Download(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Download> {
        private final DownloadMetadata.Parser mDownloadMetadataParser;
        private final Eligibility.Parser mEligibilityParser;
        private final EntitlementDetails.Parser mEntitlementDetailsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDownloadMetadataParser = new DownloadMetadata.Parser(objectMapper);
            this.mEntitlementDetailsParser = new EntitlementDetails.Parser(objectMapper);
            this.mEligibilityParser = new Eligibility.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Download parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Download:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Download parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Download:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Download parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Download");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1772461995) {
                        if (iHashCode != -1087004713) {
                            if (iHashCode == -930847859 && next.equals("eligibility")) {
                                b = 1;
                            }
                        } else if (next.equals("downloadMetadata")) {
                            b = 0;
                        }
                    } else if (next.equals("entitlementDetails")) {
                        b = 2;
                    }
                    EntitlementDetails entitlementDetails = null;
                    DownloadMetadata downloadMetadata = null;
                    Eligibility eligibility = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            downloadMetadata = this.mDownloadMetadataParser.parse(jsonNode2);
                        }
                        builder.downloadMetadata = downloadMetadata;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            eligibility = this.mEligibilityParser.parse(jsonNode2);
                        }
                        builder.eligibility = eligibility;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            entitlementDetails = this.mEntitlementDetailsParser.parse(jsonNode2);
                        }
                        builder.entitlementDetails = entitlementDetails;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Download so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private Download parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1772461995) {
                                if (iHashCode != -1087004713) {
                                    if (iHashCode == -930847859 && currentName.equals("eligibility")) {
                                        b = 1;
                                    }
                                } else if (currentName.equals("downloadMetadata")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("entitlementDetails")) {
                                b = 2;
                            }
                            EntitlementDetails entitlementDetails = null;
                            DownloadMetadata downloadMetadata = null;
                            Eligibility eligibility = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    downloadMetadata = this.mDownloadMetadataParser.parse(jsonParser);
                                }
                                builder.downloadMetadata = downloadMetadata;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    eligibility = this.mEligibilityParser.parse(jsonParser);
                                }
                                builder.eligibility = eligibility;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    entitlementDetails = this.mEntitlementDetailsParser.parse(jsonParser);
                                }
                                builder.entitlementDetails = entitlementDetails;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Download so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
