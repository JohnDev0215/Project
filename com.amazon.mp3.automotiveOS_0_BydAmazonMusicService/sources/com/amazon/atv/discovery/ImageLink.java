package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.Image;
import com.amazon.atv.discovery.ImageSize;
import com.amazon.atv.discovery.LinkItem;
import com.amazon.atv.discovery.NavigationalAction;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class ImageLink extends LinkItem {
    public final Optional<String> alternateText;
    public final Optional<Image> backgroundImage;
    public final boolean gradientRequired;
    public final String image;
    public final Optional<ImageSize> imageSize;
    public final Optional<String> text;

    protected ImageLink(Builder builder) {
        super(builder);
        this.text = Optional.fromNullable(builder.text);
        this.alternateText = Optional.fromNullable(builder.alternateText);
        this.gradientRequired = builder.gradientRequired;
        this.imageSize = Optional.fromNullable(builder.imageSize);
        this.backgroundImage = Optional.fromNullable(builder.backgroundImage);
        this.image = (String) Preconditions.checkNotNull(builder.image, "Unexpected null field: image");
    }

    @Override // com.amazon.atv.discovery.LinkItem, com.amazon.atv.discovery.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImageLink)) {
            return false;
        }
        ImageLink imageLink = (ImageLink) obj;
        return super.equals(obj) && Objects.equal(this.text, imageLink.text) && Objects.equal(this.alternateText, imageLink.alternateText) && Objects.equal(Boolean.valueOf(this.gradientRequired), Boolean.valueOf(imageLink.gradientRequired)) && Objects.equal(this.imageSize, imageLink.imageSize) && Objects.equal(this.backgroundImage, imageLink.backgroundImage) && Objects.equal(this.image, imageLink.image);
    }

    @Override // com.amazon.atv.discovery.LinkItem, com.amazon.atv.discovery.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.text, this.alternateText, Boolean.valueOf(this.gradientRequired), this.imageSize, this.backgroundImage, this.image);
    }

    @Override // com.amazon.atv.discovery.LinkItem, com.amazon.atv.discovery.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("text", this.text).add("alternateText", this.alternateText).add("gradientRequired", this.gradientRequired).add("imageSize", this.imageSize).add("backgroundImage", this.backgroundImage).add(ContextMappingConstants.IMAGE, this.image).toString();
    }

    @NotThreadSafe
    public static class Builder extends LinkItem.Builder {
        public String alternateText;
        public Image backgroundImage;
        public boolean gradientRequired;
        public String image;
        public ImageSize imageSize;
        public String text;

        public ImageLink build() {
            return new ImageLink(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ImageLink> {
        private final Analytics.Parser mAnalyticsParser;
        private final Image.Parser mImageParser;
        private final ImageSize.Parser mImageSizeParser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final NavigationalAction.Parser mNavigationalActionParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mNavigationalActionParser = new NavigationalAction.Parser(objectMapper);
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mImageParser = new Image.Parser(objectMapper);
            this.mStringParser = stringParser;
            this.mImageSizeParser = new ImageSize.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ImageLink parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ImageLink:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ImageLink parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ImageLink:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ImageLink parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ImageLink");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case -878192644:
                            if (next.equals("imageSize")) {
                                b = 7;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 10;
                            }
                            break;
                        case -145609529:
                            if (next.equals("alternateText")) {
                                b = 1;
                            }
                            break;
                        case 3321850:
                            if (next.equals("link")) {
                                b = 3;
                            }
                            break;
                        case 3556653:
                            if (next.equals("text")) {
                                b = 0;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 4;
                            }
                            break;
                        case 100313435:
                            if (next.equals(ContextMappingConstants.IMAGE)) {
                                b = 2;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 5;
                            }
                            break;
                        case 1191572123:
                            if (next.equals("selected")) {
                                b = 9;
                            }
                            break;
                        case 1292595405:
                            if (next.equals("backgroundImage")) {
                                b = 8;
                            }
                            break;
                        case 2125551343:
                            if (next.equals("gradientRequired")) {
                                b = 6;
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
                    ImmutableMap<String, String> immutableMap = null;
                    Image image = null;
                    ImageSize imageSize = null;
                    ItemType itemType = null;
                    NavigationalAction navigationalAction = null;
                    String str3 = null;
                    String str4 = null;
                    switch (b) {
                        case 0:
                            if (!jsonNode2.isNull()) {
                                str = this.mStringParser.parse(jsonNode2);
                            }
                            builder.text = str;
                            break;
                        case 1:
                            if (!jsonNode2.isNull()) {
                                str4 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.alternateText = str4;
                            break;
                        case 2:
                            if (!jsonNode2.isNull()) {
                                str3 = this.mStringParser.parse(jsonNode2);
                            }
                            builder.image = str3;
                            break;
                        case 3:
                            if (!jsonNode2.isNull()) {
                                navigationalAction = this.mNavigationalActionParser.parse(jsonNode2);
                            }
                            builder.link = navigationalAction;
                            break;
                        case 4:
                            if (!jsonNode2.isNull()) {
                                itemType = (ItemType) this.mItemTypeParser.parse(jsonNode2);
                            }
                            builder.type = itemType;
                            break;
                        case 5:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field version can't be null");
                            }
                            builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                            break;
                            break;
                        case 6:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field gradientRequired can't be null");
                            }
                            builder.gradientRequired = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 7:
                            if (!jsonNode2.isNull()) {
                                imageSize = this.mImageSizeParser.parse(jsonNode2);
                            }
                            builder.imageSize = imageSize;
                            break;
                        case 8:
                            if (!jsonNode2.isNull()) {
                                image = this.mImageParser.parse(jsonNode2);
                            }
                            builder.backgroundImage = image;
                            break;
                        case 9:
                            if (jsonNode2.isNull()) {
                                throw new JsonContractException("primitive field selected can't be null");
                            }
                            builder.selected = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                            break;
                            break;
                        case 10:
                            if (!jsonNode2.isNull()) {
                                immutableMap = this.mStringMapParser.parse(jsonNode2);
                            }
                            builder.metadata = immutableMap;
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
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ImageLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.image, this, ContextMappingConstants.IMAGE);
            JsonParsingUtils.checkNotNull(builder.link, this, "link");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ImageLink parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case -878192644:
                                    if (currentName.equals("imageSize")) {
                                        b = 7;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 10;
                                    }
                                    break;
                                case -145609529:
                                    if (currentName.equals("alternateText")) {
                                        b = 1;
                                    }
                                    break;
                                case 3321850:
                                    if (currentName.equals("link")) {
                                        b = 3;
                                    }
                                    break;
                                case 3556653:
                                    if (currentName.equals("text")) {
                                        b = 0;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 4;
                                    }
                                    break;
                                case 100313435:
                                    if (currentName.equals(ContextMappingConstants.IMAGE)) {
                                        b = 2;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 5;
                                    }
                                    break;
                                case 1191572123:
                                    if (currentName.equals("selected")) {
                                        b = 9;
                                    }
                                    break;
                                case 1292595405:
                                    if (currentName.equals("backgroundImage")) {
                                        b = 8;
                                    }
                                    break;
                                case 2125551343:
                                    if (currentName.equals("gradientRequired")) {
                                        b = 6;
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
                            ImmutableMap<String, String> immutableMap = null;
                            Image image = null;
                            ImageSize imageSize = null;
                            ItemType itemType = null;
                            NavigationalAction navigationalAction = null;
                            String str3 = null;
                            String str4 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.text = str;
                                    break;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.alternateText = str4;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.image = str3;
                                    break;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        navigationalAction = this.mNavigationalActionParser.parse(jsonParser);
                                    }
                                    builder.link = navigationalAction;
                                    break;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    break;
                                case 5:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    break;
                                    break;
                                case 6:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field gradientRequired can't be null");
                                    }
                                    builder.gradientRequired = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        imageSize = this.mImageSizeParser.parse(jsonParser);
                                    }
                                    builder.imageSize = imageSize;
                                    break;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        image = this.mImageParser.parse(jsonParser);
                                    }
                                    builder.backgroundImage = image;
                                    break;
                                case 9:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field selected can't be null");
                                    }
                                    builder.selected = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                                    break;
                                    break;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap;
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
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ImageLink so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.image, this, ContextMappingConstants.IMAGE);
                    JsonParsingUtils.checkNotNull(builder.link, this, "link");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    return builder.build();
                }
            }
        }
    }
}
