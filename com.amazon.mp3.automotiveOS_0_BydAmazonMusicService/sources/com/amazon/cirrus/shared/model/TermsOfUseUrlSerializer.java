package com.amazon.cirrus.shared.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class TermsOfUseUrlSerializer extends JsonSerializer<TermsOfUseUrl> {
    public static final TermsOfUseUrlSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TermsOfUseUrlSerializer termsOfUseUrlSerializer = new TermsOfUseUrlSerializer();
        INSTANCE = termsOfUseUrlSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.shared.model.TermsOfUseUrlSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(TermsOfUseUrl.class, termsOfUseUrlSerializer);
    }

    private TermsOfUseUrlSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull TermsOfUseUrl termsOfUseUrl, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (termsOfUseUrl == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(termsOfUseUrl, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(TermsOfUseUrl termsOfUseUrl, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("version");
        SimpleSerializers.serializeString(termsOfUseUrl.getVersion(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(termsOfUseUrl.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(termsOfUseUrl.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ImagesContract.URL);
        SimpleSerializers.serializeString(termsOfUseUrl.getUrl(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        SimpleSerializers.serializeString(termsOfUseUrl.getType(), jsonGenerator, serializerProvider);
    }
}
