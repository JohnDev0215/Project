package com.amazon.musicplaylist.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class ParentalControlSettingsSerializer extends JsonSerializer<ParentalControlSettings> {
    public static final ParentalControlSettingsSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ParentalControlSettingsSerializer parentalControlSettingsSerializer = new ParentalControlSettingsSerializer();
        INSTANCE = parentalControlSettingsSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.ParentalControlSettingsSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ParentalControlSettings.class, parentalControlSettingsSerializer);
    }

    private ParentalControlSettingsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ParentalControlSettings parentalControlSettings, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (parentalControlSettings == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(parentalControlSettings, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ParentalControlSettings parentalControlSettings, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("explicitLanguageAllowed");
        SimpleSerializers.serializePrimitiveBoolean(parentalControlSettings.isExplicitLanguageAllowed(), jsonGenerator, serializerProvider);
    }
}
