package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackresource.SyeCdn;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class SyeUrlResponse {
    public final Long duration;
    public final ImmutableList<SyeCdn> listOfCdns;
    public final Optional<Long> maxBitrateBps;
    public final String startTime;

    private SyeUrlResponse(Builder builder) {
        this.listOfCdns = (ImmutableList) Preconditions.checkNotNull(builder.listOfCdns, "Unexpected null field: listOfCdns");
        this.startTime = (String) Preconditions.checkNotNull(builder.startTime, "Unexpected null field: startTime");
        this.maxBitrateBps = Optional.fromNullable(builder.maxBitrateBps);
        this.duration = (Long) Preconditions.checkNotNull(builder.duration, "Unexpected null field: duration");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SyeUrlResponse)) {
            return false;
        }
        SyeUrlResponse syeUrlResponse = (SyeUrlResponse) obj;
        return Objects.equal(this.listOfCdns, syeUrlResponse.listOfCdns) && Objects.equal(this.startTime, syeUrlResponse.startTime) && Objects.equal(this.maxBitrateBps, syeUrlResponse.maxBitrateBps) && Objects.equal(this.duration, syeUrlResponse.duration);
    }

    public int hashCode() {
        return Objects.hashCode(this.listOfCdns, this.startTime, this.maxBitrateBps, this.duration);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("listOfCdns", this.listOfCdns).add("startTime", this.startTime).add("maxBitrateBps", this.maxBitrateBps).add("duration", this.duration).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Long duration;
        public ImmutableList<SyeCdn> listOfCdns;
        public Long maxBitrateBps;
        public String startTime;

        public SyeUrlResponse build() {
            return new SyeUrlResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SyeUrlResponse> {
        private final ListParser<SyeCdn> mSyeListOfCdnsParser;
        private final SimpleParsers.StringParser miso8601timestampParser;
        private final SimpleParsers.LongParser mlongTargetParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mSyeListOfCdnsParser = ListParser.newParser(new SyeCdn.Parser(objectMapper));
            this.miso8601timestampParser = SimpleParsers.StringParser.INSTANCE;
            this.mlongTargetParser = SimpleParsers.LongParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SyeUrlResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeUrlResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SyeUrlResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeUrlResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SyeUrlResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SyeUrlResponse");
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
                                b = 3;
                            }
                            break;
                        case 236155547:
                            if (next.equals("listOfCdns")) {
                                b = 0;
                            }
                            break;
                        case 1525959612:
                            if (next.equals("maxBitrateBps")) {
                                b = 2;
                            }
                            break;
                    }
                    Long l = null;
                    ImmutableList<SyeCdn> immutableList = null;
                    String str = null;
                    Long l2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mSyeListOfCdnsParser.parse(jsonNode2);
                        }
                        builder.listOfCdns = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.miso8601timestampParser.parse(jsonNode2);
                        }
                        builder.startTime = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            l2 = this.mlongTargetParser.parse(jsonNode2);
                        }
                        builder.maxBitrateBps = l2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            l = this.mlongTargetParser.parse(jsonNode2);
                        }
                        builder.duration = l;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SyeUrlResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.listOfCdns, this, "listOfCdns");
            JsonParsingUtils.checkNotNull(builder.startTime, this, "startTime");
            JsonParsingUtils.checkNotNull(builder.duration, this, "duration");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private SyeUrlResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 3;
                                    }
                                    break;
                                case 236155547:
                                    if (currentName.equals("listOfCdns")) {
                                        b = 0;
                                    }
                                    break;
                                case 1525959612:
                                    if (currentName.equals("maxBitrateBps")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            Long l = null;
                            ImmutableList<SyeCdn> immutableList = null;
                            String str = null;
                            Long l2 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mSyeListOfCdnsParser.parse(jsonParser);
                                }
                                builder.listOfCdns = immutableList;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.miso8601timestampParser.parse(jsonParser);
                                }
                                builder.startTime = str;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l2 = this.mlongTargetParser.parse(jsonParser);
                                }
                                builder.maxBitrateBps = l2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    l = this.mlongTargetParser.parse(jsonParser);
                                }
                                builder.duration = l;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SyeUrlResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.listOfCdns, this, "listOfCdns");
                    JsonParsingUtils.checkNotNull(builder.startTime, this, "startTime");
                    JsonParsingUtils.checkNotNull(builder.duration, this, "duration");
                    return builder.build();
                }
            }
        }
    }
}
