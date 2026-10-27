package com.amazon.atv.schedule.distribution;

import com.amazon.atv.schedule.distribution.Image;
import com.amazon.atv.schedule.distribution.LocalizedString;
import com.amazon.atv.schedule.distribution.ParentDetails;
import com.amazon.atv.schedule.distribution.Rating;
import com.amazon.atv.schedule.distribution.ReleaseDetails;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class LinearProgram {
    public final Optional<LocalizedString> description;
    public final Optional<ImmutableMap<ImagePlacement, Image>> images;
    public final Optional<ImmutableList<ParentDetails>> parentDetails;
    public final Optional<String> programId;
    public final Optional<LinearProgramType> programType;
    public final Optional<ImmutableList<Rating>> ratings;
    public final Optional<ReleaseDetails> releaseDetails;
    public final Optional<LocalizedString> title;

    private LinearProgram(Builder builder) {
        this.description = Optional.fromNullable(builder.description);
        this.ratings = Optional.fromNullable(builder.ratings);
        this.parentDetails = Optional.fromNullable(builder.parentDetails);
        this.programType = Optional.fromNullable(builder.programType);
        this.programId = Optional.fromNullable(builder.programId);
        this.images = Optional.fromNullable(builder.images);
        this.title = Optional.fromNullable(builder.title);
        this.releaseDetails = Optional.fromNullable(builder.releaseDetails);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LinearProgram)) {
            return false;
        }
        LinearProgram linearProgram = (LinearProgram) obj;
        return Objects.equal(this.description, linearProgram.description) && Objects.equal(this.ratings, linearProgram.ratings) && Objects.equal(this.parentDetails, linearProgram.parentDetails) && Objects.equal(this.programType, linearProgram.programType) && Objects.equal(this.programId, linearProgram.programId) && Objects.equal(this.images, linearProgram.images) && Objects.equal(this.title, linearProgram.title) && Objects.equal(this.releaseDetails, linearProgram.releaseDetails);
    }

    public int hashCode() {
        return Objects.hashCode(this.description, this.ratings, this.parentDetails, this.programType, this.programId, this.images, this.title, this.releaseDetails);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(MediaTrack.ROLE_DESCRIPTION, this.description).add("ratings", this.ratings).add("parentDetails", this.parentDetails).add("programType", this.programType).add("programId", this.programId).add("images", this.images).add("title", this.title).add("releaseDetails", this.releaseDetails).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public LocalizedString description;
        public ImmutableMap<ImagePlacement, Image> images;
        public ImmutableList<ParentDetails> parentDetails;
        public String programId;
        public LinearProgramType programType;
        public ImmutableList<Rating> ratings;
        public ReleaseDetails releaseDetails;
        public LocalizedString title;

        public LinearProgram build() {
            return new LinearProgram(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<LinearProgram> {
        private final MapParser<ImagePlacement, Image> mImageMapParser;
        private final EnumParser<LinearProgramType> mLinearProgramTypeParser;
        private final LocalizedString.Parser mLocalizedStringParser;
        private final ListParser<ParentDetails> mParentDetailsListParser;
        private final ListParser<Rating> mRatingListParser;
        private final ReleaseDetails.Parser mReleaseDetailsParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mRatingListParser = ListParser.newParser(new Rating.Parser(objectMapper));
            this.mParentDetailsListParser = ListParser.newParser(new ParentDetails.Parser(objectMapper));
            this.mImageMapParser = MapParser.newParser(EnumParser.newParser(ImagePlacement.class), new Image.Parser(objectMapper));
            this.mLinearProgramTypeParser = EnumParser.newParser(LinearProgramType.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mReleaseDetailsParser = new ReleaseDetails.Parser(objectMapper);
            this.mLocalizedStringParser = new LocalizedString.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public LinearProgram parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearProgram:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public LinearProgram parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("LinearProgram:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private LinearProgram parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            LocalizedString localizedString;
            ImmutableList<Rating> immutableList;
            ImmutableList<ParentDetails> immutableList2;
            LinearProgramType linearProgramType;
            String str;
            ImmutableMap<ImagePlacement, Image> immutableMap;
            LocalizedString localizedString2;
            ReleaseDetails releaseDetails;
            JsonParsingUtils.throwIfNotObject(jsonNode, "LinearProgram");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1843684293:
                            if (next.equals("releaseDetails")) {
                                b = 7;
                            }
                            break;
                        case -1724546052:
                            if (next.equals(MediaTrack.ROLE_DESCRIPTION)) {
                                b = 0;
                            }
                            break;
                        case -1185250696:
                            if (next.equals("images")) {
                                b = 5;
                            }
                            break;
                        case -968780097:
                            if (next.equals("programId")) {
                                b = 4;
                            }
                            break;
                        case 110371416:
                            if (next.equals("title")) {
                                b = 6;
                            }
                            break;
                        case 983597686:
                            if (next.equals("ratings")) {
                                b = 1;
                            }
                            break;
                        case 1010581470:
                            if (next.equals("programType")) {
                                b = 3;
                            }
                            break;
                        case 2046119480:
                            if (next.equals("parentDetails")) {
                                b = 2;
                            }
                            break;
                    }
                    localizedString = null;
                    releaseDetails = null;
                    localizedString2 = null;
                    immutableMap = null;
                    str = null;
                    linearProgramType = null;
                    immutableList2 = null;
                    immutableList = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LinearProgram so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            localizedString = this.mLocalizedStringParser.parse(jsonNode2);
                        }
                        builder.description = localizedString;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mRatingListParser.parse(jsonNode2);
                        }
                        builder.ratings = immutableList;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            immutableList2 = this.mParentDetailsListParser.parse(jsonNode2);
                        }
                        builder.parentDetails = immutableList2;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            linearProgramType = (LinearProgramType) this.mLinearProgramTypeParser.parse(jsonNode2);
                        }
                        builder.programType = linearProgramType;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.programId = str;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mImageMapParser.parse(jsonNode2);
                        }
                        builder.images = immutableMap;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            localizedString2 = this.mLocalizedStringParser.parse(jsonNode2);
                        }
                        builder.title = localizedString2;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            releaseDetails = this.mReleaseDetailsParser.parse(jsonNode2);
                        }
                        builder.releaseDetails = releaseDetails;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing LinearProgram so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private LinearProgram parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1843684293:
                                    if (currentName.equals("releaseDetails")) {
                                        b = 7;
                                    }
                                    break;
                                case -1724546052:
                                    if (currentName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                                        b = 0;
                                    }
                                    break;
                                case -1185250696:
                                    if (currentName.equals("images")) {
                                        b = 5;
                                    }
                                    break;
                                case -968780097:
                                    if (currentName.equals("programId")) {
                                        b = 4;
                                    }
                                    break;
                                case 110371416:
                                    if (currentName.equals("title")) {
                                        b = 6;
                                    }
                                    break;
                                case 983597686:
                                    if (currentName.equals("ratings")) {
                                        b = 1;
                                    }
                                    break;
                                case 1010581470:
                                    if (currentName.equals("programType")) {
                                        b = 3;
                                    }
                                    break;
                                case 2046119480:
                                    if (currentName.equals("parentDetails")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            LocalizedString localizedString = null;
                            ReleaseDetails releaseDetails = null;
                            LocalizedString localizedString2 = null;
                            ImmutableMap<ImagePlacement, Image> immutableMap = null;
                            String str = null;
                            LinearProgramType linearProgramType = null;
                            ImmutableList<ParentDetails> immutableList = null;
                            ImmutableList<Rating> immutableList2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        localizedString = this.mLocalizedStringParser.parse(jsonParser);
                                    }
                                    builder.description = localizedString;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList2 = this.mRatingListParser.parse(jsonParser);
                                    }
                                    builder.ratings = immutableList2;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mParentDetailsListParser.parse(jsonParser);
                                    }
                                    builder.parentDetails = immutableList;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        linearProgramType = (LinearProgramType) this.mLinearProgramTypeParser.parse(jsonParser);
                                    }
                                    builder.programType = linearProgramType;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.programId = str;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mImageMapParser.parse(jsonParser);
                                    }
                                    builder.images = immutableMap;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        localizedString2 = this.mLocalizedStringParser.parse(jsonParser);
                                    }
                                    builder.title = localizedString2;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        releaseDetails = this.mReleaseDetailsParser.parse(jsonParser);
                                    }
                                    builder.releaseDetails = releaseDetails;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LinearProgram so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing LinearProgram so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
