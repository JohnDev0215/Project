package com.amazon.avod.playbackresourcev2;

import android.annotation.SuppressLint;
import com.amazon.atvplaybackresource.SyeUrlResponse;
import com.amazon.avod.playbackresourcev2.Prsv2Error;
import com.amazon.avod.playbackresourcev2.SyeCdnV2;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.DLog;
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
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.joda.time.Period;

/* JADX INFO: loaded from: classes2.dex */
public class SyeUrlResponseV2 {
    private final Optional<ImmutableList<SyeCdnV2>> mCdns;
    private final Optional<String> mDuration;
    private final Optional<Prsv2Error> mError;
    private final Optional<Integer> mMaxBitrateBps;
    private final Optional<String> mStartTime;

    SyeUrlResponseV2(@Nonnull Builder builder) {
        Preconditions.checkNotNull(builder, "builder");
        this.mCdns = Optional.fromNullable(builder.mCdns);
        this.mDuration = Optional.fromNullable(builder.mDuration);
        this.mMaxBitrateBps = Optional.fromNullable(builder.mMaxBitrateBps);
        this.mStartTime = Optional.fromNullable(builder.mStartTime);
        this.mError = Optional.fromNullable(builder.mError);
    }

    @Nonnull
    public Optional<Prsv2Error> getError() {
        return this.mError;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("cdns", this.mCdns).add("duration", this.mDuration).add("maxBitrateBps", this.mMaxBitrateBps).add("startTime", this.mStartTime).add("error", this.mError).toString();
    }

    @Nullable
    @SuppressLint({"VisibleForTests"})
    public SyeUrlResponse transformToSyeUrlResponse() {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        if (this.mError.isPresent()) {
            return null;
        }
        if (!this.mCdns.isPresent() || !this.mStartTime.isPresent() || !this.mMaxBitrateBps.isPresent() || !this.mDuration.isPresent()) {
            DLog.warnf("SyeUrlResponseV2 %s transformToSyeUrlResponse returning null.", this);
            return null;
        }
        UnmodifiableIterator<SyeCdnV2> it = this.mCdns.get().iterator();
        while (it.hasNext()) {
            builder.addAll((Iterable) it.next().transformToSyeCdnList());
        }
        SyeUrlResponse.Builder builder2 = new SyeUrlResponse.Builder();
        builder2.listOfCdns = builder.build();
        builder2.startTime = this.mStartTime.get();
        builder2.maxBitrateBps = Long.valueOf(this.mMaxBitrateBps.get().intValue());
        builder2.duration = Long.valueOf(new Period(this.mDuration.get()).toStandardSeconds().getSeconds());
        return builder2.build();
    }

    static class Builder {
        private ImmutableList<SyeCdnV2> mCdns;
        private String mDuration;
        private Prsv2Error mError;
        private Integer mMaxBitrateBps;
        private String mStartTime;

        Builder() {
        }

        @Nonnull
        SyeUrlResponseV2 build() {
            return new SyeUrlResponseV2(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<SyeUrlResponseV2> {
        private final Prsv2Error.Parser mErrorParser;
        private final SimpleParsers.IntegerParser mIntegerParser;
        private final SimpleParsers.StringParser mStringParser;
        private final ListParser<SyeCdnV2> mSyeUrlsCdnListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, SyeUrlResponseV2.class);
            this.mSyeUrlsCdnListParser = ListParser.newParser(new SyeCdnV2.Parser(objectMapper));
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mIntegerParser = SimpleParsers.IntegerParser.INSTANCE;
            this.mErrorParser = new Prsv2Error.Parser(objectMapper);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public SyeUrlResponseV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SyeUrlResponseV2");
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
                                b = 3;
                            }
                            break;
                        case -1992012396:
                            if (next.equals("duration")) {
                                b = 1;
                            }
                            break;
                        case 3048934:
                            if (next.equals("cdns")) {
                                b = 0;
                            }
                            break;
                        case 96784904:
                            if (next.equals("error")) {
                                b = 4;
                            }
                            break;
                        case 1525959612:
                            if (next.equals("maxBitrateBps")) {
                                b = 2;
                            }
                            break;
                    }
                    Prsv2Error prsv2Error = null;
                    ImmutableList<SyeCdnV2> immutableList = null;
                    String str = null;
                    Integer num = null;
                    String str2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mSyeUrlsCdnListParser.parse(jsonNode2);
                        }
                        builder.mCdns = immutableList;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mDuration = str;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            num = this.mIntegerParser.parse(jsonNode2);
                        }
                        builder.mMaxBitrateBps = num;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mStartTime = str2;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            prsv2Error = this.mErrorParser.parse(jsonNode2);
                        }
                        builder.mError = prsv2Error;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SyeUrlResponseV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public SyeUrlResponseV2 parseInternal(@Nonnull JsonParser jsonParser) throws IOException {
            return parseInternal(jsonParser, new Builder());
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        protected SyeUrlResponseV2 parseInternal(@Nonnull JsonParser jsonParser, @Nonnull Builder builder) throws IOException {
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            while (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    jsonParser.nextToken();
                    JsonToken currentToken = jsonParser.getCurrentToken();
                    byte b = -1;
                    try {
                        switch (currentName.hashCode()) {
                            case -2129294769:
                                if (currentName.equals("startTime")) {
                                    b = 4;
                                }
                                break;
                            case -1992012396:
                                if (currentName.equals("duration")) {
                                    b = 2;
                                }
                                break;
                            case -934426595:
                                if (currentName.equals("result")) {
                                    b = 0;
                                }
                                break;
                            case 3048934:
                                if (currentName.equals("cdns")) {
                                    b = 1;
                                }
                                break;
                            case 96784904:
                                if (currentName.equals("error")) {
                                    b = 5;
                                }
                                break;
                            case 1525959612:
                                if (currentName.equals("maxBitrateBps")) {
                                    b = 3;
                                }
                                break;
                        }
                        if (b != 0) {
                            ImmutableList<SyeCdnV2> immutableList = null;
                            Prsv2Error prsv2Error = null;
                            String str = null;
                            Integer num = null;
                            String str2 = null;
                            if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mSyeUrlsCdnListParser.parse(jsonParser);
                                }
                                builder.mCdns = immutableList;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.mDuration = str2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    num = this.mIntegerParser.parse(jsonParser);
                                }
                                builder.mMaxBitrateBps = num;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.mStartTime = str;
                            } else if (b == 5) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    prsv2Error = this.mErrorParser.parse(jsonParser);
                                }
                                builder.mError = prsv2Error;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } else {
                            parseInternal(jsonParser, builder);
                        }
                    } catch (JsonContractException e) {
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SyeUrlResponseV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                }
                jsonTokenNextToken = jsonParser.nextToken();
            }
            return builder.build();
        }
    }
}
