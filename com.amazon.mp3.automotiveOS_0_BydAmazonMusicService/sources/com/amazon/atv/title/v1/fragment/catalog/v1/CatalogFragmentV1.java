package com.amazon.atv.title.v1.fragment.catalog.v1;

import com.amazon.atv.title.v1.CatalogFragment;
import com.amazon.atv.title.v1.Timestamp;
import com.amazon.atv.title.v1.fragment.catalog.v1.Contributors;
import com.amazon.atv.title.v1.fragment.catalog.v1.GenreCode;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.find.utils.PageUriUtils;
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
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class CatalogFragmentV1 extends CatalogFragment {
    public final Optional<String> amazonMaturityRating;
    public final Optional<String> amazonMaturityRatingAstId;
    public final Optional<Contributors> contributors;
    public final Optional<Timestamp> dvdStreetDate;
    public final Optional<String> entityType;
    public final Optional<Integer> episodeNumber;
    public final Optional<ImmutableList<ImmutableList<GenreCode>>> genreCodes;
    public final Optional<ImmutableList<String>> genres;
    public final String id;
    public final Optional<String> language;
    public final Optional<Timestamp> originalAirDate;
    public final Optional<Timestamp> preorderReleaseDate;
    public final Optional<Timestamp> productSiteLaunchDate;
    public final Optional<Timestamp> publicReleaseDate;
    public final Optional<String> regulatoryRating;
    public final Optional<Integer> runtimeSeconds;
    public final Optional<Integer> seasonNumber;
    public final Optional<ImmutableList<String>> studios;
    public final Optional<String> synopsis;
    public final Optional<Timestamp> theatricalReleaseDate;
    public final String title;
    public final TitleType type;

    private CatalogFragmentV1(Builder builder) {
        super(builder);
        this.runtimeSeconds = Optional.fromNullable(builder.runtimeSeconds);
        this.originalAirDate = Optional.fromNullable(builder.originalAirDate);
        this.id = (String) Preconditions.checkNotNull(builder.id, "Unexpected null field: id");
        this.regulatoryRating = Optional.fromNullable(builder.regulatoryRating);
        this.genreCodes = Optional.fromNullable(builder.genreCodes);
        this.productSiteLaunchDate = Optional.fromNullable(builder.productSiteLaunchDate);
        this.synopsis = Optional.fromNullable(builder.synopsis);
        this.title = (String) Preconditions.checkNotNull(builder.title, "Unexpected null field: title");
        this.seasonNumber = Optional.fromNullable(builder.seasonNumber);
        this.theatricalReleaseDate = Optional.fromNullable(builder.theatricalReleaseDate);
        this.amazonMaturityRating = Optional.fromNullable(builder.amazonMaturityRating);
        this.dvdStreetDate = Optional.fromNullable(builder.dvdStreetDate);
        this.episodeNumber = Optional.fromNullable(builder.episodeNumber);
        this.language = Optional.fromNullable(builder.language);
        this.publicReleaseDate = Optional.fromNullable(builder.publicReleaseDate);
        this.preorderReleaseDate = Optional.fromNullable(builder.preorderReleaseDate);
        this.amazonMaturityRatingAstId = Optional.fromNullable(builder.amazonMaturityRatingAstId);
        this.studios = Optional.fromNullable(builder.studios);
        this.genres = Optional.fromNullable(builder.genres);
        this.contributors = Optional.fromNullable(builder.contributors);
        this.entityType = Optional.fromNullable(builder.entityType);
        this.type = (TitleType) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
    }

    @Override // com.amazon.atv.title.v1.CatalogFragment, com.amazon.atv.title.v1.Fragment
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CatalogFragmentV1)) {
            return false;
        }
        CatalogFragmentV1 catalogFragmentV1 = (CatalogFragmentV1) obj;
        return super.equals(obj) && Objects.equal(this.runtimeSeconds, catalogFragmentV1.runtimeSeconds) && Objects.equal(this.originalAirDate, catalogFragmentV1.originalAirDate) && Objects.equal(this.id, catalogFragmentV1.id) && Objects.equal(this.regulatoryRating, catalogFragmentV1.regulatoryRating) && Objects.equal(this.genreCodes, catalogFragmentV1.genreCodes) && Objects.equal(this.productSiteLaunchDate, catalogFragmentV1.productSiteLaunchDate) && Objects.equal(this.synopsis, catalogFragmentV1.synopsis) && Objects.equal(this.title, catalogFragmentV1.title) && Objects.equal(this.seasonNumber, catalogFragmentV1.seasonNumber) && Objects.equal(this.theatricalReleaseDate, catalogFragmentV1.theatricalReleaseDate) && Objects.equal(this.amazonMaturityRating, catalogFragmentV1.amazonMaturityRating) && Objects.equal(this.dvdStreetDate, catalogFragmentV1.dvdStreetDate) && Objects.equal(this.episodeNumber, catalogFragmentV1.episodeNumber) && Objects.equal(this.language, catalogFragmentV1.language) && Objects.equal(this.publicReleaseDate, catalogFragmentV1.publicReleaseDate) && Objects.equal(this.preorderReleaseDate, catalogFragmentV1.preorderReleaseDate) && Objects.equal(this.amazonMaturityRatingAstId, catalogFragmentV1.amazonMaturityRatingAstId) && Objects.equal(this.studios, catalogFragmentV1.studios) && Objects.equal(this.genres, catalogFragmentV1.genres) && Objects.equal(this.contributors, catalogFragmentV1.contributors) && Objects.equal(this.entityType, catalogFragmentV1.entityType) && Objects.equal(this.type, catalogFragmentV1.type);
    }

    @Override // com.amazon.atv.title.v1.CatalogFragment, com.amazon.atv.title.v1.Fragment
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.runtimeSeconds, this.originalAirDate, this.id, this.regulatoryRating, this.genreCodes, this.productSiteLaunchDate, this.synopsis, this.title, this.seasonNumber, this.theatricalReleaseDate, this.amazonMaturityRating, this.dvdStreetDate, this.episodeNumber, this.language, this.publicReleaseDate, this.preorderReleaseDate, this.amazonMaturityRatingAstId, this.studios, this.genres, this.contributors, this.entityType, this.type);
    }

    @Override // com.amazon.atv.title.v1.CatalogFragment, com.amazon.atv.title.v1.Fragment
    public String toString() {
        return MoreObjects.toStringHelper(this).add("runtimeSeconds", this.runtimeSeconds).add("originalAirDate", this.originalAirDate).add("id", this.id).add("regulatoryRating", this.regulatoryRating).add("genreCodes", this.genreCodes).add("productSiteLaunchDate", this.productSiteLaunchDate).add("synopsis", this.synopsis).add("title", this.title).add("seasonNumber", this.seasonNumber).add("theatricalReleaseDate", this.theatricalReleaseDate).add("amazonMaturityRating", this.amazonMaturityRating).add("dvdStreetDate", this.dvdStreetDate).add("episodeNumber", this.episodeNumber).add("language", this.language).add("publicReleaseDate", this.publicReleaseDate).add("preorderReleaseDate", this.preorderReleaseDate).add("amazonMaturityRatingAstId", this.amazonMaturityRatingAstId).add("studios", this.studios).add("genres", this.genres).add("contributors", this.contributors).add(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, this.entityType).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).toString();
    }

    @NotThreadSafe
    public static class Builder extends CatalogFragment.Builder {
        public String amazonMaturityRating;
        public String amazonMaturityRatingAstId;
        public Contributors contributors;
        public Timestamp dvdStreetDate;
        public String entityType;
        public Integer episodeNumber;
        public ImmutableList<ImmutableList<GenreCode>> genreCodes;
        public ImmutableList<String> genres;
        public String id;
        public String language;
        public Timestamp originalAirDate;
        public Timestamp preorderReleaseDate;
        public Timestamp productSiteLaunchDate;
        public Timestamp publicReleaseDate;
        public String regulatoryRating;
        public Integer runtimeSeconds;
        public Integer seasonNumber;
        public ImmutableList<String> studios;
        public String synopsis;
        public Timestamp theatricalReleaseDate;
        public String title;
        public TitleType type;

        public CatalogFragmentV1 build() {
            return new CatalogFragmentV1(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<CatalogFragmentV1> {
        private final Contributors.Parser mContributorsParser;
        private final SimpleParsers.StringParser mFragmentVersionParser;
        private final ListParser<ImmutableList<GenreCode>> mGenreListParser;
        private final SimpleParsers.IntegerParser mNullableIntegerParser;
        private final ListParser<String> mStringListParser;
        private final SimpleParsers.StringParser mStringParser;
        private final Timestamp.Parser mTimestampParser;
        private final EnumParser<TitleType> mTitleTypeParser;
        private final SimpleParsers.StringParser mValidUntilTimestampParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mGenreListParser = ListParser.newParser(ListParser.newParser(new GenreCode.Parser(objectMapper)));
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringListParser = ListParser.newParser(stringParser);
            this.mNullableIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mTitleTypeParser = EnumParser.newParser(TitleType.class);
            this.mTimestampParser = new Timestamp.Parser(objectMapper);
            this.mStringParser = stringParser;
            this.mFragmentVersionParser = stringParser;
            this.mValidUntilTimestampParser = stringParser;
            this.mContributorsParser = new Contributors.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public CatalogFragmentV1 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CatalogFragmentV1:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public CatalogFragmentV1 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("CatalogFragmentV1:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CatalogFragmentV1 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Integer num;
            Timestamp timestamp;
            String str;
            String str2;
            String str3;
            ImmutableList<ImmutableList<GenreCode>> immutableList;
            Timestamp timestamp2;
            String str4;
            String str5;
            Integer num2;
            Timestamp timestamp3;
            String str6;
            Timestamp timestamp4;
            Integer num3;
            String str7;
            Timestamp timestamp5;
            Timestamp timestamp6;
            String str8;
            ImmutableList<String> immutableList2;
            ImmutableList<String> immutableList3;
            String str9;
            Contributors contributors;
            String str10;
            TitleType titleType;
            JsonParsingUtils.throwIfNotObject(jsonNode, "CatalogFragmentV1");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2074407033:
                            if (next.equals("runtimeSeconds")) {
                                b = 0;
                            }
                            break;
                        case -1905664732:
                            if (next.equals("episodeNumber")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case -1879142051:
                            if (next.equals("studios")) {
                                b = 18;
                            }
                            break;
                        case -1613589672:
                            if (next.equals("language")) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case -1482998339:
                            if (next.equals(PageUriUtils.ENTITY_TYPE_QUERY_PARAM)) {
                                b = 22;
                            }
                            break;
                        case -1452295929:
                            if (next.equals("originalAirDate")) {
                                b = 1;
                            }
                            break;
                        case -1441689501:
                            if (next.equals("genreCodes")) {
                                b = 5;
                            }
                            break;
                        case -1360577524:
                            if (next.equals("seasonNumber")) {
                                b = 9;
                            }
                            break;
                        case -1326474750:
                            if (next.equals("theatricalReleaseDate")) {
                                b = 10;
                            }
                            break;
                        case -1249499312:
                            if (next.equals("genres")) {
                                b = 19;
                            }
                            break;
                        case -1038497870:
                            if (next.equals("amazonMaturityRating")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case -54813502:
                            if (next.equals("validUntil")) {
                                b = 20;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 2;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 23;
                            }
                            break;
                        case 110371416:
                            if (next.equals("title")) {
                                b = 8;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 3;
                            }
                            break;
                        case 389049859:
                            if (next.equals("dvdStreetDate")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case 1103318613:
                            if (next.equals("regulatoryRating")) {
                                b = 4;
                            }
                            break;
                        case 1326823980:
                            if (next.equals("publicReleaseDate")) {
                                b = 15;
                            }
                            break;
                        case 1343016823:
                            if (next.equals("productSiteLaunchDate")) {
                                b = 6;
                            }
                            break;
                        case 1375976184:
                            if (next.equals("contributors")) {
                                b = 21;
                            }
                            break;
                        case 1510236362:
                            if (next.equals("preorderReleaseDate")) {
                                b = 16;
                            }
                            break;
                        case 1828656532:
                            if (next.equals("synopsis")) {
                                b = 7;
                            }
                            break;
                        case 1836266155:
                            if (next.equals("amazonMaturityRatingAstId")) {
                                b = 17;
                            }
                            break;
                    }
                    num = null;
                    titleType = null;
                    str10 = null;
                    contributors = null;
                    str9 = null;
                    immutableList3 = null;
                    immutableList2 = null;
                    str8 = null;
                    timestamp6 = null;
                    timestamp5 = null;
                    str7 = null;
                    num3 = null;
                    timestamp4 = null;
                    str6 = null;
                    timestamp3 = null;
                    num2 = null;
                    str5 = null;
                    str4 = null;
                    timestamp2 = null;
                    immutableList = null;
                    str3 = null;
                    str2 = null;
                    str = null;
                    timestamp = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CatalogFragmentV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            num = this.mNullableIntegerParser.parse(jsonNode2);
                        }
                        builder.runtimeSeconds = num;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            timestamp = this.mTimestampParser.parse(jsonNode2);
                        }
                        builder.originalAirDate = timestamp;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mFragmentVersionParser.parse(jsonNode2);
                        }
                        builder.version = str2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.regulatoryRating = str3;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mGenreListParser.parse(jsonNode2);
                        }
                        builder.genreCodes = immutableList;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            timestamp2 = this.mTimestampParser.parse(jsonNode2);
                        }
                        builder.productSiteLaunchDate = timestamp2;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.synopsis = str4;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.title = str5;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            num2 = this.mNullableIntegerParser.parse(jsonNode2);
                        }
                        builder.seasonNumber = num2;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            timestamp3 = this.mTimestampParser.parse(jsonNode2);
                        }
                        builder.theatricalReleaseDate = timestamp3;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.amazonMaturityRating = str6;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            timestamp4 = this.mTimestampParser.parse(jsonNode2);
                        }
                        builder.dvdStreetDate = timestamp4;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            num3 = this.mNullableIntegerParser.parse(jsonNode2);
                        }
                        builder.episodeNumber = num3;
                        continue;
                    case 14:
                        if (!jsonNode2.isNull()) {
                            str7 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.language = str7;
                        continue;
                    case 15:
                        if (!jsonNode2.isNull()) {
                            timestamp5 = this.mTimestampParser.parse(jsonNode2);
                        }
                        builder.publicReleaseDate = timestamp5;
                        continue;
                    case 16:
                        if (!jsonNode2.isNull()) {
                            timestamp6 = this.mTimestampParser.parse(jsonNode2);
                        }
                        builder.preorderReleaseDate = timestamp6;
                        continue;
                    case 17:
                        if (!jsonNode2.isNull()) {
                            str8 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.amazonMaturityRatingAstId = str8;
                        continue;
                    case 18:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mStringListParser.parse(jsonNode2);
                        }
                        builder.studios = immutableList2;
                        continue;
                    case 19:
                        if (!jsonNode2.isNull()) {
                            immutableList3 = this.mStringListParser.parse(jsonNode2);
                        }
                        builder.genres = immutableList3;
                        continue;
                    case 20:
                        if (!jsonNode2.isNull()) {
                            str9 = this.mValidUntilTimestampParser.parse(jsonNode2);
                        }
                        builder.validUntil = str9;
                        continue;
                    case 21:
                        if (!jsonNode2.isNull()) {
                            contributors = this.mContributorsParser.parse(jsonNode2);
                        }
                        builder.contributors = contributors;
                        continue;
                    case 22:
                        if (!jsonNode2.isNull()) {
                            str10 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.entityType = str10;
                        continue;
                    case 23:
                        if (!jsonNode2.isNull()) {
                            titleType = (TitleType) this.mTitleTypeParser.parse(jsonNode2);
                        }
                        builder.type = titleType;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing CatalogFragmentV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.id, this, "id");
            JsonParsingUtils.checkNotNull(builder.version, this, "version");
            JsonParsingUtils.checkNotNull(builder.title, this, "title");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private CatalogFragmentV1 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2074407033:
                                    if (currentName.equals("runtimeSeconds")) {
                                        b = 0;
                                    }
                                    break;
                                case -1905664732:
                                    if (currentName.equals("episodeNumber")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case -1879142051:
                                    if (currentName.equals("studios")) {
                                        b = 18;
                                    }
                                    break;
                                case -1613589672:
                                    if (currentName.equals("language")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case -1482998339:
                                    if (currentName.equals(PageUriUtils.ENTITY_TYPE_QUERY_PARAM)) {
                                        b = 22;
                                    }
                                    break;
                                case -1452295929:
                                    if (currentName.equals("originalAirDate")) {
                                        b = 1;
                                    }
                                    break;
                                case -1441689501:
                                    if (currentName.equals("genreCodes")) {
                                        b = 5;
                                    }
                                    break;
                                case -1360577524:
                                    if (currentName.equals("seasonNumber")) {
                                        b = 9;
                                    }
                                    break;
                                case -1326474750:
                                    if (currentName.equals("theatricalReleaseDate")) {
                                        b = 10;
                                    }
                                    break;
                                case -1249499312:
                                    if (currentName.equals("genres")) {
                                        b = 19;
                                    }
                                    break;
                                case -1038497870:
                                    if (currentName.equals("amazonMaturityRating")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case -54813502:
                                    if (currentName.equals("validUntil")) {
                                        b = 20;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 2;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 23;
                                    }
                                    break;
                                case 110371416:
                                    if (currentName.equals("title")) {
                                        b = 8;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 3;
                                    }
                                    break;
                                case 389049859:
                                    if (currentName.equals("dvdStreetDate")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case 1103318613:
                                    if (currentName.equals("regulatoryRating")) {
                                        b = 4;
                                    }
                                    break;
                                case 1326823980:
                                    if (currentName.equals("publicReleaseDate")) {
                                        b = 15;
                                    }
                                    break;
                                case 1343016823:
                                    if (currentName.equals("productSiteLaunchDate")) {
                                        b = 6;
                                    }
                                    break;
                                case 1375976184:
                                    if (currentName.equals("contributors")) {
                                        b = 21;
                                    }
                                    break;
                                case 1510236362:
                                    if (currentName.equals("preorderReleaseDate")) {
                                        b = 16;
                                    }
                                    break;
                                case 1828656532:
                                    if (currentName.equals("synopsis")) {
                                        b = 7;
                                    }
                                    break;
                                case 1836266155:
                                    if (currentName.equals("amazonMaturityRatingAstId")) {
                                        b = 17;
                                    }
                                    break;
                            }
                            Integer num = null;
                            TitleType titleType = null;
                            String str = null;
                            Contributors contributors = null;
                            String str2 = null;
                            ImmutableList<String> immutableList = null;
                            ImmutableList<String> immutableList2 = null;
                            String str3 = null;
                            Timestamp timestamp = null;
                            Timestamp timestamp2 = null;
                            String str4 = null;
                            Integer num2 = null;
                            Timestamp timestamp3 = null;
                            String str5 = null;
                            Timestamp timestamp4 = null;
                            Integer num3 = null;
                            String str6 = null;
                            String str7 = null;
                            Timestamp timestamp5 = null;
                            ImmutableList<ImmutableList<GenreCode>> immutableList3 = null;
                            String str8 = null;
                            String str9 = null;
                            String str10 = null;
                            Timestamp timestamp6 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num = this.mNullableIntegerParser.parse(jsonParser);
                                    }
                                    builder.runtimeSeconds = num;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        timestamp6 = this.mTimestampParser.parse(jsonParser);
                                    }
                                    builder.originalAirDate = timestamp6;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str10 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str10;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str9 = this.mFragmentVersionParser.parse(jsonParser);
                                    }
                                    builder.version = str9;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.regulatoryRating = str8;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList3 = this.mGenreListParser.parse(jsonParser);
                                    }
                                    builder.genreCodes = immutableList3;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        timestamp5 = this.mTimestampParser.parse(jsonParser);
                                    }
                                    builder.productSiteLaunchDate = timestamp5;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.synopsis = str7;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.title = str6;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num3 = this.mNullableIntegerParser.parse(jsonParser);
                                    }
                                    builder.seasonNumber = num3;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        timestamp4 = this.mTimestampParser.parse(jsonParser);
                                    }
                                    builder.theatricalReleaseDate = timestamp4;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.amazonMaturityRating = str5;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        timestamp3 = this.mTimestampParser.parse(jsonParser);
                                    }
                                    builder.dvdStreetDate = timestamp3;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        num2 = this.mNullableIntegerParser.parse(jsonParser);
                                    }
                                    builder.episodeNumber = num2;
                                    continue;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.language = str4;
                                    continue;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        timestamp2 = this.mTimestampParser.parse(jsonParser);
                                    }
                                    builder.publicReleaseDate = timestamp2;
                                    continue;
                                case 16:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        timestamp = this.mTimestampParser.parse(jsonParser);
                                    }
                                    builder.preorderReleaseDate = timestamp;
                                    continue;
                                case 17:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.amazonMaturityRatingAstId = str3;
                                    continue;
                                case 18:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mStringListParser.parse(jsonParser);
                                    }
                                    builder.studios = immutableList2;
                                    continue;
                                case 19:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mStringListParser.parse(jsonParser);
                                    }
                                    builder.genres = immutableList;
                                    continue;
                                case 20:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mValidUntilTimestampParser.parse(jsonParser);
                                    }
                                    builder.validUntil = str2;
                                    continue;
                                case 21:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        contributors = this.mContributorsParser.parse(jsonParser);
                                    }
                                    builder.contributors = contributors;
                                    continue;
                                case 22:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.entityType = str;
                                    continue;
                                case 23:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        titleType = (TitleType) this.mTitleTypeParser.parse(jsonParser);
                                    }
                                    builder.type = titleType;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CatalogFragmentV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing CatalogFragmentV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.id, this, "id");
                    JsonParsingUtils.checkNotNull(builder.version, this, "version");
                    JsonParsingUtils.checkNotNull(builder.title, this, "title");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    return builder.build();
                }
            }
        }
    }
}
