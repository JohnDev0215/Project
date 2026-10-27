package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.atv.xrayv2.Biography;
import com.amazon.atv.xrayv2.Item;
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
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class Person extends Item {
    public final Optional<Biography> biography;
    public final String id;
    public final Optional<String> image;
    public final String name;
    public final Optional<ImmutableList<Role>> roles;

    private Person(Builder builder) {
        super(builder);
        this.name = (String) Preconditions.checkNotNull(builder.name, "Unexpected null field: name");
        this.id = (String) Preconditions.checkNotNull(builder.id, "Unexpected null field: id");
        this.biography = Optional.fromNullable(builder.biography);
        this.image = Optional.fromNullable(builder.image);
        this.roles = Optional.fromNullable(builder.roles);
    }

    @Override // com.amazon.atv.xrayv2.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person person = (Person) obj;
        return super.equals(obj) && Objects.equal(this.name, person.name) && Objects.equal(this.id, person.id) && Objects.equal(this.biography, person.biography) && Objects.equal(this.image, person.image) && Objects.equal(this.roles, person.roles);
    }

    @Override // com.amazon.atv.xrayv2.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.name, this.id, this.biography, this.image, this.roles);
    }

    @Override // com.amazon.atv.xrayv2.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.name).add("id", this.id).add("biography", this.biography).add(ContextMappingConstants.IMAGE, this.image).add("roles", this.roles).toString();
    }

    @NotThreadSafe
    public static class Builder extends Item.Builder {
        public Biography biography;
        public String id;
        public String image;
        public String name;
        public ImmutableList<Role> roles;

        public Person build() {
            return new Person(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Person> {
        private final Analytics.Parser mAnalyticsParser;
        private final Biography.Parser mBiographyParser;
        private final EnumParser<ItemType> mItemTypeParser;
        private final ListParser<Role> mRoleListParser;
        private final MapParser<String, String> mStringMapParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mBiographyParser = new Biography.Parser(objectMapper);
            this.mItemTypeParser = EnumParser.newParser(ItemType.class);
            this.mRoleListParser = ListParser.newParser(EnumParser.newParser(Role.class));
            this.mAnalyticsParser = new Analytics.Parser(objectMapper);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mStringMapParser = MapParser.newParser(stringParser, stringParser);
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Person parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Person:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Person parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Person:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Person parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            Analytics analytics;
            String str2;
            ImmutableMap<String, String> immutableMap;
            String str3;
            Biography biography;
            ItemType itemType;
            String str4;
            ImmutableList<Role> immutableList;
            JsonParsingUtils.throwIfNotObject(jsonNode, "Person");
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
                                b = 1;
                            }
                            break;
                        case -450004177:
                            if (next.equals("metadata")) {
                                b = 3;
                            }
                            break;
                        case 3355:
                            if (next.equals("id")) {
                                b = 4;
                            }
                            break;
                        case 3373707:
                            if (next.equals("name")) {
                                b = 0;
                            }
                            break;
                        case 3575610:
                            if (next.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                b = 6;
                            }
                            break;
                        case 60358643:
                            if (next.equals("biography")) {
                                b = 5;
                            }
                            break;
                        case 100313435:
                            if (next.equals(ContextMappingConstants.IMAGE)) {
                                b = 7;
                            }
                            break;
                        case 108695229:
                            if (next.equals("roles")) {
                                b = 8;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 9;
                            }
                            break;
                        case 2140311562:
                            if (next.equals("debugAttributes")) {
                                b = 2;
                            }
                            break;
                    }
                    str = null;
                    immutableList = null;
                    str4 = null;
                    itemType = null;
                    biography = null;
                    str3 = null;
                    immutableMap = null;
                    str2 = null;
                    analytics = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Person so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.name = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            analytics = this.mAnalyticsParser.parse(jsonNode2);
                        }
                        builder.analytics = analytics;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttributes = str2;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mStringMapParser.parse(jsonNode2);
                        }
                        builder.metadata = immutableMap;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.id = str3;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            biography = this.mBiographyParser.parse(jsonNode2);
                        }
                        builder.biography = biography;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            itemType = (ItemType) this.mItemTypeParser.parse(jsonNode2);
                        }
                        builder.type = itemType;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.image = str4;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mRoleListParser.parse(jsonNode2);
                        }
                        builder.roles = immutableList;
                        continue;
                    case 9:
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                        continue;
                        break;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Person so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            JsonParsingUtils.checkNotNull(builder.name, this, "name");
            JsonParsingUtils.checkNotNull(builder.id, this, "id");
            JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Person parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 1;
                                    }
                                    break;
                                case -450004177:
                                    if (currentName.equals("metadata")) {
                                        b = 3;
                                    }
                                    break;
                                case 3355:
                                    if (currentName.equals("id")) {
                                        b = 4;
                                    }
                                    break;
                                case 3373707:
                                    if (currentName.equals("name")) {
                                        b = 0;
                                    }
                                    break;
                                case 3575610:
                                    if (currentName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                                        b = 6;
                                    }
                                    break;
                                case 60358643:
                                    if (currentName.equals("biography")) {
                                        b = 5;
                                    }
                                    break;
                                case 100313435:
                                    if (currentName.equals(ContextMappingConstants.IMAGE)) {
                                        b = 7;
                                    }
                                    break;
                                case 108695229:
                                    if (currentName.equals("roles")) {
                                        b = 8;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 9;
                                    }
                                    break;
                                case 2140311562:
                                    if (currentName.equals("debugAttributes")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            String str = null;
                            ImmutableList<Role> immutableList = null;
                            String str2 = null;
                            ItemType itemType = null;
                            Biography biography = null;
                            String str3 = null;
                            ImmutableMap<String, String> immutableMap = null;
                            String str4 = null;
                            Analytics analytics = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.name = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        analytics = this.mAnalyticsParser.parse(jsonParser);
                                    }
                                    builder.analytics = analytics;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.debugAttributes = str4;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableMap = this.mStringMapParser.parse(jsonParser);
                                    }
                                    builder.metadata = immutableMap;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.id = str3;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        biography = this.mBiographyParser.parse(jsonParser);
                                    }
                                    builder.biography = biography;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        itemType = (ItemType) this.mItemTypeParser.parse(jsonParser);
                                    }
                                    builder.type = itemType;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.mStringParser.parse(jsonParser);
                                    }
                                    builder.image = str2;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mRoleListParser.parse(jsonParser);
                                    }
                                    builder.roles = immutableList;
                                    continue;
                                case 9:
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
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Person so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Person so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.name, this, "name");
                    JsonParsingUtils.checkNotNull(builder.id, this, "id");
                    JsonParsingUtils.checkNotNull(builder.type, this, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    return builder.build();
                }
            }
        }
    }
}
