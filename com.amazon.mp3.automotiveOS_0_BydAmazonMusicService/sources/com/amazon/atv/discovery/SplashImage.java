package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.discovery.Item;
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
public class SplashImage extends Item {
    public final String identifier;
    public final Optional<String> imageDescription;
    public final ImmutableMap<String, String> images;

    private SplashImage(Builder builder) {
        super(builder);
        this.imageDescription = Optional.fromNullable(builder.imageDescription);
        this.identifier = (String) Preconditions.checkNotNull(builder.identifier, "Unexpected null field: identifier");
        this.images = (ImmutableMap) Preconditions.checkNotNull(builder.images, "Unexpected null field: images");
    }

    @Override // com.amazon.atv.discovery.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SplashImage)) {
            return false;
        }
        SplashImage splashImage = (SplashImage) obj;
        return super.equals(obj) && Objects.equal(this.imageDescription, splashImage.imageDescription) && Objects.equal(this.identifier, splashImage.identifier) && Objects.equal(this.images, splashImage.images);
    }

    @Override // com.amazon.atv.discovery.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.imageDescription, this.identifier, this.images);
    }

    @Override // com.amazon.atv.discovery.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("imageDescription", this.imageDescription).add("identifier", this.identifier).add("images", this.images).toString();
    }

    @NotThreadSafe
    public static class Builder extends Item.Builder {
        public String identifier;
        public String imageDescription;
        public ImmutableMap<String, String> images;

        public SplashImage build() {
            return new SplashImage(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SplashImage> {
        private final Analytics.Parser mAnalyticsParser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SplashImage parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SplashImage:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SplashImage parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SplashImage:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SplashImage parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ItemType itemType;
            String str;
            String str2;
            ImmutableMap<String, String> immutableMap;
            Analytics analytics;
            ImmutableMap<String, String> immutableMap2;
            String str3;
            JsonParsingUtils.throwIfNotObject(jsonNode, "SplashImage");
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
                                b = 5;
                            }
                            break;
                        case -1618432855:
                            if (next.equals("identifier")) {
                                b = 3;
                            }
                            break;
                        case -1185250696:
                            if (next.equals("images")) {
                                b = 6;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 4;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 0;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 1;
                            }
                            break;
                        case 1488366497:
                            if (next.equals("imageDescription")) {
                                b = 2;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 7;
                            }
                            break;
                    }
                    itemType = null;
                    str3 = null;
                    immutableMap2 = null;
                    analytics = null;
                    immutableMap = null;
                    str2 = null;
                    str = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SplashImage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            itemType = (ItemType) this.mItemTypeParser.parse(jsonNode2);
                        }
                        builder.type = itemType;
                        continue;
                    case 1:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        continue;
                        break;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.imageDescription = str;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.identifier = str2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mStringMapParser.parse(jsonNode2);
                        }
                        builder.metadata = immutableMap;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableMap2 = this.mStringMapParser.parse(jsonNode2);
                        }
                        builder.images = immutableMap2;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = str3;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SplashImage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.identifier, this, "identifier");
            JsonParsingUtils.checkNotNull(builder.images, this, "images");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SplashImage parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 5;
                                    }
                                    break;
                                case -1618432855:
                                    if (currentName.equals("identifier")) {
                                        b = 3;
                                    }
                                    break;
                                case -1185250696:
                                    if (currentName.equals("images")) {
                                        b = 6;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 4;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 0;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 1;
                                    }
                                    break;
                                case 1488366497:
                                    if (currentName.equals("imageDescription")) {
                                        b = 2;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = 7;
                                    }
                                    break;
                            }
                            ItemType itemType = null;
                            String str = null;
                            ImmutableMap<String, String> immutableMap = null;
                            Analytics analytics = null;
                            ImmutableMap<String, String> immutableMap2 = null;
                            String str2 = null;
                            String str3 = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    continue;
                                case 1:
                                    if (currentToken == JsonToken.VALUE_NULL) {
                                        throw new JsonContractException("primitive field version can't be null");
                                    }
                                    builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                                    continue;
                                    break;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.imageDescription = str3;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.identifier = str2;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap2 = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap2;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.images = immutableMap;
                                    continue;
                                case 7:
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
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SplashImage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SplashImage so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.identifier, this, "identifier");
                    JsonParsingUtils.checkNotNull(builder.images, this, "images");
                    return builder.build();
                }
            }
        }
    }
}
