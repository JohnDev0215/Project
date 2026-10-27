package com.amazon.atv.schedule.distribution;

import com.amazon.atv.schedule.distribution.Image;
import com.amazon.atv.schedule.distribution.LocalizedString;
import com.amazon.atv.schedule.distribution.Rating;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class LinearStation {
    public final Optional<Image> stationImage;
    public final Optional<LocalizedString> stationName;
    public final Optional<ImmutableList<Rating>> stationRating;

    private LinearStation(Builder builder) {
        this.stationImage = Optional.fromNullable(builder.stationImage);
        this.stationRating = Optional.fromNullable(builder.stationRating);
        this.stationName = Optional.fromNullable(builder.stationName);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LinearStation)) {
            return false;
        }
        LinearStation linearStation = (LinearStation) obj;
        return Objects.equal(this.stationImage, linearStation.stationImage) && Objects.equal(this.stationRating, linearStation.stationRating) && Objects.equal(this.stationName, linearStation.stationName);
    }

    public int hashCode() {
        return Objects.hashCode(this.stationImage, this.stationRating, this.stationName);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("stationImage", this.stationImage).add("stationRating", this.stationRating).add("stationName", this.stationName).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Image stationImage;
        public LocalizedString stationName;
        public ImmutableList<Rating> stationRating;

        public LinearStation build() {
            return new LinearStation(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LinearStation> {
        private final Image.Parser mImageParser;
        private final LocalizedString.Parser mLocalizedStringParser;
        private final ListParser<Rating> mRatingListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRatingListParser = ListParser.newParser(new Rating.Parser(objectMapper));
            this.mImageParser = new Image.Parser(objectMapper);
            this.mLocalizedStringParser = new LocalizedString.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LinearStation parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearStation:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LinearStation parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearStation:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private LinearStation parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "LinearStation");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -463083535) {
                        if (iHashCode != -161458041) {
                            if (iHashCode == 1241855455 && next.equals("stationName")) {
                                b = 2;
                            }
                        } else if (next.equals("stationImage")) {
                            b = 0;
                        }
                    } else if (next.equals("stationRating")) {
                        b = 1;
                    }
                    LocalizedString localizedString = null;
                    Image image = null;
                    ImmutableList<Rating> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            image = this.mImageParser.parse(jsonNode2);
                        }
                        builder.stationImage = image;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mRatingListParser.parse(jsonNode2);
                        }
                        builder.stationRating = immutableList;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            localizedString = this.mLocalizedStringParser.parse(jsonNode2);
                        }
                        builder.stationName = localizedString;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LinearStation so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private LinearStation parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -463083535) {
                                if (iHashCode != -161458041) {
                                    if (iHashCode == 1241855455 && currentName.equals("stationName")) {
                                        b = 2;
                                    }
                                } else if (currentName.equals("stationImage")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("stationRating")) {
                                b = 1;
                            }
                            LocalizedString localizedString = null;
                            Image image = null;
                            ImmutableList<Rating> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    image = this.mImageParser.parse(jsonParser);
                                }
                                builder.stationImage = image;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mRatingListParser.parse(jsonParser);
                                }
                                builder.stationRating = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    localizedString = this.mLocalizedStringParser.parse(jsonParser);
                                }
                                builder.stationName = localizedString;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LinearStation so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
