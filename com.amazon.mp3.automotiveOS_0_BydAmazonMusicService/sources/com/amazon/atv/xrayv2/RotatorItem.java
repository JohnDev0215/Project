package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.xrayv2.Image;
import com.amazon.atv.xrayv2.Item;
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

/* JADX INFO: loaded from: classes2.dex */
public class RotatorItem extends Item {
    public final String alternateText;
    public final Image backgroundImage;
    public final String clickstreamId;
    public final String intentURI;
    public final Optional<Image> logoImage;
    public final Optional<String> trailerURL;

    private RotatorItem(Builder builder) {
        super(builder);
        this.intentURI = (String) Preconditions.checkNotNull(builder.intentURI, "Unexpected null field: intentURI");
        this.backgroundImage = (Image) Preconditions.checkNotNull(builder.backgroundImage, "Unexpected null field: backgroundImage");
        this.alternateText = (String) Preconditions.checkNotNull(builder.alternateText, "Unexpected null field: alternateText");
        this.trailerURL = Optional.fromNullable(builder.trailerURL);
        this.logoImage = Optional.fromNullable(builder.logoImage);
        this.clickstreamId = (String) Preconditions.checkNotNull(builder.clickstreamId, "Unexpected null field: clickstreamId");
    }

    @Override // com.amazon.atv.xrayv2.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RotatorItem)) {
            return false;
        }
        RotatorItem rotatorItem = (RotatorItem) obj;
        return super.equals(obj) && Objects.equal(this.intentURI, rotatorItem.intentURI) && Objects.equal(this.backgroundImage, rotatorItem.backgroundImage) && Objects.equal(this.alternateText, rotatorItem.alternateText) && Objects.equal(this.trailerURL, rotatorItem.trailerURL) && Objects.equal(this.logoImage, rotatorItem.logoImage) && Objects.equal(this.clickstreamId, rotatorItem.clickstreamId);
    }

    @Override // com.amazon.atv.xrayv2.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.intentURI, this.backgroundImage, this.alternateText, this.trailerURL, this.logoImage, this.clickstreamId);
    }

    @Override // com.amazon.atv.xrayv2.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("intentURI", this.intentURI).add("backgroundImage", this.backgroundImage).add("alternateText", this.alternateText).add("trailerURL", this.trailerURL).add("logoImage", this.logoImage).add("clickstreamId", this.clickstreamId).toString();
    }

    @NotThreadSafe
    public static class Builder extends Item.Builder {
        public String alternateText;
        public Image backgroundImage;
        public String clickstreamId;
        public String intentURI;
        public Image logoImage;
        public String trailerURL;

        public RotatorItem build() {
            return new RotatorItem(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<RotatorItem> {
        private final Analytics.Parser mAnalyticsParser;
        private final Image.Parser mImageParser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            this.mImageParser = new Image.Parser(objectMapper);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public RotatorItem parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RotatorItem:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public RotatorItem parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("RotatorItem:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RotatorItem parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            Image image;
            Analytics analytics;
            String str2;
            String str3;
            ImmutableMap<String, String> immutableMap;
            String str4;
            Image image2;
            String str5;
            ItemType itemType;
            JsonParsingUtils.throwIfNotObject(jsonNode, "RotatorItem");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2070887620:
                            if (next.equals("trailerURL")) {
                                b = 6;
                            }
                            break;
                        case -1693017210:
                            if (next.equals("analytics")) {
                                b = 2;
                            }
                            break;
                        case -1580939664:
                            if (next.equals("logoImage")) {
                                b = 7;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 5;
                            }
                            break;
                        case -145609529:
                            if (next.equals("alternateText")) {
                                b = 4;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 9;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 10;
                            }
                            break;
                        case 499334448:
                            if (next.equals("intentURI")) {
                                b = 0;
                            }
                            break;
                        case 1292595405:
                            if (next.equals("backgroundImage")) {
                                b = 1;
                            }
                            break;
                        case 1764439619:
                            if (next.equals("clickstreamId")) {
                                b = 8;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 3;
                            }
                            break;
                    }
                    str = null;
                    itemType = null;
                    str5 = null;
                    image2 = null;
                    str4 = null;
                    immutableMap = null;
                    str3 = null;
                    str2 = null;
                    analytics = null;
                    image = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RotatorItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.intentURI = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            image = this.mImageParser.parse(jsonNode2);
                        }
                        builder.backgroundImage = image;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = str2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.alternateText = str3;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mStringMapParser.parse(jsonNode2);
                        }
                        builder.metadata = immutableMap;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.trailerURL = str4;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            image2 = this.mImageParser.parse(jsonNode2);
                        }
                        builder.logoImage = image2;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.clickstreamId = str5;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            itemType = (ItemType) this.mItemTypeParser.parse(jsonNode2);
                        }
                        builder.type = itemType;
                        continue;
                    case 10:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        continue;
                        break;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing RotatorItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.intentURI, this, "intentURI");
            JsonParsingUtils.checkNotNull(builder.backgroundImage, this, "backgroundImage");
            JsonParsingUtils.checkNotNull(builder.alternateText, this, "alternateText");
            JsonParsingUtils.checkNotNull(builder.clickstreamId, this, "clickstreamId");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private RotatorItem parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2070887620:
                                    if (currentName.equals("trailerURL")) {
                                        b = 6;
                                    }
                                    break;
                                case -1693017210:
                                    if (currentName.equals("analytics")) {
                                        b = 2;
                                    }
                                    break;
                                case -1580939664:
                                    if (currentName.equals("logoImage")) {
                                        b = 7;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 5;
                                    }
                                    break;
                                case -145609529:
                                    if (currentName.equals("alternateText")) {
                                        b = 4;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 9;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 10;
                                    }
                                    break;
                                case 499334448:
                                    if (currentName.equals("intentURI")) {
                                        b = 0;
                                    }
                                    break;
                                case 1292595405:
                                    if (currentName.equals("backgroundImage")) {
                                        b = 1;
                                    }
                                    break;
                                case 1764439619:
                                    if (currentName.equals("clickstreamId")) {
                                        b = 8;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            ItemType itemType = null;
                            String str2 = null;
                            Image image = null;
                            String str3 = null;
                            ImmutableMap<String, String> immutableMap = null;
                            String str4 = null;
                            String str5 = null;
                            Analytics analytics = null;
                            Image image2 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.intentURI = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        image2 = this.mImageParser.parse(jsonParser);
                                    }
                                    builder.backgroundImage = image2;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str5;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.alternateText = str4;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.trailerURL = str3;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        image = this.mImageParser.parse(jsonParser);
                                    }
                                    builder.logoImage = image;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.clickstreamId = str2;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    continue;
                                case 10:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    continue;
                                    break;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RotatorItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing RotatorItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.intentURI, this, "intentURI");
                    JsonParsingUtils.checkNotNull(builder.backgroundImage, this, "backgroundImage");
                    JsonParsingUtils.checkNotNull(builder.alternateText, this, "alternateText");
                    JsonParsingUtils.checkNotNull(builder.clickstreamId, this, "clickstreamId");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    return builder.build();
                }
            }
        }
    }
}
