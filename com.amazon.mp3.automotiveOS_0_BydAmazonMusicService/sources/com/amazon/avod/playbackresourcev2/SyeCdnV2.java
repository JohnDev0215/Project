package com.amazon.avod.playbackresourcev2;

import android.annotation.SuppressLint;
import com.amazon.atvplaybackresource.SyeCdn;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.urlvending.SyeFrontendUrl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SyeCdnV2 {
    private final Optional<String> mChannelId;
    private final Optional<List<SyeFrontendUrl>> mFrontendUrls;
    private final Optional<String> mName;
    private final Optional<String> mToken;

    private SyeCdnV2(Builder builder) {
        this.mName = Optional.fromNullable(builder.mName);
        this.mChannelId = Optional.fromNullable(builder.mChannelId);
        this.mFrontendUrls = Optional.fromNullable(builder.mFrontendUrls);
        this.mToken = Optional.fromNullable(builder.mToken);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.mName).add("channelId", this.mChannelId).add("frontendUrls", this.mFrontendUrls).add("token", this.mToken).toString();
    }

    @Nonnull
    @SuppressLint({"VisibleForTests"})
    public List<SyeCdn> transformToSyeCdnList() {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        if (!this.mFrontendUrls.isPresent()) {
            DLog.warnf("SyeCdnV2 %s transformToSyeCdnList returning null list", this);
            return builder.build();
        }
        for (SyeFrontendUrl syeFrontendUrl : this.mFrontendUrls.get()) {
            if (!syeFrontendUrl.frontendUrl.isPresent() || !syeFrontendUrl.urlSetId.isPresent() || !this.mName.isPresent() || !this.mToken.isPresent() || !this.mChannelId.isPresent()) {
                DLog.warnf("SyeCdnV2 transformToSyeCdnList skipped frontendUrl %s", syeFrontendUrl);
            } else {
                SyeCdn.Builder builder2 = new SyeCdn.Builder();
                builder2.frontendUrl = syeFrontendUrl.frontendUrl.get();
                builder2.cdnName = this.mName.get();
                builder2.urlSetId = syeFrontendUrl.urlSetId.get();
                builder2.token = this.mToken.get();
                builder2.channelId = this.mChannelId.get();
                builder.add(builder2.build());
            }
        }
        return builder.build();
    }

    static class Builder {
        public String mChannelId;
        public List<SyeFrontendUrl> mFrontendUrls;
        public String mName;
        public String mToken;

        Builder() {
        }

        @Nonnull
        SyeCdnV2 build() {
            return new SyeCdnV2(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<SyeCdnV2> {
        private final SimpleParsers.StringParser mStringParser;
        private final ListParser<SyeFrontendUrl> mSyeFrontendUrlListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, SyeCdnV2.class);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mSyeFrontendUrlListParser = ListParser.newParser(new SyeFrontendUrl.Parser(objectMapper));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public SyeCdnV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "SyeCdnV2");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1306815978:
                            if (next.equals("frontendUrls")) {
                                b = 2;
                            }
                            break;
                        case 3373707:
                            if (next.equals("name")) {
                                b = 0;
                            }
                            break;
                        case 110541305:
                            if (next.equals("token")) {
                                b = 3;
                            }
                            break;
                        case 1461735806:
                            if (next.equals("channelId")) {
                                b = 1;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    ImmutableList<SyeFrontendUrl> immutableList = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mName = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mChannelId = str3;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mSyeFrontendUrlListParser.parse(jsonNode2);
                        }
                        builder.mFrontendUrls = immutableList;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mToken = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing SyeCdnV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public SyeCdnV2 parseInternal(@Nonnull JsonParser jsonParser) throws IOException {
            Builder builder = new Builder();
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
                            case -1306815978:
                                if (currentName.equals("frontendUrls")) {
                                    b = 2;
                                }
                                break;
                            case -934426595:
                                if (currentName.equals("result")) {
                                    b = 4;
                                }
                                break;
                            case 3373707:
                                if (currentName.equals("name")) {
                                    b = 0;
                                }
                                break;
                            case 110541305:
                                if (currentName.equals("token")) {
                                    b = 3;
                                }
                                break;
                            case 1461735806:
                                if (currentName.equals("channelId")) {
                                    b = 1;
                                }
                                break;
                        }
                        String str = null;
                        String str2 = null;
                        ImmutableList<SyeFrontendUrl> immutableList = null;
                        String str3 = null;
                        if (b == 0) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                str = this.mStringParser.parse(jsonParser);
                            }
                            builder.mName = str;
                        } else if (b == 1) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                str3 = this.mStringParser.parse(jsonParser);
                            }
                            builder.mChannelId = str3;
                        } else if (b == 2) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                immutableList = this.mSyeFrontendUrlListParser.parse(jsonParser);
                            }
                            builder.mFrontendUrls = immutableList;
                        } else if (b == 3) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                str2 = this.mStringParser.parse(jsonParser);
                            }
                            builder.mToken = str2;
                        } else if (b != 4) {
                            jsonParser.skipChildren();
                        }
                    } catch (JsonContractException e) {
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing SyeCdnV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                }
                jsonTokenNextToken = jsonParser.nextToken();
            }
            return builder.build();
        }
    }
}
