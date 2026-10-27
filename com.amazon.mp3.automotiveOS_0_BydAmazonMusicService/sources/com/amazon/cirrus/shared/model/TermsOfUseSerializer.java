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
public class TermsOfUseSerializer extends JsonSerializer<TermsOfUse> {
    public static final TermsOfUseSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TermsOfUseSerializer termsOfUseSerializer = new TermsOfUseSerializer();
        INSTANCE = termsOfUseSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.shared.model.TermsOfUseSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(TermsOfUse.class, termsOfUseSerializer);
    }

    private TermsOfUseSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull TermsOfUse termsOfUse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (termsOfUse == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (termsOfUse instanceof TermsOfUseAccepted) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("com.amazon.cirrus.shared.model#TermsOfUseAccepted");
            TermsOfUseAcceptedSerializer.INSTANCE.serializeFields((TermsOfUseAccepted) termsOfUse, jsonGenerator, serializerProvider);
        } else {
            boolean z = termsOfUse instanceof TermsOfUseUrl;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(termsOfUse, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("com.amazon.cirrus.shared.model#TermsOfUseUrl");
                TermsOfUseUrlSerializer.INSTANCE.serializeFields((TermsOfUseUrl) termsOfUse, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(TermsOfUse termsOfUse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("version");
        SimpleSerializers.serializeString(termsOfUse.getVersion(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(termsOfUse.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(termsOfUse.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        SimpleSerializers.serializeString(termsOfUse.getType(), jsonGenerator, serializerProvider);
    }
}
