package com.amazon.atvplaybackresource.playready;

import com.amazon.atvplaybackresource.playready.OutputProtection;
import com.amazon.atvplaybackresource.playready.PlayReadyRightsWindow;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyLicenseMetadata {
    public final Optional<String> consumptionDuration;
    public final Optional<String> gracePeriod;
    public final Optional<String> keyId;
    public final Optional<OutputProtection> outputProtection;
    public final Optional<PlayReadyLicensePersistence> persistenceType;
    public final Optional<PlayReadyRightsWindow> rightsWindow;

    private PlayReadyLicenseMetadata(Builder builder) {
        this.consumptionDuration = Optional.fromNullable(builder.consumptionDuration);
        this.outputProtection = Optional.fromNullable(builder.outputProtection);
        this.gracePeriod = Optional.fromNullable(builder.gracePeriod);
        this.persistenceType = Optional.fromNullable(builder.persistenceType);
        this.keyId = Optional.fromNullable(builder.keyId);
        this.rightsWindow = Optional.fromNullable(builder.rightsWindow);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlayReadyLicenseMetadata)) {
            return false;
        }
        PlayReadyLicenseMetadata playReadyLicenseMetadata = (PlayReadyLicenseMetadata) obj;
        return Objects.equal(this.consumptionDuration, playReadyLicenseMetadata.consumptionDuration) && Objects.equal(this.outputProtection, playReadyLicenseMetadata.outputProtection) && Objects.equal(this.gracePeriod, playReadyLicenseMetadata.gracePeriod) && Objects.equal(this.persistenceType, playReadyLicenseMetadata.persistenceType) && Objects.equal(this.keyId, playReadyLicenseMetadata.keyId) && Objects.equal(this.rightsWindow, playReadyLicenseMetadata.rightsWindow);
    }

    public int hashCode() {
        return Objects.hashCode(this.consumptionDuration, this.outputProtection, this.gracePeriod, this.persistenceType, this.keyId, this.rightsWindow);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("consumptionDuration", this.consumptionDuration).add("outputProtection", this.outputProtection).add("gracePeriod", this.gracePeriod).add("persistenceType", this.persistenceType).add("keyId", this.keyId).add("rightsWindow", this.rightsWindow).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String consumptionDuration;
        public String gracePeriod;
        public String keyId;
        public OutputProtection outputProtection;
        public PlayReadyLicensePersistence persistenceType;
        public PlayReadyRightsWindow rightsWindow;

        public PlayReadyLicenseMetadata build() {
            return new PlayReadyLicenseMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlayReadyLicenseMetadata> {
        private final SimpleParsers.StringParser mIso8601DurationParser;
        private final OutputProtection.Parser mOutputProtectionParser;
        private final EnumParser<PlayReadyLicensePersistence> mPlayReadyLicensePersistenceParser;
        private final PlayReadyRightsWindow.Parser mPlayReadyRightsWindowParser;
        private final SimpleParsers.StringParser mkeyIdParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPlayReadyRightsWindowParser = new PlayReadyRightsWindow.Parser(objectMapper);
            this.mOutputProtectionParser = new OutputProtection.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mIso8601DurationParser = stringParser;
            this.mkeyIdParser = stringParser;
            this.mPlayReadyLicensePersistenceParser = EnumParser.newParser(PlayReadyLicensePersistence.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlayReadyLicenseMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlayReadyLicenseMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlayReadyLicenseMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlayReadyLicenseMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlayReadyLicenseMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlayReadyLicenseMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1617984838:
                            if (next.equals("outputProtection")) {
                                b = 1;
                            }
                            break;
                        case 101944282:
                            if (next.equals("keyId")) {
                                b = 4;
                            }
                            break;
                        case 341629519:
                            if (next.equals("consumptionDuration")) {
                                b = 0;
                            }
                            break;
                        case 1948798119:
                            if (next.equals("rightsWindow")) {
                                b = 5;
                            }
                            break;
                        case 2010675097:
                            if (next.equals("gracePeriod")) {
                                b = 2;
                            }
                            break;
                        case 2061360761:
                            if (next.equals("persistenceType")) {
                                b = 3;
                            }
                            break;
                    }
                    PlayReadyRightsWindow playReadyRightsWindow = null;
                    String str = null;
                    OutputProtection outputProtection = null;
                    String str2 = null;
                    PlayReadyLicensePersistence playReadyLicensePersistence = null;
                    String str3 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mIso8601DurationParser.parse(jsonNode2);
                        }
                        builder.consumptionDuration = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            outputProtection = this.mOutputProtectionParser.parse(jsonNode2);
                        }
                        builder.outputProtection = outputProtection;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mIso8601DurationParser.parse(jsonNode2);
                        }
                        builder.gracePeriod = str2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            playReadyLicensePersistence = (PlayReadyLicensePersistence) this.mPlayReadyLicensePersistenceParser.parse(jsonNode2);
                        }
                        builder.persistenceType = playReadyLicensePersistence;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mkeyIdParser.parse(jsonNode2);
                        }
                        builder.keyId = str3;
                    } else if (b == 5) {
                        if (!jsonNode2.isNull()) {
                            playReadyRightsWindow = this.mPlayReadyRightsWindowParser.parse(jsonNode2);
                        }
                        builder.rightsWindow = playReadyRightsWindow;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlayReadyLicenseMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlayReadyLicenseMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1617984838:
                                    if (currentName.equals("outputProtection")) {
                                        b = 1;
                                    }
                                    break;
                                case 101944282:
                                    if (currentName.equals("keyId")) {
                                        b = 4;
                                    }
                                    break;
                                case 341629519:
                                    if (currentName.equals("consumptionDuration")) {
                                        b = 0;
                                    }
                                    break;
                                case 1948798119:
                                    if (currentName.equals("rightsWindow")) {
                                        b = 5;
                                    }
                                    break;
                                case 2010675097:
                                    if (currentName.equals("gracePeriod")) {
                                        b = 2;
                                    }
                                    break;
                                case 2061360761:
                                    if (currentName.equals("persistenceType")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            PlayReadyRightsWindow playReadyRightsWindow = null;
                            String str = null;
                            OutputProtection outputProtection = null;
                            String str2 = null;
                            PlayReadyLicensePersistence playReadyLicensePersistence = null;
                            String str3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mIso8601DurationParser.parse(jsonParser);
                                }
                                builder.consumptionDuration = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    outputProtection = this.mOutputProtectionParser.parse(jsonParser);
                                }
                                builder.outputProtection = outputProtection;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mIso8601DurationParser.parse(jsonParser);
                                }
                                builder.gracePeriod = str2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    playReadyLicensePersistence = (PlayReadyLicensePersistence) this.mPlayReadyLicensePersistenceParser.parse(jsonParser);
                                }
                                builder.persistenceType = playReadyLicensePersistence;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mkeyIdParser.parse(jsonParser);
                                }
                                builder.keyId = str3;
                            } else if (b == 5) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    playReadyRightsWindow = this.mPlayReadyRightsWindowParser.parse(jsonParser);
                                }
                                builder.rightsWindow = playReadyRightsWindow;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlayReadyLicenseMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
