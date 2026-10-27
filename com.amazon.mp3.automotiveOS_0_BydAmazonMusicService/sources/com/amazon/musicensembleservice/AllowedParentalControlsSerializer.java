package com.amazon.musicensembleservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class AllowedParentalControlsSerializer extends JsonSerializer<AllowedParentalControls> {
    public static final AllowedParentalControlsSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AllowedParentalControlsSerializer allowedParentalControlsSerializer = new AllowedParentalControlsSerializer();
        INSTANCE = allowedParentalControlsSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.AllowedParentalControlsSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AllowedParentalControls.class, allowedParentalControlsSerializer);
    }

    private AllowedParentalControlsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AllowedParentalControls allowedParentalControls, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (allowedParentalControls == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(allowedParentalControls, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AllowedParentalControls allowedParentalControls, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("hasExplicitLanguage");
        SimpleSerializers.serializeBoolean(allowedParentalControls.isHasExplicitLanguage(), jsonGenerator, serializerProvider);
    }
}
