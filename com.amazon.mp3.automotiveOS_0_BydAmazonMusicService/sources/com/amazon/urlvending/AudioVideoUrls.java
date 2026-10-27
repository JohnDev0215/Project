package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.AudioTrackMetadata;
import com.amazon.urlvending.AvUrlsCdnSet;
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

/* JADX INFO: loaded from: classes5.dex */
public class AudioVideoUrls {
    public final Optional<ImmutableList<AudioTrackMetadata>> audioTrackMetadata;
    public final Optional<ImmutableList<AvUrlsCdnSet>> avCdnUrlSets;
    public final Optional<String> defaultAudioTrackId;

    private AudioVideoUrls(Builder builder) {
        this.avCdnUrlSets = Optional.fromNullable(builder.avCdnUrlSets);
        this.defaultAudioTrackId = Optional.fromNullable(builder.defaultAudioTrackId);
        this.audioTrackMetadata = Optional.fromNullable(builder.audioTrackMetadata);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AudioVideoUrls)) {
            return false;
        }
        AudioVideoUrls audioVideoUrls = (AudioVideoUrls) obj;
        return Objects.equal(this.avCdnUrlSets, audioVideoUrls.avCdnUrlSets) && Objects.equal(this.defaultAudioTrackId, audioVideoUrls.defaultAudioTrackId) && Objects.equal(this.audioTrackMetadata, audioVideoUrls.audioTrackMetadata);
    }

    public int hashCode() {
        return Objects.hashCode(this.avCdnUrlSets, this.defaultAudioTrackId, this.audioTrackMetadata);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("avCdnUrlSets", this.avCdnUrlSets).add("defaultAudioTrackId", this.defaultAudioTrackId).add("audioTrackMetadata", this.audioTrackMetadata).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<AudioTrackMetadata> audioTrackMetadata;
        public ImmutableList<AvUrlsCdnSet> avCdnUrlSets;
        public String defaultAudioTrackId;

        public AudioVideoUrls build() {
            return new AudioVideoUrls(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AudioVideoUrls> {
        private final ListParser<AudioTrackMetadata> mAudioTrackMetadataResponseListParser;
        private final ListParser<AvUrlsCdnSet> mAvCdnUrlSetsParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAvCdnUrlSetsParser = ListParser.newParser(new AvUrlsCdnSet.Parser(objectMapper));
            this.mAudioTrackMetadataResponseListParser = ListParser.newParser(new AudioTrackMetadata.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
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
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1260956316) {
                        if (iHashCode != 1508230129) {
                            if (iHashCode == 1635950472 && next.equals("avCdnUrlSets")) {
                                b = 0;
                            }
                        } else if (next.equals("defaultAudioTrackId")) {
                            b = 1;
                        }
                    } else if (next.equals("audioTrackMetadata")) {
                        b = 2;
                    }
                    ImmutableList<AudioTrackMetadata> immutableList = null;
                    ImmutableList<AvUrlsCdnSet> immutableList2 = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mAvCdnUrlSetsParser.parse(jsonNode2);
                        }
                        builder.avCdnUrlSets = immutableList2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.defaultAudioTrackId = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mAudioTrackMetadataResponseListParser.parse(jsonNode2);
                        }
                        builder.audioTrackMetadata = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AudioVideoUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

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
                            int iHashCode = currentName.hashCode();
                            if (iHashCode != -1260956316) {
                                if (iHashCode != 1508230129) {
                                    if (iHashCode == 1635950472 && currentName.equals("avCdnUrlSets")) {
                                        b = 0;
                                    }
                                } else if (currentName.equals("defaultAudioTrackId")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("audioTrackMetadata")) {
                                b = 2;
                            }
                            ImmutableList<AudioTrackMetadata> immutableList = null;
                            ImmutableList<AvUrlsCdnSet> immutableList2 = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList2 = this.mAvCdnUrlSetsParser.parse(jsonParser);
                                }
                                builder.avCdnUrlSets = immutableList2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.defaultAudioTrackId = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mAudioTrackMetadataResponseListParser.parse(jsonParser);
                                }
                                builder.audioTrackMetadata = immutableList;
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
