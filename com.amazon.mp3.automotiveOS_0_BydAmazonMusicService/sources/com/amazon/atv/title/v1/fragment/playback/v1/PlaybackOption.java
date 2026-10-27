package com.amazon.atv.title.v1.fragment.playback.v1;

import com.amazon.atv.title.v1.fragment.playback.v1.RentalTerms;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
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
public class PlaybackOption {
    public final Optional<ImmutableList<DisplayTreatment>> displayTreatments;
    public final Optional<String> playbackAsin;
    public final Optional<String> playbackToken;
    public final Optional<RentalTerms> rentalTerms;

    private PlaybackOption(Builder builder) {
        this.playbackToken = Optional.fromNullable(builder.playbackToken);
        this.playbackAsin = Optional.fromNullable(builder.playbackAsin);
        this.displayTreatments = Optional.fromNullable(builder.displayTreatments);
        this.rentalTerms = Optional.fromNullable(builder.rentalTerms);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackOption)) {
            return false;
        }
        PlaybackOption playbackOption = (PlaybackOption) obj;
        return Objects.equal(this.playbackToken, playbackOption.playbackToken) && Objects.equal(this.playbackAsin, playbackOption.playbackAsin) && Objects.equal(this.displayTreatments, playbackOption.displayTreatments) && Objects.equal(this.rentalTerms, playbackOption.rentalTerms);
    }

    public int hashCode() {
        return Objects.hashCode(this.playbackToken, this.playbackAsin, this.displayTreatments, this.rentalTerms);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("playbackToken", this.playbackToken).add("playbackAsin", this.playbackAsin).add("displayTreatments", this.displayTreatments).add("rentalTerms", this.rentalTerms).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<DisplayTreatment> displayTreatments;
        public String playbackAsin;
        public String playbackToken;
        public RentalTerms rentalTerms;

        public PlaybackOption build() {
            return new PlaybackOption(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<PlaybackOption> {
        private final SimpleParsers.StringParser mASINParser;
        private final ListParser<DisplayTreatment> mDisplayTreatmentListParser;
        private final SimpleParsers.StringParser mPlaybackTokenParser;
        private final RentalTerms.Parser mRentalTermsParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDisplayTreatmentListParser = ListParser.newParser(EnumParser.newParser(DisplayTreatment.class));
            this.mRentalTermsParser = new RentalTerms.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mPlaybackTokenParser = stringParser;
            this.mASINParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public PlaybackOption parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackOption:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlaybackOption parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("PlaybackOption:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackOption parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "PlaybackOption");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -929395554:
                            if (next.equals("playbackToken")) {
                                b = 0;
                            }
                            break;
                        case 557711709:
                            if (next.equals("displayTreatments")) {
                                b = 2;
                            }
                            break;
                        case 1050178243:
                            if (next.equals("rentalTerms")) {
                                b = 3;
                            }
                            break;
                        case 1354930578:
                            if (next.equals("playbackAsin")) {
                                b = 1;
                            }
                            break;
                    }
                    RentalTerms rentalTerms = null;
                    String str = null;
                    String str2 = null;
                    ImmutableList<DisplayTreatment> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mPlaybackTokenParser.parse(jsonNode2);
                        }
                        builder.playbackToken = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mASINParser.parse(jsonNode2);
                        }
                        builder.playbackAsin = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mDisplayTreatmentListParser.parse(jsonNode2);
                        }
                        builder.displayTreatments = immutableList;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            rentalTerms = this.mRentalTermsParser.parse(jsonNode2);
                        }
                        builder.rentalTerms = rentalTerms;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing PlaybackOption so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private PlaybackOption parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -929395554:
                                    if (currentName.equals("playbackToken")) {
                                        b = 0;
                                    }
                                    break;
                                case 557711709:
                                    if (currentName.equals("displayTreatments")) {
                                        b = 2;
                                    }
                                    break;
                                case 1050178243:
                                    if (currentName.equals("rentalTerms")) {
                                        b = 3;
                                    }
                                    break;
                                case 1354930578:
                                    if (currentName.equals("playbackAsin")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            RentalTerms rentalTerms = null;
                            String str = null;
                            String str2 = null;
                            ImmutableList<DisplayTreatment> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mPlaybackTokenParser.parse(jsonParser);
                                }
                                builder.playbackToken = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mASINParser.parse(jsonParser);
                                }
                                builder.playbackAsin = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mDisplayTreatmentListParser.parse(jsonParser);
                                }
                                builder.displayTreatments = immutableList;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    rentalTerms = this.mRentalTermsParser.parse(jsonParser);
                                }
                                builder.rentalTerms = rentalTerms;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing PlaybackOption so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
