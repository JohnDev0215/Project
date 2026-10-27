package com.amazon.atvplaybackresource;

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
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class AudioTrackMetadata {
    public final Optional<String> audioSubtype;
    public final Optional<String> audioTrackId;
    public final Optional<String> displayName;
    public final Optional<String> index;
    public final Optional<String> languageCode;
    public final Optional<String> trackGroupId;

    private AudioTrackMetadata(Builder builder) {
        this.languageCode = Optional.fromNullable(builder.languageCode);
        this.trackGroupId = Optional.fromNullable(builder.trackGroupId);
        this.index = Optional.fromNullable(builder.index);
        this.audioTrackId = Optional.fromNullable(builder.audioTrackId);
        this.displayName = Optional.fromNullable(builder.displayName);
        this.audioSubtype = Optional.fromNullable(builder.audioSubtype);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AudioTrackMetadata)) {
            return false;
        }
        AudioTrackMetadata audioTrackMetadata = (AudioTrackMetadata) obj;
        return Objects.equal(this.languageCode, audioTrackMetadata.languageCode) && Objects.equal(this.trackGroupId, audioTrackMetadata.trackGroupId) && Objects.equal(this.index, audioTrackMetadata.index) && Objects.equal(this.audioTrackId, audioTrackMetadata.audioTrackId) && Objects.equal(this.displayName, audioTrackMetadata.displayName) && Objects.equal(this.audioSubtype, audioTrackMetadata.audioSubtype);
    }

    public int hashCode() {
        return Objects.hashCode(this.languageCode, this.trackGroupId, this.index, this.audioTrackId, this.displayName, this.audioSubtype);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("languageCode", this.languageCode).add("trackGroupId", this.trackGroupId).add("index", this.index).add("audioTrackId", this.audioTrackId).add("displayName", this.displayName).add("audioSubtype", this.audioSubtype).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String audioSubtype;
        public String audioTrackId;
        public String displayName;
        public String index;
        public String languageCode;
        public String trackGroupId;

        public AudioTrackMetadata build() {
            return new AudioTrackMetadata(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AudioTrackMetadata> {
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AudioTrackMetadata parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AudioTrackMetadata:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AudioTrackMetadata parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AudioTrackMetadata:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AudioTrackMetadata parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AudioTrackMetadata");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2092349083:
                            if (next.equals("languageCode")) {
                                b = 0;
                            }
                            break;
                        case -1871378385:
                            if (next.equals("trackGroupId")) {
                                b = 1;
                            }
                            break;
                        case 100346066:
                            if (next.equals("index")) {
                                b = 2;
                            }
                            break;
                        case 841043140:
                            if (next.equals("audioSubtype")) {
                                b = 5;
                            }
                            break;
                        case 1641214736:
                            if (next.equals("audioTrackId")) {
                                b = 3;
                            }
                            break;
                        case 1714148973:
                            if (next.equals("displayName")) {
                                b = 4;
                            }
                            break;
                    }
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.languageCode = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.trackGroupId = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.index = str;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.audioTrackId = str;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.displayName = str;
                    } else if (b == 5) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.audioSubtype = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AudioTrackMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AudioTrackMetadata parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2092349083:
                                    if (currentName.equals("languageCode")) {
                                        b = 0;
                                    }
                                    break;
                                case -1871378385:
                                    if (currentName.equals("trackGroupId")) {
                                        b = 1;
                                    }
                                    break;
                                case 100346066:
                                    if (currentName.equals("index")) {
                                        b = 2;
                                    }
                                    break;
                                case 841043140:
                                    if (currentName.equals("audioSubtype")) {
                                        b = 5;
                                    }
                                    break;
                                case 1641214736:
                                    if (currentName.equals("audioTrackId")) {
                                        b = 3;
                                    }
                                    break;
                                case 1714148973:
                                    if (currentName.equals("displayName")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.languageCode = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.trackGroupId = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.index = str;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.audioTrackId = str;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.displayName = str;
                            } else if (b == 5) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.audioSubtype = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AudioTrackMetadata so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
