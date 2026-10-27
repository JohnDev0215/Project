package com.amazon.music.platform.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class ParentalControlsSerializer extends JsonSerializer<ParentalControls> {
    public static final ParentalControlsSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ParentalControlsSerializer parentalControlsSerializer = new ParentalControlsSerializer();
        INSTANCE = parentalControlsSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.ParentalControlsSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ParentalControls.class, parentalControlsSerializer);
    }

    private ParentalControlsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ParentalControls parentalControls, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (parentalControls == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(parentalControls, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ParentalControls parentalControls, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("hasExplicitLanguage");
        SimpleSerializers.serializeBoolean(parentalControls.isHasExplicitLanguage(), jsonGenerator, serializerProvider);
    }
}
