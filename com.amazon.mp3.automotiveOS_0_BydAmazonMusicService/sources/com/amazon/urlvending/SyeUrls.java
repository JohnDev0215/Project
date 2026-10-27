package com.amazon.urlvending;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.SyeDomain;
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
public class SyeUrls {
    public final Optional<Long> duration;
    public final Optional<Long> maxBitrateBps;
    public final Optional<String> startTime;
    public final Optional<ImmutableList<SyeDomain>> syeDomainList;

    private SyeUrls(Builder builder) {
        this.duration = Optional.fromNullable(builder.duration);
        this.startTime = Optional.fromNullable(builder.startTime);
        this.syeDomainList = Optional.fromNullable(builder.syeDomainList);
        this.maxBitrateBps = Optional.fromNullable(builder.maxBitrateBps);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SyeUrls)) {
            return false;
        }
        SyeUrls syeUrls = (SyeUrls) obj;
        return Objects.equal(this.duration, syeUrls.duration) && Objects.equal(this.startTime, syeUrls.startTime) && Objects.equal(this.syeDomainList, syeUrls.syeDomainList) && Objects.equal(this.maxBitrateBps, syeUrls.maxBitrateBps);
    }

    public int hashCode() {
        return Objects.hashCode(this.duration, this.startTime, this.syeDomainList, this.maxBitrateBps);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("duration", this.duration).add("startTime", this.startTime).add("syeDomainList", this.syeDomainList).add("maxBitrateBps", this.maxBitrateBps).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Long duration;
        public Long maxBitrateBps;
        public String startTime;
        public ImmutableList<SyeDomain> syeDomainList;

        public SyeUrls build() {
            return new SyeUrls(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SyeUrls> {
        private final SimpleParsers.LongParser mLongParser;
        private final SimpleParsers.StringParser mStringParser;
        private final ListParser<SyeDomain> mSyeDomainListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSyeDomainListParser = ListParser.newParser(new SyeDomain.Parser(objectMapper));
            this.mLongParser = SimpleParsers.LongParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SyeUrls parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeUrls:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SyeUrls parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeUrls:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SyeUrls parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SyeUrls");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -2129294769:
                            if (next.equals("startTime")) {
                                b = 1;
                            }
                            break;
                        case -1992012396:
                            if (next.equals("duration")) {
                                b = 0;
                            }
                            break;
                        case -1296601183:
                            if (next.equals("syeDomainList")) {
                                b = 2;
                            }
                            break;
                        case 1525959612:
                            if (next.equals("maxBitrateBps")) {
                                b = 3;
                            }
                            break;
                    }
                    Long l = null;
                    Long l2 = null;
                    String str = null;
                    ImmutableList<SyeDomain> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            l2 = this.mLongParser.parse(jsonNode2);
                        }
                        builder.duration = l2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.startTime = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mSyeDomainListParser.parse(jsonNode2);
                        }
                        builder.syeDomainList = immutableList;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            l = this.mLongParser.parse(jsonNode2);
                        }
                        builder.maxBitrateBps = l;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SyeUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SyeUrls parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -2129294769:
                                    if (currentName.equals("startTime")) {
                                        b = 1;
                                    }
                                    break;
                                case -1992012396:
                                    if (currentName.equals("duration")) {
                                        b = 0;
                                    }
                                    break;
                                case -1296601183:
                                    if (currentName.equals("syeDomainList")) {
                                        b = 2;
                                    }
                                    break;
                                case 1525959612:
                                    if (currentName.equals("maxBitrateBps")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            Long l = null;
                            Long l2 = null;
                            String str = null;
                            ImmutableList<SyeDomain> immutableList = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l2 = this.mLongParser.parse(jsonParser);
                                }
                                builder.duration = l2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.startTime = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mSyeDomainListParser.parse(jsonParser);
                                }
                                builder.syeDomainList = immutableList;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mLongParser.parse(jsonParser);
                                }
                                builder.maxBitrateBps = l;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SyeUrls so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
