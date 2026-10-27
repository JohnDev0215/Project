package com.amazon.cirrus.shared.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class TermsOfUseAcceptedSerializer extends JsonSerializer<TermsOfUseAccepted> {
    public static final TermsOfUseAcceptedSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TermsOfUseAcceptedSerializer termsOfUseAcceptedSerializer = new TermsOfUseAcceptedSerializer();
        INSTANCE = termsOfUseAcceptedSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.shared.model.TermsOfUseAcceptedSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(TermsOfUseAccepted.class, termsOfUseAcceptedSerializer);
    }

    private TermsOfUseAcceptedSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull TermsOfUseAccepted termsOfUseAccepted, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (termsOfUseAccepted == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(termsOfUseAccepted, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(TermsOfUseAccepted termsOfUseAccepted, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("acceptedDate");
        SimpleSerializers.serializeDate(termsOfUseAccepted.getAcceptedDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("version");
        SimpleSerializers.serializeString(termsOfUseAccepted.getVersion(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(termsOfUseAccepted.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(termsOfUseAccepted.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        SimpleSerializers.serializeString(termsOfUseAccepted.getType(), jsonGenerator, serializerProvider);
    }
}
