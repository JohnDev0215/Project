package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.UrlProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public class AdUrlsProperties {
    public final Optional<ImmutableList<UrlProperties>> adUrlsList;
    public final Optional<Long> duration;
    public final boolean stitched;
    public final Optional<String> streamingTechnology;

    private AdUrlsProperties(Builder builder) {
        this.duration = Optional.fromNullable(builder.duration);
        this.adUrlsList = Optional.fromNullable(builder.adUrlsList);
        this.stitched = builder.stitched;
        this.streamingTechnology = Optional.fromNullable(builder.streamingTechnology);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdUrlsProperties)) {
            return false;
        }
        AdUrlsProperties adUrlsProperties = (AdUrlsProperties) obj;
        return Objects.equal(this.duration, adUrlsProperties.duration) && Objects.equal(this.adUrlsList, adUrlsProperties.adUrlsList) && Objects.equal(Boolean.valueOf(this.stitched), Boolean.valueOf(adUrlsProperties.stitched)) && Objects.equal(this.streamingTechnology, adUrlsProperties.streamingTechnology);
    }

    public int hashCode() {
        return Objects.hashCode(this.duration, this.adUrlsList, Boolean.valueOf(this.stitched), this.streamingTechnology);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("duration", this.duration).add("adUrlsList", this.adUrlsList).add("stitched", this.stitched).add("streamingTechnology", this.streamingTechnology).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public ImmutableList<UrlProperties> adUrlsList;
        public Long duration;
        public boolean stitched;
        public String streamingTechnology;

        public AdUrlsProperties build() {
            return new AdUrlsProperties(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AdUrlsProperties> {
        private final ListParser<UrlProperties> mAdUrlsListParser;
        private final SimpleParsers.LongParser mLongParser;
        private final SimpleParsers.StringParser mStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAdUrlsListParser = ListParser.newParser(new UrlProperties.Parser(objectMapper));
            this.mLongParser = SimpleParsers.LongParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AdUrlsProperties parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AdUrlsProperties:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AdUrlsProperties parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AdUrlsProperties:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AdUrlsProperties parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AdUrlsProperties");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1992012396:
                            if (next.equals("duration")) {
                                b = 0;
                            }
                            break;
                        case -1271404786:
                            if (next.equals("streamingTechnology")) {
                                b = 3;
                            }
                            break;
                        case 1167956197:
                            if (next.equals("adUrlsList")) {
                                b = 1;
                            }
                            break;
                        case 1547179280:
                            if (next.equals("stitched")) {
                                b = 2;
                            }
                            break;
                    }
                    String str = null;
                    Long l = null;
                    ImmutableList<UrlProperties> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            l = this.mLongParser.parse(jsonNode2);
                        }
                        builder.duration = l;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mAdUrlsListParser.parse(jsonNode2);
                        }
                        builder.adUrlsList = immutableList;
                    } else if (b == 2) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field stitched can't be null");
                        }
                        builder.stitched = SimpleParsers.parsePrimitiveBoolean(jsonNode2);
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.streamingTechnology = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AdUrlsProperties so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AdUrlsProperties parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1992012396:
                                    if (currentName.equals("duration")) {
                                        b = 0;
                                    }
                                    break;
                                case -1271404786:
                                    if (currentName.equals("streamingTechnology")) {
                                        b = 3;
                                    }
                                    break;
                                case 1167956197:
                                    if (currentName.equals("adUrlsList")) {
                                        b = 1;
                                    }
                                    break;
                                case 1547179280:
                                    if (currentName.equals("stitched")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            String str = null;
                            Long l = null;
                            ImmutableList<UrlProperties> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mLongParser.parse(jsonParser);
                                }
                                builder.duration = l;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mAdUrlsListParser.parse(jsonParser);
                                }
                                builder.adUrlsList = immutableList;
                            } else if (b == 2) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field stitched can't be null");
                                }
                                builder.stitched = SimpleParsers.parsePrimitiveBoolean(jsonParser);
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.streamingTechnology = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AdUrlsProperties so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
