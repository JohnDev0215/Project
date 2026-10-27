package com.amazon.avod.playbackresourcev2;

import com.amazon.avod.playbackresourcev2.PlayReadyLicenseV2;
import com.amazon.avod.playbackresourcev2.Prsv2Error;
import com.amazon.avod.playbackresourcev2.WidevineLicenseV2;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DrmLicenseResourcesResponse {
    private final Optional<Prsv2Error> mError;
    private final Optional<PlayReadyLicenseV2> mPlayReadyLicense;
    private final Optional<WidevineLicenseV2> mWidevineLicense;

    private DrmLicenseResourcesResponse(@Nonnull Builder builder) {
        Preconditions.checkNotNull(builder, "builder");
        this.mPlayReadyLicense = Optional.fromNullable(builder.mPlayReadyLicenseV2);
        this.mWidevineLicense = Optional.fromNullable(builder.mWidevineLicense);
        if (builder.mMessage != null || builder.mCode != null) {
            Prsv2Error.Builder builder2 = new Prsv2Error.Builder();
            builder2.mCode = builder.mCode;
            builder2.mMessage = builder.mMessage;
            this.mError = Optional.of(builder2.build());
            return;
        }
        this.mError = Optional.absent();
    }

    @Nonnull
    public Optional<PlayReadyLicenseV2> getPlayReadyLicense() {
        return this.mPlayReadyLicense;
    }

    @Nonnull
    public Optional<WidevineLicenseV2> getWidevineLicense() {
        return this.mWidevineLicense;
    }

    @Nonnull
    public Optional<Prsv2Error> getError() {
        return this.mError;
    }

    static class Builder {
        private String mCode;
        private String mMessage;
        private PlayReadyLicenseV2 mPlayReadyLicenseV2;
        private WidevineLicenseV2 mWidevineLicense;

        Builder() {
        }

        DrmLicenseResourcesResponse build() {
            return new DrmLicenseResourcesResponse(this);
        }
    }

    public static class Parser extends PlaybackResourcesParserBase<DrmLicenseResourcesResponse> {
        private final PlayReadyLicenseV2.Parser mPlayReadyLicenseParser;
        private final SimpleParsers.StringParser mStringParser;
        private final WidevineLicenseV2.Parser mWidevineLicenseResourceParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, DrmLicenseResourcesResponse.class);
            this.mPlayReadyLicenseParser = new PlayReadyLicenseV2.Parser(objectMapper);
            this.mWidevineLicenseResourceParser = new WidevineLicenseV2.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public DrmLicenseResourcesResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DrmLicenseResourcesResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1892930428:
                            if (next.equals("widevineLicense")) {
                                b = 3;
                            }
                            break;
                        case 3059181:
                            if (next.equals("code")) {
                                b = 1;
                            }
                            break;
                        case 530671218:
                            if (next.equals("playReadyLicense")) {
                                b = 2;
                            }
                            break;
                        case 954925063:
                            if (next.equals("message")) {
                                b = 0;
                            }
                            break;
                    }
                    WidevineLicenseV2 widevineLicenseV2 = null;
                    String str = null;
                    String str2 = null;
                    PlayReadyLicenseV2 playReadyLicenseV2 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mMessage = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.mCode = str2;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            playReadyLicenseV2 = this.mPlayReadyLicenseParser.parse(jsonNode2);
                        }
                        builder.mPlayReadyLicenseV2 = playReadyLicenseV2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            widevineLicenseV2 = this.mWidevineLicenseResourceParser.parse(jsonNode2);
                        }
                        builder.mWidevineLicense = widevineLicenseV2;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DrmLicenseResourcesResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.amazon.avod.playbackresourcev2.PlaybackResourcesParserBase
        @Nonnull
        public DrmLicenseResourcesResponse parseInternal(@Nonnull JsonParser jsonParser) throws IOException {
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
                            case -1892930428:
                                if (currentName.equals("widevineLicense")) {
                                    b = 3;
                                }
                                break;
                            case 3059181:
                                if (currentName.equals("code")) {
                                    b = 1;
                                }
                                break;
                            case 530671218:
                                if (currentName.equals("playReadyLicense")) {
                                    b = 2;
                                }
                                break;
                            case 954925063:
                                if (currentName.equals("message")) {
                                    b = 0;
                                }
                                break;
                        }
                        String str = null;
                        WidevineLicenseV2 widevineLicenseV2 = null;
                        PlayReadyLicenseV2 playReadyLicenseV2 = null;
                        String str2 = null;
                        if (b == 0) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                str = this.mStringParser.parse(jsonParser);
                            }
                            builder.mMessage = str;
                        } else if (b == 1) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                str2 = this.mStringParser.parse(jsonParser);
                            }
                            builder.mCode = str2;
                        } else if (b == 2) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                playReadyLicenseV2 = this.mPlayReadyLicenseParser.parse(jsonParser);
                            }
                            builder.mPlayReadyLicenseV2 = playReadyLicenseV2;
                        } else if (b == 3) {
                            if (currentToken != JsonToken.VALUE_NULL) {
                                widevineLicenseV2 = this.mWidevineLicenseResourceParser.parse(jsonParser);
                            }
                            builder.mWidevineLicense = widevineLicenseV2;
                        } else {
                            jsonParser.skipChildren();
                        }
                    } catch (JsonContractException e) {
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DrmLicenseResourcesResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                }
                jsonTokenNextToken = jsonParser.nextToken();
            }
            return builder.build();
        }
    }
}
