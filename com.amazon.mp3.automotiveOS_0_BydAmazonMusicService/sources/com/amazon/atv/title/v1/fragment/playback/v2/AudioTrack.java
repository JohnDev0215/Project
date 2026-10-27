package com.amazon.atv.title.v1.fragment.playback.v2;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
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
public class AudioTrack {
    public final Optional<String> displayName;
    public final Optional<String> id;
    public final boolean isOriginalLanguage;
    public final Optional<String> language;
    public final Optional<String> type;

    private AudioTrack(Builder builder) {
        this.language = Optional.fromNullable(builder.language);
        this.id = Optional.fromNullable(builder.id);
        this.isOriginalLanguage = builder.isOriginalLanguage;
        this.type = Optional.fromNullable(builder.type);
        this.displayName = Optional.fromNullable(builder.displayName);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AudioTrack)) {
            return false;
        }
        AudioTrack audioTrack = (AudioTrack) obj;
        return Objects.equal(this.language, audioTrack.language) && Objects.equal(this.id, audioTrack.id) && Objects.equal(Boolean.valueOf(this.isOriginalLanguage), Boolean.valueOf(audioTrack.isOriginalLanguage)) && Objects.equal(this.type, audioTrack.type) && Objects.equal(this.displayName, audioTrack.displayName);
    }

    public int hashCode() {
        return Objects.hashCode(this.language, this.id, Boolean.valueOf(this.isOriginalLanguage), this.type, this.displayName);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("language", this.language).add("id", this.id).add("isOriginalLanguage", this.isOriginalLanguage).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("displayName", this.displayName).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String displayName;
        public String id;
        public boolean isOriginalLanguage;
        public String language;
        public String type;

        public AudioTrack build() {
            return new AudioTrack(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AudioTrack> {
        private final SimpleParsers.StringParser mbasicStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mbasicStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AudioTrack parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AudioTrack:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AudioTrack parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AudioTrack:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AudioTrack parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AudioTrack");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1613589672:
                            if (next.equals("language")) {
                                b = 0;
                            }
                            break;
                        case -116531821:
                            if (next.equals("isOriginalLanguage")) {
                                b = 2;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 1;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
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
                            str = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.language = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.id = str;
                    } else if (b == 2) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field isOriginalLanguage can't be null");
                        }
                        builder.isOriginalLanguage = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.type = str;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mbasicStringParser.parse(jsonNode2);
                        }
                        builder.displayName = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AudioTrack so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AudioTrack parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1613589672:
                                    if (currentName.equals("language")) {
                                        b = 0;
                                    }
                                    break;
                                case -116531821:
                                    if (currentName.equals("isOriginalLanguage")) {
                                        b = 2;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 1;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
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
                                    str = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.language = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.id = str;
                            } else if (b == 2) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field isOriginalLanguage can't be null");
                                }
                                builder.isOriginalLanguage = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.type = str;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mbasicStringParser.parse(jsonParser);
                                }
                                builder.displayName = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AudioTrack so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
