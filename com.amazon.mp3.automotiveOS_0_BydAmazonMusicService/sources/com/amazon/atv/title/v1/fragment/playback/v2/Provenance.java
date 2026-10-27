package com.amazon.atv.title.v1.fragment.playback.v2;

import com.amazon.atv.title.v1.VideoQuality;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
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
public class Provenance {
    public final Optional<String> subscriptionName;
    public final Optional<ProvenanceType> type;
    public final ImmutableList<VideoQuality> videoQuality;

    private Provenance(Builder builder) {
        this.type = Optional.fromNullable(builder.type);
        this.subscriptionName = Optional.fromNullable(builder.subscriptionName);
        this.videoQuality = (ImmutableList) Preconditions.checkNotNull(builder.videoQuality, "Unexpected null field: videoQuality");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Provenance)) {
            return false;
        }
        Provenance provenance = (Provenance) obj;
        return Objects.equal(this.type, provenance.type) && Objects.equal(this.subscriptionName, provenance.subscriptionName) && Objects.equal(this.videoQuality, provenance.videoQuality);
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.subscriptionName, this.videoQuality);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("subscriptionName", this.subscriptionName).add("videoQuality", this.videoQuality).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String subscriptionName;
        public ProvenanceType type;
        public ImmutableList<VideoQuality> videoQuality;

        public Provenance build() {
            return new Provenance(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Provenance> {
        private final EnumParser<ProvenanceType> mProvenanceTypeParser;
        private final ListParser<VideoQuality> mVideoQualityListParser;
        private final SimpleParsers.StringParser mbasicStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mProvenanceTypeParser = EnumParser.newParser(ProvenanceType.class);
            this.mVideoQualityListParser = ListParser.newParser(EnumParser.newParser(VideoQuality.class));
            this.mbasicStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Provenance parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Provenance:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Provenance parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Provenance:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private Provenance parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Provenance");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -515935928) {
                        if (iHashCode != 3575610) {
                            if (iHashCode == 504058884 && next.equals("videoQuality")) {
                                b = 2;
                            }
                        } else if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                            b = 0;
                        }
                    } else if (next.equals("subscriptionName")) {
                        b = 1;
                    }
                    ImmutableList<VideoQuality> immutableList = null;
                    ProvenanceType provenanceType = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            provenanceType = (ProvenanceType) this.mProvenanceTypeParser.parse(jsonNode2);
                        }
                        builder.type = provenanceType;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.subscriptionName = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mVideoQualityListParser.parse(jsonNode2);
                        }
                        builder.videoQuality = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Provenance so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.videoQuality, this, "videoQuality");
            return builder.build();
        }

        @Nonnull
        private Provenance parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -515935928) {
                                if (iHashCode != 3575610) {
                                    if (iHashCode == 504058884 && currentName.equals("videoQuality")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                    b = 0;
                                }
                            } else if (currentName.equals("subscriptionName")) {
                                b = 1;
                            }
                            ImmutableList<VideoQuality> immutableList = null;
                            ProvenanceType provenanceType = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    provenanceType = (ProvenanceType) this.mProvenanceTypeParser.parse(jsonParser);
                                }
                                builder.type = provenanceType;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.subscriptionName = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mVideoQualityListParser.parse(jsonParser);
                                }
                                builder.videoQuality = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Provenance so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.videoQuality, this, "videoQuality");
                    return builder.build();
                }
            }
        }
    }
}
