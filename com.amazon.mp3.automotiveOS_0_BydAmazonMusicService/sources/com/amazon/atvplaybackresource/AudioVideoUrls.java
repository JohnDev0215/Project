package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackresource.AudioTrackMetadata;
import com.amazon.atvplaybackresource.AvUrlsCdnSet;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
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
public class AudioVideoUrls {
    public final Optional<ImmutableList<AudioTrackMetadata>> audioTrackMetadata;
    public final Optional<ImmutableList<AvUrlsCdnSet>> avCdnUrlSets;
    public final Optional<String> contentIdUsed;
    public final Optional<String> defaultAudioTrackId;

    private AudioVideoUrls(Builder builder) {
        this.avCdnUrlSets = Optional.fromNullable(builder.avCdnUrlSets);
        this.defaultAudioTrackId = Optional.fromNullable(builder.defaultAudioTrackId);
        this.audioTrackMetadata = Optional.fromNullable(builder.audioTrackMetadata);
        this.contentIdUsed = Optional.fromNullable(builder.contentIdUsed);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AudioVideoUrls)) {
            return false;
        }
        AudioVideoUrls audioVideoUrls = (AudioVideoUrls) obj;
        return Objects.equal(this.avCdnUrlSets, audioVideoUrls.avCdnUrlSets) && Objects.equal(this.defaultAudioTrackId, audioVideoUrls.defaultAudioTrackId) && Objects.equal(this.audioTrackMetadata, audioVideoUrls.audioTrackMetadata) && Objects.equal(this.contentIdUsed, audioVideoUrls.contentIdUsed);
    }

    public int hashCode() {
        return Objects.hashCode(this.avCdnUrlSets, this.defaultAudioTrackId, this.audioTrackMetadata, this.contentIdUsed);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("avCdnUrlSets", this.avCdnUrlSets).add("defaultAudioTrackId", this.defaultAudioTrackId).add("audioTrackMetadata", this.audioTrackMetadata).add("contentIdUsed", this.contentIdUsed).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<AudioTrackMetadata> audioTrackMetadata;
        public ImmutableList<AvUrlsCdnSet> avCdnUrlSets;
        public String contentIdUsed;
        public String defaultAudioTrackId;

        public AudioVideoUrls build() {
            return new AudioVideoUrls(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AudioVideoUrls> {
        private final ListParser<AudioTrackMetadata> mAudioTrackMetadataListParser;
        private final ListParser<AvUrlsCdnSet> mAvCdnUrlSetsParser;
        private final SimpleParsers.StringParser mstringTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAvCdnUrlSetsParser = ListParser.newParser(new AvUrlsCdnSet.Parser(objectMapper));
            this.mAudioTrackMetadataListParser = ListParser.newParser(new AudioTrackMetadata.Parser(objectMapper));
            this.mstringTargetParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AudioVideoUrls parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AudioVideoUrls:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AudioVideoUrls parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AudioVideoUrls:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AudioVideoUrls parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AudioVideoUrls");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1260956316:
                            if (next.equals("audioTrackMetadata")) {
                                b = 2;
                            }
                            break;
                        case -628258479:
                            if (next.equals("contentIdUsed")) {
                                b = 3;
                            }
                            break;
                        case 1508230129:
                            if (next.equals("defaultAudioTrackId")) {
                                b = 1;
                            }
                            break;
                        case 1635950472:
                            if (next.equals("avCdnUrlSets")) {
                                b = 0;
                            }
                            break;
                    }
                    String str = null;
                    ImmutableList<AvUrlsCdnSet> immutableList = null;
                    String str2 = null;
                    ImmutableList<AudioTrackMetadata> immutableList2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mAvCdnUrlSetsParser.parse(jsonNode2);
                        }
                        builder.avCdnUrlSets = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.defaultAudioTrackId = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mAudioTrackMetadataListParser.parse(jsonNode2);
                        }
                        builder.audioTrackMetadata = immutableList2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.contentIdUsed = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AudioVideoUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AudioVideoUrls parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1260956316:
                                    if (currentName.equals("audioTrackMetadata")) {
                                        b = 2;
                                    }
                                    break;
                                case -628258479:
                                    if (currentName.equals("contentIdUsed")) {
                                        b = 3;
                                    }
                                    break;
                                case 1508230129:
                                    if (currentName.equals("defaultAudioTrackId")) {
                                        b = 1;
                                    }
                                    break;
                                case 1635950472:
                                    if (currentName.equals("avCdnUrlSets")) {
                                        b = 0;
                                    }
                                    break;
                            }
                            String str = null;
                            ImmutableList<AvUrlsCdnSet> immutableList = null;
                            String str2 = null;
                            ImmutableList<AudioTrackMetadata> immutableList2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mAvCdnUrlSetsParser.parse(jsonParser);
                                }
                                builder.avCdnUrlSets = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.defaultAudioTrackId = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mAudioTrackMetadataListParser.parse(jsonParser);
                                }
                                builder.audioTrackMetadata = immutableList2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTargetParser.parse(jsonParser);
                                }
                                builder.contentIdUsed = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AudioVideoUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
