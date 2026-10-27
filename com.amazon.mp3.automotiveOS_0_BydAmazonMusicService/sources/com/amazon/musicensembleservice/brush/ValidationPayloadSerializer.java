package com.amazon.musicensembleservice.brush;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class ValidationPayloadSerializer extends JsonSerializer<ValidationPayload> {
    public static final ValidationPayloadSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ValidationPayloadSerializer validationPayloadSerializer = new ValidationPayloadSerializer();
        INSTANCE = validationPayloadSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.ValidationPayloadSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ValidationPayload.class, validationPayloadSerializer);
    }

    private ValidationPayloadSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ValidationPayload validationPayload, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (validationPayload == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(validationPayload, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ValidationPayload validationPayload, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("widgetGroupId");
        SimpleSerializers.serializeString(validationPayload.getWidgetGroupId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("includeAllWidgets");
        SimpleSerializers.serializeBoolean(validationPayload.isIncludeAllWidgets(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("campaignMetadata");
        MetadataMapSerializer.INSTANCE.serialize(validationPayload.getCampaignMetadata(), jsonGenerator, serializerProvider);
    }
}
