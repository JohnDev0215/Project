package com.amazon.avod.config.internal;

import com.amazon.avod.json.SimpleMapParser;
import com.amazon.avod.util.EmptyToNullFunction;
import com.amazon.avod.util.json.JacksonJsonStreamParser;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonValidator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class PlayerAppStartupConfigResponse {

    @Nonnull
    public final ImmutableMap<String, String> customerConfig;

    @Nonnull
    public final ImmutableMap<String, String> deviceConfig;

    PlayerAppStartupConfigResponse(@Nonnull Builder builder) {
        this.customerConfig = (ImmutableMap) Preconditions.checkNotNull(builder.customerConfig, "builder.customerConfig");
        this.deviceConfig = (ImmutableMap) Preconditions.checkNotNull(builder.deviceConfig, "builder.deviceConfig");
    }

    static class Builder {
        ImmutableMap<String, String> customerConfig = ImmutableMap.of();
        ImmutableMap<String, String> deviceConfig = ImmutableMap.of();

        Builder() {
        }

        @Nonnull
        public PlayerAppStartupConfigResponse build() {
            return new PlayerAppStartupConfigResponse(this);
        }
    }

    public static class Parser implements JacksonJsonStreamParser<PlayerAppStartupConfigResponse> {
        private final DeviceConfigParser mDeviceConfigParser = new DeviceConfigParser();
        private final SimpleMapParser mSimpleMapParser = new SimpleMapParser(new EmptyToNullFunction());

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public PlayerAppStartupConfigResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            Builder builder = new Builder();
            while (true) {
                JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                    if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                        String currentName = jsonParser.getCurrentName();
                        jsonParser.nextToken();
                        currentName.hashCode();
                        if (currentName.equals("deviceConfig")) {
                            builder.deviceConfig = this.mDeviceConfigParser.parse(jsonParser);
                        } else if (currentName.equals("customerConfig")) {
                            builder.customerConfig = this.mSimpleMapParser.parse(jsonParser);
                        } else {
                            jsonParser.skipChildren();
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
