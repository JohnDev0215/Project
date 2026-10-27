package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.Explanation;
import com.amazon.atv.discovery.Item;
import com.amazon.atv.discovery.TitleActions;
import com.amazon.atv.featureconfig.ImageType;
import com.amazon.atv.title.Title;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class TitleCard extends Item {
    public final Optional<Title> decoratedTitle;
    public final Optional<ImmutableList<Explanation>> explanations;
    public final Optional<ImageType> preferredImage;
    public final Optional<TitleActions> titleActions;
    public final String titleId;
    public final Optional<String> titleIdType;

    private TitleCard(Builder builder) {
        super(builder);
        this.decoratedTitle = Optional.fromNullable(builder.decoratedTitle);
        this.titleId = (String) Preconditions.checkNotNull(builder.titleId, "Unexpected null field: titleId");
        this.titleActions = Optional.fromNullable(builder.titleActions);
        this.preferredImage = Optional.fromNullable(builder.preferredImage);
        this.explanations = Optional.fromNullable(builder.explanations);
        this.titleIdType = Optional.fromNullable(builder.titleIdType);
    }

    @Override // com.amazon.atv.discovery.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleCard)) {
            return false;
        }
        TitleCard titleCard = (TitleCard) obj;
        return super.equals(obj) && Objects.equal(this.decoratedTitle, titleCard.decoratedTitle) && Objects.equal(this.titleId, titleCard.titleId) && Objects.equal(this.titleActions, titleCard.titleActions) && Objects.equal(this.preferredImage, titleCard.preferredImage) && Objects.equal(this.explanations, titleCard.explanations) && Objects.equal(this.titleIdType, titleCard.titleIdType);
    }

    @Override // com.amazon.atv.discovery.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.decoratedTitle, this.titleId, this.titleActions, this.preferredImage, this.explanations, this.titleIdType);
    }

    @Override // com.amazon.atv.discovery.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("decoratedTitle", this.decoratedTitle).add("titleId", this.titleId).add("titleActions", this.titleActions).add("preferredImage", this.preferredImage).add("explanations", this.explanations).add("titleIdType", this.titleIdType).toString();
    }

    @NotThreadSafe
    public static class Builder extends Item.Builder {
        public Title decoratedTitle;
        public ImmutableList<Explanation> explanations;
        public ImageType preferredImage;
        public TitleActions titleActions;
        public String titleId;
        public String titleIdType;

        public TitleCard build() {
            return new TitleCard(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TitleCard> {
        private final Analytics.Parser mAnalyticsParser;
        private final ListParser<Explanation> mExplanationListParser;
        private final EnumParser<ImageType> mImageTypeParser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;
        private final TitleActions.Parser mTitleActionsParser;
        private final Title.Parser mTitleParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mExplanationListParser = ListParser.newParser(new Explanation.Parser(objectMapper));
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mImageTypeParser = EnumParser.newParser(ImageType.class);
            this.mTitleParser = new Title.Parser(objectMapper);
            this.mStringParser = stringParser;
            this.mTitleActionsParser = new TitleActions.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TitleCard parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleCard:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TitleCard parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleCard:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TitleCard parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            Title title;
            ItemType itemType;
            String str;
            TitleActions titleActions;
            ImageType imageType;
            ImmutableMap<String, String> immutableMap;
            ImmutableList<Explanation> immutableList;
            Analytics analytics;
            String str2;
            String str3;
            JsonParsingUtils.throwIfNotObject(jsonNode, "TitleCard");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1694411430:
                            if (next.equals("preferredImage")) {
                                b = 5;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 8;
                            }
                            break;
                        case -1307249261:
                            if (next.equals("titleId")) {
                                b = 2;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 6;
                            }
                            break;
                        case -431887483:
                            if (next.equals("titleActions")) {
                                b = 4;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 1;
                            }
                            break;
                        case 77854074:
                            if (next.equals("explanations")) {
                                b = 7;
                            }
                            break;
                        case 215086957:
                            if (next.equals("titleIdType")) {
                                b = 9;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 3;
                            }
                            break;
                        case 1948695969:
                            if (next.equals("decoratedTitle")) {
                                b = 0;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 10;
                            }
                            break;
                    }
                    title = null;
                    str3 = null;
                    str2 = null;
                    analytics = null;
                    immutableList = null;
                    immutableMap = null;
                    imageType = null;
                    titleActions = null;
                    str = null;
                    itemType = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleCard so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            title = this.mTitleParser.parse(jsonNode2);
                        }
                        builder.decoratedTitle = title;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            itemType = (ItemType) this.mItemTypeParser.parse(jsonNode2);
                        }
                        builder.type = itemType;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.titleId = str;
                        continue;
                    case 3:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        continue;
                        break;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            titleActions = this.mTitleActionsParser.parse(jsonNode2);
                        }
                        builder.titleActions = titleActions;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            imageType = (ImageType) this.mImageTypeParser.parse(jsonNode2);
                        }
                        builder.preferredImage = imageType;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mStringMapParser.parse(jsonNode2);
                        }
                        builder.metadata = immutableMap;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mExplanationListParser.parse(jsonNode2);
                        }
                        builder.explanations = immutableList;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.titleIdType = str2;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = str3;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleCard so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(builder.titleId, this, "titleId");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TitleCard parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1694411430:
                                    if (currentName.equals("preferredImage")) {
                                        b = 5;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 8;
                                    }
                                    break;
                                case -1307249261:
                                    if (currentName.equals("titleId")) {
                                        b = 2;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 6;
                                    }
                                    break;
                                case -431887483:
                                    if (currentName.equals("titleActions")) {
                                        b = 4;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 1;
                                    }
                                    break;
                                case 77854074:
                                    if (currentName.equals("explanations")) {
                                        b = 7;
                                    }
                                    break;
                                case 215086957:
                                    if (currentName.equals("titleIdType")) {
                                        b = 9;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 3;
                                    }
                                    break;
                                case 1948695969:
                                    if (currentName.equals("decoratedTitle")) {
                                        b = 0;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = 10;
                                    }
                                    break;
                            }
                            Title title = null;
                            String str = null;
                            String str2 = null;
                            Analytics analytics = null;
                            ImmutableList<Explanation> immutableList = null;
                            ImmutableMap<String, String> immutableMap = null;
                            ImageType imageType = null;
                            TitleActions titleActions = null;
                            String str3 = null;
                            ItemType itemType = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        title = this.mTitleParser.parse(jsonParser);
                                    }
                                    builder.decoratedTitle = title;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.titleId = str3;
                                    continue;
                                case 3:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    continue;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        titleActions = this.mTitleActionsParser.parse(jsonParser);
                                    }
                                    builder.titleActions = titleActions;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        imageType = (ImageType) this.mImageTypeParser.parse(jsonParser);
                                    }
                                    builder.preferredImage = imageType;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mExplanationListParser.parse(jsonParser);
                                    }
                                    builder.explanations = immutableList;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.titleIdType = str2;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleCard so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleCard so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(builder.titleId, this, "titleId");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    return builder.build();
                }
            }
        }
    }
}
