package com.amazon.atv.title.v1;

import com.amazon.atv.title.Title;
import com.amazon.atv.title.v1.AccessibilityFragment;
import com.amazon.atv.title.v1.AcquisitionFragment;
import com.amazon.atv.title.v1.CatalogFragment;
import com.amazon.atv.title.v1.ComputedFragment;
import com.amazon.atv.title.v1.FamilyFragment;
import com.amazon.atv.title.v1.ImagesFragment;
import com.amazon.atv.title.v1.ImdbFragment;
import com.amazon.atv.title.v1.PlaybackFragment;
import com.amazon.atv.title.v1.ReviewsFragment;
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
public class TitleV1 extends Title {
    public final Optional<AccessibilityFragment> accessibility;
    public final Optional<AcquisitionFragment> acquisition;
    public final Optional<CatalogFragment> catalog;
    public final Optional<ComputedFragment> computed;
    public final Optional<FamilyFragment> family;
    public final Optional<ImagesFragment> images;
    public final Optional<ImdbFragment> imdb;
    public final Optional<PlaybackFragment> playback;
    public final Optional<ReviewsFragment> reviews;

    private TitleV1(Builder builder) {
        super(builder);
        this.reviews = Optional.fromNullable(builder.reviews);
        this.playback = Optional.fromNullable(builder.playback);
        this.imdb = Optional.fromNullable(builder.imdb);
        this.acquisition = Optional.fromNullable(builder.acquisition);
        this.computed = Optional.fromNullable(builder.computed);
        this.catalog = Optional.fromNullable(builder.catalog);
        this.family = Optional.fromNullable(builder.family);
        this.images = Optional.fromNullable(builder.images);
        this.accessibility = Optional.fromNullable(builder.accessibility);
    }

    @Override // com.amazon.atv.title.Title
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleV1)) {
            return false;
        }
        TitleV1 titleV1 = (TitleV1) obj;
        return super.equals(obj) && Objects.equal(this.reviews, titleV1.reviews) && Objects.equal(this.playback, titleV1.playback) && Objects.equal(this.imdb, titleV1.imdb) && Objects.equal(this.acquisition, titleV1.acquisition) && Objects.equal(this.computed, titleV1.computed) && Objects.equal(this.catalog, titleV1.catalog) && Objects.equal(this.family, titleV1.family) && Objects.equal(this.images, titleV1.images) && Objects.equal(this.accessibility, titleV1.accessibility);
    }

    @Override // com.amazon.atv.title.Title
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.reviews, this.playback, this.imdb, this.acquisition, this.computed, this.catalog, this.family, this.images, this.accessibility);
    }

    @Override // com.amazon.atv.title.Title
    public String toString() {
        return MoreObjects.toStringHelper(this).add("reviews", this.reviews).add("playback", this.playback).add("imdb", this.imdb).add("acquisition", this.acquisition).add("computed", this.computed).add("catalog", this.catalog).add("family", this.family).add("images", this.images).add("accessibility", this.accessibility).toString();
    }

    @NotThreadSafe
    public static class Builder extends Title.Builder {
        public AccessibilityFragment accessibility;
        public AcquisitionFragment acquisition;
        public CatalogFragment catalog;
        public ComputedFragment computed;
        public FamilyFragment family;
        public ImagesFragment images;
        public ImdbFragment imdb;
        public PlaybackFragment playback;
        public ReviewsFragment reviews;

        public TitleV1 build() {
            return new TitleV1(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TitleV1> {
        private final AccessibilityFragment.Parser mAccessibilityFragmentParser;
        private final AcquisitionFragment.Parser mAcquisitionFragmentParser;
        private final CatalogFragment.Parser mCatalogFragmentParser;
        private final ComputedFragment.Parser mComputedFragmentParser;
        private final FamilyFragment.Parser mFamilyFragmentParser;
        private final ImagesFragment.Parser mImagesFragmentParser;
        private final ImdbFragment.Parser mImdbFragmentParser;
        private final PlaybackFragment.Parser mPlaybackFragmentParser;
        private final ReviewsFragment.Parser mReviewsFragmentParser;
        private final SimpleParsers.StringParser mTitleVersionParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mReviewsFragmentParser = new ReviewsFragment.Parser(objectMapper);
            this.mAcquisitionFragmentParser = new AcquisitionFragment.Parser(objectMapper);
            this.mComputedFragmentParser = new ComputedFragment.Parser(objectMapper);
            this.mPlaybackFragmentParser = new PlaybackFragment.Parser(objectMapper);
            this.mImagesFragmentParser = new ImagesFragment.Parser(objectMapper);
            this.mImdbFragmentParser = new ImdbFragment.Parser(objectMapper);
            this.mAccessibilityFragmentParser = new AccessibilityFragment.Parser(objectMapper);
            this.mCatalogFragmentParser = new CatalogFragment.Parser(objectMapper);
            this.mFamilyFragmentParser = new FamilyFragment.Parser(objectMapper);
            this.mTitleVersionParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TitleV1 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleV1:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TitleV1 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleV1:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TitleV1 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ReviewsFragment reviewsFragment;
            PlaybackFragment playbackFragment;
            ImdbFragment imdbFragment;
            AcquisitionFragment acquisitionFragment;
            String str;
            ComputedFragment computedFragment;
            CatalogFragment catalogFragment;
            FamilyFragment familyFragment;
            ImagesFragment imagesFragment;
            AccessibilityFragment accessibilityFragment;
            JsonParsingUtils.throwIfNotObject(jsonNode, "TitleV1");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1281860764:
                            if (next.equals("family")) {
                                b = 7;
                            }
                            break;
                        case -1185250696:
                            if (next.equals("images")) {
                                b = 8;
                            }
                            break;
                        case -599163123:
                            if (next.equals("computed")) {
                                b = 5;
                            }
                            break;
                        case -213139122:
                            if (next.equals("accessibility")) {
                                b = 9;
                            }
                            break;
                        case 3236002:
                            if (next.equals("imdb")) {
                                b = 2;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 4;
                            }
                            break;
                        case 555704345:
                            if (next.equals("catalog")) {
                                b = 6;
                            }
                            break;
                        case 1099953179:
                            if (next.equals("reviews")) {
                                b = 0;
                            }
                            break;
                        case 1272352653:
                            if (next.equals("acquisition")) {
                                b = 3;
                            }
                            break;
                        case 1879168539:
                            if (next.equals("playback")) {
                                b = 1;
                            }
                            break;
                    }
                    reviewsFragment = null;
                    accessibilityFragment = null;
                    imagesFragment = null;
                    familyFragment = null;
                    catalogFragment = null;
                    computedFragment = null;
                    str = null;
                    acquisitionFragment = null;
                    imdbFragment = null;
                    playbackFragment = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            reviewsFragment = this.mReviewsFragmentParser.parse(jsonNode2);
                        }
                        builder.reviews = reviewsFragment;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            playbackFragment = this.mPlaybackFragmentParser.parse(jsonNode2);
                        }
                        builder.playback = playbackFragment;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            imdbFragment = this.mImdbFragmentParser.parse(jsonNode2);
                        }
                        builder.imdb = imdbFragment;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            acquisitionFragment = this.mAcquisitionFragmentParser.parse(jsonNode2);
                        }
                        builder.acquisition = acquisitionFragment;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str = this.mTitleVersionParser.parse(jsonNode2);
                        }
                        builder.version = str;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            computedFragment = this.mComputedFragmentParser.parse(jsonNode2);
                        }
                        builder.computed = computedFragment;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            catalogFragment = this.mCatalogFragmentParser.parse(jsonNode2);
                        }
                        builder.catalog = catalogFragment;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            familyFragment = this.mFamilyFragmentParser.parse(jsonNode2);
                        }
                        builder.family = familyFragment;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            imagesFragment = this.mImagesFragmentParser.parse(jsonNode2);
                        }
                        builder.images = imagesFragment;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            accessibilityFragment = this.mAccessibilityFragmentParser.parse(jsonNode2);
                        }
                        builder.accessibility = accessibilityFragment;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TitleV1 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1281860764:
                                    if (currentName.equals("family")) {
                                        b = 7;
                                    }
                                    break;
                                case -1185250696:
                                    if (currentName.equals("images")) {
                                        b = 8;
                                    }
                                    break;
                                case -599163123:
                                    if (currentName.equals("computed")) {
                                        b = 5;
                                    }
                                    break;
                                case -213139122:
                                    if (currentName.equals("accessibility")) {
                                        b = 9;
                                    }
                                    break;
                                case 3236002:
                                    if (currentName.equals("imdb")) {
                                        b = 2;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 4;
                                    }
                                    break;
                                case 555704345:
                                    if (currentName.equals("catalog")) {
                                        b = 6;
                                    }
                                    break;
                                case 1099953179:
                                    if (currentName.equals("reviews")) {
                                        b = 0;
                                    }
                                    break;
                                case 1272352653:
                                    if (currentName.equals("acquisition")) {
                                        b = 3;
                                    }
                                    break;
                                case 1879168539:
                                    if (currentName.equals("playback")) {
                                        b = 1;
                                    }
                                    break;
                            }
                            ReviewsFragment reviewsFragment = null;
                            AccessibilityFragment accessibilityFragment = null;
                            ImagesFragment imagesFragment = null;
                            FamilyFragment familyFragment = null;
                            CatalogFragment catalogFragment = null;
                            ComputedFragment computedFragment = null;
                            String str = null;
                            AcquisitionFragment acquisitionFragment = null;
                            ImdbFragment imdbFragment = null;
                            PlaybackFragment playbackFragment = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        reviewsFragment = this.mReviewsFragmentParser.parse(jsonParser);
                                    }
                                    builder.reviews = reviewsFragment;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        playbackFragment = this.mPlaybackFragmentParser.parse(jsonParser);
                                    }
                                    builder.playback = playbackFragment;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        imdbFragment = this.mImdbFragmentParser.parse(jsonParser);
                                    }
                                    builder.imdb = imdbFragment;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        acquisitionFragment = this.mAcquisitionFragmentParser.parse(jsonParser);
                                    }
                                    builder.acquisition = acquisitionFragment;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mTitleVersionParser.parse(jsonParser);
                                    }
                                    builder.version = str;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        computedFragment = this.mComputedFragmentParser.parse(jsonParser);
                                    }
                                    builder.computed = computedFragment;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        catalogFragment = this.mCatalogFragmentParser.parse(jsonParser);
                                    }
                                    builder.catalog = catalogFragment;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        familyFragment = this.mFamilyFragmentParser.parse(jsonParser);
                                    }
                                    builder.family = familyFragment;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        imagesFragment = this.mImagesFragmentParser.parse(jsonParser);
                                    }
                                    builder.images = imagesFragment;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        accessibilityFragment = this.mAccessibilityFragmentParser.parse(jsonParser);
                                    }
                                    builder.accessibility = accessibilityFragment;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleV1 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
