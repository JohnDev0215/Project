package com.amazon.atv.title.v1.fragment.playback.v2;

import com.amazon.atv.title.v1.fragment.playback.v2.Provenance;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class DisplayTreatments {
    public final Optional<Provenance> PROVENANCE;
    public final ImmutableList<Treatment> eligibleTreatments;

    private DisplayTreatments(Builder builder) {
        this.PROVENANCE = Optional.fromNullable(builder.PROVENANCE);
        this.eligibleTreatments = (ImmutableList) Preconditions.checkNotNull(builder.eligibleTreatments, "Unexpected null field: eligibleTreatments");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DisplayTreatments)) {
            return false;
        }
        DisplayTreatments displayTreatments = (DisplayTreatments) obj;
        return Objects.equal(this.PROVENANCE, displayTreatments.PROVENANCE) && Objects.equal(this.eligibleTreatments, displayTreatments.eligibleTreatments);
    }

    public int hashCode() {
        return Objects.hashCode(this.PROVENANCE, this.eligibleTreatments);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("PROVENANCE", this.PROVENANCE).add("eligibleTreatments", this.eligibleTreatments).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Provenance PROVENANCE;
        public ImmutableList<Treatment> eligibleTreatments;

        public DisplayTreatments build() {
            return new DisplayTreatments(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DisplayTreatments> {
        private final Provenance.Parser mProvenanceParser;
        private final ListParser<Treatment> mTreatmentListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTreatmentListParser = ListParser.newParser(EnumParser.newParser(Treatment.class));
            this.mProvenanceParser = new Provenance.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DisplayTreatments parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DisplayTreatments:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DisplayTreatments parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DisplayTreatments:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private DisplayTreatments parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DisplayTreatments");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != 584838689) {
                        if (iHashCode == 845646898 && next.equals("eligibleTreatments")) {
                            b = 1;
                        }
                    } else if (next.equals("PROVENANCE")) {
                        b = 0;
                    }
                    ImmutableList<Treatment> immutableList = null;
                    Provenance provenance = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            provenance = this.mProvenanceParser.parse(jsonNode2);
                        }
                        builder.PROVENANCE = provenance;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mTreatmentListParser.parse(jsonNode2);
                        }
                        builder.eligibleTreatments = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DisplayTreatments so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.eligibleTreatments, this, "eligibleTreatments");
            return builder.build();
        }

        @Nonnull
        private DisplayTreatments parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != 584838689) {
                                if (iHashCode == 845646898 && currentName.equals("eligibleTreatments")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("PROVENANCE")) {
                                b = 0;
                            }
                            ImmutableList<Treatment> immutableList = null;
                            Provenance provenance = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    provenance = this.mProvenanceParser.parse(jsonParser);
                                }
                                builder.PROVENANCE = provenance;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mTreatmentListParser.parse(jsonParser);
                                }
                                builder.eligibleTreatments = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DisplayTreatments so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.eligibleTreatments, this, "eligibleTreatments");
                    return builder.build();
                }
            }
        }
    }
}
