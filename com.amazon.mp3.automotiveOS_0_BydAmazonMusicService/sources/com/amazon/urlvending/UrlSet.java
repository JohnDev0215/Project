package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.urlvending.types.Encode;
import com.amazon.urlvending.types.UrlSetsMapKey;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public class UrlSet {
    public final Optional<Encode> encode;
    public final Optional<String> rank;
    public final Optional<String> sessionId;
    public final Optional<ImmutableMap<UrlSetsMapKey, ImmutableMap<String, String>>> urlsMap;
    public final Optional<String> urlsMapKeyType;

    private UrlSet(Builder builder) {
        this.rank = Optional.fromNullable(builder.rank);
        this.sessionId = Optional.fromNullable(builder.sessionId);
        this.encode = Optional.fromNullable(builder.encode);
        this.urlsMapKeyType = Optional.fromNullable(builder.urlsMapKeyType);
        this.urlsMap = Optional.fromNullable(builder.urlsMap);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UrlSet)) {
            return false;
        }
        UrlSet urlSet = (UrlSet) obj;
        return Objects.equal(this.rank, urlSet.rank) && Objects.equal(this.sessionId, urlSet.sessionId) && Objects.equal(this.encode, urlSet.encode) && Objects.equal(this.urlsMapKeyType, urlSet.urlsMapKeyType) && Objects.equal(this.urlsMap, urlSet.urlsMap);
    }

    public int hashCode() {
        return Objects.hashCode(this.rank, this.sessionId, this.encode, this.urlsMapKeyType, this.urlsMap);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("rank", this.rank).add(Splash.PARAMS_SESSION_ID, this.sessionId).add("encode", this.encode).add("urlsMapKeyType", this.urlsMapKeyType).add("urlsMap", this.urlsMap).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Encode encode;
        public String rank;
        public String sessionId;
        public ImmutableMap<UrlSetsMapKey, ImmutableMap<String, String>> urlsMap;
        public String urlsMapKeyType;

        public UrlSet build() {
            return new UrlSet(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<UrlSet> {
        private final EnumParser<Encode> mEncodeParser;
        private final SimpleParsers.StringParser mStringParser;
        private final MapParser<UrlSetsMapKey, ImmutableMap<String, String>> mUrlsMapParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mEncodeParser = EnumParser.newParser(Encode.class);
            EnumParser enumParserNewParser = EnumParser.newParser(UrlSetsMapKey.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mUrlsMapParser = MapParser.newParser(enumParserNewParser, MapParser.newParser(stringParser, stringParser));
            this.mStringParser = stringParser;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public UrlSet parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("UrlSet:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public UrlSet parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("UrlSet:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private UrlSet parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "UrlSet");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1298776554:
                            if (next.equals("encode")) {
                                b = 2;
                            }
                            break;
                        case -544553663:
                            if (next.equals("urlsMapKeyType")) {
                                b = 3;
                            }
                            break;
                        case -169285160:
                            if (next.equals("urlsMap")) {
                                b = 4;
                            }
                            break;
                        case 3492908:
                            if (next.equals("rank")) {
                                b = 0;
                            }
                            break;
                        case 607796817:
                            if (next.equals(Splash.PARAMS_SESSION_ID)) {
                                b = 1;
                            }
                            break;
                    }
                    ImmutableMap<UrlSetsMapKey, ImmutableMap<String, String>> immutableMap = null;
                    String str = null;
                    String str2 = null;
                    Encode encode = null;
                    String str3 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.rank = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.sessionId = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            encode = (Encode) this.mEncodeParser.parse(jsonNode2);
                        }
                        builder.encode = encode;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.urlsMapKeyType = str3;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            immutableMap = this.mUrlsMapParser.parse(jsonNode2);
                        }
                        builder.urlsMap = immutableMap;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing UrlSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private UrlSet parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1298776554:
                                    if (currentName.equals("encode")) {
                                        b = 2;
                                    }
                                    break;
                                case -544553663:
                                    if (currentName.equals("urlsMapKeyType")) {
                                        b = 3;
                                    }
                                    break;
                                case -169285160:
                                    if (currentName.equals("urlsMap")) {
                                        b = 4;
                                    }
                                    break;
                                case 3492908:
                                    if (currentName.equals("rank")) {
                                        b = 0;
                                    }
                                    break;
                                case 607796817:
                                    if (currentName.equals(Splash.PARAMS_SESSION_ID)) {
                                        b = 1;
                                    }
                                    break;
                            }
                            ImmutableMap<UrlSetsMapKey, ImmutableMap<String, String>> immutableMap = null;
                            String str = null;
                            String str2 = null;
                            Encode encode = null;
                            String str3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.rank = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.sessionId = str2;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    encode = (Encode) this.mEncodeParser.parse(jsonParser);
                                }
                                builder.encode = encode;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mStringParser.parse(jsonParser);
                                }
                                builder.urlsMapKeyType = str3;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableMap = this.mUrlsMapParser.parse(jsonParser);
                                }
                                builder.urlsMap = immutableMap;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing UrlSet so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
