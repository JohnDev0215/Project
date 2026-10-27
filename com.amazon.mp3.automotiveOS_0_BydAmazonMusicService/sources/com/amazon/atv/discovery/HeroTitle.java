package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.ImageSize;
import com.amazon.atv.discovery.Item;
import com.amazon.atv.title.Title;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
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
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class HeroTitle extends Item {
    public final Optional<String> alternateText;
    public final Optional<Title> decoratedTitle;
    public final boolean gradientRequired;
    public final String heroImage;
    public final Optional<ImageSize> imageSize;
    public final Optional<String> text;
    public final String titleId;
    public final Optional<String> titleIdType;

    private HeroTitle(Builder builder) {
        super(builder);
        this.titleId = (String) Preconditions.checkNotNull(builder.titleId, "Unexpected null field: titleId");
        this.decoratedTitle = Optional.fromNullable(builder.decoratedTitle);
        this.titleIdType = Optional.fromNullable(builder.titleIdType);
        this.imageSize = Optional.fromNullable(builder.imageSize);
        this.gradientRequired = builder.gradientRequired;
        this.alternateText = Optional.fromNullable(builder.alternateText);
        this.text = Optional.fromNullable(builder.text);
        this.heroImage = (String) Preconditions.checkNotNull(builder.heroImage, "Unexpected null field: heroImage");
    }

    @Override // com.amazon.atv.discovery.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HeroTitle)) {
            return false;
        }
        HeroTitle heroTitle = (HeroTitle) obj;
        return super.equals(obj) && Objects.equal(this.titleId, heroTitle.titleId) && Objects.equal(this.decoratedTitle, heroTitle.decoratedTitle) && Objects.equal(this.titleIdType, heroTitle.titleIdType) && Objects.equal(this.imageSize, heroTitle.imageSize) && Objects.equal(Boolean.valueOf(this.gradientRequired), Boolean.valueOf(heroTitle.gradientRequired)) && Objects.equal(this.alternateText, heroTitle.alternateText) && Objects.equal(this.text, heroTitle.text) && Objects.equal(this.heroImage, heroTitle.heroImage);
    }

    @Override // com.amazon.atv.discovery.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.titleId, this.decoratedTitle, this.titleIdType, this.imageSize, Boolean.valueOf(this.gradientRequired), this.alternateText, this.text, this.heroImage);
    }

    @Override // com.amazon.atv.discovery.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("titleId", this.titleId).add("decoratedTitle", this.decoratedTitle).add("titleIdType", this.titleIdType).add("imageSize", this.imageSize).add("gradientRequired", this.gradientRequired).add("alternateText", this.alternateText).add("text", this.text).add("heroImage", this.heroImage).toString();
    }

    @NotThreadSafe
    public static class Builder extends Item.Builder {
        public String alternateText;
        public Title decoratedTitle;
        public boolean gradientRequired;
        public String heroImage;
        public ImageSize imageSize;
        public String text;
        public String titleId;
        public String titleIdType;

        public HeroTitle build() {
            return new HeroTitle(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<HeroTitle> {
        private final Analytics.Parser mAnalyticsParser;
        private final ImageSize.Parser mImageSizeParser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;
        private final Title.Parser mTitleParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mTitleParser = new Title.Parser(objectMapper);
            this.mStringParser = stringParser;
            this.mImageSizeParser = new ImageSize.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public HeroTitle parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("HeroTitle:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public HeroTitle parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("HeroTitle:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private HeroTitle parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "HeroTitle");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2016684671:
                            if (next.equals("heroImage")) {
                                b = 10;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case -1307249261:
                            if (next.equals("titleId")) {
                                b = 0;
                            }
                            break;
                        case -878192644:
                            if (next.equals("imageSize")) {
                                b = 2;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 9;
                            }
                            break;
                        case -145609529:
                            if (next.equals("alternateText")) {
                                b = 4;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 8;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 6;
                            }
                            break;
                        case 215086957:
                            if (next.equals("titleIdType")) {
                                b = 5;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 7;
                            }
                            break;
                        case 1948695969:
                            if (next.equals("decoratedTitle")) {
                                b = 1;
                            }
                            break;
                        case 2125551343:
                            if (next.equals("gradientRequired")) {
                                b = 3;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    Analytics analytics = null;
                    String str3 = null;
                    ImmutableMap<String, String> immutableMap = null;
                    String str4 = null;
                    ItemType itemType = null;
                    String str5 = null;
                    String str6 = null;
                    ImageSize imageSize = null;
                    Title title = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.titleId = str;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                title = this.mTitleParser.parse(jsonNode2);
                            }
                            builder.decoratedTitle = title;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                imageSize = this.mImageSizeParser.parse(jsonNode2);
                            }
                            builder.imageSize = imageSize;
                            break;
                        case 3:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field gradientRequired can't be null");
                            }
                            builder.gradientRequired = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                str6 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.alternateText = str6;
                            break;
                        case 5:
                            if (!jsonNode2.isNull()) {
                                str5 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.titleIdType = str5;
                            break;
                        case 6:
                            if (!jsonNode2.isNull()) {
                                itemType = (ItemType) this.mItemTypeParser.parse(jsonNode2);
                            }
                            builder.type = itemType;
                            break;
                        case 7:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 8:
                            if (!jsonNode2.isNull()) {
                                str4 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.text = str4;
                            break;
                        case 9:
                            if (!jsonNode2.isNull()) {
                                immutableMap = this.mStringMapParser.parse(jsonNode2);
                            }
                            builder.metadata = immutableMap;
                            break;
                        case 10:
                            if (!jsonNode2.isNull()) {
                                str3 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.heroImage = str3;
                            break;
                        case 11:
                            if (!jsonNode2.isNull()) {
                                analytics = this.mAnalyticsParser.parse(jsonNode2);
                            }
                            builder.analytics = analytics;
                            break;
                        case 12:
                            if (!jsonNode2.isNull()) {
                                str2 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.debugAttributes = str2;
                            break;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing HeroTitle so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.titleId, this, "titleId");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.heroImage, this, "heroImage");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private HeroTitle parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2016684671:
                                    if (currentName.equals("heroImage")) {
                                        b = 10;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case -1307249261:
                                    if (currentName.equals("titleId")) {
                                        b = 0;
                                    }
                                    break;
                                case -878192644:
                                    if (currentName.equals("imageSize")) {
                                        b = 2;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 9;
                                    }
                                    break;
                                case -145609529:
                                    if (currentName.equals("alternateText")) {
                                        b = 4;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 8;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 6;
                                    }
                                    break;
                                case 215086957:
                                    if (currentName.equals("titleIdType")) {
                                        b = 5;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 7;
                                    }
                                    break;
                                case 1948695969:
                                    if (currentName.equals("decoratedTitle")) {
                                        b = 1;
                                    }
                                    break;
                                case 2125551343:
                                    if (currentName.equals("gradientRequired")) {
                                        b = 3;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            Analytics analytics = null;
                            String str3 = null;
                            ImmutableMap<String, String> immutableMap = null;
                            String str4 = null;
                            ItemType itemType = null;
                            String str5 = null;
                            String str6 = null;
                            ImageSize imageSize = null;
                            Title title = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.titleId = str;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        title = this.mTitleParser.parse(jsonParser);
                                    }
                                    builder.decoratedTitle = title;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        imageSize = this.mImageSizeParser.parse(jsonParser);
                                    }
                                    builder.imageSize = imageSize;
                                    break;
                                case 3:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field gradientRequired can't be null");
                                    }
                                    builder.gradientRequired = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.alternateText = str6;
                                    break;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.titleIdType = str5;
                                    break;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    break;
                                case 7:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str4;
                                    break;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap;
                                    break;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.heroImage = str3;
                                    break;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    break;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str2;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    break;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing HeroTitle so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.titleId, this, "titleId");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.heroImage, this, "heroImage");
                    return builder.build();
                }
            }
        }
    }
}
